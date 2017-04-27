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
import entitats.Tractaments;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author mark
 */
public final class Controlador {
    
    public Model m;
    private Vista v;
    private int filasel=-1;
    
    Bonsai bonsai/*=  (Bonsai) v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 5)*/;
    int id;
    String nom;
    String nomBotanic;

    Macetes maceta/*= (Macetes) v.getTaulaMacetes().getValueAt(v.getTaulaMacetes().getSelectedRow(), 4)*/;
    int idMaceta;
    String forma;
    String color;
    
    Tractaments trac;
    int idTrac;
    String motiu;
    String tipus;

    public Controlador(Model m, Vista v) {

        this.m = m;
        this.v = v;
        
       
        carregaTaula((ArrayList) m.getDAOBonsai().selectAll(),v.getjTable1(),Bonsai.class);
        carregaTaula((ArrayList) m.getDAOMacetes().selectAll(),v.getTaulaMacetes(),Macetes.class);
        carregaTaula((ArrayList) m.getDAOTractaments().selectAll(),v.getTaulaTractaments(),Tractaments.class);
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
                    insert.set4_maceta((Macetes) v.getMacetesLliuresCB().getSelectedItem());
                    
                    m.getDAOBonsai().insert(insert);
                    
                    actualitzaTaula(); 
                            
                } else if(actionEvent.getSource().equals(v.getUpdateBTN())){
                    Bonsai update= (Bonsai) v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 5);

                    update.set2_nom(v.getNomJTF().getText());
                    update.set3_nomBotanic(v.getNomBotanicJTF().getText());
                    update.set4_maceta((Macetes) v.getMacetesLliuresCB().getSelectedItem());
                    bonsai.set4_maceta((Macetes) v.getMacetesLliuresCB().getSelectedItem());
                    
                    m.getDAOMacetes().update((Macetes) v.getMacetesLliuresCB().getSelectedItem());
                    
                    m.getDAOBonsai().update(update);
                    
                    actualitzaTaula();           

                } else if(actionEvent.getSource().equals(v.getDeleteBTN())){
                    Bonsai esborrar= (Bonsai) v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 5);
                    
                    esborrar.set4_maceta(null);
                    m.getDAOBonsai().delete(esborrar);
                     
                     actualitzaTaula();
                
                } else if(actionEvent.getSource().equals(v.getCreaMacetaBTN())){
                    Macetes insert = new Macetes();
                    insert.set2_forma(v.getFormaJTF().getText());
                    insert.set3_color(v.getColorJTF().getText());
                    insert.set4_bonsai(bonsai);
                    m.getDAOMacetes().insert(insert);

                    actualitzaTaula();
                    
                    
                } else if(actionEvent.getSource().equals(v.getModificaMacetaBTN())){
                    Macetes update= (Macetes) v.getTaulaMacetes().getValueAt(v.getTaulaMacetes().getSelectedRow(), 4);
                    update.set2_forma(v.getFormaJTF().getText());
                    update.set3_color(v.getColorJTF().getText());
                    update.set4_bonsai((Bonsai) v.getBonsaisLliuresCB().getSelectedItem());
                    
                    bonsai.set4_maceta(update);
                                        
                    m.getDAOBonsai().update((Bonsai) v.getBonsaisLliuresCB().getSelectedItem());
                    m.getDAOMacetes().update(update);
                                       
                    actualitzaTaula();
                    
                    
                } else if(actionEvent.getSource().equals(v.getEsborrarMacetaBTN())){
                    Macetes esborrar= (Macetes) v.getTaulaMacetes().getValueAt(v.getTaulaMacetes().getSelectedRow(), 4);
                    
                    
                    esborrar.set4_bonsai(null);
                    bonsai.set4_maceta(null);
                    m.getDAOBonsai().update(bonsai);
                    
                    m.getDAOMacetes().delete(esborrar);
                     
                    actualitzaTaula();
                     
                    
                } else if(actionEvent.getSource().equals(v.getCreaTractamentBTN())){
                    Tractaments insert = new Tractaments();
                    insert.set2_tipo(v.getTipusTracJTF().getText());
                    insert.set3_descripcio(v.getMotiuTracJTA().getText());
                    insert.set4_bonsai(bonsai);
                    m.getDAOTractaments().insert(insert);

                    actualitzaTaula();
                    
                    
                } else if(actionEvent.getSource().equals(v.getEditaTractamentBTN())){
                    Tractaments update= (Tractaments) v.getTaulaTractaments().getValueAt(v.getTaulaTractaments().getSelectedRow(), 4);
                    update.set2_tipo(v.getFormaJTF().getText());
                    update.set3_descripcio(v.getColorJTF().getText());
                    update.set4_bonsai(bonsai);
                    
                    //bonsai.set5_tractaments(Tractaments);
                                        
//                    m.getDAOBonsai().update(bonsai);
                    m.getDAOTractaments().update(update);
                                       
                    actualitzaTaula();
                    
                    
                } else if(actionEvent.getSource().equals(v.getEsborraTractamentBTN())){
                    Tractaments esborrar= (Tractaments) v.getTaulaTractaments().getValueAt(v.getTaulaTractaments().getSelectedRow(), 4);
                    
                    esborrar.set4_bonsai(null);
                    //bonsai.set5_tractaments(null);
                   // m.getDAOBonsai().update(bonsai);
                    
                   // m.getDAOTractaments().update(esborrar);
                    
                    m.getDAOTractaments().delete(esborrar);
                     
                     actualitzaTaula();     
                     
                     
                     
                
                }  
                
                
            }      
        };
        
        v.getAddBTN().addActionListener(actionListener);
        v.getUpdateBTN().addActionListener(actionListener);
        v.getDeleteBTN().addActionListener(actionListener);
        
        v.getCreaMacetaBTN().addActionListener(actionListener);
        v.getModificaMacetaBTN().addActionListener(actionListener);
        v.getEsborrarMacetaBTN().addActionListener(actionListener);
        
         v.getCreaTractamentBTN().addActionListener(actionListener);
        v.getEditaTractamentBTN().addActionListener(actionListener);
        v.getEsborraTractamentBTN().addActionListener(actionListener);
                
        
       

       
        
        
        
        MouseAdapter mouseAdapter=new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
                try {
                   int filasel = v.getjTable1().getSelectedRow();
                    if (filasel != -1) {    
                        bonsai =  (Bonsai) v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 5);
                        maceta = (Macetes) v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 3); 
                       
                        id = Integer.parseInt(v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 0).toString());
                       
                        nom = (String) v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 1);
                        v.getNomJTF().setText(nom);

                        nomBotanic = (String) v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 2);
                        v.getNomBotanicJTF().setText(nomBotanic);
                        
                        if (v.getjTable1().getValueAt(v.getjTable1().getSelectedRow(), 3)!= null){
  
                            try{
                            
                            maceta = (Macetes) v.getMacetesLliuresCB().getSelectedItem();

                            int idma = recorreTaula(v.getTaulaMacetes(), maceta.get1_idMaceta());
                            v.getTaulaMacetes().getSelectionModel().setSelectionInterval(idma, idma);
                            } catch (NullPointerException ec){
                            
                            }
                        } 

                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Ha petat (BONSAI): " + ex);
                    
                }
                
                
                try {
                   int filasel = v.getTaulaMacetes().getSelectedRow();
                    if (filasel != -1) {
                        
                        maceta =  (Macetes) v.getTaulaMacetes().getValueAt(v.getTaulaMacetes().getSelectedRow(), 4);
                        v.getBonsaisLliuresCB().setSelectedItem(maceta);
                        
                        idMaceta = Integer.parseInt(v.getTaulaMacetes().getValueAt(v.getTaulaMacetes().getSelectedRow(), 0).toString());
                                               
                        forma = (String) v.getTaulaMacetes().getValueAt(v.getTaulaMacetes().getSelectedRow(), 1);
                        v.getFormaJTF().setText(forma);
                        
                        color = (String) v.getTaulaMacetes().getValueAt(v.getTaulaMacetes().getSelectedRow(), 2);
                        v.getColorJTF().setText(color);
                        
                        if (v.getTaulaMacetes().getValueAt(v.getTaulaMacetes().getSelectedRow(), 3)!= null){
                            bonsai = (Bonsai) v.getTaulaMacetes().getValueAt(v.getTaulaMacetes().getSelectedRow(), 3);
                        }
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Ha petat (MACETES): " + ex);
                    
                }
                
                try {
                   int filasel = v.getTaulaTractaments().getSelectedRow();
                    if (filasel != -1) {
                        
                        trac =  (Tractaments) v.getTaulaTractaments().getValueAt(v.getTaulaTractaments().getSelectedRow(), 4);
                        v.getBonsaisLliuresCB().setSelectedItem(maceta);

                        tipus = (String) v.getTaulaTractaments().getValueAt(v.getTaulaTractaments().getSelectedRow(), 1);
                        v.getTipusTracJTF().setText(tipus);
                        
                        motiu = (String) v.getTaulaTractaments().getValueAt(v.getTaulaTractaments().getSelectedRow(), 2);
                        v.getMotiuTracJTA().setText(motiu);
                        
                        if (v.getTaulaTractaments().getValueAt(v.getTaulaTractaments().getSelectedRow(), 3)!= null){
                            bonsai = (Bonsai) v.getTaulaTractaments().getValueAt(v.getTaulaTractaments().getSelectedRow(), 3);
                        }
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Ha petat (TRACTAMENTS): " + ex);
                    
                }
                
               // try {
                    
                   Bonsai fila = (Bonsai) v.getTotsElsBonsaiCB().getSelectedItem();
                   
                        filtraPerBonsai(fila);
                        
                    
                    
                
                
              //  } catch (Exception ex) {
               //     System.out.println("Ha petat (TRACTAMENTS): " + ex);
                    
               // }

            
                
                
            }
        };
        
        v.getjTable1().addMouseListener(mouseAdapter);
        v.getTaulaMacetes().addMouseListener(mouseAdapter);
        v.getTaulaTractaments().addMouseListener(mouseAdapter);
        
        
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
                
                if(e.getSource().equals(v.getColorJTF())){
                    color = v.getColorJTF().getText().trim();
                }
                
                if(e.getSource().equals(v.getFormaJTF())){
                    forma = v.getFormaJTF().getText().trim();
                }
                
                if(e.getSource().equals(v.getBonsaisLliuresCB())){
                    bonsai = (Bonsai) v.getBonsaisLliuresCB().getSelectedItem();
                }
                
                if(e.getSource().equals(v.getMacetesLliuresCB())){
                    maceta = (Macetes) v.getMacetesLliuresCB().getSelectedItem();
                }
                
                if(e.getSource().equals(v.getTotsElsBonsaiCB())){
                    bonsai = (Bonsai) v.getTotsElsBonsaiCB().getSelectedItem();
                }
                
                if(e.getSource().equals(v.getTipusTracJTF())){
                    tipus = v.getTipusTracJTF().getText().trim();
                }
                
                if(e.getSource().equals(v.getMotiuTracJTA())){
                    motiu = v.getMotiuTracJTA().getText().trim();
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
           // column.setMaxWidth(250);
        }
        
        ompleComboBoxMacetes(v.getBonsaisLliuresCB());
        ompleComboBoxBonsai(v.getMacetesLliuresCB());
        ompleComboBox(v.getTotsElsBonsaiCB());
        return columna;

    }
    

    @SuppressWarnings("empty-statement")
    private int recorreTaula(JTable taula, long id){
         int i;
        for( i=0;taula.getRowCount()>i && (long) taula.getValueAt(i, 0)!=id;i++);
        if( (long) taula.getValueAt(i, 0)== id)return i;
        else return -1;
        
    }
    
    private void amagaObjecte(){
        v.getjTable1().getColumnModel().getColumn(5).setMinWidth(0);
        v.getjTable1().getColumnModel().getColumn(5).setMaxWidth(0);
        v.getjTable1().getColumnModel().getColumn(5).setPreferredWidth(0);
        
        v.getTaulaMacetes().getColumnModel().getColumn(4).setMinWidth(0);
        v.getTaulaMacetes().getColumnModel().getColumn(4).setMaxWidth(0);
        v.getTaulaMacetes().getColumnModel().getColumn(4).setPreferredWidth(0);
    
        v.getTaulaTractaments().getColumnModel().getColumn(4).setMinWidth(0);
        v.getTaulaTractaments().getColumnModel().getColumn(4).setMaxWidth(0);
        v.getTaulaTractaments().getColumnModel().getColumn(4).setPreferredWidth(0);
        
    }
    
    private void actualitzaTaula(){
        
        carregaTaula((ArrayList) m.getDAOBonsai().selectAll(),v.getjTable1(),Bonsai.class);
        carregaTaula((ArrayList) m.getDAOMacetes().selectAll(),v.getTaulaMacetes(),Macetes.class);
        carregaTaula((ArrayList) m.getDAOTractaments().selectAll(),v.getTaulaTractaments(),Tractaments.class);
        amagaObjecte();
        netejaCamps();
    
    }
    

public JComboBox ompleComboBoxMacetes(JComboBox comboBox) {
    comboBox.removeAllItems(); 
    List llista = m.getDAOBonsai().selectAll();
        comboBox.addItem(null);
    for(int i=0; i<llista.size();i++){
        Bonsai b =(Bonsai) llista.get(i);
        if(b.get4_maceta()== null){
            comboBox.addItem(b);
           
        }
    }
    
    return comboBox;
}
    
public JComboBox ompleComboBoxBonsai(JComboBox comboBox) {
    comboBox.removeAllItems(); 
    List llista = m.getDAOMacetes().selectAll();
        comboBox.addItem(null);
    for(int i=0; i<llista.size();i++){
        Macetes m =(Macetes) llista.get(i);
        if(m.get4_bonsai()== null){
            comboBox.addItem(m);
            
        }
    }
    
    return comboBox;
}

    private void netejaCamps(){
    
        String net= "";
        
        v.getColorJTF().setText(net);
        v.getFormaJTF().setText(net);
        
        v.getBonsaisLliuresCB().setSelectedIndex(0);
        v.getMacetesLliuresCB().setSelectedIndex(0);
        
        v.getNomJTF().setText(net);
        v.getNomBotanicJTF().setText(net);
        
        v.getTipusTracJTF().setText(net);
        v.getMotiuTracJTA().setText(net);
    }

    private JComboBox ompleComboBox(JComboBox comboBox) {
       
        comboBox.removeAllItems(); 
        List llista = m.getDAOBonsai().selectAll();
        comboBox.addItem(null);
        
        for(int i=0; i<llista.size();i++){
            Bonsai m =(Bonsai) llista.get(i);
            comboBox.addItem(m);


        }
    
    return comboBox;

        
        
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
    
    private void filtraPerBonsai(Bonsai fila) {
             List<Tractaments> filtrar = fila.get5_tractaments();
             
             carregaTaula((ArrayList) filtrar,v.getTaulaTractaments(),Tractaments.class);
        
            }
    
}