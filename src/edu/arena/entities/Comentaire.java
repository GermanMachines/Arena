/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;


public class Comentaire {
    int id_com;
  int id_user;
   String desc_com;
   String date_com;
   int id_post;
   String nomuser;

    public Comentaire(int id_com, int id_user, String desc_com, int id_post) {
         this.id_com = id_com;
          this.id_user = id_user;
            this.desc_com = desc_com;
                   this.id_post = id_post;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }
   
   

    public Comentaire(int id_com,int id_user ,String desc_com , String date_com , int id_post ) {
        this.id_com = id_com;
        this.id_user = id_user;
        this.desc_com = desc_com;
          this.date_com = date_com;  
          this.id_post = id_post;
          
    }

    public Comentaire(int id_user ,String desc_com , String date_com , int id_post ) {
      
        this.id_user = id_user;
         this.desc_com = desc_com;
         this.date_com = date_com;  
          this.id_post = id_post;
          
        
    }

    public Comentaire(int idC, String nomuser, String Commentaire, String Datecom) {
      this.id_com = idC;
      this.nomuser=nomuser;
      this.desc_com = Commentaire;
      this.date_com = Datecom;    
      
    }

    
    

   
    public int getId_com() {
        return id_com;
    }

    

    public String getDesc_com() {
        return desc_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDate_com() {
        return date_com;
    }

    public void setDate_com(String date_com) {
        this.date_com = date_com;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

  

    public void setDesc_com(String desc_com) {
        this.desc_com = desc_com;
    }
  


  
    @Override
    public String toString() {
        return "post{" + "id_com=" + id_com + ", id_user=" + id_user + ", desc_com=" + desc_com +  ", date_com=" + date_com +  ", id_post=" + id_post + '}';
       
    }

 
   
}
