package com.audentest;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;

import com.audentest.SupportClasses.GameClasses.Game;
import com.audentest.SupportClasses.GameClasses.Gun;
import com.audentest.SupportClasses.GameClasses.Player;
import com.audentest.SupportClasses.PhysicsClasses.LineCollider;
import com.audentest.SupportClasses.PhysicsClasses.Vector2;
import com.audentest.SupportClasses.GameClasses.Bullet;


public class GameManager implements Runnable {

    private Game game;
    private GamePanel gamePanel;
    private int localPlayer;
    public boolean isFiring = false;
    private Thread renderingThread;
    private double deltaTime;
    private double zoomSpeed = 10;
    private boolean limitFPS = true;
    private double goalFPS = 60;
    

    public GameManager(Game Game, GamePanel GamePanel, int LocalPlayer){
        game = Game;
        gamePanel = GamePanel;
        localPlayer = LocalPlayer;
    }
    
    public void run() 
    {
        renderingThread = new Thread(() -> {
            while (true) {
                // Render game graphics
                gamePanel.repaint();

                // Sleep for a short interval
                try {
                    Thread.sleep(((long)((1.0/goalFPS)*1000000000-deltaTime)/1000000) < 0? 0 : ((long)((1.0/goalFPS)*1000000000-deltaTime)/1000000)); // Adjust as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        new Thread(renderingThread).start();
        long start = 0;
        long end = 0;

        if(limitFPS)
        {
            while (true)
            {
                start = System.nanoTime();
                update();

                end = System.nanoTime();
                deltaTime = end - start;   // Calculate the elapsed time in nanoseconds
                try {
                    Thread.sleep(((long)((1f/goalFPS)*1000000000-deltaTime)/1000000) < 0? 0 : ((long)((1f/goalFPS)*1000000000-deltaTime)/1000000));
                    deltaTime = (1f/goalFPS)*1000000000;
                } catch (InterruptedException e) {
                
                }
            }
        } 
        else
        {
            while(true)
            {
                start = System.nanoTime();
                update();

                end = System.nanoTime();
                deltaTime = end - start;   // Calculate the elapsed time in nanoseconds
            }
        }
        
    }

    public synchronized void fireBullet()
    {
        
        Gun gun = game.getPlayers().get(localPlayer).getGun();
        if(game.getPlayers().get(localPlayer).getGun().getReloadTime() >= game.getPlayers().get(localPlayer).getGun().getReloadTimeRequirment())
        {
            if(ColliderManager.isCollidingAnyWalls(
                game,
                new LineCollider(
                    (float) (game.getPlayers().get(localPlayer).getXPosition()),
                    (float) (game.getPlayers().get(localPlayer).getYPosition()),
                    (float) (game.getPlayers().get(localPlayer).getXPosition() + game.getPlayers().get(localPlayer).getGun().getBarrelLength()*Math.cos(Math.toRadians(game.getPlayers().get(localPlayer).getAngle()))),
                    (float) (game.getPlayers().get(localPlayer).getYPosition() + game.getPlayers().get(localPlayer).getGun().getBarrelLength()*Math.sin(Math.toRadians(game.getPlayers().get(localPlayer).getAngle())))

                )
            ).isEmpty()){
                game.getLocalBullets().add(new Bullet(
                    game.getPlayers().get(localPlayer), 
                    game.getPlayers().get(localPlayer).getGun().getDamage(), 
                    (float) (game.getPlayers().get(localPlayer).getXPosition() + game.getPlayers().get(localPlayer).getGun().getBarrelLength()*Math.cos(Math.toRadians(game.getPlayers().get(localPlayer).getAngle()))), 
                    (float) (game.getPlayers().get(localPlayer).getYPosition() + game.getPlayers().get(localPlayer).getGun().getBarrelLength()*Math.sin(Math.toRadians(game.getPlayers().get(localPlayer).getAngle()))),
                    (float) (gun.getBulletSpeed()*Math.cos(Math.toRadians(game.getPlayers().get(localPlayer).getAngle()+(Math.random()*gun.getAccuracy()-gun.getAccuracy()/2)))), 
                    (float) (gun.getBulletSpeed()*Math.sin(Math.toRadians(game.getPlayers().get(localPlayer).getAngle()+(Math.random()*gun.getAccuracy()-gun.getAccuracy()/2))))
                    ));
            }

                game.getPlayers().get(localPlayer).getGun().setReloadTime(0);
        }
    }

    synchronized private void update()
    {
        //shoot local players bullet if isFiring
        if(isFiring && game.getPlayers().get(localPlayer).getHealth() > 0)
        {
            
            this.fireBullet();
        }


        //update player's velocity
        int velocityIncrease = 300;
        if(InputManager.isKeyDown(KeyEvent.VK_W)&&InputManager.isKeyDown(KeyEvent.VK_S))
        {
            game.getPlayers().get(localPlayer).setYVelocity(0);
        }
        else if(InputManager.isKeyDown(KeyEvent.VK_S))
        {
            game.getPlayers().get(localPlayer).setYVelocity(velocityIncrease);
        }
        else if(InputManager.isKeyDown(KeyEvent.VK_W))
        {
            game.getPlayers().get(localPlayer).setYVelocity(-velocityIncrease);
        }
        else
        {
            game.getPlayers().get(localPlayer).setYVelocity(0); 
        }
        if(InputManager.isKeyDown(KeyEvent.VK_D)&&InputManager.isKeyDown(KeyEvent.VK_A))
        {
            game.getPlayers().get(localPlayer).setXVelocity(0);
        }
        else if(InputManager.isKeyDown(KeyEvent.VK_D))
        {
            game.getPlayers().get(localPlayer).setXVelocity(velocityIncrease);
        }
        else if(InputManager.isKeyDown(KeyEvent.VK_A))
        {
            game.getPlayers().get(localPlayer).setXVelocity(-velocityIncrease);
        }
        else
        {
            game.getPlayers().get(localPlayer).setXVelocity(0); 
        }
        if(InputManager.isKeyDown(KeyEvent.VK_UP))
        {
            gamePanel.setZoom((float)(gamePanel.getZoom() + gamePanel.getZoom()*deltaTime/1000000000*zoomSpeed));
        }
        else if(InputManager.isKeyDown(KeyEvent.VK_DOWN))
        {
            gamePanel.setZoom((float)(gamePanel.getZoom() - gamePanel.getZoom()*deltaTime/1000000000*zoomSpeed));
        }

        

        gamePanel.setZoom(MouseWheelInputManager.getTotalMouseScroll());


        //update player's positions and their respective gun reload times
        for (Map.Entry<Integer, Player> playerEntry : game.getPlayers().entrySet()) 
        {
            if(playerEntry.getValue().getHealth() > 0)
            {
                //reloading their gun
                game.getPlayers().get(playerEntry.getKey()).getGun().setReloadTime((float)(
                    game.getPlayers().get(playerEntry.getKey()).getGun().getReloadTime() >= game.getPlayers().get(playerEntry.getKey()).getGun().getReloadTimeRequirment()? 
                    game.getPlayers().get(playerEntry.getKey()).getGun().getReloadTimeRequirment() : game.getPlayers().get(playerEntry.getKey()).getGun().getReloadTime() + deltaTime/1000000000
                ));

                //adjust players position
                game.getPlayers().get(playerEntry.getKey()).setXPosition(game.getPlayers().get(playerEntry.getKey()).getXVelocity()*(deltaTime/1000000000) + game.getPlayers().get(playerEntry.getKey()).getXPosition());
                game.getPlayers().get(playerEntry.getKey()).setYPosition(game.getPlayers().get(playerEntry.getKey()).getYVelocity()*(deltaTime/1000000000) + game.getPlayers().get(playerEntry.getKey()).getYPosition());




                //adjust player in they're colliding
                Vector2 collision = ColliderManager.isCollidingAny(game, game.getPlayers().get(playerEntry.getKey()));
                while(collision != null)
                {
                    double dy = game.getPlayers().get(playerEntry.getKey()).getYPosition() - collision.getY();
                    double dx = game.getPlayers().get(playerEntry.getKey()).getXPosition() - collision.getX();

                    double s = Math.sqrt(dy*dy + dx*dx)/game.getPlayers().get(playerEntry.getKey()).getSize();

                    dy = dy/s*1.00001;
                    dx = dx/s*1.00001;

                    

                    game.getPlayers().get(playerEntry.getKey()).setXPosition(collision.getX() + dx);
                    game.getPlayers().get(playerEntry.getKey()).setYPosition(collision.getY() + dy);
                
                    collision = ColliderManager.isCollidingAny(game, game.getPlayers().get(playerEntry.getKey()));
                } 
            }
        }
        //set local player angle
        double dx = MouseInputManager.getMouseX() - gamePanel.getWidth()/2;
        double dy = MouseInputManager.getMouseY() - gamePanel.getHeight()/2;

        if(dx == 0)
        {
            if (dy > 0)
            {
                game.getPlayers().get(localPlayer).setAngle(90);
            }
            else if(dy < 0)
            {
                game.getPlayers().get(localPlayer).setAngle(-90);
            }
        }
        else if(dx > 0)
        {
            game.getPlayers().get(localPlayer).setAngle(Math.toDegrees(Math.atan(dy/dx)));
        }
        else if(dx < 0)
        {
            game.getPlayers().get(localPlayer).setAngle(Math.toDegrees(Math.atan(dy/dx))+180);
        }
        synchronized(game.getBullets())
        {
            //bullet update
            for(int b = game.getBullets().size()-1; b >= 0;b--)
            {
                Bullet bullet = game.getBullets().get(b);
                // boolean realCollision = false;
                // ArrayList<Player> playerCollisons = ColliderManager.isCollidingAnyPlayers(game,new LineCollider(bullet.getXPosition(),bullet.getYPosition(), bullet.getXPosition()+bullet.getXVelocity()*deltaTime/1000000000*1.5, bullet.getYPosition()+bullet.getYVelocity()*deltaTime/1000000000*1.5));
                // if(playerCollisons.size() != 0){
                //     for(int i = 0; i < playerCollisons.size(); i++)
                //     {   
                //         if(playerCollisons.get(i).getHealth() >= 0){
                //             playerCollisons.get(i).setHealth(playerCollisons.get(i).getHealth() - (bullet.getDamage()));
                //             realCollision = true;
                //         }
                //     }
                    
                // }

                // if(ColliderManager.isCollidingAnyWalls(game, new LineCollider(bullet.getXPosition() - bullet.getXVelocity()*deltaTime/1000000000*0.5,bullet.getYPosition() - bullet.getYVelocity()*deltaTime/1000000000*0.5, bullet.getXPosition()+bullet.getXVelocity()*deltaTime/1000000000*1.6, bullet.getYPosition()+bullet.getYVelocity()*deltaTime/1000000000*1.6)).size() == 0)
                // {
                //     bullet.setXPosition((float)(bullet.getXPosition()+bullet.getXVelocity()*deltaTime/1000000000));
                //     bullet.setYPosition((float)(bullet.getYPosition()+bullet.getYVelocity()*deltaTime/1000000000));
                // }
                // else{
                //     realCollision = true;
                // }
                // if(realCollision){
                //     game.getBullets().remove(b);
                // }
                bullet.setXPosition((float)(bullet.getXPosition()+bullet.getXVelocity()*deltaTime/1000000000));
                bullet.setYPosition((float)(bullet.getYPosition()+bullet.getYVelocity()*deltaTime/1000000000));
            }
        }
        synchronized (game.getLocalBullets())
        {
            for(int b = game.getLocalBullets().size()-1; b >= 0;b--)
            {
                Bullet bullet = game.getLocalBullets().get(b);
                // boolean realCollision = false;
                // ArrayList<Player> playerCollisons = ColliderManager.isCollidingAnyPlayers(game,new LineCollider(bullet.getXPosition(),bullet.getYPosition(), bullet.getXPosition()+bullet.getXVelocity()*deltaTime/1000000000*1.5, bullet.getYPosition()+bullet.getYVelocity()*deltaTime/1000000000*1.5));
                // if(playerCollisons.size() != 0){
                //     for(int i = 0; i < playerCollisons.size(); i++)
                //     {   
                //         if(playerCollisons.get(i).getHealth() >= 0){
                //             playerCollisons.get(i).setHealth(playerCollisons.get(i).getHealth() - (bullet.getDamage()));
                //             realCollision = true;
                //         }
                //     }
                    
                // }

                // if(ColliderManager.isCollidingAnyWalls(game, new LineCollider(bullet.getXPosition() - bullet.getXVelocity()*deltaTime/1000000000*0.5,bullet.getYPosition() - bullet.getYVelocity()*deltaTime/1000000000*0.5, bullet.getXPosition()+bullet.getXVelocity()*deltaTime/1000000000*1.6, bullet.getYPosition()+bullet.getYVelocity()*deltaTime/1000000000*1.6)).size() == 0)
                // {
                //     bullet.setXPosition((float)(bullet.getXPosition()+bullet.getXVelocity()*deltaTime/1000000000));
                //     bullet.setYPosition((float)(bullet.getYPosition()+bullet.getYVelocity()*deltaTime/1000000000));
                // }
                // else{
                //     realCollision = true;
                // }
                // if(realCollision){
                //     game.getLocalBullets().remove(b);
                // }
                bullet.setXPosition((float)(bullet.getXPosition()+bullet.getXVelocity()*deltaTime/1000000000));
                bullet.setYPosition((float)(bullet.getYPosition()+bullet.getYVelocity()*deltaTime/1000000000));
            }
            
        }
    }   
}
