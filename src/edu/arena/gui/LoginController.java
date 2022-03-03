/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.Services.UserService;
import edu.arena.entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private Button btnLogin;
    private Stage stage;
    private Scene scene;
    Parent root;
    double x,y;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        UserService us = new UserService();

        
        
        //saving user data in textfile
        Path path = Paths.get("C:/Users/SBS/Arena/src/edu/arena/utils/data.txt");
        User user = us.getUser(tfUsername.getText());
     
        
        
        if(us.login(tfUsername.getText()) && tfUsername.getText().toLowerCase().equals("admin")){
            
            //writing data in the text file
            List<String> lines = Arrays.asList(Integer.toString(user.getId()),user.getNom());
            Files.write(path, lines,StandardCharsets.UTF_8);
            
            root = FXMLLoader.load(getClass().getResource("BackOffice.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
          //  contentArea.getChildren().removeAll();
          //  contentArea.getChildren().setAll(fxml);
        }
        else if(us.login(tfUsername.getText())) {
            
            //writing data in the textfile
            List<String> lines = Arrays.asList(Integer.toString(user.getId()),user.getNom());
            Files.write(path, lines,StandardCharsets.UTF_8);
            
            
            root = FXMLLoader.load(getClass().getResource("FrontOffice.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        }else{
            
            //remove old file & create new one
            String chemin = "C:/Users/SBS/Arena/src/edu/arena/utils/data.txt";
            File f = new File(chemin);
            f.delete();
            f.createNewFile();
            System.out.println("error");
        }
    }
    
}
