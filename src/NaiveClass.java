import java.util.ArrayList;

/**
 * Die Klasse NaiveClass bildet die Naive Datenstruktur (eine ArrayList) ab.
 * Jedes Element ist eine Station
 */
public class NaiveClass {
    private ArrayList<Station> stations;
    private int[] counterArray;//Index 1: Airportcounter, Index 2: Trainstationcounter
    private int trainstationsFromAirport;
    private double radiusFromAirport;


    /**
     * Fügt die aus der Main eingelesenen Stations, in die Objektliste stations.
     * @param stations
     */
    public void addPointsInList(ArrayList<Station> stations){
        this.stations = stations;
    }

    /**
     * Gibt die Anzahl aller Airports und Trainstations aus, die sich innerhalb des radius,
     * ausgehend vom Punkt (x, y) befinden.
     * @param x X-Koordinate der Usereingabe
     * @param y Y-Koordinate der Usereingabe
     * @param radius
     */
    public void printStations(double x, double y, double radius){
        counterArray = new int[3];
        ArrayList<Station> stationsFromCoord = getStationsFromCoord(x, y, radius);

        for(Station s : stationsFromCoord){
            if(s.getType() == TypeEnum.AIRPORT) {
                counterArray[1]++; //Airportcounter
            }else{
                counterArray[2]++; //Trainstationcounter
            }
        }

        System.out.println();
        System.out.println("Junctions less than " + radius + " unit from x = " + x + " y = " + y);
        System.out.println("  > Airports: " + counterArray[1] + "  Trainstations: " + counterArray[2]);
    }

    /**
     *  Berechnet und gibt, die Anzahl aller Flughäfen,
     *  in deren r-Längeneinheiten-Umkreis (radiusFromAirport)
     *  sich mindestens n Bahnhöfe (trainstationsFromAirport) beﬁnden, aus.
     * @param trainstationsFromAirport
     * @param radiusFromAirport
     */
    public void printStationsFromAirport(int trainstationsFromAirport, double radiusFromAirport){
        System.out.println("Calculating........");
        counterArray = new int[3];

        trainstationsFromAirport = 20; //testdaten ToDo: Rauslöschen wenn nicht benötigt
        radiusFromAirport = 15;

        for(Station s : stations){ //Durch Stationsliste durchlaufen
            if(s.getType() == TypeEnum.AIRPORT){ //Nach Airports checken
                for(Station s1 : getStationsFromCoord(s.getX(),s.getY(),radiusFromAirport)){ //Für jeden Airport alle Stationen im Radius abspeichern und durch iterieren
                    if(s1.getType() == TypeEnum.TRAINSTATION){ // falls Station eine Trainstation, dann Trainstation counter erÃ¶hen
                        counterArray[2]++; //Trainstation Counter
                        if(counterArray[2] >= trainstationsFromAirport){ //Wenn trainstatiocounter mit der Usereingabe Ã¼bereinstimmen airportcounter erhÃ¶hen
                            counterArray[1]++; //Airport counter
                            break; //wenn mind. anzahl erfüllt ist, brech die schleife ab um unnötige Vergleiche zu vermeiden und den counter unnötigen zu erhöhen
                        }
                    }
                }
                counterArray[2] = 0; //trainstationcounter wieder null setzen für den nächsten airport
            }
        }

        System.out.println("Airports with at least " + trainstationsFromAirport + " Trainstations less than " + radiusFromAirport + " away");
        System.out.println("  > " + counterArray[1]);
    }

    /**
     * Returnt alle Stations, die sich innerhalb der übergebenen Radius befinden
     * @param x
     * @param y
     * @param radius
     * @return
     */
    private ArrayList<Station> getStationsFromCoord(double x, double y, double radius){
        ArrayList tempList = new ArrayList();

        for(Station s: stations){
            if(s.getDistance(x,y) <= radius){ //verlgeicht ob distance vom Startpunkt zur nÃ¤chsten Station im Radius liegt
                tempList.add(s);
            }
        }

        return tempList;
    }
}
