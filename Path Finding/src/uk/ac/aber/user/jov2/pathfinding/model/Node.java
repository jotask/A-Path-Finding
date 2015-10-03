package uk.ac.aber.user.jov2.pathfinding.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Node {
	
	public static final int BASICMOVEMENTCOST = 10;
	public static final int DIAGONALMOVEMENTCOST = 14;
	
	public enum NODE_STATE {
		EMPTY (Color.WHITE),
		TARGET (Color.YELLOW),
		START (Color.ORANGE),
		PATH (Color.GREEN),
		OBSTACLE (Color.BLACK),
		CHECKED (Color.GRAY);
		
		public Color color;
		private NODE_STATE(Color c) {
			this.color = c;
		}
		
		};
	
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
		sr.setColor(currentState.color);
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
	
	public boolean equals(Node other) {
		if((this.x == other.x ) && (this.y == other.y))
			return true;
		return false;
	}

}
