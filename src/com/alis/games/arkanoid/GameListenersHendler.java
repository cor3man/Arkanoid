package com.alis.games.arkanoid;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by cor3man on 25.10.2016.
 */
public class GameListenersHendler {
	private Game game;
	private ArkanoidGame arkanoidGame;

	public GameListenersHendler (ArkanoidGame arkanoidGame){
		this.arkanoidGame = arkanoidGame;
		this.game = arkanoidGame.getGame();
	}

	public void setGameListener() {
		game.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if ((arkanoidGame.getState() == GameState.Running) || (arkanoidGame.getState() == GameState.Ready)) {
					arkanoidGame.getRacquet().setX( e.getX() - arkanoidGame.getRacquet().getWidth() / 2 );
				}

				if (arkanoidGame.getState() == GameState.Paused){
					arkanoidGame.getPauseScreen().mouseMoved(e);
				}

				if (arkanoidGame.getState() == GameState.GameOver){
					arkanoidGame.getGameOverScreen().mouseMoved(e);
				}

				if (arkanoidGame.getState() == GameState.MainMenu){
					arkanoidGame.getMainMenuScreen().mouseMoved(e);
				}
			}
		});

		game.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (arkanoidGame.getState() == GameState.Paused) {
					arkanoidGame.getPauseScreen().mouseClicked(e);
					arkanoidGame.getBall().resetOldTime();
					arkanoidGame.getBonusManager().resetOldTime();
					return;
				}

				if (arkanoidGame.getState() == GameState.MainMenu) {
					arkanoidGame.getMainMenuScreen().mouseClicked(e);
					arkanoidGame.getBall().resetOldTime();
					arkanoidGame.getBonusManager().resetOldTime();
					return;
				}

				if (arkanoidGame.getState() == GameState.GameOver) {
					arkanoidGame.getGameOverScreen().mouseClicked(e);
					arkanoidGame.getBall().resetOldTime();
					arkanoidGame.getBonusManager().clear();
					return;
				}

				if (arkanoidGame.getState() == GameState.Ready) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						arkanoidGame.setState(GameState.Running);
						arkanoidGame.getBall().resetOldTime();
						return;
					}
				}
				if (arkanoidGame.getState() == GameState.Running) {
					if (e.getButton() == MouseEvent.BUTTON3) arkanoidGame.setState(GameState.Paused);
					return;
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
	}




}
