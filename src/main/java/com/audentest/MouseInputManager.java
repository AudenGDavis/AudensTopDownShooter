package com.audentest;

import java.awt.event.MouseMotionAdapter;

import java.awt.event.MouseEvent;



public class MouseInputManager extends MouseMotionAdapter 
{
    private static int mouseX;
    private static int mouseY;
    
    @Override
    public void mouseMoved(MouseEvent e) 
    {
        mouseX = e.getX();
        mouseY = e.getY();
        
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public static int getMouseX(){
        return mouseX;
    }

    public static int getMouseY(){
        return mouseY;
    }

    


}   
