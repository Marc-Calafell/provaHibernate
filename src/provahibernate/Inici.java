/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provahibernate;

import Controlador.Controlador;
import Model.Model;
import Vista.Vista;
import entitats.Bonsai;
import entitats.Macetes;
import java.util.ArrayList;
import java.util.List;





public class Inici {
    
    private static final  Model m = new Model(Bonsai.class);
    private static final  Vista v = new Vista();
    private static Controlador c;
    
    public static void main(String[] args){
     
        c = new Controlador(m,v);
        v.setVisible(true);
//        
//        long id=1;
//
        Bonsai bonsai1 = new Bonsai();
        bonsai1.set2_nom("Saisho no bonsai");
        bonsai1.set3_nomBotanic("Cydonia Oblonga");
        
        Bonsai bonsai2 = new Bonsai();
        bonsai2.set2_nom("Saisho no bonsai");
        bonsai2.set3_nomBotanic("Cydonia Oblonga");
        
        
        
        Macetes maceta1= new Macetes();
        maceta1.setColor("verd");
        maceta1.set2_forma("rectangular");
        
        
        
        ArrayList macetesA = new ArrayList();
        macetesA.add(maceta1);
        
        Bonsai bonsai3 = new Bonsai();
        bonsai3.set2_nom("Saisho no bonsai");
        bonsai3.set3_nomBotanic("Cydonia Oblonga");
        bonsai3.set4_macetes(macetesA);

//        m.insert(bonsai1);
//        m.insert(bonsai2);
        m.insert(bonsai3);
//        
//        m.selectOne(bonsai1.getId_Bonsai());
//        
//        List<Bonsai> Bonsais = m.selectAll();
//        
//        for (Bonsai b : Bonsais) {
//            System.out.println("|" + b.getNom()+"|" + b.getNomBotanic()+"|");
//        }    
//        
//        
//        bonsai2.setNom("Saisho no bonsai 2");
//        m.update(bonsai2);
//        
//        m.delete(bonsai3);
 //       System.exit(0);
        
    }
        
}
    

