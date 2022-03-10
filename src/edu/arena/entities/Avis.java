/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

/**
 *
 * @author DeathKnight
 */
public class Avis {
    public int id;
    private int score;
    private String commentaire;
    private int idUtulisateur;
    private int idJeux;
    private String nomUtulisateur;
    private String nomJeux;
  
    

    
    
        public Avis(){
            
        }
        
       public Avis(int score, int idUtulisateur, int idJeux ,String nomJeux) {
        this.score = score;
        this.idUtulisateur = idUtulisateur;
        this.idJeux = idJeux;
        this.nomJeux = nomJeux;
    }
    public Avis(int score, String commentaire, int idUtulisateur, int idJeux,String nomJeux) {
        this.score = score;
        this.commentaire = commentaire;
        this.idUtulisateur = idUtulisateur;
        this.idJeux = idJeux;
        this.nomJeux =  nomJeux;
    }
    
       public Avis(int score, String commentaire, int idUtulisateur, int idJeux) {
        this.score = score;
        this.commentaire = commentaire;
        this.idUtulisateur = idUtulisateur;
        this.idJeux = idJeux;
    }
    
    
    public Avis( int id,int score, String commentaire, int idUtulisateur, int idJeux , String nomJeux) {
        this.id = id;
        this.score = score;
        this.commentaire = commentaire;
        this.idUtulisateur = idUtulisateur;
        this.idJeux = idJeux;
        this.nomJeux = nomJeux;
    }
       
     public Avis( int id,int score, String commentaire, int idUtulisateur, int idJeux) {
        this.id = id;
        this.score = score;
        this.commentaire = commentaire;
        this.idUtulisateur = idUtulisateur;
        this.idJeux = idJeux;
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdUtulisateur() {
        return idUtulisateur;
    }

    public void setIdUser(int idUtulisateur) {
        this.idUtulisateur = idUtulisateur;
    }

    public int getIdJeux() {
        return idJeux;
    }

    public String getNomUtulisateur() {
        return nomUtulisateur;
    }

    public void setNomUtulisateur(String nomUtulisateur) {
        this.nomUtulisateur = nomUtulisateur;
    }

    public String getNomJeux() {
        return  nomJeux;
    }

    public void setNomJeux(String nomJeux) {
        this.nomJeux = nomJeux;
    }

    public void setIdUtulisateur(int idUtulisateur) {
        this.idUtulisateur = idUtulisateur;
    }

    public void setIdJeux(int idJeux) {
        this.idJeux = idJeux;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", score=" + score + ", commentaire=" + commentaire + ", idUtulisateur=" + idUtulisateur + ", idJeux=" + idJeux + ", nomUtulisateur=" + nomUtulisateur + ", nomJeux=" + nomJeux + '}';
    }
    

   


  


}
