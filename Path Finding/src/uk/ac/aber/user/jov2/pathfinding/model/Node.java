package uk.ac.aber.user.jov2.pathfinding.model;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Node {
	
	public static final int BASICMOVEMENTCOST = 10;
	public static final int DIAGONALMOVEMENTCOST = 14;
	
	public NODESTATE currentState;
	
	public static float SIZE;
	
	private int heuristic; // Is the heuristic of destination
	private int g; // is the distance from the source
	
	public int x, y;
	
	private Node parent;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		currentState = NODESTATE.EMPTY;
	}
	
	public void setState(NODESTATE state){
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
	
	public void calculateH(Node target){
		int x = Math.abs(this.x - target.x);
		int y = Math.abs(this.y - target.y);
		this.heuristic = x + y * BASICMOVEMENTCOST;
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
	
	public Node getParent(){
		return this.parent;
	}
	
	public boolean haveParent(){
		if(parent != null){
			return true;
		}else{
			return false;
		}
	}

	public void setParent(Node parent){
		this.parent = parent;
	}
	
	public boolean equals(Node other) {
		if((this.x == other.x ) && (this.y == other.y))
			return true;
		return false;
	}
	
	public NODESTATE getState(){
		return this.currentState;
	}
	
	@Override
	public String toString() {
		return x + " - " + y;
	}

}
