/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * @author NathanielEllsworth
 *
 */
public class Trail extends GameObject{
	
	private float alpha = 1;// basically what this is going to do is its going to take its alpha value of one so 
	//the objects look like they are fading out into the distance. Once it fades out the the object's tail trail will be destroyed
	private float life;
	private Handler handler;
	private Color color;
	
	private int width, height;
	
	//life is between: 0.001 - 0.1
	

	public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
	}




	public void tick() {
		if(alpha > life){
			alpha -= (life - 0.0001f); // the smaller the number the longer the life of the particle trail is going to be
		}else handler.removeObject(this);
		
	}


	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; //the alpha composite can only be used with the Graphics2D variables here 
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		g2d.setComposite(makeTransparent(1)); //basically sandwiching in the Alpha and the one. (above will be Alpha, below will be 1)
	}
	
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
		//and this is pretty much the method that will be able to render out the transparencies in the object's tail
	}


	public Rectangle getBounds() {
		return null;
	}

}
