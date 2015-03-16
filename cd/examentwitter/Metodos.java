/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd.examentwitter;

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
    
    
    public Metodos(){
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("44IX9P6DRLuSdMZGwkd21je9h")
                .setOAuthConsumerSecret("q5NndPo7M8DYdhFd9WlYuwQLxHH0YCUUQTHMFRNBaB0F8s41AL")
                .setOAuthAccessToken("154877482-XrH4VUE0GeiLWZf80f8vE8vqbWmdI0adyVD1hR5v")
                .setOAuthAccessTokenSecret("uL67zpwpMQ1MoO095e6erXS7kweGkvxjtaPbFhDMqQuSh");
        
        twitter = new TwitterFactory(cb.build()).getInstance();
    }
    
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
    
    public void tweet(){
        
        try {
            Status status = twitter.updateStatus(Metodos.tuit());
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        } catch (TwitterException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
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
    
    
    public static String recipientId(){
        return JOptionPane.showInputDialog("A quien quiere mandar el mensaje");
    }
}
