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
public class Outils {
        public static int idUser;
            public static String EmailUser;
 
    public static int getIdUser() {
        return idUser;
    }

    public Outils() { 
    }
     public static void start(int currentUserID) {
        idUser = currentUserID;
    }

    public static String getEmailUser() {
        return EmailUser;
    }
    
      public static int getCurrentSession() {
        if (idUser != -1) {
            return idUser;
        }
        return -1;

    }
       public static void close() throws Exception {
        if (idUser != -1) {
            idUser = -1;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }

    public static void setIdUser(int idUser) {
        Outils.idUser = idUser;
    }

    public static void setEmailUser(String EmailUser) {
        Outils.EmailUser = EmailUser;
    }
            


}
