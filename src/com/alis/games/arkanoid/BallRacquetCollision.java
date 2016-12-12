package com.alis.games.arkanoid;

import java.awt.*;


public class BallRacquetCollision {

	private final static float COEF = 1F; //no need anymore, always 1.0
	private Rectangle bounds;
	private Ball ball;
	private int radius = Ball.BALL_SIZE / 2;
	private float centerX;
	private float centerY;
	private float ax;
	private float ay;

	// -30, -45, -60, 80, 60, 45, 30
	private static int[] angelsArray = new int[] {150, 135, 120, 80, 60, 45, 30};
	private static float[] radiansArray;

	public BallRacquetCollision(Ball ball) {
		this.ball = ball;
		convertToRadians();
	}

	public boolean isCollision(Rectangle bounds){
		ax = ball.getAx();
		ay = ball.getAy();
		centerX = ball.getX() + ax + radius;
		centerY = ball.getY() + ay + radius;
		this.bounds = bounds;

		if (( bounds.y - centerY >= radius )||( bounds.y - centerY < ay )) return false; //Ball is above the racquet - no collision

		if ((centerX + radius > bounds.x) && (centerX - radius  < ( bounds.x + bounds.width))) {
			collisionOnTopOfRacquet();
			return true;
		}
		return false;
	}

	private void collisionOnTopOfRacquet(){
		float step = bounds.width / radiansArray.length;
		float bound = bounds.x;
		for (int i = 0; i < radiansArray.length; i++) {
			bound += step;
			if (centerX < bound) {
				ax = calcAX(radiansArray[i]);
				ay = calcAY(radiansArray[i]);
				ay *= -1;
				ball.setAx(ax);
				ball.setAy(ay);
				break;
			}
		}
	}

	public float calcAX (float ang){
		float ax = (float)Math.cos(ang);
		return ax * COEF;
	}

	public float calcAY (float ang){
		float ay = Math.abs((float)Math.sin(ang));
		return ay * COEF;
	}

	private static void convertToRadians(){
		radiansArray = new float[angelsArray.length];
		for (int i = 0; i < angelsArray.length; i++) {
			radiansArray[i] = angelsArray[i]*(float)Math.PI/180;
		}
	}

	public static float[] getRadiansArray() {
		return radiansArray;
	}
}
