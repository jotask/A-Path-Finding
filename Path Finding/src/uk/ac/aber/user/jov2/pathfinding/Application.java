package uk.ac.aber.user.jov2.pathfinding;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import uk.ac.aber.user.jov2.pathfinding.algorithm.AStart;
import uk.ac.aber.user.jov2.pathfinding.model.Algorithm;

public class Application implements ApplicationListener{
	
	private ShapeRenderer sr;
	private OrthographicCamera cam;
	private Algorithm algorithm;
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		cfg.width = 600;
		cfg.height = 600;
		cfg.title = "Path Finding";
		cfg.resizable = false;
		cfg.fullscreen = false;
		
		new LwjglApplication(new Application(), cfg);
	}

	@Override
	public void create() {
		sr = new ShapeRenderer();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		algorithm = new AStart(cam);
	}

	@Override
	public void resize(int width, int height) { }

	@Override
	public void render() {
		algorithm.update(Gdx.graphics.getDeltaTime());
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeType.Filled);
		algorithm.render(sr);
		sr.end();
	}

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() {
		sr.dispose();
	}

}
