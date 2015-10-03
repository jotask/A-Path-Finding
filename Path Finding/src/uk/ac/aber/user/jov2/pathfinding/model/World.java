package uk.ac.aber.user.jov2.pathfinding.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class World {
	
	private Node[][] nodes;
	
	public World(int size, OrthographicCamera cam) {
		nodes = new Node[size][size];
		for(int i = 0; i < nodes.length; i++){
			for(int j = 0; j < nodes[0].length; j++){
				nodes[i][j] = new Node(i, j);
			}
		}
		Node.SIZE = (int) (cam.viewportWidth / size);
	}
	
	public Node getRandomPoint(){
		int x = MathUtils.random(nodes.length - 1);
		int y = MathUtils.random(nodes[0].length - 1);
		return nodes[x][y];
	}
	
	public Node getNode(int x, int y){
		Node result;
		try{
			result = nodes[x][y];
		}catch(ArrayIndexOutOfBoundsException e){
			result = null;
		}
		return result;
	}
	
	public void restart(Node start, Node target){
		for(int i = 0; i < nodes.length; i++){
			for(int j = 0; j < nodes.length; j++){
				Node n = nodes[i][j];
				if(n.getState() == NODESTATE.OBSTACLE){
					continue;
				}
				if(!n.equals(start) && !n.equals(target))
					n.setState(NODESTATE.EMPTY);
			}
		}
	}
	
	public Node[][] getWorld(){
		return nodes;
	}
	
	public void render(ShapeRenderer sr){
		for(int i = 0; i < nodes.length; i++){
			for(int j = 0; j < nodes[0].length; j++){
				nodes[i][j].render(sr);
			}
		}
	}
	
	public void calculateH(Node target){
		for(int i = 0; i < nodes.length; i++){
			for(int j = 0; j < nodes[0].length; j++){
				nodes[i][j].calculateH(target);;
			}
		}
	}
	
	public int getSize(){
		return nodes.length;
	}
	
	public void edge(ShapeRenderer sr){
		sr.setColor(Color.BLACK);
		for(int i = 0; i < nodes.length; i++){
			for(int j = 0; j < nodes[0].length; j++){
				nodes[i][j].debug(sr);
			}
		}
	}
	
}
