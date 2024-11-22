package hashes;

import java.util.LinkedList;

public class HashTable <K extends String, V>{
    
    //We start creating a vector of linked lists (each position is a list)

    private LinkedList<HashEntry<K,V>>[] hashTable;
    private int size;
    
    private int hashingAlgorithm;
    //can be 0, 1 or 2
    //0 - Default, simplest, made in class, not very efficient
    //1 - Hasing by division
    //2 - DBJ2 algorithm, that one is weird but the guy says it distirbutes the positions very well

    public HashTable(int size, int hashingAlgorithm){
        this.size = size;
        this.hashingAlgorithm = hashingAlgorithm;
        hashTable = new LinkedList[size];
    }

    //To get the position it checks witch hashing algorithm we are using
    public int getPosition(K Tag) throws Exception{
        int position = 0;

        try{
            if(hashingAlgorithm == 0)
                position = Math.abs( Tag.hashCode() );
            if(hashingAlgorithm == 1)
                position = hashDivisao(Tag);
            if(hashingAlgorithm == 2)
                position = hashDJB2(Tag);
            
            return position;
        }catch(Exception e){
            throw new Exception("\nError at getPosition(); Current state: Tag: "+Tag+"; Position: "+position+"; Error: "+e);
        }
    }

    public int hashDivisao (K Tag){
        int soma = 0;

        for ( char c : Tag.toCharArray() ){
            soma += (int) c;
        }
        return soma % this.size;
    }
    public int hashDJB2 (K Tag) {
        long hash = 5381;
        for ( char c : Tag.toCharArray()){
            hash = (( hash << 5 ) + hash) + c; // hash * 33 + c
        }

        return Math.abs((int)( hash % this.size ));
    }

    public LinkedList<V> find(K Tag) throws Exception{
        if(Tag == null)
            return null;
        
        int position = 0;
        LinkedList<V> blockFound = new LinkedList<V>();

        try {
            position = getPosition(Tag);
        
            //Returns null if the position is empty
            if(hashTable[position] == null)
                return null;
            else{
                LinkedList<HashEntry<K, V>> currentBlock = hashTable[position];
                
                for(HashEntry<K, V> currentEntry : currentBlock){
                    if(Tag.equals( currentEntry.Tag )){
                        blockFound.add(currentEntry.value);
                    }
                }
                return blockFound;
            }
        } catch (Exception e) {
            throw new Exception("\nError at find(); Current state: Tag: "+Tag+"; Position: "+position+"; Error: "+e);
        }
    }

    public boolean put(K Tag, V value) throws Exception{
        int position = 0;
        if(Tag == null)
            return false;
        
        //checks if there no equal value to the one we are trying to insert
        //in this system this wont occour cuz the articles are different
        
        try {
            LinkedList<V> currentValueForTag = find(Tag);

            if(currentValueForTag != null && currentValueForTag.contains(value))
                return false;

            position = getPosition(Tag);
        
            LinkedList<HashEntry<K, V>> currentList = hashTable[position];
            if(currentList == null)
                currentList = new LinkedList<HashEntry<K, V>> (); //creates the list
                
            currentList.add(new HashEntry<K, V> (Tag, value)); //inserts the value
            hashTable[position] = currentList; //inserts the list on the positon
                
            return true; //A success inserton!
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("\nError at put(); Current state: Tag: "+Tag+" Position: "+position+" Error: "+e);
        }

    }
    
    public void printOut(){

        for(int i = 0; i<hashTable.length; i++){
            System.out.println("-----------");
            System.out.println("Positon " + i + ":");
            if(hashTable[i] == null)
                System.out.println("Empty position");
            else{
                LinkedList<HashEntry<K, V>> currentList = hashTable[i];
                System.out.println(currentList.size() +" Items in this position");//instead of content i just count how many values in the mosition
                
                //for(int j = 0; j<currentList.size(); j++){
                //    System.out.println(currentList.get(j).toString() + " - ");
                //}
            }
        }
    }

    public String[] getDistribuition(){

        String[] data = new String[33];
        String tmp = "";

        //first position is the hash algorithm used
        if(hashingAlgorithm == 1)
            data[0] = "hashDivisao";
        else
            data[0] = "hashDJB2";

        //second position is axis information ( position vs amount of txts)
        data[1] = "pos amount";

        //the other position are  position-quantity pair values
        for(int i = 0; i<hashTable.length; i++){
            tmp = (i) + " ";
            if(hashTable[i] == null)
                tmp = tmp + "0";
            else{
                LinkedList<HashEntry<K, V>> currentList = hashTable[i];
                tmp = tmp + currentList.size();//instead of content i just count how many values in the mosition
            }

            data[i+2] = tmp;
        }

        return data;

    }
}
