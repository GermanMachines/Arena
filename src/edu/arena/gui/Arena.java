/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

/**
 *
 * @author Ayadi
 */
public class Arena extends Application {
        //define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage primaryStage) {
        
        
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("DashbordAdminHome.fxml"));
              Parent root = FXMLLoader.load(getClass().getResource("Loginpage.fxml"));
             

             //grab your root here
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
            
            
            
            
        });

        //move around here
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
       // Scene scene = new Scene(root, 1087, 649);
        Scene scene = new Scene(root, 350, 350);
      // Scene scene = new Scene(root);
        //set transparent
        scene.setFill(Color.TRANSPARENT);
          primaryStage.setTitle("Arena+");
                    primaryStage.setScene(scene); 
                    primaryStage.show();

                    
                    
        } catch (IOException ex) {
            Logger.getLogger(Arena.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}