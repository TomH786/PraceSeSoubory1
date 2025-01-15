import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Zakaznik zakaznik1 = new Zakaznik("Pepa", LocalDate.of(1981 , 5, 12), "Uherské Hradiště", 5);
        Zakaznik zakaznik2 = new Zakaznik("Jana", LocalDate.of(1990 , 3, 15), "Uherské Hradiště", 3);
        Zakaznik zakaznik3 = new Zakaznik("Lucie", LocalDate.of(1975 , 7, 25), "Uherské Hradiště", 11);
        Zakaznik zakaznik4 = new Zakaznik("Karel", LocalDate.of(1975 , 7, 25), "Brno", 7);


        EvidenceZakazniku evidence = new EvidenceZakazniku();
        evidence.pridejZakaznika(zakaznik1);
        evidence.pridejZakaznika(zakaznik2);
        evidence.pridejZakaznika(zakaznik3);
        evidence.pridejZakaznika(zakaznik4);
        evidence.odeberPoslednihoZakaznika();


        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src//soubor.txt")))){
            for (Zakaznik zakaznik : evidence.ziskejZakazniky()){
                writer.println(zakaznik.getJmeno() + ":" + zakaznik.getDatumNarozeni() + ":" + zakaznik.getMesto() + ":" + zakaznik.getPocetProdeju());
            }
        }catch (IOException e){
            System.out.println(e.getLocalizedMessage());
        }

        EvidenceZakazniku evidence2 = new EvidenceZakazniku();
        final String ODDELOVAC = ":";

        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("src//soubor.txt")))){
            while (scanner.hasNextLine()){
                String radek = scanner.nextLine();
                String[] pole = radek.split(ODDELOVAC);
                String jmeno = pole[0];
                LocalDate datumNarozeni = LocalDate.parse(pole[1]);
                String mesto = pole[2];
                int pocetProdeju = Integer.parseInt(pole[3]);
                evidence2.pridejZakaznika(new Zakaznik(jmeno, datumNarozeni, mesto, pocetProdeju));

            }
        }catch (FileNotFoundException e){
            System.out.println(e.getLocalizedMessage());
        }

        System.out.println(evidence2.vyberZakazniky(4));

        int vsechnyProdeje = 0;
        int pocet = 0;
        for (Zakaznik zakaznik : evidence.ziskejZakazniky()){
            if ("Uherské Hradiště".equals(zakaznik.getMesto())){
                vsechnyProdeje += zakaznik.getPocetProdeju();
                pocet++;
            }
        }
        System.out.println("Průměrný počet prodejů: " + (double)vsechnyProdeje/pocet);

    }
}