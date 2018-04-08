package com.views;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import static com.views.PDFView.pageNumberFont;
import static com.views.PDFView.titleFooterFont;

class TableHeader extends PdfPageEventHelper {
    /** The header text. */
    String header;
    /** The template with the total number of pages. */
    PdfTemplate total;

    /**
     * Allows us to change the content of the header.
     * @param header The new header String
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Creates the PdfTemplate that will hold the total number of pages.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }

    /**
     * Adds a header to every page
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(2);
        try {
            table.setWidths(new int[]{24, 24});
            table.setTotalWidth(527);
            table.setLockedWidth(true);
            table.getDefaultCell().setFixedHeight(20);
            table.getDefaultCell().setBorder(Rectangle.TOP);
            table.getDefaultCell().setBorderColor(BaseColor.PINK);
            table.addCell(header);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell cell = new PdfPCell();
            Phrase phrase = new Phrase();
            phrase.setFont(pageNumberFont);
            phrase.add(String.format("Страница %d", writer.getPageNumber()));
            cell.setPhrase(phrase);
            cell.setBorder(Rectangle.TOP);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorderColor(BaseColor.PINK);
            table.addCell(cell);
            table.writeSelectedRows(0, -1, 34, 30, writer.getDirectContent());
        }
        catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    /**
     * Fills out the total number of pages before the document is closed.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                2, 2, 0);
    }
}
