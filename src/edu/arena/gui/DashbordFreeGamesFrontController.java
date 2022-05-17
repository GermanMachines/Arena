/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Match;
import edu.arena.services.MatchCrud;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class DashbordFreeGamesFrontController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
          ArrayList<String> Listfreegames = new ArrayList<String>(); // Create an ArrayList object


    
//     public ObservableList<Match> Matchdata = FXCollections.observableArrayList();
//                MatchCrud mtc = new MatchCrud();
//                  private List<Match> Match= new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //  Matchdata.addAll(mtc.readAll());

        //Match.addAll(getData());
        // Jeux.addAll(jcr.readAll());
   
        //System.out.println(data.size());
          try {
            loadlistdata();
        } catch (IOException ex) {
        }
         
        int column=0;
        int row=1;
           try {
        for(int i=0 ; i<10;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/edu/arena/gui/ItemFreeGame.fxml"));
         
                AnchorPane anchorPane = fxmlLoader.load();
          
            System.out.println(Listfreegames.get(i));
            
            ItemFreeGameController itemController = fxmlLoader.getController();
            itemController.setData(1,Listfreegames.get(i+1),Listfreegames.get(i+2));
            if(column == 2){
                column=0;
                row++;
            }
            grid.add(anchorPane, column++, row); //Child , column , row
            
            //Set Item Grid Width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);
            
            
               //Set Item Grid Height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);
            
            
            
            
            GridPane.setMargin(anchorPane,new Insets(60));
              }
           }
           catch (IOException ex) {
                ex.printStackTrace();
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
               Listfreegames.removeAll(Listfreegames);
       for(int i=0 ; i < Games.length();i++){
           JSONObject game = Games.getJSONObject(i);
           int id = game.getInt("id");
           String title = game.getString("title");
           String thumbnail = game.getString("thumbnail");
           String Genre = game.getString("genre");
           String platform = game.getString("platform");
           String release_date = game.getString("release_date");
           
         //  System.out.println(id+ "  " + title+ "  " + thumbnail);
           
    
            Listfreegames.add(String.valueOf(id));
            Listfreegames.add(title);
            Listfreegames.add(Genre);
            
       }
            

       
       
       return null;
   }

    
    
}
