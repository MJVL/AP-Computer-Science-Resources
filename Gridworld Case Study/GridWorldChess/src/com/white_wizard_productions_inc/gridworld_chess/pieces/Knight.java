package com.white_wizard_productions_inc.gridworld_chess.pieces;

import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.LinkedList;

import com.white_wizard_productions_inc.gridworld_chess.ChessBoard;

public class Knight extends AbstractPiece {

	public static final int value = 3;

	public Knight(Color color) {
		super(value, color);
	}

	@Override
	public LinkedList<Location> getMoveLocations(ChessBoard board) {
		LinkedList<Location> locations = new LinkedList<Location>();
		Location[] possibleLocations = {
				new Location(this.getLocation().getRow() - 2, this
						.getLocation().getCol() + 1),
				new Location(this.getLocation().getRow() - 1, this
						.getLocation().getCol() + 2),
				new Location(this.getLocation().getRow() + 1, this
						.getLocation().getCol() + 2),
				new Location(this.getLocation().getRow() + 2, this
						.getLocation().getCol() + 1),
				new Location(this.getLocation().getRow() + 2, this
						.getLocation().getCol() - 1),
				new Location(this.getLocation().getRow() + 1, this
						.getLocation().getCol() - 2),
				new Location(this.getLocation().getRow() - 1, this
						.getLocation().getCol() - 2),
				new Location(this.getLocation().getRow() - 2, this
						.getLocation().getCol() - 1) };
		for (Location location : possibleLocations) {
			// loop through the eight possible locations; if it is a valid
			// location,
			// and it is empty or contains a piece of the opposite color, add it
			// to the list
			if (board.isValid(location)
					&& (board.get(location) == null || isOpponentPiece((AbstractPiece) board
							.get(location)))) {
				locations.add(location);
			}
		}
		return locations;
	}

}
