import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- EP2 Teamarbeit V0.1 ---");

        ArrayList<Station> stations = getStationsFromFile();

        for(Station s : stations){
            System.out.println(s.toString());
        }
    }

    //TEST KOMMENTAR FÜR GIT WIEDER LÖSCHEN
    //TEST 2 FÜR GIT
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
