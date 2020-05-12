package com.company;

import com.company.exception.NameScoreException;

import java.io.File;

public class NameScoreUtility {
    public static void main(String[] args){
        String usage="Usage: com.company.NameScoreService inputNameFile";
        if (args.length!=1){
            System.err.println("Missing inputNameFile");
            System.err.println(usage);
            System.exit(1);
        }
        File inputNameFile=new File(args[0]);
        if (!inputNameFile.exists() || !inputNameFile.isFile()){
            System.err.println("Invalid inputNameFile argument");
            System.err.println(usage);
            System.exit(1);
        }
        NameScoreService ns=new NameScoreServiceImpl(inputNameFile);
        try {
            System.out.println("Score = "+ns.calculateTotalScore());
        } catch (NameScoreException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
