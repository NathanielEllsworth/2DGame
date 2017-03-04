package com.NathanielEllsworth.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

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
		new Window(WIDTH, HEIGHT, "Ellsworth's 2DGame", this); // 'this' referring to the game parameter
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
				tick(); // put in a tick method
				delta--;
			}
			if(running)
				render(); // put in a render method
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		
	}
	
	private void render(){ //buffer strategy will help lower the frames per second to keep it from crashing
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3); //three is the number of buffers a game should have (creates 3 buffers within the game)
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.green);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String args[]){
		new Game(); //will call constructor
		
		
	}

}
