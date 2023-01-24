public class Rook {

    private int row;
    private int col;
    private boolean isBlack;

    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            if (board.verifyVertical(this.row, this.col, endRow, endCol) || board.verifyHorizontal(this.row, this.col, endRow, endCol)) {
                return true;    // Horizontal and vertical movement given the board is clear
            }
        }
        return false;
    }
}
