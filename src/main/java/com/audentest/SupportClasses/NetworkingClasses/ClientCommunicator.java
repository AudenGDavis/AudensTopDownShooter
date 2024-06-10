package com.audentest.SupportClasses.NetworkingClasses;

import com.audentest.SupportClasses.GameClasses.Game;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ClientCommunicator implements Runnable {
    private final String ipAddress;
    private final int portNumber;
    private final Game game;
    private final Gson gson;
    private final int localPlayer;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Socket server = null;
    private volatile boolean running = true;

    public ClientCommunicator(String ipAddress, int portNumber, Game game, int localPlayer) {
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
        this.game = game;
        this.localPlayer = localPlayer;
        this.gson = new Gson();
    }

    public void run() {
        try {
            System.out.println("Attempting to connect to server at " + ipAddress + ":" + portNumber);
            server = new Socket(ipAddress, portNumber);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new PrintWriter(server.getOutputStream(), true);
            System.out.println("Connected to server");

            while (running) {
                try {
                    String serverMessage = in.readLine();
                    if (serverMessage == null) {
                        System.out.println("Server closed the connection");
                        break;
                    }
                    Game updatedGame = gson.fromJson(serverMessage, Game.class);
                    game.updateFromServer(updatedGame, localPlayer);

                    String clientPackage = gson.toJson(game.getClientPackage(localPlayer), ClientPackage.class);
                    out.println(clientPackage);
                } catch (JsonSyntaxException e) {
                    System.err.println("Error parsing JSON: " + e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    System.err.println("IOException during communication: " + e.getMessage());
                    e.printStackTrace();
                    break; // Exit loop on IO exceptions
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to connect to server: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void stop() {
        running = false;
        closeResources();
    }

    private void closeResources() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (server != null) {
                server.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
