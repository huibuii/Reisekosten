import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Person> listePersonen = new ArrayList<Person>();
    private static Verzeichnis verzeichnis = new Verzeichnis(listePersonen);
    private static Person p1, p2;
    private static ArrayList<Reisekosten> reisekosten = new ArrayList<Reisekosten>();

    public static void main(String[] args) throws IOException {

        if (load() == false) System.out.println("Sie haben noch keine Personen hinzugefügt");
        while (true) {
            starten();
            System.out.println("Möchten Sie noch etwas ändern? (y/n)");
            var eingabe = scanner.nextLine();
            if (!eingabe.equalsIgnoreCase("y")) {
                save();
                break;
            }
        }
    }

    private static void save() throws IOException {
        FileWriter myWriter = new FileWriter("C:\\Users\\Service\\Documents\\test.txt");
        for (int i = 0; i < listePersonen.size(); i++) {
            if (listePersonen.get(i).getListeReisekosten().size() != 0) {
                for (Reisekosten reise : listePersonen.get(i).getListeReisekosten()) {
                    var namefahrer = reise.getFahrer();
                    var nameZahler = listePersonen.get(i).getName();
                    var kosten = reise.getKosten();
                    var anzahl = reise.getAnzahlPersonen();
                    var date = reise.getDatum();
                    myWriter.write(nameZahler + ";" + namefahrer + ";" + kosten + ";" + anzahl + ";" + date + "\n");
                }
            } else {
                myWriter.write(listePersonen.get(i).getName() + ";" + "\n");
            }
        }
        myWriter.close();
        System.out.println("Es wurde gespeichert");
    }

    private static boolean load() throws IOException {
        BufferedReader myReader = new BufferedReader(new FileReader("C:\\Users\\Service\\Documents\\test.txt"));
        String text = "";
        int t = 0;
        while (true) {
            text = myReader.readLine();
            if (text != null) {
                String[] daten = text.split(";");
                if (daten.length != 2 && daten.length != 1) {
                    var result = suche(daten[0]);
                    Person p = result.getResult();
                    var index = suche(daten[0]).getIndex();
                    if (index == -1) {
                        p = new Person(daten[0]);
                        listePersonen.add(p);
                    }
                    String[] hilfe = daten[4].split("-");
                    p.fuegeNeueReiseHinzu(new Reisekosten(Integer.parseInt(daten[3]), LocalDate.of(Integer.parseInt(hilfe[0]), Integer.parseInt(hilfe[1]), Integer.parseInt(hilfe[2])), Double.parseDouble(daten[2]), daten[1]));

                } else if (daten.length == 1) {
                    Person p = new Person(daten[0]);
                    listePersonen.add(p);
                } else {
                    Person p = new Person(daten[0]);
                    listePersonen.add(p);
                    p = new Person(daten[1]);
                    listePersonen.add(p);
                }
                t++;
            } else {
                break;
            }
        }
        myReader.close();
        if (t > 0) return true;
        else return false;
    }

    private static void starten() {
        System.out.println("Möchten Sie eine neue Reise hinzufügen? (y/n)");
        var eingabe = scanner.nextLine();
        if (eingabe.equalsIgnoreCase("y")) {

            Person zahler;
            int anzMitfahrer;
            double kosten;
            LocalDate datum;
            String nameFahrer;

            System.out.println("Geben Sie bitte die Daten der Fahrt an:");
            System.out.println("--------------------------------------");

            zahler = suche(ermittleNameZahler()).getResult();
            nameFahrer = ermittleNameFahrer();
            anzMitfahrer = ermittleAnzahlMitfahrer();
            kosten = ermittleKosten();
            datum = ermittleDatum();

            reisekostenZuPersonHinzufuegen(anzMitfahrer, datum, kosten, nameFahrer, zahler);//Der Zahler bekommt die Reisekosten zugewiesen
            //for(Person p : listePersonen)System.out.println(p.getName());
        } else {

        }
    }

    private static SearchResult<Person> suche(String name) {
        for (int i = 0; i < verzeichnis.getlistePerson().size(); i++) {
            var person = verzeichnis.getlistePerson().get(i);
            if (person.getName().equals(name)) {
                return new SearchResult<>(person, i);
            }
        }
        return new SearchResult<>(null, -1);
    }

    private static void reisekostenZuPersonHinzufuegen(int anzMitfahrer, LocalDate datum, double kosten, String nameFahrer, Person zahler) {
        zahler.fuegeNeueReiseHinzu(new Reisekosten(anzMitfahrer, datum, kosten, nameFahrer));
    }

    private static String ermittleNameZahler() {
        String nameZahler = "";
        if (listePersonen.size() > 0) {
            System.out.println("Handelt es sich bei dem Zahler um eine dieser Personen? (y/n) ");
            for (Person p : listePersonen) System.out.println(p.getName());
            var eingabe = scanner.nextLine();
            if (eingabe.equalsIgnoreCase("y")) {
                while (true) {
                    System.out.println("");
                    System.out.println("Geben Sie den Namen des Zahlers an: ");
                    nameZahler = scanner.nextLine();
                    var zahler = suche(nameZahler).getResult();
                    if (zahler != null) return nameZahler;
                    System.out.println("Den Namen gibt es nicht");
                }
            }
        }
        System.out.println("Name Zahler: ");
        nameZahler = neuer().getName();
        return nameZahler;
    }

    private static String ermittleNameFahrer() {
        String nameFahrer;
        if (listePersonen.size() > 0) {
            System.out.println("Handelt es sich bei dem Fahrer um eine dieser Personen? (y/n) ");
            for (Person p : listePersonen) System.out.println(p.getName());
            var eingabe = scanner.nextLine();
            if (eingabe.equalsIgnoreCase("y")) {
                while (true) {
                    System.out.println("");
                    System.out.println("Geben Sie den Namen des Fahrers an: ");
                    nameFahrer = scanner.nextLine();
                    var fahrer = suche(nameFahrer).getResult();
                    if (fahrer != null) return nameFahrer;
                    System.out.println("Den Namen gibt es nicht");
                }
            }
        }
        System.out.println("Name Fahrer: ");
        nameFahrer = neuer().getName();
        return nameFahrer;
    }

    private static Person neuer() {
        Person p;
        while (true) {
            var name = scanner.nextLine();
            if (suche(name) == null) {
                System.out.println("den Namen gibt es schon");
                System.out.println("Bitte erneut eingeben");
            } else {
                p = new Person(name);
                listePersonen.add(p);
                break;
            }
        }
        return p;
    }

    private static double ermittleKosten() {
        double kosten;
        while (true) {
            try {
                System.out.println("");
                System.out.println("Geben Sie die Kosten an: ");
                kosten = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Die Eingabe wurde nicht akzeptiert");
                scanner.nextLine();
            }
        }
        return kosten;
    }

    private static int ermittleAnzahlMitfahrer() {
        int anzMitfahrer;
        while (true) {
            try {
                System.out.println("");
                System.out.println("Geben Sie die Anzahl der Mitfahrer an: ");
                anzMitfahrer = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Die Eingabe wurde nicht akzeptiert");
                scanner.nextLine();
            }
        }
        return anzMitfahrer;
    }

    private static LocalDate ermittleDatum() {
        int jahr;
        int monat;
        int tag;
        while (true) {
            try {
                System.out.println("");
                System.out.println("Geben Sie das Jahr ein: ");
                jahr = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Die Eingabe wurde nicht akzeptiert. ");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.println("");
                System.out.println("Geben Sie den Monat ein: ");
                monat = scanner.nextInt();
                scanner.nextLine();
                if (monat > 0 && monat < 13) break;
                System.out.println("Die Eingabe wurde nicht akzeptiert.");
            } catch (Exception e) {
                System.out.println("Die Eingabe wurde nicht akzeptiert.");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.println("");
                System.out.println("Geben Sie den Tag ein: ");
                tag = scanner.nextInt();
                scanner.nextLine();
                // if(monat == 1, 3, 5, 7, 9, 11, ...) optional
                if (tag > 0 && tag < 31) break;
                System.out.println("Die Eingabe wurde nicht akzeptiert.");
            } catch (Exception e) {
                System.out.println("Die Eingabe wurde nicht akzeptiert.");
                scanner.nextLine();
            }
        }
        return LocalDate.of(jahr, monat, tag);
    }
}
