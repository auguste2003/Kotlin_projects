public class TictactoModel{
    /* The default field entry for empty fields */
    public final char EMPTY = '_';
    /* The mark of player 1 on the playing field */
    public final char PLAYER_1 = 'X';
    /* The mark of player 2 on the playing field */
    public  final char PLAYER_2 = 'O'; // change to public

    /* Playing field with 9 entries */
    private  char[] gameState; // Private

    /* Counts the moves. Even moves are for PLAYER_1; odd moves are for PLAYER_2. */
    public char moveCount = 0;

    public static void main(String[] args) {
        var game = new TictactoModel();
    }

    /**
     * Creates a new TicTacToe game.
     */
    public TictactoModel() {
        newGame();
    }

    /**
     * Resets the game state.
     */
    public void newGame() {
        gameState = new char[]
                {EMPTY, EMPTY, EMPTY,
                        EMPTY, EMPTY, EMPTY,
                        EMPTY, EMPTY, EMPTY};
        moveCount = 0;
    }

    /**
     *
     * @return the Field gameState
     */
    public char[] getBoard() {
        return gameState;
    }
    public  boolean isValidFieldIndex(int field) {
        return field >= 0 && field < gameState.length;
    }

    /**
     * Checks if a game field is empty.
     * @param field The number of a valid field to check [0, 8].
     * @return True if the field is empty, false otherwise.
     */
    public boolean isEmptyField(int field) {
        if(isValidFieldIndex(field))
            return gameState[field] == EMPTY;
        else
            throw new IndexOutOfBoundsException("Valid fields are [0, 8]");
    }

    /**
     * Returns the active players mark.
     * @return Mark of the player (PLAYER_1 or PLAYER_2).
     */
    public char getActivePlayer() {
        return (moveCount % 2 == 0) ? PLAYER_1 : PLAYER_2;
    }

    /**
     * Makes a new move with the currently active player.
     * @param field An empty field the player chooses for this turn.
     */
    public void move(int field) {
        if(!isGameOver()) {
            if (!isValidFieldIndex(field))
                throw new IndexOutOfBoundsException("Valid fields are [0, 8]");
            if (!isEmptyField(field))
                throw new RuntimeException("The chosen field is already taken");

            gameState[field] = getActivePlayer();
            moveCount++;

            // Print the state after the move
            System.out.println(this);
        } else
            throw new RuntimeException("The game is already over. Please start a new game.");
    }

    /**
     *
     * Checks if the game is over.
     * @return True if a player has won or the game is a draw, false otherwise.
     */
    public boolean isGameOver() {
        return hasPlayer1Won() || hasPlayer2Won() || moveCount >= gameState.length;
    }

    /**
     * Checks if Player 1 has won the game.
     * @return
     */
    public boolean hasPlayer1Won() {
        return checkWinCondition(PLAYER_1);
    }

    /**
     * Checks if Player 1 has won the game.
     * @return
     */
    public boolean hasPlayer2Won() {
        return checkWinCondition(PLAYER_2);
    }

    /**
     *
     * @param player take a player in parameter
     * @return true if that player has won
     */

    public  boolean checkWinCondition(char player) {
        return  // Check rows
                (gameState[0] == player && gameState[1] == player && gameState[2] == player) ||
                        (gameState[3] == player && gameState[4] == player && gameState[5] == player) ||
                        (gameState[6] == player && gameState[7] == player && gameState[8] == player) ||
                        // Check columns
                        (gameState[0] == player && gameState[3] == player && gameState[6] == player) ||
                        (gameState[1] == player && gameState[4] == player && gameState[7] == player) ||
                        (gameState[2] == player && gameState[5] == player && gameState[8] == player) ||
                        // Check diagonals
                        (gameState[0] == player && gameState[4] == player && gameState[8] == player) ||
                        (gameState[2] == player && gameState[4] == player && gameState[6] == player);
    }

    /**
     *
     * @return field , that the controller draws
     */
    @Override
    public String toString() {
        return String.format("%s\n%c %c %c\n%c %c %c\n%c %c %c",
                isGameOver() ? "Game Over " : "Make move for "+getActivePlayer(),
                gameState[0], gameState[1], gameState[2],
                gameState[3], gameState[4], gameState[5],
                gameState[6], gameState[7], gameState[8]);
    }
}


