/**
 * Eine Enumeration, die die Verwaltung der Stations Types (Airport, Trainstation) vereinfacht bzw. übersichtlicher gestaltet.
 */
public enum TypeEnum {
    AIRPORT (1),
    TRAINSTATION(2);

    int index;
    TypeEnum(int i){
        index = i;
    }
    
    int showIndex(){
        return index;
    }
}

