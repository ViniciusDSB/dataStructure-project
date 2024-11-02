package huffman;

//implementing the interface method for compargn objects
//in this case we specify objects of the same type, TrieNode
public class TrieNode implements Comparable<TrieNode>{
    
    char character;
    int frequency; //also know as priority or weight

    TrieNode left, right;

    //intermediary node constructor
    public TrieNode(int frequency, TrieNode left, TrieNode right){
        this.character = '\0'; //this node has no character
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    //leaf node constructor
    public TrieNode(char character, int frequency){
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public int compareTo(TrieNode otherNode){
        return (this.frequency - otherNode.frequency);
    }

}