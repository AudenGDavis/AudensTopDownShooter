package com.audentest;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.audentest.SupportClasses.GameClasses.Game;
import com.audentest.SupportClasses.GameClasses.Gun;
import com.audentest.SupportClasses.GameClasses.Bullet;
import com.audentest.SupportClasses.GameClasses.Player;
import com.audentest.SupportClasses.GameClasses.Wall;
import com.audentest.SupportClasses.NetworkingClasses.PlayerConnection;
import com.audentest.SupportClasses.PhysicsClasses.Vector2;

public class GameEngine extends JFrame
{

    Game game;
    GameManager gameManager;
    GamePanel gamePanel;
    PlayerConnection playerConnection;
    

    public GameEngine (String name, PlayerConnection PlayerConnection, Game Game)
    {
        game = Game;
        playerConnection = PlayerConnection;
        this.setTitle("Client");

        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        gamePanel = new GamePanel(game, this, playerConnection.getPlayerID());
        this.add(gamePanel);

        this.setBackground(Color.white);

        gameManager = new GameManager(game, gamePanel, playerConnection.getPlayerID());
        

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
