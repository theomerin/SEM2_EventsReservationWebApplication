package com.eventsteam.register;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
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
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String address = request.getParameter("address");
		String zip = request.getParameter("zip");
		String email = request.getParameter("email");
		String contact = request.getParameter("mobilenum");
		System.out.print(lastName);
		String seatNumber = request.getParameter("seatNo");
		String seatType;
		String seatPrice;
		char seatCode = seatNumber.charAt(0);
		
		switch(seatCode) {
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
				seatType = "VIP";
				seatPrice = "5000";
				break;
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
				seatType = "General Admission";
				seatPrice = "500";
				break;
			default:
				seatType = "Patron";
				seatPrice = "2500";
				break;
		}

		try {

			String text = firstName + " " + lastName + "\n" + address + " "
					+ zip + "\n" + email + "\n" + contact;

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
			Font fontHeader = new Font(Font.FontFamily.HELVETICA  , 20, Font.BOLD);
			Font fontInfo = new Font(Font.FontFamily.HELVETICA  , 16, Font.BOLD);
			Font fontFooter = new Font(Font.FontFamily.HELVETICA  , 14, Font.ITALIC);
			Font header = new Font(Font.FontFamily.HELVETICA  , 8, Font.ITALIC);
			// step 4
			Paragraph head = new Paragraph("You have successfully reserved the seat: " + seatNumber, fontHeader);
			Paragraph subhead1 = new Paragraph("\nCUSTOMER INFORMATION", fontInfo);
			Paragraph subhead2 = new Paragraph("\nRESERVATION SUMMARY", fontInfo);
			Paragraph foot = new Paragraph("\nNOTE: You must secure your payment within five (5) working days or your reservation will be forfeited", fontFooter);
			head.setAlignment(Element.ALIGN_CENTER);
			subhead1.setAlignment(Element.ALIGN_CENTER);
			subhead2.setAlignment(Element.ALIGN_CENTER);
			foot.setAlignment(Element.ALIGN_CENTER);
			document.add(new Paragraph("EVENTSTEAM Reservation System", header));
			document.add(head);
			document.add(subhead1);
			document.add(new Paragraph("Customer: " + firstName + " " + lastName));
			document.add(new Paragraph("Address: " + address + " " + zip));
			document.add(new Paragraph("Email: " + email));
			document.add(new Paragraph("Contact Number: " + contact));
			document.add(subhead2);
			document.add(new Paragraph("Seat Number: " + seatNumber));
			document.add(new Paragraph("Seat Type: " + seatType));
			document.add(new Paragraph("Price: " + seatPrice));
			document.add(foot);
			
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
		} catch (DocumentException e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * Serial version UID.
	 */
}
