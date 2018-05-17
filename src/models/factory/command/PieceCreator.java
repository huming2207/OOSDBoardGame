package models.factory.command;

import models.piece.Piece;

@FunctionalInterface
public interface PieceCreator
{
    Piece createPiece();
}
