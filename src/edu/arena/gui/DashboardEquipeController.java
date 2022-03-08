/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXButton;
import edu.arena.entities.Equipe;
import edu.arena.services.EquipeService;
import edu.arena.utils.MyDB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DashboardEquipeController implements Initializable {

    @FXML
    private TextField tfNomEq;
    @FXML
    private TextField tfLogoEq;
    @FXML
    private TextField tfScoreEq;
    @FXML
    private TextField tfRegionEq;
    @FXML
    private TableView<Equipe> tvboxEq;
    @FXML
    private TableColumn<Equipe, Integer> colIDEq;
    @FXML
    private TableColumn<Equipe, String> ColNomEq;
    @FXML
    private TableColumn<Equipe, String> ColLogoEq;
    @FXML
    private TableColumn<Equipe, Integer> ColScoreEq;
    @FXML
    private TableColumn<Equipe, String> ColRegionEq;
    @FXML
    private Button btnAjoutEq;
    @FXML
    private Button btnModifEq;
    @FXML
    private Button btnSuppEq;
         public ObservableList<Equipe> EquipeData = FXCollections.observableArrayList();
 
    @FXML
    private JFXButton btnquit;

    
      public int getTxtScore() {
        return Integer.parseInt(tfScoreEq.getText());
    }
    
                EquipeService es = new EquipeService();

    
      @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException {
    if(event.getSource()== btnAjoutEq){
             addEquipe();
         }else if(event.getSource()==btnSuppEq){
             DeleteAction();
         }else if (event.getSource()==btnModifEq){
             EditAction();
         }
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
        
        try {
            EquipeData.addAll(es.afficher());
        } catch (SQLException ex) {
                        System.out.println(ex.getMessage());

        }
     
      
       
         colIDEq.setCellValueFactory(new PropertyValueFactory<>("idEquipe"));
         ColNomEq.setCellValueFactory(new PropertyValueFactory<>("nom"));
         ColLogoEq.setCellValueFactory(new PropertyValueFactory<>("logo"));
         ColScoreEq.setCellValueFactory(new PropertyValueFactory<>("score"));        
         ColRegionEq.setCellValueFactory(new PropertyValueFactory<>("region"));


        tvboxEq.setItems(EquipeData);
       
       
       
       


    }


    
    
    
    private void addEquipe() throws SQLException, MalformedURLException, IOException{
        
        Equipe E;
        String nom = tfNomEq.getText();
        String logo = tfLogoEq.getText();
        String region = tfRegionEq.getText();

        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

          if(nom.equals("") || logo.equals("") || region.equals("") ){
               //Alert Saisie Tournois :
             alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill all the fields first!");
            alert.showAndWait();
            //Alert Saisie Tournois !
          }else{
               E = new Equipe(nom,logo,getTxtScore(),region);
               try{
                    es.ajouter(E); 
                     alert.setAlertType(Alert.AlertType.INFORMATION);
                     alert.setTitle("Add Equipe");
                     alert.setHeaderText("Results:");
                     alert.setContentText("Equipe added successfully!");
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
        EquipeData.clear();
        try {
            EquipeData.addAll(es.afficher());
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());

        }
    
    }
    
    
    
    
    
    
    
    
    
    
    
    


    private void EditAction() { 
if (tvboxEq.getSelectionModel().getSelectedItem() != null) {

            Equipe J = tvboxEq.getSelectionModel().getSelectedItem();
            
           es.UpdateF(J.getIdEquipe(),tfNomEq.getText(),tfLogoEq.getText(),getTxtScore(), tfRegionEq.getText());
            
            Alert EditeJeuxAlert = new Alert(Alert.AlertType.INFORMATION);
            EditeJeuxAlert.setTitle("edit");
            EditeJeuxAlert.setHeaderText(null);
            EditeJeuxAlert.setContentText("Equipe was succfuly Updated");
            EditeJeuxAlert.showAndWait();

        } else {
            //Alert Select jeux :
            Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
            selectMealAlert.setTitle("Select a Equipe");
            selectMealAlert.setHeaderText(null);
            selectMealAlert.setContentText("You need to select Equipe first!");
            selectMealAlert.showAndWait();
            //Alert Select jeux !
        }

 EquipeData.clear();
        try {
            EquipeData.addAll(es.afficher());
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());

        }


}
    
    
    
    
    
    private void DeleteAction() throws SQLException {

        if (tvboxEq.getSelectionModel().getSelectedItem() != null) {
            Alert deleteTournoislert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteTournoislert.setTitle("Delete Partner");
            deleteTournoislert.setHeaderText(null);
            deleteTournoislert.setContentText("Are you sure want to delete this EQUIPE ?");
            Optional<ButtonType> optiondeleteTournoisAlert = deleteTournoislert.showAndWait();
            if (optiondeleteTournoisAlert.get() == ButtonType.OK) {
                Equipe E = tvboxEq.getSelectionModel().getSelectedItem();
                System.out.println(E.getIdEquipe());
                    es.supprimer(E.getIdEquipe());
             


                //Alert Delete Blog :
                Alert succDeleteMealAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteMealAlert.setTitle("Delete Blog");
                succDeleteMealAlert.setHeaderText("Results:");
                succDeleteMealAlert.setContentText("Equipe deleted successfully!");

                succDeleteMealAlert.showAndWait();
            } else if (optiondeleteTournoisAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select Equipe :
            Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
            selectMealAlert.setTitle("Select a Equipe");
            selectMealAlert.setHeaderText(null);
            selectMealAlert.setContentText("You need to select Equipe first!");
            selectMealAlert.showAndWait();
            //Alert Select Equipe !

        }
        
         EquipeData.clear();
        try {
            EquipeData.addAll(es.afficher());
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());

        }
        
        
    }


    @FXML
    private void HandleMouseAction(MouseEvent event) throws FileNotFoundException, SQLException{
         Equipe equipe= tvboxEq.getSelectionModel().getSelectedItem();
        System.out.println("id:"+ equipe.getIdEquipe());
        System.out.println("nom:"+ equipe.getNom());
        System.out.println("logo:"+ equipe.getLogo());
        System.out.println("Score:"+ equipe.getScore());
        System.out.println("Region:"+ equipe.getRegion());
              tfNomEq.setText(equipe.getNom());
            tfLogoEq.setText(equipe.getLogo());
            tfRegionEq.setText(equipe.getRegion());
            tfScoreEq.setText(String.valueOf(equipe.getScore()));
        
    }

    @FXML
    private void quitter(ActionEvent event) {
           try{
            btnquit.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/LoginAdmin.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    @FXML
    private void upload(ActionEvent event) {
        FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getAbsolutePath();
            String newfile = imageFile.replace("C:\\Users\\LENOVO\\Desktop\\Arena1\\src\\resources\\", "");
            tfLogoEq.setText(newfile);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
}
