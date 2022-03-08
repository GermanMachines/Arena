/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;




public class Post {
    int id_post;
   String titre;
   String auteur;
    String img_post;
    String date_post;
    int rate;
    static int test;

    public static int getTest() {
        return test;
    }

    public static void setTest(int test) {
        Post.test = test;
    }
    

    public Post(int id_post, String titre, String auteur, String img_post, String date_post,int rate) {
        this.id_post = id_post;
        this.titre = titre;
        this.auteur = auteur;
        this.img_post = img_post;
        this.date_post = date_post;
        this.rate = rate;    
    }

    public Post(String titre, String auteur, String img_post, String date_post) {
        this.titre = titre;
        this.auteur = auteur;
        this.img_post = img_post;
        this.date_post = date_post;
        
      
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
     
    
   
    public Post() {
      
    }

  

 
    public String getDate_post() {
        return date_post;
    }

    public void setDate_post(String date_post) {
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
        return "post{" + "id_post=" + id_post + ", titre=" + titre + ", auteur=" + auteur + ", img_post=" + img_post +  ", date_post=" + date_post + ", rate=" + rate +'}';
    }

   
 
   
}
