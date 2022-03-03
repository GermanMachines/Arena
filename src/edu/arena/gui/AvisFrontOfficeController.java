/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.Services.AvisService;
import edu.arena.Services.ProduitService;
import edu.arena.entities.Avis;
import edu.arena.entities.Produit;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class AvisFrontOfficeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
       @FXML
    private Label lnomproduit;
    @FXML
    private Label lidproduit;
    @FXML
    private ChoiceBox<Integer> score;
    @FXML
    private Label lnomproduit1;
    @FXML
    private Label lidproduit1;
    @FXML
    private ChoiceBox<Integer> score1;
    @FXML
    private Label lnomproduit2;
    @FXML
    private Label lidproduit2;
    @FXML
    private ChoiceBox<Integer> score2;
    private Scene scene;
    Parent root;
    static int i = 0;
    @FXML
    private Label iduser;
    @FXML
    private TextField tfCommentaire;
    @FXML
    private TextField tfCommentaire1;
    @FXML
    private TextField tfCommentaire2;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    //    Event event = null;
        ObservableList<Integer> scores = FXCollections.observableArrayList(1,2,3,4,5);
            score.setItems(scores);
            score1.setItems(scores);
            score2.setItems(scores);
            ProduitService ps = new ProduitService();
            try{
                 
                File myObj = new File("C:/Users/SBS/Arena/src/edu/arena/utils/data.txt");
                Scanner myReader = new Scanner(myObj);
                String id = myReader.nextLine();
                String nom =myReader.nextLine();
      
                iduser.setText(id);
           
        
                iduser.setVisible(false);
                
                lidproduit.setVisible(false);
                lidproduit1.setVisible(false);
                lidproduit2.setVisible(false);
     
                myReader.close();
    
                ObservableList<Produit> listProduit = ps.afficher();
                Produit p1 = listProduit.get(0);
                Produit p2 = listProduit.get(1);
                Produit p3 = listProduit.get(2);
                listProduit.forEach(p -> System.out.println(p.toString()));
                lnomproduit.setText(p1.getNom());
                lidproduit.setText(Integer.toString(p1.getId()));
                
                lnomproduit1.setText(p2.getNom());
                lidproduit1.setText(Integer.toString(p2.getId()));
                
                lnomproduit2.setText(p3.getNom());
                lidproduit2.setText(Integer.toString(p3.getId()));
                //ActionEvent event = new ActionEvent();
        /*        score.setOnAction(e -> {
                    try{
                        sendAvis(score,tfCommentaire,lidproduit,iduser);
                    }catch(SQLException ex){
                        ex.printStackTrace();
                    }
                });
                
                score1.setOnAction(e -> {
                    try{
                        sendAvis(score1,tfCommentaire1,lidproduit1,iduser);
                    }catch(SQLException ex){
                        ex.printStackTrace();
                    }
                });
                
                score2.setOnAction(e -> {
                    try{
                        sendAvis(score2,tfCommentaire2,lidproduit2,iduser);
                    }catch(SQLException ex){
                        ex.printStackTrace();
                    }
                });*/
            }catch(Exception e){
                e.printStackTrace();
            }
    
        
    }

        public void sendAvis(ChoiceBox<Integer> cb, TextField commentaire ,Label idProd , Label idUser) throws SQLException{
            AvisService as = new AvisService();
            Avis a = new Avis();
            a.setScore(cb.getValue());
            a.setCommentaire(commentaire.getText());
            a.setIdProduit(Integer.parseInt(idProd.getText()));
            a.setIdUser(Integer.parseInt(iduser.getText()));
            
            System.out.println("send avis method "+a.toString());
                as.ajouter(a);
                        
            
        
            
    }
        

    @FXML
    private void load(ContextMenuEvent event) {
            System.out.println("test");
    }

    @FXML
    private void sendBtn(ActionEvent event) {
        if(event.getSource() == btn1){
              try{
                        sendAvis(score,tfCommentaire,lidproduit,iduser);
                    }catch(SQLException ex){
                        ex.printStackTrace();
                    }
        }
        
         if(event.getSource() == btn2){
              try{
                        sendAvis(score1,tfCommentaire1,lidproduit1,iduser);
                    }catch(SQLException ex){
                        ex.printStackTrace();
                    }
        }
         
          if(event.getSource() == btn3){
              try{
                        sendAvis(score2,tfCommentaire2,lidproduit2,iduser);
                    }catch(SQLException ex){
                        ex.printStackTrace();
                    }
        }
    }
}

    
       
    
    


    