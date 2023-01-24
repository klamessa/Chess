public class Queen {

    private int row;
    private int col;
    private boolean isBlack;

    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {      // The Queen has a lot of mobility, calls all the motions: Verical + Horizonal and Diagonal
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            if (board.verifyVertical(this.row, this.col, endRow, endCol)
                    || board.verifyHorizontal(this.row, this.col, endRow, endCol)
                    || board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
                return true;
            }
        }
        return false;
    }
}
