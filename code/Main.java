import compresses.*;
import hashes.*;

import java.util.Scanner;

public class Main{
    
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        

        //ASKS USER THE PATH FOR A FOLDER WITH TXTs
        System.out.println("Type the path to the txts directory that contains the articles.");
        String pdfDirPath = scan.next();

        //INSANCES HUFFMAN COMPRESSION ALGO AND USE IT
        
        //compression exemple
        //String input = "abracadabra";
        //HuffmanTrie huffman = new HuffmanTrie(txt_file);
        //
        //String compressed = huffman.compress("abracadabra");
        //String decompressed = huffman.decompress(compressed);
        //    
        //System.out.println("Compressed " + compressed);
        //System.out.println("Decompressed: " + decompressed);

        //ASKS WHAT HASH ALGO SHOULD BE USED
        System.out.println("Wich compression algorith do you want to use? \n 1 - hash DHB2 or 2 - division nash?");
        int hashAlgo = scan.nextInt();

        //exemple of hash table usage
        //HashTable<Integer, String> testHash = new HashTable<Integer, String>(10);
        //testHash.put(1, "Sheldon");
        //testHash.put(1, "Nome");
        //testHash.put(3, "Azingfa");
        //testHash.put(5, "Vinicius");
        //testHash.put(4, "Buarque");
        //testHash.put(1, "Luiza");
        //testHash.put(8, "Vinicius");
        //testHash.put(8, "Vinicius"); //Do not insert a same value into a same position
        //
        //testHash.printOut();
        //System.out.println(testHash.get(1));

        //INSTANTIATE HASH ALGO AND USE IT

    }
}