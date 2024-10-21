/**
* Author: Timothy Pratt
* Date: 11/01/2022
* File: HashMap.java
* Section A
* Project 7
* CS231
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class HashMap<K, V> implements MapSet<K, V>
{
    LinkedList<KeyValuePair<K, V>>[] map;
    int size;
    double loadFactor;
    int collisions;

    //returns the length of map
    private int capacity()
    {
        return map.length;
    }

    //returns index of a key from its hash code and map capacity
    private int hash(K key)
    {
        return Math.abs(key.hashCode() % capacity());
    }

    // private int hash(K key)
    // {
    //     return (int) Math.abs((capacity() * key.hashCode() * 1.618) % capacity());
    // }

    //hash map constructors
    public HashMap()
    {
        map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[10];
        size = 0;
        loadFactor = .75;
        collisions = 0;
    }
    
    public HashMap(int initialCapacity)
    {
        map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[initialCapacity];
        size = 0;
        loadFactor = .75;
        collisions = 0;
    }

    public HashMap(int initialCapacity, double loadFactor)
    {
        map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[initialCapacity];
        size = 0;
        this.loadFactor = loadFactor;
        collisions = 0;
    }

    //returns size
    public int size()
    {
        return size;
    }

    //resets all fields
    public void clear()
    {
        map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[10];
        size = 0;
    }

    //adds new key-value pair to hash map
    public V put(K key, V value)
    {
        int index = hash(key);

        if (map[index] == null)
        {
            map[index] = new LinkedList<KeyValuePair<K, V>>();
            map[index].add(new KeyValuePair<K, V>(key, value));
            size++;

            if (size > loadFactor * capacity())
            {
                resizeUp();
            }

            return null;
        }
        else
        {
            for (KeyValuePair<K, V> kvp : map[index])
            {
                if (kvp.getKey().equals(key))
                {
                    V oldValue = kvp.getValue();
                    kvp.setValue(value);
                    return oldValue;
                }
            }

            map[index].add(new KeyValuePair<K, V>(key, value));
            size++;
            collisions++;

            if (size > loadFactor * capacity())
            {
                resizeUp();
            }

            return null;
        }
    }

    //increases capacity of hash map if current size > capacity * loadFactor
    private void resizeUp()
    {
        ArrayList<KeyValuePair<K, V>> kvps = entrySet();
        map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[(int) (capacity() / loadFactor)];
        size = 0;
        collisions = 0;

        for(KeyValuePair<K, V> kvp : kvps)
        {
            put(kvp.getKey(), kvp.getValue());
        }
    }

    //returns value associated with given key
    public V get(K key) {
        int index = hash(key);

        if (map[index] == null)
        {
            return null;
        }
        else
        {
            for (KeyValuePair<K, V> kvp : map[index])
            {
                if (kvp.getKey().equals(key))
                {
                    return kvp.getValue();
                }
            }
            
            return null;
        }
    }

    //returns true if hash map contains a key-value pair with given key
    public boolean containsKey(K key)
    {
        if (get(key) == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //removes a key value pair associated with given key
    public V remove(K key)
    {
        V toReturn = get(key);
        int index = hash(key);

        if (map[index] == null)
        {
            return null;
        }
        else
        {
            for (KeyValuePair<K, V> kvp : map[index])
            {
                if (kvp.getKey().equals(key))
                {
                    map[index].remove(kvp);
                    break;
                }
            }

            size--;

            if (size < loadFactor * capacity())
            {
                resizeDown();
            }

            return toReturn;
        }
    }

    //decreases capacity of hash map if current size < capacity * loadFactor^3
    private void resizeDown()
    {
        ArrayList<KeyValuePair<K, V>> kvps = entrySet();
        map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[(int) (capacity() * loadFactor)];
        size = 0;
        collisions = 0;

        for(KeyValuePair<K, V> kvp : kvps)
        {
            put(kvp.getKey(), kvp.getValue());
        }
    }

    //returns array list of all keys in hash map
    public ArrayList<K> keySet()
    {
        ArrayList<K> output = new ArrayList<K>();

        for(LinkedList<KeyValuePair<K, V>> list : map)
        {
            if (list == null)
            {
                continue;
            }
            for(KeyValuePair<K, V> kvp : list)
            {
                output.add(kvp.getKey());
            }
        }

        return output;
    }

    //returns array list of all values in hash map
    public ArrayList<V> values()
    {
        ArrayList<V> output = new ArrayList<V>();

        for(LinkedList<KeyValuePair<K, V>> list : map)
        {
            if (list == null)
            {
                continue;
            }
            for(KeyValuePair<K, V> kvp : list)
            {
                output.add(kvp.getValue());
            }
        }

        return output;
    }

    //returns array list of all key value pairs in hash map    
    public ArrayList<MapSet.KeyValuePair<K, V>> entrySet()
    {
        ArrayList<KeyValuePair<K, V>> output = new ArrayList<KeyValuePair<K, V>>();

        for(LinkedList<KeyValuePair<K, V>> list : map)
        {
            if (list == null)
            {
                continue;
            }
            for(KeyValuePair<K, V> kvp : list)
            {
                output.add(kvp);
            }
        }
        return output;
    }

    //returns a string representation of hash map
    public String toString()
    {
        String s = "";
        ArrayList<MapSet.KeyValuePair<K, V>> arr = entrySet();

        for (int i = 0; i < arr.size(); i++)
        {
            s += "" + arr.get(i).getKey() + "     " + arr.get(i).getValue() + "\n";
        }

        return s;
    }

    public int getDepth()
    {
        return 0;
    }

    public int getCollisions()
    {
        return collisions;
    }

    public static void main(String[] args)
    {
        HashMap hm = new HashMap();

        //testing capacity
        System.out.println(hm.capacity() + " = 10");

        //testing size
        System.out.println(hm.size() + " = 0");

        //testing put
        hm.put("one", 1);
        hm.put("two", 2);
        hm.put("three", 3);
        hm.put("four", 4);
        hm.put("five", 5);

        //testing size again
        System.out.println(hm.size() + " = 5");

        //testing get
        System.out.println(hm.get("one") + " = 1");
        System.out.println(hm.get("two") + " = 2");
        System.out.println(hm.get("three") + " = 3");
        System.out.println(hm.get("four") + " = 4");
        System.out.println(hm.get("five") + " = 5");

        //testing containsKey
        System.out.println(hm.containsKey("one") + " = true");
        System.out.println(hm.containsKey("zero") + " = false");

        //testing remove
        hm.remove("five");
        System.out.println(hm.size() + " = 4");

        //testing keySet
        System.out.println(hm.keySet());

        //testing values
        System.out.println(hm.values());

        //testing entrySet
        System.out.println(hm.entrySet());

        //testing toString
        System.out.println(hm.toString());

        //testing clear
        hm.clear();
        System.out.println(hm.size() + " = 0");
    }
}