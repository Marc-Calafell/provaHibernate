/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;



import entitats.Bonsai;
import entitats.Macetes;
import entitats.Tractaments;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author alumne
 */
public class Model {

    private static final Session sesio=HibernateUtil.getSessionFactory().openSession();
    private ClasseDAO<Bonsai> DAOBonsai= new ClasseDAO<>(Bonsai.class, sesio);
    
    private ClasseDAO<Macetes> DAOMacetes= new ClasseDAO<>(Macetes.class, sesio);
    private ClasseDAO<Tractaments> DAOTractaments= new ClasseDAO<>(Tractaments.class, sesio);
    
    public void tancaSessio() {
        sesio.close();
    }

    public ClasseDAO<Macetes> getDAOMacetes() {
        return DAOMacetes;
    }

    public ClasseDAO<Bonsai> getDAOBonsai() {
        return DAOBonsai;
    }

    public ClasseDAO<Tractaments> getDAOTractaments() {
        return DAOTractaments;
    }

   
    
    
}
