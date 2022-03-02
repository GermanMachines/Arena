/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Comentaire;
import edu.arena.entities.Post;
import edu.arena.services.ComentaireCrud;
import edu.arena.services.PostCrud;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class PostBackController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfAuteur;
    @FXML
    private TextField tfDate;
    @FXML
    private TextField tfImage;
    @FXML
    private TableColumn<Post,Integer> ColId;
    @FXML
    private TableColumn<Post,String> ColTitre;
    @FXML
    private TableColumn<Post,String> ColAuteur;
    @FXML
    private TableColumn<Post,String> ColImage;
    @FXML
    private TableColumn<Post,String> ColDate;
    @FXML
    private TableView<Post> tabPost;
    
    PostCrud p =new PostCrud();
    
    public ObservableList<Post> data =FXCollections.observableArrayList();
    private Integer id_post;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private TextField tfSearch;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {           afficherEvent();
       } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        FilteredList<Post> filteredata = new FilteredList<>(data,p-> true);
        tfSearch.textProperty().addListener((observable , oldValue,newValue) ->{
          filteredata.setPredicate(Post ->{
              // If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Post.getAuteur().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Post.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Post.getImg_post()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
              
              
          });
            
            
            
            
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
		SortedList<Post> sortedData = new SortedList<>(filteredata);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tabPost.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tabPost.setItems(sortedData);
        
        
    }
      private void afficherEvent() throws SQLException {
       PostCrud c = new PostCrud();
       
        data.addAll(p.showpost());
          System.out.println(data);
        
  ColId.setCellValueFactory(new PropertyValueFactory<>("id_post"));
       ColTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
      ColAuteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
       ColImage.setCellValueFactory(new PropertyValueFactory<>("img_post"));
         ColDate.setCellValueFactory(new PropertyValueFactory<>("date_post"));
      tabPost.setItems(data);
   }  

    @FXML
    private void addPost(ActionEvent event)  throws SQLException {
        String titre = tfTitre.getText();
        String auteur = tfAuteur.getText();
       
        String img_post = tfImage.getText();
          String date_post = tfDate.getText();
        Post P = new Post(titre,auteur,img_post,date_post);
          Post p = new Post(tfTitre.getText(),tfAuteur.getText(),tfImage.getText(),tfDate.getText());
        
       PostCrud pst = new PostCrud();
        
        pst.ajouter(p);
        
         try {
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Added!");
                alert.show();
                afficherEvent();
                        
               
                
               
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
                 afficherEvent();

            }
                  
        
    }
  
    @FXML
    private void updatePost(ActionEvent event) throws SQLException {
        {
          Post A= tabPost.getSelectionModel().getSelectedItem();
           

        String date = tfDate.getText();
        String titre = tfTitre.getText();
        String image = tfImage.getText();
        String auteur = tfAuteur.getText();
       
       
       
        
        A.setTitre(titre);
         A.setAuteur(auteur);
         A.setImg_post(image);
         A.setDate_post(date);
        
        PostCrud aS = new PostCrud();
        if (aS.update(A)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La modification d'event a été effectué avec succées");
        alert.showAndWait();
        afficherEvent();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La modufication d'event n'a pas été effectué!");
        alert.showAndWait();   
        afficherEvent();
        }
    }
        
    }

    @FXML
    private void deletePost(ActionEvent event) throws SQLException {
    Post A= tabPost.getSelectionModel().getSelectedItem();
        PostCrud aS = new PostCrud();
        if (aS.delete(A)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La suppression d'event a été effectué avec succées");
        alert.showAndWait();
        afficherEvent();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La supression d'event n'a pas été effectué!");
        alert.showAndWait();   
        afficherEvent();
        }
    }

    @FXML
    private void selectPost(MouseEvent event) {
        
    Post A= tabPost.getSelectionModel().getSelectedItem();
    tfTitre.setText(A.getTitre());
    tfAuteur.setText(A.getAuteur());
    tfImage.setText(A.getImg_post());
    tfDate.setText(A.getDate_post());
    
        
    }

   
//    @FXML
//    private void recherche(ActionEvent event) {
//           PostCrud post = new PostCrud();
//            
//        ObservableList<post> liste = Post.f(tfFind.getText());
//         ColId.setCellValueFactory(new PropertyValueFactory<>("id_post"));
//       ColTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
//      ColAuteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
//       ColImage.setCellValueFactory(new PropertyValueFactory<>("img_post"));
//         ColDate.setCellValueFactory(new PropertyValueFactory<>("date_post"));
//      tabPost.setItems(liste);
//    
//    }


    }

    
    
    
    
    
    

