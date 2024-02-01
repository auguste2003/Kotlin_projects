
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;



import static org.junit.Assert.assertArrayEquals;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Hier teste ich das Model von dem Tictacto Spiel
 */
public class TicTactoModelTest {
TictactoModel model =new TictactoModel();
     char[]     gameState ={};
    /**
     * Hier wird die newGame() getestet
     */
    @Test
    void newGameSouldCreateAFieldGameState(){
        gameState = new char[]
                {'_', '_', '_',
                        '_', '_', '_',
                        '_', '_', '_'};
   model.newGame();
   assertArrayEquals(gameState,model.getBoard());
    }
    /**
     * Mouvementcount testen
     */
    @Test
    void afternewGame(){
        model.newGame();
        model.move(2);
        model.move(4);
        model.move(3);

        assertEquals(3,model.moveCount);
    }
    /**
     * hier wird getBoard() getestet
     * Die Methode gibt ein gameState Feld zurück
     */
    @Test
void getBoardSchuldReturnGameState(){
        gameState = new char[]
                {'_', '_', '_',
                        '_', '_', '_',
                        '_', '_', '_'};
    model.getBoard();

    assertArrayEquals(gameState, model.getBoard());
  //  assertEquals(0,model.m);
}
/**
 * Hier wird die Methode  isValidFieldIndex() getestet
 */
/*
@Test
    void  isValidFieldIndexSchuldReturnTrueWenTheFieldIsBetween0And8(){
    int field1 =9;
    int field =3;
    model. isValidFieldIndex(field);
    assertTrue(model. isValidFieldIndex(field));
    assertFalse(model. isValidFieldIndex(field1));
}*/
/**
 * Hier wird die isEmptyField getetet
 * est gibt true zurück , wenn das Feld leer ist
 */
@Test
    void isEmptyFielReturnTrueWenTheFieldIsEmpty(){
    int field =4;
    assertTrue(model.isEmptyField(field));
}
/**
 * Hier wird die getActivePlayer() gestestet
 * Das der jetztige Olayer aus
 */
@Test
    void getActivePlayerReturnTheActivePlayer(){

}
/**
 * hier wird move() getestet
 * Wenn ein Feld eingegen ist , muss ein neues gamstate gezeichnet werden
 */
@Test
void moveDrawAnewGameStat(){
    int field =3;
    model.move(field);
    gameState = new char[]
            {'_', '_', '_',
                    'X', '_', '_',
                    '_', '_', '_'};
    assertArrayEquals(gameState, model.getBoard());
}
    @Test
    void testRuntimeExceptionofMove(){
        int field =8;

       // model.move(field);
      //  model.move(field);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            model.move(field);
            model.move(field);
        });

    }

    /**
     * teste ob die exeption für den falschen Index fonktioniert.
     */
    @Test
    void testIndexOutOfBoundsExceptionofMove(){
        int field =9;

        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            model.move(field);

        });

    }
    /**
     *  RuntimeException Wenn das Spiel schon fertig ist
     */
    @Test
    void  RuntimeExceptionWenGameOver(){
        TictactoModel model2 =new TictactoModel();
        model2.move(0);
        model2.move(1);
        model2.move(3);
        model2.move(2);
        model2.move(6);

        int field =8;

        // model.move(field);
        //  model.move(field);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            model.move(field);
            model.move(field);
        });
    }
    /**
 * Hier wird dann die Methode isGameOver getestet
 */
@Test
    void isGameOverReturnTrueWenOnePlayerHasWon(){
    int field =2;
    int field2 =4;
    int field3=3;
    model.move(field);
    model.move(field2);
    model.move(field3);

    assertFalse(model.isGameOver());
    TictactoModel model2 =new TictactoModel();
    model2.move(0);
    model2.move(1);
    model2.move(3);
    model2.move(2);
    model2.move(6);
    assertTrue(model2.isGameOver());
}


    /**
 * Die Methode hasPlayer1Won testen
 * Das gibt true zurück , wenn der erste Player gewonnen hat
 */
@Test
void hasPlayer1WonReturnTrueWenTheFirstPlayerWon(){

    model.move(0);
    model.move(1);
    model.move(3);
    model.move(2);
    model.move(6);


    assertTrue(model.hasPlayer1Won());
}
    /**
     * Die Methode hasPlayer1Won testen
     * Das gibt true zurück , wenn der zweite Player gewonnen hat
     */
    @Test
    void hasPlayer2WonReturnTrueWenTheFirstPlayerWon(){

        model.move(0);
        model.move(1);
        model.move(3);
        model.move(2);
        model.move(6);


        assertFalse(model.hasPlayer2Won());
    }

    /**
     * Diese Methode testet ,ob das Index valid ist
     *
     */
    @Test
    void istIndexValidSchouldReturnTrueWenIndexIsBetween0and8(){
        TictactoModel model =new TictactoModel();

        assertTrue(model.isValidFieldIndex(3));
    }
    /**
     * Diese testet die checkWinCondition , ob die Symbole diagonal sortiert werden oder
     * in rows oder columns
     */
    @Test
    void checkWinConditionReturntrueWendthesymbolsAreAligne(){
        model.move(0);
        model.move(1);
        model.move(3);
        model.move(2);
        model.move(6);


        assertTrue(model.checkWinCondition('X'));
    }
    /**
     * hier wird die toString () getestet
     * Diese gibt das Feld zurück
     */
    @Test
   void ToStringReturnTheField(){
        model.move(0);
      /*  model.move(1);
        model.move(3);
        model.move(2);
        model.move(6);*/
String nachricht = "Make move for O\nX _ _ \nX _ _ \n_ _ _" ;



assertNotEquals(nachricht,model.toString());

    }

}
