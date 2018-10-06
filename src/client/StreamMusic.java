package client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.stream.FileImageInputStream;

/**
 *
 * @author victor
 */
public class StreamMusic implements Runnable {
    int port;
    DatagramSocket socket;
    
    public StreamMusic(int port) throws SocketException {
        this.port = port;
        socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        
        // recebe a música enviada pelo servidor
        while (true) {
            DatagramPacket receivedPacket = new DatagramPacket(new byte[1024], new byte[1024].length);
            
            
            try {
                
                socket.receive(receivedPacket);
                
                
            } catch (IOException ex) {
                Logger.getLogger(StreamMusic.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println(receivedPacket.getData());
        }
    }
}
