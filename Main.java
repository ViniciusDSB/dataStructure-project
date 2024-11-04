package dataStrctureProject;

import compresses.*;
import hashes.*;

import java.util.Scanner;

public class Main{
    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        //get the path to the files
        System.out.println("Type the path to the PDF directory that contains the articles.");
        String pdfDirPath = scan.next();

        //read path
        //instance txtDealer and use it


        //instance compression and use it

        //asks what hash algorithm should be used
        System.out.println("Wich compression algorith do you want to use? \n 1 - hash DHB2 or 2 - division nash?");
        int hashAlgo = scan.nextInt();

        //instance chosen hash and indexes the files

    }
}