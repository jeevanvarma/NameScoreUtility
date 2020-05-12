package com.company;

import com.company.exception.NameScoreException;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/*
Service implementation class to parse csv file and calculate the name score
 */
public class NameScoreServiceImpl implements NameScoreService {
    private File inputNameFile;
    public NameScoreServiceImpl(File inputNameFile){
        this.inputNameFile=inputNameFile;
    }

    /*
    Method to parse csv file and read the first line. This method is separated out for easier test method mocking
     */
    public String getFirstLineContents() throws NameScoreException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputNameFile));
            return br.readLine();
        }catch(IOException e){
            throw new NameScoreException("Unable to read input file",e);
        }
    }

    /*
        Prepare a list of names from first line contents
        @return List<Name>
         */
    @Override
    public List<Name> prepareNameList() throws NameScoreException {
        return Arrays.stream(getFirstLineContents().split(","))
                .parallel()
                .map(NameImpl::new)
                .collect(Collectors.toList());
    }

    /*
    Calculates the csv file score
    @return score value
     */
    @Override
    public long calculateTotalScore() throws NameScoreException {
        AtomicLong counter=new AtomicLong(1);
        return prepareNameList().stream()
                .sorted()
                .mapToLong(name -> name.getScore() * counter.getAndIncrement())
                .sum();
    }
}
