package com.company;

import com.company.exception.NameScoreException;

import java.util.List;

public interface NameScoreService {
    List<Name> prepareNameList() throws NameScoreException;
    long calculateTotalScore() throws NameScoreException;
}
