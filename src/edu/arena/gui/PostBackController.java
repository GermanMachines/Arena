/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.arena.entities.Comentaire;
import edu.arena.entities.Post;
import edu.arena.services.ComentaireCrud;
import edu.arena.services.PostCrud;
import edu.arena.utils.DataBase;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class PostBackController implements Initializable {

    
      Connection con;
    Statement ste;
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
    
    ObservableList<Post> data =FXCollections.observableArrayList();
 
    private Integer id_post;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    
  
    @FXML
    private TextField search;
    
    
      ObservableList< PieChart.Data> piechartdata;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {           afficherEvent();
       } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
    
        
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
     Post P;
      PostCrud pst =new PostCrud();
        String titre = tfTitre.getText();
        String auteur = tfAuteur.getText();
        String image =tfImage.getText(); 
        String date = tfDate.getText();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);

        
        if( (titre.equals("") || auteur.equals("") || date.equals("") || image.equals("") ) ){
                     
             alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill all the fields first!");
            alert.showAndWait();
          
        }else{
                  
        P = new Post(titre,auteur,image,date);
        
        try{
        pst.ajouter(P);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("Add post");
        alert.setHeaderText("Results:");
        alert.setContentText("post added successfully!");
        } catch (SQLException ex){
                     //Alert Error jeux :
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Adding Error !! ");
            alert.setContentText(ex.getMessage());
            //Alert Error jeux !
        } finally{
              alert.showAndWait();
        }
         Notifications notificationBuilder = Notifications.create().title("notification").text("post ajouter avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER).onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event){
                       System.out.println("clicked on");
                   } 
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();    
        
        }
        data.clear();
        data.addAll(pst.showpost());
        //ShowJeux();
               
   

             
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
        alert.setContentText("La modification de post a été effectué avec succées");
        alert.showAndWait();
        afficherEvent();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La modification de post n'a pas été effectué!");
        alert.showAndWait();   
        afficherEvent();
        }  
        data.clear();
        data.addAll(aS.showpost());
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
        alert.setContentText("La suppression de post a été effectué avec succées");
        alert.showAndWait();
        afficherEvent();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La suppression de post n'a pas été effectué!");
        alert.showAndWait();   
        afficherEvent();
        }
         data.clear();
        data.addAll(aS.showpost());
    }

    @FXML
    private void selectPost(MouseEvent event) {
        
    Post A= tabPost.getSelectionModel().getSelectedItem();
    tfTitre.setText(A.getTitre());
    tfAuteur.setText(A.getAuteur());
    tfImage.setText(A.getImg_post());
    tfDate.setText(A.getDate_post());
    
        
    }



    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException {
        
        try {
            Connection con = DataBase.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet query_set = stmt.executeQuery("select titre,auteur,img_post,date_post from post");
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C:/Users/Lenovo/Desktop/post.pdf"));
            my_pdf_report.open();
            PdfPTable my_report_table = new PdfPTable(4);
            PdfPCell table_cell;
            
             table_cell = new PdfPCell(new Phrase("titre"));
            my_report_table.addCell(table_cell);
            
            table_cell = new PdfPCell(new Phrase("auteur"));
            my_report_table.addCell(table_cell);
            
            table_cell = new PdfPCell(new Phrase("image"));
            my_report_table.addCell(table_cell);
            
            table_cell = new PdfPCell(new Phrase("date"));
            my_report_table.addCell(table_cell);
            
            while (query_set.next()) {
                String titre = query_set.getString("titre");
                table_cell = new PdfPCell(new Phrase(titre));
                my_report_table.addCell(table_cell);
                
                String auteur = query_set.getString("auteur");
                table_cell = new PdfPCell(new Phrase(auteur));
                my_report_table.addCell(table_cell);
                
                String img_post = query_set.getString("img_post");
                table_cell = new PdfPCell(new Phrase(img_post));
                my_report_table.addCell(table_cell);
                
                 String date_post = query_set.getString("date_post");
                table_cell = new PdfPCell(new Phrase(date_post));
                my_report_table.addCell(table_cell);
              
            }
            my_pdf_report.add(my_report_table);
            my_pdf_report.close();
            query_set.close();
            stmt.close();
            con.close();

            System.out.println("ok");

        } catch (SQLException ex) {
            System.out.println("err");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PostBackController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(PostBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
   

    }

   

    @FXML
    private void search(KeyEvent event) throws SQLException {
         data.clear();
        // System.out.println("heyy yuuu");
        data.addAll(p.showpost().stream().filter((e)-> e.getTitre().toLowerCase().contains(search.getText().toLowerCase())
                                                  || e.getAuteur().toLowerCase().contains(search.getText().toLowerCase())
                                                  || e.getDate_post().toLowerCase().contains(search.getText().toLowerCase())
        ).collect(Collectors.toList()));
       // System.out.println(data); 
      
    }
 
    
} 
    
    
    
    

