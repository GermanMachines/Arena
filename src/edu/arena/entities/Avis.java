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
    private int idProduit;
    private String nomUtulisateur;
    private String nomProduit;
    

    
    
        public Avis(){
            
        }
    public Avis(int score, String commentaire, int idUtulisateur, int idProduit) {
        this.score = score;
        this.commentaire = commentaire;
        this.idUtulisateur = idUtulisateur;
        this.idProduit = idProduit;
    }
    
    public Avis( int id,int score, String commentaire, int idUtulisateur, int idProduit) {
        this.id = id;
        this.score = score;
        this.commentaire = commentaire;
        this.idUtulisateur = idUtulisateur;
        this.idProduit = idProduit;
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

    public int getIdProduit() {
        return idProduit;
    }

    public String getNomUtulisateur() {
        return nomUtulisateur;
    }

    public void setNomUtulisateur(String nomUtulisateur) {
        this.nomUtulisateur = nomUtulisateur;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setIdUtulisateur(int idUtulisateur) {
        this.idUtulisateur = idUtulisateur;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", score=" + score + ", commentaire=" + commentaire + ", idUtulisateur=" + idUtulisateur + ", idProduit=" + idProduit + ", nomUtulisateur=" + nomUtulisateur + ", nomProduit=" + nomProduit + '}';
    }
    

   


  


}
