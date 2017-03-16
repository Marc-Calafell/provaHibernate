/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

@Transient
ArrayList _4_macetes= new ArrayList();
@Transient
ArrayList _5_tractaments= new ArrayList();    

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

    public ArrayList get4_macetes() {
        return _4_macetes;
    }

    public void set4_macetes(ArrayList _4_macetes) {
        this._4_macetes = _4_macetes;
    }

    public ArrayList get5_tractaments() {
        return _5_tractaments;
    }

    public void set5_tractaments(ArrayList _5_tractaments) {
        this._5_tractaments = _5_tractaments;
    }
    



}