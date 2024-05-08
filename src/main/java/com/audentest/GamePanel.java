package com.audentest;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.audentest.SupportClasses.GameClasses.Game;
import com.audentest.SupportClasses.GameClasses.Bullet;
import com.audentest.SupportClasses.GameClasses.Player;
import com.audentest.SupportClasses.GameClasses.Wall;

public class GamePanel extends JPanel
{
    Game game;
    private double xCamera = 0;
    private double yCamera = 0;
    private float zoom = 1f;
    JFrame parentFrame;
    private int localPlayer = 0;
    public static final float MIN_ZOOM = 0.05f;
    
    public GamePanel(Game Game, JFrame parentFrame){
        this.game = Game;
        this.parentFrame = parentFrame;
        this.setBackground(Color.GRAY);
    }

    public float getZoom()
    {
        return zoom;
    }

    public void setZoom(float zoom)
    {
        this.zoom = zoom < MIN_ZOOM? MIN_ZOOM : zoom;
    }

    

    public void paint(Graphics G) { 
        this.setBounds(0,0,parentFrame.getWidth(),parentFrame.getHeight());
        xCamera = game.getPlayers().get(localPlayer).getXPosition();
        yCamera = game.getPlayers().get(localPlayer).getYPosition();
        

        //test

        
        Graphics2D g = (Graphics2D) G.create();
        // g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//anti aliasing


        //draw player
        g.setStroke(new BasicStroke(zoom * 5));
        
        for (int i = game.getPlayers().size() -1 ; i >= 0; i--){
            Player player = game.getPlayers().get(i);
            if(player.getHealth() > 0){
                g.setStroke(new BasicStroke(zoom * 3));
                g.setColor(player.getTeam() == 1? new Color(30, 0, 222) : new Color(235, 16, 0));
                g.drawOval((int) worldToFrameX((player.getXPosition()-player.getSize())),(int) worldToFrameY((player.getYPosition()-player.getSize())), (int)(2*player.getSize()*zoom), (int)(2*player.getSize()*zoom));

                g.setColor(player.getGun().getColor());
                g.setStroke(new BasicStroke(zoom * 5));
                g.drawLine(
                    (int)worldToFrameX(player.getXPosition()), 
                    (int)worldToFrameY(player.getYPosition()), 
                    (int)worldToFrameX(player.getXPosition() + player.getGun().getBarrelLength()*Math.cos(Math.toRadians(player.getAngle()))), 
                    (int)worldToFrameY(player.getYPosition() + player.getGun().getBarrelLength()*Math.sin(Math.toRadians(player.getAngle()))));
            }
            else
            {
                g.setStroke(new BasicStroke(zoom * 3));
                g.setColor(Color.DARK_GRAY);
                g.drawOval((int) worldToFrameX((player.getXPosition()-player.getSize())),(int) worldToFrameY((player.getYPosition()-player.getSize())), (int)(2*player.getSize()*zoom), (int)(2*player.getSize()*zoom));
            }
        }


        //draw shadows
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < game.getWalls().size(); i++){
            Wall wall = game.getWalls().get(i);
            // if(isOnScreenX(wall.getStart().getX()) && isOnScreenX(wall.getEnd().getX()) && isOnScreenY(wall.getStart().getY()) && isOnScreenY(wall.getEnd().getY())){
            //     drawShadow(g, wall, parentFrame.getWidth(), parentFrame.getHeight());
            // }

            drawShadow(g, wall, parentFrame.getWidth(), parentFrame.getHeight());


            
        }


        g.setStroke(new BasicStroke(zoom * 5));
        for (int i = 0; i < game.getWalls().size(); i++){
            Wall wall = game.getWalls().get(i);
            g.setColor(Color.BLACK);
            Line2D line = new Line2D.Double(worldToFrameX(wall.getStart().getX()), worldToFrameY(wall.getStart().getY()), worldToFrameX(wall.getEnd().getX()), worldToFrameY(wall.getEnd().getY()));
            
            g.draw(line);


            
        }

        
        

        //draw bullets
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(zoom * 3));
        for (int b = 0; b < game.getBullets().size(); b++)
        {
            Bullet bullet = game.getBullets().get(b);
            g.setStroke(new BasicStroke(zoom * 5));
            g.drawLine((int)worldToFrameX(bullet.getXPosition()), (int)worldToFrameY(bullet.getYPosition()), (int)worldToFrameX(bullet.getXPosition() + bullet.getXVelocity()/100), (int)worldToFrameY(bullet.getYPosition() + bullet.getYVelocity()/100));
            
            

  
        }

        



        
        g.dispose();
    }

    private double worldToFrameX(double X)
    {
        return (X - xCamera)*zoom + this.getWidth()/2;
    }
    private double worldToFrameY(double Y)
    {
        return (Y - yCamera)*zoom + this.getHeight()/2;
    }

    private boolean isOnScreenX(double X){
        return  0 < (X - xCamera)*zoom + this.getWidth()/2 &&  (X - xCamera)*zoom + this.getWidth()/2 < parentFrame.getWidth();
    }

    private boolean isOnScreenY(double Y){
        return  0 < (Y - xCamera)*zoom + this.getHeight()/2 && (Y - xCamera)*zoom + this.getHeight()/2 < parentFrame.getHeight();
    }

    public void drawShadow(Graphics2D g,Wall wall, double screenWidth, double screenHeight)
    {
        double r = Math.sqrt((screenHeight/2)*(screenHeight/2) + (screenWidth/2)*(screenWidth/2));

        int[] xPoints = new int[4];
        int[] yPoints = new int[4];


        xPoints[0] = (int) worldToFrameX(wall.getStart().getX());
        yPoints[0] = (int) worldToFrameY(wall.getStart().getY());

        xPoints[1] = (int) worldToFrameX(wall.getEnd().getX()); 
        yPoints[1] = (int) worldToFrameY(wall.getEnd().getY());

        
        xPoints[3] = (int)(screenWidth/2) + (int) ShadowClass.getShadowPointX(
            worldToFrameX(wall.getStart().getX()) - screenWidth/2, 
            worldToFrameY(wall.getStart().getY())- screenHeight/2, 
            worldToFrameX(wall.getEnd().getX()) - screenWidth/2, 
            worldToFrameY(wall.getEnd().getY())- screenHeight/2, 
            r);


            

            
        yPoints[3] = (int)(screenHeight/2) + (int) ShadowClass.getShadowPointY(
            worldToFrameX(wall.getStart().getX()) - screenWidth/2, 
            worldToFrameY(wall.getStart().getY()) - screenHeight/2, 
            worldToFrameX(wall.getEnd().getX()) - screenWidth/2, 
            worldToFrameY(wall.getEnd().getY()) - screenHeight/2, 
            r);

             
        xPoints[2] = (int)(screenWidth/2) + (int) ShadowClass.getShadowPointX(
            worldToFrameX(wall.getEnd().getX()) - screenWidth/2, 
            worldToFrameY(wall.getEnd().getY())- screenHeight/2, 
            worldToFrameX(wall.getStart().getX()) - screenWidth/2, 
            worldToFrameY(wall.getStart().getY())- screenHeight/2, 
            r);

        yPoints[2] = (int)(screenHeight/2) + (int) ShadowClass.getShadowPointY(
            worldToFrameX(wall.getEnd().getX()) - screenWidth/2, 
            worldToFrameY(wall.getEnd().getY())- screenHeight/2, 
            worldToFrameX(wall.getStart().getX()) - screenWidth/2, 
            worldToFrameY(wall.getStart().getY())- screenHeight/2, 
            r);

        
        g.fillPolygon(xPoints,yPoints,4);

    }


    
    
    
}
