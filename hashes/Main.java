package hashing;

public class Main{

    public void main(String[] args){
        HashTable<Integer, String> testHash = new HashTable<Integer, String>(10);
        testHash.put(1, "Sheldon");
        testHash.put(1, "Nome");
        testHash.put(3, "Azingfa");
        testHash.put(5, "Vinicius");
        testHash.put(4, "Buarque");
        testHash.put(1, "Luiza");
        testHash.put(8, "Vinicius");
        testHash.put(8, "Vinicius"); //Do not insert a same value into a same position

        testHash.printOut();
        System.out.println(testHash.get(1));//try to get all values in position at key 1;
    }
}