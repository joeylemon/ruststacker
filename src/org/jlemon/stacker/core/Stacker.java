package org.jlemon.stacker.core;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;

public class Stacker {
	
	/**
	 * Detect when a key has been pressed
	 * @param key - The name of the key that was pressed
	 */
	public void keyPressed(int key) throws AWTException, IOException, InterruptedException{
		if(Main.isRustOpen()){
			if(key == 5){
				stack(FurnaceType.SMALL);
			}else if(key == 6){
				stack(FurnaceType.LARGE);
			}
		}else{
			System.out.println("[ERROR] Rust is not open, task will not be performed.");
		}
	}
	
	/**
	 * Stack a furnace
	 * @param type - The furnace's type
	 */
	public void stack(FurnaceType type){
		try{
			int x = type.getStartingX();
			int div = type.getStartingDivisor();
			
			System.out.println("\nStacking a " + type.toString().toLowerCase() + " furnace.");
			
			Robot robot = getRobot();
			
			Point initial = type.getSlot(2);
			robot.mouseMove((int)initial.getX(), (int)initial.getY());
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
			robot.mouseMove(x, Constant.Y_VAL);
			robot.delay(200);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
			robot.mouseMove((int)Constant.STACK.getX(), (int)Constant.STACK.getY());
			
			for(int i = 3; i <= type.getLoops(); i++){
				robot.mouseMove(x, Constant.Y_VAL);
				robot.delay(200);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				
				robot.mouseMove((int)Constant.STACK.getX(), (int)Constant.STACK.getY());
				robot.mousePress(InputEvent.BUTTON1_MASK);
				
				Point slot = type.getSlot(i);
				robot.mouseMove((int)slot.getX(), (int)slot.getY());
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				
				div--;
				x = Constant.LEFT + (Constant.LENGTH / div);
				
				double slot_num = i - 2.0;
				double loops = type.getLoops() - 2.0;
				String progress = "\r" + getProgressBar(slot_num / loops);
				System.out.write(progress.getBytes());
			}
			
			System.out.write("\rTask completed.                    \n".getBytes());
		}catch (Exception ex){
			System.exit(0);
		}
	}
	
	/**
	 * Convert a decimal (0-1) into a progress bar
	 * @param decimal - The decimal
	 * @return The progress bar as a string
	 */
	public String getProgressBar(double decimal){
		double bars = Math.floor(decimal * Constant.MAX_BARS);
		double remainder = Math.floor(Constant.MAX_BARS - bars);
		
		String progress = "[";
		for(int i = 0; i < (int)bars; i++){
			progress += "=";
		}
		for(int i = 0; i < (int)remainder; i++){
			progress += " ";
		}
		progress += "]";
		
		return progress;
	}
	
	/**
	 * Get the default robot with the proper delays
	 * @return The default robot
	 */
	public Robot getRobot() throws AWTException{
		Robot robot = new Robot();
		robot.setAutoDelay(40);
	    robot.setAutoWaitForIdle(true);
	    return robot;
	}

}
