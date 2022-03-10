package edu.arena.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXButton;
import edu.arena.entities.Order;
import edu.arena.entities.Product;
import edu.arena.services.OrderCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Foura
 */
public class ProductQuantityController implements Initializable {

    @FXML
    private TextField qtyFld;
    private MyListener myListener;
    private Product p;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXButton closeOnConfirmBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Product p, MyListener myListener) {
        this.p = p;
        this.myListener = myListener;
    }

    @FXML
    private void confirmOrder(ActionEvent event) {
        int idProduct = ItemProductController.product.getId();
        int idUser = 1;
        int productQty = Integer.parseInt(qtyFld.getText());
        Order o = new Order(idProduct, idUser, productQty);

        OrderCRUD ocrud = new OrderCRUD();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            ocrud.addOrder(o);
            Stage stage = (Stage) closeOnConfirmBtn.getScene().getWindow();
            stage.close();
            alert.setTitle("Success");
            alert.setHeaderText("sent");
            alert.setContentText("order sent successfully");
        } catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("not sent");
            alert.setContentText("order isn't sent");
            System.out.println(ex.getMessage());
        } finally {
            alert.showAndWait();
        }
    }

    @FXML
    private void onClickClose(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
