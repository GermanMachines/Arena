/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Order;
import edu.arena.services.OrderCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Foura
 */
public class MyOrdersController implements Initializable {

    @FXML
    private Label closeIcon;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    private MyListener myListener;
    
    public static List<Order> myOrdersList = new ArrayList<>();
    public static List<Order> ord = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshElements();
    }    

    @FXML
    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) closeIcon.getScene().getWindow();
        stage.close();
    }
    
    public void refreshElements() {
        try {
            OrderCRUD ocrud = new OrderCRUD();

            myOrdersList.clear();
            myOrdersList.addAll(ocrud.showMyOrders(30));

            ord.clear();
            ord.addAll(getData());
            
            System.out.println(myOrdersList);
            System.out.println(ord);

            int column = 0;
            int row = 1;
            for (int i = 0; i < myOrdersList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemOrder.fxml"));
                AnchorPane anchorPane;
                anchorPane = fxmlLoader.load();

                ItemOrderController itemController = fxmlLoader.getController();
                itemController.setData(myOrdersList.get(i), myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static List<Order> getData() {
        List<Order> orderItem = new ArrayList<>();
        Order or;

        for (Order o : myOrdersList) {
            or = new Order();
            or.setNum(o.getNum());
            or.setTotalPrice(o.getTotalPrice());
            or.setProductName(o.getProductName());
            or.setProductQty(o.getProductQty());

            orderItem.add(or);
        }

        return orderItem;

    }
}
