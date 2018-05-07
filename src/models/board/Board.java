package models.board;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import controllers.GameLogic;
import controllers.HomeController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.RoleType;
import models.player.Player;

import java.util.Timer;

public class Board implements ListChangeListener<Piece>
{
    private Player communismPlayer;
    private Player capitalismPlayer;
    private Player currentPlayer;
    private ObservableList<Piece> pieceList;
    private GameLogic gameLogic;
    private Timeline turnTimeline;

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
        this.pieceList.addListener(this);
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
            if(piece.getCoordinate().getPosX() == posX && piece.getCoordinate().getPosY() == posY) {
                return piece;
            }
        }

        return null;
    }

    /**
     * Initialise and start the timeout countdown timer
     * @param piece - Piece selected in the candidate area
     */
    public void beginCountdown(Piece piece)
    {
        System.out.println("Timeout countdown begins");

        // Fixed 5 seconds countdown for now, will add a parser later for user config in assignment 2
        turnTimeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            System.out.println("5 seconds reached, timeout!");
            gameLogic.timeout(piece);
            this.currentPlayer.setSelectedPiece(null);
        }));

        turnTimeline.play();
    }

    /**
     * Stop the timeout timer
     */
    public void stopCountdown()
    {
        System.out.println("Timeout countdown stops");

        if(turnTimeline == null)
            return;
        turnTimeline.stop();
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
                    this.gameLogic.getGuiController().commitUIChanges(piece.getCoordinate(), piece.getStyle());
                }
            }

            if(change.wasRemoved()) {
                for(Piece piece : change.getRemoved()) {
                    // Put an empty string to the style (may need to set a default button style later on)
                    this.gameLogic.getGuiController().commitUIChanges(piece.getCoordinate(), "");
                }
            }
        }
    }
}
