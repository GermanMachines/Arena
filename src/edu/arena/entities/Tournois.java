/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

import java.sql.Date;

/**
 *
 * @author tarek
 */
public class Tournois {
            Integer IdTournois;
	 String Titre; 
	 String Date_debut;
         String Date_fin;
         String DescriptionTournois;
         String Type;
         Integer NbrParticipants;
         Integer IdJeux;
        String NomJeux;
        String Winner;
        String Status;
        
    public Tournois() {
    }
         
         
         
         

    public Tournois(Integer IdTournois, String Titre, String Date_debut, String Date_fin, String DescriptionTournois, String Type, Integer NbrParticipants, Integer IdJeux) {
        this.IdTournois = IdTournois;
        this.Titre = Titre;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.DescriptionTournois = DescriptionTournois;
        this.Type = Type;
        this.NbrParticipants = NbrParticipants;
        this.IdJeux = IdJeux;
    }

    public Tournois(String Titre, String Date_debut, String Date_fin, String DescriptionTournois, String Type, Integer NbrParticipants, Integer IdJeux) {
        this.Titre = Titre;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.DescriptionTournois = DescriptionTournois;
        this.Type = Type;
        this.NbrParticipants = NbrParticipants;
        this.IdJeux = IdJeux;
    }

    public Tournois(Integer IdTournois, String Titre) {
        this.IdTournois = IdTournois;

         this.Titre = Titre;
        
    }

    public Tournois(String Titre, String Date_debut, String Date_fin, String DescriptionTournois, String Type, Integer NbrParticipants, String NomJeux) {
        this.Titre = Titre;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.DescriptionTournois = DescriptionTournois;
        this.Type = Type;
        this.NbrParticipants = NbrParticipants;
        this.NomJeux = NomJeux;


    }

    public Tournois(Integer IdTournois,String Titre, String Date_debut, String Date_fin, String DescriptionTournois, String Type, Integer NbrParticipants, Integer IdJeux , String Winner , String Status) {
     this.IdTournois = IdTournois;
        this.Titre = Titre;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.DescriptionTournois = DescriptionTournois;
        this.Type = Type;
        this.NbrParticipants = NbrParticipants;
        this.IdJeux = IdJeux;
        this.Winner=Winner;
        this.Status=Status;

    }

    public Integer getIdTournois() {
        return IdTournois;
    }

    public String getType() {
        return Type;
    }

    public Integer getNbrParticipants() {
        return NbrParticipants;
    }

    public Integer getIdJeux() {
        return IdJeux;
    }

    public void setIdTournois(Integer IdTournois) {
        this.IdTournois = IdTournois;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setNbrParticipants(Integer NbrParticipants) {
        this.NbrParticipants = NbrParticipants;
    }

    public void setIdJeux(Integer IdJeux) {
        this.IdJeux = IdJeux;
    }

    public String getNomJeux() {
        return NomJeux;
    }

    public void setNomJeux(String NomJeux) {
        this.NomJeux = NomJeux;
    }
         
    
    

    public String getDescriptionTournois() {
        return DescriptionTournois;
    }

    public void setDescriptionTournois(String DescriptionTournois) {
        this.DescriptionTournois = DescriptionTournois;
    }





    public String getTitre() {
        return Titre;
    }

    public String getDate_debut() {
        return Date_debut;
    }

    public String getDate_fin() {
        return Date_fin;
    }



    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public void setDate_debut(String Date_debut) {
        this.Date_debut = Date_debut;
    }

    public void setDate_fin(String Date_fin) {
        this.Date_fin = Date_fin;
    }

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String Winner) {
        this.Winner = Winner;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Tournois{" + "IdTournois=" + IdTournois + ", Titre=" + Titre + ", Date_debut=" + Date_debut + ", Date_fin=" + Date_fin + ", DescriptionTournois=" + DescriptionTournois + ", Type=" + Type + ", NbrParticipants=" + NbrParticipants + ", IdJeux=" + IdJeux + ", NomJeux=" + NomJeux + ", Winner=" + Winner + ", Status=" + Status + '}';
    }

    
   

   

   
    
    
    
    

}
