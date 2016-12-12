package com.alis.games.arkanoid.bonuses;

import com.alis.games.arkanoid.ArkanoidGame;
import com.alis.games.arkanoid.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by cor3man on 29.11.2016.
 */
public class BonusManager implements Drawable{

	private ArrayList<BonusX> listOfBonuses = new ArrayList<>();
	public BonusManager(ArkanoidGame arcanoidGame){
		BonusX.setArkanoidGame(arcanoidGame);
	}

	public void setBonus(Rectangle brickBounds){
		int dice = new Random(System.nanoTime()).nextInt(6);
		if ((dice == 5)||(dice == 4))
			listOfBonuses.add(new BonusFactory(brickBounds).getBonusX());
	}

	public void clear(){
		listOfBonuses = new ArrayList<>();
	}

	public void move(){
		if (listOfBonuses.size() == 0) return;
		Iterator<BonusX> iter = listOfBonuses.iterator();
		while(iter.hasNext()) {
			if (!iter.next().move()){// if after moving bonus is out of bounds then remove
				iter.remove();
			}
		}
	}

	public void draw(Graphics2D g){
		for(BonusX b: listOfBonuses){
			b.draw(g);
		}
	}

	public void resetOldTime(){
		for(BonusX b: listOfBonuses){
			b.resetOldTime();
		}
	}

}
