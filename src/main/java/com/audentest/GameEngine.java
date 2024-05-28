package com.audentest;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.audentest.SupportClasses.GameClasses.Game;
import com.audentest.SupportClasses.GameClasses.Gun;
import com.audentest.SupportClasses.GameClasses.Bullet;
import com.audentest.SupportClasses.GameClasses.Player;
import com.audentest.SupportClasses.GameClasses.Wall;
import com.audentest.SupportClasses.PhysicsClasses.Vector2;

public class GameEngine extends JFrame
{

    Game game;
    GameManager gameManager;
    GamePanel gamePanel;
    ServerCommunicator serverCommunicator;

    public GameEngine (String name)
    {
        this.setTitle("Client");

        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        serverCommunicator = new ServerCommunicator(game,42069);

        gamePanel = new GamePanel(game, this, serverCommunicator.getPlayerConnection().getPlayerID());
        this.add(gamePanel);

        this.setBackground(Color.white);

        gameManager = new GameManager(game, gamePanel, serverCommunicator.getPlayerConnection().getPlayerID());
        

        this.addKeyListener(new InputManager());
        this.addMouseMotionListener(new MouseInputManager());
        this.addMouseWheelListener(new MouseWheelInputManager());
        MouseClickManager mouseClickManager = new MouseClickManager();
        mouseClickManager.gameManager = gameManager;
        this.addMouseListener(mouseClickManager);

        
        new Thread(gameManager).start();
        this.repaint();



        
        
    }

    
}
