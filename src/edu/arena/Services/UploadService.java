/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.Services;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author LENOVO
 */
public class UploadService {
     public String upload(String Path) {
        String fileNameInServer = "";
        try {
            FileUploader fu = new FileUploader("ressources");

            //Upload
             fileNameInServer = fu.upload(Path);
             System.out.println(fileNameInServer);

            return fileNameInServer;
        } catch (ProtocolException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
        return fileNameInServer;

    }

    public boolean delete(String fileNameInServer) {
        try {
            FileUploader fu = new FileUploader("ressources");

            if (fu.delete(fileNameInServer)) {

                System.out.println("File " + fileNameInServer + " deleted.");
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(UploadService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

  

    
}
