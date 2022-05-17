/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Jeux;
import edu.arena.entities.Order;
import edu.arena.entities.Product;

/**
 *
 * @author tarek
 */
public interface MyListener {
    public void onClickListener(int idjeux);
    public void onClickListener(Product p);
    public void onClickOrderListener(Order o);
}
