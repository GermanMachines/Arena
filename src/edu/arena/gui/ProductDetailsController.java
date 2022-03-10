/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Product;
import edu.arena.services.ProductCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Foura
 */
public class ProductDetailsController implements Initializable {

    private MyListener myListener;
    private Product p;
    @FXML
    private Label nameLbl;
    @FXML
    private Label descLbl;
    @FXML
    private ImageView imageLbl;
    @FXML
    private Label priceLbl;
    @FXML
    private Label closeIcon;
    @FXML
    private Rating rating;

    public static List<Product> myProducts = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) closeIcon.getScene().getWindow();
        stage.close();
    }

    public void setData(Product p, MyListener myListener) {
        this.p = p;
        this.myListener = myListener;
        rating.setRating(p.getRate());
        nameLbl.setText(p.getName());
        descLbl.setText(p.getDesc());
        priceLbl.setText(p.getPrice() + "DT");
        System.out.println(p.getRate());
//        Image image = new Image(getClass().getResourceAsStream(p.getImage() + ""));
//        img.setImage(image);
    }

    @FXML
    private void addToCart(ActionEvent event) {
        myProducts.add(p);
        System.out.println("product " + p);
        System.out.println("all: " + myProducts);
    }

    @FXML
    private void rate(ActionEvent event) throws SQLException {

        ProductCRUD pcrud = new ProductCRUD();
        if (pcrud.saveRate((int) rating.getRating(), p.getId())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Rating saved");
            alert.showAndWait();
//            ProductsFrontController.productsList.clear();
//
//            ProductsFrontController.productsList.addAll(pcrud.showAllProducts());
//            
//            ProductsFrontController.prod.addAll(ProductsFrontController.getData());

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Rating not saved");
            alert.showAndWait();

        }

    }

}
