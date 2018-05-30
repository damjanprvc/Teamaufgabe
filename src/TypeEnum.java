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

