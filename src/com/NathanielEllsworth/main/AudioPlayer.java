/**
 * 
 */
package com.NathanielEllsworth.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * @author NathanielEllsworth
 *
 */
public class AudioPlayer {
	
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	//public static Map<String, Sound> soundMap2 = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load(){
		
		try {
			soundMap.put("menu_sound", new Sound("res/click_sound.ogg"));
			
			//soundMap2.put("shop_sound", new Sound("res/coin_sound.ogg"));
			
			musicMap.put("music", new Music("res/background_music.ogg"));
		} catch (SlickException e) {
			
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key){
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key){
		return soundMap.get(key);
	}
	
	//public static Sound getSound2(String key){
	//	return soundMap2.get(key);
	//}
}
