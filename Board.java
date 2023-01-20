// Written by Ketim Lamessa, lames005
public class Board {

    private Piece[][] board;

    public Board() {
        this.board= new Piece[8][8];
    }

    public Piece getPiece(int row, int col) {
        return this.board[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        this.board[row][col] = piece;
    }

    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        boolean bool = 0 <= startRow && startRow < 8 && startCol < 8 && startCol >= 0 && endRow >= 0 && endRow < 8 && endCol < 8 && endCol >= 0;
        if (bool && this.board[startRow][startCol] != null && this.board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
            this.board[endRow][endCol] = this.board[startRow][startCol];
            this.board[endRow][endCol].setPosition(endRow,endCol);
            this.board[startRow][startCol] = null;
            return true;
        }
        return false;
    }

    public boolean isGameOver() {
       int kingCounter = 0;
       for (int i = 0; i < board.length; i++) {
           for (int j = 0; j < board[i].length; j++) {
               if (this.board[i][j] != null && (this.board[i][j].getCharacter() == '\u2654' || this.board[i][j].getCharacter() == '\u265a')) {
                   kingCounter++;
               }
           }
       }
       if (kingCounter != 2) { // checks if game is over and which player is the winner
           for (int i = 0; i < board.length; i++) {
               for (int j = 0; j < board[i].length; j++) {
                   if (this.board[i][j]!= null && this.board[i][j].getCharacter() == '\u2654') {
                       System.out.println("White won!");
                   } else if (this.board[i][j]!= null && this.board[i][j].getCharacter() == '\u265A') {
                       System.out.println("Black won!");
                   }
               }
           }
           return true;
       }
       return false;
    }

    public void clear() {    // loops through very space in on the board and sets every space to empty
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = null;
            }
        }
    }

    public String toString() {
        String s = new String();
        s += "   0 1 2 3 4 5 6 7\n";
        for (int i = 0; i < 8; i++) {
            s += i + " ";
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j] != null) {
                    s += "|" + this.board[i][j].getCharacter();
                } else {
                    s += "| ";
                }
            }
            s += "|\n";
        }
        return s;
    }

    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow < 8 && startRow >= 0 && endRow < 8 && endRow >= 0 && startCol < 8 && startCol >= 0 && endCol < 8 && endCol >= 0  // boared 0 - 8so
                && this.board[startRow][startCol] != null && this.board[startRow][startCol].getIsBlack() == isBlack
                && (this.board[endRow][endCol] == null || this.board[endRow][endCol].getIsBlack() != isBlack)) {
                    return true;
        }
        return false;
    }

    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int distRowAbs = Math.abs(endRow - startRow);
        int distColAbs = Math.abs(endCol - startCol);
        if ((distRowAbs == 1 && distColAbs <= 1 || distRowAbs <= 1 && distColAbs == 1)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow) {
            return false;
        }
        int nextSpot = 1;
        if (startCol > endCol) {
            for (int i = startRow - nextSpot; i > endCol; i--) {
                if (this.board[startRow][i]!= null) {
                    return false;
                }
            }
        } else if (startCol < endCol) {
            for (int i = startRow + nextSpot; i < endRow; i++) {
                if (this.board[startRow][i]!= null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) {
            return false;
        }
        int nextSpot = 1;
        if (startRow > endRow) {
            for (int i = startRow - nextSpot; i > endRow; i--) {
                if (this.board[i][startCol]!= null) {
                    return false;
                }
            }
        } else if (startRow < endRow) {
            for (int i = startRow + nextSpot; i < endRow; i++) {
                if (this.board[i][startCol]!= null) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean verifyDiagonal (int startRow, int startCol, int endRow, int endCol) {
        int distColAbs = Math.abs(startCol - endCol);
        //int slope = ((endCol - startCol) / (endRow - startRow));

        if (distColAbs == 1) {
            return true;
        }
        int i = 1;
        while (i < distColAbs) {
            if ((endRow < startRow) && (endCol > startCol) && board[startRow-i][startCol+i] == null){
                return true;
            }else if ((endRow > startRow) && (endCol > startCol) && board[startRow+i][startCol+i] == null) {
                return true;
            }else if ((startRow > endRow) && (startCol > endCol) && board[startRow+i][startCol-i] == null){
                return true;
            }else if ((startRow < endRow) && (endCol > startCol) && board[startRow-i][startCol-i] == null){
                return true;
            }
            i++;
        }
        return false;

    }

    public boolean verifyL(int startRow, int startCol, int endRow, int endCol) {
        if ((startRow - endRow == -2 && startCol - endCol == 1) || (startRow - endRow == -1 && startCol - endCol == 2)
                || (startRow - endRow == 1 && startCol - endCol == 2) || (startRow - endRow == 2 && startCol - endCol == 1)
                || (startRow - endRow == 2 && startCol - endCol == -1) || (startRow - endRow == 1 && startCol - endCol == -2)
                || (startRow - endRow == -1 && startCol - endCol == -2) || (startRow - endRow == -2 && startCol - endCol == -1)) {
            return true;  // The 8 unique cases of the differences allows us to create all the valid spots for the knight.
        }
        return false;
    }
}