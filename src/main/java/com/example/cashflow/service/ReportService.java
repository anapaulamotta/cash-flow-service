package com.example.cashflow.service;

import com.example.cashflow.dto.response.TransactionReportDTO;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ReportService {

    @Autowired
    TransactionService service;

    public void getReport(List<String[]> credit, List<String[]> debit, String[] total) throws IOException {

        String[] creditHeader = {"","Créditos",""};
        String[] debitHeader = {"","Débitos",""};
        String[] header = {"id", "descrição", "valor", "data e hora"};

        Writer writer = Files.newBufferedWriter(Paths.get("report.csv"));
        CSVWriter csvWriter = new CSVWriter(writer);

        csvWriter.writeNext(creditHeader);
        csvWriter.writeNext(header);
        csvWriter.writeAll(credit);
        csvWriter.writeNext(creditHeader);
        csvWriter.writeNext(debitHeader);
        csvWriter.writeAll(debit);
        csvWriter.writeNext(total);


        csvWriter.flush();
        writer.close();

    }
}

