package com.alis.games.arkanoid.bonuses;

/**
 * Created by cor3man on 29.11.2016.
 */

import com.alis.games.arkanoid.ArkanoidGame;
import com.alis.games.arkanoid.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class BonusX implements Drawable{

	public static final float SPEED = 0.4f;

	BufferedImage image;
	private float x;
	private float y;
	private float ay = 0.1f;
	private float ownSpeed;

	private int width;
	private int height;

	protected static ArkanoidGame arkanoidGame;

	private long oldTime;

	public BonusX(Rectangle brickBounds){

		ownSpeed = SPEED - (float)(new Random(System.currentTimeMillis()).nextInt(3) - 1) / 10;

		image = setImage();
		width = image.getWidth();
		height = image.getHeight();

		x = brickBounds.x + brickBounds.width / 2 - width / 2;
		y = brickBounds.y - brickBounds.height / 2 + height;

		oldTime = System.nanoTime();
	}

	public boolean move() {
		Rectangle racquetBounds = arkanoidGame.getRacquet().getBounds();
		long deltaTime = System.nanoTime() - oldTime;
		oldTime = System.nanoTime();//oldTime + deltaTime;
		float pixelMovement = deltaTime / 1000000 * ownSpeed;

		if (isCatched(racquetBounds)) {
			actRocquet();
			return false;
		}

		if (y + ay > arkanoidGame.getHeight() - height) {
			return false;
		}
		y = y + ay * pixelMovement;

		return true;
	}

	public boolean isCatched(Rectangle rect){
		if ((x + width  > rect.x)&&(x < rect.x + rect.width)
				&&(y + height + ay >= rect.y)&&(y < rect.y + rect.height)) return true;
		return false;
	}

	public abstract BufferedImage setImage();

	public void resetOldTime(){
		oldTime = System.nanoTime();
	}

	public abstract void actRocquet();

	public static void setArkanoidGame(ArkanoidGame arcanoidGame){
		BonusX.arkanoidGame  = arcanoidGame;
	}

	public void draw(Graphics2D g){
		g.drawImage(image, (int)x, (int)y, null);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
