import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static ArrayList<Station>[][] grid;
    public static void main(String[] args) {
        System.out.println("--- EP2 Teamarbeit V1.0 ---");



        //Programinitsialisierung
        ArrayList<Station> stations = getStationsFromFile(); //Daten vom File einlesen
        Scanner sc = new Scanner(System.in); // Scanner für den Userinput
        int userInput;
        double x = 0, y = 0, radius = 0;
        int trainstationsFromAirport;  //USER EINGABE
        double radiusFromAirport; //USEREINGABE
        long startTime, stopTime, elapsedTime;
        //-----------------------------------------

        System.out.println("Pressen Sie die jeweilige Nummer und drücken Sie Enter");
        System.out.println("1: Naive Methode");
        System.out.println("2: Effiziente Methode");
        System.out.println("0: Quit");
        System.out.println("-------------------------------------------------------");

        userInput = sc.nextInt(); //Userinput

        //Menü
        switch (userInput){
            case 0:
                System.out.println("QUITTING PROGRAM");
                System.out.println("------------------------------------");
                break;
            case 1:
                System.out.println("NAIVE METHODE");
                //ToDO: Naive Methode aufrufen
                //NaiveClass naiveClass = new NaiveClass(stations,x,y,radius);
                //naiveClass.printOutStations();
                System.out.println("------------------------------------------------------");
                System.out.println("Pressen Sie die jeweilige Nummer und drücken Sie Enter");
                System.out.println("1: Gebe alle Stationen vom Punkt(x,y) aus");
                System.out.println("2: Gebe alle Flughäfen aus die im Radius r mind. x Bahnhöfe haben");
                userInput = sc.nextInt();
                switch (userInput){
                    case 0:
                        System.out.println("QUITTING PROGRAM");
                        System.out.println("------------------------------------");
                        break;
                    case 1:
                        System.out.println("Gebe alle Stationen vom Punkt(x,y) aus");
                        System.out.println("------------------------------------------");

                        //ToDo: evtl sicherstellen das richtige Eingabe eingegeben wurde
                        //User gibt die x und y und radius ein
                        System.out.println("Geben Sie die jeweiligen Koordinaten ein");
                        System.out.print("x = ");
                        x = sc.nextDouble();
                        System.out.print("y = ");
                        y = sc.nextDouble();
                        System.out.print("radius = ");
                        radius = sc.nextDouble();
                        System.out.println("-----------------------------------");


                        NaiveClass naiveClass = new NaiveClass(stations,x,y,radius);
                        startTime = System.nanoTime();
                        naiveClass.printOutStations();
                        stopTime = System.nanoTime();
                        elapsedTime = stopTime - startTime;
                        elapsedTime = TimeUnit.SECONDS.convert(elapsedTime,TimeUnit.NANOSECONDS);

                        System.out.println("LAUFZEIT: " + elapsedTime + " ns");

                        break;
                    case 2:
                        System.out.println("Gebe alle Flughäfen aus die im Radius r mind. x Bahnhöfe haben");
                        System.out.println("-----------------------------------------------");
                        System.out.println("Geben Sie den jeweiligen Radius vom Flughafen ein");
                        System.out.print("radiusFromAirport = ");
                        radiusFromAirport = sc.nextDouble();
                        System.out.println("Geben Sie die mind. Anzahl der Banhöfe ein");
                        System.out.print("trainstationsFromAirport = ");
                        trainstationsFromAirport = sc.nextInt();
                        System.out.println("-------------------------------------");

                        NaiveClass naiveClass1 = new NaiveClass(stations, trainstationsFromAirport, radiusFromAirport);
                        startTime = System.nanoTime();
                        naiveClass1.printOutStationsFromAirport();
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
            case 2:
                System.out.println("EFFIZIENTE METHODE");
                //ToDo: Effiziente Methode aufrufen und switch statement für methodenauswahl implementieren
                System.out.println("-------------------------------------");
                break;
            default:
                System.out.println("Invalid selection");
        }






        /*
        double x = 1818.54657;
        double y = 5813.29982;
        double radius = 100.0; //in km? anpassen irgendwie?
        */



        //stations.sort(Comparator.comparingDouble(Station::getDistance)); //SORT LIST
        /*
        for(Station s : stations){
            System.out.println(s.getDistanceFromZero() + "   " + s.toString());
        }*/




        // ---------------
        // 2D-GRID
        // ---------------
        // Berechnung der GRID GrÃ¶ÃŸe - Siehe https://algs4.cs.princeton.edu/lectures/99GeometricSearch-2x2.pdf
        double maxx = 0.0;
        double maxy = 0.0;
        double minx = 0.0;
        double miny = 0.0;
        int c = 0;
        for(Station s: stations){
            if(s.getX() > maxx) maxx = s.getX();
            if(s.getY() > maxy) maxy = s.getY();
            if(s.getX() < minx) minx = s.getX();
            if(s.getY() < miny) miny = s.getY();
            c++;
        }
        System.out.println("MAX X: "+ maxx);
        System.out.println("MAX Y: "+ maxy);
        System.out.println("MIN X: "+ minx);
        System.out.println("MIN Y: "+ miny);
        System.out.println("Anz Stationen: "+ c);

        // Initialise GRID
        // Jedes Grid Element EnthÃ¤lt eine Liste (nicht-hierarchischeDatenstruktur) mit den darin enthaltenen Stationen
        GridInit();

        // Insert Points in Grid
        addPointsInGrid(stations);

        // TODO: Aus dem Radius und dem gegebenen Punkt die zu durchsuchende FlÃ¤che berechenen
        searchInGrid(x,y,radius);
    }

    private static void GridInit(){
        grid = new ArrayList[300][300];
        for(int i = 0; i < 300; i++)
            for(int j = 0; j < 300; j++)
                grid[i][j] = new ArrayList<Station>();
    }
    private static void addPointsInGrid(ArrayList<Station> stations){
        for(Station s : stations){
            grid[(int)Math.abs(s.getX())/67][(int)Math.abs(s.getY())/49].add(s);
        }
    }

    //Gibt alle Airports und Trainstations aus
    private static void searchInGrid(double x, double y, double radius){
        int xAdapted = (int)x/67; //x Coord angepasst fÃ¼r den Grid
        int yAdapted = (int)y/49; //y Coord angepasst fÃ¼r den Grid
        int radiusAdapted = (int) radius/67; //ToDo: Radius zum Grid anpassen, sowie x und y
        int trainstationCounter = 0;
        int airportCounter = 0;

        int i = xAdapted - radiusAdapted;
        int j = yAdapted - radiusAdapted;
        if (xAdapted - radiusAdapted < 0)
            i = 0;
        if (yAdapted - radiusAdapted < 0)
            j = 0;

        for(; i <= xAdapted + radiusAdapted; i++)
        {
            for(; j <= yAdapted + radiusAdapted; j++)
            {
                for(Station s : grid[i][j]){
                    if(s.getDistance(x,y) <= radius) {
                        if(s.getType().equals("AIRPORT")){
                            airportCounter++;
                        }else if(s.getType().equals("TRAINSTATION")){
                            trainstationCounter++;
                        }
                    }

                }
            }
        }

        System.out.println("Im Radius " + radius + " liegen " + airportCounter + " FlughÃ¤fen und " + trainstationCounter + " BahnhÃ¶fe");
    }

    //Gibt die Anzahl der FlughÃ¤fen aus die mind. x Trainstation im radius y haben
    private static void searchInGrid(int x, int y, int trainstationsFromAirport, int radiusFromAirport){
        // TODO
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
