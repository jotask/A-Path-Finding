package uk.ac.aber.user.jov2.pathfinding.algorithm;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import uk.ac.aber.user.jov2.pathfinding.model.Algorithm;
import uk.ac.aber.user.jov2.pathfinding.model.Node;
import uk.ac.aber.user.jov2.pathfinding.model.Node.NODE_STATE;
import uk.ac.aber.user.jov2.pathfinding.model.World;

public class AStart extends Algorithm {

	private World world;
	private OrthographicCamera cam;
		
	private Node start, target;

	public AStart(OrthographicCamera cam) {
		this.cam = cam;
		world = new World(10, this.cam);
		start = world.getRandomPoint();
		start.setState(NODE_STATE.START);
		target = world.getRandomPoint();
		target.setState(NODE_STATE.TARGET);
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.justTouched()) {
			Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(mouse);
			if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
				int x = (int) (mouse.x / Node.SIZE);
				int y = (int) (mouse.y / Node.SIZE);
				start = world.getNode(x, y);
				start.setState(NODE_STATE.START);
			} else if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
				int x = (int) (mouse.x / Node.SIZE);
				int y = (int) (mouse.y / Node.SIZE);
				target = world.getNode(x, y);
				target.setState(NODE_STATE.TARGET);
			}
			world.restart(start, target);
		}else if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			this.aStart();
		}
	}
	
	private void aStart(){
		// TODO A start algorithm
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
					System.out.println("Path finded");
//					setPath(current, target);
					return;
				}
				ArrayList<Node> adjacent = this.getAdjacents(current);
				for(Node n: adjacent){
					if(n != null){
						if(!start.equals(n) && !target.equals(n))
							n.setState(NODE_STATE.CHECKED);
						n.setParent(current);
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
	
//	private void setPath(Node start, Node target){
//		LinkedList<Node> path = new LinkedList<>();
//		Node current = target;
//		boolean done = false;
//		while(!done){
//			path.addFirst(current);
//			current.setState(NODE_STATE.PATH);
//			current = current.getParen();
//			
//			if((current.x == start.x) && (current.y == target.y)){
//				done = true;
//			}
//			
//		}
//		
//	}
	
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
		n.add(world.getNode(x, y + 1));
		// top right
		n.add(world.getNode(x + 1, y + 1));
		// right
		n.add(world.getNode(x + 1, y));
		// bottom right
		n.add(world.getNode(x + 1, y - 1));
		// bottom
		n.add(world.getNode(x, y - 1));
		// bottom left
		n.add(world.getNode(x - 1, y - 1));
		// left
		n.add(world.getNode(x - 1, y));
		// left top
		n.add(world.getNode(x - 1, y + 1));
		
		return n;
		
	}

	@Override
	public void render(ShapeRenderer sr) { world.render(sr); }
	
	@Override
	public void edge(ShapeRenderer sr) { world.edge(sr); }
}
