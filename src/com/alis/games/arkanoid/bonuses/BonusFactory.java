package com.alis.games.arkanoid.bonuses;

import java.awt.*;
import java.util.Random;

/**
 * Created by cor3man on 29.11.2016.
 */
public class BonusFactory {

	private BonusX bonus;

	public BonusFactory(Rectangle brickBounds){
		int bonusType = new Random(System.currentTimeMillis()).nextInt(3);
		switch (bonusType){
			case 0:
				bonus = new BonusInc(brickBounds);
				break;
			case 1:
				bonus = new BonusDec(brickBounds);
				break;
			case 2:
				bonus = new BonusTriple(brickBounds);
				break;
		}


	}
	public BonusX getBonusX(){
		return bonus;
	}
}
