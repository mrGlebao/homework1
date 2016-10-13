package ru.sbt.bit.ood.hw1;

import org.apache.commons.csv.CSVRecord;

import java.io.*;

import static ru.sbt.bit.ood.hw1.CsvParser.parse;
import static ru.sbt.bit.ood.hw1.FileDownloader.downloadTradesFileFromFTP;

public class TradesJob {

    private final TradesDAO tradesDAO;

    public TradesJob(TradesDAO tradesDAO) {
        this.tradesDAO = tradesDAO;
    }

    public void run() {
        String filename = downloadTradesFileFromFTP();
        Iterable<CSVRecord> tradeRecords = parse(filename);
        updateTrades(tradeRecords);
    }

    private void updateTrades(Iterable<CSVRecord> trades) {
        tradesDAO.deleteAll();
        for (CSVRecord tradeRecord : trades) {
            Trade trade = new Trade(tradeRecord.toMap());
            tradesDAO.save(trade);
        }
    }


}
