package uk.ac.aber.user.jov2.pathfinding.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import uk.ac.aber.user.jov2.pathfinding.model.Algorithm;
import uk.ac.aber.user.jov2.pathfinding.model.NODESTATE;
import uk.ac.aber.user.jov2.pathfinding.model.Node;
import uk.ac.aber.user.jov2.pathfinding.model.World;

public class AStart extends Algorithm {

	private World world;
	private OrthographicCamera cam;
		
	private Node start, target;

	public AStart(OrthographicCamera cam) {
		this.cam = cam;
		world = new World(10, this.cam);
		start = world.getRandomPoint();
		start.setState(NODESTATE.START);
		target = world.getRandomPoint();
		target.setState(NODESTATE.TARGET);
		this.preset();
	}
	
	private void preset(){
		// FIXME Delete method for testing
		start.setState(NODESTATE.EMPTY);
		start = world.getNode(0, 0);
		start.setState(NODESTATE.START);
		
		target.setState(NODESTATE.EMPTY);
		int size = world.getSize() - 1;
		target = world.getNode(size, size);
		target.setState(NODESTATE.TARGET);
		
		int sx = 3;
		int sy = 6;
		world.getNode(sx, sy).setState(NODESTATE.OBSTACLE);
		world.getNode(sx + 1, sy - 1).setState(NODESTATE.OBSTACLE);
		world.getNode(sx + 2, sy - 2).setState(NODESTATE.OBSTACLE);
		world.getNode(sx + 3, sy - 3).setState(NODESTATE.OBSTACLE);
		
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.justTouched()) {
			Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(mouse);
			int x = (int) (mouse.x / Node.SIZE);
			int y = (int) (mouse.y / Node.SIZE);
			
			Node selected = world.getNode(x, y);
			boolean isObstacle;

			if(selected.getState().equals(NODESTATE.OBSTACLE)){
				isObstacle = true;
			}else{
				isObstacle = false;
			}
			
			if(selected.equals(start) || selected.equals(target)){
				return;
			}else if(isObstacle){
				System.out.println("obstacle");
				selected.setState(NODESTATE.EMPTY);
			}
			
			if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
				if(Gdx.input.isKeyPressed(Keys.O)){
					selected.setState(NODESTATE.OBSTACLE);
				}else{
					start = selected;
					start.setState(NODESTATE.START);
				}
			} else if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
				target = selected;
				target.setState(NODESTATE.TARGET);
			}
			world.restart(start, target);
		}else if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			this.aStart();
		}
	}
	
	private void aStart(){
		// TODO A start algorithm
//		world.haveObstacles();
		boolean pathFinded = false;
		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		open.add(start);
		
		Node current;
		
		if(start != null && target != null){
			world.calculateH(target);
			while(!pathFinded){
				current = getLowestFInOpen(open);
				closed.add(current);
				open.remove(current);
				if((target.x == current.x) && (target.y == current.y)){
					pathFinded = true;
//					setPath();
					return;
				}
				
				if(current.getState() == NODESTATE.OBSTACLE){
					System.out.println("YES");
					continue;
				}
				
				ArrayList<Node> adjacent = this.getAdjacents(current);
				for(Node n: adjacent){
					if(n != null){
						if(!start.equals(n) && !target.equals(n))
							n.setState(NODESTATE.CHECKED);
						n.setG(current.getH() + n.getH());
						if(!open.contains(n)){
							n.setParent(current);
							open.add(n);
						}
					}
				}
				
				if(open.isEmpty()){
					System.out.println("Path not found");
				}
			}
		}else{
			System.out.println("Start or target not defined");
		}
	}
	
	protected void setPath(){
		// FIXME get to a infinite loop
		LinkedList<Node> path = new LinkedList<>();
		Node current = target;
		boolean done = false;
		while(!done){
			System.out.println(current);
			path.addFirst(current);
			if(!current.equals(target))
				current.setState(NODESTATE.PATH);
			current = current.getParent();
			if(current.equals(start)){
				System.out.println("done");
				done = true;
			}
		}
		
	}
	
	private Node getLowestFInOpen(ArrayList<Node> open){
		Node lowest = null;
		int lowestF = Integer.MAX_VALUE;
		for(Node n: open){
			if(n.getF() < lowestF){
				lowest = n;
				lowestF = lowest.getF();
			}
		}
		return lowest;
	}
	
	private ArrayList<Node> getAdjacents(Node node){
		// TODO implements 8 neighbours or 4 neighbours selection
		int x = node.x;
		int y = node.y;
		ArrayList<Node> n = new ArrayList<Node>();
		// top
		Node top = world.getNode(x, y + 1);
		n.add(top);
		// top right
		Node topRight = world.getNode(x + 1, y + 1);
		n.add(topRight);
		// right
		Node right = world.getNode(x + 1, y);
		n.add(right);
		// bottom right
		Node bottomRight = world.getNode(x + 1, y - 1);
		n.add(bottomRight);
		// bottom
		Node bottom = world.getNode(x, y - 1);
		n.add(bottom);
		// bottom left
		Node bottomLeft = world.getNode(x - 1, y - 1);
		n.add(bottomLeft);
		// left
		Node left = world.getNode(x - 1, y);
		n.add(left);
		// left top
		Node leftTop = world.getNode(x - 1, y + 1);
		n.add(leftTop);
		return n;
	}
	
	protected ArrayList<Node> getWithoutParents(ArrayList<Node> node){
		ArrayList<Node> r = new ArrayList<Node>();
		for(Node n: node){
			if(n != null){
				if(!n.haveParent())
					r.add(n);
			}
		}
		return r;
	}

	@Override
	public void render(ShapeRenderer sr) { world.render(sr); }
	
	@Override
	public void edge(ShapeRenderer sr) { world.edge(sr); }
}
