/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author NathanielEllsworth
 * 
 * in shop you will be able to upgrade your health, upgrade your speed and upgrade your health all the way
 *
 */
public class Shop extends MouseAdapter{
	//Mouse Adapter allows you to add a mouse lister onto this class
	//because I'm going to add a mouse listener onto the Shop class, It's going to look for MouseEvent (mousePressed method) every time you click the mouse 
	
	Handler handler;// using the handler to change things that are pressed
	HUD hud;
	
	private int B1 = 100; //B1 = Box 1    *************************
	private int B2 = 100;				//*************************
	private int B3 = 100;				//*************************
	
	public Shop(Handler handler, HUD hud){
		this.handler = handler; 
		this.hud = hud;
	}
	
	public void render(Graphics g){
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 12);
		Font fnt3 = new Font("arial", 1, 20);
		
		//header
		g.setColor(Color.green);
		g.setFont (fnt);
		g.drawString("SHOP", 235, 70);
		
		//boxes
		
		//box 1 "Upgrade Health"
		g.setColor(Color.white);
		g.setFont(fnt2);
		g.drawString("Upgrade Health", 110, 200);
		g.drawString("Cost: " + B1 , 110, 220); // just going to use the actual score as it adds up (score = payment system)
		g.drawRect(100, 100, 100, 80);
		
		//box 2 "Upgrade Speed"
		g.drawString("Upgrade Speed", 260, 200);
		g.drawString("Cost: " + B2 , 260, 220); // just going to use the actual score as it adds up (score = payment system)
		g.drawRect(250, 100, 100, 80);
		
		//box 3 "Refill Health"
		g.drawString("Refill Health", 410, 200);
		g.drawString("Cost: " + B3 , 410, 220); // just going to use the actual score as it adds up (score = payment system)
		g.drawRect(400, 100, 100, 80);
		
		
		g.setFont(fnt3);
		g.drawString("TOTAL FUNDS: " + hud.getFunds(), 210, 280);
		g.drawString("Press Space to go back", 195, 340);
		
		
		//Box 1
		
		g.setColor(Color.green);     
		g.fillRect(110, 125, 50, 8); //small health bar
		g.setColor(Color.red);		 
		g.fillRect(159, 125, 6, 8);  //small damage on health bar
		g.setColor(Color.green);
		g.fillRect(110, 150, 80, 8); // Upgraded health bar
		
		
		//Box 2
		
		g.setColor(Color.white);
		g.fillRect(305, 125, 32, 32); // Player
		g.fillRect(260, 130, 32, 2); // Speed Tail
		g.fillRect(266, 140, 32, 2); // Speed Tail
		g.fillRect(260, 150, 32, 2); // Speed Tail
		
		
		//Box 3
		
		g.setColor(Color.green);     
		g.fillRect(410, 125, 4, 8); //small health bar
		g.setColor(Color.red);		 
		g.fillRect(414, 125, 76, 8);  //small damage on health bar
		g.setColor(Color.green);
		g.fillRect(410, 150, 80, 8); // Upgraded health bar
		
		
		
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX(); // so wherever you press on the screen it will store it in the mx and my position
		int my = e.getY();
		
		//box 1
		if(mx >= 100 && mx <= 200){
			if(my >= 100 && my <= 180){
				// if clicked between these positions than you have selected box 1 (Upgrade Health)
				//System.out.println("box 1");
				if(hud.getFunds() >= B1){
					hud.setFunds(hud.getFunds() - B1); //so it's going to take the score that you have now and subtract it by B1 (which is a thousand)
					B1 += 100;//***********************************
					hud.bounds += 20;
					hud.HEALTH = (100 + (hud.bounds/2)); // set it to 100 because thats the starting value and say you upgrade your health 5 times
					// if you didn't divide it by 2 than the health would be at 200 and than the h.u.d would multiply it by 2 again making it 400
					//which is not what you want. you just want it just at 200. (you're off setting the heads-up-display by dividing by 2)
				}
			}
//			AudioPlayer.getSound("shop_sound").play();
		}
		
		//box 2
		if(mx >= 250 && mx <= 350){
			if(my >= 100 && my <= 180){
				// if clicked between these positions than you have selected box 2 (Upgrade Speed)
				//System.out.println("box 2");
				if(hud.getFunds() >= B2){
					hud.setFunds(hud.getFunds() - B2); //so it's going to take the score that you have now and subtract it by B1 (which is a thousand)
					B2 += 100;//*************************************
					handler.spd++; //so every time you upgrade it will go from 5 to 6 to 7 to 8 to 9 etc. (class KeyInput lines 45 to 48) 
				}
			}
//			AudioPlayer.getSound("shop_sound").play();
		}
		
		//box 3
		if(mx >= 400 && mx <= 500){
			if(my >= 100 && my <= 180){
				// if clicked between these positions than you have selected box 3 (Refill Health)
				//System.out.println("box 3");
				if(hud.getFunds() >= B3){
					hud.setFunds(hud.getFunds() - B3); //so it's going to take the score that you have now and subtract it by B1 (which is a thousand)
					hud.HEALTH = (100 + (hud.bounds/2));
				}
			}
//			AudioPlayer.getSound("shop_sound").play();
		}
		
	}

}
