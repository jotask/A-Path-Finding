package uk.ac.aber.user.jov2.pathfinding.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Cell {
	
	public enum CELL_STATE {EMPTY, POINT, EDGE, PATH};
	
	public CELL_STATE currentState;
	
	public static float SIZE;
	
	private int x, y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		currentState = CELL_STATE.EMPTY;
	}
	
	public void setState(CELL_STATE state){
		currentState = state;
	}
	
	public void render(ShapeRenderer sr){
		if(currentState == CELL_STATE.EMPTY){
			sr.setColor(Color.WHITE);
		}else if(currentState == CELL_STATE.EDGE){
			sr.setColor(Color.RED);
		}else if(currentState == CELL_STATE.PATH){
			sr.setColor(Color.GREEN);
		}else if(currentState == CELL_STATE.POINT){
			sr.setColor(Color.BLUE);
		}
		sr.box(x * SIZE, y * SIZE, 0, SIZE, SIZE, 0);
	}

}
