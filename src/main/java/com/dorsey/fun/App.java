package com.dorsey.fun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Sets;

/**
 * Word Ladders testing
 *
 */

public class App
{
    public Pair<Integer, List<String>> ladderLength(String start, String end, HashSet<String> dict)
    {
        if (dict.size() == 0)
            return Pair.of(0, Collections.emptyList());

        dict.add(end);

        Deque<String> wordQueue = new LinkedList<>();
        Deque<Integer> distanceQueue = new LinkedList<>();
        List<String> wordRoute = new ArrayList<>();

        wordQueue.add(start);
        distanceQueue.add(1);
        wordRoute.add(start);

        //track the shortest path
        int result = Integer.MAX_VALUE;
        while (!wordQueue.isEmpty()) {
            boolean addedWords = false;
            String currWord = wordQueue.pop();
            Integer currDistance = distanceQueue.pop();

            if (currWord.equals(end)) {
                result = Math.min(result, currDistance);
            }

            for (int i = 0; i < currWord.length(); i++) {
                char[] currCharArr = currWord.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    currCharArr[i] = c;

                    String newWord = new String(currCharArr);
                    if (dict.contains(newWord)) {
                        System.out.println("Went from currWord: "+currWord+" to newWord: "+newWord);
                        wordQueue.add(newWord);
                        distanceQueue.add(currDistance + 1);
                        dict.remove(newWord);
                        wordRoute.add(newWord);
                        addedWords = true;
                    }
                }
            }
            if (!addedWords)
            {
                wordRoute.remove(currWord);
                System.out.println("Should remove currWord: "+currWord);
            }
        }

        if (result < Integer.MAX_VALUE)
        {
            wordRoute.add(end);
            return Pair.of(result, wordRoute);
        }
        else
            return Pair.of(0, Collections.emptyList());
    }

    public static void main( String[] args )
    {
        App newApp = new App();
        HashSet<String> wordDictionary = Sets.newHashSet("hot","dot","dog","lot","log");
        Pair<Integer, List<String>> wordDistance = newApp.ladderLength("hit", "cog", wordDictionary);
        System.out.println( "Hello World!");
        System.out.println("The distance is "+wordDistance.getLeft());
        List<String> words = wordDistance.getRight();
        System.out.println("There are "+words.size()+" words.");
        words.forEach(System.out::println);
        System.out.println("The words are done");
    }
}
