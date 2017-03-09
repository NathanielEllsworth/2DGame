package com.NathanielEllsworth.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * @author NathanielEllsworth
 *
 */
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 4047949324018978146L;
	
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; // 12 * 9 is just for ease for a good aspect ratio
	

	private Thread thread;
	private boolean running = false;
	
	private Random r; 
	//create instance of the handler
	private Handler handler;
	
	private HUD hud;
	
	private Spawn spawner;
	

	public Game(){
		
		handler = new Handler(); // handler above the window to avoid sometimes getting an error and crash sometimes
		//because the window class was being created before the handler class was when the game was initializing starting
		//the game does not see anything until it is initialized
		this.addKeyListener(new KeyInput(handler));//tells the game "Hey, were going to be using keyboard keys so make sure you're
		//'listening' for it." I'm pretty sure it's called the ascii keys where Q will be 81, A is 65, Z is 90, etc. each
		//number corresponds to a different key on the keyboard
		
		new Window(WIDTH, HEIGHT, "Ellsworth's 2DGame", this); // 'this' referring to the game parameter
		
		hud = new HUD();
		
		spawner = new Spawn(handler, hud); // will get an error if this line is above the next because h.u.d. has to be created first.
		//remember code reads from the top down.
		
		r = new Random();
		
		//individual objects in the game
		//spawned the player in the middle of the screen instead of at random
		handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler)); // player class constructor
		
		
		//basic enemies
		//handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		//handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		//handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		//handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		
	}
	
	//so the order of the launch of the game is the Window is created, which starts the Start method, which then called
	//the Run method, which then called the Render method, which called the Handler method. So the Handler method needs
	//to be above the window method so the window knows what it is. Code compiles from top to bottom
	
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
	
	public void run(){ //here is the game loop which is like the heart beat of the game, every game has some version of a game loop like this
		this.requestFocus();//this line just means I don't have to click on the screen to have keyboard control on the player
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
		handler.tick();
		hud.tick();
	}
	
	private void render(){ //buffer strategy will help lower the frames per second to keep it from crashing
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3); //three is the number of buffers a game should have (creates 3 buffers within the game)
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		hud.render(g); // code reads from top to bottom so this goes underneath the handler.
		//first it renders all of the objects (handler) then it renders the Heads-Up_Display (h.u.d.)
		//so the heads up display renders on top of the player
		
		g.dispose();
		bs.show();
		
	}
	
	//clamp method
	public static int clamp(int var, int min, int max){//max will be the window's with and height, same with minimum. this 
		//makes it so the player stays inside the games window
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args[]){
		new Game(); //will call constructor
		
		
	}

}
