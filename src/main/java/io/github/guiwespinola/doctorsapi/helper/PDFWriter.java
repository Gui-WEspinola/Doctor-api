package io.github.guiwespinola.doctorsapi.helper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.github.guiwespinola.doctorsapi.entity.Doctor;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

import static com.itextpdf.text.BaseColor.BLACK;
import static com.itextpdf.text.BaseColor.LIGHT_GRAY;
import static com.itextpdf.text.FontFactory.getFont;

@Component
public class PDFWriter {

    public void createLocalPdf(List<Doctor> doctorList, String saveLocation) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(saveLocation));

            document.open();

            document.addTitle("Tabela de Plantões");
            addTitle("Tabela de Plantões", document);

            PdfPTable table = new PdfPTable(doctorList.get(0).getClass().getDeclaredFields().length - 1);

            addTableHeader(table);
            addRows(table, doctorList);

            document.add(table);
            document.close();
        } catch (FileNotFoundException | DocumentException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }

    }

    private static void addTableHeader(PdfPTable table) {
        table.setPaddingTop(10);
        Stream.of("Médico(a)", "Plantões", "Triagem", "Visitas", "Cirurgias").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(LIGHT_GRAY);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
    }

    private static void addRows(PdfPTable table, List<Doctor> doctorList) {
        doctorList.forEach(doctor -> {
            table.addCell(doctor.getNome());
            table.addCell(doctor.getPlantao());
            table.addCell(doctor.getTriagem());
            table.addCell(doctor.getVisita());
            table.addCell(doctor.getCirurgia());
        });
    }

    private static void addTitle(String title, Document document) throws DocumentException {
        Font font = getFont(FontFactory.TIMES_ROMAN, 16, BLACK);
        Chunk chunk = new Chunk(title, font);

        chunk.setLineHeight(10);

        document.add(new Phrase(""));
        document.add(chunk);
    }
}
