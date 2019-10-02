package com.sabinfo.sabtok.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Header;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import com.sabinfo.sabtok.wiki.model.Page;
import com.lowagie.*;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.html.simpleparser.HTMLWorker;

public class PagePDFTemplate extends AbstractPdfView{

	private static Logger logger = Logger.getLogger(ReportController.class);
	
	@Override
	protected void buildPdfDocument(Map model, Document document, PdfWriter arg2, HttpServletRequest arg3,
			HttpServletResponse arg4) throws Exception {
		Map<String,Page> data= (Map<String,Page>) model.get("Page");
		 try{
			    
			    Page page = data.get("Page");
			    logger.info("Generating pdf report for page"+page.getPageId());
				document.open();
				Header tittle = new Header("Page Id : ", page.getPageId());
				Header Description = new Header("Tittle: ", page.getTitle());
				document.add(tittle);
				document.add(Description);
				HTMLWorker htmlWorker = new HTMLWorker(document);
				htmlWorker.parse(new StringReader(page.getContent()));
				
		 }catch (Exception e) {
			// TODO: handle exception
		}
		
	    
/*	    OutputStream file = new FileOutputStream(new File("C:\\plm\\Test.pdf"));
	   
	    PdfWriter.getInstance(document, file);
	    document.open();
	    Header tittle = new Header("Page Id : ", page.getPageId());
		Header Description = new Header("Tittle: ", page.getTitle());
		document.add(tittle);
		document.add(Description);
	    HTMLWorker htmlWorker = new HTMLWorker(document);
	    htmlWorker.parse(new StringReader(page.getContent()));
	    document.close();
	    file.close();
	    
	    
		document.close();
		   HtmlWriter.getInstance(document, new FileOutputStream("C:\\plm\\rint_kk.html"));*/
		
	}

} 
