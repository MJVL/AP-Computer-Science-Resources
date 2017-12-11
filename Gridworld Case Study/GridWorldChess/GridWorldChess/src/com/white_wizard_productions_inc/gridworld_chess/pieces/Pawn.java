package com.white_wizard_productions_inc.gridworld_chess.pieces;

import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.white_wizard_productions_inc.gridworld_chess.ChessBoard;
import com.white_wizard_productions_inc.gridworld_chess.ChessWorld;

public class Pawn extends AbstractPiece {

	public static final int value = 1;

	public Pawn(Color color) {
		super(value, color);
	}

	public void changeThePiece() {
		if (testToChange()) {
			String selection = (String) JOptionPane.showInputDialog(null,
					"Select your piece\n", "Promotion", JOptionPane.OK_OPTION,
					null, new String[] { "Bishop", "Knight", "Rook", "Queen" },
					"Queen");
			AbstractPiece piece = null;
			if (selection.equals("Queen")) {
				piece = new Queen(this.getColor());
			} else if (selection.equals("Rook")) {
				piece = new Rook(this.getColor());
			} else if (selection.equals("Bishop")) {
				piece = new Bishop(this.getColor());
			} else if (selection.equals("Knight")) {
				piece = new Knight(this.getColor());
			}
			Location thisLocation = this.getLocation();
			piece.putSelfInGrid(this.getGrid(), thisLocation);
			int id = 0;
			if (this.getColor() == Color.BLACK) {
				id = 1;
			}
			// find the removed piece, and replace it with the new piece
			AbstractPiece[] pieces = ChessWorld.getPlayers()[id].getPieces();
			for (int i = 0; i < pieces.length; i++) {
				if (pieces[i].getLocation() == null) {
					pieces[i] = piece;
					return;
				}
			}
		}
	}

	public boolean testToChange() {
		if (this.getColor() == Color.BLACK && this.getLocation().getRow() == 7) {
			return true;
		}
		if (this.getColor() == Color.WHITE && this.getLocation().getRow() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public LinkedList<Location> getMoveLocations(ChessBoard board) {
		LinkedList<Location> locations = new LinkedList<Location>();
		int direction = Location.NORTH;
		if (this.getColor() == Color.BLACK) {
			direction = Location.SOUTH;
		}
		if (board.isValid(this.getLocation().getAdjacentLocation(direction))
				&& this.getGrid().get(
						this.getLocation().getAdjacentLocation(direction)) == null) {
			locations.add(this.getLocation().getAdjacentLocation(direction));
		}
		if (board.isValid(this.getLocation().getAdjacentLocation(
				direction + Location.HALF_LEFT))
				&& this.getGrid().get(
						this.getLocation().getAdjacentLocation(
								direction + Location.HALF_LEFT)) != null
				&& isOpponentPiece((AbstractPiece) this.getGrid().get(
						this.getLocation().getAdjacentLocation(
								direction + Location.HALF_LEFT)))) {
			locations.add(this.getLocation().getAdjacentLocation(
					direction + Location.HALF_LEFT));
		}
		if (board.isValid(this.getLocation().getAdjacentLocation(
				direction + Location.HALF_RIGHT))
				&& this.getGrid().get(
						this.getLocation().getAdjacentLocation(
								direction + Location.HALF_RIGHT)) != null
				&& isOpponentPiece((AbstractPiece) this.getGrid().get(
						this.getLocation().getAdjacentLocation(
								direction + Location.HALF_RIGHT)))) {
			locations.add(this.getLocation().getAdjacentLocation(
					direction + Location.HALF_RIGHT));
		}
		if (this.getColor() == Color.BLACK && this.getLocation().getRow() == 1) {
			if (this.getGrid().isValid(
					new Location(this.getLocation().getRow() + 2, this
							.getLocation().getCol()))) {
				locations.add(new Location(this.getLocation().getRow() + 2,
						this.getLocation().getCol()));
			}
		} else if (this.getColor() == Color.WHITE
				&& this.getLocation().getRow() == 6) {
			if (board.isValid(new Location(this.getLocation().getRow() - 2,
					this.getLocation().getCol()))) {
				locations.add(new Location(this.getLocation().getRow() - 2,
						this.getLocation().getCol()));
			}
		}
		return locations;
	}
}
