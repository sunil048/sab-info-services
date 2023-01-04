package com.sabtok.report.template;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import com.itextpdf.xmp.impl.XMPSerializerHelper;
import com.sabtok.entity.Page;

//itext
public class GeneratePdfReport1 {
	public static ByteArrayInputStream generatePdfForPageByItext(Page page) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		 try {
	            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
	            com.itextpdf.text.pdf.PdfWriter.getInstance(document, out);
	            document.open();
	            com.itextpdf.text.html.simpleparser.HTMLWorker htmlWorker = new com.itextpdf.text.html.simpleparser.HTMLWorker(document);
	            htmlWorker.parse(new StringReader(page.getContent()));
	          //  XMPSerializerHelper worker = XMPSerializerHelper.getInstance();
	            document.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 
		 return new ByteArrayInputStream(out.toByteArray());
	}
}
