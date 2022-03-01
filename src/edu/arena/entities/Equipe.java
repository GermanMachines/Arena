/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

/**
 *
 * @author LENOVO
 */
public class Equipe {
    
    private int idEquipe;
    private String nom,logo;
    private int score;
    private String region;
  

    public Equipe(int idEquipe, String nom, String logo, int score, String region) {
      
        this.nom = nom;
        this.logo = logo;
        this.score = score;
        this.region = region;
        
    }

    public Equipe(String nom, String logo, int score, String region) {
        this.nom = nom;
        this.logo = logo;
        this.score = score;
        this.region = region;
       
    }

  

 

    public int getIdEquipe() {
        return idEquipe;
    }

    public String getNom() {
        return nom;
    }

    public String getLogo() {
        return logo;
    }

    public int getScore() {
        return score;
    }

    public String getRegion() {
        return region;
    }
   

  /*  public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }*/

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Equipe{" + "idEquipe=" + idEquipe + ", nom=" + nom + ", logo=" + logo + ", score=" + score + ", region=" + region + '}';
    }
    

    
     
    
    
}
