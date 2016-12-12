package com.alis.games.arkanoid.menu;

import com.alis.games.arkanoid.ArkanoidGame;
import com.alis.games.arkanoid.Assets;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by cor3man on 29.10.2016.
 */

public abstract class GameMenu {

	public final static int MENU_POS_Y = 140;

	ArrayList<MenuItem> menu = new ArrayList<>();;
	private MenuItem caption;
	private ArkanoidGame arkanoidGame;

	protected String currentChoice;

	public GameMenu(ArkanoidGame arkanoidGame, String[] strList){
		this.arkanoidGame = arkanoidGame;
		currentChoice = strList[1];
		caption = new MenuItem(strList[0].toString(), Assets.font40);
		caption.rect.setLocation(arkanoidGame.getWidth() / 2 - caption.rect.width / 2,
				MENU_POS_Y - caption.rect.height - 20 );
		//list
		for (int i = 1; i < strList.length; i++) {
			menu.add( new MenuItem(strList[i].toString(), Assets.font30));
		}

		int ay = MENU_POS_Y;
		for ( MenuItem m: menu ) {
			m.rect.setLocation(arkanoidGame.getWidth() / 2 - m.rect.width / 2, ay);
			ay += m.rect.height;
		}
	}
	public void mouseMoved(MouseEvent e){
		for (int i = 0; i < menu.size(); i++) {
			MenuItem m = menu.get(i);
			if ((m.rect.x  < e.getX()) && (m.rect.x + m.rect.width > e.getX())&&
					(m.rect.y < e.getY()) && (m.rect.y + m.rect.height > e.getY())) {
				currentChoice = m.text;
			}
		}
	}

	public abstract void mouseClicked(MouseEvent e);

	public void printScreen(Graphics2D g) {
		g.drawImage(Assets.fog, 0, 0, null);
		g.setFont(Assets.font40);
		g.setColor(new Color(160, 160, 160));

		g.drawString(caption.text, caption.rect.x, caption.rect.y + caption.rect.height);
		g.setFont(Assets.font30);
		for (MenuItem m: menu) {
			g.setColor(new Color(160, 160, 160));
			if (m.text.equals(currentChoice)) g.setColor(new Color(255, 255, 255));
			g.drawString(m.text, m.rect.x, m.rect.y + m.rect.height);
		}
	}

	class MenuItem {
		public String text;
		public Rectangle rect;

		public MenuItem(String text, Font font){
			this.text = text;
			Graphics2D g = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR).createGraphics();
			g.setFont(font);
			int width = g.getFontMetrics().stringWidth(text);
			int height = g.getFontMetrics().getHeight();
			rect = new Rectangle(0, 0, width, height);
		}
	}


}
