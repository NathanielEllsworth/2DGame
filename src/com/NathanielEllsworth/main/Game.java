package com.NathanielEllsworth.main;

import java.awt.Canvas;

/**
 * @author NathanielEllsworth
 *
 */
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 4047949324018978146L;
	
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; // 12 * 9 is just for ease for a good aspect ratio

	public Game(){
		new Window(WIDTH, HEIGHT, "Lick My Nuts!", this); // 'this' referring to the game parameter
	}
	
	
	
	public synchronized void start(){
		
	}
	
	public void run(){
		
	}
	
	public static void main(String args[]){
		new Game(); //will call constructor
		
		
	}

}
