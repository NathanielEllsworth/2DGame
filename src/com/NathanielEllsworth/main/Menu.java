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
	private Random r = new Random();
	
	public Menu(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();// these temporary variables when pressed store what the x position and y position is into the mx, my variables
		int my = e.getY();
		//mx is mouse x, my is mouse y
		
		if(mouseOver(mx, my, 210, 150, 200, 64)){
			game.gameState = STATE.Game;
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); // player class constructor
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));//level-1 will have an enemy
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
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Menu", 240, 70);
		
		g.setFont(fnt2);
		g.setColor(Color.white);
		g.drawRect(210, 150, 200, 64); // debug hot-swap, (CTRL + S) to see real time code changes 
		g.drawString("Play", 270, 190);
		
		
		g.setColor(Color.white);
		g.drawRect(210, 250, 200, 64);
		g.drawString("Help", 270, 290);
		
		g.setColor(Color.white);
		g.drawRect(210, 350, 200, 64);
		g.drawString("Quit", 270, 390);
	}

}