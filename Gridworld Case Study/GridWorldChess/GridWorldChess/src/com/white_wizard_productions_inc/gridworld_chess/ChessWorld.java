package com.white_wizard_productions_inc.gridworld_chess;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.white_wizard_productions_inc.gridworld_chess.pieces.AbstractPiece;
import com.white_wizard_productions_inc.gridworld_chess.pieces.King;
import com.white_wizard_productions_inc.gridworld_chess.pieces.Pawn;
import com.white_wizard_productions_inc.gridworld_chess.pieces.Rook;

public class ChessWorld extends ActorWorld {

	private static Player[] players;
	private boolean isClicked;
	private int turn;
	private AbstractPiece currentPiece;
	private boolean gameOver = false;
	private boolean playAgain = true;

	public ChessWorld() {
		players = new Player[] { new Player(Color.WHITE),
				new Player(Color.BLACK) };
		isClicked = false;
		turn = 0;
		currentPiece = null;
	}

	public ChessWorld(Grid<Actor> grid) {
		this();
		this.setGrid(grid);
	}

	public static Player[] getPlayers() {
		return players;
	}

	public boolean locationClicked(Location loc) {
		if (!gameOver) {
			AbstractPiece piece = (AbstractPiece) getGrid().get(loc);
			if (isClicked) {
				isClicked = false;
				if (currentPiece.getValidMoveLocations(
						(ChessBoard) this.getGrid()).contains(loc)) {
					Location prevLoc = currentPiece.getLocation();
					currentPiece.moveTo(loc);
					if (currentPiece instanceof Pawn) {
						((Pawn) currentPiece).changeThePiece();
					} else if (currentPiece instanceof King) {
						((King) currentPiece).setHasMoved(true);
						ArrayList<Location> adjLocs = currentPiece.getGrid()
								.getValidAdjacentLocations(
										currentPiece.getLocation());
						if (!adjLocs.contains(prevLoc)) {
							// he castled
							if (currentPiece.getLocation().getCol() == prevLoc
									.getCol() - 2) {
								((Rook) ((AbstractPiece) currentPiece.getGrid()
										.get(
												new Location(
														currentPiece
																.getLocation()
																.getRow(), 0))))
										.castleIt((ChessBoard) this.getGrid());
							} else {
								((Rook) ((AbstractPiece) currentPiece.getGrid()
										.get(
												new Location(
														currentPiece
																.getLocation()
																.getRow(), 7))))
										.castleIt((ChessBoard) this.getGrid());
							}
						}
					} else if (currentPiece instanceof Rook) {
						((Rook) currentPiece).setHasMoved(true);
					}
					int temp = 0;
					switch (turn) {
					case 0:
						temp = (players[1].getPieces()[0]
								.findKing((ChessBoard) this.getGrid())
								.getState(players[1].getPieces()[0].findKing(
										(ChessBoard) this.getGrid())
										.getLocation(), (ChessBoard) this
										.getGrid()));
						switch (temp) {
						case King.CHECK:
							System.out.println("Black is now in Check");
							break;
						case King.CHECK_MATE:
							gameOver = true;
							System.out
									.println("Black is now in CHECKMATE...GAME OVER"
											+ "\n" + "WHITE WINS");
							break;
						}
						turn++;
						break;
					case 1:
						turn = 0;
						temp = (players[0].getPieces()[0]
								.findKing((ChessBoard) this.getGrid())
								.getState(players[1].getPieces()[0].findKing(
										(ChessBoard) this.getGrid())
										.getLocation(), (ChessBoard) this
										.getGrid()));
						switch (temp) {
						case King.CHECK:
							System.out.println("White is now in Check");
							break;
						case King.CHECK_MATE:
							gameOver = true;
							System.out
									.println("White is now in CHECKMATE...GAME OVER"
											+ "\n" + "WHITE WINS");
							break;
						}
						break;
					}
				}
			} else {
				if (piece != null) {
					switch (turn) {
					case 0:
						if (piece.getColor() != Color.WHITE) {
							return true;
						}
						break;
					case 1:
						if (piece.getColor() != Color.BLACK) {
							return true;
						}
						break;
					}
					isClicked = true;
					currentPiece = piece;
				}

			}
			// System.out.println(turn + ", " + isClicked);

		} else {
			if (playAgain) {
				int n = JOptionPane.showConfirmDialog(null,
						"Do you wish to play again?", "Play again?",
						JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					newGame();
				} else {
					playAgain = false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		ChessWorld chessWorld = new ChessWorld(new ChessBoard());
		int row = 1, col = 0;
		for (int i = 1; i >= 0; i--) {
			for (AbstractPiece piece : players[i].getPieces()) {
				chessWorld.add(new Location(row, col), piece);
				if (col >= 7) {
					col = 0;
					if (row == 1) {
						row--;
					} else {
						row = 6;
					}
				} else {
					col++;
				}
			}
			if (row == 6) {
				row++;
			}
		}
		chessWorld.show();
	}

	public void newGame() {
		gameOver = false;
		players = new Player[] { new Player(Color.WHITE),
				new Player(Color.BLACK) };
		isClicked = false;
		turn = 0;
		currentPiece = null;
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				this.getGrid().remove(new Location(i, j));
			}
		}
		int row = 1, col = 0;
		for (int i = 1; i >= 0; i--) {
			for (AbstractPiece piece : players[i].getPieces()) {
				this.add(new Location(row, col), piece);
				if (col >= 7) {
					col = 0;
					if (row == 1) {
						row--;
					} else {
						row = 6;
					}
				} else {
					col++;
				}
			}
			if (row == 6) {
				row++;
			}
		}
	}
}
