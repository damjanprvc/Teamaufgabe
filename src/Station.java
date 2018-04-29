public class Station {
    private double x;
    private double y;
    private String type;
    private double distance; //Varibale in die die Distance abgespeichert wird


    public Station(double x, double y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
        distance = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
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

    //Gibt die Distanz jeder Station vom NUll punkt an (nur ein Versuch)
    public double getDistance(){
        return distance;
    }

    @Override
    public String toString() {
        return "Station{" +
                "x=" + x +
                ", y=" + y +
                ", type='" + type + '\'' +
                '}';
    }
}
