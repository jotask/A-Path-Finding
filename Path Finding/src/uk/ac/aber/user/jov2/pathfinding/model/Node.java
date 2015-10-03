package uk.ac.aber.user.jov2.pathfinding.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Node {
	
	public static final int BASICMOVEMENTCOST = 10;
	public static final int DIAGONALMOVEMENTCOST = 14;
	
	public enum NODE_STATE {EMPTY, POINT, EDGE, PATH, OBSTACLE, CHECKED};
	
	public NODE_STATE currentState;
	
	public static float SIZE;
	
	private int heuristic; // Is the heuristic of destination
	private int g; // is the distance from the source
	
	public int x, y;
	
	private Node parent;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		currentState = NODE_STATE.EMPTY;
	}
	
	public void setState(NODE_STATE state){
		currentState = state;
	}
	
	public void render(ShapeRenderer sr){
		if(currentState == NODE_STATE.EMPTY){
			sr.setColor(Color.WHITE);
		}else if(currentState == NODE_STATE.EDGE){
			sr.setColor(Color.RED);
		}else if(currentState == NODE_STATE.PATH){
			sr.setColor(Color.GREEN);
		}else if(currentState == NODE_STATE.POINT){
			sr.setColor(Color.BLUE);
		}else if(currentState == NODE_STATE.OBSTACLE){
			sr.setColor(Color.BLACK);
		}else if(currentState == NODE_STATE.CHECKED){
			sr.setColor(Color.GRAY);
		}
		sr.box(x * SIZE, y * SIZE, 0, SIZE, SIZE, 0);
	}

	public void debug(ShapeRenderer sr) {
		sr.box(x * SIZE, y * SIZE, 0, SIZE, SIZE, 0);
	}
	
	public int getH(){
		return this.heuristic;
	}
	
	public int getG(){
		return this.g;
	}
	
	public int getF(){
		return this.heuristic + this.g;
	}
	
	public void setH(int _h){
		this.heuristic = _h;
	}
	
	public void setG(int _g){
		this.g = _g;
	}
	
	public Node getParen(){
		return this.parent;
	}

	public void setParent(Node parent){
		this.parent = parent;
	}

}
