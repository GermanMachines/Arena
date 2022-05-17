
import edu.arena.entities.Order;
import edu.arena.entities.Product;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Foura
 */
public interface MyListener {

    public void onClickListener(Product p);
    public void onClickOrderListener(Order o);

}
