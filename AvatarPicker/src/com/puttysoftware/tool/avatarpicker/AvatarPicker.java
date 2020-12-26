package com.puttysoftware.tool.avatarpicker;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.puttysoftware.ack.AvatarConstructionKit;
import com.puttysoftware.ack.AvatarImageModel;
import com.puttysoftware.errorlogger.ErrorLogger;
import com.puttysoftware.images.BufferedImageIcon;

public class AvatarPicker {
	private ErrorLogger errHandler;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public AvatarPicker() {
		super();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			errHandler = new ErrorLogger("AvatarPicker");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		frame = new JFrame("Avatar Picker");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		JLabel avatarLabel = new JLabel();
		JLabel idLabel = new JLabel("Avatar ID: ");
		JTextField idText = new JTextField();
		idText.setColumns(64);
		JButton pickButton = new JButton("Pick");
		pickButton.addActionListener(l -> {
			try {
				AvatarImageModel model = AvatarConstructionKit.constructAvatar();
				BufferedImageIcon avatarImage = model.generateAvatarImage();
				avatarLabel.setIcon(avatarImage);
				idLabel.setText("Avatar ID: ");
				idText.setText(model.getAvatarImageID());
				frame.pack();
			} catch (IOException e) {
				if (errHandler != null) {
					errHandler.logWarning(e);
				}
			}
		});
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(l -> {
			try {
				AvatarImageModel model = AvatarConstructionKit.constructFromAvatarID(idText.getText());
				BufferedImageIcon avatarImage = model.generateAvatarImage();
				avatarLabel.setIcon(avatarImage);
				idLabel.setText("Avatar ID: ");
				frame.pack();
			} catch (IOException e) {
				if (errHandler != null) {
					errHandler.logWarning(e);
				}
			} catch (IllegalArgumentException i) {
				idLabel.setText("Avatar ID (INVALID): ");
			}
		});
		content.setOpaque(true);
		content.add(pickButton);
		content.add(loadButton);
		content.add(avatarLabel);
		content.add(idLabel);
		content.add(idText);
		frame.setContentPane(content);
		frame.pack();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			AvatarPicker app = new AvatarPicker();
			app.frame.setVisible(true);
		});
	}
}
