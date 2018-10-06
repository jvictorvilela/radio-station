package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victor
 */
public class listenCommands implements Runnable {
    
    Socket client;
    
    public listenCommands(Socket client) {
        this.client = client;
    }

    // Thread que aguarda receber comandos e os executa
    public void run() {
        try {
            
            PrintStream output = new PrintStream(client.getOutputStream());
            Scanner input = new Scanner(System.in);
            while (input.hasNextLine()) {
                String buff = input.nextLine();
                output.println("1");
                output.println(buff);
            }
            
            
            
           
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(listenCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
