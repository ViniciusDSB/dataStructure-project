package compresses;

import java.util.*;

//we probably need to change the Map<> collection to our own hash system

public class HuffmanTrie {
    
    TrieNode root;

    //a key and a value mapped
    Map<Character, String> huffmanCodeMap;

    public HuffmanTrie(String input){
        Map<Character, Integer> frequencies = calculatefrequencies(input);
        huffmanCodeMap = new HashMap<>();
        buildTrie(frequencies);
    }

    private Map<Character, Integer> calculatefrequencies(String input){
        //its a hash maping a value to an integer
        Map<Character, Integer> frequencies = new HashMap<>();
        
        char currentChar;
        int currentFreq;

        //reads all the position of the input in the string
        for(int i = 0; i<input.length(); i++){
            currentChar = input.charAt(i);

            //returns the value associated with that key, or 0 if the value is not there
            currentFreq = frequencies.getOrDefault(currentChar, 0);
            
            //adds 1 on the frequwncie and store back on the table
            frequencies.put(currentChar, currentFreq);
        }

        return frequencies;
    }

    private void buildTrie(Map<Character, Integer> frequencies){
        //priority list to store the nodes; priority is proportionaly inverse to the frequency
        //(most frequenct simbols must be added first)
        PriorityQueue<TrieNode> priority = new PriorityQueue<TrieNode>();
    
        //iterate shtorug the frequency table and created a node for each
        for(Map.Entry<Character, Integer> entry: frequencies.entrySet()){
            TrieNode newNode = new TrieNode(entry.getKey(), entry.getValue());
            priority.add(newNode);
        }

        while(priority.size() > 1){
            //new node with the child
            TrieNode left = priority.poll();
            TrieNode right = priority.poll();
            TrieNode newNode = new TrieNode(left.frequency + right.frequency, left, right);
            
            priority.add(newNode);
        }

        root = priority.poll(); //here is where the trie is created
        generateHuffmanCodes(root, "");
    }

    private void generateHuffmanCodes(TrieNode node, String code){

        if(node == null) return;

        //leaf node -> add the node to the symbol table
        //with correspondig codiication

        if((node.left == null && node.right == null)){
            huffmanCodeMap.put(node.character, code);
        }

        //left child - add 0 to the representairon, right -> adds 1
        generateHuffmanCodes(node.left, code + '0');
        generateHuffmanCodes(node.right, code + '1');


    }

    //method that compresses the string
    public String compress(String input){

        String compressedInput = "";

        //iterate throught the string and gets the char
        //adds it to the huffman representarion
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
//não da pra usar recursão da decompressão? ou mesmo compressão
//por que não usar numeors inteiros no 0 e 1? será por que inteiros ocupam mais byes?

//The code teacher wrote is not optimized: its vocabulary is defineed as being the input you gave to compress
//so, for exaple, if you compress the word marvel you have problem trying to decompess the word "kiwi";


