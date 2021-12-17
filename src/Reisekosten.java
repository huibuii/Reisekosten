import java.time.LocalDate;
import java.util.ArrayList;

public class Reisekosten {
    private String fahrer;
    private int anzahlPersonen;
    private LocalDate datum;
    private double kosten;
    private ArrayList<Person> mitfaher;

    public Reisekosten(int anzahlPersonen, LocalDate datum, double kosten, String fahrer){
        this.anzahlPersonen = anzahlPersonen;
        this.fahrer = fahrer;
        this.datum = datum;
        this.kosten = kosten;
    }

    public int getAnzahlPersonen() {
        return this.anzahlPersonen;
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

    public void setAnzahlPersonen(int anzahlPersonen) {
        this.anzahlPersonen = anzahlPersonen;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setKosten(double kosten) {
        this.kosten = kosten;
    }

}
