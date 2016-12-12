package com.alis.games.arkanoid.bonuses;

import com.alis.games.arkanoid.ArkanoidGame;
import com.alis.games.arkanoid.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cor3man on 29.11.2016.
 */
public class BonusDec extends BonusX{

	public BonusDec(Rectangle brickBounds){
		super(brickBounds);
	}

	public BufferedImage setImage(){
		return Assets.bonus_minus;
	}

	public void actRocquet(){
		arkanoidGame.getRacquet().decRacquet();
	}

}
