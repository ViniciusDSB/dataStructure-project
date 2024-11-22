package trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TrieNode {
	
    Map<Character, TrieNode> children;
    LinkedList<String> docList = null;
    boolean isEndOfWord;

    // Construtor padrão
    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }
	
}