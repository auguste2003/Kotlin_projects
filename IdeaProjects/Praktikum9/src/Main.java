import processing.core.PApplet;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


        public class Main extends PApplet {
            public static void main(String[] args) {
                PApplet.main(Main.class);
            }
            // ici se trouve tout le code processing

            // Definiere eine Klasse für die Objekte
            class GameObject {
                float x, y; // Position des Objekts
                float speed; // Geschwindigkeit des Objekts

                GameObject(float x, float y, float speed) {
                    this.x = x;
                    this.y = y;
                    this.speed = speed;
                }

                void move() {
                    // Hier könntest du die Bewegungslogik implementieren
                    // In diesem Beispiel bewegen sich die Objekte horizontal
                    x += speed;
                    if (x > width || x < 0) {
                        speed *= -1; // Ändere die Richtung, wenn das Objekt das Spielfeld verlässt
                    }
                }

                void display() {
                    // Hier könntest du die Darstellung des Objekts implementieren
                    fill(255);
                    ellipse(x, y, 20, 20);
                }
            }

            // Liste für die Objekte
            ArrayList<GameObject> objects = new ArrayList<GameObject>();
public void settings(){
    size(400, 200);
}
           public  void setup() {

                // Erstelle zwei Objekte und füge sie der Liste hinzu
                objects.add(new GameObject(50, height / 2, 2));
                objects.add(new GameObject(150, height / 2, 1));
            }

          public   void draw() {
                background(0);

                // Bewege und zeige alle Objekte in der Liste
                for (GameObject obj : objects) {
                    obj.move();
                    obj.display();
                }

                // Verbinde die Objekte mit Linien
                connectObjects();

            }

            void connectObjects() {
                stroke(255);
                for (int i = 0; i < objects.size() - 1; i++) {
                    GameObject obj1 = objects.get(i);
                    GameObject obj2 = objects.get(i + 1);
                    line(obj1.x, obj1.y, obj2.x, obj2.y);
                }
            }

        }


