package com.alis.games.arkanoid;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.*;

/**
 * Created by cor3man on 26.10.2016.
 */
public class AssetManager {
	public AssetManager(){

		try {
			Assets.Ball = ImageIO.read(getClass().getResource("/resources/soccer_ball.png"));
			Assets.background = ImageIO.read(getClass().getResource("/resources/GameBackground.png"));
			Assets.Panel = ImageIO.read(getClass().getResource("/resources/RightPanel.png"));
			Assets.YBrick = ImageIO.read(getClass().getResource("/resources/YBrick.png"));
			Assets.RedBrick = ImageIO.read(getClass().getResource("/resources/RedBrick.png"));
			Assets.BlueBrick = ImageIO.read(getClass().getResource("/resources/BlueBrick.png"));
			Assets.fog = ImageIO.read(getClass().getResource("/resources/fog.png"));
			Assets.racquet = ImageIO.read(getClass().getResource("/resources/racquet.png"));
			Assets.bonus_plus = ImageIO.read(getClass().getResource("/resources/bonus_plus.png"));
			Assets.bonus_minus = ImageIO.read(getClass().getResource("/resources/bonus_minus.png"));
			Assets.bonus_3 = ImageIO.read(getClass().getResource("/resources/bonus_3.png"));
			Assets.racquet_long = ImageIO.read(getClass().getResource("/resources/racquet_long.png"));
			Assets.racquet_short = ImageIO.read(getClass().getResource("/resources/racquet_short.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		InputStream is = getClass().getResourceAsStream("/resources/levels.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			line = br.readLine();
		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assets.levels = sb.toString();

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			try {
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/LazenbyCompSmooth.ttf")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		Assets.font40 = new Font("Lazenby Computer", Font.PLAIN, 40);
		Assets.font30 = new Font("Lazenby Computer", Font.PLAIN, 30);
		Assets.font20 = new Font("Lazenby Computer", Font.PLAIN, 20);
		Assets.font10 = new Font("Lazenby Computer", Font.PLAIN, 10);
	}


}
