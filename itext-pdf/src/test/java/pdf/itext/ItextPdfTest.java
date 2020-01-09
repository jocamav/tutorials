package pdf.itext;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

public class ItextPdfTest {
    @Test
    public void generateSimplePdf() throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter("target/hello world.pdf"));
        Document document = new Document(pdf);
        String line = "Hello! Welcome to iTextPdf";
        document.add(new Paragraph(line));
        document.close();
    }

    @Test
    public void generateListPdf() throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter("target/list example.pdf"));
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

        document.add(new Paragraph("iText is:").setFont(font));

        List list = new List()
                .setSymbolIndent(12)
                .setFont(font);

        list.add(new ListItem("Never gonna give you up"))
                .add(new ListItem("Never gonna let you down"))
                .add(new ListItem("Never gonna run around and desert you"))
                .add(new ListItem("Never gonna make you cry"))
                .add(new ListItem("Never gonna say goodbye"))
                .add(new ListItem("Never gonna tell a lie and hurt you"));

        document.add(list);
        document.close();
    }

    @Test
    public void generateTable() throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("target/table example.pdf"));
        Document doc = new Document(pdfDoc);

        Table table = new Table(UnitValue.createPercentArray(8)).useAllAvailableWidth();

        for (int i = 0; i < 16; i++) {
            table.addCell("hi");
        }

        doc.add(table);
        doc.close();
    }

    @Test
    public void tableWithBackGround() throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("target/table with background.pdf"));
        Document doc = new Document(pdfDoc);

        Table table;
        Cell cell;
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        table = new Table(UnitValue.createPercentArray(16)).useAllAvailableWidth();
        for (int aw = 0; aw < 16; aw++) {
            cell = new Cell().add(new Paragraph("hi").setFont(font).setFontColor(ColorConstants.WHITE));
            cell.setBackgroundColor(ColorConstants.BLUE);
            cell.setBorder(Border.NO_BORDER);
            cell.setTextAlignment(TextAlignment.CENTER);
            table.addCell(cell);
        }
        doc.add(table);

        doc.close();
    }

    @Test
    public void tableWithHeaderAndFormat() throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("target/table with header.pdf"));
        Document doc = new Document(pdfDoc);

        Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();

        addRowHeaderToTable(table);
        addRowsToTable(table, 5);
        doc.add(table);

        doc.close();
    }

    private void addRowHeaderToTable(Table table) throws IOException {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    Cell header = new Cell();
                    header.add(new Paragraph(columnTitle).setFont(font).setFontColor(ColorConstants.WHITE));
                    header.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                    header.setBorder(new SolidBorder(ColorConstants.BLUE, 1));
                    table.addCell(header);
                });
    }

    private void addRowsToTable(Table table, int numberOfRows) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < table.getNumberOfColumns(); j++) {
                table.addCell(String.format("Row [%d] Column [%d]", i + 1, j + 1));
            }
        }
    }
}
