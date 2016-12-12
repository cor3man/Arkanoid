package com.alis.games.arkanoid.bricks;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.alis.games.arkanoid.Assets;
import com.alis.games.arkanoid.Drawable;

public class FieldOfBricks implements Drawable{
	public LinkedList<Brick> listOfBricks = new LinkedList<>();

	public FieldOfBricks(){
		
	}
	
	public void claer(){
		listOfBricks = new LinkedList<Brick>();
	}

	public void createBricksField(char[] charsOfLevel){
		BufferedImage brickImg;
		int y = 40;
		int kx = 0;
		int power = 1;
		for (int i = 0; i < 99; i++) {
			if (i % 9 == 0) {
				y += 21;
				kx = 0;
			}
			switch (charsOfLevel[i]) {
				case 'y':
					brickImg = Assets.YBrick; break;
				case 'b':
					brickImg = Assets.BlueBrick; break;
				case 'r':
					brickImg = Assets.RedBrick; break;
				default:
					brickImg = null;
					break;
			}
			if (brickImg != null) listOfBricks.add(new Brick(new Rectangle(kx*50 + 3, y, 50, 20), power, brickImg));
			kx ++;
		}
	}

	public LinkedList<Brick> getListOfBricks() {
		return listOfBricks;
	}

	@Override
	public void draw(Graphics2D g) {
		for (Brick b: listOfBricks) {
			g.drawImage(b.getBrickImg(), (int)b.getRectangle().getX(), (int)b.getRectangle().getY(), null);
		}
	}
}
