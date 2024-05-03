package com.audentest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import javax.swing.event.MouseInputAdapter;


public class InputManager implements KeyListener
{
    static HashMap<Integer, Boolean> currentKeys= new HashMap<>();

    public InputManager()
    {
        currentKeys.put(KeyEvent.VK_W, false);
        currentKeys.put(KeyEvent.VK_S, false);
        currentKeys.put(KeyEvent.VK_A, false);
        currentKeys.put(KeyEvent.VK_D, false);
        currentKeys.put(KeyEvent.VK_UP, false);
        currentKeys.put(KeyEvent.VK_DOWN, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            currentKeys.replace(e.getKeyCode(), true);
            break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            currentKeys.replace(e.getKeyCode(), false);
            break;
        }
    }

    public static synchronized boolean isKeyDown(Integer e)
    {
        return currentKeys.get(e);
    }

    
    
}
