/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

/**
 *
 * @author tarek
 */
public class JeuxTournois {
    
    
     private int idequipe;

    private int idTournois;

    public JeuxTournois(int idequipe, int idTournois) {
        this.idequipe = idequipe;
        this.idTournois = idTournois;
    }

    public JeuxTournois(int idE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public int getIdequipe() {
        return idequipe;
    }

    public int getIdTournois() {
        return idTournois;
    }

    public void setIdequipe(int idequipe) {
        this.idequipe = idequipe;
    }

    public void setIdTournois(int idTournois) {
        this.idTournois = idTournois;
    }

    @Override
    public String toString() {
        return "JeuxTournois{" + "idequipe=" + idequipe + ", idTournois=" + idTournois + '}';
    }
    
    
    
    
}
