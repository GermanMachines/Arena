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
public class MatchEquipe {
    int idMatch ;
    int IdEquipe ;
    int Score;

    public MatchEquipe(int idMatch, int IdEquipe, int Score) {
        this.idMatch = idMatch;
        this.IdEquipe = IdEquipe;
        this.Score = Score;
    }
    
     public MatchEquipe(int idMatch, int IdEquipe) {
        this.idMatch = idMatch;
        this.IdEquipe = IdEquipe;
  
    }

    public MatchEquipe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public int getIdMatch() {
        return idMatch;
    }

    public int getIdEquipe() {
        return IdEquipe;
    }

    public int getScore() {
        return Score;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public void setIdEquipe(int IdEquipe) {
        this.IdEquipe = IdEquipe;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    @Override
    public String toString() {
        return "MatchEquipe{" + "idMatch=" + idMatch + ", IdEquipe=" + IdEquipe + ", Score=" + Score + '}';
    }
    
    
    
}
