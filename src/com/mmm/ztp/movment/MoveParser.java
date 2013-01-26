package com.mmm.ztp.movment;

public final class MoveParser {
	
	public Movement parse(Integer moveId)
	{
		switch (moveId) {
		
		case 1: return new FullCosinusMovement();	
		case 2: return new FullSinusMovement();
		case 3: return new MinusCosinusMovement();
		case 4: return new MinusSinusMovement();
		case 5: return new SimpleForwardMove();

		default:
			return new FullCosinusMovement();
		}
	}

}
