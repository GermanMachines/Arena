/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

//import static Test.parse;
import com.jfoenix.controls.JFXTextField;
import edu.arena.entities.Email;
import edu.arena.entities.Jeux;
import edu.arena.services.JeuxCrud;
import edu.arena.services.UploadServices;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import edu.arena.utils.DataBase;
import static edu.arena.utils.DataBase.db;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import static java.nio.file.Files.list;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyEvent;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class DashbordJeuxController implements Initializable {
    
    
    Connection con;
    Statement ste;
    ObservableList< PieChart.Data> piechartdata;

    ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();
    JeuxCrud jcr = new JeuxCrud();

    public ObservableList<Jeux> data = FXCollections.observableArrayList();
    
    
    UploadServices uploadservices = new UploadServices();

    @FXML
    private TextField tfNomJeux;
    @FXML
    private TextField tfImageJeux;
    @FXML
    private TableView<Jeux> tvJeux;
    @FXML
    private TableColumn<Jeux, Integer> colid;
    @FXML
    private TableColumn<Jeux, String> colNomJeux;
    @FXML
    private TableColumn<Jeux, String> colImageJeux;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
    
    @FXML
    private ImageView JeuxImage;
    @FXML
    private PieChart piechart;
    @FXML
    private Button btnSendMail;
    @FXML
    private JFXTextField searchTF;
    
    
        public ObservableList<String> List = FXCollections.observableArrayList();

    @FXML
    private ListView<String> FreeGamesList;
    @FXML
    private ImageView Imagejeux;
    
     @FXML
     private void handleButtonAction(ActionEvent event) throws SQLException{
         if(event.getSource()== btnInsert){
             addJeux();
         }else if(event.getSource()== btnDelete){
             DeleteAction();
         }else if(event.getSource()==btnUpdate){
             EditAction();
         }
     }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadlistdata();
        } catch (IOException ex) {
        }
        
//           Image myimage = new Image(getClass().getResourceAsStream("https://www.freetogame.com/g/1/thumbnail.jpg"));
//               Imagejeux.setImage(myimage);
//        
                 

                
 

                 // myImage = new Image(getClass().getResourceAsStream(""));
              
               

                  
         data.addAll(jcr.readAll());
         colid.setCellValueFactory(new PropertyValueFactory<>("IdJeux"));
         colNomJeux.setCellValueFactory(new PropertyValueFactory<>("NomJeux"));
         colImageJeux.setCellValueFactory(new PropertyValueFactory<>("ImageJeux"));
         
         
        tvJeux.setItems(data);
       // ShowJeux();
        
              // TODO
        //graph
        loadData();
        piechart.setData(piechartdata);
        // TODO
    }    
    


     
    
    public ObservableList<Jeux> getJeuxList() {
        ObservableList<Jeux> jeuxList=FXCollections.observableArrayList();
        Connection con ;
        String query = "select * from jeux"; //ORDER BY P asc

        con = DataBase.getInstance().getConnection();
            
        Statement st;
        ResultSet rs;
        
        try {

             st = con.createStatement();
             rs = st.executeQuery(query);
             Jeux jeux;
             while(rs.next()){
                 jeux= new Jeux(rs.getInt("IdJeux"),rs.getString("NomJeux"),rs.getString("ImageJeux"));
                 jeuxList.add(jeux);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jeuxList;
    }
    
    
    public void ShowJeux(){
         ObservableList<Jeux> jeux = getJeuxList();
         colid.setCellValueFactory(new PropertyValueFactory<>("IdJeux"));
         colNomJeux.setCellValueFactory(new PropertyValueFactory<>("NomJeux"));
         colImageJeux.setCellValueFactory(new PropertyValueFactory<>("ImageJeux"));
         
         
        tvJeux.setItems(jeux);
        
    }


    
    private void addJeux() {

       // String FilenameInserver = uploadservices.upload(tfImageJeux.getText());

        Jeux J;
        String nom = tfNomJeux.getText();
        String Image = tfImageJeux.getText();
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);

        
        if( (nom.equals("") || Image.equals("") ) ){
                       //Alert saisie jeux :
             alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill all the fields first!");
            alert.showAndWait();
            //Alert saisie jeux !
        }else{
                  
        J = new Jeux(nom, Image);
        
        try{
        jcr.ajouter(J);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Game");
        alert.setHeaderText("Results:");
        alert.setContentText("Game added successfully!");
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
        }
        data.clear();
        data.addAll(jcr.readAll());
        //ShowJeux();
                loadData();
        piechart.setData(piechartdata);
    }

    

     
    
    
        private void EditAction() {

        if (tvJeux.getSelectionModel().getSelectedItem() != null) {

            Jeux J = tvJeux.getSelectionModel().getSelectedItem();

            jcr.Update(J.getIdJeux(),tfNomJeux.getText(),tfImageJeux.getText());
            Alert EditeJeuxAlert = new Alert(Alert.AlertType.INFORMATION);
            EditeJeuxAlert.setTitle("edit");
            EditeJeuxAlert.setHeaderText(null);
            EditeJeuxAlert.setContentText("Game was succfuly Updated");
            EditeJeuxAlert.showAndWait();

        } else {
            //Alert Select jeux :
            Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
            selectMealAlert.setTitle("Select a Game");
            selectMealAlert.setHeaderText(null);
            selectMealAlert.setContentText("You need to select Game first!");
            selectMealAlert.showAndWait();
            //Alert Select jeux !
        }

//                JeuxCrud su =new JeuxCrud();
//                su.Update(125, "final", "image.jpg");
        //ShowJeux();
    }
    
    
    
    
    public void changeNomCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Jeux JeuxSelected = tvJeux.getSelectionModel().getSelectedItem();
        JeuxSelected.setNomJeux(edittedCell.getNewValue().toString());
    }
    
    
        public void changeImageCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Jeux JeuxSelected = tvJeux.getSelectionModel().getSelectedItem();
        JeuxSelected.setImageJeux(edittedCell.getNewValue().toString());
    }
    
    
    
    
    
    
    
    
    
    
    private void DeleteAction() throws SQLException {

        if (tvJeux.getSelectionModel().getSelectedItem() != null) {
            Alert deleteJeuxAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteJeuxAlert.setTitle("Delete Partner");
            deleteJeuxAlert.setHeaderText(null);
            deleteJeuxAlert.setContentText("Are you sure want to delete this Game ?");
            Optional<ButtonType> optiondeleteJeuxAlert = deleteJeuxAlert.showAndWait();
            if (optiondeleteJeuxAlert.get() == ButtonType.OK) {
                Jeux J = tvJeux.getSelectionModel().getSelectedItem();
                try {
                    jcr.delete(J.getIdJeux());
                } catch (SQLException ex) {
                    ex.getMessage();
                }
                data.clear();
                data.addAll(jcr.readAll());

                //Alert Delete Blog :
                Alert succDeleteMealAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteMealAlert.setTitle("Delete Game");
                succDeleteMealAlert.setHeaderText("Results:");
                succDeleteMealAlert.setContentText("Game deleted successfully!");

                succDeleteMealAlert.showAndWait();
            } else if (optiondeleteJeuxAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select Game :
            Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
            selectMealAlert.setTitle("Select a Game");
            selectMealAlert.setHeaderText(null);
            selectMealAlert.setContentText("You need to select Game first!");
            selectMealAlert.showAndWait();
            //Alert Select Game !

        }
        //ShowJeux();
                loadData();
        piechart.setData(piechartdata);
    }
    
    
    


    @FXML
    private void handleMouseAction(MouseEvent event) throws FileNotFoundException {
        Jeux jeux= tvJeux.getSelectionModel().getSelectedItem();
        System.out.println("id:"+ jeux.getIdJeux());
        System.out.println("NomJeux:"+ jeux.getNomJeux());
        System.out.println("Image:"+ jeux.getImageJeux());
        tfNomJeux.setText(jeux.getNomJeux());
        tfImageJeux.setText(jeux.getImageJeux());
        String imagename = jcr.getImageJeux(jeux.getIdJeux());
        Image myImage = new Image(getClass().getResourceAsStream("/resources/"+imagename+""));
            //   Image image = new Image(getClass().getResourceAsStream("/resources/fifa2022.png"));
      //  img.setImage(image);
        JeuxImage.setImage(myImage);
         
    }

    @FXML
    private void upload(ActionEvent event) {
         FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getAbsolutePath();
            String newimageFile = imageFile.replace("C:\\Users\\tarek\\OneDrive\\Documents\\NetBeansProjects\\Arena++\\src\\resources\\","");

            tfImageJeux.setText(newimageFile);
        } 
    }

    private void loadData() {

  String query = "select COUNT(*) as count,j.NomJeux From tournois t, jeux j WHERE j.IdJeux=t.IdJeux GROUP BY t.IdJeux"; //ORDER BY P asc

        piechartdata = FXCollections.observableArrayList();

        con = DataBase.getInstance().getConnection();

        try {

            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {

                piechartdata.add(new PieChart.Data(rs.getString("j.NomJeux"),rs.getInt("count")));
                p.add(rs.getString("j.NomJeux"));
                c.add(rs.getInt("count"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    
    
    
    
    @FXML
    private void filter(KeyEvent event) {
        data.clear();
        // System.out.println("heyy yuuu");
        data.addAll(jcr.readAll().stream().filter((e)
                -> e.getNomJeux().toLowerCase().contains(searchTF.getText().toLowerCase())
        ).collect(Collectors.toList()));
       // System.out.println(data);
    }
    
    
    

    @FXML
    private void Sendmail(ActionEvent event) {
                    //    Emprunt E = empruntadmin.getSelectionModel().getSelectedItem();
              //  int id=E.getId();

           //     ServicesUsers us =new ServicesUsers();
                 // String mail=us.getMail(id);
                String mail="ayadipawlou@gmail.com";
                
                     Email email = new Email();
        String message = null;
       // message.put("Title", "Arena Administration " );
//         message.put("UpdatedAt","");
//        message.put("Description", "hh");
       // message.put("Content","please return the book  ! You have passed the dead line");
       message="Welcome to Arena";
        try {
            Email.sendEmail(mail,"Arena Administration", message);

        } catch (Exception ex) {
            ex.getMessage();
        }
    }
    
 
    
    public void loadlistdata() throws MalformedURLException, IOException{
        
     BufferedReader reader;
    String line;
    StringBuffer responseContent = new StringBuffer();
    URL url = new URL("https://www.freetogame.com/api/games?platform=pc");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
connection.setRequestMethod("GET");
int status = connection.getResponseCode();
       System.out.println(status);


reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
while((line=reader.readLine())!=null){
    responseContent.append(line);
}
reader.close();

      // System.out.println(responseContent.toString());
parse(responseContent.toString());

connection.disconnect();
        
       
             
    }
    
    
       public String parse(String ResponseBody){
       JSONArray Games = new JSONArray(ResponseBody);
               List.removeAll(List);
       for(int i=0 ; i < Games.length();i++){
           JSONObject game = Games.getJSONObject(i);
           int id = game.getInt("id");
           String title = game.getString("title");
           String thumbnail = game.getString("thumbnail");
           String Genre = game.getString("genre");
           String platform = game.getString("platform");
           String release_date = game.getString("release_date");
           
           //System.out.println(id+ "  " + title+ "  " + thumbnail);
           
    
            List.addAll(String.valueOf(id),title,thumbnail,Genre,platform,release_date);
       }
            FreeGamesList.getItems().addAll(List);

       
       
       return null;
   }

    @FXML
    private void displaySelected(MouseEvent event) {
        
        
        
        String imageSource = FreeGamesList.getSelectionModel().getSelectedItem();
         System.out.println(imageSource);
//            Image myimage = new Image(getClass().getResourceAsStream("https://www.freetogame.com/g/1/thumbnail.jpg"));
//            Imagejeux.setImage(myimage);



                  Image myImage;
                  String X=imageSource;
                  String noSpaceStr =X.replaceAll("\\s", ""); // using built in method  
                    //System.out.println(noSpaceStr);
                  URI uri = URI.create("C://xampp//htdocs//ResourcesArena//");
                URI uri2 = uri.resolve("./"+noSpaceStr+".jpg");
               String  uri_to_string= uri2.toString();

                  
        try {
            myImage = new Image(new FileInputStream(uri_to_string));
            Imagejeux.setImage(myImage);
        } catch (FileNotFoundException ex) {
            System.out.println("Image Not Found");
        }
       
    }

 
    
    
}
