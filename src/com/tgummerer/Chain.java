/*
 * Performance analysis
 * 
 * Sample class for performance check taken from http://blog.dhananjaynene.com/2008/07/performance-comparison-c-java-python-ruby-jython-jruby-groovy/
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

public class Chain
{
    private Person first = null;

    public Chain(int size)
    {
        Person last = null;
        Person current = null;
        for (int i = 0 ; i < size ; i++)
        {
            current = new Person(i);
            if (first == null) first = current;
            if (last != null)
            {
                last.setNext(current);
                current.setPrev(last);
            }
            last = current;
        }
        first.setPrev(last);
        last.setNext(first);
    }

    public Person kill(int nth)
    {
        Person current = first;
        int shout = 1;
        while(current.getNext() != current)
        {
            shout = current.shout(shout, nth);
            current = current.getNext();
        }
        first = current;
        return current;
    }

    public Person getFirst()
    {
        return first;
    }
    public static void main(String[] args)
    {
        int ITER = 100000;
        long start = System.nanoTime();
        for (int i = 0 ; i < ITER ; i++)
        {
            Chain chain = new Chain(40);
            chain.kill(3);
        }
        long end = System.nanoTime();
        System.out.println("Time per iteration = " + ((end - start) / (ITER )) + " nanoseconds.");
    }
}
