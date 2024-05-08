package com.audentest;

import java.awt.event.MouseListener;

import java.awt.event.MouseEvent;



public class MouseClickManager implements MouseListener
{
    private static boolean isMouse1Down;
    public GameManager gameManager;
    


    public static boolean getIsMouse1Down(){
        return isMouse1Down; 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gameManager.fireBullet();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gameManager.isFiring = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gameManager.isFiring = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }


}
