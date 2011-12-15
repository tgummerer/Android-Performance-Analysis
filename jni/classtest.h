/*
 * Performance analysis
 * 
 * Sample class for performance check taken from http://blog.dhananjaynene.com/2008/07/performance-comparison-c-java-python-ruby-jython-jruby-groovy/
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */
#ifndef CLASSTEST_H
#define CLASSTEST_H

class Person
{  

    public:  

        Person(int count);
        int shout(int shout, int nth);
        int count();
        Person* next();
        void next(Person* person);
        Person* prev();
        void prev(Person* person);
    private:  
        int _count;  
        Person* _next;  
        Person* _prev;  
};  

class Chain  
{  
    public:  
        Chain(int size); 
        Person* kill(int nth);
        Person* first();
    private:  
        Person* _first;  
}; 

#endif
