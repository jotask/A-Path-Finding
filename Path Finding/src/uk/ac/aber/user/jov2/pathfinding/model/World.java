package uk.ac.aber.user.jov2.pathfinding.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class World {
	
	private Cell[][] cells;
	
	public World(int size, OrthographicCamera cam) {
		cells = new Cell[size][size];
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[0].length; j++){
				cells[i][j] = new Cell(i, j);
			}
		}
		Cell.SIZE = (int) (cam.viewportWidth / size); 
	}
	
	public Cell getRandomPoint(){
		int x = MathUtils.random(cells.length - 1);
		int y = MathUtils.random(cells[0].length - 1);
		return cells[x][y];
	}
	
	public Cell getcell(int x, int y){
		return cells[x][y];
	}
	
	public void render(ShapeRenderer sr){
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[0].length; j++){
				cells[i][j].render(sr);
			}
		}
	}

}
