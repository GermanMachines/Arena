/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author DeathKnight
 */

public class Email {

    public static void sendEmail(String addr, String subject,String message) throws Exception {
        //abdousfayhitest@gmail.com
        String from = "tarekayadi0@gmail.com";
        String pass ="123456789*-";
        String[] to = {addr};
        String host ="smtp.gmail.com" ;
                    String messageText = message;

            boolean sessionDebug = false;

        Properties props = System.getProperties();
         props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

         java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
           InternetAddress[] address = {new InternetAddress(addr)};
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, from, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        //
        /*
            String content = new String(Files.readAllBytes(Paths.get("/fixit/Views/BlogPost.html")), "UTF-8");
            Path pathToFile = Paths.get("/fixit/Views/BlogPost.html");
             System.out.println("Path is : "+ pathToFile);
            message = content;
         */

//        String messageBody = "<!DOCTYPE html>\n"
//                + "<html>\n"
//                + "<head>\n"
//                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
//                + "<style>\n"
//                + "* {\n"
//                + "  box-sizing: border-box;\n"
//                + "}\n"
//                + "\n"
//                + "/* Add a gray background color with some padding */\n"
//                + "body {\n"
//                + "  font-family: Arial;\n"
//                + "  padding: 20px;\n"
//                + "  background: #f1f1f1;\n"
//                + "}\n"
//                + "\n"
//                + "/* Header/Blog Title */\n"
//                + ".header {\n"
//                + "  padding: 30px;\n"
//                + "  font-size: 40px;\n"
//                + "  text-align: center;\n"
//                + "  background: white;\n"
//                + "}\n"
//                + "\n"
//                + "/* Create two unequal columns that floats next to each other */\n"
//                + "/* Left column */\n"
//                + ".leftcolumn {   \n"
//                + "  float: left;\n"
//                + "  width: 75%;\n"
//                + "}\n"
//                + "\n"
//                + "/* Right column */\n"
//                + ".rightcolumn {\n"
//                + "  float: left;\n"
//                + "  width: 25%;\n"
//                + "  padding-left: 20px;\n"
//                + "}\n"
//                + "\n"
//                + "/* Fake image */\n"
//                + ".fakeimg {\n"
//                + "  background-color: #aaa;\n"
//                + "  width: 100%;\n"
//                + "  padding: 20px;\n"
//                + "}\n"
//                + "\n"
//                + "/* Add a card effect for articles */\n"
//                + ".card {\n"
//                + "   background-color: white;\n"
//                + "   padding: 20px;\n"
//                + "   margin-top: 20px;\n"
//                + "}\n"
//                + "\n"
//                + "/* Clear floats after the columns */\n"
//                + ".row:after {\n"
//                + "  content: \"\";\n"
//                + "  display: table;\n"
//                + "  clear: both;\n"
//                + "}\n"
//                + "\n"
//                + "/* Footer */\n"
//                + ".footer {\n"
//                + "  padding: 20px;\n"
//                + "  text-align: center;\n"
//                + "  background: #ddd;\n"
//                + "  margin-top: 20px;\n"
//                + "}\n"
//                + "\n"
//                + "/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */\n"
//                + "@media screen and (max-width: 800px) {\n"
//                + "  .leftcolumn, .rightcolumn {   \n"
//                + "    width: 100%;\n"
//                + "    padding: 0;\n"
//                + "  }\n"
//                + "}\n"
//                + "</style>\n"
//                + "</head>\n"
//                + "<body>\n"
//                + "\n"
//                + "<div class=\"header\">\n"
//                + "  <h2>GEstudent+</h2>\n"
//                + "</div>\n"
//                + "\n"
//                + "<div class=\"row\">\n"
//                + "  <div class=\"\">\n"
//                + "    <div class=\"card\">\n"
//                + "      \n"
//                + "    </div>\n"
//                + "    \n"
//                + "  </div>\n"
//                + "  \n"
//                + "</div>\n"
//                + "\n"
//                + "\n"
//                + "\n"
//                + "</body>\n"
//                + "</html>";
        //
     

    }
}
