/*
 * Performance analysis
 * 
 * Sample class for performance check taken from http://blog.dhananjaynene.com/2008/07/performance-comparison-c-java-python-ruby-jython-jruby-groovy/
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

public class Person
{
    int count;
    private Person prev = null;
    private Person next = null;

    public Person(int count)
    {
        this.count = count;
    }

    public int shout(int shout, int deadif)
    {
        if (shout < deadif) return (shout + 1);
        this.getPrev().setNext(this.getNext());
        this.getNext().setPrev(this.getPrev());
        return 1;
    }

    public int getCount()
    {
        return this.count;
    }

    public Person getPrev()
    {
        return prev;
    }

    public void setPrev(Person prev)
    {
        this.prev = prev;
    }

    public Person getNext()
    {
        return next;
    }

    public void setNext(Person next)

    {
        this.next = next;
    }
}
