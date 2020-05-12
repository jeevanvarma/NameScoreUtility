package com.company;

import java.nio.CharBuffer;

public class NameImpl implements Name{
    private String firstName;

    public NameImpl(String firstName){
        this.firstName=firstName;
    }
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getName(){
        return getFirstName();
    }

    @Override
    public long getScore(){
        return CharBuffer.wrap(getName().toUpperCase().toCharArray())
                .chars()
                .parallel()
                .filter(x -> (x>='A' && x<='Z'))
                .mapToLong( x -> x - 'A' + 1)
                .sum();
    }

    @Override
    public int compareTo(Name o) {
        return this.getName().compareTo(o.getName());
    }
}
