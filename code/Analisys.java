import trie.*;
import compresses.*;
import hashes.*;

//for reading the files
import java.io.*;
import java.nio.file.*;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Analisys{
    
    public static void main(String[] args){
        
        //reads the files, gets and compresses the content, then stores on hash table
        try {

            System.setProperty("file.encoding", "UTF-8");
            Scanner scan = new Scanner(System.in);
            
            //Args: first position is the hash table size
            //second is hash algorithm(1 or 2)
            //third is filePath
            //fourth specifies which part to run, huffman only or trie only ( 0 or 1)
            //fifth argument tells huffman method if it should write the hash table on the txt file or not
            //  because the distribuition is aways the same, so we do not have to get it everytime
            
            List<FileData> filesData = readFiles(args[2]);
            int op = Integer.parseInt(args[3]);

            if(op == 0){
                String analisysFile = "../huffmanHashAnalitics.txt";
                int size = Integer.parseInt(args[0]);
                int hashAlgo = Integer.parseInt(args[1]);
                int getHashTable = Integer.parseInt(args[4]);

                huffmanStuff(filesData, scan, size, hashAlgo, analisysFile, getHashTable);
            }
            else{
                String analisysFile = "../trieAnalitics.txt";
                trieStuff(filesData, scan, analisysFile);
            }

        }catch (Exception e){
            System.out.println("\nPROBLEM ON MAIN FUNCTION: " +  e);
        }   
    }
    
    public static void huffmanStuff(List<FileData> filesData, Scanner scan, int size, int hashAlgo, String analisysFile, int getHashTable) throws Exception{
        try {

            HashTable<String, String> myTable = new HashTable<String, String>(size, hashAlgo);
            
            Runtime runtime = Runtime.getRuntime();

            //Reads files and store on our hash table
            long t0 = System.currentTimeMillis();
            long t0_freeMem = runtime.freeMemory();
            for(FileData file : filesData){

                String filename = file.getFilename();

                String compressedFileContent = compress(file.getContent());

                myTable.put(filename, compressedFileContent);
            }
            long t1 = System.currentTimeMillis();
            long t1_freeMem = runtime.freeMemory();
            
            //writign time and mem to the txt
            long deltaT = t1-t0;
            long deltaMem =  t0_freeMem - t1_freeMem;

            String[] data = new String[35];
            data[0] = ""+deltaT+" ms";
            writeData(analisysFile, data, "COMPRESSION TIME");

            data[0] = ""+deltaMem+" bytes";
            writeData(analisysFile, data, "used memory");

            if(getHashTable == 1){
                data = myTable.getDistribuition();
                writeData(analisysFile, data, "HASH TABLE DISTRIBUITION");
            }
            
            //myTable.printOut();
        } catch (Exception e) {
            System.out.println("PORBLEM AT huffmanStuff(); ERROR: " + e);
        }

    }

    public static String compress(String content){
        try{
            String compressed;

            HuffmanTrie myHuffTrie = new HuffmanTrie(content);

            compressed = myHuffTrie.compress(content);
        
            return compressed;
        }catch(Exception e){
            System.out.println("PROBLEM TRYING TO COMPRESS FILE CONTENT: " + e);
            return "";
        }

    }

    public static void trieStuff(List<FileData> filesData, Scanner scan, String analisysFile)throws Exception{
        try {
            
            Trie myTrie = new Trie();

            Runtime runtime = Runtime.getRuntime();

            //Indexing word on Trie
            long t0_freeMem = runtime.freeMemory();
            long t0 = System.currentTimeMillis();
            for(FileData file : filesData){
                myTrie.insertText(file.getContent(), file.getFilename());
            }

            long t1 = System.currentTimeMillis();
            long t1_freeMem = runtime.freeMemory();

            //writign time and mem to the txt
            long deltaT = t1-t0;
            String[] data = {""+deltaT+" ms"};
            writeData(analisysFile, data, "INDEXING TIME");

            //calc and write memory used
            long deltaMem =  t0_freeMem - t1_freeMem;
            data[0] = ""+deltaMem+" bytes";
            writeData(analisysFile, data, "used memory");

            //searching for words
            String[] expressions = new String[100];

            List<FileData> wordsFile = readFiles("../expressions/");
            for(FileData file : wordsFile){
                String content = file.getContent();
                expressions = content.split("\n");
            }

            t0 = System.currentTimeMillis();
            for( String toSearch : expressions){
                myTrie.search(toSearch);
            }
            
            t1 = System.currentTimeMillis();
            deltaT = t1-t0;
            data[0] = ""+deltaT+" ms";
            writeData(analisysFile, data, "TOTAL SEARCH TIME TIME");
    
        } catch (Exception e) {
            System.out.println("PROBLEM AT trieStuff(); ERROR: "+ e);
        }
    }


    //reads the files on the path, stores their title and content in a list ad returns that list
    public static List<FileData> readFiles(String dirPath) throws IOException {
        List<FileData> filesData = new ArrayList<>();

        // Get a stream of all .txt files in the directory
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dirPath), "*.txt")) {
            for (Path filePath : stream) {

                String fileName = filePath.getFileName().toString();
                String content = Files.readString(filePath);
                
                filesData.add(new FileData(fileName, content));
            }
        } catch (DirectoryIteratorException e) {
            throw e.getCause();
        }

        return filesData;
    }

    public static void writeData(String filePath, String[] data, String dataLabel){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter(filePath, true));
            writer.println(dataLabel);
            
            for(String dataToAppend : data){
                if(dataToAppend != null)
                    writer.println(dataToAppend);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("PROBLEM AT writeData(); ERROR: " + e);
        }

    }
    
}