package com.sabtok.report.template;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Header;
import com.lowagie.text.Phrase;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sabtok.entity.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneratePdfReport {

	public static ByteArrayInputStream generatePDFForPage(Page page) throws IOException {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			Header tittle = new Header("Page Id : ", page.getPageId());
			Header Description = new Header("Tittle: ", page.getTitle());
			document.add(tittle);
			document.add(Description);
			HTMLWorker htmlWorker = new HTMLWorker(document);
			htmlWorker.parse(new StringReader(page.getContent()));
			document.close();

		} catch (DocumentException ex) {
			log.error("Error occurred: {0}", ex);
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	public static ByteArrayInputStream pageReport(Page page) throws IOException {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(60);
			table.setWidths(new int[] { 1, 3, 3 });
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Id", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Name", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Population", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			// for (City city : cities) {

			PdfPCell cell;

			cell = new PdfPCell(new Phrase(page.getPageId().toString()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(page.getTitle()));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(page.getContent())));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(5);
			table.addCell(cell);
			// }

			PdfWriter.getInstance(document, out);
			// document.open();
			// document.add(table);
			document.open();
			Header tittle = new Header("Page Id : ", page.getPageId());
			Header Description = new Header("Tittle: ", page.getTitle());
			document.add(tittle);
			document.add(Description);
			HTMLWorker htmlWorker = new HTMLWorker(document);
			htmlWorker.parse(new StringReader(page.getContent()));

			document.close();

		} catch (DocumentException ex) {

			log.error("Error occurred: {0}", ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}
