import java.util.ArrayList;

/**
 * Die Klasse Grid bildet ein 300x300 Grid, vom typ ArrayList, ab.
 * Jedes Element im Grid besteht wiederum auch aus einer ArrayList.
 * Alle Stations werden in das Grid eingefügt und daraufhin weiter bearbeitet bzw. ausgelesen (Effizient)
 */
public class Grid {
    private ArrayList<Station>[][] grid;
    private ArrayList<Station> airportList = new ArrayList<Station>();

    /**
     * Constructor. Wird ein new() Objekt erzeugt, wird auch GridInit() ausgeführt.
     */
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
     * @return counter-Array vom Typ int[]. Index 1: Airportcounter, Index 2: Trainstationcounter
     */
    public int[] getStations(double x, double y, double radius){
        int[] counter = new int[3]; // Index 1: Airportcounter, Index 2: Trainstationcounter
        int xAdapted = (int)Math.abs(x/70); //x Coord angepasst für den Grid
        int yAdapted = (int)Math.abs(y/70); //y Coord angepasst für den Grid
        int radiusAdapted = (int) (radius/70) + 1;

        int aussen = 1;
        int innen = 1;
        for(int i = Math.abs(xAdapted - radiusAdapted); i <= xAdapted + radiusAdapted; i++)
        {
            // System.out.println(aussen + ". Durchlauf der äußeren Schleife");
            for(int j = Math.abs(yAdapted - radiusAdapted); j <= yAdapted + radiusAdapted; j++) {
                // System.out.println("\t" + innen + ". Durchlauf der inneren Schleife");
                for(Station s : grid[i][j]){
                    if(s.getDistance(x,y) <= radius) {
                        if(s.getType() == TypeEnum.AIRPORT){
                            counter[1]++;
                        }else if(s.getType() == TypeEnum.TRAINSTATION){
                            counter[2]++;
                        }
                    }

                }
            }
            innen = 1;
        }
        return counter;
    }

    /**
     * Berechnet und gibt, die Anzahl aller Flughäfen,
     * in deren r-Längeneinheiten-Umkreis (radiusFromAirport)
     * sich mindestens n Bahnhöfe (trainstationsFromAirport) beﬁnden, aus.
     * @param trainstationsFromAirport
     * @param radiusFromAirport
     * @return airports-Counter vom Typ int
     */
    public int getStationsFromAirport(int trainstationsFromAirport, double radiusFromAirport){
        int[] counter;
        int airports = 0;

        for(Station air : airportList){
            counter = getStations(air.getX(), air.getY(), radiusFromAirport);
            if(counter[2] >= trainstationsFromAirport) {
                airports++;
            }
        }
        return airports;
    }
}
