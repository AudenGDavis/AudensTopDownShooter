package com.audentest;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class MouseWheelInputManager implements MouseWheelListener 
{
    

    private static float mouseScroll;
    private static float totalMouseScroll = 1;
    private static float totalMin = GamePanel.MIN_ZOOM;
    private float mouseSensitivity = 0.08f;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseScroll = (float)e.getWheelRotation() * mouseSensitivity;
        totalMouseScroll -= mouseScroll;
        totalMouseScroll = totalMouseScroll < totalMin? totalMin : totalMouseScroll;
        
        
        
    }

    public static synchronized float getMouseScroll()
    {
        return mouseScroll;
    }

    public static synchronized float getTotalMouseScroll()
    { 
        return totalMouseScroll;
    }

    



}   
