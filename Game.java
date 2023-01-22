import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        Board gameBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", gameBoard);
        System.out.println(gameBoard.toString());
        Scanner scanner = new Scanner (System.in);
        // TODO: check which player's turn it is

        boolean takeTurn = true;
        while (!gameBoard.isGameOver()) {    // Checks player's turn, while game is still at play.'
            if (takeTurn) {
                System.out.println("It is currently white's turn.");
            } else {
                System.out.println("It is currently black's turn.");
            }
            System.out.println("What is your move? (format: [startRow][endRow][startColumn][endColumn])");
            int startRow = scanner.nextInt();
            int startColumn = scanner.nextInt();
            int endRow = scanner.nextInt();
            int endColumn = scanner.nextInt();
            if (gameBoard.movePiece(startRow, startColumn, endRow, endColumn)) {
                System.out.println(gameBoard.toString());
                takeTurn =!takeTurn;      // Boolean value is inversed until while loop cannot run anymore
            } else {
                System.out.println("Invalid move");
                System.out.println(gameBoard.toString());
            }
        }
        scanner.close(); //  While case broken, the game is no longer in play.
        System.out.println("The Game is over");
        }
}


