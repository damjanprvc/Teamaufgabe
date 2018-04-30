public class Station {
    private double x;
    private double y;
    private String type;
    private double distanceToStart;

    public Station(double x, double y, String type) {
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

    public String getType() {
        return type;
    }

    //Gibt die Distanz zum Startpunkt zurück
    public double getDistance(double x1, double y1){
        //Formel = Pythagoras mit (P1.X - P2.X)^2 fpr Y auch
        double sumX = x - x1;
        double sumY = y - y1;
        double distance = Math.hypot(sumX,sumY);
        distanceToStart = distance; // distance für die Klassenvariable für den toString abgespeichert
        return distance ;
    }

    @Override
    public String toString() {
        return "Station{"+"Distance zum Start: "+distanceToStart+
                "              x=" + x +
                ", y=" + y +
                ", type='" + type + '\'' +
                '}';
    }
}
