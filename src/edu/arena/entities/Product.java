/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

/**
 *
 * @author Foura
 */
public class Product {

    private int id;
    private String name;
    private int qty;
    private String desc;
    private String image;
    private int idCategory;
    private String catName;
    private String catDesc;
    private int price;
    private int rate;

    public Product() {

    }

    public Product(int id, String name, int qty, String desc, String image, int idCategory, String catName, String catDesc, int price, int rate) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.desc = desc;
        this.image = image;
        this.idCategory = idCategory;
        this.catName = catName;
        this.catDesc = catDesc;
        this.price = price;
        this.rate = rate;
    }

    public Product(String name, int qty, String desc, String image, int idCategory, String catName, String catDesc, int price, int rate) {
        this.name = name;
        this.qty = qty;
        this.desc = desc;
        this.image = image;
        this.idCategory = idCategory;
        this.catName = catName;
        this.catDesc = catDesc;
        this.price = price;
        this.rate = rate;
    }

    public Product(int id, String name, int price, int qty, String desc, String image, int idCategory, String catName, String catDesc) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.desc = desc;
        this.image = image;
        this.idCategory = idCategory;
        this.catName = catName;
        this.catDesc = catDesc;
    }

    public Product(String name, int price, int qty, String desc, String image, int idCategory, String catName, String catDesc) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.desc = desc;
        this.image = image;
        this.idCategory = idCategory;
        this.catName = catName;
        this.catDesc = catDesc;
    }

    public Product(int id, String name, int price, int qty, String desc, String image, int idCategory) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.desc = desc;
        this.image = image;
        this.price = price;
        this.idCategory = idCategory;
    }

    public Product(String name, int price, int qty, String desc, String image, int idCategory) {
        this.name = name;
        this.qty = qty;
        this.desc = desc;
        this.image = image;
        this.price = price;
        this.idCategory = idCategory;
    }

    public Product(String name, int price, int qty, String desc, String image) {
        this.name = name;
        this.qty = qty;
        this.desc = desc;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public String getDesc() {
        return desc;
    }

    public String getImage() {
        return image;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getCatName() {
        return catName;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", qty=" + qty + ", desc=" + desc + ", image=" + image + ", idCategory=" + idCategory + ", catName=" + catName + ", catDesc=" + catDesc + ", price=" + price + ", rate=" + rate + '}';
    }

}
