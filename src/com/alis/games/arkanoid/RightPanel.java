package com.alis.games.arkanoid;

import java.awt.*;

/**
 * Created by cor3man on 29.11.2016.
 */
public class RightPanel implements Drawable {

	private ArkanoidGame arkanoidGame;

	public RightPanel(ArkanoidGame arkanoidGame){
		this.arkanoidGame = arkanoidGame;
	}

	public void draw(Graphics2D g){
		g.drawImage(Assets.Panel, arkanoidGame.getActionAreaWidth(), 0, null);
		// number of balls
		g.setFont(Assets.font30);
		g.setColor(new Color(215, 215, 215));
		int fontWidth = g.getFontMetrics().stringWidth(String.valueOf(arkanoidGame.getNumberOfBalls()));
		int fontHeight = g.getFontMetrics().getHeight();
		g.drawString(String.valueOf(arkanoidGame.getNumberOfBalls()), 521 - fontWidth / 2, 83 + fontHeight / 2);
		// scores
		g.setFont(Assets.font20);
		g.setColor(new Color(215, 215, 215));
		fontWidth = g.getFontMetrics().stringWidth(String.valueOf(arkanoidGame.getScores()));
		fontHeight = g.getFontMetrics().getHeight();
		g.drawString(String.valueOf(arkanoidGame.getScores()), 521 - fontWidth / 2, 143 + fontHeight / 2);

	}
}
