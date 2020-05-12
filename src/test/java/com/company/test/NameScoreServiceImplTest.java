package com.company.test;

import com.company.NameScoreService;
import com.company.NameScoreServiceImpl;
import com.company.exception.NameScoreException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class NameScoreServiceImplTest {
    private NameScoreService getNameService(String fileContents){
        return new NameScoreServiceImpl(new File("")){
            @Override
            public String getFirstLineContents() throws NameScoreException {
                return fileContents;
            }
        };
    }
    @Test
    public void test_with_some_values(){
        NameScoreService nameScoreService=getNameService("\"MARY\",\"PATRICIA\",\"LINDA\",\"BARBARA\",\"VINCENZO\",\"SHON\",\"LYNWOOD\",\"JERE\",\"HAI\"");
        try {
            Assertions.assertEquals(3194,nameScoreService.calculateTotalScore());
        }catch(NameScoreException e){
            Assertions.fail("test_with_some_values failed",e);
        }
    }
    @Test
    public void test_with_some_space_between_values(){
        NameScoreService nameScoreService=getNameService("\"MARY\" , \"PATRICIA\", \"LINDA\" ,\"BARBARA\" , \"VINCENZO\",\"SHON\",\"LYNWOOD\",\"JERE\",\"HAI\"");
        try {
            Assertions.assertEquals(3194,nameScoreService.calculateTotalScore());
        }catch(NameScoreException e){
            Assertions.fail("test_with_some_space_between_values failed",e);
        }
    }
    @Test
    public void test_with_no_value(){
        NameScoreService nameScoreService=getNameService("");
        try {
            Assertions.assertEquals(0,nameScoreService.calculateTotalScore());
        }catch(NameScoreException e){
            Assertions.fail("test_with_no_value failed",e);
        }
    }
    @Test
    public void test_with_non_alphabetic_values(){
        NameScoreService nameScoreService=getNameService("\"123\"");
        try {
            Assertions.assertEquals(0,nameScoreService.calculateTotalScore());
        }catch(NameScoreException e){
            Assertions.fail("test_with_non_alphabetic_values",e);
        }
    }
}
