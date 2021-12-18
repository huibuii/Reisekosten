import java.time.LocalDate;
import java.util.ArrayList;

public class Reisekosten {
    private String fahrer;
    private LocalDate datum;
    private double kosten;
    private ArrayList<Person> mitfahrer;

    public Reisekosten(ArrayList<Person> mitfahrer, LocalDate datum, double kosten, String fahrer){
        this.mitfahrer = mitfahrer;
        this.fahrer = fahrer;
        this.datum = datum;
        this.kosten = kosten;
    }

    public ArrayList<Person> getAnzahlPersonen() {
        return this.mitfahrer;
    }

    public LocalDate getDatum() {
        return this.datum;
    }

    public double getKosten() {
        return this.kosten;
    }

    public String getFahrer() {
        return fahrer;
    }

    public void setFahrer(String fahrer) {
        this.fahrer = fahrer;
    }

    public void setAnzahlPersonen(ArrayList<Person> anzahlPersonen) {
        this.mitfahrer = anzahlPersonen;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setKosten(double kosten) {
        this.kosten = kosten;
    }

}
