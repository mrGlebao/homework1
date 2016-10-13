package ru.sbt.bit.ood.hw1;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zabor on 13.10.2016.
 */
public class FileDownloader {
    public static String downloadTradesFileFromFTP() {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("localhost", 8090);
            ftpClient.login("foo", "password");
            File tempFile = File.createTempFile("trades", "download");
            OutputStream out = new FileOutputStream(tempFile);
            ftpClient.retrieveFile("public/prod/trades.csv", out);
            out.close();
            return tempFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not download the file");
        }
    }
}
