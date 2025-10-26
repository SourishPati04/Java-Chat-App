package client;

import java.io.*;
import java.net.*;
import util.Encryption;

public class ClientCode{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientCode(String host, int port) throws IOException{
        socket = new Socket(host,port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendMessage(String msg){
        out.println(Encryption.encrypt(msg));
    }
    public BufferedReader getInput(){
        return in;
    }
    public void close() throws IOException {
        socket.close();
    }
}