import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;

public class ToDo {
    public static String[] todos = new String[10];
    public static String[] todosKopie = new String[10];
    static LocalDate[] daten = new LocalDate[10];
    static LocalDate[] datenKopie = new LocalDate[10];



    //diese Methode erlaubt uns neue Aufgabe als String in das Array hinzufügen,
    //wenn diese Aufgabe noch nicht im Array vorhanden ist. Dabei füge ich neue Aufgabe nur
    //an dijenige Stelle hinzu, die mit "Null" beleget ist. Beim Vergleichen von schon vorghanenen
    //Aufgaben mit dem Titel aus der Eingabe verwende ich ein If-statment, um die NullPointerExeption
    //beim möglichen Vergleich von dem Titel mit einem Element, das mit "Null" belegt ist, zu vermeiden.
    //Gleichzeitig initialisiere ich das i-te Element aus dem Array "daten" mit einem festen Datum, wenn
    //an diese Stelle i im Array "todos" ein neues Element hinzugefügt wird.
    public static boolean neueAufgabe (String titel) {
        for (int i = 0; i < todos.length; i++) {
            if (!(todos[i]==null)) {
                if (titel.equals(todos[i])) {
                    return false;
                }
            }
        }

        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == (null)) {
                todos[i] = titel;
                daten[i] = LocalDate.of(2020, 12, 23);
                return true;
            }
        }
    return false;
    }

    //diese Methode erlaubt uns alle im Array vorhandenen Aufgaben aufzulisten.
    //Dabei liste ich nur mit tatsächlichen Aufgaben belegte Elemente des Arrays
    //(und nicht dijenige Elemente, die mit "Null" belegt sind).
    public static String listeAlleAufgaben () {
        String ergebniss = "Aufgaben auf der Liste:\n";
        for (int i = 0; i < todos.length; i++) {
            if (!(todos[i] == (null))) {
                String Aufgabe = todos[i];
                ergebniss += Aufgabe + "\n";
            }
        }
        return ergebniss;
    }

    //diese Methode erlaubt uns Aufgaben aus dem Array zu löschen.
    //Dabei  setze ich die zu löschende Aufgabe (hier "Titel" genannt) zuerst auf "Null"
    //und übertrage den String-Wert des nächsten Elements auf das vorherige
    //(also auf die Position des Titels).Das wird für alle Elemente des Arrays,
    //die nach dem zu löschendem Element sich befinden, gemacht
    //(natürlich erst wenn ein solches Titel im Array überhaupt vorhanden ist).
    //Danach setze ich entweder das letzte Element "t" aus dem Array todos "auf "Null"
    //oder das Element "t", das rechts von sich schon ein "Null" besitzt.
    //um die beiden Arrays "todos" und "daten" zu synchronisieren, mache ich es auch für das
    //Element "t" aus dem Array "daten".
    public static boolean loescheAufgabe (String titel) {
        int MAX = todos.length;
        for (int i = 0; i < todos.length; i++) {
            if (!(todos[i] == null)) {
                if (titel.equals(todos[i])) {
                    todos[i] = null;
                    daten[i] = null;
                    for (int t = i; t < todos.length-1; t++) {
                        todos[t] = todos[t + 1];
                        daten[t] = daten[t + 1];
                        if (t < MAX - 1 && todos[t + 1] == null) {
                            todos[t] = null;
                            daten[t] = null;
                        } else if (todos[MAX - 2] == todos[MAX - 1]) {
                            todos[MAX - 1] = null;
                            daten[MAX - 1] = null;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }




    //diese Methode erlaubt uns eine schon vorhandene Aufgsbe mit einem neuen Titel zu versehen.
    //Dabei verwende ich wieder ein If-statment, um die NullPointerExeption beim möglichen Vergleich
    //von dem alten Titel mit dem Element, das mit "Null" belegt ist, zu vermeiden (dies mache ich auch in den
    //weiteren Methoden so und deswegen werde ich es im Weiteren nicht mehr betonen).
    public static boolean bearbeiteAufgabe (String alterTitel, String neuerTitel) {
        for (int i = 0; i < todos.length; i++) {
            if (!(todos[i] == null)) {
                if (todos[i].equals(alterTitel)) {
                    for (int t = 0; t < todos.length; t++) {
                        if (neuerTitel.equals(todos[t]))
                            return false;
                        else {
                            todos[i] = neuerTitel;
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }
    //diese Methode erlaubt uns ein Datum zu einer Aufgabe hinzuzufügen. Dafür habe ich ein weiteres Array
    //gleicher Größe angelegt, das Daten im Format Jahr-Monat-Tag enthält. Da die beiden Arrays gleiche
    //Größe haben, kann man einfach das gleiche Index (zB 5) wie im Array "todos" verwenden, um auf dasselbe
    //(fünfte) Element aus dem Array "daten" zugreifen zu können.
    public static boolean setzeDatum (String titel, int jahr, int monat, int tag) {
        for (int i = 0; i < todos.length; i++) {
            if (!(todos[i] == null)) {
                if (todos[i].equals(titel)) {
                    if ((monat <=12) && (tag <=31)) {
                        daten[i] = LocalDate.of(jahr, monat, tag);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //diese Methode erlaubt uns alle vorhandenen Aufgaben mit dazugehörigen Daten aufzulisten.
    //da bei dem Hinzufügen einer neuen Aufagbe auf die Stelle "i" im Array todos, das i-te Element
    //des Arrays daten mit einem festen Datum initialisiert wird, bekommen wir für alle Aufgaben immer
    //die gleichen Daten. Mit der Funktion "setzeDatum" kann man dies ändern und ein beliebiges Datum
    //als Fälligkeitsdatum für eine Aufgabe auswählen.
    public static String listeAlleAufgabenMitDatum () {
        String ergebniss = "Aufgaben auf der Liste:\n";
        for (int i = 0; i < todos.length; i++) {
            if (!(todos[i] == (null))) {
                ergebniss = ergebniss + todos[i] + ", fällig am " + daten[i] + "\n";
            }
        }
        return ergebniss;
    }

    //diese Methode erlaubt uns die Elemente aus dem Array todos nach Titel zu sortieren. Dazu habe ich
    //das Bubblesort Algorithmus verwendet. Das übergebene Parameter entscheidet dabei, ob die Elemente
    //alphabetisch auf- oder absteigend sortiert werden (mittels if-Statment).
    public static String nachTitelSortieren(boolean aufsteigend) {
        if (aufsteigend == true) {
            for (int t = 0; t < (todosKopie.length - 2); t++) {
                for (int i = 0; i < (todosKopie.length - t - 1); i++) {
                    if (!(todosKopie[i] == null) && !(todosKopie[i + 1] == null)) {
                        if (todosKopie[i].compareTo(todosKopie[i + 1]) > 0) {
                            String a = todosKopie[i + 1];
                            todosKopie[i + 1] = todosKopie[i];
                            todosKopie[i] = a;
                        }
                    }
                }
            }
        String ergebnissSortAuf = "Aufgaben auf der Liste:\n";
            for (int i = 0; i < todosKopie.length; i++) {
                if (!(todosKopie[i] == (null))) {
                    String aufgabe1 = todosKopie[i];
                    ergebnissSortAuf += aufgabe1 + "\n";
                }
            }
        return ergebnissSortAuf;
        }

        else {
            for (int t = 0; t < (todosKopie.length - 2); t++) {
                for (int i = 0; i < (todosKopie.length - t - 1); i++) {
                    if (!(todosKopie[i] == null) && !(todosKopie[i + 1] == null)) {
                        if (todosKopie[i].compareTo(todosKopie[i + 1]) < 0) {
                            String a = todosKopie[i + 1];
                            todosKopie[i + 1] = todosKopie[i];
                            todosKopie[i] = a;
                        }
                    }
                }
            }
            String ergebnissSortAb = "Aufgaben auf der Liste:\n";
            for (int i = 0; i < todosKopie.length; i++) {
                if (!(todosKopie[i] == (null))) {
                    String aufgabe2 = todosKopie[i];
                    ergebnissSortAb += aufgabe2 + "\n";
                }
            }
            return ergebnissSortAb;
        }
    }

    //diese Methode erlaubt uns die Elemente aus dem Array todos nach Datum zu sortieren. Dazu habe ich
    //wieder das Bubblesort Algorithmus verwendet. Das übergebene Parameter entscheidet dabei, ob die Elemente
    //auf- oder absteigend sortiert werden (mittels if-Statment).
    public static String nachDatumSortieren(boolean aufsteigend) {
        if (aufsteigend == true) {
            for (int t = 0; t < (datenKopie.length - 2); t++) {
                for (int i = 0; i < (datenKopie.length - t - 1); i++) {
                    if (!(datenKopie[i] == null) && !(datenKopie[i + 1] == null)) {
                        if (datenKopie[i].compareTo(datenKopie[i + 1]) > 0) {
                            LocalDate a = datenKopie[i + 1];
                            datenKopie[i + 1] = datenKopie[i];
                            datenKopie[i] = a;
                        }
                    }
                }
            }

            String ergebnissSortAuf = "Aufgaben auf der Liste:\n";
                for (int i = 0; i < datenKopie.length; i++) {
                    if (!(datenKopie[i] == (null))) {
                        LocalDate datum1 = datenKopie[i];
                        ergebnissSortAuf += datum1 + "\n";
                    }
                }
            return ergebnissSortAuf;
        }

        else {
            for (int t = 0; t < (datenKopie.length - 2); t++) {
                for (int i = 0; i < (datenKopie.length - t - 1); i++) {
                    if (!(datenKopie[i] == null) && !(datenKopie[i + 1] == null)) {
                        if (datenKopie[i].compareTo(datenKopie[i + 1]) < 0) {
                            LocalDate a = datenKopie[i + 1];
                            datenKopie[i + 1] = datenKopie[i];
                            datenKopie[i] = a;
                        }
                    }
                }
            }
            String ergebnissSortAuf = "Aufgaben auf der Liste:\n";
            for (int i = 0; i < datenKopie.length; i++) {
                if (!(datenKopie[i] == (null))) {
                    LocalDate datum2 = datenKopie[i];
                    ergebnissSortAuf += datum2 + "\n";
                }
            }
            return ergebnissSortAuf;
        }
    }

    // Diese Methode erlaubt uns den Inhalt der backup.txt Datei, parst die String-Representationen der beiden arrays zu lesen.
    // Füllt die beiden arrays mit entsprechenden Werten aus backup.txt Datei aus
    private static void backupDateiLesen() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("backup.txt"));
        String todosString = reader.readLine();
        String datesString = reader.readLine();
        if (todosString != null) {
            String[] todosAsStrings = todosString.substring(1, todosString.length() - 1).split(", ");
            for (int i = 0; i < todosAsStrings.length; i++) {
                if (todosAsStrings[i].equals("null")) {
                    todos[i] = null;
                } else {
                    todos[i] = todosAsStrings[i];
                }
            }
            String[] datesAsStrings = datesString.substring(1, datesString.length() - 1).split(", ");
            for (int i = 0; i < datesAsStrings.length; i++) {
                if (!datesAsStrings[i].equals("null")) {
                    daten[i] = LocalDate.parse(datesAsStrings[i]);
                }
            }
        }
        reader.close();
    }

    // Diese Methode erlaubt uns eine String-Representation des Inhalts der beiden arrays in der backup.txt Datei (eine Zeile für jedes array)
    // zu speichern. Sie Wird aufgerufen kurz vor dem Ende der Programmausführung.
    private static void imBackupDateiSpeichern() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("backup.txt"));
        writer.write(Arrays.toString(todos));
        writer.write("\n");
        writer.write(Arrays.toString(daten));
        writer.close();
    }


    public static void main (String[] args) throws IOException {

        HashMap<int, String> map = new HashMap<int, String>()


        backupDateiLesen();
        boolean Schleife = true;
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        System.out.println("Willkommen in Ihrer To-Do-Liste, was möchten Sie tun?");
        while (Schleife) {
            System.out.println("[1] To-Dos anzeigen\n" + "[2] Eintrag hinzufügen\n" + "[3] Eintrag löschen\n" + "[4] Programm beenden\n" + "[5] Eintrag bearbeiten\n" + "[6] Datum setzen\n" + "[7] To-Dos mit Datum anzeigen\n" + "[8] To-Dos sortieren");
            int auswahl = scanner.nextInt();
            if (auswahl == 1) {
                System.out.println(listeAlleAufgaben());
            }
            else if (auswahl == 2) {
                String neuerTitel = scanner.next();
                if (neueAufgabe(neuerTitel)){
                    System.out.println("Die Aufgabe " + neuerTitel + " wurde hinzugefügt");
                }
                else {
                    System.out.println("Die Aufgabe " + neuerTitel + " konnte nicht hinzugefügt werden");
                }

            }
            else if (auswahl == 3) {
                String löscheTitel = scanner.next();
                    if (loescheAufgabe(löscheTitel)) {
                        System.out.println("Die Aufgabe " + löscheTitel + " wurde gelöscht");
                    }
                    else {
                        System.out.println("Die Aufgabe " + löscheTitel + " wurde nicht gefunden." );
                    }
            }
            else if (auswahl == 4) {
                scanner.close();
                imBackupDateiSpeichern();
                Schleife = false;
                System.exit(0);
            }
            else if (auswahl == 5) {
                System.out.println("Bitte geben Sie einen Titel ein:");
                String alterTitel = scanner.next();
                System.out.println("Bitte geben Sie einen neuen Titel ein:");
                String neuerTitel = scanner.next();
                    if (bearbeiteAufgabe(alterTitel,neuerTitel)) {
                        System.out.println("Die Aufgabe " + alterTitel + " wurde umbenannt zu " + neuerTitel);
                    }
                    else {
                        System.out.println("Die Aufgabe " + alterTitel + " konnte nicht umbennant werden");
                    }
            }
            else if (auswahl == 6) {
                System.out.println("Bitte geben Sie einen Titel ein:");
                String neuerTitel = scanner.next();
                System.out.println("Bitte geben Sie ein Datum ein:");
                System.out.println("Bitte geben Sie zuerst das Jahr ein:");
                int neuesDatumJahr = scanner.nextInt();
                System.out.println("Bitte geben Sie Monat ein:");
                int neuesDatumMonat = scanner.nextInt();
                System.out.println("Bitte geben Sie Tag ein:");
                int neuesDatumTag = scanner.nextInt();
                    if (setzeDatum(neuerTitel, neuesDatumJahr, neuesDatumMonat, neuesDatumTag)) {
                        LocalDate datum = LocalDate.of(neuesDatumJahr,neuesDatumMonat,neuesDatumTag);
                        System.out.println("Die Aufgabe " + neuerTitel + " ist am " + datum +" fällig");
                    }
                    else {
                        System.out.println("Das Datum für " + neuerTitel + " konnte nicht gesetzt werden.");
                    }
           }
            else if (auswahl == 7) {
                System.out.println(listeAlleAufgabenMitDatum());
           }
            else if (auswahl == 8) {
                for (int i = 0; i < todosKopie.length; i++) {
                    todosKopie[i] = todos[i];
                }
                for (int i = 0; i < datenKopie.length; i++) {
                    datenKopie[i] = daten[i];
                }
                System.out.println("[1] nach Titel aufsteigend\n" + "[2] nach Titel absteigend\n" + "[3] nach Datum aufsteigend\n" + "[4] nach Datum absteigend");
                int auswahlSortierung = scanner.nextInt();
                if (auswahlSortierung==1) {
                    System.out.println(nachTitelSortieren(true));
                }
                else if (auswahlSortierung==2) {
                    System.out.println(nachTitelSortieren(false));
                }
                else if (auswahlSortierung==3) {
                    System.out.println(nachDatumSortieren(true));
                }
                else if (auswahlSortierung==4) {
                    System.out.println(nachDatumSortieren(false));
                }
            }
            else {
                System.out.println("Ungültige Eingage");
            }
        }
    }
}