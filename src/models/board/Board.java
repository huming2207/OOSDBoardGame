package models.board;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import controllers.GameLogic;
import helpers.reactions.AbstractReaction;
import helpers.reactions.ReactionLevel;
import helpers.reactions.ReactionManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import models.piece.Piece;
import models.piece.type.RoleType;
import models.player.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Board model class, containing current board status
 *
 * @author Ming Hu (s3554025)
 * @since Assignment 1
 */
public class Board implements Serializable
{
    private Player communismPlayer;
    private Player capitalismPlayer;
    private Player currentPlayer;

    // These three objects below cannot be serialize, so I use a convert
    private transient ObservableList<Piece> pieceList;
    private transient GameLogic gameLogic;
    private transient Timeline turnTimeline;
    private transient AbstractReaction reaction;

    /**
     * Create a new board
     * @param gameLogic Game logic controller
     * @param communismPlayerName Communism player's nick name
     * @param capitalismPlayerName Capitalism player's nick name
     */
    @Requires({"gameLogic != null", "!communismPlayerName.isEmpty()", "!capitalismPlayerName.isEmpty()"})
    public Board(GameLogic gameLogic, String communismPlayerName, String capitalismPlayerName)
    {
        this.gameLogic = gameLogic;
        this.communismPlayer = new Player(communismPlayerName, RoleType.COMMUNISM_PIECE);
        this.capitalismPlayer = new Player(capitalismPlayerName, RoleType.CAPITALISM_PIECE);
        this.pieceList = FXCollections.observableArrayList();
        this.pieceList.addListener(this.gameLogic);
        this.reaction = ReactionManager.getReaction();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException
    {
        // Serialize non-transient members above
        outputStream.defaultWriteObject();

        // Serialize piece list
        ArrayList<Piece> pieces = new ArrayList<>(this.pieceList);
        outputStream.writeObject(pieces);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException
    {
        // Deserialize non-transient member above...
        inputStream.defaultReadObject();

        // Deserialize and load the piece list
        ArrayList<Piece> pieces = (ArrayList<Piece>)inputStream.readObject();
        this.reaction = ReactionManager.getReaction();
        this.pieceList = FXCollections.observableArrayList();
        this.pieceList.addAll(pieces);
    }

    public void setGameLogic(GameLogic gameLogic)
    {
        this.gameLogic = gameLogic;
    }

    public void refreshPieceList()
    {
        // Load the piece list into an array list buffer
        ArrayList<Piece> pieces = new ArrayList<>(this.pieceList);

        // Refresh listener
        this.pieceList.addListener(this.gameLogic);

        // Refresh piece list
        this.pieceList.clear();
        this.pieceList.addAll(pieces);
        pieces.clear();

        // Add player into the candidate zone
        this.gameLogic.getGuiController().commitPlayerSelection(this.getCurrentPlayer());
    }

    /**
     * Get communism player
     * @return communism player
     */
    public Player getCommunismPlayer()
    {
        return communismPlayer;
    }

    /**
     * Set communism player
     * @param communismPlayer communism player
     */
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
    @Ensures("result.size() > 0")
    public ObservableList<Piece> getPieceList()
    {
        return pieceList;
    }

    /**
     * Set piece list
     * @param pieceList piece list
     */
    @Requires("pieceList.size() > 0")
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
    @Requires({"posX > 0", "posY > 0"})
    public Piece getPieceFromList(int posX, int posY)
    {
        for(Piece piece : this.pieceList) {
            if(piece.getCoordinate().getPosX() == posX && piece.getCoordinate().getPosY() == posY) return piece;
        }

        return null;
    }

    /**
     * Initialise and start the timeout countdown timer
     * @param piece - Piece selected in the candidate area
     */
    public void beginCountdown(Piece piece)
    {
        this.reaction.handleReaction(ReactionLevel.DEBUG, "Timeout counter starts...");

        // Fixed 5 seconds countdown for now, will add a parser later for user config in assignment 2
        this.turnTimeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            this.gameLogic.timeout(piece);
            this.currentPlayer.setSelectedPiece(null);
        }));

        this.turnTimeline.play();
    }

    /**
     * Stop the timeout timer
     */
    public void stopCountdown()
    {
        this.reaction.handleReaction(ReactionLevel.DEBUG, "Timeout counter stops...");

        if(this.turnTimeline == null) return;
        this.turnTimeline.stop();
    }

}
