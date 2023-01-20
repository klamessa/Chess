public class Bishop {
    private int row;
    private int col;
    private boolean isBlack;

    public Bishop(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {     // The Bishop uses a purely diagonal move, so the verify Diagonal is called.
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            if (board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
                return true;
            }
        }
        return false; // An ilegal bishop motion.
    }
}
