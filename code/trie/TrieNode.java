package trie;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public class TrieNode {
	
    Map<Character, TrieNode> children;
    LinkedList<String> documentList; //that guy stores a linked list of the documents the word appears
    boolean isEndOfWord;

    // empty constructor just for the nodes
    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }

    //that constructor stores an end of word, which means is also adds a document to the list
    public void endOfWord(boolean isEndOfWord, String documentTitle) {
        this.children = new HashMap<>();
        this.isEndOfWord = isEndOfWord;

        if(this.documentList != null){
            if(!documentList.contains(documentTitle))//if the list alredy have that document there is no need to add again
                documentList.add(documentTitle);
        }else{
            this.documentList = new LinkedList<String>();
                documentList.add(documentTitle);
        }
    }
	
}