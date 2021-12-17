import java.util.ArrayList;

public class Verzeichnis {

    private ArrayList<Person> listePerson;

    public Verzeichnis(ArrayList<Person> listeReisekosten){
        this.listePerson = listeReisekosten;
    }
    public ArrayList<Person> getlistePerson(){
        return this.listePerson;
    }
}
