/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface IUser <T> {
     public void ajouter(T p) throws SQLException;
     public void modifier(int id, T p) throws SQLException;
     public void supprimer( int id) throws SQLException;
     public List<T> afficher() throws SQLException;
    // public List<T> chercher(List<T> initialList, String input) ;
    
}
