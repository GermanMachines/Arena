/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Order;
import edu.arena.entities.Product;
import edu.arena.services.OrderCRUD;
import edu.arena.services.ProductCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Foura
 */
public class ItemProductController implements Initializable {

    @FXML
    private Label nameLbl;
    @FXML
    private Label priceLbl;
    @FXML
    private ImageView img;

    private MyListener myListener;
    private Product p;
    public static Product product;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void click(MouseEvent event) {
        myListener.onClickListener(p);
    }

    public void setData(Product p, MyListener myListener) {
        this.p = p;
        this.myListener = myListener;
        nameLbl.setText(p.getName());
        priceLbl.setText(p.getPrice() + " DT");
        Image myImage = new Image(getClass().getResourceAsStream("/resources/" + p.getImage() + ""));
        img.setImage(myImage);
//        Image image = new Image(getClass().getResourceAsStream(p.getImage() + ""));
//        img.setImage(image);
    }

    @FXML
    private void showDetails(ActionEvent event) {
        try {
            URL fxURL = getClass().getResource("ProductDetails.fxml");
            FXMLLoader loader = new FXMLLoader(fxURL);
            Parent root = loader.load();
            ProductDetailsController pdc = loader.getController();
            pdc.setData(p, myListener);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addOrder(ActionEvent event) {
        try {
            URL fxURL = getClass().getResource("ProductQuantity.fxml");
            FXMLLoader loader = new FXMLLoader(fxURL);
            Parent root = loader.load();
            ProductQuantityController pqc = loader.getController();
            pqc.setData(p, myListener);
            product = p;
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
//        int idProduct = p.getId();
//        int idUser = 1;
//        int productQty = 1;
//        Order o = new Order(idProduct, idUser, productQty);
//
//        OrderCRUD ocrud = new OrderCRUD();
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        try {
//            ocrud.addOrder(o);
//            alert.setTitle("Success");
//            alert.setHeaderText("sent");
//            alert.setContentText("order sent successfully");
//        } catch (SQLException ex) {
//            alert.setAlertType(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("not sent");
//            alert.setContentText("order isn't sent");
//            System.out.println(ex.getMessage());
//        } finally {
//            alert.showAndWait();
//        }

    }
}
