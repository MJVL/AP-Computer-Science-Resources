package com.white_wizard_productions_inc.gridworld_chess.pieces;

import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.LinkedList;

import com.white_wizard_productions_inc.gridworld_chess.ChessBoard;

public class Rook extends AbstractPiece {

	public static final int value = 5;

	private boolean hasMoved = false;

	public boolean isMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	public Rook(Color color) {
		super(value, color);
	}

	public void castleIt(ChessBoard board) {
		Location kingLocation = this.findKingLocation(board);
		if (this.getLocation().getCol() < kingLocation.getCol()) {
			Location newLocation = new Location(this.getLocation().getRow(),
					this.getLocation().getCol() + 2);
			this.moveTo(newLocation);
		} else {
			Location newLocation = new Location(this.getLocation().getRow(),
					this.getLocation().getCol() - 2);
			this.moveTo(newLocation);
		}
	}

	@Override
	public LinkedList<Location> getMoveLocations(ChessBoard board) {
		LinkedList<Location> locations = new LinkedList<Location>();
		// start at the Rook's location
		Location location = new Location(this.getLocation().getRow(), this
				.getLocation().getCol());
		// loop through all directions of the board for the Queen
		for (int direction = Location.NORTH; direction < Location.FULL_CIRCLE; direction += Location.RIGHT) {
			// loop through the spaces in a certain direction and add if the
			// space is empty, and is a valid location
			while (board.isValid(location.getAdjacentLocation(direction))
					&& board.get(location.getAdjacentLocation(direction)) == null) {
				locations.add(location.getAdjacentLocation(direction));
				location = new Location(location.getAdjacentLocation(direction)
						.getRow(), location.getAdjacentLocation(direction)
						.getCol());
			}
			// if the space in the same direction, but one space farther, is
			// occupied by a piece of the opposite color, add it
			if (board.isValid(location.getAdjacentLocation(direction))
					&& isOpponentPiece((AbstractPiece) board.get(location
							.getAdjacentLocation(direction)))) {
				locations.add(location.getAdjacentLocation(direction));
			}
			// reset the location back to the Rook's location
			location = new Location(this.getLocation().getRow(), this
					.getLocation().getCol());
		}
		return locations;
	}

}
