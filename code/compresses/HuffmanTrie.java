package compresses;

import java.util.*;

//Raw usage exemple of huffmanTrie
//String input = "abracadabra";
//HuffmanTrie huffman = new HuffmanTrie(input);
//
//String compressed = huffman.compress("abracadabra");
//String decompressed = huffman.decompress(compressed);
//    
//System.out.println("Compressed " + compressed);
//System.out.println("Decompressed: " + decompressed);
//
//should return Compressed 111001011101101110111111100101110
//Decompressed: abracadabra


public class HuffmanTrie {
    
    //The compreessed text in a HuffmanTrie tree
    TrieNode root;

    //A hash table with the binary code corresponding to each character
    Map<Character, String> huffmanCodeMap;

    public HuffmanTrie(String input){
        Map<Character, Integer> frequencies = mapCharFrequencies(input); //returns a hashMap
        huffmanCodeMap = new HashMap<>();
        buildTrie(frequencies);
    }

    private Map<Character, Integer> mapCharFrequencies(String input){
        //its a hash maping a value to an integer
        Map<Character, Integer> frequenciesMap = new HashMap<>();
        
        char currentChar;
        int currentFreq;

        //reads all the characters of the input in string
        for(int i = 0; i<input.length(); i++){
            currentChar = input.charAt(i);

            //returns the frequencie of the character or 0 if its not there yet
            currentFreq = frequenciesMap.getOrDefault(currentChar, 0);
            
            //increases the frequencie and stores on the hashTable
            frequenciesMap.put(currentChar, currentFreq+1);
        }

        return frequenciesMap;
    }

    private void buildTrie(Map<Character, Integer> frequencies){
        //Here we use priority list to store the nodes;
        //priority is proportionaly inverse to the frequency
        //so most frequent simbols must be added first, cuz huffmanTrie trees are bottom-top built
        //By doing that less frequent symbols get bigger codes, because they are in the bottom

        //--Make That with handmade priorQueue
        PriorityQueue<TrieNode> priority = new PriorityQueue<TrieNode>();
    
        //iterates through the frequenciesMap and creates a node for each

        //--the following is the new code with the handmade hashTable and TrieNode
        //for(hashEntry<Character, Integer> entry : frequencies.toVector()){
        //    TrieNode newNode = new TrieNode(entry.getKey(), entry.getValue());
        //    priority.add(newNode);
        //}

        //teacher code with java predefined structures
        for(Map.Entry<Character, Integer> entry : frequencies.entrySet()){
            TrieNode newNode = new TrieNode(entry.getKey(), entry.getValue());
            priority.add(newNode);
        }

        while(priority.size() > 1){
            //creates new left and right Trie nodes with the first elements of the priorityQueue
            //and creates a node that is just the sum of left and right frequencies
            TrieNode left = priority.poll();
            TrieNode right = priority.poll();
            TrieNode newNode = new TrieNode(left.frequency + right.frequency, left, right);
            
            //stores that frequency node on the queue
            //so the queue is always organized by frequncy priority
            priority.add(newNode);
            
        }

        //When there is only 1 element in the queue
        //that last element is a huffmanTrie tree root
        root = priority.poll(); 
        generateHuffmanCodes(root, "");
    }

    private void generateHuffmanCodes(TrieNode node, String code){

        if(node == null) return;

        //leaf node -> add the node to the symbol table
        //with correspondig codiication

        if((node.left == null && node.right == null)){
            huffmanCodeMap.put(node.character, code);
        }

        //left child - add 0 to the representaion, right -> adds 1
        generateHuffmanCodes(node.left, code + '0');
        generateHuffmanCodes(node.right, code + '1');


    }

    //method that compresses a text based on our huffmanCodeMap
    public String compress(String input){

        String compressedInput = "";

        //gets each character representation from the huffmanCodeMap and adds to thecompressed string
        for (int i = 0; i < input.length(); i++) {
            compressedInput += huffmanCodeMap.get(input.charAt(i));
        }

        return compressedInput;
    }

    public String decompress(String compressedInput){

        String decompressedInput = "";
        TrieNode current = root;
        char currentBit;

        for (int i = 0; i < compressedInput.length(); i++) {
            currentBit = compressedInput.charAt(i);
            if(currentBit == '0')
                current = current.left;
            else
                current = current.right;

            if(current.left == null && current.right == null){
                decompressedInput += current.character;
                current = root;
            }
            
        }

        return decompressedInput;

    }

}

//do not forget to change the bit comparison to simple quotes, its a char
//Para o trabalhho poderems usar as estruturas do proprio j\va, com opritoqueue e map?

//The code teacher wrote is not optimized: its vocabulary is defineed as being the input you gave to compress
//so, for exaple, if you compress the word marvel you have problem trying to decompess the word "kiwi";


