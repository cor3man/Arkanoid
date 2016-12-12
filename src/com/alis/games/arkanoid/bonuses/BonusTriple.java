package com.alis.games.arkanoid.bonuses;

import com.alis.games.arkanoid.ArkanoidGame;
import com.alis.games.arkanoid.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cor3man on 29.11.2016.
 */
public class BonusTriple extends BonusX {
	public BonusTriple(Rectangle brickBounds){
		super(brickBounds);
	}

	public BufferedImage setImage(){
		return Assets.bonus_3;
	}

	public void actRocquet(){
		arkanoidGame.getBall().tripletBall();;
	}
}
