package pl.sda;

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
        System.out.println("Prosze podac url do wybranej kategorii produktow");
        String url = sc.nextLine();
        app.app(url);
    }
}
