/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;


import com.jfoenix.controls.JFXButton;
import edu.arena.entities.Order;
import edu.arena.services.OrderCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Foura
 */
public class ItemOrderController implements Initializable {

    @FXML
    private Label nameLbl;
    @FXML
    private Label priceLbl;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private Label qtyLbl;
    private MyListener myListener;
    private Order o;
    @FXML
    private Label productLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cancelOrder(ActionEvent event) {
        int id = o.getId();
        OrderCRUD ocrud = new OrderCRUD();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            ocrud.deleteOrder(id);
            MyOrdersController.myOrdersList.clear();
            MyOrdersController.myOrdersList.addAll(ocrud.showMyOrders(1));
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
            alert.setTitle("Success");
            alert.setHeaderText("deleted");
            alert.setContentText("product deleted successfully");
        } catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("not deleted");
            alert.setContentText("product isn't deleted");
            System.out.println(ex.getMessage());
        } finally {
            alert.showAndWait();
        }
    }

    @FXML
    private void click(MouseEvent event) {
        myListener.onClickOrderListener(o);
    }

    public void setData(Order o, MyListener myListener) {
        this.o = o;
        this.myListener = myListener;
        nameLbl.setText("Order " + o.getNum());
        priceLbl.setText(o.getTotalPrice() + " DT");
        qtyLbl.setText(o.getProductQty() + "");
        productLbl.setText(o.getProductName());
//        Image image = new Image(getClass().getResourceAsStream(p.getImage() + ""));
//        img.setImage(image);
    }

}
