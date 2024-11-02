package huffman;

public class Main {
 
    public static void main(String[] args){

        String input = "abracadabra";
        HuffmanTrie huffman = new HuffmanTrie(input + input+ input+ input+ input);

        String compressed = huffman.compress("abracadabra");
        System.out.println("Length: " + compressed.length());

        String decompressed = huffman.decompress(compressed);
        System.out.println("Length: " + decompressed.length());

        System.out.println("Compressed " + compressed);
        System.out.println("Decompressed: " + decompressed);

    }

}
