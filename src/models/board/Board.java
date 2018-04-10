package models.board;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import controllers.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import models.piece.Piece;
import models.piece.type.RoleType;
import models.player.Player;

public class Board implements ListChangeListener<Piece>
{
    private Player communismPlayer;
    private Player capitalismPlayer;
    private Player currentPlayer;
    private ObservableList<Piece> pieceList;
    private HomeController homeController;

    /**
     * Create a new board
     * @param homeController Home controller
     * @param communismPlayerName Communism player's nick name
     * @param capitalismPlayerName Capitalism player's nick name
     */
    @Requires({"homeController != null", "!communismPlayerName.isEmpty()"})
    public Board(HomeController homeController, String communismPlayerName, String capitalismPlayerName)
    {
        this.homeController = homeController;
        this.communismPlayer = new Player(communismPlayerName, RoleType.COMMUNISM_PIECE);
        this.capitalismPlayer = new Player(capitalismPlayerName, RoleType.CAPITALISM_PIECE);
        this.pieceList = FXCollections.observableArrayList();
        this.pieceList.addListener(this);
    }

    /**
     * Get communism player
     * @return communism player
     */
    @Ensures("communismPlayer != null")
    public Player getCommunismPlayer()
    {
        return communismPlayer;
    }

    /**
     * Set communism player
     * @param communismPlayer communism player
     */
    @Requires("communismPlayer != null")
    public void setCommunismPlayer(Player communismPlayer)
    {
        this.communismPlayer = communismPlayer;
    }

    /**
     * Get capitalism player
     * @return capitalism player
     */
    public Player getCapitalismPlayer()
    {
        return capitalismPlayer;
    }

    /**
     * Set capitalism player
     * @param capitalismPlayer capitalism player
     */
    public void setCapitalismPlayer(Player capitalismPlayer)
    {
        this.capitalismPlayer = capitalismPlayer;
    }

    /**
     * Get current player which is in his/her turn
     * @return current player
     */
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     * Set current player which is in his/her turn
     * @param currentPlayer current player
     */
    public void setCurrentPlayer(Player currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Get piece list
     * @return piece list
     */
    public ObservableList<Piece> getPieceList()
    {
        return pieceList;
    }

    /**
     * Set piece list
     * @param pieceList piece list
     */
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


    /**
     * This method triggers when any changes is made in the piece list. It will update UI via home controller.
     * @param change Piece changes in the list
     */
    @Override
    public void onChanged(Change<? extends Piece> change)
    {
        while(change.next()) {
            if(change.wasAdded()) {
                for(Piece piece : change.getAddedSubList()) {
                    this.homeController.commitUIChanges(piece.getCoordinate(), piece.getStyle());
                }
            }

            if(change.wasRemoved()) {
                for(Piece piece : change.getRemoved()) {
                    this.homeController.commitUIChanges(piece.getCoordinate(), "");
                }
            }
        }
    }
}
