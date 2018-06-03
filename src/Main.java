import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Dieses Programm liest alle Stations (Trainstations & Airports) aus der Datei "junctions.csv"
 * und verarbeitet sie entsprechend weiter.
 * Dabei stehen 2 Varianten zur verfügung:
 * - Naive Methode: Stations werden in einer Liste gespeichert und beim suchen nacheinander durchiteriert
 * - Effiziente Mehode (GRID): Stations werden in einem Grid gespeichert und beim suchen werden NUR die jeweiligen Grid-Elemente durchsucht
 *
 * @author Damjan Prvulovic
 * @author Abhishek Singh
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- EP2 Teamarbeit V1.0 ---");

        // Programinitsialisierung
        ArrayList<Station> stations = getStationsFromFile(); //Daten vom File einlesen
        Scanner sc = new Scanner(System.in); // Scanner für den Userinput
        int userInput;
        double x = 0, y = 0, radius = 0;
        int trainstationsFromAirport;  //USEREINGABE
        double radiusFromAirport; //USEREINGABE
        long startTime, stopTime, elapsedTime;


        System.out.println("Pressen Sie die jeweilige Nummer und drücken Sie Enter");
        System.out.println("1: Naive Methode");
        System.out.println("2: Effiziente Methode");
        System.out.println("0: Quit");
        System.out.println("-------------------------------------------------------");
        System.out.print("> ");

        userInput = sc.nextInt(); //Userinput
        //Menü
        switch (userInput){
            case 0:
                System.out.println("QUITTING PROGRAM");
                System.out.println("------------------------------------");
                break;
            case 1: //Naive
                System.out.println("NAIVE METHODE");
                System.out.println("Pressen Sie die jeweilige Nummer und drücken Sie Enter");
                System.out.println("1: Gebe alle Stationen vom Punkt(x,y) aus");
                System.out.println("2: Gebe alle Flughäfen aus die im Radius r mind. x Bahnhöfe haben");
                System.out.print("> ");
                userInput = sc.nextInt();
                switch (userInput){
                    case 0:
                        System.out.println("QUITTING PROGRAM");
                        System.out.println("------------------------------------");
                        break;
                    case 1: //TEILAUFGABE 1
                        System.out.println("Gebe alle Stationen vom Punkt(x,y) aus");
                        System.out.println("------------------------------------------");

                        //User gibt die x und y und radius ein
                        System.out.println("Geben Sie die jeweiligen Koordinaten ein");
                        System.out.print("x = ");
                        x = sc.nextDouble();
                        System.out.print("y = ");
                        y = sc.nextDouble();
                        System.out.print("radius = ");
                        radius = sc.nextDouble();
                        System.out.println("-----------------------------------");

                        NaiveClass naiveClass = new NaiveClass();
                        startTime = System.nanoTime();
                        naiveClass.addPointsInList(stations);
                        System.out.println("Junctions less than " + radius + " unit from x = " + x + " y = " + y);
                        System.out.println("  > Airports: " + naiveClass.getStations(x,y,radius)[1] + "  Trainstations: " + naiveClass.getStations(x,y,radius)[2]);

                        stopTime = System.nanoTime();

                        elapsedTime = stopTime - startTime;
                        elapsedTime = TimeUnit.MILLISECONDS.convert(elapsedTime,TimeUnit.NANOSECONDS);

                        System.out.println("LAUFZEIT: " + elapsedTime + " ms");

                        break;
                    case 2: //TEILAUFGABE 2
                        System.out.println("Gebe alle Flughäfen aus die im Radius r mind. x Bahnhöfe haben");
                        System.out.println("-----------------------------------------------");
                        System.out.println("Geben Sie die mind. Anzahl der Bahnhöfe ein");
                        System.out.print("trainstationsFromAirport = ");
                        trainstationsFromAirport = sc.nextInt();
                        System.out.println("Geben Sie den jeweiligen Radius vom Flughafen ein");
                        System.out.print("radiusFromAirport = ");
                        radiusFromAirport = sc.nextDouble();
                        System.out.println("-------------------------------------");

                        NaiveClass naiveClass1 = new NaiveClass();

                        startTime = System.nanoTime();

                        naiveClass1.addPointsInList(stations);
                        System.out.println("Airports with at least " + trainstationsFromAirport + " Trainstations less than " + radiusFromAirport + " units away");
                        System.out.println("  > " + naiveClass1.getStationsFromAirport(trainstationsFromAirport, radiusFromAirport));

                        stopTime = System.nanoTime();

                        elapsedTime = stopTime - startTime;
                        elapsedTime = TimeUnit.SECONDS.convert(elapsedTime,TimeUnit.NANOSECONDS);
                        System.out.println("LAUFZEIT: " + elapsedTime + " Sekunden");

                        break;
                    default:
                        System.out.println("Invalid selection");
                }
                System.out.println("-----------------------------------");
                break;
            case 2: //GRID
                System.out.println("EFFIZIENTE METHODE");
                System.out.println("Pressen Sie die jeweilige Nummer und drücken Sie Enter");
                System.out.println("1: Gebe alle Stationen vom Punkt(x,y) aus");
                System.out.println("2: Gebe alle Flughäfen aus die im Radius r mind. x Bahnhöfe haben");
                System.out.print("> ");
                userInput = sc.nextInt();
                switch (userInput){
                    case 0:
                        System.out.println("QUITTING PROGRAM");
                        System.out.println("------------------------------------");
                        break;
                    case 1: //TEILAUFGABE 1
                        System.out.println("Gebe alle Stationen vom Punkt(x,y) aus");
                        System.out.println("------------------------------------------");

                        //User gibt die x und y und radius ein
                        System.out.println("Geben Sie die jeweiligen Koordinaten ein");
                        System.out.print("x = ");
                        x = sc.nextDouble();
                        System.out.print("y = ");
                        y = sc.nextDouble();
                        System.out.print("radius = ");
                        radius = sc.nextDouble();
                        System.out.println("-----------------------------------");

                        startTime = System.nanoTime();

                        Grid grid1 = new Grid();
                        grid1.addPointsInGrid(stations);
                        System.out.println("Junctions less than " + radius + " unit from x = " + x + " y = " + y);
                        System.out.println("  > Airports: " + grid1.getStations(x,y,radius)[1] + "  Trainstations: " + grid1.getStations(x,y,radius)[2]);

                        stopTime = System.nanoTime();

                        elapsedTime = stopTime - startTime;
                        elapsedTime = TimeUnit.MILLISECONDS.convert(elapsedTime,TimeUnit.NANOSECONDS);
                        System.out.println("LAUFZEIT: " + elapsedTime + " ms");

                        System.out.println("-------------------------------------");

                        break;
                    case 2: //TEILAUFGABE 2
                        System.out.println("Gebe alle Flughäfen aus die im Radius r mind. x Bahnhöfe haben");
                        System.out.println("-----------------------------------------------");
                        System.out.println("Geben Sie die mind. Anzahl der Banhöfe ein");
                        System.out.print("trainstationsFromAirport = ");
                        trainstationsFromAirport = sc.nextInt();
                        System.out.println("Geben Sie den jeweiligen Radius vom Flughafen ein");
                        System.out.print("radiusFromAirport = ");
                        radiusFromAirport = sc.nextDouble();
                        System.out.println("-------------------------------------");

                        startTime = System.nanoTime();

                        Grid grid2 = new Grid();
                        grid2.addPointsInGrid(stations);
                        System.out.println("Airports with at least " + trainstationsFromAirport + " Trainstations less than " + radiusFromAirport + " units away");
                        System.out.println("  > " + grid2.getStationsFromAirport(trainstationsFromAirport, radiusFromAirport));

                        stopTime = System.nanoTime();

                        elapsedTime = stopTime - startTime;
                        elapsedTime = TimeUnit.MILLISECONDS.convert(elapsedTime,TimeUnit.NANOSECONDS);
                        System.out.println("LAUFZEIT: " + elapsedTime + " ms");

                        break;
                    default:
                        System.out.println("Invalid selection");
                }
                System.out.println("-----------------------------------");
                break;
            default:
                System.out.println("Invalid selection");
        }
    }

    /**
     * Liest und returnt alle Stations aus dem File "/data/junctions.csv"
     * @return ArrayList<Station> - Liste mit allen Stations
     */
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
                    stations.add(new Station(Double.parseDouble(temp.next()), Double.parseDouble(temp.next()), TypeEnum.valueOf(temp.next())));
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
