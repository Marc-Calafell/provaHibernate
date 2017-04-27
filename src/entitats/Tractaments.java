/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author mark
 */
@Entity
public class Tractaments implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _1_idTractament;
    
    @Column(name = "tipo")
    private String _2_tipo;
    
    @Column(name = "descripcio")
    private String _3_descripcio;
    
    @ManyToOne()
    private Bonsai _4_bonsai;

    public long get1_idTractament() {
        return _1_idTractament;
    }

    public void set1_idTractament(long _1_idTractament) {
        this._1_idTractament = _1_idTractament;
    }

    public String get2_tipo() {
        return _2_tipo;
    }

    public void set2_tipo(String _2_tipo) {
        this._2_tipo = _2_tipo;
    }

    public String get3_descripcio() {
        return _3_descripcio;
    }

    public void set3_descripcio(String _3_descripcio) {
        this._3_descripcio = _3_descripcio;
    }

    public Bonsai get4_bonsai() {
        return _4_bonsai;
    }

    public void set4_bonsai(Bonsai _4_bonsai) {
        this._4_bonsai = _4_bonsai;
    }
    
    

    public Tractaments() {
    }

    public Tractaments(long _1_idTractament, String _2_tipo, String _3_descripcio) {
        this._1_idTractament = _1_idTractament;
        this._2_tipo = _2_tipo;
        this._3_descripcio = _3_descripcio;
    }

    
    @Override
    public String toString() {
        return String.valueOf(_1_idTractament);
    }
    

    

    
    
}
