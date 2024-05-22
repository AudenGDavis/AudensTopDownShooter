package com.audentest;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


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
    private int miliSecondRate = 1;//milliseconds per update
    public boolean isFiring = false;
    private Thread renderingThread;

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
                    Thread.sleep(10); // Adjust as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        new Thread(renderingThread).start();
        while(true){
        
            try {
                Thread.sleep(miliSecondRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            this.update();
        
            

        }
        
    }

    public synchronized void fireBullet(){
        Gun gun = game.getPlayers().get(localPlayer).getGun();
        if(game.getPlayers().get(localPlayer).getGun().getReloadTime() >= game.getPlayers().get(localPlayer).getGun().getReloadTimeRequirment()){

            if(ColliderManager.isCollidingAnyWalls(
                game,
                new LineCollider(
                    (float) (game.getPlayers().get(localPlayer).getXPosition()),
                    (float) (game.getPlayers().get(localPlayer).getYPosition()),
                    (float) (game.getPlayers().get(localPlayer).getXPosition() + game.getPlayers().get(localPlayer).getGun().getBarrelLength()*Math.cos(Math.toRadians(game.getPlayers().get(localPlayer).getAngle()))),
                    (float) (game.getPlayers().get(localPlayer).getYPosition() + game.getPlayers().get(localPlayer).getGun().getBarrelLength()*Math.sin(Math.toRadians(game.getPlayers().get(localPlayer).getAngle())))

                )
            ).isEmpty()){
                game.getBullets().add(new Bullet(
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

    synchronized private void update(){
        //shoot local players bullet if isFiring
        if(isFiring)
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
            gamePanel.setZoom(gamePanel.getZoom() + gamePanel.getZoom()*miliSecondRate/1000);
        }
        else if(InputManager.isKeyDown(KeyEvent.VK_DOWN))
        {
            gamePanel.setZoom(gamePanel.getZoom() - gamePanel.getZoom()*miliSecondRate/1000);
        }

        

        gamePanel.setZoom(MouseWheelInputManager.getTotalMouseScroll());

        
        //update player's positions and their respective gun reload times
        for(int p = 0; p < game.getPlayers().size();p++)
        {
            //reloading their gun
            game.getPlayers().get(p).getGun().setReloadTime(
                game.getPlayers().get(p).getGun().getReloadTime() >= game.getPlayers().get(p).getGun().getReloadTimeRequirment()? 
                game.getPlayers().get(p).getGun().getReloadTimeRequirment() : game.getPlayers().get(p).getGun().getReloadTime() + miliSecondRate
            );


            //adjust players position
            game.getPlayers().get(p).setXPosition(game.getPlayers().get(p).getXVelocity()*(((float)miliSecondRate)/1000) + game.getPlayers().get(p).getXPosition());
            game.getPlayers().get(p).setYPosition(game.getPlayers().get(p).getYVelocity()*(((float)miliSecondRate)/1000) + game.getPlayers().get(p).getYPosition());





            //adjust player in they're colliding
            Vector2 collision = ColliderManager.isCollidingAny(game, game.getPlayers().get(p));
            while(collision != null)
            {
                double dy = game.getPlayers().get(p).getYPosition() - collision.getY();
                double dx = game.getPlayers().get(p).getXPosition() - collision.getX();

                double s = Math.sqrt(dy*dy + dx*dx)/game.getPlayers().get(p).getSize();

                dy = dy/s*1.00001;
                dx = dx/s*1.00001;

                

                game.getPlayers().get(p).setXPosition(collision.getX() + dx);
                game.getPlayers().get(p).setYPosition(collision.getY() + dy);
                collision = ColliderManager.isCollidingAny(game, game.getPlayers().get(p));
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
        




        //bullet update
        for(int b = game.getBullets().size()-1; b >= 0;b--)
        {
            Bullet bullet = game.getBullets().get(b);
            // ArrayList<Vector2> wallCollisions = ColliderManager.isCollidingAnyWalls(game, new LineCollider(bullet.getXPosition(), bullet.getYPosition(), bullet.getXPosition()+bullet.getXVelocity()*miliSecondRate/1000, bullet.getYPosition()+bullet.getYVelocity()*miliSecondRate/1000));

            // LineCollider lineCollider = new LineCollider(bullet.getXPosition(), bullet.getYPosition(), bullet.getXPosition() + bullet.getXVelocity()/100, bullet.getYPosition() + bullet.getYVelocity()/100);

            boolean realCollision = false;
            ArrayList<Player> playerCollisons = ColliderManager.isCollidingAnyPlayers(game,new LineCollider(bullet.getXPosition(),bullet.getYPosition(), bullet.getXPosition()+bullet.getXVelocity()*miliSecondRate/1000*1.5, bullet.getYPosition()+bullet.getYVelocity()*miliSecondRate/1000*1.5));
            if(playerCollisons.size() != 0){
                for(int i = 0; i < playerCollisons.size(); i++)
                {   
                    if(playerCollisons.get(i).getHealth() >= 0){
                        playerCollisons.get(i).setHealth(playerCollisons.get(i).getHealth() - (bullet.getDamage()));
                        realCollision = true;
                    }
                }
                
            }

            if(ColliderManager.isCollidingAnyWalls(game, new LineCollider(bullet.getXPosition() - bullet.getXVelocity()*miliSecondRate/1000*0.5,bullet.getYPosition() - bullet.getYVelocity()*miliSecondRate/1000*0.5, bullet.getXPosition()+bullet.getXVelocity()*miliSecondRate/1000*1.6, bullet.getYPosition()+bullet.getYVelocity()*miliSecondRate/1000*1.6)).size() == 0)
            {
                bullet.setXPosition(bullet.getXPosition()+bullet.getXVelocity()*miliSecondRate/1000);
                bullet.setYPosition(bullet.getYPosition()+bullet.getYVelocity()*miliSecondRate/1000);
            }
            else{
                realCollision = true;
            }
            if(realCollision){
                game.getBullets().remove(b);
            }
        }
    }   
}
