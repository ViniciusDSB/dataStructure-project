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

public class Main{
    
    public static void main(String[] args){
        System.setProperty("file.encoding", "UTF-8");
        Scanner scan = new Scanner(System.in);

        //reads the files, gets and compresses the content, then stores on hash table
        try {

            //ASKS USER THE PATH FOR A FOLDER WITH TXTs
            System.out.println("Type the path to the txts directory that contains the articles.");
            String filepath = scan.nextLine();

            System.out.println("Alright, what ya next movement\n 1 - Only compress with huffman and store on a hash table\n 2 - Just index the words on a Trie structure \n3 - EVERYTHING, LETS GOO");
            int op = scan.nextInt();
            
            List<FileData> filesData = readFiles(filepath);

            if(op == 1){
                
                //Asks what hash algorithm must be used
                System.out.println("Which hash algorithm do you want to use? \n 1 - hash by division or 2 - hash DHB2?");
                int hashAlgo = scan.nextInt();
                int size = 31;
                
                huffmanStuff(filesData, scan, size, hashAlgo);
                //myTable.printOut();
                
            }else if(op == 2){
                
                trieStuff(filesData, scan);

            }else{
                //Asks what hash algorithm must be used
                System.out.println("Which hash algorithm do you want to use? \n 1 - hash by division or 2 - hash DHB2?");
                int hashAlgo = scan.nextInt();
                int size = 31;
                
                huffmanStuff(filesData, scan, size, hashAlgo);
                //myTable.printOut();

                trieStuff(filesData, scan);
            }

            scan.close();
        }catch (Exception e){
            System.out.println("\nPROBLEM ON MAIN FUNCTION: " +  e);
        }   
    }
    
    public static void huffmanStuff(List<FileData> filesData, Scanner scan, int size, int hashAlgo) throws Exception{
        try {

            HashTable<String, String> myTable = new HashTable<String, String>(size, hashAlgo);
        
            //Reads files and store on our hash table
            for(FileData file : filesData){

                String filename = file.getFilename();
                String compressedFileContent = compress(file.getContent());
                System.out.print("Compressed: " + filename);

                //storing on hashTable
                if(myTable.put(filename, compressedFileContent));
                    System.out.println(" Stored: " + filename);
            }
        } catch (Exception e) {
            System.out.println("PORBLEM AT huffmanStuff(); ERROR: " + e);
        }

    }

    public static void trieStuff(List<FileData> filesData, Scanner scan){
        try {
            Trie myTrie = new Trie();

            //Indexing word on Trie
            for(FileData file : filesData){
                String documentTitle = file.getFilename();
                String documentContent = file.getContent();
            
                myTrie.insertText(documentContent, documentTitle);
                System.out.println("Successfully inserted document " + documentTitle);
            }

            //searching for words
            System.out.println("Type -1 to exit.");
            System.out.println("Type a word, lets see which documents have it: ");
            
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
            System.out.println("Ok, bye!");
    
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

}