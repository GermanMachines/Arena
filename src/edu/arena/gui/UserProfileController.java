/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.arena.entities.Outils;
import edu.arena.entities.User;
import edu.arena.services.UploadService;
import edu.arena.services.UserService;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class UserProfileController implements Initializable {
    UserService us = new UserService();
     UploadService uploadservice = new UploadService();

    @FXML
    private JFXTextField tfnom;
    @FXML
    private JFXTextField tfsurnom;
    @FXML
    private JFXTextField tfemail;
    @FXML
    private JFXTextField tftel;
    @FXML
    private JFXTextField tfequipe;
    @FXML
    private JFXTextField tfrole;
    @FXML
    private ImageView profileimage;
    @FXML
    private JFXTextField tfimage;
    @FXML
    private JFXButton modif;
 int iduser = Outils.getCurrentSession();
    @FXML
    private Button retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        System.out.println(iduser);
        System.out.println("idddd"+iduser);
        User u = us.findbyidcode(iduser);
               String imagename = us.getImage(iduser);
               System.out.println(imagename);
       tfnom.setText(u.getNom());
        tfsurnom.setText(u.getSurnom());
      Image image = new Image(getClass().getResourceAsStream("/resources/"+imagename+""));
              tfimage.setText(u.getImage());
               tfnom.setText(u.getNom());
                       tfemail.setText(u.getEmail());
        tftel.setText(u.getTelephone());
                tfequipe.setText(String.valueOf(u.getId_equipe()));
                profileimage.setImage(image);



    
    
    }    

    @FXML
    private void upload(ActionEvent event) {
          FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getAbsolutePath();
            String newfile = imageFile.replace("C:\\Users\\LENOVO\\Desktop\\Arena1\\src\\resources\\","");
            tfimage.setText(newfile);
        }
    }

    @FXML
    private void modif(ActionEvent event) throws SQLException {
       
     User uu = us.findbyidcode(iduser);
//           if (!tfimage.getText().equals("")) {
//
//            String oldname = us.getImage(iduser);
//            uploadservice.delete(oldname);
//
//            String FilenameInserver = uploadservice.upload(tfimage.getText());
//            us.updateimage(iduser, FilenameInserver);
//        }
        String imagename = us.getImage(iduser);
 ////  Image image = new Image(getClass().getResourceAsStream("/resources/"+imagename+""));        // profileimage.setImage(image);
   //     profileimage.setImage(image);
        //get image name
        int idd= uu.getId();
       String nom = tfnom.getText();
       String surnom = tfsurnom.getText();    
       String imagee = tfimage.getText();
      String email = tfemail.getText();
         String telephone = tftel.getText();
          int ideqq = Integer.parseInt(tfequipe.getText());
  String mdp = uu.getMdp();
    String blockk = uu.getBlock();
      String rolee = uu.getRole();
//      //   String mdp1 = tfmdp1.getText();
//          String tel = tftel.getText();
     User uuu= new User(idd,nom, surnom ,imagee, email, mdp, telephone, ideqq, rolee, blockk );
System.out.println(uuu);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
          try{
                  us.modifier3(uuu);
                     alert.setAlertType(Alert.AlertType.INFORMATION);
                     alert.setTitle("User modified");
                     alert.setHeaderText("Results:");
                     alert.setContentText("User modified successfully!");
               } catch (SQLException ex){
                                //Alert Error Tournois :
                       alert.setAlertType(Alert.AlertType.WARNING);
                       alert.setTitle("ERROR");
                       alert.setHeaderText("Adding Error !! ");
                       alert.setContentText(ex.getMessage());
                       //Alert Error Tournois !
        } finally{
              alert.showAndWait();
        }
          }

    @FXML
    private void quit(ActionEvent event) {
          try{
            retour.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/Loginj.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }


    }
    
    

