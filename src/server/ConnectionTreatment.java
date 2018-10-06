package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author victor
 */

// classe que trata a conexão do cliente
public class ConnectionTreatment implements Runnable {
    
    Socket tcpSocket;
    InputStream inputStream;
    int udpPort;
    int stationNumber;
    Scanner input;
    PrintStream printStream;
    Radio radio;
    Thread UDPconnectionThread;
    
    public ConnectionTreatment(Socket tcpSocket, Radio radio) throws IOException {
        this.tcpSocket = tcpSocket;
        inputStream = tcpSocket.getInputStream();
        printStream = new PrintStream(tcpSocket.getOutputStream());
        UDPconnectionThread = null;
        this.radio = radio;
        input = new Scanner(inputStream);
    }

    @Override
    public void run() {
        while (true) {
            listen();
        }
    }
    
    // comando que lê comandos do cliente e os executa
    private void executeCommand(int type, int arg) {
        switch (type) {
            case 0: // Hello
                udpPort = arg;
                break;
            case 1: // mudar estação
                udpConnection(arg);
                break;
        }
        reply(type, arg);
    }
    
    private void udpConnection(int stationNumber) {        
        // Verifica se já existe alguma conexao udp já estabelecida
        if (UDPconnectionThread != null) {
            UDPconnectionThread.interrupt();
        }
        
        // faz a conexão udp com o cliente para enviar a música
        UDPconnection udp = new UDPconnection(tcpSocket.getInetAddress().getHostAddress(), udpPort, stationNumber);
        Thread t = new Thread(udp);
        t.start();
        UDPconnectionThread = t;
    }
    

    
    // método que aguarda receber um comando, quando recebe executa o método executeCommand() passando os parametros
    private void listen() {
        int type;
        int arg;

       type = Integer.parseInt(input.nextLine());
       arg = Integer.parseInt(input.nextLine());
       executeCommand(type, arg);
    }
    
    private void reply(int type, int arg) {
        switch (type) {
            case 0: // Welcome
                printStream.println("0");
                printStream.println(radio.getNumStations());
                break;
            case 1: // Announce
                printStream.println("1");
                // falta terminar..
                // songName
                break;
        }
    }
    
}
