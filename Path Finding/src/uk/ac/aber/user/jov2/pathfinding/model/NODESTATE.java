package uk.ac.aber.user.jov2.pathfinding.model;

import com.badlogic.gdx.graphics.Color;

public enum NODESTATE {

	EMPTY (Color.WHITE),
	TARGET (Color.YELLOW),
	START (Color.ORANGE),
	PATH (Color.GREEN),
	OBSTACLE (Color.BLUE),
	CHECKED (Color.GRAY);
	
	public Color color;
	
	private NODESTATE(Color color) {
		this.color = color;
	}

}
