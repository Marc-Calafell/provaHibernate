/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

/**
 *
 * @author alumne
 */
public class ClasseDAO<T> {

    private Session sesio;
    private Transaction tx;
    
    private Class p;

    public ClasseDAO(Class<T> p, Session sesio) {
        this.p = p;
        this.sesio=sesio;
    }

    public ClasseDAO() {
        
    }
    
    public long insert(T objecte) throws HibernateException {
        long id = 0;

        try {
            iniciaOperacio();
            id = Long.parseLong(String.valueOf(sesio.save(objecte)));
            tx.commit();
        } catch (HibernateException he) {
            tractaExcepcio(he);
            throw he;
        } 

        return id;
    }

    public void update(T objecte) throws HibernateException {
        try {
            iniciaOperacio();
            sesio.update(objecte);
            tx.commit();
        } catch (HibernateException he) {
            tractaExcepcio(he);
            throw he;
        } 
    }

    public void delete(T objecte) throws HibernateException {
        try {
            iniciaOperacio();
            sesio.delete(objecte);
            tx.commit();
        } catch (HibernateException he) {
            tractaExcepcio(he);
            throw he;
        } 
    }

    public T selectOne(int idObjecte) throws HibernateException {
        T objecte = null;
        try {
            iniciaOperacio();
            objecte = (T) sesio.get(p, idObjecte);
            tx.commit();
        } catch (HibernateException he) {
            tractaExcepcio(he);
            throw he;
        } 

        return objecte;
    }

    public List<T> selectAll() throws HibernateException {
        ArrayList<T> llista = new ArrayList<>();
        try {
            iniciaOperacio();
            llista = (ArrayList) sesio.createQuery("from "+p.getSimpleName()).list();
            tx.commit();
        } catch (HibernateException he) {
            tractaExcepcio(he);
            throw he;
        } 

        return llista;
    }

    private void iniciaOperacio() {
        tx = sesio.beginTransaction();
    }

    private void tractaExcepcio(HibernateException he){//throws HibernateException {
        tx.rollback();
        //throw new HibernateException("Error a la capa d'accés a dades", he);
    }
}
