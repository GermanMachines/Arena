/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.JeuxTournois;
import edu.arena.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tarek
 */
public class JeuxTournoisCrud {
    
    private Connection con;
    private Statement ste;

    public JeuxTournoisCrud() {
      con = DataBase.getInstance().getConnection();
    }
    
    
    public void ajouter(JeuxTournois jt) {
        try {
            PreparedStatement pre = con.prepareStatement(" INSERT INTO participation (IdEquipe, IdTournois) VALUES ( ?, ?); ");
            pre.setInt(1, jt.getIdequipe());
            pre.setInt(2, jt.getIdTournois());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JeuxTournoisCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
        public boolean supprimerJeuxTournois(int IdEquipe, int IdTournois) throws SQLException {

        PreparedStatement pre = con.prepareStatement("Delete from participation where IdEquipe=? and  IdTournois=? ");
        try {

            pre.setInt(1, IdEquipe);
            pre.setInt(2, IdTournois);
            if (pre.executeUpdate() != 0) {
                System.out.println("Deleted");

                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     //   System.out.println("id not found!!!");
        return false;

    }
        
        
        
            public int getTournois(int idjeux ) {
       int idT=0;

        try {
            PreparedStatement pre = con.prepareStatement("select IdTournois from participation where IdJeux=?");
            pre.setInt(1, idjeux);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
               idT = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return idT;
    }
            
            
       public int VerifParticipation(int idEquipe , int idT) {
          int res=0;
        try {
            PreparedStatement pre = con.prepareStatement("select count(*) from participation where IdEquipe=? and IdTournois=?");
            pre.setInt(1, idEquipe);
            pre.setInt(2, idT);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
               res = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return res;
    }
       
       

        public int VerifNombreParticipation(int idT) {
          int res=0;
        try {
            PreparedStatement pre = con.prepareStatement("SELECT count( * ) as  total_record FROM participation WHERE IdTournois =?");
            pre.setInt(1, idT);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
               res = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return res;
    }
       
       
       
       
       
            
            
            
        public List<Integer> rechercheJeuxTournois(int idTournois) {
        {
            List<Integer> arr = new ArrayList<>();

            try {
                PreparedStatement pre = con.prepareStatement("SELECT  t.IdTournois,e.IdEquipe  FROM  equipe e ,tournois t, participation p WHERE p.IdEquipe = e.IdEquipe AND p.IdTournois = t.IdTournois and p.IdTournois=? ");
                pre.setInt(1, idTournois);
                ResultSet rs = pre.executeQuery();

                while (rs.next()) {
                    

                    int idT = rs.getInt(1);
                    int idcla=rs.getInt("IdEquipe");
                    
                    JeuxTournois ce = new JeuxTournois(idTournois, idcla);
                    ce.setIdequipe(idcla);
                    arr.add(idcla);
                }
            } catch (SQLException ex) {
                Logger.getLogger(JeuxTournoisCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

            return arr;
        }
    }
            
            
            
            
//                   public List<Integer> rechercheJeuxTournois(int idTournois) {
//        {
//            List<Integer> arr = new ArrayList<>();
//
//            try {
//                PreparedStatement pre = con.prepareStatement("SELECT e.IdEquipe  FROM  equipe e ,tournois t, participation p WHERE p.IdEquipe = e.IdEquipe AND p.IdTournois = t.IdTournois and p.IdTournois=? ");
//                pre.setInt(1, idTournois);
//                ResultSet rs = pre.executeQuery();
//
//                while (rs.next()) {
//
//
//                    int idE = rs.getInt(1);
//                    int idt = rs.getInt("IdTournois");
//                    
//                    JeuxTournois ce = new JeuxTournois(idE);
//                    ce.setIdTournois(idt);
//                    arr.add(idt);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(JeuxTournoisCrud.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            return arr;
//        }
//    } 

    public List<Integer> rechercheJT(int idTournois) {
            List<Integer> arr = new ArrayList<>();

            try {
                PreparedStatement pre = con.prepareStatement("SELECT e.IdEquipe  FROM  equipe e ,tournois t, participation p WHERE p.IdEquipe = e.IdEquipe AND p.IdTournois = t.IdTournois and p.IdTournois=? ");
                pre.setInt(1, idTournois);
                ResultSet rs = pre.executeQuery();

                while (rs.next()) {
                    
                    int idE = rs.getInt(1);
                   // int idt = rs.getInt("IdTournois");
                    
                   // JeuxTournois ce = new JeuxTournois(idE);
                    //ce.setIdTournois(idt);
                   // System.out.println(idE);
                    arr.add(idE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(JeuxTournoisCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

            return arr;
        }


    
            
    
    
    
}
