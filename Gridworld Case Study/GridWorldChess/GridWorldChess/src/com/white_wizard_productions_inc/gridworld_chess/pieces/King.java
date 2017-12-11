package com.white_wizard_productions_inc.gridworld_chess.pieces;

import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.white_wizard_productions_inc.gridworld_chess.ChessBoard;

public class King extends AbstractPiece {

	public static final int CHECK = 2;
	public static final int CHECK_MATE = 3;
	public static final int value = Integer.MAX_VALUE;

	private boolean hasMoved = false;
	private boolean hasChecked = false;

	public boolean isMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	public boolean isChecked() {
		return hasChecked;
	}

	public King(Color color) {
		super(value, color);
	}

	public boolean canCastle() {
		boolean temp = (!hasMoved && !hasChecked);
		if (!temp) {
			return temp;
		}
		if ((this.getGrid().get(
				new Location(this.getLocation().getRow(), this.getLocation()
						.getCol() - 1)) == null)
				&& (this.getGrid().get(
						new Location(this.getLocation().getRow(), this
								.getLocation().getCol() - 2)) == null)) {
			temp = true;
			return temp;
		} else if ((this.getGrid().get(
				new Location(this.getLocation().getRow(), this.getLocation()
						.getCol() - 1)) == null)
				&& (this.getGrid().get(
						new Location(this.getLocation().getRow(), this
								.getLocation().getCol() - 2)) == null)) {
			temp = true;
			return temp;
		}
		return false;
	}

	public int getState(Location moveLocation, ChessBoard board) {
		Set<Location> opponentMoveLocations = new HashSet<Location>();
		ArrayList<AbstractPiece> opponentPieces = new ArrayList<AbstractPiece>();
		// find the opponent's pieces
		for (Location occupiedLocation : board.getOccupiedLocations()) {
			// don't add the piece unless it is the opponent's color
			if (isOpponentPiece(((AbstractPiece) board.get(occupiedLocation)))) {
				opponentPieces.add((AbstractPiece) board.get(occupiedLocation));
			}
		}
		// find all the move locations of the opponent pieces
		for (AbstractPiece opponentPiece : opponentPieces) {
			LinkedList<Location> potentialOpponentMoveLocations = new LinkedList<Location>();
			potentialOpponentMoveLocations = opponentPiece
					.getMoveLocations(board);
			// add the opponent move locations to the set
			for (Location opponentMoveLocation : potentialOpponentMoveLocations) {
				opponentMoveLocations.add(opponentMoveLocation);
			}
		}
		// if the location is any of the opponent's valid move locations
		// it is a location check or check mate, otherwise is safe
		for (Location opponentMoveLocation : opponentMoveLocations) {
			if (moveLocation.equals(opponentMoveLocation)) {
				if (isInCheckmate(board)) {
					return CHECK_MATE;
				}
				return CHECK;
			}
		}
		return SAFE;
	}

	public boolean isInCheck(ChessBoard board) {
		Set<Location> opponentMoveLocations = new HashSet<Location>();
		ArrayList<AbstractPiece> opponentPieces = new ArrayList<AbstractPiece>();
		// find the opponent's pieces
		for (Location occupiedLocation : board.getOccupiedLocations()) {
			// don't add the piece unless it is the opponent's color
			if (isOpponentPiece(((AbstractPiece) board.get(occupiedLocation)))) {
				opponentPieces.add((AbstractPiece) board.get(occupiedLocation));
			}
		}
		// find all the move locations of the opponent pieces
		for (AbstractPiece opponentPiece : opponentPieces) {
			LinkedList<Location> potentialOpponentMoveLocations = new LinkedList<Location>();
			potentialOpponentMoveLocations = opponentPiece
					.getMoveLocations(board);
			// add the opponent move locations to the set
			for (Location opponentMoveLocation : potentialOpponentMoveLocations) {
				opponentMoveLocations.add(opponentMoveLocation);
			}
		}
		Location kingLocation = this.findKingLocation(board);
		for (Location opponentMoveLocation : opponentMoveLocations) {
			if (kingLocation.equals(opponentMoveLocation)) {
				return true;
			}
		}
		return false;
	}

	public boolean isInCheckmate(ChessBoard board) {
		Set<Location> playerMoveLocations = new HashSet<Location>();
		ArrayList<AbstractPiece> playerPieces = new ArrayList<AbstractPiece>();
		// find the opponent's pieces
		for (Location occupiedLocation : board.getOccupiedLocations()) {
			// don't add the piece unless it is the player's piece
			if (isPlayerPiece(((AbstractPiece) board.get(occupiedLocation)))) {
				playerPieces.add((AbstractPiece) board.get(occupiedLocation));
			}
		}
		// find all the move locations of the opponent pieces
		for (AbstractPiece playerPiece : playerPieces) {
			LinkedList<Location> potentialPlayerMoveLocations = new LinkedList<Location>();
			potentialPlayerMoveLocations = playerPiece.getMoveLocations(board);
			// add the opponent move locations to the set
			for (Location playerMoveLocation : potentialPlayerMoveLocations) {
				boolean endangerment = playerPiece
						.endangersKing(playerMoveLocation);
				if (!endangerment) {
					playerMoveLocations.add(playerMoveLocation);
				}
			}
		}
		return playerMoveLocations.isEmpty();
	}

	public boolean castleCheck(Location loc, ChessBoard board) {
		Set<Location> opponentMoveLocations = new HashSet<Location>();
		ArrayList<AbstractPiece> opponentPieces = new ArrayList<AbstractPiece>();
		// find the opponent's pieces
		for (Location occupiedLocation : board.getOccupiedLocations()) {
			// don't add the piece unless it is the opponent's color
			if (isOpponentPiece(((AbstractPiece) board.get(occupiedLocation)))) {
				opponentPieces.add((AbstractPiece) board.get(occupiedLocation));
			}
		}
		// find all the move locations of the opponent pieces
		for (AbstractPiece opponentPiece : opponentPieces) {
			LinkedList<Location> potentialOpponentMoveLocations = new LinkedList<Location>();
			potentialOpponentMoveLocations = opponentPiece
					.getMoveLocations(board);
			// add the opponent move locations to the set
			for (Location opponentMoveLocation : potentialOpponentMoveLocations) {
				opponentMoveLocations.add(opponentMoveLocation);
			}
		}
		Location kingLocation = loc;
		for (Location opponentMoveLocation : opponentMoveLocations) {
			if (kingLocation.equals(opponentMoveLocation)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public LinkedList<Location> getMoveLocations(ChessBoard board) {
		LinkedList<Location> locations = new LinkedList<Location>();
		for (Location validLocation : board.getValidAdjacentLocations(this
				.getLocation())) {
			if (board.get(validLocation) == null
					|| isOpponentPiece((AbstractPiece) board.get(validLocation))) {
				locations.add(validLocation);
			}
		}

		if (canCastle()) {
			Location[] castleLocations = new Location[] {
					new Location(this.getLocation().getRow(), this
							.getLocation().getCol() - 2),
					new Location(this.getLocation().getRow(), this
							.getLocation().getCol() + 2) };
			int direction = -1;
			for (Location castleLocation : castleLocations) {
				if (castleLocation.equals(new Location(this.getLocation()
						.getRow(), this.getLocation().getCol() + 2))) {
					direction = 1;
				}
				if (board.isValid(castleLocation)
						&& board.get(castleLocation) == null
						&& (!castleCheck(new Location(this.getLocation()
								.getRow(), this.getLocation().getCol()
								+ direction), board))
						&& (!castleCheck(new Location(this.getLocation()
								.getRow(), this.getLocation().getCol() + 2
								* direction), board)))
					locations.add(castleLocation);
			}
		}

		return locations;
	}
}
