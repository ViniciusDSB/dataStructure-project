//import tree.*;
import compresses.*;
import hashes.*;

import java.util.Scanner;

public class Main{
    
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        
        //ASKS USER THE PATH FOR A FOLDER WITH TXTs
        System.out.println("Type the path to the txts directory that contains the articles.");
        String pdfDirPath = scan.nextLine();

        
        //INSANCES HUFFMAN COMPRESSION ALGO AND USE IT


        //INSTANTIATE HASH ALGO AND USE IT

        //Asks what hashing should be used
        System.out.println("Wich compression algorithm do you want to use? \n 1 - hash DHB2 or 2 - division nash?");
        int hashAlgo = scan.nextInt();

        HashTable<String, String> myTable = new HashTable<String, String>(30, hashAlgo);
        storeOnHash(myTable, "rusbe", "0110011101000010110");
        
    }


    public String getText(String filename){

        String filetext = "";
        
        //reads file...
        
        return filetext;
    }

    public String compress(String text){

        //this should return a string with the compressed text
        //and the title of the file
        //basicaly a hashEntry

        HuffmanTrie huff = new HuffmanTrie(text);
        return huff.compress(text);

    }

    public static void storeOnHash(HashTable myTable, String title, String text){
        
        myTable.put(title, text);
        myTable.printOut();

    }

}