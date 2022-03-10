/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;
import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import edu.arena.entities.Equipe;
import edu.arena.entities.Jeux;
import edu.arena.entities.JeuxTournois;
import edu.arena.entities.Match;
import edu.arena.entities.MatchEquipe;
import edu.arena.entities.Tournois;
import edu.arena.services.EquipeCrud;
import edu.arena.services.JeuxCrud;
import edu.arena.services.JeuxTournoisCrud;
import edu.arena.services.MatchCrud;
import edu.arena.services.MatchEquipeCrud;
import edu.arena.services.TournoisCrud;
import edu.arena.services.UploadServices;
import edu.arena.utils.DataBase;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class DashbordTournoisController implements Initializable {

    
    Connection con;
    Statement ste;
    ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();
    TournoisCrud jcr = new TournoisCrud();
        JeuxCrud jc = new JeuxCrud();
        EquipeCrud je = new EquipeCrud();
        MatchCrud ma= new MatchCrud();
           JeuxTournoisCrud jT = new JeuxTournoisCrud();
            MatchEquipeCrud mec = new MatchEquipeCrud();
           
     public ObservableList<Tournois> TournoisData = FXCollections.observableArrayList();

    public ObservableList<Integer> dataJeuxTournois = FXCollections.observableArrayList(1,3,4);

    
    public ObservableList<Tournois> data = FXCollections.observableArrayList();
    
     public ObservableList<Equipe> EquipeData = FXCollections.observableArrayList();
     public ObservableList<Match> MatchData = FXCollections.observableArrayList();
     public ObservableList<Equipe> EquipeDataMatch= FXCollections.observableArrayList();

    
    UploadServices uploadservices = new UploadServices();
    
    @FXML
    private JFXTextField tfTitre;
    @FXML
    private JFXDatePicker tfDateDebut;
    @FXML
    private JFXDatePicker tfDateFin;
    @FXML
    private JFXTextField tfdescription;
    @FXML
    private JFXComboBox<String> comboJeux;
    public ObservableList<String> Jeuxff = FXCollections.observableArrayList(jc.ListJeux());

    @FXML
    private JFXComboBox<String> Combotype;
    public ObservableList<String> typeff = FXCollections.observableArrayList("1v1", "Equipe");
    @FXML
    private JFXTextField tfnbrparticipants;
    @FXML
    private TableView<Tournois> tvTournoix;
    @FXML
    private TableColumn<Tournois, Integer> colidTournois;
    @FXML
    private TableColumn<Tournois, String> colTitreTournois;
    @FXML
    private TableColumn<Tournois, String> colDatedebutTournois;
    @FXML
    private TableColumn<Tournois, String> colDatefinTournois;
    @FXML
    private TableColumn<Tournois, String> colDescriptionTournois;
    @FXML
    private TableColumn<Tournois, Integer> colnbParticipantsTournois;
    @FXML
    private TableColumn<Tournois, String> colTypeTournois;
    @FXML
    private TableColumn<Tournois, Integer> colIdJeuxTournois;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private JFXComboBox<Integer> EquipeComboBox;
    public ObservableList<Integer> equipeff = FXCollections.observableArrayList(je.ListEquipe());

    @FXML
    private TableView<Tournois> TournoisTv;
    @FXML
    private TableColumn<Tournois, String> colTitre;
    @FXML
    private TableColumn<Tournois, String> colDescription;
    @FXML
    private TableColumn<Tournois, Integer> colDateDebut;
    @FXML
    private TableView<Equipe> TournoisEquipeTv;
    private TableColumn<JeuxTournois, Integer> equipe;
    @FXML
    private JFXButton DeleteEqTo;
    String idTournoisaffecter="1";
    @FXML
    private JFXTextField tfidT;
    @FXML
    private TableColumn<JeuxTournois, Integer> colequipe;
    @FXML
    private TableColumn<JeuxTournois, String> colNomEquipe;
    @FXML
    private TableColumn<JeuxTournois, Integer> colScoreEquipe;
    @FXML
    private TableColumn<JeuxTournois, String> colRegionEquipe;
    @FXML
    private ComboBox<String> TournoisCombo;
     public ObservableList<String> Tournoist = FXCollections.observableArrayList(jcr.ListTournois());

    @FXML
    private TableColumn<Match, Integer> colidmatch;
    @FXML
    private TableColumn<Match, String> coldatematch;
    @FXML
    private TableColumn<Match, String> colnomtournois;
    @FXML
    private TableColumn<JeuxTournois, Integer> colidequipe;
    @FXML
    private TableColumn<JeuxTournois, String> colnomequipe;
    @FXML
    private TableColumn<JeuxTournois, String> colregion;
    @FXML
    private JFXDatePicker tfDateMatch;
    @FXML
    private TableView<Match> tvTMatch;
    @FXML
    private TableView<Equipe> TournoismatchTv;
    @FXML
    private JFXTextField tfscore;
    @FXML
    private TableView<Tournois> tvTournoix1;
    @FXML
    private TableColumn<Tournois, Integer> colidTournois1;
    @FXML
    private TableColumn<Tournois, String> colTitreTournois1;
    @FXML
    private TableView<Match> TvMatchTournois;
    @FXML
    private TableColumn<Match, Integer> colIdMatch;
    @FXML
    private TableColumn<Match, String> colDateMatch;
    @FXML
    private TableColumn<Equipe,String> ColNomEquipe;
    @FXML
    private TableColumn<Equipe, String> ColRegionEq;
    @FXML
    private TableView<Equipe> tvEquipeMatch;
    @FXML
    private JFXTextField tfScore;
    @FXML
    private TableColumn<Equipe, Integer> ColIdEquipe;
    @FXML
    private JFXTextField tfWinner;
    @FXML
    private JFXTextField tfStatus;
    @FXML
    private TableColumn<Tournois, String> ColWinner;
    @FXML
    private TableColumn<Tournois, String> ColStatus;
    @FXML
    private JFXTextField tfReference;
    @FXML
    private TableColumn<Match, String> ColReference;
    @FXML
    private JFXTextField tfSearchTournois;
    
        public int getTxtnbParticipants() {
        return Integer.parseInt(tfnbrparticipants.getText());
    }
        
          public int getTxtScore() {
        return Integer.parseInt(tfScore.getText());
    }
        
                @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException {
    if(event.getSource()== btnInsert){
   addTournois();
         }else if(event.getSource()==btnDelete){
             DeleteAction();
         }else if (event.getSource()==btnUpdate){
             EditAction();
         }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        
        
        try {
            data.addAll(jcr.readAll());
        } catch (SQLException ex) {
                        System.out.println(ex.getMessage());

        }
     
      
       
         colidTournois.setCellValueFactory(new PropertyValueFactory<>("IdTournois"));
         colTitreTournois.setCellValueFactory(new PropertyValueFactory<>("Titre"));
         colDatedebutTournois.setCellValueFactory(new PropertyValueFactory<>("Date_debut"));
         colDatefinTournois.setCellValueFactory(new PropertyValueFactory<>("Date_fin"));        
         colDescriptionTournois.setCellValueFactory(new PropertyValueFactory<>("DescriptionTournois"));
         colTypeTournois.setCellValueFactory(new PropertyValueFactory<>("Type"));  
         colnbParticipantsTournois.setCellValueFactory(new PropertyValueFactory<>("NbrParticipants"));
         colIdJeuxTournois.setCellValueFactory(new PropertyValueFactory<>("IdJeux"));
         ColWinner.setCellValueFactory(new PropertyValueFactory<>("Winner"));
         ColStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        tvTournoix.setItems(data);
       
       
       
       
       
        
        Combotype.setItems(typeff);
        comboJeux.setItems(Jeuxff); 
         TournoisCombo.setItems(Tournoist); 

        EquipeComboBox.setItems(equipeff);
                ShowTournoisv2();
               ShowTournoisv4();
                ShowTournoisv5();


    }    
    
    

    
    

    private void addTournois() throws SQLException, MalformedURLException, IOException{
        
        Tournois T;
        String Titre = tfTitre.getText();
      //  String DateD = tfDateDebut.getValue().format(DateTimeFormatter.ISO_DATE);
       // String DateF = tfDateFin.getValue().format(DateTimeFormatter.ISO_DATE);
        String Desc = tfdescription.getText();
        String combotype = Combotype.getValue();
        String Nomjeux = comboJeux.getValue();
        int idjeux = jc.getidJeuxbynom(Nomjeux);

        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

          if(Titre.equals("") || Desc.equals("") || combotype.equals("") || ( (tfDateDebut.getValue().format(DateTimeFormatter.ISO_DATE)).compareTo(tfDateFin.getValue().format(DateTimeFormatter.ISO_DATE))>0) ){
               //Alert Saisie Tournois :
             alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill all the Required fields first! And verify Date Time");
            alert.showAndWait();
            //Alert Saisie Tournois !
          }else{
               T = new Tournois(Titre,tfDateDebut.getValue().format(DateTimeFormatter.ISO_DATE),tfDateFin.getValue().format(DateTimeFormatter.ISO_DATE),Desc,combotype,getTxtnbParticipants(),idjeux);
               try{
                    jcr.ajouter(T); 
                     alert.setAlertType(Alert.AlertType.INFORMATION);
                     alert.setTitle("Add Tournois");
                     alert.setHeaderText("Results:");
                     alert.setContentText("Tournois added successfully!");
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
        data.clear();
        try {
            data.addAll(jcr.readAll());
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());

        }
        //ShowTournois();
        
        Calendar(tfTitre.getText(),tfDateDebut.getValue().format(DateTimeFormatter.ISO_DATE),tfDateFin.getValue().format(DateTimeFormatter.ISO_DATE));
//        
//        // Using Calendar api
//          URL url = new URL("https://www.googleapis.com/calendar/v3/calendars/c_6gv9km36vml1p5d7cftkkfd6io@group.calendar.google.com/events/");
//HttpURLConnection http = (HttpURLConnection)url.openConnection();
//http.setRequestMethod("POST");
//http.setDoOutput(true);
//http.setRequestProperty("Authorization", "Bearer ya29.A0ARrdaM_bcC1MxvT9OVedSjog-HO_59GIeFSAiyfnqGWO8CZ34H72k7t7uXn0H0dX4WRX_ep15q_x55ec5UIg0Y6Xk0NFHFtIp8J3csJplPBx5JpAymDKcBObb0RtFofuYmzNgbUsTw5f24MhG6Om3rd9xmlo");
//http.setRequestProperty("Content-Type", "application/json");
//
//String data = "{\n\"summary\": \""+tfTitre.getText()+"\",\n  \"location\": \"Arena Application\",\n  \"start\": {\n    \"dateTime\": \""+tfDateDebut.getValue().format(DateTimeFormatter.ISO_DATE)+"T10:00:00.000-07:00\"\n  },\n  \"end\": {\n    \"dateTime\": \""+tfDateFin.getValue().format(DateTimeFormatter.ISO_DATE)+"T10:25:00.000-07:00\"\n    }\n\n}";
////String data = "{\n\"summary\": \"tournament\",\n  \"location\": \"Arena Application\",\n  \"start\": {\n    \"dateTime\": \""+tfDateDebut.getValue().format(DateTimeFormatter.ISO_DATE)+"T10:00:00.000-07:00\"\n  },\n  \"end\": {\n    \"dateTime\": \""+tfDateFin.getValue().format(DateTimeFormatter.ISO_DATE)+"\n    },\n\"etag\": \"\", \n      \"backgroundColor\": \"#b80672\", \n      \"timeZone\": \"UTC\", \n      \"accessRole\": \"reader\",\n\"kind\": \"calendar#calendarListEntry\", \n      \"foregroundColor\": \"#ffffff\", \n      \"defaultReminders\": [], \n      \"colorId\": \"2\"\n\n}\n";
//byte[] out = data.getBytes(StandardCharsets.UTF_8);
//
//OutputStream stream = http.getOutputStream();
//stream.write(out);
//
//System.out.println(http.getResponseCode() + " " + http.getResponseMessage() + "Tournmend Event added to Calendar Successfully");
//http.disconnect();
//        
//        // end Calendar 
        
        
        
        
        
    }
    
    
    
        
        private void EditAction() throws SQLException {

        if (tvTournoix.getSelectionModel().getSelectedItem() != null) {

            Tournois T = tvTournoix.getSelectionModel().getSelectedItem();
            
            String Nomjeux = comboJeux.getValue();
            int idjeux = jc.getidJeuxbynom(Nomjeux);
            jcr.Update(T.getIdTournois(),tfTitre.getText(),tfDateDebut.getValue().format(DateTimeFormatter.ISO_DATE),tfDateFin.getValue().format(DateTimeFormatter.ISO_DATE),tfdescription.getText(),Combotype.getValue(),getTxtnbParticipants(),idjeux,tfWinner.getText() , tfStatus.getText());
            Alert EditeTournoisAlert = new Alert(Alert.AlertType.INFORMATION);
            EditeTournoisAlert.setTitle("edit");
            EditeTournoisAlert.setHeaderText(null);
            EditeTournoisAlert.setContentText("Tournois was succfuly Updated");
            EditeTournoisAlert.showAndWait();

        } else {
            //Alert Select Tournois :
            Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
            selectMealAlert.setTitle("Select a Tournois");
            selectMealAlert.setHeaderText(null);
            selectMealAlert.setContentText("You need to select Tournois first!");
            selectMealAlert.showAndWait();
            //Alert Select Tournois !
        }
                data.clear();
        data.addAll(jcr.readAll());

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void DeleteAction() throws SQLException {

        if (tvTournoix.getSelectionModel().getSelectedItem() != null) {
            Alert deleteTournoislert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteTournoislert.setTitle("Delete Partner");
            deleteTournoislert.setHeaderText(null);
            deleteTournoislert.setContentText("Are you sure want to delete this Tournament ?");
            Optional<ButtonType> optiondeleteTournoisAlert = deleteTournoislert.showAndWait();
            if (optiondeleteTournoisAlert.get() == ButtonType.OK) {
                Tournois T = tvTournoix.getSelectionModel().getSelectedItem();
                try {
                    jcr.delete(T.getIdTournois());
                } catch (SQLException ex) {
                    ex.getMessage();
                }
                data.clear();
                data.addAll(jcr.readAll());

                //Alert Delete Blog :
                Alert succDeleteMealAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteMealAlert.setTitle("Delete Blog");
                succDeleteMealAlert.setHeaderText("Results:");
                succDeleteMealAlert.setContentText("Game deleted successfully!");

                succDeleteMealAlert.showAndWait();
            } else if (optiondeleteTournoisAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select Tournois :
            Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
            selectMealAlert.setTitle("Select a Tournois");
            selectMealAlert.setHeaderText(null);
            selectMealAlert.setContentText("You need to select Tournois first!");
            selectMealAlert.showAndWait();
            //Alert Select Tournois !

        }
      //  ShowTournois();
    }
    
    
    @FXML
        private void handleMouseAction(MouseEvent event) throws FileNotFoundException, SQLException {
        Tournois tournoi= tvTournoix.getSelectionModel().getSelectedItem();
        System.out.println("id:"+ tournoi.getIdTournois());
        System.out.println("Titre:"+ tournoi.getTitre());
        System.out.println("DateDebut:"+ tournoi.getDate_debut());
        System.out.println("DateFin:"+ tournoi.getDate_fin());
        System.out.println("Description:"+ tournoi.getDescriptionTournois());
        System.out.println("Type:"+ tournoi.getType());
        System.out.println("NombresParticipants:"+ tournoi.getNbrParticipants());
        System.out.println("IdJeux:"+ tournoi.getIdJeux());
        System.out.println("Winner:"+ tournoi.getWinner());
        System.out.println("Status:"+ tournoi.getStatus());

        String NomGame = jc.getJeuxBynom(tournoi.getIdJeux());
        tfTitre.setText(tournoi.getTitre());
       // tfDateDebut.setText(tournoi.getDate_debut());
       // tfDateFin.setText(tournoi.getDate_fin());
       // tfDateDebut.setValue().format(DateTimeFormatter.ISO_DATE);
            tfDateDebut.setValue(LocalDate.now());
            tfDateFin.setValue(LocalDate.now());
        tfdescription.setText(tournoi.getDescriptionTournois());
        comboJeux.setValue(NomGame);
        Combotype.setValue(tournoi.getType());
        tfnbrparticipants.setText(String.valueOf(tournoi.getNbrParticipants()));
        tfWinner.setText(tournoi.getWinner());
        tfStatus.setText(tournoi.getStatus());

    }
    
    
    
    

    
        private void ShowTournoisv2() {
        
          ObservableList<Tournois> jeux = getTournoisList();
         colTitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
         colDescription.setCellValueFactory(new PropertyValueFactory<>("DescriptionTournois"));
         colDateDebut.setCellValueFactory(new PropertyValueFactory<>("IdTournois"));


        TournoisTv.setItems(jeux);
    }
    
    
 private void ShowTournoisv3(){
        TournoisEquipeTv.getItems();
 
        }
        
        
        
    

    private ObservableList<Tournois> getTournoisList() {
        ObservableList<Tournois> TournoisList=FXCollections.observableArrayList();
        Connection con ;
        String query = "select * from tournois"; //ORDER BY P asc

        con = DataBase.getInstance().getConnection();
            
        Statement st;
        ResultSet rs;
        
        try {
             st = con.createStatement();
             rs = st.executeQuery(query);
             Tournois tournoi;
             while(rs.next()){
                 tournoi= new Tournois(rs.getInt("IdTournois"),rs.getString("Titre"),rs.getString("Date_debut"),rs.getString("Date_fin"),rs.getString("DescriptionTournois"),rs.getString("Type"),rs.getInt("NbrParticipants"),rs.getInt("IdJeux"),rs.getString("Winner"),rs.getString("Status") );
                 TournoisList.add(tournoi);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return TournoisList;

    }
    
    

    
    


    @FXML
    private void AffecterTournois(ActionEvent event) {
              int idTournois = Integer.parseInt(tfidT.getText());
        JeuxTournoisCrud cer = new JeuxTournoisCrud();
        JeuxTournois ce = new JeuxTournois(EquipeComboBox.getValue(), idTournois);
        
        int nbparticipants = getTxtnbParticipants();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                
                
        int nbp = cer.VerifNombreParticipation(idTournois);
        if(nbparticipants>nbp){
        cer.ajouter(ce);
         alert.setAlertType(Alert.AlertType.INFORMATION);
                     alert.setTitle("Add Tournois");
                     alert.setHeaderText("Results:");
                     alert.setContentText("Tournois added successfully!");
                                 alert.showAndWait();
        }else{
             //Alert  Tournois :
             alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Tournois Full");
            alert.setHeaderText(null);
            alert.setContentText("You cant participate the tournament is already full");
            alert.showAndWait();
            //Alert  Tournois !
        }
        
        dataJeuxTournois.clear();
        dataJeuxTournois.addAll(jT.rechercheJeuxTournois((EquipeComboBox.getValue())));

        ShowTournoisv3();
        
    }




    private ObservableList<JeuxTournois> getJeuxTournoisList() {
        ObservableList<JeuxTournois> JeuxTournoisList=FXCollections.observableArrayList();
        Connection con ;
        String query = "select * from participation"; //ORDER BY P asc

        con = DataBase.getInstance().getConnection();
            
        Statement st;
        ResultSet rs;
        
        try {
             st = con.createStatement();
             rs = st.executeQuery(query);
             JeuxTournois jeuxtournoi;
             while(rs.next()){
                 jeuxtournoi= new JeuxTournois(rs.getInt("IdEquipe"),rs.getInt("IdTournois"));
                 JeuxTournoisList.add(jeuxtournoi);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return JeuxTournoisList;


    }

    @FXML
    private void handleMouseActionv1(MouseEvent event) {
        Tournois tournoix= TournoisTv.getSelectionModel().getSelectedItem();
        tfidT.setText(String.valueOf(tournoix.getIdTournois()));
    }

    @FXML
    private void selectTournois(ActionEvent event) throws SQLException {
                int idTournois = Integer.parseInt(tfidT.getText());
        dataJeuxTournois.clear();
        dataJeuxTournois.addAll(jT.rechercheJT((idTournois)));
        //System.out.println(dataJeuxTournois);
        
                EquipeData.clear();
        List<Equipe> equipebyid=new ArrayList<>();
       // List<Equipe> equipebyid = je.getEquipebyid(dataJeuxTournois.get(0));
       for(int i=0;i<dataJeuxTournois.size();i++){
       equipebyid.addAll(je.getEquipebyid(dataJeuxTournois.get(i)));
       }
        System.out.println(equipebyid);
    
        EquipeData.addAll(equipebyid);
        
        //ShowTournoisv3();
        colequipe.setCellValueFactory(new PropertyValueFactory<>("IdEquipe"));
        colNomEquipe.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colScoreEquipe.setCellValueFactory(new PropertyValueFactory<>("score"));
        colRegionEquipe.setCellValueFactory(new PropertyValueFactory<>("region"));

        
        
        TournoisEquipeTv.setItems(EquipeData);
        

    }

    
    
    
    
    
    
    
            private void ShowTournoisv4() {
        
          ObservableList<Match> match = getMatchList();
         colidmatch.setCellValueFactory(new PropertyValueFactory<>("IdMatch"));
         coldatematch.setCellValueFactory(new PropertyValueFactory<>("DateMatch"));
         colnomtournois.setCellValueFactory(new PropertyValueFactory<>("Titre"));
         ColReference.setCellValueFactory(new PropertyValueFactory<>("Reference"));


        tvTMatch.setItems(match);
    }
    
    
                private void ShowTournoisv5() {
        
          ObservableList<Tournois> tournois = getTournoisList();
         colidTournois1.setCellValueFactory(new PropertyValueFactory<>("IdTournois"));
         colTitreTournois1.setCellValueFactory(new PropertyValueFactory<>("Titre"));


        tvTournoix1.setItems(tournois);
    }
    
    
                

    
    
    
    @FXML
    private void AjouterMatch(ActionEvent event) throws SQLException {
        Match m;
    String Ref= tfReference.getText();
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(Ref.equals("")){
            //Alert saisie Match :
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill all the fields first!");
            alert.showAndWait();
            //Alert saisie Match !
        }else{
                    m = new Match(jcr.getidTournoisbynom(TournoisCombo.getValue()),tfDateMatch.getValue().format(DateTimeFormatter.ISO_DATE),Ref);
                    try{
                                ma.ajouter(m);
                            alert.setAlertType(Alert.AlertType.INFORMATION);
                            alert.setTitle("Add Match");
                            alert.setHeaderText("Results:");
                            alert.setContentText("Match added successfully!");
                    }catch (SQLException ex){
                                    //Alert Error Match :
                           alert.setAlertType(Alert.AlertType.WARNING);
                           alert.setTitle("ERROR");
                           alert.setHeaderText("Adding Error !! ");
                           alert.setContentText(ex.getMessage());
                           //Alert Error Match !
        }  finally{
              alert.showAndWait();
        }
        }
        data.clear();
        data.addAll(jcr.readAll());
        ShowTournoisv4();
    }

    private ObservableList<Match> getMatchList() {
        ObservableList<Match> MatchList=FXCollections.observableArrayList();
        Connection con ;
        String query = "SELECT idMatch , DateMatch , Reference , t.titre FROM matchs m , tournois t WHERE t.IdTournois=m.IdTournois"; //ORDER BY P asc

        con = DataBase.getInstance().getConnection();
            
        Statement st;
        ResultSet rs;
        
        try {
             st = con.createStatement();
             rs = st.executeQuery(query);
             Match match;
             while(rs.next()){
                 match= new Match(rs.getInt("idMatch"),rs.getString("t.titre"),rs.getString("DateMatch"),rs.getString("Reference"));
                 MatchList.add(match);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return MatchList;
    }
    
    
    
    

    @FXML
    private void selectMatch(ActionEvent event) throws SQLException {

                Match Tournois = tvTMatch.getSelectionModel().getSelectedItem();
               String Titretournois= Tournois.getTitre();
               int idtournois=jcr.getidTournoisbynom(Titretournois);
        System.out.println(Titretournois);
        dataJeuxTournois.clear();
        dataJeuxTournois.addAll(jT.rechercheJT((idtournois)));
        //System.out.println(dataJeuxTournois);
        
                EquipeData.clear();
        List<Equipe> equipebyid=new ArrayList<>();
       // List<Equipe> equipebyid = je.getEquipebyid(dataJeuxTournois.get(0));
       for(int i=0;i<dataJeuxTournois.size();i++){
       equipebyid.addAll(je.getEquipebyid(dataJeuxTournois.get(i)));
       }
        System.out.println(equipebyid);
    
        EquipeData.addAll(equipebyid);
        
        //ShowTournoisv3();
        colidequipe.setCellValueFactory(new PropertyValueFactory<>("IdEquipe"));
        colnomequipe.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colregion.setCellValueFactory(new PropertyValueFactory<>("region"));

        
        
        TournoismatchTv.setItems(EquipeData);

    }

    @FXML
    private void Affecterequipematch(ActionEvent event) throws SQLException {
        
                     
        
        
        Match match= tvTMatch.getSelectionModel().getSelectedItem();
          int idmat= match.getIdMatch();
        Equipe equipe = TournoismatchTv.getSelectionModel().getSelectedItem();
           int idequipe= equipe.getIdEquipe();
           
           System.out.println(idmat);
           System.out.println(idequipe);


             MatchEquipeCrud mec = new MatchEquipeCrud();
             
             int score = Integer.parseInt(tfscore.getText());
             
        MatchEquipe me = new MatchEquipe(idmat, idequipe ,score );
                   System.out.println(me);

        mec.ajouter(me);
       
        
    }



    @FXML
    private void ViewMatch(ActionEvent event) throws SQLException {
               Tournois T = tvTournoix1.getSelectionModel().getSelectedItem();
               int Idtournois= T.getIdTournois();
               
               
                 MatchData.clear();

               List<Match> match = ma.getMatchbyIdTournois(Idtournois);
               
                     MatchData.addAll(match);
                     System.out.println(MatchData);
                colIdMatch.setCellValueFactory(new PropertyValueFactory<>("idMatch"));
        colDateMatch.setCellValueFactory(new PropertyValueFactory<>("DateMatch"));

        

        TvMatchTournois.setItems(MatchData);

         
    }

    @FXML
    private void ViewEquipes(ActionEvent event) throws SQLException {
                       Match M = TvMatchTournois.getSelectionModel().getSelectedItem();
               int IdMatch= M.getIdMatch();
               
               System.out.println(IdMatch);
                 EquipeDataMatch.clear();

               List<Equipe> Eq = je.getEquipebyidMatch(IdMatch);
               
                     EquipeDataMatch.addAll(Eq);
                     System.out.println(EquipeDataMatch);
                ColNomEquipe.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColRegionEq.setCellValueFactory(new PropertyValueFactory<>("region"));
        ColIdEquipe.setCellValueFactory(new PropertyValueFactory<>("IdEquipe"));

        

        tvEquipeMatch.setItems(EquipeDataMatch);
    }

    @FXML
    private void UpdateScore(ActionEvent event) throws SQLException {
        
               Match M = TvMatchTournois.getSelectionModel().getSelectedItem();
               int IdMatch= M.getIdMatch();
                    Equipe E = tvEquipeMatch.getSelectionModel().getSelectedItem();
               int IdEquipe= E.getIdEquipe();
               int score = getTxtScore();
               
               mec.Update(IdMatch, IdEquipe, score);
               
    }

    @FXML
    private void filterTournois(KeyEvent event) throws SQLException {
                data.clear();
        // System.out.println("heyy yuuu");
        data.addAll(jcr.readAll().stream().filter((e)
                -> e.getTitre().toLowerCase().contains(tfSearchTournois.getText().toLowerCase())
//                || e.getDate_debut().toLowerCase().contains(tfSearchTournois.getText().toLowerCase())
//                || e.getDate_fin().toLowerCase().contains(tfSearchTournois.getText().toLowerCase())
                || e.getDescriptionTournois().toLowerCase().contains(tfSearchTournois.getText().toLowerCase())
                || e.getStatus().toLowerCase().contains(tfSearchTournois.getText().toLowerCase())
        ).collect(Collectors.toList()));
       // System.out.println(data);
    }
    
    
    
    
    
public void Calendar(String Titre , String Datedeb , String Datefin) throws MalformedURLException, IOException{
        // Using Calendar api
          URL url = new URL("https://www.googleapis.com/calendar/v3/calendars/c_6gv9km36vml1p5d7cftkkfd6io@group.calendar.google.com/events/");
HttpURLConnection http = (HttpURLConnection)url.openConnection();
http.setRequestMethod("POST");
http.setDoOutput(true);
http.setRequestProperty("Authorization", "Bearer ya29.A0ARrdaM8BQLOkXkvzvtX8NOBNkiIJ2r4EzMxDZlFXWHV6YZvqOGCtfRlt-ETb7_E64qMJQ1aGsWyv9jAMLnzaH5FUKDL8lqn1kwliZzUndyzLcQHq00gR9L0iaVBTjHbIl040ki7KLZrmqOogqoeWOrgQ5ZRY");
http.setRequestProperty("Content-Type", "application/json");

String data = "{\n\"summary\": \""+Titre+"\",\n  \"location\": \"Arena Application\",\n  \"start\": {\n    \"dateTime\": \""+Datedeb+"T10:00:00.000-07:00\"\n  },\n  \"end\": {\n    \"dateTime\": \""+Datefin+"T10:25:00.000-07:00\"\n    }\n\n}";
//String data = "{\n\"summary\": \"tournament\",\n  \"location\": \"Arena Application\",\n  \"start\": {\n    \"dateTime\": \""+tfDateDebut.getValue().format(DateTimeFormatter.ISO_DATE)+"T10:00:00.000-07:00\"\n  },\n  \"end\": {\n    \"dateTime\": \""+tfDateFin.getValue().format(DateTimeFormatter.ISO_DATE)+"\n    },\n\"etag\": \"\", \n      \"backgroundColor\": \"#b80672\", \n      \"timeZone\": \"UTC\", \n      \"accessRole\": \"reader\",\n\"kind\": \"calendar#calendarListEntry\", \n      \"foregroundColor\": \"#ffffff\", \n      \"defaultReminders\": [], \n      \"colorId\": \"2\"\n\n}\n";
byte[] out = data.getBytes(StandardCharsets.UTF_8);

OutputStream stream = http.getOutputStream();
stream.write(out);

System.out.println(http.getResponseCode() + " " + http.getResponseMessage() + "Tournmend Event added to Calendar Successfully");
http.disconnect();
        
        // end Calendar 
}

    @FXML
    private void DeleteEquipeTournois(ActionEvent event) {
        
         Tournois T = TournoisTv.getSelectionModel().getSelectedItem();
         Equipe E = TournoisEquipeTv.getSelectionModel().getSelectedItem();
         System.out.println(E.getIdEquipe());
                  System.out.println(T.getIdTournois());

         
        try {
            jT.supprimerJeuxTournois(E.getIdEquipe(), T.getIdTournois());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
    }
 
    
}
