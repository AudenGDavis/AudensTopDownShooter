package com.audentest;

import java.util.ArrayList;
import java.util.Map;

import com.audentest.SupportClasses.GameClasses.Game;
import com.audentest.SupportClasses.GameClasses.Player;
import com.audentest.SupportClasses.PhysicsClasses.CircleCollider;
import com.audentest.SupportClasses.PhysicsClasses.LineCollider;
import com.audentest.SupportClasses.PhysicsClasses.Vector2;

public abstract class ColliderManager {

    public static Vector2 isColliding(LineCollider collider1, LineCollider collider2){
            Vector2 collision;
            if (collider1.getXEnd() == collider1.getXStart() && collider2.getXStart() == collider2.getXEnd())//both are verticle
            {
                if(collider1.getXEnd() == collider2.getXEnd()){
                    return null;
                }
                else{
                    collision = new Vector2(collider1.getXStart(), collider1.getXEnd());
                }
            }
            else if(collider1.getXEnd() == collider1.getXStart() && collider2.getXStart() != collider2.getXEnd()){ //line 1 is verticle
                double slope2 = collider2.getXEnd() == collider2.getXStart()? 10000 : (collider2.getYStart() - collider2.getXEnd()) / (collider2.getXStart() - collider2.getXEnd());
                double intercept2 = collider2.getYStart() - (slope2 * collider2.getXStart());
                collision = new Vector2(collider1.getXEnd(), collider1.getXEnd()*slope2+intercept2);
            }
            else if (collider1.getXEnd() != collider1.getXStart() && collider2.getXStart() == collider2.getXEnd())// line 2 is verticle
            {
                double slope1 = collider1.getXEnd() == collider1.getXStart()? 10000 : (collider1.getYStart() - collider1.getYEnd()) / (collider1.getXStart() - collider1.getXEnd());
                double intercept1 = collider1.getYStart() - (slope1 * collider1.getXStart()); 
                collision = new Vector2(collider2.getXStart(), collider2.getXStart()*slope1+intercept1);
                
            }
            else
            {
                double slope1 = (collider1.getYStart() - collider1.getYEnd()) / (collider1.getXStart() - collider1.getXEnd());
                double intercept1 = collider1.getYStart() - (slope1 * collider1.getXStart()); 
                
                double slope2 = (collider2.getYStart() - collider2.getYEnd()) / (collider2.getXStart() - collider2.getXEnd());
                double intercept2 = collider2.getYStart() - (slope2 * collider2.getXStart());

                collision = new Vector2((intercept2 - intercept1) / (slope1 - slope2), ((intercept2 - intercept1) / (slope1 - slope2))*slope1+intercept1);

            }
            
            if(collider1.isWithinBound(collision) && collider2.isWithinBound(collision)){
                return collision;
            }
            return null;
       }

    public static Vector2 isColliding(LineCollider line, CircleCollider circle)
    {
        if (line.getXStart() == line.getXEnd())
        {
            
            double yOption1 = circle.getYStart() + Math.sqrt((circle.getRadius())*(circle.getRadius()) - (line.getXEnd() - circle.getXStart()) * (line.getXEnd() - circle.getXStart()));
            double yOption2 = circle.getYStart() - Math.sqrt((circle.getRadius())*(circle.getRadius()) - (line.getXEnd() - circle.getXStart()) * (line.getXEnd() - circle.getXStart()));

            
            
            if (!(yOption1 < line.getYStart() && yOption1 > line.getYEnd()) && !(yOption1 > line.getYStart() && yOption1 < line.getYEnd())){
                
                yOption1 = Math.sqrt(-1);
            }

            if (!(yOption2 < line.getYStart() && yOption2 > line.getYEnd()) && !(yOption2 > line.getYStart() && yOption2 < line.getYEnd())){
                
                yOption2 = Math.sqrt(-1);
            }


            if(Double.isNaN(yOption1) && Double.isNaN(yOption2))
            {
                return null;
            }


            if (Double.isNaN(yOption1) && !Double.isNaN(yOption2)){
                
                
                return Vector2.getDistance(new Vector2(circle.getXStart(), circle.getYStart()), new Vector2(line.getXStart(), line.getYStart())) > Vector2.getDistance(new Vector2(circle.getXStart(), circle.getYStart()), new Vector2(line.getXEnd(), line.getYEnd()))? new Vector2(line.getXEnd(), line.getYEnd()) : new Vector2(line.getXStart(), line.getYStart());
            }
            else if(!Double.isNaN(yOption1) && Double.isNaN(yOption2))
            {
                
                return Vector2.getDistance(new Vector2(circle.getXStart(), circle.getYStart()), new Vector2(line.getXStart(), line.getYStart())) > Vector2.getDistance(new Vector2(circle.getXStart(), circle.getYStart()), new Vector2(line.getXEnd(), line.getYEnd()))? new Vector2(line.getXEnd(), line.getYEnd()) : new Vector2(line.getXStart(), line.getYStart());
            }
            else
            {
                return new Vector2(line.getXStart(), (yOption1+yOption2)/2);
            }
        } 
        else 
        {
            double s = (line.getYStart() - line.getYEnd()) / (line.getXStart() - line.getXEnd());
            double i = line.getYStart() - circle.getYStart() - s * (line.getXStart() - circle.getXStart());

            

            double xOption1 = (-2*s*i - Math.sqrt((2*s*i) * (2*s*i) -4 * (s*s+1) * (i*i-circle.getRadius()*circle.getRadius()))) / (2 * (s*s+1));
            double xOption2 = (-2*s*i + Math.sqrt((2*s*i) * (2*s*i) -4 * (s*s+1) * (i*i-circle.getRadius()*circle.getRadius()))) / (2 * (s*s+1));

            

            
            if (!(xOption1+ circle.getXStart() < line.getXStart() && xOption1+ circle.getXStart() > line.getXEnd()) && !(xOption1+ circle.getXStart() > line.getXStart() && xOption1+ circle.getXStart() < line.getXEnd())){
                
                xOption1 = Math.sqrt(-1);
            }

            if (!(xOption2+ circle.getXStart()< line.getXStart() && xOption2+ circle.getXStart() > line.getXEnd()) && !(xOption2+ circle.getXStart() > line.getXStart() && xOption2+ circle.getXStart() < line.getXEnd())){
                
                xOption2 = Math.sqrt(-1);
            }
            
            if(Double.isNaN(xOption1) && Double.isNaN(xOption2))
            {
                return null;
            }
            
            if (Double.isNaN(xOption1) && !Double.isNaN(xOption2))
            {
                return Vector2.getDistance(new Vector2(circle.getXStart(), circle.getYStart()), new Vector2(line.getXStart(), line.getYStart())) > Vector2.getDistance(new Vector2(circle.getXStart(), circle.getYStart()), new Vector2(line.getXEnd(), line.getYEnd()))? new Vector2(line.getXEnd(), line.getYEnd()) : new Vector2(line.getXStart(), line.getYStart());
            }
            else if(!Double.isNaN(xOption1) && Double.isNaN(xOption2))
            {
                return Vector2.getDistance(new Vector2(circle.getXStart(), circle.getYStart()), new Vector2(line.getXStart(), line.getYStart())) > Vector2.getDistance(new Vector2(circle.getXStart(), circle.getYStart()), new Vector2(line.getXEnd(), line.getYEnd()))? new Vector2(line.getXEnd(), line.getYEnd()) : new Vector2(line.getXStart(), line.getYStart());
            }
            else
            {
                return new Vector2((xOption1+xOption2)/2  + circle.getXStart(), (xOption1+xOption2)/2*s + i + circle.getYStart());
            }
        }
    }
   
    public static Vector2 isColliding(CircleCollider circle, LineCollider line)
    {
        return ColliderManager.isColliding(line,circle);
    }

    public static Vector2 isColliding(CircleCollider circle1, CircleCollider circle2){
        if(Math.sqrt((circle1.getXStart() - circle2.getXStart()) * (circle1.getXStart() - circle2.getXStart()) + (circle1.getYStart() - circle2.getYStart()) * (circle1.getYStart() - circle2.getYStart())) <= circle1.getRadius() + circle2.getRadius()){
            return new Vector2((circle1.getXStart()*circle1.getRadius() + circle2.getXStart()*circle2.getRadius())/(circle1.getRadius()+circle2.getRadius()), (circle1.getXStart()*circle1.getRadius() + circle2.getYStart()*circle2.getRadius())/(circle1.getRadius()+circle2.getRadius()));
        }
        else
        {
            return null;
        }
    }

    public static Vector2 isCollidingAny(Game game,Player player){
        for(int w = 0; w < game.getWalls().size();w++)
        {
            Vector2 collision = isColliding(player.getCollider(), game.getWalls().get(w).getCollider());
            if(collision != null)
            {
                return collision;//need to set collision point to end of line segment
            }
        }

        return null;
    }

    public static ArrayList<Vector2> isCollidingAnyWalls(Game game,LineCollider lineCollider)
    {
        ArrayList<Vector2> Collisons = new ArrayList<>();
        for(int w = 0; w < game.getWalls().size();w++)
        {
            Vector2 collision = isColliding(lineCollider, game.getWalls().get(w).getCollider());
            if(collision != null)
            {
                
                Collisons.add(collision);
            }
        }

        return Collisons;
    }

    public static ArrayList<Player> isCollidingAnyPlayers(Game game,LineCollider lineCollider)
    {
        ArrayList<Player> players = new ArrayList<>();

        for (Map.Entry<Integer, Player> playerEntry : game.getPlayers().entrySet()) 
        {
            Vector2 collision = isColliding(lineCollider, game.getPlayers().get(playerEntry.getKey()).getCollider());
            if(collision != null)
            {
                players.add(game.getPlayers().get(playerEntry.getKey()));
            }
        }

        return players;
    }
}
