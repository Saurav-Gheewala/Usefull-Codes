package com.example.Certificate.service;

import jakarta.mail.internet.MimeMessage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

@org.springframework.stereotype.Service
public class Service
{

    @Autowired
    JavaMailSender javaMailSender;
    public ResponseEntity<String> process(MultipartFile file) {
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader))
        {
            for (CSVRecord csvRecord : csvParser)
            {
                String name = csvRecord.get("name");
                String email = csvRecord.get("email");
                byte[] certificate = genCertificate(name);
                try {
                    sendCertificate(name, email, certificate);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
        }
    }
    public byte[] genCertificate(String name) throws Exception
    {
        float x = 300;
        float y = 220;
        Path pdfPath = Paths.get("D:\\Spring_Boot\\Certificate\\src\\main\\resources\\Inspairo_Certifiate.pdf");
        try (PDDocument document = PDDocument.load(pdfPath.toFile()))
        {
            PDPage page = document.getPage(0); // Access the first (and only) page
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true))
            {
                contentStream.setFont(PDType1Font.TIMES_ITALIC, 25);
                contentStream.lineTo( x - (name.length()*5) ,y);
                contentStream.beginText();
                contentStream.newLineAtOffset(x - (name.length()*5), y);
                contentStream.showText(name);
                contentStream.endText();
            }

            // Convert the modified document to a byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
    public void sendCertificate(String name, String email, byte[] certificate) throws Exception
    {
        int i = 1;
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);
        mailMessage.setTo(email);
        mailMessage.setSubject("Your Certificate");
        mailMessage.setText("Your Certificate for participant");
        mailMessage.addAttachment("Certificate.pdf", new ByteArrayResource(certificate));
        javaMailSender.send(message);
        System.out.println( name + ": Sent");
    }
}
