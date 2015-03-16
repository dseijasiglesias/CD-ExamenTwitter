/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd.examentwitter;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import twitter4j.DirectMessage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @version 1.0 Alfa
 * @author Seijas
 */
public class Metodos {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    Twitter twitter;
    
    /**
     * Constructor con las accesKey
     * Sustituir AAA... BBB... CCC... DDD... por las respectivas keys consegidas
     */
    public Metodos(){
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("AAAAAAAAAAAAAAAAAAAAAA")
                .setOAuthConsumerSecret("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB")
                .setOAuthAccessToken("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC")
                .setOAuthAccessTokenSecret("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        
        twitter = new TwitterFactory(cb.build()).getInstance();
    }
    
    /**
    public Metodos(){
        Authenticator.setDefault(new Authenticator() {
            
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication ("usuario", "password".toCharArray());
            }
        });
    }
    */
    
    /**
     * Metodo para visualizar todo el timeLine del usuario logueado
     */
    public void timeLine(){
        
        try {
            List <Status> statuses = twitter.getHomeTimeline();
            
            System.out.println("Mostrando timeline");
            for (Status status : statuses) {
                System.out.println(status.getUser().getName() + ": " + status.getText());
            }
        } catch (TwitterException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo para buscar una determinada secuencia caracteres por todo Twitter
     */
    public void search(){
        
        try {
            String search = JOptionPane.showInputDialog("Qué secuencia de caracteres desea buscar?");
            QueryResult result = twitter.search(new Query(search));
            
            for (Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ": " + status.getText());
            }
        } catch (TwitterException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Metodo para publicar un tuit publico en el timeline del usuario logueado
     */
    public void tweet(){
        
        try {
            Status status = twitter.updateStatus(Metodos.tuit());
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        } catch (TwitterException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Metodos para escribir mensajes para Twitter, siempre con el limite de 160 caracteres
     * @return devuelve el String con el tuit, siempre con 160 caracteres o menos
     */
    public static String tuit(){
        String tuit = null;
        
        do{
            if(tuit != null){
                JOptionPane.showMessageDialog(null, "Recuerde que la longitud maxima del tuit no puede exceder los 160caracteres,\nSu tuit tiene una longitud de: " + tuit.length());
            }
            tuit = JOptionPane.showInputDialog("Escriba su tuit\nMaximo 160 caracteres");
        }while(tuit.length() >= 160);
        
        return tuit;
    }
    
    /**
     * Metodos para mandar un tuit privado a otro usuario de la red
     */
    public void mp(){
        
        try {
            String name = Metodos.recipientId();
            String mp = Metodos.tuit();
            
            DirectMessage message = twitter.sendDirectMessage(name, mp);
            
            System.out.println("Sent: " + message.getText() + " to @" + message.getRecipientScreenName());
            
        } catch (TwitterException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Metodo para pedir por JOptionPane la id de un usuario
     * @return retorna un String con un nick que el usuario desea buscar
     */
    public static String recipientId(){
        return JOptionPane.showInputDialog("A quien quiere mandar el mensaje");
    }
}
