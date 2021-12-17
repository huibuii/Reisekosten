import java.util.ArrayList;

public class Person {
    private ArrayList<Reisekosten> listeReisekosten;
    private String name;

    public Person(String name){
        this.listeReisekosten = new ArrayList<Reisekosten>();
        this.name = name;
    }

    public void fuegeNeueReiseHinzu(Reisekosten reisekosten){
        listeReisekosten.add(reisekosten);
        System.out.println("Der Eintrag wurde hinzugefügt");
    }
    public void entferne(Reisekosten reisekosten){
        listeReisekosten.remove(reisekosten);
        System.out.println("Der Eintrag wurde entfernt");
    }

    public ArrayList<Reisekosten> getListeReisekosten() {
        return listeReisekosten;
    }

    public void setListeReisekosten(ArrayList<Reisekosten> listeReisekosten) {
        this.listeReisekosten = listeReisekosten;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
