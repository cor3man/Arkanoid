package com.alis.games.arkanoid;

import com.alis.games.arkanoid.bonuses.BonusManager;
import com.alis.games.arkanoid.bricks.BricksManager;
import com.alis.games.arkanoid.menu.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cor3man on 25.10.2016.
 */
public class ArkanoidGame {

	public static final int NAX_NUMBER_OF_LIVES = 4;
	public static final int MAX_NUMBER_OF_BALLS = 3;

	GameState state = GameState.MainMenu;

	String[] pauseList = new String[] {"Paused", "resume", "main Menu", "options", "restart"};
	String[] gameOverList = new String[] {"Game Over", "main Menu", "Play again"};
	String[] mainMenuList = new String[] {"ARCANOID DEMO", "New game", "Options", "High scores", "exit"};


	private int numberOfBalls = NAX_NUMBER_OF_LIVES;
	private int width;
	private int height;
	private int actionAreaWidth;
	private int actionAreaHeight;
	private GameListenersHendler listeners;
	private Game game;
	private GameMenu pauseScreen;
	private GameMenu gameOverScreen;
	private GameMenu mainMenuScreen;
	private Racquet racquet;
	private BonusManager bonusManager;
	private Ball ball;
	private int scores;
	private RightPanel rightPanel;
	private BricksManager bricksManager;

	public ArkanoidGame(Game game) {
		this.game = game;
		this.width = game.getWidth();
		this.height = game.getHeight();
		actionAreaHeight = height;
		actionAreaWidth = height;

		new AssetManager();
		rightPanel = new RightPanel(this);
		bonusManager = new BonusManager(this);
		racquet = new Racquet(this);
		racquet.setWidth(Assets.racquet.getWidth());
		racquet.setHeight(Assets.racquet.getHeight());
		new Ball(this, MAX_NUMBER_OF_BALLS);
		ball = Ball.addOriginBall();
		bricksManager = new BricksManager(this);

		mainMenuScreen = new MenuFactory(this, MenuType.MainMenu, mainMenuList).getMenu();
		gameOverScreen = new MenuFactory(this, MenuType.GameOverMenu, gameOverList).getMenu();
		pauseScreen = new MenuFactory(this, MenuType.PauseMenu, pauseList).getMenu();

		listeners = new GameListenersHendler(this);
		listeners.setGameListener();

	}

	public void nextLevel(int level){
		bonusManager.clear();
		bricksManager.createField(level);
		racquet = new Racquet(this);
		ball = Ball.addOriginBall();
		numberOfBalls = MAX_NUMBER_OF_BALLS;
		setState(GameState.Ready);
	}

	public void restartGame(){
		bonusManager.clear();
		bricksManager.createField(1);
		racquet = new Racquet(this);
		ball = Ball.addOriginBall();
		numberOfBalls = MAX_NUMBER_OF_BALLS;
		scores = 0;
	}

	public void update(BufferedImage drawing){

		Graphics2D g = drawing.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);

		if (state == GameState.Running){
			updateRunning(g);
			drawHelpNote(g, "Right click to PAUSE");
			Ball.move();
			bonusManager.move();
			
		}
		if (state == GameState.Ready) {
			updateRunning(g);
			drawHelpNote(g, "Left click to START");
			ball.moveBallOnReady();	
		}
		if (state == GameState.Paused) updatePaused(g);
		if (state == GameState.GameOver) updateGameOver(g);
		if (state == GameState.MainMenu) updateMainMenu(g);

		g.dispose();
	}

	public void updateRunning(Graphics2D g) {
		g.drawImage(Assets.background, 0, 0, null);
		racquet.draw(g);
		rightPanel.draw(g);
		ball.draw(g);
		bricksManager.draw(g);
		bonusManager.draw(g);
	}

	public void drawHelpNote(Graphics2D g, String str) {
		g.setFont(Assets.font10);
		g.setColor(new Color(215, 215, 215));
		g.drawString(str, 10, height - 5);
	}

	public void updatePaused(Graphics2D g) {
		updateRunning(g);
		pauseScreen.printScreen(g);
	}

	public void updateMainMenu(Graphics2D g) {
		updateRunning(g);
		mainMenuScreen.printScreen(g);
	}

	public void updateGameOver(Graphics2D g) {
		updateRunning(g);
		gameOverScreen.printScreen(g);
	}

	public void decreaseNumberOfBalls() {
		this.numberOfBalls --;
		if (this.numberOfBalls == 0) state = GameState.GameOver;
	}

	public void incScores(int score) {
		this.scores += score;
	}

// getters and setters

	public void setState(GameState state) {
		this.state = state;
	}

	public GameState getState() {
		return state;
	}

	public BonusManager getBonusManager(){
		return bonusManager;
	}
	public int getScores() {
		return scores;
	}

	public int getNumberOfBalls() {
		return numberOfBalls;
	}

	public GameMenu getPauseScreen() {
		return pauseScreen;
	}

	public GameMenu getGameOverScreen() {
		return gameOverScreen;
	}

	public GameMenu getMainMenuScreen() {
		return mainMenuScreen;
	}

	public BricksManager getBricksManager(){
		return bricksManager;
	}

	public Ball getBall() {
		return ball;
	}

	public int getActionAreaWidth() {
		return actionAreaWidth;
	}

	public int getActionAreaHeight() {
		return actionAreaHeight;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Racquet getRacquet() {
		return racquet;
	}

	public Game getGame() {
		return game;
	}
}
