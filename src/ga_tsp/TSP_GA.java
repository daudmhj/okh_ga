/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author Daud
 */
public class TSP_GA {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "D:\\ITS/MATERI KULIAH/Sem 7/OKH/FP/datasets/big1.csv";
        ExecutionTimer timer = new ExecutionTimer();
        File file = new File(fileName); // TODO: read about File Names
        try {
            Scanner inputStream = new Scanner(file);
            int isi = inputStream.nextInt();
            //Membuat array untuk city
            City a[] = new City[isi];
            int  index = 0;
            while (inputStream.hasNext()){
                String b=inputStream.next();
                String array1[]= b.split(",");

                a[index] = new City(index, Integer.parseInt(array1[0]),Integer.parseInt(array1[1]));
                TourManager.addCity(a[index]);
                index++;
            // memasukkan data ke array
            }
            inputStream.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

            // Initialize population
            Population ga = new Population(200, true);
            System.out.println("Initial distance: " + ga.getFittest().getDistance());

            // Evolve population for x generations
            int generation = 200;
            ga = GA.evolvePopulation(ga);
            for (int i = 0; i < generation; i++) {
                ga = GA.evolvePopulation(ga);
                timer.end();
                while(timer.duration() >= 1000){
                    // Print final results
                    System.out.println("Finished");
                    System.out.println("Final distance: " + ga.getFittest().getDistance());
                    System.out.println("Solution:");
                    System.out.println(ga.getFittest());

                    // export to csv
                    PrintWriter pw = new PrintWriter(new File("hasilGA.csv"));
                    StringBuilder sb = new StringBuilder();
                    sb.append("Urutan Kota yang akan dikunjungi");
                    sb.append("\n");
                    String hasil = String.valueOf(ga.getFittest());
                    String[] urutan = hasil.split(" -> ");

                    for(int u = 0; u < urutan.length; u++){
                        sb.append(urutan[u]);
                        sb.append("\n");
                    }
                    
                    sb.append("\n");
                    sb.append("Distance : " + ga.getFittest().getDistance());
                    
                    pw.write(sb.toString());
                    pw.close();
                    System.out.println("FIle CSV bisa dilihat di root directory project dengan nama hasilGA.csv");

                    timer.end();
                    System.out.println("");
                    System.out.println("Running time: " + timer.duration() + "ms");
                    System.out.println("Waktu Habis! Itereasi Terakhir : " + i);
                    System.exit(0);
                }
            }
            
            // Print final results
            System.out.println("Finished");
            System.out.println("Final distance: " + ga.getFittest().getDistance());
            System.out.println("Solution:");
            System.out.println(ga.getFittest());
            
            // export to csv
            PrintWriter pw = new PrintWriter(new File("hasilGA.csv"));
            StringBuilder sb = new StringBuilder();
            sb.append("Urutan Kota yang akan dikunjungi");
            sb.append("\n");
            String hasil = String.valueOf(ga.getFittest());
            String[] urutan = hasil.split(" -> ");
            
            for(int u = 0; u < urutan.length; u++){
                sb.append(urutan[u]);
                sb.append("\n");
            }
            
            sb.append("\n");
            sb.append("Distance : " + ga.getFittest().getDistance());
            
            pw.write(sb.toString());
            pw.close();
            System.out.println("FIle CSV bisa dilihat di root directory project dengan nama hasilGA.csv");
            
            timer.end();
            System.out.println("");
            System.out.println("Running time: " + timer.duration() + "ms");
            System.out.println("Itereasi Terakhir : " + generation);
            System.exit(0);
        }
}