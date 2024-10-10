/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tokenizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Emrecan
 */
public class Tokenizer {

    /**
     * @param args komut satırı argümanları
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Dosya adını girin: ");
        String dosyaAdi = scanner.nextLine();
        scanner.close();

        try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = okuyucu.readLine()) != null) {
                // Satırı kelimelere ayır ve yazdır
                kelimeleştirVeYazdir(satir);
            }
        } catch (IOException e) {
            System.err.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
        }
    }

    // Verilen satırı kelimelere ayıran ve ekrana yazdıran fonksiyon
    private static void kelimeleştirVeYazdir(String satir) {
        StringBuilder ayrıştırılmışSatir = new StringBuilder();
        char[] karakterler = satir.toCharArray();

        for (int i = 0; i < karakterler.length; i++) {
            char c = karakterler[i];

            if (rakamMi(c)) {
                // Eğer karakter bir rakamsa
                while (i < karakterler.length && rakamMi(karakterler[i])) {
                    ayrıştırılmışSatir.append(karakterler[i]);
                    i++;
                }
                i--; // Fazladan ilerlemeyi geri al
                ayrıştırılmışSatir.append(' ');
            } else if (operatorMu(c) || c == '(' || c == ')') {
                // Eğer karakter bir operatör veya parantezse
                ayrıştırılmışSatir.append(' ').append(c).append(' ');
            }
        }

        // Fazla boşlukları temizle ve sonucu yazdır
        System.out.println(fazlaBosluklariTemizle(ayrıştırılmışSatir.toString()));
    }

    // Karakterin rakam olup olmadığını kontrol eden fonksiyon
    private static boolean rakamMi(char c) {
        return c >= '0' && c <= '9';
    }

    // Karakterin operatör olup olmadığını kontrol eden fonksiyon
    private static boolean operatorMu(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Fazla boşlukları temizleyen fonksiyon
    private static String fazlaBosluklariTemizle(String str) {
        StringBuilder sonuc = new StringBuilder();
        boolean boslukVar = false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                if (!boslukVar) {
                    sonuc.append(c);
                    boslukVar = true;
                }
            } else {
                sonuc.append(c);
                boslukVar = false;
            }
        }

        return sonuc.toString().trim();
    }
}