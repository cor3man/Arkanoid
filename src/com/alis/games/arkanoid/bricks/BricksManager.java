package com.alis.games.arkanoid.bricks;

import com.alis.games.arkanoid.ArkanoidGame;
import com.alis.games.arkanoid.Assets;
import com.alis.games.arkanoid.Ball;
import com.alis.games.arkanoid.Drawable;

import java.awt.*;
import java.util.Iterator;

public class BricksManager implements Drawable{

	private FieldOfBricks field;
	private ArkanoidGame arkanoidGame;
	private int level = 1;
	
	public BricksManager(ArkanoidGame arkanoidGame){
		this.arkanoidGame = arkanoidGame;
		field = new FieldOfBricks();
	}

	public void createField(int level){
		String sl = "["+level+"]";
		char[] charsOfLevel = new char[99];
		int pos = Assets.levels.indexOf(sl);
		if (pos == -1) pos = 0;
		Assets.levels.getChars(pos+3,pos+102,charsOfLevel,0);
		field.claer();
		field.createBricksField(charsOfLevel);
	}

	public void isCollision(Ball ball){
		if (field.getListOfBricks().isEmpty()) {
			arkanoidGame.nextLevel(++level);
			return;
		}
		Iterator<Brick> iter = field.getListOfBricks().iterator();
		while (iter.hasNext()){
			Brick b = iter.next();
			if (checkBallBrickCollision(ball, b)) {
				if (!b.decPower()) {
					arkanoidGame.getBonusManager().setBonus(b.getRectangle());
					iter.remove();
				}
				arkanoidGame.incScores(1);
			}
		}
	}

	private boolean checkBallBrickCollision(Ball ball, Brick brick){
		float x0 = ball.getX()+ Ball.BALL_SIZE/2;
		float y0 = ball.getY() + Ball.BALL_SIZE/2;
		float x1 = (float)brick.getRectangle().getX();
		float y1 = (float)brick.getRectangle().getY();
		float x2 = x1 + brick.getRectangle().width;
		float y2 = y1 + brick.getRectangle().height;
		float r = Ball.BALL_SIZE/2;
		float ax = ball.getAx();
		float ay = ball.getAy();

		//don't touch!

		if ((x1 < x0)&&(x2 > x0)) {
			if (Math.abs(y1 - y0) < r) {
				if (((ax > 0)&&(ay > 0))||((ax < 0)&&( ay > 0))){
					ball.setAy(ball.getAy() * -1);
					return true;
				}
			}
			if (Math.abs(y0 - y2) < r) {
				if (((ax > 0)&&(ay < 0))||((ax < 0)&&( ay < 0))){
					ball.setAy(ball.getAy() * -1);
					return true;
				}
			}
		}

		if ((y1 < y0)&&(y2 > y0)) {
			if (Math.abs(x1 - x0) < r) {
				if (((ax > 0)&&(ay > 0))||((ax > 0)&&( ay < 0))){
					ball.setAx(ball.getAx() * -1);
					return true;
				}
			}
			if (Math.abs(x0 - x2) < r) {
				if (((ax < 0)&&(ay < 0))||((ax < 0)&&( ay > 0))){
					ball.setAx(ball.getAx() * -1);
					return true;
				}
			}
		}
		return false;

	}

	@Override
	public void draw(Graphics2D g) {
		field.draw(g);
	}
}
