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
    private String block;


    public User(int id, String nom, String surnom, String image, String email, String mdp, String telephone, int id_equipe, String role, String Block) {
        this.id= id ;
        this.nom = nom;
        this.surnom = surnom;
        this.image = image;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
        this.id_equipe = id_equipe;
        this.role = role;
        this.block = Block ;
    }

    public User(String nom, String surnom, String image, String email, String mdp, String telephone, int id_equipe, String role, String block) {
        this.nom = nom;
        this.surnom = surnom;
        this.image = image;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
        this.id_equipe = id_equipe;
        this.role = role;
        this.block = block;
    }

    public User(int id, String nom, String surnom, String email, String telephone) {
        this.id=id;
        this.nom = nom;
        this.surnom = surnom;
        this.email = email;
        this.telephone = telephone;

    }

    public User() {
    }

    public User(String nom, String surnom, String image, String email, String mdp, String telephone) {
        this.nom = nom;
        this.surnom = surnom;
        this.image = image;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
    }

    public User(int id, String nom, String surnom, String image, String email, String mdp, String telephone) {
        this.id = id;
        this.nom = nom;
        this.surnom = surnom;
        this.image = image;
        this.email = email;
        this.mdp = mdp;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getBlock() {
        return block;
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
    public User(String nom, String surnom, String image, String email, String mdp, String telephone, String role ,String block) {
        this.nom = nom;
        this.surnom = surnom;
        this.image = image;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
        this.role = role;
        this.block = block;
    }

   

}
