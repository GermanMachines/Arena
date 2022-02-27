/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

import java.sql.Timestamp;

/**
 *
 * @author tarek
 */
public class Match {
    int idMatch;
    int IdTournois;
    String DateMatch;
    String Titre;
    String Reference;

    public Match(int idMatch, int IdTournois, String DateMatch) {
        this.idMatch = idMatch;
        this.IdTournois = IdTournois;
        this.DateMatch = DateMatch;

    }

    public Match(int IdTournois, String DateMatch,String Reference) {
        this.IdTournois = IdTournois;
        this.DateMatch = DateMatch;
        this.Reference=Reference;
    }
    
        public Match(int idMatch, String Titre , String DateMatch , String Reference ) {
            
        this.idMatch = idMatch;
        this.Titre=Titre;
        this.DateMatch = DateMatch;
        this.Reference=Reference;

    }

    public Match() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


        

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }
    

    public int getIdMatch() {
        return idMatch;
    }

    public int getIdTournois() {
        return IdTournois;
    }

    public String getDateMatch() {
        return DateMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public void setIdTournois(int IdTournois) {
        this.IdTournois = IdTournois;
    }

    public void setDateMatch(String DateMatch) {
        this.DateMatch = DateMatch;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String Reference) {
        this.Reference = Reference;
    }

    @Override
    public String toString() {
        return "Match{" + "idMatch=" + idMatch + ", IdTournois=" + IdTournois + ", DateMatch=" + DateMatch + ", Titre=" + Titre + ", Reference=" + Reference + '}';
    }

    
    
    
  
    
    

}
