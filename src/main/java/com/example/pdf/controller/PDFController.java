package com.example.pdf.controller;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PDFController {

    private static final Log LOG = LogFactory.getLog(PDFController.class);

    @GetMapping("/V0/statements/{statement-id}")
    public void generatePDF(@PathVariable("statement-id") String statementId, HttpServletResponse response) {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currDate = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + "_" + statementId + "_" + currDate + "" +".pdf";

        response.setHeader(headerKey, headerValue);

        try {
            export(response);
        } catch (IOException io){}

    }

    @GetMapping("/V0/statements")
    public ResponseEntity getStatements(@RequestParam(required = false) String contract){

        LOG.info("El valor de contract es: " + contract);

        Client client = new Client();
        client.setId("52b81ce7b8e289e04e76efc45e9");
        client.setContractId(contract != null ? contract : "001304769600084868");
        client.setDocumentId("0131158480039262");
        client.setDate(new ClientDate());
        client.getDate().setYear("21");
        client.getDate().setMonth("01");

        Client client1 = new Client();
        client1.setId("52b81ce7b8e289e04e76efc45e8");
        client1.setContractId(contract != null ? contract : "001304769600097654");
        client1.setDocumentId("0131158480039262");
        client1.setDate(new ClientDate());
        client1.getDate().setYear("21");
        client1.getDate().setMonth("02");

        List<Client> list = new ArrayList<>();
        list.add(client);
        list.add(client1);

        return ResponseEntity.ok().body(list);
    }


    public void export(HttpServletResponse httpServletResponse) throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, httpServletResponse.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        Paragraph paragraph = new Paragraph("Thi is the test text", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontP = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(12);
        Paragraph paragraphText = new Paragraph("Thi is the test text", fontP);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraphText);
        document.close();

    }



}
