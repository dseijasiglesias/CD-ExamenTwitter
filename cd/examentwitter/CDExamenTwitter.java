package cd.examentwitter;

import javax.swing.JOptionPane;

/**
 * @version 1.0 Alfa
 * @author Seijas
 */
public class CDExamenTwitter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Metodos twitter = new Metodos();
        
        boolean modifier = false;
        
        do{
            int option = JOptionPane.showOptionDialog(null, "Que desea hacer", "Seijas's Twitter", JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, new Object[] {"ver timeline", "Buscar", "Publicar Tuit", "Mandar privado", "Sair"}, null);
            
                    switch(option){
                        
                        case 0:
                            //Ver timeline
                            twitter.timeLine();
                            break;
                        
                        case 1:
                            //buscar un tuit
                            twitter.search();
                            break;
                        
                        case 2:
                            //Publicar tuit
                            twitter.tweet();
                            break;
                        
                        case 3:
                            //Mandar mp
                            twitter.mp();
                            break;
                        
                        case 4:
                            //Salir
                            modifier = true;
                            break;
                        
                        default:
                            modifier = true;
                            break;
                    }
        }while(modifier == false);
    }
    
     
    
    
}
