package binding_of_erik_game;

import java.awt.event.KeyEvent;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;

/**
 * Created by wassing on 2016-04-06.
 */

public class EventHandler extends JComponent implements BoardListener {

	private boolean testing = false;

	private static final int SQUARE_WIDTH = 800; //graphical size of the actual GameFrame
	private static final int SQUARE_HEIGHT = 800;
	private static final int TIMER_DELAY = 50;

	private Timer upTimer = null;
	private Timer downTimer = null;
	private Timer rightTimer = null;
	private Timer leftTimer = null;

	private final Room room;
	private final AbstractMap<TileType, Color> map = TileType.eMap();

	public EventHandler(Room room) {
		this.room = room;
		this.setPreferredSize(getPreferredSize());
		room.addBoardListener(this);

		// All key binds in the game
		getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "pressedSpace");
		getActionMap().put("pressedSpace", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventHandler.this.room.fireShot("StraightShot");
			}
		});

		getInputMap().put(KeyStroke.getKeyStroke("C"), "pressedC");
		getActionMap().put("pressedC", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventHandler.this.room.fireShot("StrafeShots");
			}
		});

		getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "pressedEscape");
		getActionMap().put("pressedEscape", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventHandler.this.room.score.saveScore();
				EventHandler.this.room.score.writeHighscore();  // Save current score
				System.exit(0);
			}
		});

		getInputMap().put(KeyStroke.getKeyStroke("P"), "pressedP");
		getActionMap().put("pressedP", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameFrame.togglePaused();
				System.out.println("Paused.");
			}
		});

		getInputMap().put(KeyStroke.getKeyStroke("T"), "pressedT");
		getActionMap().put("pressedT", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				testing = !testing;
				if (testing) {
					System.out.println("Testing enabled.");
				} else {
					System.out.println("Testing disabled.");
				}
			}
		});

		getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pressedEnter");
		getActionMap().put("pressedEnter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (testing) {
					EventHandler.this.room.newRoom();
					System.out.println("New room spawned.");
				}
			}
		});

		getInputMap().put(KeyStroke.getKeyStroke("R"), "pressedR");
		getActionMap().put("pressedR", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (testing) {
					System.out.println("Room reset.");
					EventHandler.this.room.resetRoom();
				}
			}
		});

		KeyStroke upKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
		KeyStroke upKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true);
		KeyStroke downKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);
		KeyStroke downKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true);
		KeyStroke rightKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
		KeyStroke rightKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
		KeyStroke leftKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
		KeyStroke leftKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);

		getInputMap().put(upKeyPressed, "up key pressed");
		getInputMap().put(upKeyReleased, "up key released");

		getActionMap().put("up key pressed", new UpAction(false));
		getActionMap().put("up key released", new UpAction(true));

		getInputMap().put(downKeyPressed, "down key pressed");
		getInputMap().put(downKeyReleased, "down key released");

		getActionMap().put("down key pressed", new DownAction(false));
		getActionMap().put("down key released", new DownAction(true));

		getInputMap().put(rightKeyPressed, "right key pressed");
		getInputMap().put(rightKeyReleased, "right key released");

		getActionMap().put("right key pressed", new RightAction(false));
		getActionMap().put("right key released", new RightAction(true));

		getInputMap().put(leftKeyPressed, "left key pressed");
		getInputMap().put(leftKeyReleased, "left key released");

		getActionMap().put("left key pressed", new LeftAction(false));
		getActionMap().put("left key released", new LeftAction(true));

	}

	private final class UpAction extends AbstractAction {
		private boolean onKeyRelease;

		private UpAction(boolean onKeyRelease) {
			this.onKeyRelease = onKeyRelease;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			if (!onKeyRelease) {
				if (upTimer != null && upTimer.isRunning()) {
					return;
				}
				EventHandler.this.room.movePlayer(Direction.NORTH);

				upTimer = new Timer(TIMER_DELAY, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						EventHandler.this.room.movePlayer(Direction.NORTH);
					}
				});
				upTimer.start();
			} else {
				EventHandler.this.room.movePlayer(Direction.OTHER);
				if (upTimer != null && upTimer.isRunning()) {
					upTimer.stop();
					upTimer = null;
				}
			}
		}
	}

	private final class DownAction extends AbstractAction {
		private boolean onKeyRelease;

		private DownAction(boolean onKeyRelease) {
			this.onKeyRelease = onKeyRelease;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			if (!onKeyRelease) {
				if (downTimer != null && downTimer.isRunning()) {
					return;
				}
				EventHandler.this.room.movePlayer(Direction.SOUTH);

				downTimer = new Timer(TIMER_DELAY, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						EventHandler.this.room.movePlayer(Direction.SOUTH);
					}
				});
				downTimer.start();
			} else {
				EventHandler.this.room.movePlayer(Direction.OTHER);
				if (downTimer != null && downTimer.isRunning()) {
					downTimer.stop();
					downTimer = null;
				}
			}
		}
	}

	private final class RightAction extends AbstractAction {
		private boolean onKeyRelease;

		private RightAction(boolean onKeyRelease) {
			this.onKeyRelease = onKeyRelease;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			if (!onKeyRelease) {
				if (rightTimer != null && rightTimer.isRunning()) {
					return;
				}
				EventHandler.this.room.movePlayer(Direction.EAST);

				rightTimer = new Timer(TIMER_DELAY, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						EventHandler.this.room.movePlayer(Direction.EAST);
					}
				});
				rightTimer.start();
			} else {
				EventHandler.this.room.movePlayer(Direction.OTHER);
				if (rightTimer != null && rightTimer.isRunning()) {
					rightTimer.stop();
					rightTimer = null;
				}
			}
		}
	}

	private final class LeftAction extends AbstractAction {
		private boolean onKeyRelease;

		private LeftAction(boolean onKeyRelease) {
			this.onKeyRelease = onKeyRelease;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			if (!onKeyRelease) {
				if (leftTimer != null && leftTimer.isRunning()) {
					return;
				}
				EventHandler.this.room.movePlayer(Direction.WEST);

				leftTimer = new Timer(TIMER_DELAY, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						EventHandler.this.room.movePlayer(Direction.WEST);
					}
				});
				leftTimer.start();
			} else {
				EventHandler.this.room.movePlayer(Direction.OTHER);
				if (leftTimer != null && leftTimer.isRunning()) {
					leftTimer.stop();
					leftTimer = null;
				}
			}
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(SQUARE_WIDTH, SQUARE_HEIGHT);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < room.getWidth(); i++) {
			for (int j = 0; j < room.getHeight(); j++) {
				TileType square = room.getSquare(i, j);
				if (square != TileType.TRANSPARENT) {
					g2.setColor(map.get(square));
				} else {
					g2.setColor(Color.black);
				}
				g2.fillRect(i * room.getPixelWidthPerTile(), j * room.getPixelHeightPerTile(),
						room.getPixelWidthPerTile(), room.getPixelHeightPerTile());
			}
		}
	}

	public void boardChanged() {
		repaint();
	}
}