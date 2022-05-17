/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.Services;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.arena.entities.CategoryReclamation;
import edu.arena.entities.Reclamation;
import edu.arena.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.arena.utils.DataBase;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DeathKnight
 */
public class ReclamationService implements IService<Reclamation> {
   Connection connexion;
   Statement stm;
     public ReclamationService() {
        connexion = DataBase.getInstance().getConnection();
    }
     
    @Override
    public void ajouter(Reclamation o) throws SQLException {
 
       stm = connexion.createStatement();
       String req = "INSERT INTO `reclamation`(`titre`, `message`, `etat`, `idUser`, `idCategoryReclamation`) VALUES  ( ? , ? , ? , ? , ?)";
       PreparedStatement pre = connexion.prepareStatement(req); 
       pre.setString(1,o.getTitre());
       pre.setString(2,o.getMessage());
       pre.setBoolean(3, o.getEtat());
       pre.setInt(4, o.getUserId());
       pre.setInt(5, o.getCategoryReclamationId());
       pre.executeUpdate();

    }

    @Override
    public void update(Reclamation o) throws SQLException {
         stm = connexion.createStatement();
         PreparedStatement pre = connexion.prepareStatement("UPDATE `reclamation`"
                 + "                         set `titre` = ? , "
                 + "                             `message` = ? ,"
                 + "                              `etat` = ? ,"
                 + "                               `idCategoryReclamation` = ?  ,"
                 + "                                `date` = ? where id = ? ");
         System.out.println(o.getDate());
         pre.setString(1, o.getTitre());
         pre.setString(2, o.getMessage());
         pre.setBoolean(3, o.getEtat());
         pre.setInt(4, o.getCategoryReclamationId());
         pre.setDate(5, o.getDatee());
         pre.setInt(6, o.getId());
         pre.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
       PreparedStatement pre = connexion.prepareStatement("Delete from reclamation where id=? ");
       pre.setInt(1, id);
       pre.executeUpdate();  
    
    }

    @Override
    public ObservableList<Reclamation> afficher() throws SQLException {
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery("select * from reclamation");
        while (rs.next()) {
            int id = rs.getInt("id");
            String titre = rs.getString("titre");
            String message = rs.getString("message");
            int idUser = rs.getInt("idUser");
            int idCategoryReclamation  = rs.getInt("idCategoryReclamation");
            boolean etat  = rs.getBoolean("etat");
            Date date = rs.getDate("date");
       
            Reclamation rec = new Reclamation(id,titre,message,idUser,idCategoryReclamation,etat,date);
            list.add(rec);
        }
        return list;
    }
    
    
    
    
    public ObservableList<Reclamation> getAll()throws SQLException{
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        stm = connexion.createStatement();
        String query ="SELECT reclamation.id , reclamation.idUser, reclamation.idCategoryReclamation , reclamation.titre, reclamation.message ,reclamation.date ,reclamation.etat, user.nom as username , categoryreclamation.nom FROM reclamation INNER JOIN user ON reclamation.idUser = user.id INNER JOIN categoryreclamation ON reclamation.idCategoryReclamation = categoryreclamation.id";
        ResultSet rs = stm.executeQuery(query);
           while (rs.next()) {
            int idRec = rs.getInt("id");
            int idUser = rs.getInt("idUser");
            int idCategoryReclamation  = rs.getInt("idCategoryReclamation");
            
            String titre = rs.getString("titre");
            String message = rs.getString("message");
            Date date = rs.getDate("date");
            
            boolean etat  = rs.getBoolean("etat");
            String username = rs.getString("username");
            String nomCategory = rs.getString("nom");
            
       
            Reclamation rec = new Reclamation();
            rec.setId(idRec);
            rec.setUserId(idUser);
            rec.setCategoryReclamationId(idCategoryReclamation);
            rec.setTitre(titre);
            rec.setMessage(message);
            rec.setDate(date);
            rec.setEtat(etat);
            rec.setUser(new User(idUser,username));
            rec.setCategoryReclamation(new CategoryReclamation(idCategoryReclamation,nomCategory));
            rec.setNomCategory(nomCategory);
            rec.setNomUser(username);
            list.add(rec);
        }
        return list;
    }
    
    
        public List<Reclamation> tri(boolean isAsc) throws SQLException {
        List<Reclamation> list = new ArrayList<>();
        stm = connexion.createStatement();  

         String ordre = isAsc ? "" : 
                 "desc";
       
        ResultSet rs = stm.executeQuery("select * from reclamation order by `date`" + ordre );
       while (rs.next()) {
            int id = rs.getInt("id");
            String titre = rs.getString("titre");
            String message = rs.getString("message");
            int idUser = rs.getInt("idUser");
            int idCategoryReclamation  = rs.getInt("idCategoryReclamation");
            boolean etat  = rs.getBoolean("etat");
            Date date = rs.getDate("date");
       
            Reclamation rec = new Reclamation(id,titre,message,idUser,idCategoryReclamation,etat,date);
            list.add(rec);
        }
        return list;
        
  }
        
         public List<Reclamation> recherche(String title) throws SQLException {
        List<Reclamation> list = new ArrayList<>();
        stm = connexion.createStatement();  


       
        ResultSet rs = stm.executeQuery("select * from reclamation where titre like '%"+title+"%'");
       while (rs.next()) {
            int id = rs.getInt("id");
            String titre = rs.getString("titre");
            String message = rs.getString("message");
            int idUser = rs.getInt("idUser");
            int idCategoryReclamation  = rs.getInt("idCategoryReclamation");
            boolean etat  = rs.getBoolean("etat");
            Date date = rs.getDate("date");
       
            Reclamation rec = new Reclamation(id,titre,message,idUser,idCategoryReclamation,etat,date);
            list.add(rec);
        }
        return list;
        
  }
         
         public HashMap<String,Integer>  stat() throws SQLException{
              HashMap<String,Integer> stat = new HashMap<>();
               stm = connexion.createStatement();  
               ResultSet rs = stm.executeQuery("select COUNT(*) as nbTotal from reclamation");
            while (rs.next()) {
            int nbTotal = rs.getInt("nbTotal");
            stat.put("nbTotal",nbTotal);
        }
     
            rs = stm.executeQuery("select COUNT(*) as nbTrue from reclamation where etat = 1");
               while (rs.next()) {
            int nbTrue = rs.getInt("nbTrue");
            stat.put("nbTrue",nbTrue);
        }
               
            rs = stm.executeQuery("select COUNT(*) as nbFalse from reclamation where etat = 0");
                while (rs.next()) {
            int nbFalse = rs.getInt("nbFalse");
            stat.put("nbFalse",nbFalse);
        }
        return stat;
               
     }
         
        public ObservableList<Reclamation> getOne(int id)throws SQLException{
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery("SELECT reclamation.id, reclamation.idUser, reclamation.titre, reclamation.message , reclamation.etat , reclamation.date , categoryreclamation.nom , reclamation.idCategoryReclamation FROM reclamation INNER JOIN categoryreclamation ON categoryreclamation.id = reclamation.idCategoryReclamation where reclamation.idUser = "+id);
        while (rs.next()) {
            int idRec = rs.getInt("id");    
            int idUser = rs.getInt("idUser");
            int idCategoryReclamation  = rs.getInt("idCategoryReclamation");
            
            String titre = rs.getString("titre");
            String message = rs.getString("message");
            Date date = rs.getDate("date");
            
            boolean etat  = rs.getBoolean("etat");
           
            String nomCategory = rs.getString("nom");
            
       
            Reclamation rec = new Reclamation();
            rec.setId(idRec);
            rec.setUserId(idUser);
            rec.setCategoryReclamationId(idCategoryReclamation);
            rec.setTitre(titre);
            rec.setMessage(message);
            rec.setDate(date);
            rec.setEtat(etat);
       
            rec.setCategoryReclamation(new CategoryReclamation(idCategoryReclamation,nomCategory));
            rec.setNomCategory(nomCategory);
        
            list.add(rec);
        }
        return list;
    }
        public void export() throws FileNotFoundException, SQLException, DocumentException{
            stm = connexion.createStatement();
            String query = "SELECT reclamation.titre, reclamation.message ,reclamation.date ,reclamation.etat, user.nom as username , categoryreclamation.nom FROM reclamation INNER JOIN user ON reclamation.idUser = user.id INNER JOIN categoryreclamation ON reclamation.idCategoryReclamation = categoryreclamation.id";
            ResultSet rs = stm.executeQuery(query);
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C:/Users/LENOVO/Documents/github/Arena/src/edu/arena/utils/Reclamation_data.pdf"));
            my_pdf_report.open();
            PdfPTable my_report_table = new PdfPTable(5);
            PdfPCell table_cell;
                     
            table_cell = new PdfPCell(new Phrase("username"));
            my_report_table.addCell(table_cell);
            
            table_cell = new PdfPCell(new Phrase("category"));
            my_report_table.addCell(table_cell);
            
            table_cell = new PdfPCell(new Phrase("date"));
            my_report_table.addCell(table_cell);
            
            table_cell = new PdfPCell(new Phrase("etat"));
            my_report_table.addCell(table_cell);
            
            table_cell = new PdfPCell(new Phrase("message"));
            my_report_table.addCell(table_cell);
         
           while (rs.next()) {
                String nom = rs.getString("username");
                table_cell = new PdfPCell(new Phrase(nom));
                
                my_report_table.addCell(table_cell);
                String nomCategory = rs.getString("nom");
                table_cell = new PdfPCell(new Phrase(nomCategory));
                my_report_table.addCell(table_cell);
                Date date = rs.getDate("date");
                table_cell = new PdfPCell(new Phrase(date.toString()));
                my_report_table.addCell(table_cell);
                
                Boolean etat = rs.getBoolean("etat");
                table_cell = new PdfPCell(new Phrase(etat.toString()));
                my_report_table.addCell(table_cell);
                
                String message = rs.getString("message");
                table_cell = new PdfPCell(new Phrase(message));
                my_report_table.addCell(table_cell);
            }
            my_pdf_report.add(my_report_table);
            my_pdf_report.close();
            rs.close();
            stm.close();
       
        }
         
     

}
    

