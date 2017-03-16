/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mark
 */

public class Macetes {
//    @Id
//@GeneratedValue(strategy = GenerationType.IDENTITY)
//@Column(name = "idMaceta")
   private long _1_idMaceta;
    
//   @Column(name = "forma") 
   private String _2_forma;
   
//   @Column(name = "color")
   private String _3_color;  


    public Macetes(String _2_forma, String _3_color) {
        this._2_forma = _2_forma;
        this._3_color = _3_color;
        
    }

    public Macetes() {
    }
    
    public long getIdMaceta() {  return _1_idMaceta;  }
    private void setIdMaceta(long _1_idMaceta) { this._1_idMaceta = _1_idMaceta; }

    public String get2_forma() {
        return _2_forma;
    }

    public void set2_forma(String _2_forma) {
        this._2_forma = _2_forma;
    }

    

    public String getColor() { return _3_color; }
    public void setColor(String _3_color) { this._3_color = _3_color; }
   
   
}
