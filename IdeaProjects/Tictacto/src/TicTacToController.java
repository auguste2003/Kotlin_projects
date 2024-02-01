import java.util.ArrayList;
import processing.core.PApplet;

/**
 * Der conteroller ist der Vermitller zwuschen dem View und dem Model und ist mit dem Beiden in Beziehung
 */
public class TicTacToController implements ITTictactoController {
    /**
     * @param symbol ein ArrayList , das alle Symboöe speichert , die gezeichnet sind
     *  @param  model sichtbarer Attribut von dem Model
     *   @param view sichtbarer Attribut von dem View
     *    @param player 1 sichtbarer Attribut von dem ersten Player
     *     @param player 2 sichtbarer Attribut von dem zweiten Player
     *      @param
     */
    ArrayList<TicTacToController.Symbol> symbol =new ArrayList<TicTacToController.Symbol>(); // ArrayList für die Symbole , die gezeinet werden

    private TictactoModel model;//  Der Controller kennt den Modell
    private TicTactoView  view;// Der Controller kennt den View
    public  int player1;
    public  int player2;

    /**
     * Konstruller von dem Controller
     */
    public TicTacToController() {} // Konstruktor

    /**
     * diese sichtbare Methode setzt einen Model
     * @param model parameter von dem Model
     *
     */
    public void setModel(TictactoModel model) {
        this.model = model;// Modell eingeben
    }

    /**
     * diese sichtbare Methode setzt einen View
     * @param view parameter von dem View
     */
    public void setView(TicTactoView  view) {
        this.view = view;// View eingeben
    }

    /**
     * Diese sichtbare Methode gibt  einArray gameState zurück
     */
    public void startGame(){ // das erzeugt ein gameState Array
        model.newGame();
    }

    /**
     *diese sichtbare Klasse gitb die Position von dem Maus zurück beziehungsweise die Position von dem Feld ,das ausgewählt worden ist .
     * @return die entsprechende Position
     */

    public int getPositionMaus(float mouseX, float mouseY) { // Renvoit la  position de la sours entre 1 a 8

        // Überprüfe, in welche Zelle die Maus geklickt hat und gib die Nummer zurück
        int col = (int) (mouseX / view.cellWidth);
        int row = (int) (mouseY / view.cellHeight);
        return row * view.spalte + col + 1; // +1, da die Nummerierung bei 1 beginnt*/
    }

    /**
     *  Diese sichtbare Methode nimmt die Position von dem ausgesuchten Feld im Parameter und ruft die Methode move()
     *  im Model auf .
     * @param field ein Parameter für die Position von dem Feld aussucht worden ist
     */
    public void nIsPressed(int field){ //Das Spiel kann starten  und man überprüft , ob das Feld passt
        if(!model.isGameOver()) {
            if (field >= 1 && field <= 9 && model.isEmptyField(field - 1)) { // Die Felder sind von 1 bis 8 , ich überprüfe , ob das geht

                model.move(field - 1); // Das Das Feld bekommt entweder X oder 0 in position feld -1
                if (model.getActivePlayer() == model.PLAYER_1) { // Den activen Olayer finden
                    player1 = 1;// !! Die Positionen müssen wir immer initialisieren
                    player2 = 0;
                    symbol.add(new Symbol(player1,field));
                } else if (model.getActivePlayer() == model.PLAYER_2) { // Überprüft , ob es um den zweiten Player geht

                    player2 = 2;
                    player1 = 0;
                    symbol.add(new Symbol(player2,field));
                }
            }
        } else
        if(model.isGameOver()) { // Falls das Spiel fertig ist

            if (model.hasPlayer1Won()) {  // Celle ci appelle mathode hasPlayer1Won() et celle ci a son tour appelle la methode checkWincondition pour trouver ci c*est frai ou faux
                view.ganeOverMessage = "Player 1 won!"; // renvoit le message
            } else if (model.hasPlayer2Won()) { // celle ci appelle la methode hasPlayer2Won() et celle si appelle a son tour
                view.ganeOverMessage = "Player 2 won!";
            }
            else {
                view.ganeOverMessage ="Draw!"; // Personne n'a gagner le jeu
            }
        } else {
            view.ganeOverMessage ="Invalid move!"; // La position n'existe pas
        }
    }

    /**
     * Erstellt eine innere lokale  Klasse Symbol
     */
    class Symbol{ // Lokale Klasse Symbpl

        /**
         * @param player sichtbarer Attribut von dem jetztigen Player
         * @param field sichtbarer Atribut von dem feld , das ausgesucht wird
         */
        int player;
        int field;

        /**
         * Kontruktor von dem Symbol
         * @param player initialisiert den Player
         * @param field initialisiert das Feld
         */
        public Symbol(int player,int field){
            this.field=field;
            this.player=player;
        }
    }
}

