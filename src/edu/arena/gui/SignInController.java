/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.arena.entities.Equipe;
import edu.arena.entities.User;
import edu.arena.Services.EquipeService;
import edu.arena.services.UserService;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class SignInController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfsurnom;
    @FXML
    private TextField tfemail;
    @FXML

    private TextField tftel;
    @FXML
    private Button btnSignIn;
    @FXML
    private JFXTextField tfImagee;
    @FXML
    private JFXPasswordField tfmdp1;
    @FXML
    private JFXPasswordField tfmdp;
    @FXML
    private Label lblTelll;
    @FXML
    private JFXCheckBox check1;
    @FXML
    private JFXCheckBox check2;
    @FXML
    private JFXTextField tfmdpp;
    @FXML
    private JFXTextField tfmdpp1;
    @FXML
    private JFXButton lbllogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static boolean textNumeric(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isNumeric = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[0-9]+")) {
            isNumeric = false;
            validationString = validationText;

        }
        inputLabel.setText("only numero");
        return isNumeric;

    }

    @FXML
    private void AjoutUser(ActionEvent event) throws IOException, Exception {
//        UserService sp = new UserService();
//        if (sp.mailIsValid(tfemail.getText()) && (this.textNumeric(tftel, lblTelll, tftel.getText())) && (tftel.getText().length() == 8)) {
//            String nom = tfnom.getText();
//            String surnom = tfsurnom.getText();
//            String image = tfImagee.getText();
//            String email = tfemail.getText();
//            String mdp = tfmdp.getText();
//            String mdp1 = tfmdp1.getText();
//            String tel = tftel.getText();
//
//            String mdpf = UserService.encrypt(mdp);
//            if (mdp.equals(mdp1)) {
//
//                User e = new User(nom, surnom, image, email, mdpf, tel);
//
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                try {
//                    sp.ajouter2(e);
//                    alert.setTitle("Succes");
//                    alert.setHeaderText("Ajoutée");
//                    alert.setContentText("User ajoutée");
//                    /* alert.setOnCloseRequest((evn) -> {
//                try {
//                    URL fxURL = getClass().getResource("showEquipe.fxml");
//                    FXMLLoader loader = new FXMLLoader(fxURL);
//                    Parent root = loader.load();
//                    ShowEquipeController sec = loader.getController();
//                    sec.setNom("Nom :" + tfNomeq.getText());
//                    sec.setLogo("Logo :" + tfLogoEq.getText());
//                    sec.setRegion("Region :" + tfRegionEq.getText());
//
//                    tfNomeq.getScene().setRoot(root);
//                    tfLogoEq.getScene().setRoot(root);
//                    tfRegionEq.getScene().setRoot(root);
//
//                } catch (IOException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            });*/
//                } catch (SQLException ex) {
//                    alert.setAlertType(Alert.AlertType.ERROR);
//                    alert.setTitle("Error");
//                    alert.setHeaderText("Erreur d'ajour");
//                    alert.setContentText(ex.getMessage());
//                } finally {
//                    alert.showAndWait();
//                     btnSignIn.getScene().getWindow().hide();
//            Parent root =FXMLLoader.load(getClass().getResource("../gui/LoginAdmin.fxml"));
//                }
//
//            } else {
//                JOptionPane.showMessageDialog(null, "les deux mots de passe ne sont pas identique!");
//            }
//
//        } else {
//            JOptionPane.showMessageDialog(null, "Verifier vos données");
//        }
//    }
//
//    @FXML
//    private void upload(ActionEvent event) {
//        FileChooser fc = new FileChooser();
//        String imageFile = "";
//        File f = fc.showOpenDialog(null);
//
//        if (f != null) {
//            imageFile = f.getAbsolutePath();
//            String newfile = imageFile.replace("C:\\Users\\LENOVO\\Documents\\github\\Arena\\src\\resources", "");
//            tfImagee.setText(newfile);
//        }
 UserService sp = new UserService();
      //  if (sp.mailIsValid(tfemail.getText()))  {
            if((this.textNumeric(tftel, lblTelll, tftel.getText())) && (tftel.getText().length() == 8)){
            String nom = tfnom.getText();
            String surnom = tfsurnom.getText();
            String image = tfImagee.getText();
            String email = tfemail.getText();
            String mdp = tfmdp.getText();
            String mdp1 = tfmdp1.getText();
            String tel = tftel.getText();

        //    String mdpf = UserService.encrypt(mdp);
            if (mdp.equals(mdp1)) {

                User e = new User(nom, surnom, image, email, mdp, tel);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                try {
                    sp.ajouter2(e);
                    alert.setTitle("Succes");
                    alert.setHeaderText("Ajoutée");
                    alert.setContentText("User ajoutée");

                    /* alert.setOnCloseRequest((evn) -> {
                try {
                    URL fxURL = getClass().getResource("showEquipe.fxml");
                    FXMLLoader loader = new FXMLLoader(fxURL);
                    Parent root = loader.load();
                    ShowEquipeController sec = loader.getController();
                    sec.setNom("Nom :" + tfNomeq.getText());
                    sec.setLogo("Logo :" + tfLogoEq.getText());
                    sec.setRegion("Region :" + tfRegionEq.getText());

                    tfNomeq.getScene().setRoot(root);
                    tfLogoEq.getScene().setRoot(root);
                    tfRegionEq.getScene().setRoot(root);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });*/
                } catch (SQLException ex) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Erreur d'ajour");
                    alert.setContentText(ex.getMessage());
                } finally {
                    alert.showAndWait();
                    Quitter();
                    
                }

            } else {
               
                 Notifications notificationBuilder = Notifications.create()
      .title("Alert").text("les deux mots de passe ne sont pas identique!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.TOP_CENTER)
              .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        System.out.println("clicked on");
              }});
      notificationBuilder.darkStyle();
notificationBuilder.show();
            }

            } else {                        Notifications notificationBuilder = Notifications.create()
      .title("Alert").text("telephone invalide").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.TOP_CENTER)
              .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        System.out.println("clicked on");
              }});
      notificationBuilder.darkStyle();
notificationBuilder.show();}
//            } else {
//                            Notifications notificationBuilder = Notifications.create()
//      .title("Alert").text("mail invalide").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
//              .position(Pos.TOP_CENTER)
//              .onAction(new EventHandler<ActionEvent>() {
//        @Override
//        public void handle(ActionEvent event) {
//        System.out.println("clicked on");
//              }});
//      notificationBuilder.darkStyle();
//notificationBuilder.show();
//        }
        
    }

    @FXML
    private void upload(ActionEvent event) {
        FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getAbsolutePath();
            String newfile = imageFile.replace("C:\\Users\\LENOVO\\Documents\\github\\Arena\\src\\resources\\", "");
            tfImagee.setText(newfile);
        }
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
    
    @FXML
    private void Quitter() throws Exception{
        try{
            btnSignIn.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("login.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }


}
