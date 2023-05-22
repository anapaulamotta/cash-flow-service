package com.example.cashflow.service;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ReportService {


    public void getReport(List<String[]> credit, List<String[]> debit, String[] total) throws IOException {

        String[] creditHeader = {"","Créditos",""};
        String[] debitHeader = {"","Débitos",""};
        String[] header = {"Id", "Descrição", "Valor", "Data e hora"};

        String[] line = {};

        Writer writer = Files.newBufferedWriter(Paths.get("report.csv"));
        CSVWriter csvWriter = new CSVWriter(writer);

        csvWriter.writeNext(creditHeader);
        csvWriter.writeNext(header);
        csvWriter.writeAll(credit);
        csvWriter.writeNext(line);
        csvWriter.writeNext(debitHeader);
        csvWriter.writeNext(header);
        csvWriter.writeAll(debit);
        csvWriter.writeNext(line);
        csvWriter.writeNext(total);

        csvWriter.flush();
        writer.close();

    }
}

