import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Die Klasse Grid bildet ein 300x300 Grid, vom typ ArrayList, ab.
 * Jedes Element im Grid besteht wiederum auch aus einer ArrayList.
 * Alle Stations werden in das Grid eingefügt und daraufhin weiter bearbeitet bzw. ausgelesen (Effizient)
 */
public class Grid {
    private ArrayList<Station>[][] grid;
    private int[] counterArray = new int[3]; //Index 1: Airportcounter, Index 2: Trainstationcounter
    private ArrayList<Station> airportList = new ArrayList<Station>();

    public Grid() {
        GridInit();
    }

    /**
     * Initialisiert ein 300x300 Grid (Zweidimensionales Array)
     * Jedes Element besteht aus einer Liste
     */
    private void GridInit(){
        grid = new ArrayList[300][300];
        for(int i = 0; i < 300; i++)
            for(int j = 0; j < 300; j++)
                grid[i][j] = new ArrayList<Station>();
    }

    /**
     * Fügt alle Stationen aus der übergebenen ArrayList in das Grid
     * @param stations Liste von Stations, die in das Grid eingefügt werden sollen
     */
    public void addPointsInGrid(ArrayList<Station> stations){
        for(Station s : stations){
            grid[(int)Math.abs(s.getX())/70][(int)Math.abs(s.getY())/70].add(s);

            if (s.getType() == TypeEnum.AIRPORT){
                airportList.add(s);
            }
        }
    }

    /**
     * Gibt die Anzahl aller Airports und Trainstations aus, die sich innerhalb des radius,
     * ausgehend vom Punkt (x, y) befinden.
     * @param x X-Koordinate der Usereingabe
     * @param y Y-Koordinate der Usereingabe
     * @param radius
     */
    public void printStations(double x, double y, double radius){
        int xAdapted = (int)Math.abs(x/70); //x Coord angepasst fÃ¼r den Grid
        int yAdapted = (int)Math.abs(y/70); //y Coord angepasst fÃ¼r den Grid
        int radiusAdapted = (int) (radius/70) + 1;

        // int i = Math.abs(xAdapted - radiusAdapted);
        // int j = Math.abs(yAdapted - radiusAdapted);
        /*
        if (xAdapted - radiusAdapted < 0)
            i = 0;
        if (yAdapted - radiusAdapted < 0)
            j = 0;
            */
        int aussen = 1; int innen = 1;
        for(int i = Math.abs(xAdapted - radiusAdapted); i <= xAdapted + radiusAdapted; i++)
        {
            // System.out.println(aussen + ". Durchlauf der äußeren Schleife");
            for(int j = Math.abs(yAdapted - radiusAdapted); j <= yAdapted + radiusAdapted; j++) {
                // System.out.println("\t" + innen + ". Durchlauf der inneren Schleife");
                for(Station s : grid[i][j]){
                    if(s.getDistance(x,y) <= radius) {
                        if(s.getType() == TypeEnum.AIRPORT){
                            counterArray[1]++;

                        }else if(s.getType() == TypeEnum.TRAINSTATION){
                            counterArray[2]++;
                        }
                    }

                }
            }
            innen = 1;
        }
        System.out.println("Junctions less than " + radius + " unit from x = " + x + " y = " + y);
        System.out.println("  > Airports: " + counterArray[1] + "  Trainstations: " + counterArray[2]);
    }

    public void printStationsFromAirport(int trainstationsFromAirport, double radiusFromAirport){
        counterArray[1] = 0;
        counterArray[2] = 0;

        for(Station air : airportList){
            int xAdapted = (int)Math.abs(air.getX()/70);
            int yAdapted = (int)Math.abs(air.getY()/70);
            int radiusAdapted = (int) (radiusFromAirport/70) + 1;

            for(int i = Math.abs(xAdapted - radiusAdapted); i <= xAdapted + radiusAdapted; i++) {
                for(int j = Math.abs(yAdapted - radiusAdapted); j <= yAdapted + radiusAdapted; j++) {
                    for (Station s : grid[i][j]){
                        if(s.getType() == TypeEnum.TRAINSTATION && air.getDistance(s.getX(), s.getY()) <= radiusFromAirport){
                            counterArray[2]++; //Trainstation Counter
                            if(counterArray[2] >= trainstationsFromAirport){ //Wenn trainstatiocounter mit der Usereingabe Ã¼bereinstimmen airportcounter erhÃ¶hen
                                counterArray[1]++; //Airport counter
                                break; //wenn mind. anzahl erfüllt ist, brech die schleife ab um unnötige Vergleiche zu vermeiden und den counter unnötigen zu erhöhen
                            }
                        }
                    }
                }
            }
            counterArray[2] = 0;
        }

        System.out.println("Airports with at least " + trainstationsFromAirport + " Trainstations less than " + radiusFromAirport + " away");
        System.out.println("  > " + counterArray[1]);
    }
}
