/**
 * Die Klasse Station bildet eine Station mit den nötigen Properties: (x, y), type und distanceToStart ab.
 */
public class Station {
    private double x;
    private double y;
    private TypeEnum type;
    private double distanceToStart;

    public Station(double x, double y,  TypeEnum type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public TypeEnum getType() {
        return type;
    }

    /**
     * Gibt die Distanz zum übergebenen Startpunkt (x1, y1) zurück
     * @param x1
     * @param y1
     * @return Distanz zum übergebenen Startpunkt (double)
     */
    public double getDistance(double x1, double y1){
        //Formel = Pythagoras mit (P1.X - P2.X)^2 fpr Y auch
        double sumX = x - x1;
        double sumY = y - y1;
        double distance = Math.sqrt(Math.pow(sumX,2) + Math.pow(sumY,2));
        distanceToStart = distance; // distance für die Objektnvariable für den toString abgespeichert
        return distance ;
    }

    @Override
    public String toString() {
        return "Station{"+"Distance zum Start: " + distanceToStart +
                " x=" + x +
                ", y=" + y +
                ", type='" + type + '\'' +
                '}';
    }
}
