/*
 * Performance analysis
 * 
 * Sample class for performance check taken from http://blog.dhananjaynene.com/2008/07/performance-comparison-c-java-python-ruby-jython-jruby-groovy/
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#include <stdlib.h>
#include "classtest.h"

Person::Person(int count) : _next(NULL), _prev(NULL)
{
    _count = count;
}

int Person::shout(int shout, int nth)
{
    if (shout < nth) return (shout + 1);  
    _prev->_next = _next;  

    _next->_prev = _prev;  
    return 1;  
}

int Person::count()
{
    return _count;
}

Person* Person::next() 
{
    return _next;
}

void Person::next(Person* person)
{
    this->_next = person;
}

Person* Person::prev()
{
    return _prev;
}

void Person::prev(Person* person)
{
    this->_prev = person;
}

Chain::Chain(int size) : _first(NULL)
{
    Person* current = NULL;  
    Person* last = NULL;  
    for(int i = 0 ; i < size ; i++)  
    {  
        current = new Person(i);  
        if (_first == NULL) _first = current;  
        if (last != NULL)  
        {  
            last->next(current);  
            current->prev(last);  
        }  
        last = current;  
    }  
    _first->prev(last);  
    last->next(_first);  
}

Person* Chain::kill(int nth)
{
    Person* current = _first;  
    int shout = 1;  
    while(current->next() != current)  

    {  
        Person* tmp = current;  
        shout = current->shout(shout,nth);  
        current = current->next();  
        if (shout == 1)  
        {  
            delete tmp;  
        }  
    }  
    _first = current;  
    return current;  
}

Person* Chain::first()
{
    return _first;
}
