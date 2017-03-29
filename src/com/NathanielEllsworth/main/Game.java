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
	
	public static boolean paused = false; //pausing the game during game play
	public int diff = 0;
	
	//0 = normal
	//1 = hard
	
	private Random r; 
	//create instance of the handler
	private Handler handler;
	
	private HUD hud;
	
	private Spawn spawner;
	
	private Menu menu;
	
	private Shop shop;
	
	public enum STATE{ //adding menu before the game starts
		Menu,
		Select,
		Help,
		Portfolio,
		Shop,
		Game,
		End, 
	};
	
	public static STATE gameState = STATE.Menu;//(replace 'Menu' with 'Game' and it renders the game) 
	//now I can cast the enum STATE as a type, like an int, boolean, thread that holds those two types of 
	//values (Menu, Game)
	

	public Game(){
		
		handler = new Handler(); // handler above the window to avoid sometimes getting an error and crash sometimes
		//because the window class was being created before the handler class was when the game was initializing starting
		//the game does not see anything until it is initialized
		hud = new HUD();//H.U.D. has to be above 'Menu' to compile correctly (you will get an error if this is anywhere else)(have to create it before you can use it)
		shop = new Shop(handler, hud);
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));//tells the game "Hey, were going to be using keyboard keys so make sure you're
		//'listening' for it." I'm pretty sure it's called the ascii keys where Q will be 81, A is 65, Z is 90, etc. each
		//number corresponds to a different key on the keyboard
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		
		new Window(WIDTH, HEIGHT, "Ellsworth's 2DGame", this); // 'this' referring to the game parameter
		
		
		
		spawner = new Spawn(handler, hud, this); // will get an error if this line is above the next because h.u.d. has to be created first.
		//remember code reads from the top down.
		
		
		
		r = new Random();
		
		if(gameState == STATE.Game)
		{
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler)); // player class constructor
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));//level-1 will have an enemy
		}else{
			for(int i = 0; i < 15; i++){
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}
		
		//individual objects in the game
		//spawned the player in the middle of the screen instead of at random
		
		
		//handler.addObject(new EnemyBoss((Game.WIDTH / 2)-48, -1, ID.EnemyBoss, handler));
		
		
		
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
		
		
		if(gameState == STATE.Game) //if gameState equals STATE.Game then update the heads up display, spawner
		{
			if(!paused)
			{
				hud.tick();
				spawner.tick();
				handler.tick();
				
				
				if(HUD.HEALTH <= 0){
					HUD.HEALTH = 100;
					gameState = STATE.End; //When you run out of health, the game ends here!
					handler.clearEnemies();
					for(int i = 0; i < 15; i++){
						handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
					}
				}
			}
			
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Portfolio || gameState == STATE.End || gameState == STATE.Select){
			menu.tick();
			handler.tick();
			
		}
		
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
		
		if(paused){
			g.setColor(Color.white);
			g.drawString("PAUSED", 100, 100);
		}
		
		
		if(gameState == STATE.Game){
			hud.render(g); // code reads from top to bottom so this goes underneath the handler.
			//first it renders all of the objects (handler) then it renders the Heads-Up_Display (h.u.d.)
			//so the heads up display renders on top of the player
			handler.render(g);
		}else if(gameState == STATE.Shop){
			shop.render(g);//this will render shop when you are in the state of shop
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Portfolio || gameState == STATE.End || gameState == STATE.Select){
			menu.render(g);
			handler.render(g);
		}
		
		g.dispose();
		bs.show();
		
	}
	
	//clamp method
	public static float clamp(float var, float min, float max){//max will be the window's with and height, same with minimum. this 
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
