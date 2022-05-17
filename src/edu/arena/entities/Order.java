/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

import java.sql.Date;

/**
 *
 * @author Foura
 */
public class Order {

    private int id;
    private int idProduct;
    private int idUser;
    private int productQty;
    private int totalPrice;
    private Date createdAt;
    private String productName;
    private String productDesc;
    private String userName;
    private String userEmail;
    private String userRole;
    private int num;

    public Order() {
    }

    public Order(int id, int idProduct, int idUser, int productQty, int totalPrice, Date createdAt, String productName, String productDesc, String userName, String userEmail, String userRole, int num) {
        this.id = id;
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.productQty = productQty;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.productName = productName;
        this.productDesc = productDesc;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.num = num;
    }

    public Order(int idProduct, int idUser, int productQty, int totalPrice, Date createdAt, String productName, String productDesc, String userName, String userEmail, String userRole) {

        this.idProduct = idProduct;
        this.idUser = idUser;
        this.productQty = productQty;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.productName = productName;
        this.productDesc = productDesc;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        int min = 10000;
        int max = 99999;
        int random_id = (int) Math.floor(Math.random() * (max - min + 1) + min);
        this.num = random_id;
    }

    public Order(int id, int idProduct, int idUser, int productQty, Date createdAt, String productName, String productDesc, String userName, String userEmail, String userRole, int num) {
        this.id = id;
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.productQty = productQty;
        this.createdAt = createdAt;
        this.productName = productName;
        this.productDesc = productDesc;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.num = num;
    }

    public Order(int idProduct, int idUser, int productQty, Date createdAt, String productName, String productDesc, String userName, String userEmail, String userRole, int num) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.productQty = productQty;
        this.createdAt = createdAt;
        this.productName = productName;
        this.productDesc = productDesc;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.num = num;
    }

    public Order(int id, int idProduct, int idUser, int productQty, int num) {
        this.id = id;
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.productQty = productQty;
        long millis = System.currentTimeMillis();
        this.createdAt = new Date(millis);
        this.num = num;
    }

    public Order(int id, int idProduct, int idUser, int productQty, Date createdAt, int num) {
        this.id = id;
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.productQty = productQty;
        this.createdAt = createdAt;
        this.num = num;
    }

    public Order(int idProduct, int idUser, int productQty) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.productQty = productQty;
        long millis = System.currentTimeMillis();
        this.createdAt = new Date(millis);
        int min = 10000;
        int max = 99999;
        int random_id = (int) Math.floor(Math.random() * (max - min + 1) + min);
        this.num = random_id;
    }

    public Order(int idProduct, int idUser, int productQty, Date createdAt, int num) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.productQty = productQty;
        this.createdAt = createdAt;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getProductQty() {
        return productQty;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserRole() {
        return userRole;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getNum() {
        return num;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", idProduct=" + idProduct + ", idUser=" + idUser + ", productQty=" + productQty + ", totalPrice=" + totalPrice + ", createdAt=" + createdAt + ", productName=" + productName + ", productDesc=" + productDesc + ", userName=" + userName + ", userEmail=" + userEmail + ", userRole=" + userRole + ", num=" + num + '}';
    }

}
