package hashes;

import java.util.LinkedList;

public class HashTable <K, V>{
    
    private LinkedList<HashEntry<K,V>>[] hashTable;
    private int size;

    public HashTable(int size){
        hashTable = new LinkedList[size];
        this.size = size;
    }

    //code teacher told us to use, user must select one of them
    public int hashDivisao (String texto , int M ) {
        int soma = 0;
            for ( char c : texto . toCharArray () ) {
            soma += (int ) c ;
        }

        return soma % M ;
    }
    public int hashDJB2 ( String texto ) {
        long hash = 5381;
        for ( char c : texto.toCharArray ()){
            hash = (( hash << 5) + hash ) + c; // hash * 33 + c
        }

        return (int)( hash % Integer.MAX_VALUE );
    }

    //made in class
    public int getPosition(int value){
        return value%size;
    }

    public LinkedList<V> get(K key){
        int position;
        LinkedList<V> valuesFound = new LinkedList<V>();

        if(key == null)
            return null;
    
        position = getPosition(Math.abs( key.hashCode() ));
        
        //Se a posição do hash não tem valores retorna um null
        if(hashTable[position] == null)
            return null;
        else{
            LinkedList<HashEntry<K, V>> currentList = hashTable[position];
            for(HashEntry<K, V> currentEntry : currentList){
                if(key.equals( currentEntry.key )){
                    valuesFound.add(currentEntry.value);
                }
            }
            return valuesFound; //caso não tenha encontrado a chave na lista
        }
    }

    public boolean put(K key, V value){
        int position;
        if(key == null)
            return false;
        
        //checks if there no equal value to the one we are trying to insert
        LinkedList<V> currentValueForKey = get(key);
        if(currentValueForKey != null && currentValueForKey.contains(value))
            return false;

        //similar to a codification, the hashCode() method it a java deefautl method
        //that associates a number to an object, as a way to identify it.
        //it is used for comparing objects, exemple;
        //in this case it is useful cuz we dont have to worry if the key is
        //a string or an integer. it return an interger, positive or negative, thay why abs.
        //The lisked list librady takes care of the linked list insertion :)

        position = getPosition(Math.abs( key.hashCode() ));
    
        LinkedList<HashEntry<K, V>> currentList = hashTable[position];
        if(currentList == null)
            currentList = new LinkedList<HashEntry<K, V>> (); //creates the list
            
        currentList.add(new HashEntry<K, V> (key, value)); //inserts the value
        hashTable[position] = currentList; //inserts the list on the positon
            
        return true; //a success inserton!
    }
    
    public void printOut(){

        for(int i = 0; i<hashTable.length; i++){
            System.out.println("-----------");
            System.out.println("Positon " + i + ":");
            if(hashTable[i] == null)
                System.out.println("Empty position");
            else{
                LinkedList<HashEntry<K, V>> currentList = hashTable[i];
                for(int j = 0; j<currentList.size(); i++){
                    System.out.println(currentList.get(j).toString() + " - ");
                }
            }
        }

    }
}