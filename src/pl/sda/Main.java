package pl.sda;

import java.net.URL;
import java.util.Scanner;

/**
 * author:
 * Mateusz
 * Marczak
 **/
public class Main {
    public static void main(String[] args) {
        App app = new App();
        Scanner sc = new Scanner(System.in);
        System.out.println("Prosze podac url wybranej kategorii w serwisie \"Ceneo\" zaczynajaca sie wielka litera");
        System.out.println("Na przykład \"Gitary_i_akcesoria/p:Yamaha_Music;0191\"");
        String url = sc.nextLine();
        url = url.replaceAll(" ","_");
        url = "https://www.ceneo.pl/"+url;
        app.app(url);
    }
}
