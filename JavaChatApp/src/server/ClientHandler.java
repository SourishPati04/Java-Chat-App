package server;

import java.io.*;
import java.net.*;
import java.util.*;
import util.Encryption;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String nickname;
    private Set<ClientHandler> clients;

    public ClientHandler(Socket socket, Set<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Enter your nickname: ");
            nickname = in.readLine();
            broadcast("** " + nickname + " joined the chat.");

            String msg;
            while ((msg = in.readLine()) != null) {
                msg = Encryption.decrypt(msg);

                if (msg.equalsIgnoreCase("exit")) {
                    break;
                } else if (msg.startsWith("/pm")) {
                    String[] split = msg.split(" ", 3);
                    if (split.length >= 3) {
                        privateMessage(split[1], "(PM from " + nickname + "): " + split[2]);
                    }
                } else {
                    broadcast("💬 " + nickname + ": " + msg);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
            clients.remove(this);
            broadcast("🔴 " + nickname + " left the chat.");
        }
    }

    private void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.out.println(Encryption.encrypt(message));
        }
    }

    private void privateMessage(String toUser, String message) {
        for (ClientHandler client : clients) {
            if (client.nickname.equalsIgnoreCase(toUser)) {
                client.out.println(Encryption.encrypt(message));
                return;
            }
        }
        out.println(Encryption.encrypt("User not found."));
    }
}
