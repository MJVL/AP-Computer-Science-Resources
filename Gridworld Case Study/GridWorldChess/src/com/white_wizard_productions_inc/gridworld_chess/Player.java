package com.white_wizard_productions_inc.gridworld_chess;

import java.awt.Color;

import com.white_wizard_productions_inc.gridworld_chess.pieces.AbstractPiece;
import com.white_wizard_productions_inc.gridworld_chess.pieces.Bishop;
import com.white_wizard_productions_inc.gridworld_chess.pieces.King;
import com.white_wizard_productions_inc.gridworld_chess.pieces.Knight;
import com.white_wizard_productions_inc.gridworld_chess.pieces.Pawn;
import com.white_wizard_productions_inc.gridworld_chess.pieces.Queen;
import com.white_wizard_productions_inc.gridworld_chess.pieces.Rook;

public class Player {

	private AbstractPiece[] pieces;

	public Player(Color color) {
		this.pieces = new AbstractPiece[16];
		if (color == Color.BLACK) {
			for (int i = 0; i < 8; ++i) {
				pieces[i] = new Pawn(color);
			}
			pieces[8] = new Rook(color);
			pieces[9] = new Knight(color);
			pieces[10] = new Bishop(color);
			pieces[11] = new Queen(color);
			pieces[12] = new King(color);
			pieces[13] = new Bishop(color);
			pieces[14] = new Knight(color);
			pieces[15] = new Rook(color);
		} else {
			pieces[0] = new Rook(color);
			pieces[1] = new Knight(color);
			pieces[2] = new Bishop(color);
			pieces[3] = new Queen(color);
			pieces[4] = new King(color);
			pieces[5] = new Bishop(color);
			pieces[6] = new Knight(color);
			pieces[7] = new Rook(color);
			for (int i = 8; i < 16; ++i) {
				pieces[i] = new Pawn(color);
			}
		}
	}

	public AbstractPiece[] getPieces() {
		return pieces;
	}

}
