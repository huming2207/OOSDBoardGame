package helpers.exceptions;

public class DuplicatedPieceException extends Exception
{
    public DuplicatedPieceException(String message)
    {
        super(message);
        System.err.println("The user seems putting a piece above an existing piece...");
    }
}
