package models.game.board;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.game.piece.Piece;
import models.game.piece.type.RoleType;
import models.game.player.Player;

public class Board
{
    private Player communismPlayer;
    private Player capitalismPlayer;
    private Player currentPlayer;
    private ObservableList<Piece> pieceList;

    public Board(String communismPlayerName, String capitalismPlayerName)
    {
        this.communismPlayer = new Player(communismPlayerName, RoleType.COMMUNISM_PIECE);
        this.capitalismPlayer = new Player(capitalismPlayerName, RoleType.CAPITALISM_PIECE);
        this.pieceList = FXCollections.observableArrayList();
    }

    public Player getCommunismPlayer()
    {
        return communismPlayer;
    }

    public void setCommunismPlayer(Player communismPlayer)
    {
        this.communismPlayer = communismPlayer;
    }

    public Player getCapitalismPlayer()
    {
        return capitalismPlayer;
    }

    public void setCapitalismPlayer(Player capitalismPlayer)
    {
        this.capitalismPlayer = capitalismPlayer;
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }

    public ObservableList<Piece> getPieceList()
    {
        return pieceList;
    }

    public void setPieceList(ObservableList<Piece> pieceList)
    {
        this.pieceList = pieceList;
    }


    /**
     * Try find the piece in the list
     *
     * @param posX X-axis of the piece
     * @param posY Y-axis of the piece
     * @return The piece object if it has been found, or null if it has not been found
     */
    public Piece getPieceFromList(int posX, int posY)
    {
        for(Piece piece : this.pieceList) {
            if(piece.getCoordinate().getPosX() == posX && piece.getCoordinate().getPosY() == posY) {
                return piece;
            }
        }

        return null;
    }
}
