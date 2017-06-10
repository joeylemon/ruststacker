package org.jlemon.stacker.core;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static final void main(String[] args) {
		Listener listener = new Listener();
		listener.initialize();
		
		//printMouseLocation();
		
		clearConsole();
		System.out.println("Welcome to Rust Stacker.");
		System.out.println("Press 4 on the numpad to stack a small furnace.");
		System.out.println("Press 5 on the numpad to stack a large furnace.");
	}
	
	/**
	 * Clear the console by printing multiple empty lines
	 */
	public static final void clearConsole(){
		String lines = "";
		for(int i = 0; i < 30; i++){
			lines += "\n";
		}
		System.out.println(lines);
	}
	
	/**
	 * Continuously print the mouse's location for debugging
	 */
	public static final void printMouseLocation(){
		while(true){
			Point p = MouseInfo.getPointerInfo().getLocation();
			System.out.println((int)p.getX() + ", " + (int)p.getY());
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Check if Rust is open
	 * @return True if Rust is open, false if not
	 */
	public static final boolean isRustOpen(){
		try{
			String line;
			String pidInfo = "";

			Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");

			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while((line = input.readLine()) != null){
			    pidInfo += line; 
			}

			input.close();

			if(pidInfo.contains("Rust")){
				return true;
			}
		}catch (Exception ex){
			ex.printStackTrace();
			System.exit(0);
		}
		
		return false;
	}

}
