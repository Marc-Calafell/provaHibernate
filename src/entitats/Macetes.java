/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author mark
 */
@Entity
@Table(name = "macetes")
public class Macetes {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
  
   @Column(name = "idMaceta")
   private long _1_idMaceta;
    
   @Column(name = "forma") 
   private String _2_forma;
   
   @Column(name = "color")
   private String _3_color;
   
   //@OneToOne(optional=true)
    @OneToOne
   private Bonsai _4_bonsai;

    public Macetes() {
    }

    public long get1_idMaceta() {
        return _1_idMaceta;
    }

    public void set1_idMaceta(long _1_idMaceta) {
        this._1_idMaceta = _1_idMaceta;
    }

    public String get2_forma() {
        return _2_forma;
    }

    public void set2_forma(String _2_forma) {
        this._2_forma = _2_forma;
    }

    public String get3_color() {
        return _3_color;
    }

    public void set3_color(String _3_color) {
        this._3_color = _3_color;
    }

    public Bonsai get4_bonsai() {
        return _4_bonsai;
    }

    public void set4_bonsai(Bonsai _4_bonsai) {
        this._4_bonsai = _4_bonsai;
    }

    @Override
    public String toString() {
        return String.valueOf(_1_idMaceta);
    }

    
    
    
    
   
   
}
