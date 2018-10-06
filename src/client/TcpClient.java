package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import server.Server;

/**
 *
 * @author victor
 */
public class TcpClient {
    public static void main(String[] args) throws IOException {
        // Lê os argumentos para iniciar o cliente <servidor> <porta servidor> <porta udp>
        if (args.length == 3) {
            new TcpClient(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2])).execute();
        } else {
            System.out.println("Argumentos insuficientes!");
        }
    }
    
    String server;
    int serverPort;
    int UDPport;
    int numStations;
    
    public TcpClient(String server, int serverPort, int UDPport) {
        this.server = server;
        this.serverPort = serverPort;
        this.UDPport = UDPport;
    }
    
    public void execute() throws IOException {
        
        Socket client = new Socket(server, serverPort);
        System.out.println("Conexão realizada, aguardando Hand shake...");
        
        // aguardar HandShake
        handShake(client);
        
        System.out.println("Conectado ao servidor com sucesso!");

        
        // iniciar socket UDP para aguardar música
        StreamMusic stream = new StreamMusic(UDPport);
        // inicia uma nova thread para receber e reproduzir a rádio
        new Thread(stream).start();
        
        // Thread para escutar comandos
        listenCommands lc = new listenCommands(client);
        new Thread(lc).start();
        
        
    }
    
    public void handShake(Socket client) throws IOException {
        Scanner inputStream = new Scanner(client.getInputStream());
        PrintStream output = new PrintStream(client.getOutputStream());
        output.println("0");
        output.println(UDPport);
        
        int reply1;
        int reply2;
        
        reply1 = Integer.parseInt(inputStream.nextLine());
        reply2 = Integer.parseInt(inputStream.nextLine());
        
        if (reply1 != 0) {
            disconnect();
        }
        
        numStations = reply2;
        
        System.out.println("Server: Bem vindo!");
        System.out.println("Server: Número de estações: "+reply2);
        
    }
    
    public void disconnect() {
        // desconectar e finalizar processos...
    }
}
