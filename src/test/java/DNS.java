package org.example;

import javax.naming.*;
import javax.naming.directory.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            DirContext ctx = new InitialDirContext();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Alegeti o optiune:");
                System.out.println("1. Gasire ip dupa denumirea domeniului");
                System.out.println("2. Gasire domeniu dupa adresa ip / Reversive Lookup");
                System.out.println("3. Schimbare Server DNS");
                System.out.println("4. Iesire din program");

                System.out.println("Introduceti numarul corespunzator optiunii:");
                int option = Integer.parseInt(reader.readLine());

                switch (option) {
                    case 1:
                        findIpByDomain(reader, ctx);
                        break;
                    case 2:
                        findDomainByIp(reader, ctx);
                        break;
                    case 3:
                        changeDnsServer(reader, ctx);
                        break;
                    case 4:
                        return; // Ieșire din program
                    default:
                        System.out.println("Optiune invalida.");
                        break;
                }
            }
        } catch (IOException | NamingException e) {
            e.printStackTrace();
        }
    }

    public static void findIpByDomain(BufferedReader reader, DirContext ctx) {
        // Implementați funcționalitatea pentru a găsi IP-ul după numele domeniului
    }

    public static void findDomainByIp(BufferedReader reader, DirContext ctx) {
        // Implementați funcționalitatea pentru a găsi domeniul după adresa IP
    }

    public static void changeDnsServer(BufferedReader reader, DirContext ctx) {
        // Implementați funcționalitatea pentru a schimba serverul DNS
    }
}
