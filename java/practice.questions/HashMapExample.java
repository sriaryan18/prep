@SuppressWarnings("unchecked")
public class HashMapExample<K, V> {
    K[] keys;
    V[] values;
    int SIZE = 10;
    int counter = 0;
    int salt = 13232;

    public HashMapExample() {
        keys = (K[]) new Object[SIZE];
        values = (V[]) new Object[SIZE];
    }

    private int generateHash(K key) {
        String qualifiedValueOfKey = key.toString();
        int sum = 0;
        for (int i = 0; i < qualifiedValueOfKey.length(); i++) {
            sum += (int)qualifiedValueOfKey.charAt(i);
        }
        System.out.println("HASH GENERATED " + (int) (sum * salt) % SIZE + " " + qualifiedValueOfKey);
        return (int) (sum * salt) % SIZE;

    }

    public void increaseSizeOfArrays() {
        SIZE *= 2;
        keys = (K[]) new Object[SIZE];
        values = (V[]) new Object[SIZE];
    }

    public void put(K key, V value) {
        int idxToInsertValue = generateHash(key);
        if (keys[idxToInsertValue] == key) {
            values[idxToInsertValue] = value;
        } else if (values[idxToInsertValue] != null) {
            increaseSizeOfArrays();
            put(key, value);
        } else {

            keys[idxToInsertValue] = key;
            values[idxToInsertValue] = value;
        }

    }

    public V get(K key) {
        int idxToSearchValue = generateHash(key);
        V val = null;
        if (idxToSearchValue < values.length) {

            val = values[idxToSearchValue];

        }
        return val;
    }

}
