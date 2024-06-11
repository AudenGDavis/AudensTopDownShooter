package com.audentest;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.audentest.SupportClasses.GameClasses.Game;

import javax.swing.JTextField;
import javax.swing.JButton;

public class JoinMenu extends JFrame
{
    public JoinMenu()
    {
        super("Auden's Top Down Shooter");
        this.setBounds(0, 0, 700, 550);
        this.setLocation(100, 100);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JoinMenu This = this;

        JLabel title = new JLabel();
        title.setText("<html>&#160Auden's Top<br/>Down Shooter</html>");
        title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), 60));
        title.setBounds(0,-250, 700, 700);
        title.setHorizontalAlignment(0);
        title.setVerticalAlignment(0);
        this.add(title);

        JTextField ipField = new JTextField(10);
        ipField.setBounds(325, 240, 160, 40);
        ipField.setFont(new Font(ipField.getFont().getName(), ipField.getFont().getStyle(),15));
        
        this.add(ipField);

        JLabel ipLabel = new JLabel("Ip Address:");
        ipLabel.setBounds(235, 240, 140, 40);
        ipLabel.setFont(new Font(ipLabel.getFont().getName(), ipLabel.getFont().getStyle(), 15));
        this.add(ipLabel);

        JButton jButton = new JButton("Join");
        jButton.setBounds(300, 350, 100, 40);
        jButton.setFont(new Font(jButton.getFont().getName(), jButton.getFont().getStyle(), 20));
        this.add(jButton);

        JLabel errorLabel = new JLabel();
        errorLabel.setBounds(250, 400, 200, 40);
        errorLabel.setFont(new Font(errorLabel.getFont().getName(), errorLabel.getFont().getStyle(), 15));
        errorLabel.setHorizontalAlignment(0);
        errorLabel.setVerticalAlignment(0);
        this.add(errorLabel);


        jButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    ServerCommunicator serverCommunicator;
                    Game game = new Game();
                    serverCommunicator = new ServerCommunicator(ipField.getText(),game,42069);
                    System.out.println(ipField.getText());

                    This.setVisible(false);
                } 
                catch (Exception exception)
                {
                    errorLabel.setText("unable to connect to \"" + ipField.getText() + "\"");
                }
            }
            
        });

        
        

        this.paintComponents(getGraphics());
    }
}
