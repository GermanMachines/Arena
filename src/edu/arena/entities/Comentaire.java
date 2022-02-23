/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;


public class Comentaire {
    int id_com;
   String nom_com;
   String desc_com;

    public Comentaire(int id_com, String nom_com, String desc_com) {
        this.id_com = id_com;
        this.nom_com = nom_com;
        this.desc_com = desc_com;
    }

    public Comentaire(String nom_com, String desc_com) {
        this.nom_com = nom_com;
        this.desc_com = desc_com;
    }

    public int getId_com() {
        return id_com;
    }

    public String getNom_com() {
        return nom_com;
    }

    public String getDesc_com() {
        return desc_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public void setNom_com(String nom_com) {
        this.nom_com = nom_com;
    }

    public void setDesc_com(String desc_com) {
        this.desc_com = desc_com;
    }
  


  
    @Override
    public String toString() {
        return "post{" + "id_com=" + id_com + ", nom_com=" + nom_com + ", desc_com=" + desc_com +  '}';
    }

 
   
}
