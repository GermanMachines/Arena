/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
//import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author DeathKnight
 */
public class FXMain extends Application {
    double x,y;
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
     /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        }); */
      
      //  Parent root = FXMLLoader.load(getClass().getResource("../edu/arena/gui/BackOffice.fxml"));

        
     //   try{
   //     root = loader.load();
       //  Scene scene = new Scene(root, 1000, 500);
     /*   }catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(ex.getMessage());
        }*/
       // StackPane root = new StackPane();
        //root.getChildren().add(btn);
        
       
        
      
      
        //primaryStage.setScene(scene);
        //primaryStage.show();
        
        Parent root = FXMLLoader.load(getClass().getResource("../edu/arena/gui/Login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
           primaryStage.setX(event.getScreenX() - x);
           primaryStage.setY(event.getScreenY() - y);
        });
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    


}
