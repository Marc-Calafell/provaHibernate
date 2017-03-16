/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

/**
 *
 * @author mark
 */
public class Tractaments {
    private long _1_idTractament;
    private String _2_tipo;
    private String _3_descripcio;

    

    public long getIdTractament() {   return _1_idTractament;  }
    public void setIdTractament(long _1_idMaceta) { this._1_idTractament = _1_idMaceta; }

    public String getTipo() { return _2_tipo; }
    public void setTipo(String _2_tipo) { this._2_tipo = _2_tipo; }

    public String getDescripcio() { return _3_descripcio; }
    public void setDescripcio(String _3_descripcio) { this._3_descripcio = _3_descripcio; }

    public Tractaments() {
    }
    
    public Tractaments(String _2_tipo, String _3_descripcio) {
        this._2_tipo = _2_tipo;
        this._3_descripcio = _3_descripcio;
        
    }
    
}
