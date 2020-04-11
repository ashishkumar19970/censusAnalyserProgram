package com.bridgelabz.service.Test;

import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.exception.StateCensusAnalyserException;
import com.bridgelabz.model.CSVState;
import com.bridgelabz.model.CSVStateCensus;
import com.bridgelabz.service.StateCensus;
import com.bridgelabz.service.StateCensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class StateCensusAnalyserTest {
    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
    //CSV Census file path
    private final String SIMPLE_CSV_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCensusData.csv";
    private final String INCORRECT_CSV_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/tested/resources/IndiaStateCensusData.csv";
    private final String INCORRECT_CSV_TYPE_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCensusData.pdf";
    private final String INCORRECT_DELIMITER_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCensusData1.csv";
    private final String INCORRECT_HEADER_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCensusData2.csv";
    //CSV State code file path
    private final String CSV_STATE_CODE_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private final String INCORRECT_CSV_STATE_CODE_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/tested/resources/IndiaStateCode.csv";
    private final String INCORRECT_EXTENSION_CSV_STATE_CODE = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCode.pdf";
    private final String INCORRECT_IN_DELIMITER_CSV_STATE_CODE_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCode1.csv";
    private final String INCORRECT_IN_HEADER_CSV_STATE_CODE_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCode2.csv";

    @Test
    public void givenStateCensusCsvFile_WhenTrue_NumberOfRecordShouldMatch() throws CSVBuilderException {
        int totalRecords = stateCensusAnalyser.loadCensusCsvData(SIMPLE_CSV_PATH);
        Assert.assertEquals(29, totalRecords);
    }

    @Test
    public void givenStateCensusCsvFile_WhenFileNameIncorrect_ShouldThrowCustomException() {
        try {
            stateCensusAnalyser.loadCensusCsvData("src/testes/resources/StateCensusData.csv");
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.Exceptiontype.ENTERED_WRONG_FILE, e.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenTypeIncorrect_ShouldThrowCustomException() {
        try {
            File fileExtension = new File(INCORRECT_CSV_TYPE_PATH);
            stateCensusAnalyser.getFileExtension(fileExtension);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.Exceptiontype.ENTERED_WRONG_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenDelimiterIncorrect_ShouldThrowCustomException() {
        try {
            stateCensusAnalyser.loadCensusCsvData(INCORRECT_DELIMITER_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.Exceptiontype.ENTERED_INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenHeaderIncorrect_ShouldThrowCustomException() {
        try {
            stateCensusAnalyser.loadCensusCsvData(INCORRECT_HEADER_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.Exceptiontype.ENTERED_INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenTrue_NumberOfRecordShouldMatch() throws CSVBuilderException {
        int totalRecords = stateCensusAnalyser.loadSateCodeCsvData(CSV_STATE_CODE_PATH);
        Assert.assertEquals(37, totalRecords);
    }

    @Test
    public void givenStateCodeCsvFile_WhenFileNameIncorrect_ShouldThrowCustomException() {
        try {
            stateCensusAnalyser.loadSateCodeCsvData(INCORRECT_CSV_STATE_CODE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.Exceptiontype.ENTERED_WRONG_FILE, e.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenTypeIncorrect_ShouldThrowCustomException() {
        try {
            File fileExtension = new File(INCORRECT_EXTENSION_CSV_STATE_CODE);
            stateCensusAnalyser.getFileExtension(fileExtension);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.Exceptiontype.ENTERED_WRONG_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenStateCode_WhenDelimiterIncorrect_ShouldThrowCustomException() {
        try {
            stateCensusAnalyser.loadSateCodeCsvData(INCORRECT_IN_DELIMITER_CSV_STATE_CODE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.Exceptiontype.ENTERED_INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenHeaderIncorrect_ShouldThrowCustomException() {
        try {
            stateCensusAnalyser.loadSateCodeCsvData(INCORRECT_IN_HEADER_CSV_STATE_CODE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.Exceptiontype.ENTERED_INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenSortedOnState_ShouldReturnSortedList() {
        try {
            stateCensusAnalyser.loadSateCodeCsvData(SIMPLE_CSV_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].getState());
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenSortedOnState_ShouldReturnSortedList1() {
        try {
            stateCensusAnalyser.loadSateCodeCsvData(SIMPLE_CSV_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals("Madhya Pradesh", censusCSV[13].getState());
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenSortedImproperlyOnState_ShouldNotReturnSortedList() {
        try {
            stateCensusAnalyser.loadSateCodeCsvData(SIMPLE_CSV_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertNotEquals("Maharashta", censusCSV[0].getState());
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

}