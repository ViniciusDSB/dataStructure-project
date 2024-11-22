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
            
            List<FileData> filesData = readFiles(args[2]);
            int op = Integer.parseInt(args[3]);

            if(op == 0){
                String analisysFile = "../huffmanHashAnalitics.txt";
                int size = Integer.parseInt(args[0]);
                int hashAlgo = Integer.parseInt(args[1]);
                huffmanStuff(filesData, scan, size, hashAlgo, analisysFile);
            }
            else{
                String analisysFile = "../trieAnalitics.txt";
                trieStuff(filesData, scan, analisysFile);
            }
                

        }catch (Exception e){
            System.out.println("\nPROBLEM ON MAIN FUNCTION: " +  e);
        }   
    }
    
    public static void huffmanStuff(List<FileData> filesData, Scanner scan, int size, int hashAlgo, String analisysFile) throws Exception{
        try {

            HashTable<String, String> myTable = new HashTable<String, String>(size, hashAlgo);
        
            //Reads files and store on our hash table
            for(FileData file : filesData){

                String filename = file.getFilename();

                String compressedFileContent = compress(file.getContent());

                myTable.put(filename, compressedFileContent);
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
            
            long deltaT = t1-t0;
            String[] data = {""+deltaT+" ms"};
            writeData(analisysFile, data, "INDEXING TIME");

            //calc and write memory used
            long deltaMem = t1_freeMem - t0_freeMem;
            data[0] = ""+deltaMem+" bytes";
            writeData(analisysFile, data, "used memory");

            //searching for words
            
            while(true){
                String toSearch = scan.next();
    
                if(toSearch.equals("-1"))
                    break;
    
                    LinkedList res = myTrie.search(toSearch);
    
                    if(res != null)
                        System.out.println(res.toString());
                    else
                        System.out.println("Word not found!");
            }
    
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
                writer.println(dataToAppend);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error appending text: " + e.getMessage());
        }

    }
    
}