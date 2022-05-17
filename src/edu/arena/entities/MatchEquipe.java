/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

import javafx.scene.control.Label;

/**
 *
 * @author tarek
 */
public class MatchEquipe {
    int idMatch ;
    int IdEquipe ;
    int Score;
    String nomequipe;
    String region;
    
      static int test;

    public static int getTest() {
        return test;
    }

    public static void setTest(int test) {
        MatchEquipe.test = test;
    }

      
      
      
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
    }



    public MatchEquipe(int Idmatch, String nomequipe, String region, int score) {
       this.idMatch = Idmatch;
        this.nomequipe=nomequipe;
        this.region=region;
        this.Score=score;

    }

    public String getNomequipe() {
        return nomequipe;
    }

    public void setNomequipe(String nomequipe) {
        this.nomequipe = nomequipe;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
        return "MatchEquipe{" + "idMatch=" + idMatch + ", IdEquipe=" + IdEquipe + ", Score=" + Score + ", nomequipe=" + nomequipe + ", region=" + region + '}';
    }

  


    
    
    
}
