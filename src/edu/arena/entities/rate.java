/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

/**
 *
 * @author Lenovo
 */
public class rate {
    int id_rate;
    int nombre;
    int id_post;
  static int test;

    public static int getTest() {
        return test;
    }

    public static void setTest(int test) {
        rate.test = test;
    }
public rate(int id_rate,  int nombre,   int id_post) {
        this.id_post = id_post;
        this.nombre = nombre;
        this.id_rate = id_rate;
       
    
    }

    public rate( int nombre,   int id_post) {
        this.id_post = id_post;
      this.nombre = nombre;
      
       
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getId_rate() {
        return id_rate;
    }

    public void setId_rate(int id_rate) {
        this.id_rate = id_rate;
    }


   
   
    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    @Override
    public String toString() {
        return "rate{" + "id_rate=" + id_rate + ", nombre=" + nombre + ", id_post=" + id_post + '}';
    }

  
    
}