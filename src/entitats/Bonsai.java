/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "bonsai")
public class Bonsai {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_bonsai")
private long _1_id_Bonsai;

@Column(name = "nom")
private String _2_nom;

@Column(name = "nom_botanic")
private String _3_nomBotanic;

@OneToOne(optional=true)
private Macetes _4_maceta;

@OneToMany(cascade=javax.persistence.CascadeType.ALL)
@JoinColumn(name="tractaments")
private List<Tractaments> _5_tractaments= new ArrayList();    

    public Macetes get4_maceta() {
        return _4_maceta;
    }

    public void set4_maceta(Macetes _4_maceta) {
        this._4_maceta = _4_maceta;
    }

    public List<Tractaments> get5_tractaments() {
        return _5_tractaments;
    }

    public void set5_tractaments(List<Tractaments> _5_tractaments) {
        this._5_tractaments = _5_tractaments;
    }

    
    
    public long get1_id_Bonsai() {
        return _1_id_Bonsai;
    }

    public void set1_id_Bonsai(long _1_id_Bonsai) {
        this._1_id_Bonsai = _1_id_Bonsai;
    }

    

    public String get2_nom() {
        return _2_nom;
    }

    public void set2_nom(String _2_nom) {
        this._2_nom = _2_nom;
    }

    public String get3_nomBotanic() {
        return _3_nomBotanic;
    }

    public void set3_nomBotanic(String _3_nomBotanic) {
        this._3_nomBotanic = _3_nomBotanic;
    }

    @Override
    public String toString() {
        return String.valueOf(_1_id_Bonsai);
    }


}