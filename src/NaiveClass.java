import java.util.ArrayList;

public class NaiveClass {

    private double x;
    private double y;
    private double radius;
    private ArrayList<Station> stations;
    private int[] counterArray; //Index 1: Airportcounter, Index 2: Trainstationcounter
    private int trainstationsFromAirport;
    private double radiusFromAirport;


    //Konstruktor für die 1. Teilaufgabe
    public NaiveClass(ArrayList<Station> stations, double x, double y, double radius){
        this.stations = stations;
        this.x = x;
        this.y = y;
        this.radius = radius;

    }

    //Konstruktor für die 2. TeilAufgbae
    public NaiveClass(ArrayList<Station> stations, int trainstationsFromAirport, double radiusFromAirport){
        this.stations = stations;
        this.trainstationsFromAirport = trainstationsFromAirport;
        this.radiusFromAirport = radiusFromAirport;
    }

    //Gibt alle Station vom Punkt(x,y) im Radius aus
    public void printOutStations(){
        int counter = 0;
        counterArray = new int[3];
        ArrayList<Station> stationsFromCoord = getStationsFromCoord(stations, x,y, radius);

        //Ausgabe von den gefilterten Stationen
        System.out.println("Junctions less than " + radius + " unit from x = " + x + " y = " + y);
        for(Station s:stationsFromCoord){
            if(s.getType() == TypeEnum.AIRPORT) {
                counterArray[1]++; //Airportcounter
            }else{
                counterArray[2]++; //Trainstationcounter
            }
            counter++; //Gesamtcounter
        }
        System.out.println();
        System.out.println("  > Airports: " + counterArray[1] + "  Trainstations: " + counterArray[2]);
        // System.out.println("Counter: " + counter);
        System.out.println("-------------------------------------------------------");

    }

    public void printOutStationsFromAirport(){

        counterArray = new int[3];

        System.out.println("Airports with at least " + trainstationsFromAirport + " Trainstations less than " + radiusFromAirport + " away");

        for(Station s : stations){ //Durch Stationsliste durchlaufen
            if(s.getType() == TypeEnum.AIRPORT){ //Nach Airports checken
                for(Station s1 : getStationsFromCoord(stations,s.getX(),s.getY(),radiusFromAirport)){ //Für jeden Airport alle Stationen im Radius abspeichern und durch iterieren
                    if(s1.getType() == TypeEnum.TRAINSTATION){ // falls Station eine Trainstation, dann Trainstation counter erÃ¶hen
                        counterArray[2]++; //Trainstation COunter

                        if(counterArray[2] >= trainstationsFromAirport){ //Wenn trainstatiocounter mit der Usereingabe Ã¼bereinstimmen airportcounter erhÃ¶hen
                            counterArray[1]++; //Airport counter
                            break; //wenn mind. anzahl erfüllt ist, brech die schleife ab um unnötige Vergleiche zu vermeiden und den counter unnötigen zu erhöhen
                        }
                    }

                }
                counterArray[2] = 0; //trainstationcounter wieder null setzen für den nächsten airport
            }
        }
        System.out.println("  > " + counterArray[1]);
    }


    private static ArrayList<Station> getStationsFromCoord(ArrayList<Station> stations, double x, double y, double radius){
        ArrayList tempList = new ArrayList();

        for(Station s: stations){
            if(s.getDistance(x,y) <= radius){ //verlgeicht ob distance vom Startpunkt zur nÃ¤chsten Station im Radius liegt
                tempList.add(s);
            }
        }

        return tempList;
    }





}
