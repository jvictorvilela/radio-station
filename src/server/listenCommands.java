package server;

import java.util.Scanner;

/**
 *
 * @author victor
 */
public class listenCommands implements Runnable {

    // Thread que aguarda receber comandos e os executa
    public void run() {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            // falta terminar...
            System.out.println(input.nextLine());
        }
    }
    
}
