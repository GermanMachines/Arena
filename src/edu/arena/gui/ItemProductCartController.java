/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import static edu.arena.gui.ItemProductController.product;
import edu.arena.gui.MyListener;
import com.jfoenix.controls.JFXButton;
import edu.arena.entities.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class ItemProductCartController implements Initializable {

    @FXML
    private Label nameLbl;
    @FXML
    private ImageView img;
    @FXML
    private Label priceLbl;
    @FXML
    private Label descLbl;

    private MyListener myListener;
    private Product p;
    @FXML
    private JFXButton rmvBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void removeProduct(ActionEvent event) {
        ProductDetailsController.myProducts.remove(p);
        Stage stage = (Stage) rmvBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addThisProduct(ActionEvent event) {
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
        descLbl.setText(p.getDesc());
        Image myImage = new Image(getClass().getResourceAsStream("/resources/" + p.getImage() + ""));
        img.setImage(myImage);
    }

    @FXML
    private void orderThisProduct(ActionEvent event) {
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
    }

}
