import controlP5.*;  // control5 importieren
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.util.Random;
/**
 * Comtrolp 5
 */
import static controlP5.ControlP5Constants.ACTION_RELEASE; // importiert die coontrolP5 Bibliotheke
public class Anwendung extends PApplet {
    public static void main(String[] args) {
        PApplet.main(Anwendung.class);
    }

    private ControlP5 cp5;
    private Button loadButton; // Button für das Loadbutton
    private Slider sizeSlider;
    private PImage pokemon;
    private int size = 300;
    private EnabledButton enabledButton;
    private int index;
    private EnabledSlider slider;

    @Override
    public void settings() {
        setSize(600, 400);
        pixelDensity(2);
    }

    @Override
    public void setup() {
        // All ControlP5 elements are initialized inside the setup method
        // They can be accessed later through the class variables

        // Creates a ControlP5 object for the current PApplet
        cp5 = new ControlP5(this);
        /**
         * control5 ist immer im setup()
         *Alternative zu dem oberen Text
         */
        var loadingLebel = cp5.addTextlabel("LoadLabel", "Hallo -World");
        loadingLebel.setPosition(100, 5);
        loadingLebel.setText("Beliebiges Pokemon hochladen  ");
        loadingLebel.setColor(0);
        loadingLebel.setFont(createFont("Arial", 30));
        // Button to load the pokemon with the index in pokedexField
        loadButton = cp5.addButton("Load any Pokemon ");
        loadButton.setPosition(240, 50); // 10px zwischen Text und Button
        loadButton.setSize(100, 25);
        loadButton.setColorBackground(color(25, 200, 0));
        enabledButton = new EnabledButton(loadButton);
        //  enabledButton.setDisabbled(true);
        // The button calls the given method on button-click release. Release has the advantage that after the user
        // clicked the button, he/she can decide not to activate the methode by moving the mouse out of the button
        // before releasing.
        // Slider to set the size of the picture
        sizeSlider = cp5.addSlider("Size");
        sizeSlider.setPosition(200, 80);
        sizeSlider.setSize(180, 25);
        sizeSlider.setLabel("");
        sizeSlider.setRange(1, 2 * height / 3);
        sizeSlider.setValue(size); //La valeur que le nombre aura au depart
        // The following listener is called whenever the Slider changes
        slider = new EnabledSlider(sizeSlider);
        //////  slider.setLock(true);
    }

    public void keyPressed() {
        if (key == 'e') {
            System.out.println("Einschlaten ");
            enabledButton.setDisabbled(true);
            slider.setLock(true);
        } else if (key == 'a') {
            System.out.println("Ausschalten");
            enabledButton.setDisabbled(false);
            slider.setLock(false);
        }
    }

    public void draw() {
        // Note that the draw method does not draw Control-P5 UI elements.
        // These elements automatically draw themselves, unless they are hidden.
        background(255);
        // Draw separator between GUI elements and image
        fill(color(200, 200, 200));
        noStroke();
        rect(0, 0, width, height / 3);
        fill(0);
        textSize(18);
        text("Image Size:", 100, sizeSlider.getPosition()[1] + 20); // Texte en haut du load button

        // Draw image if one is available
        if (pokemon != null) {

            image(pokemon, 150, height / 3, size, size); // les hauteurs variees
            tint(255);
        }
    }

    public class EnabledButton {
        private Button button;
        private boolean zustand;

        public EnabledButton(Button button) {
            this.button = button;
        }

        public void setDisabbled(boolean state) {
            // BackgroundColor
            button.addListenerFor(ACTION_RELEASE, new CallbackListener() { //anonyme Klasse . Die Methode wird ausgeführt , wenn der der benutzer die Methode loslässt .
                @Override
                public void controlEvent(CallbackEvent callbackEvent) {
                    if (state == true) {

                        zustand = true;
                        // For MVC-Applications inside this method should only be a call of a controller-method.
                        try {
                            index = (int) (Math.random() * 150) + 1;
                            String urlTemplate = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/%03d.png";


                            // RequestImage does not block the method call, but rather loads the image in the background (=Thread)

                            pokemon = loadImage(String.format(urlTemplate, index));
                        } catch (Exception e) {
                            // Il n'yaura pas d'exeption
                        }
                    } else {
                        zustand = false;

                       // button.remove();
                    }
                }
            });
            if (state == false) {
                button.setLock(true);
                button.setColorBackground(color(0, 0, 0));
                button.setColorForeground(color(0, 0, 0));
                button.setColorActive(color(0, 0, 0));
            }
        }

        public boolean isEnabled() {
            return zustand;
        }
    }

    public class EnabledSlider {
        private Slider slider;
        private boolean zustand;

        public EnabledSlider(Slider slider) {
            this.slider = slider;
        }

        public void setLock(boolean state) {
// call a listener variable
            var callSize = new ControlListener() {
                @Override
                public void controlEvent(ControlEvent controlEvent) {
                        size = (int) slider.getValue(); // Plus on avance la souris , plus la value est grand
                }
            };
            if (state == true) {
                zustand = true;
                size = (int) slider.getValue(); // Plus on avance la souris , plus la value est grand
                slider.addListener(callSize); // Appelle tous les elements
                callSize.controlEvent(null);

                 } else {
                zustand = false;
                slider.setLock(true); // Deaktiviere den Slider
                slider.setColorBackground(color(0, 0, 0));
                slider.setColorForeground(color(0, 0, 0));
                slider.setColorActive(color(0, 0, 0));
            }
        }
        public boolean isEnabled() {
            return zustand;
        }

    }
        }





