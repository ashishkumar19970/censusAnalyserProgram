package com.bridgelabz.service.Test;

import com.bridgelabz.exception.StateCensusAnalyserException;
import com.bridgelabz.service.StateCensus;
import com.bridgelabz.service.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class StateCensusAnalyserTest {
    String STATE_CSV_FILE_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCensusData.csv";
    String STATE_CSV_WRONG_FILE_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/tested/resources/IndiaStateCensusData.csv";
    String STATE_CSV_WRONG_FILE_TYPE_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCensusData.txt";
    String STATE_CSVFILE_DELIMITERINCORRECT_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCensusData1.csv";
    String STATE_CSVHEADER_INCORRECT_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCensusData2.csv";
    //CSV State code file path
    private final String CSV_STATE_CODE_PATH = "/home/ashish/IdeaProjects/IndianCensusAnalyser/src/test/resources/IndiaStateCode.csv";
    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();

    @Test
    public void givenStateCensusCSVFile_WhenTrue_NumberOfRecordShouldMatch() throws StateCensusAnalyserException, IOException {
        int countRecord = stateCensusAnalyser.loadCensusCSVData(STATE_CSV_FILE_PATH);
        Assert.assertEquals(countRecord, 29);
    }

    @Test
    public void givenStateCensusCsvFile_WhenIncorrect_ShouldReturnCustomException() throws IOException {
        try {
            stateCensusAnalyser.loadCensusCSVData(STATE_CSV_WRONG_FILE_PATH);

        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.Exceptiontype.ENTERED_WRONG_FILE, e.type);
        }

    }

    @Test
    public void givenStateCensusCSVFile_WhenTypeIncorrect_ShouldReturnCustomException() {
        stateCensusAnalyser = new StateCensusAnalyser();
        try {
            StateCensusAnalyser.getFileExtension(new File(STATE_CSV_WRONG_FILE_TYPE_PATH));
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.Exceptiontype.ENTER_WRONG_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenIncorrectDelimiter_ShouldReturnCustomException() throws IOException {
        try {
            stateCensusAnalyser.loadCensusCSVData(STATE_CSVFILE_DELIMITERINCORRECT_PATH);

        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.Exceptiontype.ENTERED_INCORRECT_DELIMITER_OR_HEADER, e.type);
        }

    }
    @Test
    public void givenStateCensusCsvFile_WhenIncorrectHeader_ShouldReturnCustomException() throws IOException {
        try {
            stateCensusAnalyser.loadCensusCSVData(STATE_CSVHEADER_INCORRECT_PATH);

        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.Exceptiontype.ENTERED_INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }
    @Test
    public void givenStateCodeCsvFile_WhenTrue_NumberOfRecordShouldMatch() throws IOException, StateCensusAnalyserException {
        StateCensus stateCensus = new StateCensus();
        int totalRecords = stateCensus.loadCensusCodeCSVData(CSV_STATE_CODE_PATH);
        Assert.assertEquals(37, totalRecords);
    }
}