package com.alis.games.arkanoid.bricks;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Brick {
	private Rectangle rectangle;  //XY pos plus size
	private int power;
	private BufferedImage brickImg;

	public Brick(Rectangle rect, int power, BufferedImage image) {
		rectangle = rect;
		this.power = power;
		brickImg = image;
	}

	public boolean decPower(){
		if (--power == 0) return false;
		return true;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public BufferedImage getBrickImg() {
		return brickImg;
	}

	public void setBrickImg(BufferedImage brickImg) {
		this.brickImg = brickImg;
	}
	

}
