package com.alis.games.arkanoid;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends JPanel {

	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;

	private static BufferedImage drawing;
	private static ArkanoidGame arkanoidGame;

	public Game() {
		setOpaque(true);
		setFocusable(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.drawImage(drawing, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Arkanoid");
		Game game = new Game();

		JPanel cp = new JPanel(new BorderLayout());

		cp.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(5, 5, 5, 5),
				BorderFactory.createLineBorder(Color.BLACK)));

		cp.add(game, BorderLayout.CENTER);
		cp.setBackground(Color.BLACK);
		frame.setContentPane(cp);
	

		try {
			frame.setIconImage(ImageIO.read(game.getClass().getResource(
					"/resources/arkanoid.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		drawing = new BufferedImage(game.getWidth(), game.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		arkanoidGame = new ArkanoidGame(game);

	
		while (true) {
			arkanoidGame.update(drawing);
			game.repaint();
			Thread.sleep(15);
		}
	}

}