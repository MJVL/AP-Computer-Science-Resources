package com.white_wizard_productions_inc.gridworld_chess.pieces;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import com.white_wizard_productions_inc.gridworld_chess.ChessBoard;

public abstract class AbstractPiece extends Actor {

	public static final int SAFE = 0;
	public static final int UNSAFE = 1;
	private final int value;

	public AbstractPiece(int value, Color color) {
		this.value = value;
		this.setColor(color);
	}

	public boolean isOpponentPiece(AbstractPiece piece) {
		if (piece == null) {
			return false;
		}
		return this.getColor() != piece.getColor();
	}

	public boolean isPlayerPiece(AbstractPiece piece) {
		if (piece == null) {
			return false;
		}
		return this.getColor() == piece.getColor();
	}

	public boolean endangersKing(Location moveLocation) {
		ChessBoard tempBoard = new ChessBoard();
		// copy the board
		for (Location location : this.getGrid().getOccupiedLocations()) {
			tempBoard.put(location, (AbstractPiece) this.getGrid()
					.get(location));
		}
		// move the piece on the temporary board
		tempBoard.put(moveLocation, (AbstractPiece) tempBoard.get(this
				.getLocation()));
		tempBoard.remove(this.getLocation());
		if (this instanceof King) {
			return ((King) this).isInCheck(tempBoard);
		} else {
			King king = findKing(tempBoard);
			return king.isInCheck(tempBoard);
		}
	}

	public King findKing(ChessBoard board) {
		for (Location location : board.getOccupiedLocations()) {
			if (isPlayerPiece((AbstractPiece) board.get(location))
					&& ((AbstractPiece) board.get(location) instanceof King)) {
				return ((King) board.get(location));
			}
		}
		return null;
	}

	public Location findKingLocation(ChessBoard board) {
		for (Location location : board.getOccupiedLocations()) {
			if (isPlayerPiece((AbstractPiece) board.get(location))
					&& ((AbstractPiece) board.get(location) instanceof King)) {
				return location;
			}
		}
		return null;
	}

	public int getState(Location moveLocation, ChessBoard board) {
		// if the location is any of the opponent's valid move locations
		// it is an unsafe location, otherwise is safe
		for (Location opponentMoveLocation : this
				.getOpponentMoveLocations(board)) {
			if (moveLocation.equals(opponentMoveLocation)) {
				return UNSAFE;
			}
		}
		return SAFE;
	}

	public void act() {

	}

	public String getImageSuffix() {
		String output = "";
		if (getColor() == Color.BLACK) {
			output += "_black";
		} else if (getColor() == Color.WHITE) {
			output += "_white";
		}
		/*
		 * if (getState(getLocation()) == UNSAFE) { output += "_unsafe"; }
		 */
		return output;
	}

	public ArrayList<AbstractPiece> getOpponentPieces(ChessBoard board) {
		ArrayList<AbstractPiece> playerPieces = new ArrayList<AbstractPiece>();
		for (Location occupiedLocation : board.getOccupiedLocations()) {
			if (isOpponentPiece(((AbstractPiece) board.get(occupiedLocation)))) {
				playerPieces.add((AbstractPiece) board.get(occupiedLocation));
			}
		}
		return playerPieces;
	}

	public ArrayList<AbstractPiece> getPlayerPieces(ChessBoard board) {
		ArrayList<AbstractPiece> playerPieces = new ArrayList<AbstractPiece>();
		for (Location occupiedLocation : board.getOccupiedLocations()) {
			if (isPlayerPiece(((AbstractPiece) board.get(occupiedLocation)))) {
				playerPieces.add((AbstractPiece) board.get(occupiedLocation));
			}
		}
		return playerPieces;
	}

	public Set<Location> getOpponentMoveLocations(ChessBoard board) {
		Set<Location> opponentMoveLocations = new HashSet<Location>();
		for (AbstractPiece opponentPiece : getOpponentPieces(board)) {
			LinkedList<Location> potentialOpponentMoveLocations;
			if (opponentPiece instanceof Pawn) {
				potentialOpponentMoveLocations = new LinkedList<Location>(
						((Pawn) opponentPiece).getDiagMoves(board));
			} else {
				potentialOpponentMoveLocations = new LinkedList<Location>(
						opponentPiece.getMoveLocations(board));
			}
			// add the opponent move locations to the set
			for (Location opponentMoveLocation : potentialOpponentMoveLocations) {
				opponentMoveLocations.add(opponentMoveLocation);
			}
		}
		return opponentMoveLocations;
	}

	public Set<Location> getPlayerMoveLocations(ChessBoard board) {
		Set<Location> playerMoveLocations = new HashSet<Location>();
		for (AbstractPiece opponentPiece : getOpponentPieces(board)) {
			LinkedList<Location> potentialPlayerMoveLocations = new LinkedList<Location>(
					opponentPiece.getMoveLocations(board));
			// add the player move locations to the set
			for (Location opponentMoveLocation : potentialPlayerMoveLocations) {
				playerMoveLocations.add(opponentMoveLocation);
			}
		}
		return playerMoveLocations;
	}

	public LinkedList<Location> getValidMoveLocations(ChessBoard board) {
		LinkedList<Location> validLocations = new LinkedList<Location>(this
				.getMoveLocations(board));
		for (ListIterator<Location> listIterator = validLocations
				.listIterator(); listIterator.hasNext();) {
			if (endangersKing(listIterator.next())) {
				listIterator.remove();
			}
		}
		return validLocations;
	}

	public abstract LinkedList<Location> getMoveLocations(ChessBoard board);

	public int getValue() {
		return value;
	}

}
