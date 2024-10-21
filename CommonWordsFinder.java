/**
* Author: Timothy Pratt
* Date: 11/22/2022
* File: CommonWordsFinder.java
* Section A
* Project 8
* CS231
*/

import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.Comparator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CommonWordsFinder
{

    PQHeap<MapSet.KeyValuePair<String, Integer>> heap;
    ArrayList<MapSet.KeyValuePair<String, Integer>> arr;

    //constructor
    public CommonWordsFinder()
    {
        heap = new PQHeap<>(new KeyValuePairComparatorByValue<String, Integer>());
        arr = new ArrayList<MapSet.KeyValuePair<String, Integer>>();
    }

    //reads word count file and offers the words to PQHeap
    public boolean readWordCount(String filename)
    {
        try
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] words = line.split(" ");
            line = br.readLine();
            
            while(line != null)
            {
                String[] words2 = line.split(" ");
                MapSet.KeyValuePair<String, Integer> kvp = new MapSet.KeyValuePair<String,Integer>(words2[0], Integer.parseInt(words2[1]));
                heap.offer(kvp);
                line = br.readLine();
            }

            br.close();
            return true;
        }

        catch(FileNotFoundException ex)
        {
            System.out.println("CommonWordsFinder.read():: unable to open file " + filename);
            return false;
        }

        catch(IOException ex)
        {
            System.out.println("CommonWordsFinder.read():: error reading file " + filename);
            return false;
        }
    }

    //reads word count file and offers the words to Array List
    public boolean readWordCountToArr(String filename)
    {
        try
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] words = line.split(" ");
            line = br.readLine();
            
            while(line != null)
            {
                String[] words2 = line.split(" ");
                MapSet.KeyValuePair<String, Integer> kvp = new MapSet.KeyValuePair<String,Integer>(words2[0], Integer.parseInt(words2[1]));
                arr.add(kvp);
                line = br.readLine();
            }

            br.close();
            arr = mergeSort(arr);
            return true;
        }

        catch(FileNotFoundException ex)
        {
            System.out.println("CommonWordsFinder.read():: unable to open file " + filename);
            return false;
        }

        catch(IOException ex)
        {
            System.out.println("CommonWordsFinder.read():: error reading file " + filename);
            return false;
        }
    }

    //merge sort function
    public static ArrayList<MapSet.KeyValuePair<String, Integer>> mergeSort(ArrayList<MapSet.KeyValuePair<String, Integer>> arr)
    {
        if (arr.size() == 1)
        {
            return arr;
        }
        else if (arr.size() == 2)
        {
            MapSet.KeyValuePair<String, Integer> val1 = arr.get(0);
            MapSet.KeyValuePair<String, Integer> val2 = arr.get(1);
            ArrayList<MapSet.KeyValuePair<String, Integer>> output = new ArrayList<MapSet.KeyValuePair<String, Integer>>();
            if (val1.getValue() < val2.getValue())
            {
                output.add(val1);
                output.add(val2);
            }
            else
            {
                output.add(val2);
                output.add(val1);
            }
            return output;
        }

        ArrayList<MapSet.KeyValuePair<String, Integer>> leftHalf = new ArrayList<MapSet.KeyValuePair<String, Integer>>();
        
        for(int i = 0; i < arr.size()/2; i++)
        {
            leftHalf.add(arr.get(i));
        }

        ArrayList<MapSet.KeyValuePair<String, Integer>> rightHalf = new ArrayList<MapSet.KeyValuePair<String, Integer>>();

        for(int i = arr.size()/2; i < arr.size(); i++)
        {
            rightHalf.add(arr.get(i));
        }

        ArrayList<MapSet.KeyValuePair<String, Integer>> leftSorted = mergeSort(leftHalf);
        ArrayList<MapSet.KeyValuePair<String, Integer>> rightSorted = mergeSort(rightHalf);
        ArrayList<MapSet.KeyValuePair<String, Integer>> merged = merge(leftSorted, rightSorted);
        return merged;
    }

    //merge function
    public static ArrayList<MapSet.KeyValuePair<String, Integer>> merge(ArrayList<MapSet.KeyValuePair<String, Integer>> left, ArrayList<MapSet.KeyValuePair<String, Integer>> right)
    {
        ArrayList<MapSet.KeyValuePair<String, Integer>> merged = new ArrayList<MapSet.KeyValuePair<String, Integer>>();
        int leftIndex = 0;
        int rightIndex = 0;

        while(leftIndex != left.size() && rightIndex != right.size())
        {
            if (left.get(leftIndex).getValue() < right.get(rightIndex).getValue())
            {
                merged.add(left.get(leftIndex));
                leftIndex++;
            }
            else
            {
                merged.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        
        if(leftIndex == left.size())
        {
            while(rightIndex < right.size())
            {
                merged.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        else
        {
            while(leftIndex < left.size())
            {
                merged.add(left.get(leftIndex));
                leftIndex++;
            }
        }
        
        return merged;
    }

    //returns largest item in PQHeap
    public String poll()
    {
        if (heap.size() == 0)
        {
            return null;
        }
        else
        {
            return (heap.poll().getKey());
        }
    }

    //returns largest (last) item in Array List
    public String pollFromArr()
    {
        if (arr.size() == 0)
        {
            return null;
        }
        else
        {
            String toReturn = arr.get(arr.size() - 1).getKey();
            arr.remove(arr.size() - 1);
            return (toReturn);
        }
    }

    //clears the PQHeap
    public void clear()
    {
        heap = new PQHeap<>(new KeyValuePairComparatorByValue<String, Integer>());
        arr = new ArrayList<MapSet.KeyValuePair<String, Integer>>();
    }

    //main: prints n most common words per word count file
    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            System.out.println("Usage: CommonWordsFinder <N> <WC file 1> <...>");
            System.out.println("Reports the N most common words in each provided Word Count file.");
        }
        else
        {
            double begin = System.currentTimeMillis();
            CommonWordsFinder cwf = new CommonWordsFinder();
                        
            for (int i = 1; i < args.length; i++)
            {
                cwf.readWordCount(args[i]);
                //cwf.readWordCountToArr(args[i]);
                int j = 0;
                System.out.println(args[i]);

                while (j < Integer.parseInt(args[0]))
                {
                    System.out.println("" + (j + 1) + ": " + cwf.poll());
                    //System.out.println("" + (j + 1) + ": " + cwf.pollFromArr());
                    j++;
                }

                cwf.clear();
                System.out.println("");
            }

            double end = System.currentTimeMillis();
            System.out.print(end - begin);
        }
    }
}