package com.alis.games.arkanoid;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Racquet implements Drawable{

	public final static int RACQUET_POS_Y = 20;

	private int width;
	private int height;
	private int x;

	private int racquetState; // 0 - short, 1 - normal, 2 - long

	private BufferedImage racquetImg;
	private ArkanoidGame arkanoidGame;

	public Racquet(ArkanoidGame arkanoidGame) {
		this.arkanoidGame = arkanoidGame;
		racquetState = 1;
		racquetImg = Assets.racquet;
		width = racquetImg.getWidth();
		height = racquetImg.getHeight();
	}

	public Rectangle getBounds() {
		return new Rectangle(x, arkanoidGame.getActionAreaHeight() - RACQUET_POS_Y - height ,
				width, height);
	}

	private void setRacquetSize(int state){
		switch (state){
			case 0: racquetImg = Assets.racquet_short; break;
			case 1: racquetImg = Assets.racquet; break;
			case 2: racquetImg = Assets.racquet_long; break;
		}
		width = racquetImg.getWidth();
		height = racquetImg.getHeight();
	}

	public void decRacquet(){
		if (racquetState > 0) {
			setRacquetSize(--racquetState);
		}
	}

	public void incRacquet(){
		if (racquetState < 2) {
			setRacquetSize(++racquetState);
		}
	}

	@Override
	public void draw(Graphics2D g){
		g.drawImage( racquetImg, getX(),
				arkanoidGame.getActionAreaHeight() - RACQUET_POS_Y - getHeight(), null);		
	}
	
	public BufferedImage getRacquetImg() {
		return racquetImg;
	}
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if ( x >= arkanoidGame.getActionAreaWidth() - width / 2) this.x = arkanoidGame.getActionAreaWidth() - width / 2;
		else this.x = x;
	}
}