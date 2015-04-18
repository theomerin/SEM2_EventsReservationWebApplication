package com.eventsteam.register;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

/**
 * Servlet implementation class RegisterGeneratePDF
 */
@WebServlet("/RegisterGeneratePDF")
public class RegisterGeneratePDF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	        try {
	            // Get the text that will be added to the PDF
	            String text = request.getParameter("text");
	            if (text == null || text.trim().length() == 0) {
	                 text = "You didn't enter any text.";
	            }
	            // step 1
	            Document document = new Document();
	            // step 2
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            PdfWriter.getInstance(document, baos);
	            // step 3
	            document.open();
	            // step 4
	            document.add(new Paragraph(String.format(
	                "You have submitted the following text using the %s method:",
	                request.getMethod())));
	            document.add(new Paragraph(text));
	            // step 5
	            document.close();
	            
	            // setting some response headers
	            response.setHeader("Expires", "0");
	            response.setHeader("Cache-Control",
	                "must-revalidate, post-check=0, pre-check=0");
	            response.setHeader("Pragma", "public");
	            // setting the content type
	            response.setContentType("application/pdf");
	            // the contentlength
	            response.setContentLength(baos.size());
	            // write ByteArrayOutputStream to the ServletOutputStream
	            ServletOutputStream os = response.getOutputStream();
	            baos.writeTo(os);
	            os.flush();
	            os.close();
	        }
	        catch(DocumentException e) {
	            throw new IOException(e.getMessage());
	        }
	    }

	    /**
	     * Serial version UID.
	     */
}
