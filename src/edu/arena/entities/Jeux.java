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
public class Jeux {
    int IdJeux;
   String NomJeux;
   String ImageJeux;
   static String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Jeux(Integer IdJeux, String NomJeux, String ImageJeux) {
        this.IdJeux=IdJeux;
        this.NomJeux = NomJeux;
        this.ImageJeux = ImageJeux;
    }

  public Jeux(String NomJeux, String ImageJeux) {
        this.NomJeux = NomJeux;
        this.ImageJeux = ImageJeux;
    }

    public Jeux() {
    }

    public Jeux(int idjeux) {
this.IdJeux=IdJeux;   
    }

    public void setIdJeux(int IdJeux) {
        this.IdJeux = IdJeux;
    }

    public int getIdJeux() {
        return IdJeux;
    }

    public String getNomJeux() {
        return NomJeux;
    }

    public String getImageJeux() {
        return ImageJeux;
    }

    public void setNomJeux(String NomJeux) {
        this.NomJeux = NomJeux;
    }

    public void setImageJeux(String ImageJeux) {
        this.ImageJeux = ImageJeux;
    }

    @Override
    public String toString() {
        return "Jeux{" + "IdJeux=" + IdJeux + ", NomJeux=" + NomJeux + ", ImageJeux=" + ImageJeux + '}';
    }

 
   
}
