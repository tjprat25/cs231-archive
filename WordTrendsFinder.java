/**
* Author: Timothy Pratt
* Date: 11/22/2022
* File: WordTrendsFinder.java
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

public class WordTrendsFinder
{

    WordCounter wc;

    //constructor
    public WordTrendsFinder()
    {
        wc = new WordCounter("hashmap");
    }

    //reads word count file and offers the words to hashmap
    public void readWordCount(String filename)
    {
        wc.readWordCount(filename);
    }

    //returns the frequency of a given word from 
    public double frequency(String word)
    {
        return wc.getFrequency(word);
    }

    //clears hashmap
    public void clear()
    {
        wc.clearMap();
    }

    public static void main(String[] args)
    {
        if (args.length < 4)
        {
            System.out.println("USAGE java WordTrendsFinder <BaseFilename> <FileNumberBegin> <FileNumberEnd> <Word1> <Word2> ...");
            System.out.println("where <BaseFilename> contains the text part of the name of each WordCount file to analyze.");
            System.out.println("and <FileNumberBegin> specifies the first file's number suffix");
            System.out.println("and <FileNumberEnd> specifies the last number suffix in the range of word files to analyze.");
            System.out.println("<Word1> <Word2> ... is the list of words to analyze.");
        }
        else
        {
            WordTrendsFinder wt = new WordTrendsFinder();
            String base = args[0];
            int begin = Integer.parseInt(args[1]);
            int end = Integer.parseInt(args[2]);

            while (begin <= end)
            {
                if (begin < 10)
                {
                    wt.readWordCount("" + base + "0" + begin + ".txt");
                    System.out.println("" + base + "0" + begin + ".txt");
                }
                else
                {
                    wt.readWordCount("" + base + begin + ".txt");
                    System.out.println("" + base + begin + ".txt");
                }

                for (int i = 3; i < args.length; i++)
                {
                    System.out.println(wt.frequency(args[i]));
                }

                System.out.println("");
                wt.clear();
                begin++;
            }
        }
    }
}