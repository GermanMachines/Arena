/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author DeathKnight
 */
public class Avis {
    static int nbAvis=0;
    private int id;
    private int score;
    private String commentaire;
    private int idUtulisateur;
    private int idProduit;

    
    public Avis( int score, String commentaire, int idUtulisateur, int idProduit) {
        nbAvis++;
        this.id = nbAvis;
        this.score = score;
        this.commentaire = commentaire;
        this.idUtulisateur = idUtulisateur;
        this.idProduit = idProduit;
    }
       
    public Avis(int id, int score, String commentaire, int idUtulisateur, int idProduit) {
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

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", score=" + score + ", commentaire=" + commentaire + ", idUser=" + idUtulisateur + ", idProduit=" + idProduit + '}';
    }


  


}
