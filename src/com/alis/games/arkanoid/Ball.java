package com.alis.games.arkanoid;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Ball implements Drawable{
	public final static int BALL_SIZE = 16;
	public static ArrayList<Ball> balls = new ArrayList<>();
	private float speed = 0.15f;
	private long oldTime;
	private float x = .0f;
	private float y = .0f;
	private float ax = -0.4f;
	private float ay = -0.8f;
	private boolean active = false;

	private BallRacquetCollision collision;
	private ArkanoidGame arkanoidGame;

	private Ball(ArkanoidGame arkanoidGame) {
		this.arkanoidGame = arkanoidGame;
		collision = new BallRacquetCollision(this);
		oldTime = System.nanoTime();
		balls.add(this);
	}

	public Ball(ArkanoidGame arkanoidGame, int count){
		for (int i = 0; i < count; i++) {
			new Ball(arkanoidGame);
		}
	}

	private void activateBall() {
		if (!isActive()) {
			active = true;
			oldTime = System.nanoTime();
			x = arkanoidGame.getRacquet().getX()
					+ arkanoidGame.getRacquet().getWidth() / 2 - BALL_SIZE / 2;
			y = arkanoidGame.getRacquet().getBounds().y - 20;
			int angelIndex = new Random(System.nanoTime()).nextInt(BallRacquetCollision
					.getRadiansArray().length);
			ax = collision.calcAX(BallRacquetCollision.getRadiansArray()[angelIndex]);
			ay = collision.calcAY(BallRacquetCollision.getRadiansArray()[angelIndex]);
		}
	}

	public boolean isActive(){
		return active;
	}

	public static Ball addOriginBall(){
		for (Ball b: balls) {
			b.active = false;
		}
		balls.get(0).active = true;
		return balls.get(0);
	}

	public void resetOldTime(){
		for (Ball b: balls) {
			b.oldTime = System.nanoTime();
		}
	}

	public void tripletBall(){
		for (Ball ball: balls) {
			ball.activateBall();
		}
	}

	public static void move(){
		for (Ball b: balls) {
			if (b.active) b.moveBall();
		}
	}

	private int numberOfActiveBalls(){
		int i = 0;
		for (int j = 0; j < balls.size(); j++) {
			if (balls.get(j).active) i++;
		}

		return i;
	}

	public void moveBall() {
		if ((x + ax < 0)||(x + ax > arkanoidGame.getActionAreaWidth() - BALL_SIZE)) ax *= -1;
		if ((y + ay < 0)||(y + ay > arkanoidGame.getActionAreaHeight())) ay *= -1;
		if (y + ay + 10 > arkanoidGame.getActionAreaHeight()) {
			if (numberOfActiveBalls() <= 1) {
				arkanoidGame.setState(GameState.Ready);
				arkanoidGame.getBonusManager().clear();
				Ball.addOriginBall();
				arkanoidGame.decreaseNumberOfBalls();
			}
			else active = false;
		}

		collision.isCollision(arkanoidGame.getRacquet().getBounds());
		arkanoidGame.getBricksManager().isCollision(this);

		long deltaTime = System.nanoTime() - oldTime;
		oldTime = System.nanoTime();//oldTime + deltaTime;
		float pixelMovement = deltaTime / 1000000 * speed;
		x = x + ax * pixelMovement;
		y = y + ay * pixelMovement;
	}

	public void moveBallOnReady() {
		if (arkanoidGame.getRacquet().getX()
				+ arkanoidGame.getRacquet().getWidth() / 2 + BALL_SIZE / 2 >= arkanoidGame
					.getActionAreaWidth()) {
			setX(arkanoidGame.getActionAreaWidth() - Ball.BALL_SIZE);
		} else if (arkanoidGame.getRacquet().getX()
				+ arkanoidGame.getRacquet().getWidth() / 2 - BALL_SIZE / 2 <= 0) {
			setX(1);
		} else {
			setX(arkanoidGame.getRacquet().getX()
					+ arkanoidGame.getRacquet().getWidth() / 2 - BALL_SIZE / 2);
		}
		setY(arkanoidGame.getActionAreaHeight() - Racquet.RACQUET_POS_Y
				- arkanoidGame.getRacquet().getHeight() - Ball.BALL_SIZE);

	}
	
	public void draw(Graphics2D g) {
		for (Ball ball : balls) {
			if ( ball.isActive())
				g.drawImage(Assets.Ball, (int)ball.getX(), (int)ball.getY(),
						null);
		}
	}	

	public static ArrayList<Ball> getBalls() {
		return balls;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getAx() {
		return ax;
	}

	public float getAy() {
		return ay;
	}

	public void setAx(float ax) {
		this.ax = ax;
	}

	public void setAy(float ay) {
		this.ay = ay;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
}