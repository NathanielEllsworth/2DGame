/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author NathanielEllsworth
 *
 */
public class SmartEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;

	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i); 
			//'if' our certain variable gets the ID of player then set the GameObject to player
		}
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16); //rectangle collision bounds for basic enemy (loss of health)
	}
	

	public void tick() {

		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8; // -8 will avoid the enemy from ending up in the top corner
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));//enemy distance algorithm 
		
		velX = ((-1/distance) * diffX);
		velY = ((-1/distance) * diffY);
		
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.02f, handler));
	}

	public void render(Graphics g) {

		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

}
