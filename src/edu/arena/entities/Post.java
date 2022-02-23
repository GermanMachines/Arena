/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

import java.sql.Date;


public class Post {
    int id_post;
   String titre;
   String auteur;
    String img_post;
    Date date_post;
    int id_com;
    

    public Post(int id_post, String titre, String auteur, String img_post, Date date_pos,int id_com) {
        this.id_post = id_post;
        this.titre = titre;
        this.auteur = auteur;
        this.img_post = img_post;
        this.date_post = date_post;
        this.id_com=id_com;
    }

    public Post(String titre, String auteur, String img_post, Date date_post, int id_com) {
        this.titre = titre;
        this.auteur = auteur;
        this.img_post = img_post;
        this.date_post = date_post;
          this.id_com=id_com;
    }

    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public Date getDate_post() {
        return date_post;
    }

    public void setDate_post(Date date_post) {
        this.date_post = date_post;
    }

    public int getId_post() {
        return id_post;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getImg_post() {
        return img_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setImg_post(String img_post) {
        this.img_post = img_post;
    }
    
    

  
    @Override
    public String toString() {
        return "post{" + "id_post=" + id_post + ", titre=" + titre + ", auteur=" + auteur + ", img_post=" + img_post +  ", date_post=" + date_post + '}';
    }

 
   
}
