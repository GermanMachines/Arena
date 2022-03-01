/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

/**
 *
 * @author LENOVO
 */
public class User {

    private int id;
    private String nom;
    private String surnom, image, email, mdp, telephone;
    private int id_equipe;
    private String role;

    public User(int id, String nom, String surnom, String image, String email, String mdp, String telephone, int id_equipe, String role) {
        this.id= id ;
        this.nom = nom;
        this.surnom = surnom;
        this.image = image;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
        this.id_equipe = id_equipe;
        this.role = role;
    }

    public User(String nom, String surnom, String image, String email, String mdp, String telephone, int id_equipe, String role) {
        this.nom = nom;
        this.surnom = surnom;
        this.image = image;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
        this.id_equipe = id_equipe;
        this.role = role;
    }

    public User(int id, String nom, String surnom, String email, String telephone) {
        this.id=id;
        this.nom = nom;
        this.surnom = surnom;
        this.email = email;
        this.telephone = telephone;

    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getSurnom() {
        return surnom;
    }

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getId_equipe() {
        return id_equipe;
    }

    public String getRole() {
        return role;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setId_equipe(int id_equipe) {
        this.id_equipe = id_equipe;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", surnom=" + surnom + ", image=" + image + ", email=" + email + ", mdp=" + mdp + ", telephone=" + telephone + ", id_equipe=" + id_equipe + ", role=" + role + '}';
    }
    public String concat(){
        return id + ".@." + nom + ".@." + surnom + ".@." + email + ".@." + telephone + ".@."  ;
    }
    public User(String nom, String surnom, String image, String email, String mdp, String telephone, String role) {
        this.nom = nom;
        this.surnom = surnom;
        this.image = image;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
        this.role = role;
    }

}
