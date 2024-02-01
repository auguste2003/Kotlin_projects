/**
 * Dieses Interface setzt die Beziehung zwischen dem View und dem Kontoller
 */
public interface ITTicTactoView {
    void  drawBoard();

    void zeichnePressedField( int field , int player);

    void drawSymbol(int field , int player);

    void showGameOverMessage (String message);
}
