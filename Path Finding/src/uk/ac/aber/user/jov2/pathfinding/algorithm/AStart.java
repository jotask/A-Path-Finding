package uk.ac.aber.user.jov2.pathfinding.algorithm;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import uk.ac.aber.user.jov2.pathfinding.model.Algorithm;
import uk.ac.aber.user.jov2.pathfinding.model.Cell;
import uk.ac.aber.user.jov2.pathfinding.model.Cell.CELL_STATE;
import uk.ac.aber.user.jov2.pathfinding.model.World;

public class AStart extends Algorithm {

	private World world;

	private Cell a, b;

	public AStart(OrthographicCamera cam) {
		world = new World(50, cam);
		a = world.getRandomPoint();
		b = world.getRandomPoint();
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.justTouched()) {
			if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
				a.setState(CELL_STATE.EMPTY);
				a = world.getRandomPoint();
				a.setState(CELL_STATE.POINT);
			} else if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
				b.setState(CELL_STATE.EMPTY);
				b = world.getRandomPoint();
				b.setState(CELL_STATE.POINT);
			}
		}else if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			this.aStart();
		}
	}
	
	private void aStart(){
		// TODO A start algorithm
	}

	@Override
	public void render(ShapeRenderer sr) { world.render(sr); }
	
	@Override
	public void edge(ShapeRenderer sr) { world.edge(sr); }
}
