import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- EP2 Teamarbeit V1.0 ---");

        ArrayList<Station> stations = getStationsFromFile();

        //stations.sort(Comparator.comparingDouble(Station::getDistance)); //SORT LIST
        /*
        for(Station s : stations){
            System.out.println(s.getDistanceFromZero() + "   " + s.toString());
        }*/

        double x = 4240.270712914558;
        double y = 4492.579044115013;
        double radius = 100.0;
        int counter = 0;
        int airportsCounter = 0;
        int trainstationCounter = 0;

        ArrayList<Station> stationsFromCoord = getStationsFromCoord(stations, x,y, radius);

        //Ausgabe von den gefilterten Stationen
        System.out.println("Junctions less than " + radius + " unit from x = " + x + " y = " + y);
        for(Station s:stationsFromCoord){
            if(s.getType().equals("AIRPORT"))
            {
                airportsCounter++;
            }else{
                trainstationCounter++;
            }
            System.out.println(s.toString());
            counter++;
        }
        System.out.println();
        System.out.println("  > Airports: " + airportsCounter + "  Trainstations: " + trainstationCounter);
        System.out.println("Counter: " + counter);
        System.out.println("-------------------------------------------------------");

        /*
        //ALLES NOCH GESCHEID IN EINE METHODE REINHAUEN UND BERECHNUNG VERBESSERN UND EFFIZIENTER GESTALTEN z.B. Schleifen minimieren
        int trainstationsFromAirport = 20;  //USER EINGABE
        double radiusFromAirport = 15.0; //USEREINGABE
        airportsCounter = 0; //zählt die Flughäfen die die Bedingung erfüllen
        trainstationCounter = 0; //zählt die Trainstations im radius
        System.out.println("Airports with at least " + trainstationsFromAirport + " Trainstations less than " + radiusFromAirport + " away");

        for(Station s : stations){ //Durch Stationsliste durchlaufen
            if(s.getType().equals("AIRPORT")){ //Nach Airports checken
                for(Station s1 : getStationsFromCoord(stations,s.getX(),s.getY(),radiusFromAirport)){ //Für jeden Airport alle Stationen im Radius abspeichern und durch iterieren
                    if(s1.getType().equals("TRAINSTATION")){ // falls Station eine Trainstation, dann Trainstation counter eröhen
                        trainstationCounter++;

                        if(trainstationCounter >= trainstationsFromAirport){ //Wenn trainstatiocounter mit der Usereingabe übereinstimmen airportcounter erhöhen
                            airportsCounter++;
                        }
                    }

                }
                trainstationCounter = 0; //trainstationcounter wieder null setzen für den nöchsten airport
            }
        }
        System.out.println("  > " + airportsCounter);
        */

        // ---------------
        // 2D-GRID
        // ---------------
        // Berechnung der GRID Größe - Siehe https://algs4.cs.princeton.edu/lectures/99GeometricSearch-2x2.pdf
        double maxx = 0.0;
        double maxy = 0.0;
        int c = 0;
        for(Station s: stations){
            if(s.getX() > maxx) maxx = s.getX();
            if(s.getY() > maxy) maxy = s.getY();
            c++;
        }
        System.out.println("MAX X: "+ maxx);
        System.out.println("MAX Y: "+ maxy);
        System.out.println("Anz Stationen: "+ c);

        // Initialise GRID
        // Jedes Grid Element Enthält eine Liste mit den darin enthaltenen Stationen
        ArrayList[][] grid = new ArrayList[300][300];
        for(int i = 0; i < 300; i++)
            for(int j = 0; j < 300; j++)
                grid[i][j] = new ArrayList<Station>();

        // Insert Points in Grid

    }

    private static ArrayList<Station> getStationsFromCoord(ArrayList<Station> stations, double x, double y, double radius){
        ArrayList tempList = new ArrayList();

        for(Station s: stations){
            if(s.getDistance(x,y) <= radius){ //verlgeicht ob distance vom Startpunkt zur nächsten Station im Radius liegt
                tempList.add(s);
            }
        }

        return tempList;
    }

    private static ArrayList<Station> getStationsFromFile(){

        ArrayList<Station> stations = new ArrayList<>();

        try (Scanner s = new Scanner(
                new File(System.getProperty("user.dir") +
                        "/data/junctions.csv"), "UTF-8")){
            String line;
            int counter = 0;
            Scanner temp = null;

            // Benutzung von Scanner s
            while(s.hasNext()){
                if(s.hasNextLine()){
                    line = s.nextLine();
                    temp = new Scanner(line).useDelimiter(";");
                    temp.next();
                    stations.add(new Station(Double.parseDouble(temp.next()), Double.parseDouble(temp.next()), temp.next()));
                    counter++;
                }

            }
            temp.close();
            s.close();

        }catch (FileNotFoundException e){
            System.exit(1);
        }

        return stations;
    }

}
