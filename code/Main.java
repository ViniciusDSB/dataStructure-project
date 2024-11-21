import trie.*;
import compresses.*;
import hashes.*;

//for reading the files
import java.io.*;
import java.nio.file.*;

import java.util.*;

public class Main{
    
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        
        //ASKS USER THE PATH FOR A FOLDER WITH TXTs
        System.out.println("Type the path to the txts directory that contains the articles.");
        String filepath = scan.nextLine();

        System.out.println("Alright, what ya next movement\n 1 - Only compress with huffman and store on a hash table\n 2 - Just index the words on a Trie structure \n3 - EVERYTHING, LETS GOO");
        int op = scan.nextInt();

        //reads the files, gets and compresses the content, then stores on hash table
        try {
            List<FileData> filesData = readFiles(filepath);

            if(op == 1){
                //Asks what hashing should be used
                System.out.println("Which hash algorithm do you want to use? \n 1 - hash by division or 2 - hash DHB2?");
                int hashAlgo = scan.nextInt();

                HashTable<String, String> myTable = new HashTable<String, String>(30, hashAlgo);
                huffmanStuff(filesData, myTable);
                myTable.printOut();
                
            }else if(op == 2){
                Trie myTrie = new Trie();
                trieStuff(filesData, myTrie);
            }else{

                Trie myTrie = new Trie();
                trieStuff(filesData, myTrie);
                
                //Asks what hashing should be used
                System.out.println("Which hash algorithm do you want to use? \n 1 - hash by division or 2 - hash DHB2?");
                int hashAlgo = scan.nextInt();

                HashTable<String, String> myTable = new HashTable<String, String>(30, hashAlgo);
                huffmanStuff(filesData, myTable);
            }

            scan.close();
        }catch (Exception e){
            System.out.println("\nPROBLEM ON MAIN FUNCTION: " +  e);
        }   
    }
    
    public static void huffmanStuff(List<FileData> filesData, HashTable<String, String> myTable) throws Exception{
        try {
            for(FileData file : filesData){

                String filename = file.getFilename();
                String compressedFileContent = compress(file.getContent());
                System.out.print("Compressed: " + filename);

                //storing on hashTable
                if(myTable.put(filename, compressedFileContent));
                    System.out.println(" Stored: " + filename + "\n");
            }
        } catch (Exception e) {
            System.out.println("PORBLEM AT huffmanStuff(); ERROR: " + e);
        }

    }

    public static void trieStuff(List<FileData> filesData, Trie myTrie){
        try {
            myTrie.insertText("time, it needs time, to win back your love again. Love, it needs love, to bring back your love soma day!", "bazinga");
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