/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXComboBox;
import edu.arena.entities.Equipe;
import edu.arena.entities.User;
import edu.arena.Services.EquipeService;
import edu.arena.services.UserService;
import edu.arena.utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class UserDashboardController implements Initializable {
   ObservableList list = FXCollections.observableArrayList();
    ObservableList<User> dataList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<User, Integer> cl_id;
    @FXML
    private TableColumn<User, String> cl_surnom;
    @FXML
    private TableColumn<User, String> cl_nom;
    @FXML
    private TableColumn<User, String> cl_image;
    @FXML
    private TableColumn<User, String> cl_mdp;
    @FXML
    private TableColumn<User, Integer> cl_ideq;
    @FXML
    private TableColumn<User, String> cl_tel;
    @FXML
    private TableColumn<User, String> cl_role;
    @FXML
    private TableColumn<User, String> cl_block;
    @FXML
    private TableColumn<User, String> cl_email;
    @FXML
    private TableView<User> tvUser;
    @FXML
    private ComboBox<String> triBox;
    @FXML
    private Label trie;
    ObservableList<User> obl = FXCollections.observableArrayList();
    @FXML
    private TextField in_search;
    @FXML
    private Button quit;
    @FXML
    private TableView<User> tvUser1;
    @FXML
    private TableColumn<User, Integer> cl_id1;
    @FXML
    private TableColumn<User, String> cl_surnom1;
    @FXML
    private TableColumn<User, String> cl_nom1;
    @FXML
    private TableColumn<User, String> cl_image1;
    @FXML
    private TableColumn<User, String> cl_email1;
    @FXML
    private TableColumn<User, String> cl_mdp1;
    @FXML
    private TableColumn<User, String> cl_tel1;
    @FXML
    private TableColumn<User, Integer> cl_ideq1;
    @FXML
    private TableColumn<User, String> cl_role1;
    @FXML
    private TableColumn<User, String> cl_block1;
    @FXML
    private Button btndeblock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           showListUser();
           
       } catch (SQLException ex) {
           Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, null, ex);
       }

           try {
                  
 ObservableList<String> data = FXCollections.observableArrayList("id", "nom", "id_equipe");
     triBox.setItems(data);

            showListUser();
              search_user();
          
        } catch (SQLException ex) {
System.out.println (ex.getMessage());
        }
             search_user();
    }
      UserService es = new UserService();

    public void showListUser() throws SQLException {
        ObservableList<User> List ;
        List = es.show();
        ObservableList<User> List1;
       List1 = es.showb();
        cl_id.setVisible(false);
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        cl_surnom.setCellValueFactory(new PropertyValueFactory<>("surnom"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        cl_mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        cl_tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        cl_ideq.setCellValueFactory(new PropertyValueFactory<>("id_equipe"));
        cl_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        cl_block.setCellValueFactory(new PropertyValueFactory<>("block"));

        cl_block.setVisible(false);
        cl_mdp.setVisible(false);

        tvUser.setItems(List);
         cl_id1.setVisible(false);
        cl_id1.setCellValueFactory(new PropertyValueFactory<>("id"));

        cl_surnom1.setCellValueFactory(new PropertyValueFactory<>("surnom"));
        cl_nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_image1.setCellValueFactory(new PropertyValueFactory<>("image"));
        cl_email1.setCellValueFactory(new PropertyValueFactory<>("email"));
        cl_mdp1.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        cl_tel1.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        cl_ideq1.setCellValueFactory(new PropertyValueFactory<>("id_equipe"));
        cl_role1.setCellValueFactory(new PropertyValueFactory<>("role"));
        cl_block1.setCellValueFactory(new PropertyValueFactory<>("block"));

        cl_block1.setVisible(false);
        cl_mdp1.setVisible(false);
         tvUser1.setItems(List1);
            List = es.show();
    }

    @FXML
    private void trier(ActionEvent event) {
      Comparator<User> comparator ;
        if(triBox.getValue()=="id"){
         comparator = Comparator.comparingInt(User::getId);
         
        }
        else if(triBox.getValue()=="nom"){
         comparator = Comparator.comparing(User::getNom);
         
        }
        else{
         comparator = Comparator.comparingInt(User::getId_equipe);
         
        }
        
        FXCollections.sort(obl, comparator);
           tvUser.setItems(obl);
    }
    
    
     private void search_user() {
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cl_surnom.setCellValueFactory(new PropertyValueFactory<>("surnom"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        cl_mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));


        cl_tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
          cl_ideq.setCellValueFactory(new PropertyValueFactory<>("id_equipe"));
        cl_role.setCellValueFactory(new PropertyValueFactory<>("role"));
           //     cl_block.setCellValueFactory(new PropertyValueFactory<>("block"));

        try {

            String requete = "SELECT * FROM user";
            Statement st = DataBase.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
              obl.add(new User(rs.getInt("id"), rs.getString("surnom"), rs.getString("nom"), rs.getString("image"), rs.getString("email"), rs.getString("mdp"), rs.getString("telephone"),rs.getInt("id_equipe"), rs.getString("role"),rs.getString("block")));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        dataList = obl;
        tvUser.setItems(dataList);
        FilteredList<User> filteredData = new FilteredList<>(dataList, b -> true);
        in_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(User -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (User.getSurnom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else if (User.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (User.getImage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (User.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (User.getMdp().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (User.getTelephone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (User.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }/*else if (User.getBlock().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }*/
                else if (String.valueOf(User.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(User.getId_equipe()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else {
                    return false; // Does not match.
                }
            });
        });
        //SortedList<User> sortedData = new SortedList<>(filtredData);
       //  sortedData.comparatorProperty().bind(tvUser.comparatorProperty());
       //  tvUser.setItems(sortedData);

        tvUser.setItems(filteredData);
    }
     /*
     Mohamed Kalech
public void searchEmploye(){
        
        col_IdEmploye.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("IdEmploye"));
        col_NomEmploye.setCellValueFactory(new PropertyValueFactory<Employe,String>("NomEmploye"));
        col_DateEmploye.setCellValueFactory(new PropertyValueFactory<Employe,String>("DateEmploye"));
        col_NumEmploye.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("NumEmploye"));
          
         try{
          dataList=ServiceEmploye.getInstance().getAll();
          tableview.setItems(dataList);
          FilteredList<Employe> filtredData = new FilteredList<>(dataList, b -> true);
          TFRecherche.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(person-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(person.getNomEmploye().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }else if(person.getDateEmploye().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                 else if(String.valueOf(person.getNumEmploye()).indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else
                     return false;
                 });
             });
         SortedList<Employe> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);

         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }          
    }
*/
    @FXML
    private void HandleMouseAction(MouseEvent event) {
//          Equipe e = tvboxEq.getSelectionModel().getSelectedItem();
//        System.out.println(e.toString());
//        tfNomEq.setText(" " + e.getNom());
//        tfLogoEq.setText(" " + e.getLogo());
//        tfScoreEq.setText(" " + Integer.toString(e.getScore()));
//        tfRegionEq.setText(" " + e.getRegion());
//        tdIDEq.setText(Integer.toString(e.getIdEquipe()));
    }

    @FXML
    private void OnBlock(ActionEvent event) throws SQLException, JRException {
        if (tvUser.getSelectionModel().getSelectedItem() != null) {
       User J = tvUser.getSelectionModel().getSelectedItem();
       //System.out.println("g");
       //System.out.println(getTxtScore());
          es.blockUtilisateur(J);
       Alert EditeJeuxAlert = new Alert(Alert.AlertType.INFORMATION);
       EditeJeuxAlert.setTitle("edit");
       EditeJeuxAlert.setHeaderText(null);
       EditeJeuxAlert.setContentText("User was succfuly Updated");
       EditeJeuxAlert.showAndWait();
           //  showListUser();
          //    es.show();
            //  es.showb();

       } else {
       //Alert Select jeux :
       Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
       selectMealAlert.setTitle("Select a User");
       selectMealAlert.setHeaderText(null);
       selectMealAlert.setContentText("You need to select User first!");
       selectMealAlert.showAndWait();
       //Alert Select jeux !
       }
        showListUser();
     //   search_user();
//       Connection connexion = MyDB.getInstance().getConnexion();
//        try{
//            
//            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\LENOVO\\Desktop\\Arena1\\src\\Report\\report1.jrxml");  
//            
//        String sql="SELECT * FROM user ";
//            JRDesignQuery newQuery = new JRDesignQuery();
//            newQuery.setText(sql);
//            jasperDesign.setQuery(newQuery);
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//          
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connexion);
//            JasperViewer.viewReport(jasperPrint,false);           
//        } catch (JRException ex) {
//             System.out.println(ex.getMessage());
//        }
    
        
        
        
        
    }

    @FXML
    private void quitter(ActionEvent event) {
           try{
            quit.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/LoginAdmin.fxml"));

            Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    @FXML
    private void deblock(ActionEvent event) throws SQLException {
        
         if (tvUser1.getSelectionModel().getSelectedItem() != null) {
       User J = tvUser1.getSelectionModel().getSelectedItem();
       //System.out.println("g");
       //System.out.println(getTxtScore());
          es.deblockUtilisateur(J);
                 showListUser();

       Alert EditeJeuxAlert = new Alert(Alert.AlertType.INFORMATION);
       EditeJeuxAlert.setTitle("edit");
       EditeJeuxAlert.setHeaderText(null);
       EditeJeuxAlert.setContentText("Game was succfuly Updated");
       EditeJeuxAlert.showAndWait();
       } else {
       //Alert Select jeux :
       Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
       selectMealAlert.setTitle("Select a User");
       selectMealAlert.setHeaderText(null);
       selectMealAlert.setContentText("You need to select User first!");
       selectMealAlert.showAndWait();
       //Alert Select jeux !
       }
        
    }

    @FXML
    private void print(ActionEvent event) {
       Connection Connexion = DataBase.getInstance().getConnection();
               
               
        try{
            
            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\LENOVO\\Desktop\\Arena1\\src\\Report\\report1.jrxml");  
            
        String sql="SELECT * FROM user ";
           
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            
            jasperDesign.setQuery(newQuery);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, Connexion);
            JasperViewer.viewReport(jasperPrint,false);  
           
        } catch (JRException ex) {
             System.out.println(ex.getMessage());
        }
    }
    


    
    

}
