import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- EP2 Teamarbeit V0.1 ---");

        ArrayList<Station> stations = getStationsFromFile();

        stations.sort(Comparator.comparingDouble(Station::getDistance)); //SORT LIST

        /*
        for(Station s : stations){
            System.out.println(s.getDistanceFromZero() + "   " + s.toString());
        }*/

        ArrayList<Station> stationsFromCoord = getStationsFromCoord(stations, 4240.270712914558,4492.579044115013, 200.0);
        for(Station s:stationsFromCoord){
            System.out.println(s.getDistance() + "   " + s.toString());
        }


    }

    private static ArrayList<Station> getStationsFromCoord(ArrayList<Station> stations, double x, double y, double radius){
        double distance = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));//Distance den gesuchten Koordinaten
        double low = distance - radius; // Untergrenze
        double high = distance + radius; //Obergrenze
        ArrayList<Station> tempList = new ArrayList<>();

        for(Station s : stations){

            if(s.getDistance() >= low && s.getDistance() <= high){ //Verlgeich ob die distnz in der Grenze liegt
                tempList.add(s);
            }
        }

        return  tempList; //Return wert eine arrayliste mit den Stationen die das Kriterium erfüllen

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
            System.out.println("Counter: " + counter);
        }catch (FileNotFoundException e){
            System.exit(1);
        }

        return stations;
    }

}
