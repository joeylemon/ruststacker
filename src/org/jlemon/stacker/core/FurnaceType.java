package org.jlemon.stacker.core;

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

}
