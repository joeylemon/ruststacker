package org.jlemon.stacker.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Listener implements NativeKeyListener {
	
	private Stacker stacker = new Stacker();
	
	public void initialize(){
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		
		logger.setUseParentHandlers(false);
		try{
			GlobalScreen.registerNativeHook();
		}catch (NativeHookException ex){
			ex.printStackTrace();
		}
		GlobalScreen.addNativeKeyListener(new Listener());
	}

	public void nativeKeyPressed(NativeKeyEvent e){
		// NativeKeyEvent.getKeyText(e.getKeyCode())
		try{
			if(e.getKeyLocation() == 4){
				stacker.keyPressed(e.getKeyCode());
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e){
		
	}

	public void nativeKeyTyped(NativeKeyEvent e){
		
	}
	
}
