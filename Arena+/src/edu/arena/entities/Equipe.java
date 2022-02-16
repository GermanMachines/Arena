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
public class Equipe {
    int IdEquipe;
    String nom;
    String logo;
    int score;
    String region;

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
    

    public int getIdEquipe() {
        return IdEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.IdEquipe = idEquipe;
    }

    public Equipe(int idEquipe) {
        this.IdEquipe = idEquipe;
    }

    @Override
    public String toString() {
        return "Equipe{" + "IdEquipe=" + IdEquipe + ", nom=" + nom + ", logo=" + logo + ", score=" + score + ", region=" + region + '}';
    }

    public Equipe(int IdEquipe, String nom, String logo, int score, String region) {
        this.IdEquipe = IdEquipe;
        this.nom = nom;
        this.logo = logo;
        this.score = score;
        this.region = region;
    }

 
    
 
}
