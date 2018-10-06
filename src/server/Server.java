package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author victor
 */
public class Server {
    public static void main(String[] args) throws IOException {
        
        // Lê os argumentos para iniciar o servidor <porta tcp> <arquivo1> ...
        if (args.length >= 2) {
            String[] array = new String[args.length-1];
            for (int i = 1 ; i < args.length; i++) {
                array[i-1] = args[i];
            }
            new Server(Integer.parseInt(args[0]), array).execute();
        }
    }
    
    int port;
    String[] files;
    Radio radio;
    
    public Server(int port, String[] files) {
        this.port = port;
        this.files = files;
        radio = new Radio(files);
    }
    
    // Inicia o servidor
    public void execute() throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Servidor executando na porta "+port);
        
        
        
        
        // Thread para escutar comandos
        listenCommands lc = new listenCommands();
        new Thread(lc).start();
        
        // loop para aceitar conexões
        while (true) {
            // aguarda alguem se conectar
            Socket socketCliente = server.accept();
            
            // Cria um objeto para tratar a conexao do cliente
            ConnectionTreatment ct = new ConnectionTreatment(socketCliente, radio);
            
            // Cria uma thread para executar o tratamento
            new Thread(ct).start();
        }
    }
}
