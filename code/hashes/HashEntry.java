package hashes;

public class HashEntry<K, V>{
    
    public K Tag;
    public V value;

    public HashEntry(K Tag, V value){
        this.Tag = Tag;
        this.value = value;
    }

    public K getTag() {
        return Tag;
    }
    public V getValue() {
        return value;
    }

    public String toString() {
		return "(" + Tag + ", " + value + ")";
	}

}
