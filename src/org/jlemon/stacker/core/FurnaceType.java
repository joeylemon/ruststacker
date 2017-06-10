package org.jlemon.stacker.core;

import java.awt.Point;

public enum FurnaceType {
	
	LARGE(Constant.FIFTEENTHS, 16, 16),
	SMALL(Constant.THIRDS, 4, 4);
	
	private int x;
	private int div;
	private int loops;
	FurnaceType(int x, int div, int loops){
		this.x = x;
		this.div = div;
		this.loops = loops;
	}
	
	/**
	 * Get the default starting x position
	 * @return The starting x position
	 */
	public int getStartingX(){
		return x;
	}
	
	/**
	 * Get the default starting divisor
	 * @return The starting divisor
	 */
	public int getStartingDivisor(){
		return this.div;
	}
	
	/**
	 * Get the amount of times to loop while stacking
	 * @return The amount of times to loop
	 */
	public int getLoops(){
		return this.loops;
	}
	
	/**
	 * Get a slot from the furnace's inventory
	 * @param slot - The slot ID
	 * @return The location of the slot
	 */
	public Point getSlot(int slot){
		if(this == LARGE){
			int x = 0;
			int y = 0;
			if(slot <= 6){
				x = Constant.BASE_X + ((slot - 1) * Constant.X_SLOT_DIFF);
				y = Constant.BASE_Y;
			}else if(slot <= 12){
				x = Constant.BASE_X + ((slot - 7) * Constant.X_SLOT_DIFF);
				y = Constant.BASE_Y + Constant.Y_SLOT_DIFF;
			}else if(slot <= 18){
				x = Constant.BASE_X + ((slot - 13) * Constant.X_SLOT_DIFF);
				y = Constant.BASE_Y + (Constant.Y_SLOT_DIFF * 2);
			}
			
			return new Point(x, y);
		}else{
			int x = Constant.BASE_X + ((slot - 1) * Constant.X_SLOT_DIFF);
			int y = Constant.BASE_Y + (Constant.Y_SLOT_DIFF * 2);
			
			return new Point(x, y);
		}
	}

}
