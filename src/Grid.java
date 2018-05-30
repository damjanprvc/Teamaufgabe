import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Grid {
    static ArrayList<Station>[][] grid;
    private int[] counterArray = new int[3]; //Index 1: Airportcounter, Index 2: Trainstationcounter

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
     * @param stations Liste von Stations, die in das Grid eingefügt werden
     */
    public void addPointsInGrid(ArrayList<Station> stations){
        for(Station s : stations){
            grid[(int)Math.abs(s.getX())/67][(int)Math.abs(s.getY())/49].add(s);
        }
        int counter = 0;
        for(int i = 0; i < 300; i++)
            for(int j = 0; j < 300; j++)
                for (Station temp : grid[i][j]) {
                    // System.out.println(temp);
                    System.out.println(temp.toString());
                    counter++;
                }
                //grid[i][j] = new ArrayList<Station>();
        System.out.println("COUUUUNNTNTER GRID: " + counter);
    }

    /**
     * Gibt die Anzahl aller Airports und Trainstations aus, die sich innerhalb des radius,
     * ausgehend vom Punkt (x, y) befinden.
     * @param x X-Koordinate der Usereingabe
     * @param y Y-Koordinate der Usereingabe
     * @param radius
     */
    public void printStations(double x, double y, double radius){
        int xAdapted = (int)x/67; //x Coord angepasst fÃ¼r den Grid
        int yAdapted = (int)y/49; //y Coord angepasst fÃ¼r den Grid
        int radiusAdapted = (int) radius/67; //ToDo: Radius zum Grid anpassen, sowie x und y

        int i = Math.abs(xAdapted - radiusAdapted);
        int j = Math.abs(yAdapted - radiusAdapted);
        /*
        if (xAdapted - radiusAdapted < 0)
            i = 0;
        if (yAdapted - radiusAdapted < 0)
            j = 0;
            */

        for(; i <= xAdapted + radiusAdapted; i++)
        {
            for(; j <= yAdapted + radiusAdapted; j++)
            {
                for(Station s : grid[i][j]){
                    if(s.getDistance(x,y) <= radius) {
                        if(s.getType() == TypeEnum.AIRPORT){
                            counterArray[1]++;

                        }else if(s.getType() == TypeEnum.TRAINSTATION){
                            counterArray[2]++;
                        }
                        // System.out.println(s.toString());
                    }

                }
            }
        }

        System.out.println("Junctions less than " + radius + " unit from x = " + x + " y = " + y);
        System.out.println("  > Airports: " + counterArray[1] + "  Trainstations: " + counterArray[2]);
    }
}
