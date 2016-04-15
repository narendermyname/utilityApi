/**
 * 
 */
package com.naren.pdf;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDWritter {

	/**
	 * 
	 */
	public PDWritter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//createPDF();
		//readPDF();
	}

	public static void readPDF()
	{
		PDDocument pd;
		 try {
		         //  PDF file from the phone numbers are extracted
		         File input = new File("D:\\invoice.pdf");

		         // StringBuilder to store the extracted text
		         StringBuilder sb = new StringBuilder();
		         pd = PDDocument.load(input);
		         PDFTextStripper stripper = new PDFTextStripper();

		         // Add text to the StringBuilder from the PDF
		         sb.append(stripper.getText(pd));

		         // Regex. For those who do not know. The Pattern refers to the format you are looking for.
		         // In our example, we are looking for numbers with 10 digits with atleast one surrounding whitespaces
		         // on both ends.
		         Pattern p = Pattern.compile("\\s\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\s");

		         // Matcher refers to the actual text where the pattern will be found
		         Matcher m = p.matcher(sb);

		         while (m.find()){
		             // group() method refers to the next number that follows the pattern we have specified.
		             System.out.println(m.group());
		         }

		         if (pd != null) {
		             pd.close();
		         }
		 } catch (Exception e){
		         e.printStackTrace();
		        }

	}
	public static void createPDF(String fileData,String fileName)
	{
		PDDocument doc = null;
        PDPage page = null;

       try{
           doc = new PDDocument();
           page = new PDPage();

           doc.addPage(page);
           PDFont font = PDType1Font.HELVETICA_BOLD;

           PDPageContentStream content = new PDPageContentStream(doc, page);
           content.beginText();
           content.setFont( font, 12 );
           content.moveTextPositionByAmount( 100, 700 );
           content.drawString(fileData);

           content.endText();
           content.close();
          doc.save(fileName);
          doc.close();
    } catch (Exception e){
        System.out.println(e);
    }
       System.out.println("Done");
	}
	public static void createPdfStream()
	{
		byte[] reportOutput = null;
		reportOutput = "cvcvxcvxcvxcv".getBytes();				    	  
		//response.setContentType("application/binary");
       // response.setHeader("Content-Disposition", "attachment; filename=D:\\ffff.pdf");
       // response.getOutputStream().write(reportOutput);	
	}
	
	public static void createPDFTable(String path ,String [][] data) throws IOException, COSVisitorException{
	
		PDDocument doc = new PDDocument();
	     PDPage page = new PDPage();
	      doc.addPage( page );
	     PDPageContentStream contentStream = new PDPageContentStream(doc, page);



	        String[][] content = data;

	        drawTable(page, contentStream, 700, 100, content);


	        contentStream.close();
	        doc.save(path );
	}
	public static void drawTable(PDPage page, PDPageContentStream contentStream,
	        float y, float margin,
	        String[][] content) throws IOException {
	    final int rows = content.length;
	    final int cols = content[0].length;
	    final float rowHeight = 20f;
	    final float tableWidth = page.findMediaBox().getWidth()-    (2*margin);
	    final float tableHeight = rowHeight * rows;
	    final float colWidth = tableWidth/(float)cols;
	    final float cellMargin=5f;

	    float nexty = y ;
	    for (int i = 0; i <= rows; i++) {
	    contentStream.drawLine(margin,nexty,margin+tableWidth,nexty);
	            nexty-= rowHeight;
	            }

	            //draw the columns
	            float nextx = margin;
	            for (int i = 0; i <= cols; i++) {
	            contentStream.drawLine(nextx,y,nextx,y-tableHeight);
	            nextx += colWidth;
	            }

	            //now add the text
	contentStream.setFont(PDType1Font.HELVETICA_BOLD,12);

	    float textx = margin+cellMargin;
	    float texty = y-15;
	    for(int i = 0; i < content.length; i++){
	    for(int j = 0 ; j < content[i].length; j++){
	    String text = content[i][j];
	    contentStream.beginText();

	    contentStream.moveTextPositionByAmount(textx,texty);

	    contentStream.drawString(text);
	    contentStream.endText();
	    textx += colWidth;
	    }
	    texty-=rowHeight;
	    textx = margin+cellMargin;
	    }
	}
}
