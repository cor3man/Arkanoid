package com.alis.games.arkanoid.menu;

import com.alis.games.arkanoid.ArkanoidGame;
import com.alis.games.arkanoid.GameState;

import java.awt.event.MouseEvent;

/**
 * Created by cor3man on 29.11.2016.
 */
public class MenuFactory {
	private GameMenu gameMenu;

	public MenuFactory(ArkanoidGame arkanoidGame, MenuType menuType, String[] menuList) {
		switch (menuType) {
			case MainMenu:
				gameMenu = new GameMenu(arkanoidGame, menuList) {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (currentChoice.equals("New game")) {
							arkanoidGame.restartGame();
							arkanoidGame.setState(GameState.Ready);
							return;
						}
						if (currentChoice.equals("exit")) {
							System.exit(0); // wrong!!! frame.dispose()
							return;
						}
					}
				};
				break;
			case GameOverMenu:
				gameMenu = new GameMenu(arkanoidGame, menuList) {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (currentChoice.equals("Play again")) {
							arkanoidGame.restartGame();
							arkanoidGame.setState(GameState.Ready);
							return;
						}
						if (currentChoice.equals("main Menu")) {
							arkanoidGame.restartGame();
							arkanoidGame.setState(GameState.MainMenu);
							return;
						}
					}
				};
				break;
			case PauseMenu:
				gameMenu = new GameMenu(arkanoidGame, menuList) {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (currentChoice.equals("resume")) {
							arkanoidGame.setState(GameState.Running);
							return;
						}
						if (currentChoice.equals("restart")) {
							arkanoidGame.restartGame();
							arkanoidGame.setState(GameState.Ready);
							return;
						}

						if (currentChoice.equals("main Menu")) {
							arkanoidGame.restartGame();
							arkanoidGame.setState(GameState.MainMenu);
							return;
						}
					}
				};
				break;
		}
	}

	public GameMenu getMenu() {
		return gameMenu;
	}

}
