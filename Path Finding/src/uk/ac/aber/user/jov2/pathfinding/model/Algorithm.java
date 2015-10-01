package uk.ac.aber.user.jov2.pathfinding.model;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Algorithm {
	
	public abstract void update(float delta);
	public abstract void render(ShapeRenderer sr);
	
}
