package trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insertText(String text, String docTitle){
        
        String[] symbols = {".", ",", "!", "?", "...", ";"};
        
        for(String word : text.split(" ")){
            for(String symbol : symbols)
                if(word.indexOf(symbol) >=0)
                    word = word.replace(symbol, "");
                
            word = word.toLowerCase();
            insertWord(word, docTitle);
            
        }

    } 

    // inserts a word to the Trie tree
    public void insertWord(String word, String docTitle) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;
        //block to set the document list on the word end node
        if(node.docList == null)
            node.docList = new LinkedList<>();
        
        if(! node.docList.contains(docTitle))
            node.docList.add(docTitle);

        }

    // Método para buscar uma palavra completa na Trie
    public LinkedList search(String word) {
        TrieNode node = root;
        word = word.toLowerCase();
        for (char ch : word.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
                return null;
            }
        }
        return node.docList;
    }
}