/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import edu.arena.entities.User;
import edu.arena.services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class NewMdpController implements Initializable {

    private User u;
    ForgotController f = new ForgotController();
    @FXML
    private TextField tfmdp;
    @FXML
    private TextField tfmdp1;
    @FXML
    private JFXTextField tfmdpp1;
    @FXML
    private JFXTextField tfmdpp;
    @FXML
    private JFXCheckBox check1;
    @FXML
    private JFXCheckBox check2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setU(User u) {
        this.u = u;
    }

    @FXML
    private void btnvalid(ActionEvent event) throws SQLException {
        UserService es = new UserService();
        System.out.println(u);
        es.modifierMotDePasse(u, tfmdp.getText(), tfmdp1.getText());
        System.out.println(u);
        

    }

    @FXML
    private void see1(ActionEvent event) {
             if(check1.isSelected()){
            tfmdpp.setText(tfmdp.getText());
            tfmdpp.setVisible(true);
            tfmdp.setVisible(false);
          return;  
        }  tfmdp.setText(tfmdpp.getText());
            tfmdp.setVisible(true);
            tfmdpp.setVisible(false); 
    }

    @FXML
    private void see2(ActionEvent event) {
        if(check2.isSelected()){
            tfmdpp1.setText(tfmdp1.getText());
            tfmdpp1.setVisible(true);
            tfmdp1.setVisible(false);
          return;  
        }  tfmdp1.setText(tfmdpp1.getText());
            tfmdp1.setVisible(true);
            tfmdpp1.setVisible(false); 
    }

}
