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

        ArrayList<Station> stationsFromCoord = getStationsFromCoord(stations, 4240.270712914558,4492.579044115013, 100.0);
        int counter = 0;
        //Ausgabe von den gefilterten Stationen
        for(Station s:stationsFromCoord){
            System.out.println(s.toString());
            counter++;
        }
        System.out.println("Counter: " + counter);
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
