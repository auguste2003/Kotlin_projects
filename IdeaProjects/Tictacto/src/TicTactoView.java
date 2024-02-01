import processing.core.PApplet;

/**
 * Die Klasse TicTactoView , in der das Diisign von dem Spiel implementiert wird .
 */
public class TicTactoView extends PApplet implements ITTicTactoView {

    /**
     * @param controller für die Beziehung zwischen der View und dem Controller
     * @param spalte eine sichtbare  Variable  für die Anzahl von Spalten in meinem Spielfeld
     * @param reihe eine sichtbare Variable für die Anzahl von Reihen in meinem Spielfeld
     * @param reihe eine sichtbare Variable  cellWidth für die breite von einem auszusuchen Feld
     * @param cellHeight eihe eine sichtbare Variable ,die  zeigt wie hoch ein auszusuchendes Spielfeld ist
     * @param n istreihe eine sichtbare Variable , die die Position von dem ausgesuchten Feld
     * @param ganeOverMessage ist reihe eine sichtbare Variable ,die  die Nachricht speichert, die am Ende von dem Spiel zurückgegeben wird
     */
    TicTacToController controller;

    int spalte = 3;
    int reihe = 3;

    float cellWidth;
    float cellHeight;

    int n = 0;

    String ganeOverMessage = "";


    /**
     * Der Konstruktor für den Aufruf und die Initialierung von dem View
     *
     * @param width  für die Breite von dem Fenster
     * @param height zeigt wie hoch das Fenster sein muss
     */
    public TicTactoView(int width, int height) {
        setSize(width, height);
    }

    /**
     * Die sichtbare  Methode setzt einen Controller
     *
     * @param controller der in beziehung mit der View ist
     */
    public void setController(TicTacToController controller) {
        this.controller = controller;
    }

    /**
     * Diese sichtbare Methode enthält die Parameter von dem Disign , die sich nicht wiederholen
     */
    public void setup() {

    }

    /**
     * Die sichtbare Methode setzt den Hintergrund von dem Fenster auf schwarz und zeichnen das Fenster  eneut jedes Mal , dass ein Player spielt
     */
    public void draw() {
        background(0);
        drawBoard();
        for (int i = 0; i < controller.symbol.size(); i++) {

            zeichnePressedField(controller.symbol.get(i).field, controller.symbol.get(i).player);

        }
        showGameOverMessage(ganeOverMessage);

    }

    /**
     * Diese sichtbare Methode Zeichnet das Spielfeld
     * Zeichne das Spielfeld mit Rechtecken und Trennlinien
     */
    public void drawBoard() {
        cellWidth = width / spalte;
        cellHeight = height / reihe;
        stroke(255);
        strokeWeight(4);


        for (int i = 1; i < spalte; i++) {
            float x = i * cellWidth;
            line(x, 0, x, height);
        }
        for (int j = 1; j < reihe; j++) {
            float y = j * cellHeight;
            line(0, y, width, y);
        }
    }

    /**
     * Die sichtbare  Methode nimmt die Position von der Maus , wenn diese gedruückt ist gibt diese dem Controller weiter
     * Wenn die Maus gedrückt ist , sollte man entsprechend reagieren
     */


    public void mousePressed() {
        int x1 = mouseX;
        int x2 = mouseY;
        n = controller.getPositionMaus(x1, x2);
        controller.nIsPressed(n);
    }

    /**
     * Die sichtbare  Methode zeichnet die Symbole , die nach dem mausePressed erscheinen
     *
     * @param field  ist das Feld , aif der ein Spieler geklickt hat
     * @param player ist der Player , der gearade spielt
     */
    public void drawSymbol(int field, int player) {

        if (player == 1) {
            if (field == 1) {
                stroke(255);
                strokeWeight(2);

                ellipse(cellWidth / 2, cellHeight / 2, 200, 200);

            }
            if (field == 2) {
                stroke(255);
                strokeWeight(2);

                ellipse(3 * cellWidth / 2, cellHeight / 2, 200, 200);

            }
            if (field == 3) {
                stroke(255);
                strokeWeight(2);

                ellipse(5 * cellWidth / 2, cellHeight / 2, 200, 200);

            }
            if (field == 4) {
                stroke(255);
                strokeWeight(2);

                ellipse(1 * cellWidth / 2, 3 * cellHeight / 2, 200, 200);

            }
            if (field == 5) {
                stroke(255);
                strokeWeight(2);

                ellipse(3 * cellWidth / 2, 3 * cellHeight / 2, 200, 200);

            }
            if (field == 6) {
                stroke(255);
                strokeWeight(2);

                ellipse(5 * cellWidth / 2, 3 * cellHeight / 2, 200, 200);

            }
            if (field == 7) {
                stroke(255);
                strokeWeight(2);

                ellipse(1 * cellWidth / 2, 5 * cellHeight / 2, 200, 200);

            }
            if (field == 8) {
                stroke(255);
                strokeWeight(2);

                ellipse(3 * cellWidth / 2, 5 * cellHeight / 2, 200, 200);

            }
            if (field == 9) {
                stroke(255);
                strokeWeight(2);

                ellipse(5 * cellWidth / 2, 5 * cellHeight / 2, 200, 200);

            }
        } else if (player == 2) {
            if (field == 1) {
                stroke(255);
                strokeWeight(2);
                line(0, 0, cellWidth, cellHeight);
                line(0, cellHeight, cellWidth, 0);

            }
            if (field == 2) {
                stroke(255);
                strokeWeight(2);
                line(cellWidth, 0, 2 * cellWidth, cellHeight);
                line(cellWidth, cellHeight, 2 * cellWidth, 0);
            }
            if (field == 3) {
                stroke(255);
                strokeWeight(2);
                line(2 * cellWidth, 0, 3 * cellWidth, cellHeight);
                line(2 * cellWidth, cellHeight, 3 * cellWidth, 0);

            }
            if (field == 4) {
                stroke(255);
                strokeWeight(2);
                line(0, cellHeight, cellWidth, 2 * cellHeight);
                line(0, 2 * cellHeight, cellWidth, cellHeight);

            }
            if (field == 5) {
                stroke(255);
                strokeWeight(2);


                line(cellWidth, 2 * cellHeight, 2 * cellWidth, cellHeight);
                line(cellWidth, cellHeight, 2 * cellWidth, 2 * cellHeight);
            }
            if (field == 6) {
                stroke(255);
                strokeWeight(2);


                line(2 * cellWidth, cellHeight, 3 * cellWidth, 2 * cellHeight);
                line(2 * cellWidth, 2 * cellHeight, 3 * cellWidth, cellHeight);
            }
            if (field == 7) {
                stroke(255);
                strokeWeight(2);

                line(0, 2 * cellHeight, cellWidth, 3 * cellHeight);
                line(0, 3 * cellHeight, cellWidth, 2 * cellHeight);

            }
            if (field == 8) {
                stroke(255);
                strokeWeight(2);

                line(cellWidth, 2 * cellHeight, 2 * cellWidth, 3 * cellHeight);
                line(cellWidth, 3 * cellHeight, 2 * cellWidth, 2 * cellHeight);

            }
            if (field == 9) {
                stroke(255);
                strokeWeight(2);

                line(2 * cellWidth, 2 * cellHeight, 3 * cellWidth, 3 * cellHeight);
                line(2 * cellWidth, 3 * cellHeight, 3 * cellWidth, 2 * cellHeight);
            }
        }
    }

    /**
     * Die sichtbare Methode zeichen das Symbol je nach dem Spieler
     *
     * @param field  ist das Feld , aif der ein Spieler geklickt hat
     * @param player ist der Player , der gearade spielt
     */
    public void zeichnePressedField(int field, int player) {
        if (player == 1) {
            drawSymbol(field, 1);
        } else if (player == 2) {
            drawSymbol(field, 2);
        }
    }

    /**
     * Die sichtbare Methode die die Game-Over Nachricht , wenn das Spiel fertig ist
     *
     * @param message ist die Nachricht , die am Ende des Spiels erscheinen muss
     */

    public void showGameOverMessage(String message) {
        textSize(50);
        textAlign(CENTER);
        text(message, width / 2, height / 2);

    }
}

