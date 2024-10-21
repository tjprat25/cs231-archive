/**
* Author: Timothy Pratt
* Date: 10/25/2022
* File: BSTMap.java
* Section A
* Project 7
* CS231
*/

import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements MapSet<K, V>
{
    //node class
    private class Node
    {
        Node left;
        Node right;
        KeyValuePair<K, V> kvp;

        //node constructor
        public Node(KeyValuePair<K, V> kvp)
        {
            this.kvp = kvp;
            left = null;
            right = null;
        }

        //returns nodes key from kvp
        public K getKey()
        {
            return kvp.getKey();
        }

        //returns nodes value from kvp
        public V getValue()
        {
            return kvp.getValue();
        }
    }

    private Node root;
    public int size;

    //BSTMap constructor
    public BSTMap()
    {
        root = null;
        size = 0;
    }

    //returns size of BSTMap
    public int size()
    {
        return size;
    }

    //sets root to null and size to 0
    public void clear()
    {
        root = null;
        size = 0;
    }

    //adds new node to BSTMap
    public V put(K key, V value)
    {
        return put (key, value, root);
    }

    //put recursive method
    private V put(K key, V value, Node cur)
    {
        if (cur == null)
        {
            Node n = new Node(new KeyValuePair<K, V>(key, value));
            root = n;
            size++;
            return null;
        }
        
        if (key.compareTo(cur.getKey()) < 0)
        {
            if (cur.left != null)
            {
                return put(key, value, cur.left);
            }

            else
            {
                Node n = new Node(new KeyValuePair<K, V>(key, value));
                cur.left = n;
                size++;
                return null;
            }
        }

        else if (key.compareTo(cur.getKey()) > 0)
        {
            if (cur.right != null)
            {
                return put(key, value, cur.right);
            }

            else
            {
                Node n = new Node(new KeyValuePair<K,V>(key, value));
                cur.right = n;
                size++;
                return null;
            }
        }

        else
        {
            V val = cur.getValue();
            cur.kvp = new KeyValuePair<K,V>(key, value);
            return val;
        }
    }

    //returns value associated with a given tree, if there is a Node with that key in the BSTMap
    public V get(K key)
    {
        return get(key, root);
    }

    public V get(K key, Node cur)
    {
        if (cur == null)
        {
            return null;
        }

        if (key.compareTo(cur.getKey()) < 0)
        {
            return get(key, cur.left);
        }

        else if (key.compareTo(cur.getKey()) > 0)
        {
            return get(key, cur.right);
        }

        else
        {
            return cur.getValue();
        }
    }

    //checks if there is a Node with a given key in BSTMap
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

    //returns an array list of all keys in BSTMap in sorted order
    public ArrayList<K> keySet()
    {
        ArrayList<K> keys = new ArrayList<K>();
        keySet(root, keys);
        return keys;
    }

    //keySet recursive method
    private void keySet(Node cur, ArrayList<K> output)
    {
        if (cur == null)
        {
            return;
        }

        keySet(cur.left, output);
        output.add(cur.getKey());
        keySet(cur.right, output);
    }

    //returns an array list of all values in BSTMap in the order of their respective keys
    public ArrayList<V> values()
    {
        ArrayList<V> values = new ArrayList<V>();
        values(root, values);
        return values;
    }

    //values recursive method
    private void values(Node cur, ArrayList<V> output)
    {
        if (cur == null)
        {
            return;
        }

        values(cur.left, output);
        output.add(cur.getValue());
        values(cur.right, output);
    }

    //returns an array list of all kvps in BSTMap in the sorted order of their respective keys
    // public ArrayList<KeyValuePair<K, V>> entrySet()
    // {
    //     ArrayList<KeyValuePair<K, V>> kvps = new ArrayList<KeyValuePair<K, V>>();
    //     entrySet(root, kvps);
    //     return kvps;
    // }

    //entrySet recursive method
    // private void entrySet(Node cur, ArrayList<KeyValuePair<K, V>> output)
    // {
    //     if (cur == null)
    //     {
    //         return;
    //     }

    //     entrySet(cur.left, output);
    //     output.add(cur.kvp);
    //     entrySet(cur.right, output);
    // }

    //returns a string representation of BSTMap
    public String toString()
    {
        return toString(root, 1, "root");
    }

    //toString recursive method
    private String toString(Node cur, int depth, String direction)
    {
        if (cur == null)
        {
            return "";
        }

        String left = toString(cur.left, depth + 1, "left");
        String middle = direction + "     ".repeat(depth) + cur.getKey() + " " + cur.getValue() + "\n";
        String right = toString(cur.right, depth + 1, "right");
        return right + middle + left;
    }

    //returns an array list of all keys in BSTMap in pre-order
    public ArrayList<KeyValuePair<K, V>> entrySet()
    {
        ArrayList<KeyValuePair<K, V>> kvps = new ArrayList<KeyValuePair<K, V>>();
        entrySet(root, kvps);
        return kvps;
    }

    //preOrder recursive method
    private void entrySet(Node cur, ArrayList<KeyValuePair<K, V>> output)
    {
        if (cur == null)
        {
            return;
        }

        output.add(cur.kvp);
        entrySet(cur.left, output);
        entrySet(cur.right, output);
    }

    //removes the Node with the given key from the BSTMap
    public void remove(K k)
    {
        if (size == 0)
        {
            return;
        }

        Node cur = root;
        Node prev = null;

        while (true)
        {
            if (k.compareTo(cur.getKey()) < 0)
            {
                prev = cur;

                if (cur.left == null)
                {
                    return;
                }

                cur = cur.left;
            }
            else if (k.compareTo(cur.getKey()) > 0)
            {
                prev = cur;

                if (cur.right == null)
                {
                    return;
                }
                cur = cur.right;
            }
            else
            {
                if (cur.left == null && cur.right == null)
                {
                    if (cur == root)
                    {
                        root = null;
                    }
                    else if (cur == prev.left)
                    {
                        prev.left = null;
                    }
                    else
                    {
                        prev.right = null;
                    }
                }
                else if (cur.right == null)
                {
                    if (cur == root)
                    {
                        root = cur.left;
                    }
                    else if (cur == prev.left)
                    {
                        prev.left = cur.left;
                    }
                    else
                    {
                        prev.right = cur.left;
                    }
                }
                else if (cur.left == null)
                {
                    if (cur == root)
                    {
                        root = cur.right;
                    }
                    if (cur == prev.left)
                    {
                        prev.left = cur.right;
                    }
                    else
                    {
                        prev.right = cur.right;
                    }
                }
                else
                {
                    Node cur2 = cur.left;
                    Node prev2 = cur;
                    while (cur2.right != null)
                    {
                        prev2 = cur2;
                        cur2 = cur2.right;
                    }
                    if (cur2 == prev2.right)
                    {
                        prev2.right = cur2.left;
                    }
                    else
                    {
                        prev2.left = cur2.left;
                    }
                    
                    cur.kvp = cur2.kvp;
                }

                size--;
                return;
            }
        }
    }

    public int getDepth()
    {
        return getDepth(root);
    }

    private int getDepth(Node n)
    {
        if (n == null)
        {
            return 0;
        }
        else
        {
            int left = getDepth(n.left);
            int right = getDepth(n.right);

            if (left > right)
            {
                return left + 1;
            }
            else
            {
                return right + 1;
            }
        }
    }

    public int getCollisions()
    {
        return 0;
    }
}