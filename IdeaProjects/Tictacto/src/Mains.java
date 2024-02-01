import processing.core.PApplet;

public final class Mains {
    public static void main(String[] args) {
        final int GAME_SIZE = 700;
        var model = new TictactoModel();
        var controller = new TicTacToController();
        //   var view = new TicTactoView(GAME_SIZE, GAME_SIZE);
        var view = new TicTactoView(GAME_SIZE, GAME_SIZE);
        // Connect M, V and C
        controller.setModel(model);
        controller.setView(view);
        view.setController(controller);

        // Starts the processing application
        PApplet.runSketch(new String[]{"EvolisAdventureView"}, view);
    }
}
