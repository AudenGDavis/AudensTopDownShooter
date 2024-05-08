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

    public GameEngine (String name){
        
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        

        game = new Game(new ArrayList<Player>(), new ArrayList<Wall>(), new ArrayList<Bullet>());
        
        game.getWalls().add(new Wall(new Vector2(0,0), new Vector2(0,1500)));
        
        // //world border
        game.getWalls().add(new Wall(new Vector2(0,0), new Vector2(0,1500)));
        game.getWalls().add(new Wall(new Vector2(0,1500), new Vector2(2300,1500)));
        game.getWalls().add(new Wall(new Vector2(2300,1500), new Vector2(2300,0)));
        game.getWalls().add(new Wall(new Vector2(2300,0), new Vector2(0,0)));

        // //spawn house
        game.getWalls().add(new Wall(new Vector2(0,600), new Vector2(200,600)));
        game.getWalls().add(new Wall(new Vector2(0,900), new Vector2(200,900)));
        game.getWalls().add(new Wall(new Vector2(200,600), new Vector2(200,700)));
        game.getWalls().add(new Wall(new Vector2(200,900), new Vector2(200,800)));
        
        // //house 1
        game.getWalls().add(new Wall(new Vector2(100,0), new Vector2(100,400)));
        game.getWalls().add(new Wall(new Vector2(100,400), new Vector2(400,400)));
        game.getWalls().add(new Wall(new Vector2(500,400), new Vector2(700,400)));
        game.getWalls().add(new Wall(new Vector2(700,400), new Vector2(700,0)));
        game.getWalls().add(new Wall(new Vector2(400,0), new Vector2(400,200)));
        game.getWalls().add(new Wall(new Vector2(400,300), new Vector2(400,400)));
        game.getWalls().add(new Wall(new Vector2(500,0), new Vector2(500,200)));
        game.getWalls().add(new Wall(new Vector2(500,300), new Vector2(500,400)));

        // //house 2
        game.getWalls().add(new Wall(new Vector2(100,1100), new Vector2(100,1500)));
        game.getWalls().add(new Wall(new Vector2(100,1100), new Vector2(400,1100)));
        game.getWalls().add(new Wall(new Vector2(500,1100), new Vector2(700,1100)));
        game.getWalls().add(new Wall(new Vector2(700,1100), new Vector2(700,1500)));
        game.getWalls().add(new Wall(new Vector2(300,1100), new Vector2(300,1300)));
        game.getWalls().add(new Wall(new Vector2(300,1400), new Vector2(300,1500)));
        
        
        // //big house 1
        game.getWalls().add(new Wall(new Vector2(900,0), new Vector2(900,700)));
        game.getWalls().add(new Wall(new Vector2(900,700), new Vector2(1300,700)));
        game.getWalls().add(new Wall(new Vector2(1400,700), new Vector2(1400,0)));
        game.getWalls().add(new Wall(new Vector2(1300,700), new Vector2(1300,600)));
        game.getWalls().add(new Wall(new Vector2(1200,500), new Vector2(1400,500)));
        game.getWalls().add(new Wall(new Vector2(1100,0), new Vector2(1100,300)));
        game.getWalls().add(new Wall(new Vector2(1100,400), new Vector2(1100,700)));
        game.getWalls().add(new Wall(new Vector2(1000,100), new Vector2(1100,100)));
        game.getWalls().add(new Wall(new Vector2(1000,200), new Vector2(1100,200)));
        game.getWalls().add(new Wall(new Vector2(1000,300), new Vector2(1100,300)));
        game.getWalls().add(new Wall(new Vector2(1000,400), new Vector2(1100,400)));
        game.getWalls().add(new Wall(new Vector2(1000,500), new Vector2(1100,500)));
        game.getWalls().add(new Wall(new Vector2(1000,600), new Vector2(1100,600)));
        game.getWalls().add(new Wall(new Vector2(1200,500), new Vector2(1200,400)));
        game.getWalls().add(new Wall(new Vector2(1200,300), new Vector2(1200,0)));
        
        // //big house 2
        game.getWalls().add(new Wall(new Vector2(900,800), new Vector2(900,1500)));
        game.getWalls().add(new Wall(new Vector2(900,800), new Vector2(1200,800)));
        game.getWalls().add(new Wall(new Vector2(1300,800), new Vector2(1400,800)));
        game.getWalls().add(new Wall(new Vector2(1300,800), new Vector2(1400,800)));
        game.getWalls().add(new Wall(new Vector2(1400,800), new Vector2(1400,1400)));
        game.getWalls().add(new Wall(new Vector2(1100,800), new Vector2(1100,1200)));
        game.getWalls().add(new Wall(new Vector2(1100,1300), new Vector2(1100,1500)));
        game.getWalls().add(new Wall(new Vector2(1200,1000), new Vector2(1300,1000)));
        game.getWalls().add(new Wall(new Vector2(1200,1000), new Vector2(1200,1100)));
        game.getWalls().add(new Wall(new Vector2(1200,1100), new Vector2(1300,1100)));
        game.getWalls().add(new Wall(new Vector2(1400,1400), new Vector2(1200,1400)));
        game.getWalls().add(new Wall(new Vector2(1200,1400), new Vector2(1200,1200)));
    
        // //court yard 
        game.getWalls().add(new Wall(new Vector2(1500,300), new Vector2(1500,100)));
        game.getWalls().add(new Wall(new Vector2(1500,100), new Vector2(1700,100)));
        game.getWalls().add(new Wall(new Vector2(1500,300), new Vector2(1700,100)));

        game.getWalls().add(new Wall(new Vector2(2000,100), new Vector2(2200,100)));
        game.getWalls().add(new Wall(new Vector2(2200,100), new Vector2(2200,300)));
        game.getWalls().add(new Wall(new Vector2(2000,100), new Vector2(2200,300)));

        game.getWalls().add(new Wall(new Vector2(1500,1200), new Vector2(1500,1400)));
        game.getWalls().add(new Wall(new Vector2(1500,1400), new Vector2(1700,1400)));
        game.getWalls().add(new Wall(new Vector2(1500,1200), new Vector2(1500,1400)));

        game.getWalls().add(new Wall(new Vector2(2000,1400), new Vector2(2200,1400)));
        game.getWalls().add(new Wall(new Vector2(2200,1400), new Vector2(2200,1200)));
        game.getWalls().add(new Wall(new Vector2(2000,1400), new Vector2(2200,1400)));

        game.getWalls().add(new Wall(new Vector2(1700,700), new Vector2(1700,600)));
        game.getWalls().add(new Wall(new Vector2(1700,600), new Vector2(1800,600)));
        game.getWalls().add(new Wall(new Vector2(1900,600), new Vector2(2000,600)));
        game.getWalls().add(new Wall(new Vector2(2000,600), new Vector2(2000,700)));
        game.getWalls().add(new Wall(new Vector2(2000,800), new Vector2(2000,900)));
        game.getWalls().add(new Wall(new Vector2(2000,900), new Vector2(1900,900)));
        game.getWalls().add(new Wall(new Vector2(1700,800), new Vector2(1700,900)));
        game.getWalls().add(new Wall(new Vector2(1700,900), new Vector2(1800,900)));


        game.getPlayers().add(new Player(Gun.ar15(),50,750, 1));

        game.getPlayers().add(new Player(Gun.ar15(),450,750, 2));
        game.getPlayers().add(new Player(Gun.glock9(),250,250, 2));
        game.getPlayers().add(new Player(Gun.uzi(),550,50, 2));
        game.getPlayers().add(new Player(Gun.ar15(),550,1350, 2));
        game.getPlayers().add(new Player(Gun.uzi(),950,50, 2));
        game.getPlayers().add(new Player(Gun.mac10(),1250,1050, 2));
        game.getPlayers().add(new Player(Gun.ar15(),450,750, 2));
        game.getPlayers().add(new Player(Gun.ak47(),1050,1250, 2));
        game.getPlayers().add(new Player(Gun.ar15(),1650,50, 2));
        game.getPlayers().add(new Player(Gun.uzi(),1750,650, 2));
        game.getPlayers().add(new Player(Gun.awp(),1950,850, 2));
        game.getPlayers().add(new Player(Gun.awp(),2250,1250, 2));
        
        gamePanel = new GamePanel(game, this);
        this.add(gamePanel);

        this.setBackground(Color.white);

        gameManager = new GameManager(game, gamePanel, 0);
        

        this.addKeyListener(new InputManager());
        this.addMouseMotionListener(new MouseInputManager());
        this.addMouseWheelListener(new MouseWheelInputManager());
        MouseClickManager mouseClickManager = new MouseClickManager();
        mouseClickManager.gameManager = gameManager;
        this.addMouseListener(mouseClickManager);

        ;
        gameManager.run();
        this.repaint();

        
        
    }

    
}
