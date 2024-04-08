package org.example;
import org.xbill.DNS.ResolverConfig;

import javax.naming.*;
import javax.naming.directory.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;

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

    public static void findIpByDomain(BufferedReader reader, DirContext ctx) throws IOException {
        System.out.println("Introduceti numele de domeniu:");
        String numeDomeniu = reader.readLine();
        InetAddress[] addresses = InetAddress.getAllByName(numeDomeniu);
        System.out.println("Adresele IP asociate numelui de domeniu: " + numeDomeniu + " sunt: ");
        for (InetAddress address : addresses) {
            System.out.println(address.getHostAddress());
        }
    }

    public static void findDomainByIp(BufferedReader reader, DirContext ctx) {
        try {
            System.out.println("Introduceti adresa IP:");
            String adresaIP = reader.readLine();
            InetAddress address = InetAddress.getByName("172.217.6.142");
            String hostname = address.getHostName();
            if (hostname.equals(adresaIP)) {
                System.out.println("Nu s-a gasit un nume de domeniu pentru adresa IP " + adresaIP);
            } else {
                System.out.println("Numele de domeniu pentru adresa IP " + adresaIP + " este " + hostname);
            }
    } catch (UnknownHostException e) {
        e.printStackTrace();
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeDnsServer(BufferedReader reader, DirContext ctx) throws IOException {
        System.out.println("Introduceti nou server dns:");
        String DnsServer = reader.readLine();
        System.setProperty("sun.net.spi.nameservice.nameservers", DnsServer);

        String server= System.getProperty("sun.net.spi.nameservice.nameservers");
        System.out.println("Serverul DNS a fost schimbat cu succes la adresa  "+ server);

//        List<InetSocketAddress> dnsServers = ResolverConfig.getCurrentConfig().servers();
//        System.out.println("Lista de servere DNS:");
//        for (InetSocketAddress dnsServer : dnsServers) {
//            System.out.println(dnsServer.getHostString() + ":" + dnsServer.getPort());
//        }
    }
}
