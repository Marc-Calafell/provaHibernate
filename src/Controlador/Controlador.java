/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Vista.Vista;
import Model.Model;
import entitats.Bonsai;
import entitats.Macetes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author mark
 */
public class Controlador {
    
    public Model m;
    private final Vista v;
    int id;
    String nom;
    String nomBotanic;
    private int filasel=-1;
    
    

    public Controlador(Model m, Vista v) {

        this.m = m;
        this.v = v;
        
       
        carregaTaula((ArrayList) m.selectAll(),v.getjTable1(),Bonsai.class);
        
        amagaObjecte();
        
        v.setVisible(true);
        control();
    
        
    }
    
    public void control() {
        
        ActionListener actionListener = new ActionListener() {
            
            int filasel = v.getjTable1().getSelectedRow();
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource().equals(v.getAddBTN())) {
                    Bonsai insert = new Bonsai();
                    insert.set2_nom(v.getNomJTF().getText());
                    insert.set3_nomBotanic(v.getNomBotanicJTF().getText());
                    
                    m.insert(insert);
                    
                    actualitzaTaula();
                            
                } else if(actionEvent.getSource().equals(v.getUpdateBTN())){
                    Bonsai update= (Bonsai) v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 5);
                    update.set2_nom(v.getNomJTF().getText());
                    update.set3_nomBotanic(v.getNomBotanicJTF().getText());
                    
                    m.update(update);
                    
                    actualitzaTaula();
                    
                } else if(actionEvent.getSource().equals(v.getDeleteBTN())){
                    Bonsai esborrar= (Bonsai) v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 5);
                    
                    m.delete(esborrar);
                     
                     actualitzaTaula();
                }   
            }      
        };
        
        v.getAddBTN().addActionListener(actionListener);
        v.getUpdateBTN().addActionListener(actionListener);
        v.getDeleteBTN().addActionListener(actionListener);
        
        MouseAdapter mouseAdapter=new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
                
                try {
                   int filasel = v.getjTable1().getSelectedRow();
                    if (filasel != -1) {
                      id = Integer.parseInt(v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 0).toString());
                        System.out.println(id);
                        
                        nom = (String) v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 1);
                        v.getNomJTF().setText(nom);
                        System.out.println(nom);
                        
                        nomBotanic = (String) v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 2);
                        v.getNomBotanicJTF().setText(nomBotanic);
                        System.out.println(nomBotanic);
                        
                        ArrayList maceta =  (ArrayList) v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 3); 
                        carregaTaula(maceta,v.getTaulaMacetes(),Macetes.class );
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Ha petat: " + ex);
                }
            }
        };
        
        v.getjTable1().addMouseListener(mouseAdapter);
        
        FocusAdapter focusAdapter=new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                
                if(e.getSource().equals(v.getNomJTF())){
                    nom = v.getNomJTF().getText().trim();
                }
                
                if(e.getSource().equals(v.getNomBotanicJTF())){
                    nomBotanic = v.getNomBotanicJTF().getText().trim();
                }
            }
        };
    }
    
    
    
    public TableColumn carregaTaula(ArrayList resultSet, JTable taula, Class<?> classe) {

        //variables locals
        Vector columnNames = new Vector();
        Vector data = new Vector();
        //Per poder actualitzar la BD des de la taula usaríem el model comentat
        //ModelCanvisBD model;
        DefaultTableModel model;
        
        //Anotem el nº de camps de la classe
        Field[] camps = classe.getDeclaredFields();
        //Ordenem els camps alfabèticament
        Arrays.sort(camps, new OrdenarCampClasseAlfabeticament());
        int ncamps = camps.length;
        //Recorrem els camps de la classe i posem els seus noms com a columnes de la taula
        //Com hem hagut de posar _numero_ davant el nom dels camps, mostrem el nom a partir de la 4ª lletra 
        for (Field f : camps) {
            columnNames.addElement(f.getName().substring(3));
        }
        //Afegixo al model de la taula una columna on guardaré l'objecte mostrat a cada fila (amago la columna al final per a que no aparegue a la vista)
        columnNames.addElement("objecte");
        //Si hi ha algun element a l'arraylist omplim la taula
        if (resultSet.size() != 0) {

            //Guardem els descriptors de mètode que ens interessen (els getters), més una columna per guardar l'objecte sencer
            Vector<Method> methods = new Vector(ncamps +1);
            try {

                PropertyDescriptor[] descriptors = Introspector.getBeanInfo(classe).getPropertyDescriptors();
                Arrays.sort(descriptors, new OrdenarMetodeClasseAlfabeticament());
                for (PropertyDescriptor pD : descriptors) {
                    Method m = pD.getReadMethod();
                    if (m != null & !m.getName().equals("getClass")) {
                        methods.addElement(m);
                    }
                }

            } catch (IntrospectionException ex) {
                
            }
            for (Object m : resultSet) {
                Vector row = new Vector(ncamps + 1);

                for (Method mD : methods) {
                    try {
                        row.addElement(mD.invoke(m));
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        
                        
                    }
                    
                    
                }

                //Aquí guardo l'objecte sencer a la darrera columna
                row.addElement(m);
                //Finalment afegixo la fila a les dades
                data.addElement(row);
            }
            
            
      
        }

        //Utilitzem el model que permet actualitzar la BD des de la taula
        //model = new ModelCanvisBD(data, columnNames, Model.getConnexio(), columnNames.size() - 1);
        model=new DefaultTableModel(data, columnNames);
        taula.setModel(model);

        //Borro la darrera columna per a que no aparegue a la vista, però abans la guardo en una variable que al final serà el que retorna el mètode
        TableColumnModel tcm = taula.getColumnModel();
        TableColumn columna=tcm.getColumn(tcm.getColumnCount() - 1);
        //tcm.removeColumn(columna);
        
        //Fixo l'amplada de les columnes que sí es mostren
        TableColumn column;
        for (int i = 0; i < taula.getColumnCount(); i++) {
            column = taula.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }
        
        return columna;

    }

    private void amagaObjecte(){
        v.getjTable1().getColumnModel().getColumn(5).setMinWidth(0);
        v.getjTable1().getColumnModel().getColumn(5).setMaxWidth(0);
        v.getjTable1().getColumnModel().getColumn(5).setPreferredWidth(0);
    
    }
    
    private void actualitzaTaula(){
    
        carregaTaula((ArrayList) m.selectAll(),v.getjTable1(),Bonsai.class);
        amagaObjecte();
    
    }
    
    public static class OrdenarMetodeClasseAlfabeticament implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {

            Method mo1 = ((PropertyDescriptor) o1).getReadMethod();
            Method mo2 = ((PropertyDescriptor) o2).getReadMethod();

            if (mo1 != null && mo2 != null) {
                return (int) mo1.getName().compareToIgnoreCase(mo2.getName());
            }

            if (mo1 == null) {
                return -1;

            } else {
                return 1;
            }
        }
    }

    public static class OrdenarCampClasseAlfabeticament implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            return (int) (((Field) o1).getName().compareToIgnoreCase(((Field) o2).getName()));
        }
    }
    
}