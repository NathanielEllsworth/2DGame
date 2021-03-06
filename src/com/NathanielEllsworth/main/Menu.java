/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.NathanielEllsworth.main.Game.STATE;

/**
 * @author NathanielEllsworth
 *
 */
public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();// these temporary variables when pressed store what the x position and y position is into the mx, my variables
		int my = e.getY();
		//mx is mouse x, my is mouse y
		
		if(game.gameState == STATE.Menu){
		
		//play button
		if(mouseOver(mx, my, 210, 150, 200, 64)){
			game.gameState = STATE.Select;
			
			AudioPlayer.getSound("menu_sound").play();
			return;
		}
			
		//help button
		if(mouseOver(mx, my, 80, 250, 200, 64)){
			game.gameState = STATE.Help;
			
			AudioPlayer.getSound("menu_sound").play();
		}
		
		//portfolio button *******************************************************************************************
		if(mouseOver(mx, my, 320, 250, 200, 64)){
			game.gameState = STATE.Portfolio;
					
			AudioPlayer.getSound("menu_sound").play();
		}
					
		//quit button
		if(mouseOver(mx, my, 210, 350, 200, 64)){
			System.exit(1);
		}
	}
		
		if(game.gameState == STATE.Select){
			
			//normal button
			if(mouseOver(mx, my, 210, 150, 200, 64)){
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); // player class constructor
				handler.clearEnemies();//clears particles when user clicks the 'play' button
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));//level-1 will have an enemy
				
				game.diff = 0;
				
				AudioPlayer.getSound("menu_sound").play();
			}
				
			//hard button
			if(mouseOver(mx, my, 210, 250, 200, 64)){
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); // player class constructor
				handler.clearEnemies();//clears particles when user clicks the 'play' button
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));//level-1 will have an enemy
				
				game.diff = 1;
				
				AudioPlayer.getSound("menu_sound").play();
			}
						
			//Back button
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}	
		
		//back button for help
		if(game.gameState == STATE.Help){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState = STATE.Menu;
				
				AudioPlayer.getSound("menu_sound").play();
				
				return;
			}
		}
		
		//back button for portfolio ***************************************************************************
		if(game.gameState == STATE.Portfolio){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState = STATE.Menu;
				
				AudioPlayer.getSound("menu_sound").play();
				
				return;
			}
		}
		
		//back button for help
		if(game.gameState == STATE.End){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setFunds(0);
				AudioPlayer.getSound("menu_sound").play();
			}
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){//at the top of a square, if x is anywhere between those two points, continue.
			if(my > y && my < y + height){//on the sides of a square, if y is anywhere between those two points, continue.
				//if both of those are true? that means the mouse position has to fall somewhere within that box (making a 'play' button for example)
				//if you click above the button, yes it is within the x boundaries but not within the y boundaries so it wouldn't work, also vice versa
				return true;
			}else return false;
		}else return false;
		
	}
	
	
	public void tick(){
		
	}
	
	
	public void render(Graphics g){
		if(game.gameState == STATE.Menu){
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Ellsworth Engineering", 55, 85); //240, 70 "Menu"
			
			//Play
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64); // debug hot-swap, (CTRL + S) to see real time code changes 
			g.drawString("Play", 270, 190);
			
			//Help
			g.drawRect(80, 250, 200, 64);
			g.drawString("Help", 150, 290);
			
			//Portfolio
			g.drawRect(320, 250, 200, 64);
			g.drawString("Portfolio", 358, 290);
			
			//Quit
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
			
			//Help page
		}else if(game.gameState == STATE.Help){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			//Help header
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 255, 70);
			
			g.setFont(fnt3);
			g.drawString("Use W, A, S, D, keys to move player and dodge enemies", 60, 180);
			g.drawString("Use the P key to Pause the game", 160, 215);
			g.drawString("Use the Space Bar key to access the Shop", 100, 250);
			
			
			//Back Button
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);

			//Portfolio page
		}else if(game.gameState == STATE.Portfolio){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			//Help header
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Portfolio", 205, 70);
			
			g.setFont(fnt3);
			//g.drawString("Use W, A, S, D, keys to move player and dodge enemies", 60, 210);
			g.drawString("Looking through countless profiles and bios must get old", 42, 105);
			g.drawString("so I thought I would help break it up and make a fun little", 40, 140);
			g.drawString("Java game for ya! Feel free to come back anytime! ..but", 40, 175);
			g.drawString("Let's see if you can beat me at my own game", 105, 210);
			
			g.setFont(fnt);
			g.setColor(Color.red);
			g.drawString(">:) Level: 30", 150, 260);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Pass the dirty thirty and I will personally buy you lunch", 45, 295);
			g.drawString("..Good Luck!", 440, 330); //260
			
			
			
			//Back Button
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);

		}else if(game.gameState == STATE.End){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			//Help header
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 190, 100);
			
			g.setFont(fnt);
			g.drawString("Level Reached: " + hud.getLevel() , 115, 240);
			
			
			//Back Button
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again", 244, 390);

		}else if(game.gameState == STATE.Select){
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 68, 85); //240, 70 "Menu"
			
			//Select Difficulty: Normal
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64); // debug hot-swap, (CTRL + S) to see real time code changes 
			g.drawString("Normal", 260, 192);
			
			//Hard
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 275, 292);
			
			//Back to Menu
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 275, 390);
		}

	}

}
