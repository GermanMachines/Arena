import edu.arena.entities.Jeux;
import edu.arena.entities.Tournois;
import edu.arena.services.JeuxCrud;
import edu.arena.services.TournoisCrud;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

   public static void main(String[] args) throws SQLException {
            TournoisCrud su =new TournoisCrud();
             Tournois t1 = new Tournois("League of legends","2022-11-01","2022-11-06","new tournament","1v1",12,123);
             Tournois t2 = new Tournois("WAKAKA","2022-11-01","2022-11-06","new tournament","1v1",4,123);
             Tournois t3 = new Tournois("MAHSOUBA","2022-11-09","2022-11-10","new tournament","1v1",8,123);
                 
//                  su.ajouter(t1);
//                  su.ajouter(t2);
//                  su.ajouter(t3);
                 // su.delete(11);
                // su.delete("3");*/
//            su.Update(12,"Fifa","2022-12-01","2022-12-11","Each tournament match will be “best-of-one,” except for the final, which will be as described above.\n" +
//"Before each match, the team captains and the referee will hold a coin toss.","Equipe",4,137);
//            
//          System.out.println(su.readAll());
//           


            su.triTournois(1);  
            
            su.triTournois(2); 


//
//        
//            JeuxCrud sj =new JeuxCrud();
//             Jeux j1 = new Jeux("League of legends","image1.jpg");
//             Jeux j2 = new Jeux("Fifa","image2.jpg");
//             Jeux j3 = new Jeux("WWE","image3.jpg");
             
//             sj.ajouter(j1);
//              sj.ajouter(j2);
//               sj.ajouter(j3);
              
           // sj.delete(136);
            
       //    sj.Update(137,"League of legends","Imaage5.jpg");
//            
//             
        //      System.out.println(sj.readAll());
             
        
        
        }
   
   

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}