public class King {
    private int row;
    private int col;
    private boolean isBlack;

    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            if (board.verifyAdjacent(this.row, this.col, endRow, endCol)) {
                return true;  // The king can get by with just with calling adjacent verification.
            }
        }
        return false;
    }
}
