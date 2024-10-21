/**
* Author: Timothy Pratt
* Date: 11/08/2022
* File: HashMap.java
* Section A
* Project 7
* CS231
*/

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WordCounter
{
    MapSet map;
    int totalWordCount;

    //WordCounter constructor
    public WordCounter(String data_structure)
    {
        if (data_structure.equals("bst"))
        {
            map = new BSTMap<String, Integer>();
        }
        else if (data_structure.equals("hashmap"))
        {
            map = new HashMap<String, Integer>(100);
        }
        else
        {
            System.out.println("Please use either bst or hashmap");
        }
    }

    //reads text file and returns ArrayList of all words in file
    public ArrayList<String> readWords(String filename)
    {
        ArrayList<String> arr = new ArrayList<String>();

        try
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            
            while(line != null)
            {
                String[] words = line.split("[^a-zA-Z0-9']");

                for (int i = 0; i < words.length; i++)
                {
                    String word = words[i].trim().toLowerCase();

                    if (word.length() > 0)
                    {
                        arr.add(word);
                    }
                }

                line = br.readLine();
            }

            br.close();
        }

        catch(FileNotFoundException ex)
        {
            System.out.println("WordCounter.read():: unable to open file " + filename );
        }

        catch(IOException ex)
        {
            System.out.println("WordCounter.read():: error reading file " + filename);
        }
        
        totalWordCount = arr.size();
        return arr;
    }

    //puts words from given ArrayList into map, returns time taken in ms
    public double buildMap(ArrayList<String> words)
    {
        for (int i = 0; i < words.size(); i++)
        {
            if (map.containsKey(words.get(i)))
            {
                map.put(words.get(i), (int) map.get(words.get(i)) + 1);
            }
            else
            {
                map.put(words.get(i), 1);
            }
        }

        return System.nanoTime();
    }

    //clears map
    public void clearMap()
    {
        map.clear();
    }

    //returns total word wount
    public int totalWordCount()
    {
        return totalWordCount;
    }

    //returns number of unique words in map
    public int uniqueWordCount()
    {
        return map.size();
    }

    //returns count of given word in map
    public int getCount(String word)
    {
        if (map.containsKey(word))
        {
            return (int) map.get(word);
        }
        else
        {
            return 0;
        }
    }

    //returns frequency of given word in map
    public double getFrequency(String word)
    {
        if (map.containsKey(word))
        {
            return (double) getCount(word) / (double) totalWordCount();
        }
        else
        {
            return 0;
        }
    }

    //writes word count file from data in current map
    public boolean writeWordCount(String filename)
    {
        try
        {
            FileWriter fw = new FileWriter(filename);
            fw.write("totalWordCount: " + totalWordCount + "\n");
            ArrayList<MapSet.KeyValuePair<String, Integer>> arr = map.entrySet();

            for (int i = 0; i < arr.size(); i++)
            {
                fw.write(arr.get(i).getKey() + " " + arr.get(i).getValue() + "\n");
            }

            fw.close();
            return true;
        }

        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    //clears map then adds words from word count file to it
    public boolean readWordCount(String filename)
    {
        try
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] words = line.split(" ");
            totalWordCount = Integer.parseInt(words[1]);
            line = br.readLine();
            
            while(line != null)
            {
                String[] words2 = line.split(" ");
                map.put(words2[0], Integer.parseInt(words2[1]));
                line = br.readLine();
            }

            br.close();
            return true;
        }

        catch(FileNotFoundException ex)
        {
            System.out.println("WordCounter.read():: unable to open file " + filename );
            return false;
        }

        catch(IOException ex)
        {
            System.out.println("WordCounter.read():: error reading file " + filename);
            return false;
        }
    }

    //returns depth of BST for BSTMap, 0 for HashMap
    public int getDepth()
    {
        return map.getDepth();
    }

    //returns number of collisions for HashMap, 0 for BSTMap
    public int getCollisions()
    {
        return map.getCollisions();
    }

    //returns string representation of map
    public String toString()
    {
        return map.toString();
    }

    public static void main(String[] args)
    {
        // //tests
        // //WordCounter wc = new WordCounter("bst");
        // WordCounter wc = new WordCounter("hashmap");
        
        // //testing readWords
        // ArrayList<String> arr = wc.readWords("counttest.txt");
        // System.out.println(arr);

        // //testing buildMap
        // wc.buildMap(arr);
        // System.out.println(wc.toString());

        // //testing clearMap
        // wc.clearMap();
        // System.out.println(wc.toString());

        // //testing totalWordCount
        // wc.buildMap(arr);
        // System.out.println(wc.totalWordCount() + " = 24");

        // //testing uniqueWordCount
        // System.out.println(wc.uniqueWordCount() + " = 10");

        // //testing getCount
        // System.out.println(wc.getCount("it") + " = 4");
        // System.out.println(wc.getCount("best") + " = 1");

        // //testing getFrequency
        // System.out.println(wc.getFrequency("it") + " = .167");
        // System.out.println(wc.getFrequency("best") + " = .042");

        // //testing writeWordCount
        // wc.writeWordCount("wcf_counttest.txt");

        // //testing readWordCount
        // wc.readWordCount("wcf_counttest.txt");
        // System.out.println(wc.toString());


        //WordCounter wc = new WordCounter("bst");
        WordCounter wc = new WordCounter("hashmap");
        double[] arr1 = new double[8];
        
        for (int i = 0; i < 8; i++)
        {
            int[] arr2 = new int[5];

            for (int j = 0; j < 5; j++)
            {
                wc.clearMap();
                int before = (int) System.currentTimeMillis();

                if (i < 2)
                {
                    ArrayList<String> words = wc.readWords("reddit_comments_200" + (i + 8) + ".txt");
                    wc.buildMap(words);
                }
                else
                {
                    ArrayList<String> words = wc.readWords("reddit_comments_20" + (i + 8) + ".txt");
                    wc.buildMap(words);
                }

                int after = (int) System.currentTimeMillis();
                arr2[j] = after - before;
            }

            int minIndex = 0;
            int minValue = arr2[0];
            int maxIndex = 0;
            int maxValue = arr2[0];

            for (int j = 1; j < 5; j++)
            {
                if (arr2[j] < minValue)
                {
                    minIndex = j;
                    minValue = arr2[j];
                }

                if (arr2[j] > maxValue)
                {
                    minIndex = j;
                    minValue = arr2[j];
                }
            }

            double avg = 0;

            for (int j = 0; j < 5; j++)
            {
                if (j != minIndex || j != maxIndex)
                {
                    avg += arr2[j];
                }
            }

            avg /= 3;
            arr1[i] = avg;
            System.out.println("File " + (i + 1));
            //System.out.println("Total Word Count: " + wc.totalWordCount());
            //System.out.println("Unique Word Count: " + wc.uniqueWordCount());
            //System.out.println("BST Depth: " + wc.getDepth());
            //System.out.println("HashMap Collisions: " + wc.getCollisions());
        }

        for (int i = 0; i < 8; i++)
        {
            System.out.println(arr1[i]);
        }
    }
}