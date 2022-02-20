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
public class Produit {
    static int nbProduit = 0;
    private int id;
    private String nom;
    private int avisId;

     public Produit(int id, String nom) {
        this.id = id;
        this.nom = nom;   
    }
     public Produit(String nom) {
        nbProduit++;
        this.id = nbProduit;
        this.nom = nom;
       
        
    }
     
    public Produit(int id, String nom, int avisId) {
        this.id = id;
        this.nom = nom;
        this.avisId = avisId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAvisId() {
        return avisId;
    }

    public void setAvisId(int avisId) {
        this.avisId = avisId;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", reclamation=" + avisId + '}';
    }
    
    
}
