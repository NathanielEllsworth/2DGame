package com.NathanielEllsworth.main;

import java.awt.Canvas;

/**
 * @author NathanielEllsworth
 *
 */
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 4047949324018978146L;
	
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; // 12 * 9 is just for ease for a good aspect ratio
	

	private Thread thread;
	private boolean running = false;
	

	public Game(){
		new Window(WIDTH, HEIGHT, "Ellsworth's First Game!", this); // 'this' referring to the game parameter
	}
	
	
	
	public synchronized void start(){//the entire game is going to run within this thread
		
		thread = new Thread(this);
		thread.start(); // this is where the thread is going to be ran
		running = true; // is it running or is it not running
		
	}
	
	public synchronized void stop(){
		
		try{
			thread.join(); // kills off the thread
			running = false;
		}catch(Exception e){
			e.printStackTrace(); // runs an error bug 
		}
	}
	
	public void run(){ //here is the game loop which is like the heart beat of the game, every game has some version of a game loop
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	public static void main(String args[]){
		new Game(); //will call constructor
		
		
	}

}
