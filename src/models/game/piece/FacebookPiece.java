package models.game.piece;

import models.game.coordinate.Coordinate;
import models.game.piece.type.RoleType;

public class FacebookPiece extends Piece
{
    public FacebookPiece(Coordinate coordinate)
    {
        super(coordinate, 120, 30, RoleType.CAPITALISM_PIECE);
    }

    @Override
    public final String getStyle()
    {
        return "-fx-background-color:#0000ff;" +
                "-fx-shape: \"M1024 512c0-282.763636-229.236364-512-512-512C229.236364 0 0 229.236364 0 512s229.236364 512 512 " +
                "512C794.763636 1024 1024 794.763636 1024 512zM374.504727 512 374.504727 414.021818l60.043636 " +
                "0L434.548364 354.769455c0-79.918545 23.877818-137.495273 111.383273-137.495273l104.075636 0 0 " +
                "97.745455-73.262545 0c-36.724364 0-45.056 24.389818-45.056 49.943273l0 49.058909 112.919273 " +
                "0L629.201455 512l-97.512727 0 0 295.517091L434.548364 807.517091 434.548364 512 374.504727 512z\";";
    }
}
