package trie;

import java.util.*;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    //metho to take care of long texts
    //it splits the texts in words and adds the word into the Trie tree and its equivalent with ponctuation if any
    public void insertText(String text, String documentTitle){

        String[] symbols = {".", ",", "!", "?", ";", "..."};
        String auxWord = ""; //if the word has any of the symbols we store both versions
        Boolean symbolFlag;
        
        for(String word : text.split(" ")){
            symbolFlag = false;

            for(String symbol : symbols){
                if(word.indexOf(symbol) >=0){
                    auxWord = word.replace(symbol, "");
                    symbolFlag = true;
                }
            }
        
            if(!symbolFlag){
                insertWord(word, documentTitle);
            }else{
                insertWord(word, documentTitle);
                insertWord(auxWord, documentTitle);
            }
        }
    }
    //Inserts a single word on the Trie tree
    public void insertWord(String word, String documentTitle) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {

            //children is a hash map, so we add a key-value pair
            //where key is the char itself and value is another node
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.endOfWord(true, documentTitle);
    }

    //Returns true if a given word is stored
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
                return false;
            }
        }
        return node.isEndOfWord;
    }

    // Método para verificar se existe alguma palavra que começa com o prefixo
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
                return false;
            }
        }
        return true;
    }
}