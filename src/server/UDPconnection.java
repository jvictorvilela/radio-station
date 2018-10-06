package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author victor
 */
public class UDPconnection implements Runnable {
    int port;
    String server;
    int stationNumber;
    
    public UDPconnection(String server, int port, int stationNumber) {
        this.port = port;
        this.server = server;
        this.stationNumber = stationNumber;
    }

    @Override
    public void run() {  
        // faz o envio da musica para o cliente
        try {
            
            
        
        // cria um socket UDP
        DatagramSocket socket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(server);
        
        
        
        
        
        
        File file = new File("/home/victor/Área de Trabalho/music.mp3");
        FileInputStream music = new FileInputStream(file);
        
        byte[] array = new byte[1024];
        
        music.read(array);
        
        // envia música
        int aux = 0;
        while (true) {
            DatagramPacket packet = new DatagramPacket(array, array.length, IPAddress, port);
            socket.send(packet);
            aux++;
        }

        
        
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPconnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(UDPconnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    

        
    }
}
