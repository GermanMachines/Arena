/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Category;
import edu.arena.entities.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.arena.utils.DataBase;

/**
 *
 * @author Foura
 */
public class ProductCRUD {

    private Connection con;
    private Statement ste;

    public ProductCRUD() {
        con = DataBase.getInstance().getConnection();
    }

    public void addProduct(Product p) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO products (name,price,qty,description,image,idCat)VALUES (?,?,?,?,?,?);");
        pre.setString(1, p.getName());
        pre.setInt(2, p.getPrice());
        pre.setInt(3, p.getQty());
        pre.setString(4, p.getDesc());
        pre.setString(5, p.getImage());
        pre.setInt(6, p.getIdCategory());

        pre.executeUpdate();
    }

    public boolean updateProduct(String name, int price, int qty, String desc, String image, int idCat, int id) {
        try {
            PreparedStatement pre = con.prepareStatement("update products set name=?, price=?, qty=?, description=?, image=?, idCat=? where id=? ;");

            pre.setString(1, name);
            pre.setInt(2, price);
            pre.setInt(3, qty);
            pre.setString(4, desc);
            pre.setString(5, image);
            pre.setInt(6, idCat);
            pre.setInt(7, id);

            if (pre.executeUpdate() != 0) {
                System.out.println("product updated");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("id not found!");
        return false;
    }

    public boolean deleteProduct(int id) throws SQLException {

        PreparedStatement pre = con.prepareStatement("Delete from products where id=? ;");
        pre.setInt(1, id);
        if (pre.executeUpdate() != 0) {
            System.out.println("Product Deleted");
            return true;
        }
        System.out.println("id Product not found!");
        return false;

    }

    public List<Product> showAllProducts() throws SQLException {

        List<Product> listOfProducts = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("Select * from products");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            System.out.print(price);
            int qty = rs.getInt("qty");
            String desc = rs.getString("description");
            String image = rs.getString("image");
            int rate = rs.getInt("rate");
            CategoryCRUD ccrud = new CategoryCRUD();
            Category cat = ccrud.showCategory(rs.getInt("idCat"));
            int idCat = cat.getId();
            String catName = cat.getName();
            String catDesc = cat.getDesc();
            Product p = new Product(id, name, qty, desc, image, idCat, catName, catDesc, price, rate);
            listOfProducts.add(p);
        }
        return listOfProducts;
    }

    public Product showProduct(int id) throws SQLException {

        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from products where id=" + id + ";");
        String name = null;
        int price = 0;
        int qty = 0;
        String desc = null;
        String image = null;
        int idCat = 0;
        String catName = null;
        String catDesc = null;
        int rate = 0;
        if (rs.next()) {
            name = rs.getString("name");
            price = rs.getInt("price");
            qty = rs.getInt("qty");
            desc = rs.getString("description");
            image = rs.getString("image");
            rate = rs.getInt("rate");
            CategoryCRUD ccrud = new CategoryCRUD();
            Category cat = ccrud.showCategory(rs.getInt("idCat"));
            idCat = cat.getId();
            catName = cat.getName();
            catDesc = cat.getDesc();
        }
        Product p = new Product(id, name, qty, desc, image, idCat, catName, catDesc, price, rate);
        return p;
    }

    public boolean saveRate(int rate, int id) {
        try {
            PreparedStatement pre = con.prepareStatement("update products set rate=? where id=? ;");

            pre.setInt(1, rate);
            pre.setInt(2, id);

            if (pre.executeUpdate() != 0) {
                System.out.println("rate updated");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("id  not found!!!");
        return false;
    }
}
