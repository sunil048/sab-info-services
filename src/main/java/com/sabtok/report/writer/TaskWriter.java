package com.sabtok.report.writer;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sabtok.persistance.mongo.MangoDAO;

@Component
public class TaskWriter    {

	private org.bson.Document taskJobDocument;
	
	
	//https://www.codejava.net/frameworks/spring-boot/pdf-export-example
	private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("REPORT_ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("REPORT_NAME", font));
        table.addCell(cell);
         
		/*
		 * cell.setPhrase(new Phrase("Full Name", font)); table.addCell(cell);
		 * 
		 * cell.setPhrase(new Phrase("Roles", font)); table.addCell(cell);
		 * 
		 * cell.setPhrase(new Phrase("Enabled", font)); table.addCell(cell);
		 */      
    }
	
	private void writeTableTasklist(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(Color.WHITE);
        
        cell.setPhrase(new Phrase("SL NO", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("TASK_ID", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("NAME", font));
        table.addCell(cell);
		 cell.setPhrase(new Phrase("PROJECT", font)); 
		  table.addCell(cell);
		  
		  cell.setPhrase(new Phrase("OPEN DATE", font)); 
		  table.addCell(cell);
		  cell.setPhrase(new Phrase("TYPE", font)); 
		  table.addCell(cell);
		  cell.setPhrase(new Phrase("STATUS", font)); 
		  table.addCell(cell);
		       
    }
            
	private void writeTableData(PdfPTable table) {
            table.addCell(taskJobDocument.getString("REPORT_ID"));
            table.addCell(taskJobDocument.getString("REPORT_NAME"));
    }
	
	private void writeTableTasklistData(PdfPTable table) {
		List <org.bson.Document> taskList = (List<org.bson.Document>) taskJobDocument.get("TASK_DATA");
		int count = 1;
		for (org.bson.Document taskDoc: taskList) {
			 table.addCell(String.valueOf(count++));
			 table.addCell(taskDoc.getString("taskid"));
		     table.addCell(taskDoc.getString("name"));
		     table.addCell(taskDoc.getString("projectName"));
		     table.addCell(taskDoc.getString("openDate"));
		     table.addCell(taskDoc.getString("taskType"));
		     table.addCell(taskDoc.getString("status"));
		     
		}
       
}
	public void export(HttpServletResponse response,org.bson.Document doc) throws DocumentException, IOException {
		this.taskJobDocument =doc;
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("TASK REPORT", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        //table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setWidths(new float[] {1.5f, 3.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
       
        Paragraph p1 = new Paragraph("TASK LIST", font);
        p1.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p1);
        PdfPTable taskListTable = new PdfPTable(7);
        taskListTable.setWidthPercentage(100f);
        taskListTable.setWidths(new float[] {1.5f,3.5f, 3.5f, 3.0f, 3.0f, 3.5f,2.5f});
        taskListTable.setSpacingBefore(10);
        
        writeTableTasklist(taskListTable);
        writeTableTasklistData(taskListTable);
        document.add(taskListTable);
        document.close();
         
    }


}
