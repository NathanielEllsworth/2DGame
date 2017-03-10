package com.NathanielEllsworth.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * 
 * @author NathanielEllsworth
 * 
 * the only purpose of this class is to create the window of the game
 *
 */
public class Window extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5604853402217992119L;
	
	/**
	 * created a JFrame which creates the frame of the game's window
	 * 
	 * @param width of game window
	 * @param height of game window
	 * @param title of the window
	 * @param game inside of the window
	 */
	
	public Window(int width, int height, String title, Game game){
		
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //not needed but by setting this to null the window will appear in the center of the screen 
											//rather than the top left
		frame.add(game); // here I'm adding the 'game class' into the frame
		frame.setVisible(true); // setting it to visible so you can actually see it. Super complex concept here
		game.start(); // running the start method that's in the game class
		
	}
	
}
