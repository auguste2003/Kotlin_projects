import java.util.ArrayList;

/**
 * Dieses Interface setzt die Beziehung zwischen dem View und dem Kontoller
 */
public interface ITTictactoController {
    void startGame();

    /**
     *
     * @param field ein Parameter für die Position von dem Feld aussucht worden ist
     */
    void nIsPressed(int field);

}
