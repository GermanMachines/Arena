/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Category;
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
public class CategoryCRUD {

    private Connection con;
    private Statement ste;

    public CategoryCRUD() {
        con = DataBase.getInstance().getConnection();
    }

    public void addCategory(Category c) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO categories (name,description)VALUES (?,?);");
        pre.setString(1, c.getName());
        pre.setString(2, c.getDesc());

        pre.executeUpdate();
    }

    public boolean updateCategory(String name, String desc, int id) throws SQLException {
        PreparedStatement pre = con.prepareStatement("update categories set name=?, description=? where id=" + id + ";");

        pre.setString(1, name);
        pre.setString(2, desc);

        if (pre.executeUpdate() != 0) {
            System.out.println("category updated");
            return true;
        }

        System.out.println("id not found!");
        return false;
    }

    public boolean deleteCategory(int id) throws SQLException {

        PreparedStatement pre = con.prepareStatement("Delete from categories where id=? ;");
        pre.setInt(1, id);
        if (pre.executeUpdate() != 0) {
            System.out.println("Category Deleted");
            return true;
        }
        System.out.println("id Category not found!");
        return false;

    }

    public List<Category> showAllCategories() throws SQLException {

        List<Category> listOfCategories = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select id,name,description from categories;");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String desc = rs.getString("description");
            Category c = new Category(id, name, desc);
            listOfCategories.add(c);
        }
        return listOfCategories;
    }

    public Category showCategory(int id) throws SQLException {

        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select name,description from categories where id=" + id + ";");
        String name = null;
        String desc = null;
        if (rs.next()) {
            name = rs.getString("name");
            desc = rs.getString("description");
        }
        Category c = new Category(id, name, desc);
        return c;
    }
}
