/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import entitats.Bonsai;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import Vista.Vista;

/**
 *
 * @author mark
 * @param <T>
 */
public class Model<T> {
    private Session sesion; 
    private Transaction tx;
            
    private final Class p;
    public Model(Class<T> p) {
        this.p = p;
    }

    public long insert(Bonsai bonsai) throws HibernateException { 
        long id = 0;

        try {

            iniciaOperacio(); 
            id = (Long) sesion.save(bonsai); 
            tx.commit(); 

        } catch (HibernateException he) { 
            tractaExcepcio(he); 
            throw he; 

        } finally { 
        sesion.close(); 
        }

        return id; 
    }
    public void update(Bonsai bonsai) throws HibernateException { 
        try { 
            iniciaOperacio(); 
            sesion.update(bonsai); 
            tx.commit(); 

        } catch (HibernateException he) { 
            tractaExcepcio(he); 
            throw he; 

        } finally { 
            sesion.close(); 
        } 
    }
    public void delete(Bonsai bonsai) throws HibernateException { 
        try { 
            iniciaOperacio(); 
            sesion.delete(bonsai); 
            tx.commit(); 
        } catch (HibernateException he) { 
            tractaExcepcio(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        } 
    }
    public T selectOne(long idObjecte) throws HibernateException {
            T objecte = null;
            try {
                iniciaOperacio();
                objecte = (T) sesion.get(p, idObjecte);
            } finally {
                sesion.close();
            }

            return objecte;
        }
    public ArrayList<T> selectAll() throws HibernateException {
            ArrayList<T> llista = new ArrayList<>();
            try {
                iniciaOperacio();
                llista = (ArrayList) sesion.createQuery("from "+p.getSimpleName()).list();
            } finally {
                sesion.close();
            }

            return llista;
        }
    private void iniciaOperacio() throws HibernateException { 
        sesion = HibernateUtil.getSessionFactory().openSession(); 
        tx = sesion.beginTransaction(); 
    }
    private void tractaExcepcio(HibernateException he) throws HibernateException { 
        tx.rollback(); 
        throw new HibernateException("Error a la capa d'accés a dades", he); 
    } 

    
   
    
    
}




    
    
    

