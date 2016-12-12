package com.alis.games.arkanoid.bonuses;

import com.alis.games.arkanoid.ArkanoidGame;
import com.alis.games.arkanoid.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cor3man on 29.11.2016.
 */
public class BonusInc extends BonusX{

	public BonusInc(Rectangle brickBounds){
		super(brickBounds);
	}

	public BufferedImage setImage(){
		return Assets.bonus_plus;
	}

	public void actRocquet(){
		arkanoidGame.getRacquet().incRacquet();
	}
}
