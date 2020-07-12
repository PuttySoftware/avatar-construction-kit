package com.puttysoftware.ackdemo;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.puttysoftware.ack.AvatarConstructionKit;
import com.puttysoftware.ack.AvatarImageModel;

public class AvatarMaker {
    private AvatarMaker() {
        super();
    }

    public static void main(String[] args)
            throws UnsupportedLookAndFeelException, IOException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        JFrame frame = new JFrame("Avatar Maker Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        JLabel avatarLabel = new JLabel();
        JLabel idLabel = new JLabel();
        JButton button = new JButton("Make an Avatar!");
        button.addActionListener(l -> {
            try {
                AvatarImageModel model = AvatarConstructionKit
                        .constructAvatar();
                avatarLabel.setIcon(model.generateAvatarImage());
                idLabel.setText("Avatar ID: " + model.getAvatarImageID());
                frame.pack();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        content.setOpaque(true);
        content.add(button);
        content.add(avatarLabel);
        content.add(idLabel);
        frame.setContentPane(content);
        frame.pack();
        frame.setVisible(true);
    }
}
