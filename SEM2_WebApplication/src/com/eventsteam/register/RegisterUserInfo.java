package com.eventsteam.register;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class RegisterUserInfo
 */
@WebServlet("/RegisterUserInfo")
public class RegisterUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Date date = new Date();
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String address = request.getParameter("input-address");
		String zip = request.getParameter("input-zip");
		String email = request.getParameter("input-email");
		String contact = request.getParameter("input-contact");
		Cookie ck[] = request.getCookies();
		String seatNumber = ck[0].getValue();

		String seatType;
		String seatPrice;
		char seatCode = seatNumber.charAt(0);

		switch (seatCode) {
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

		request.setAttribute("fName", firstName);
		request.setAttribute("lName", lastName);
		request.setAttribute("add", address);
		request.setAttribute("zip", zip);
		request.setAttribute("email", email);
		request.setAttribute("contact", contact);
		request.setAttribute("seatNumber", seatNumber);
		request.setAttribute("type", seatType);
		request.setAttribute("price", seatPrice);
		RequestDispatcher rd = request
				.getRequestDispatcher("RegisterGeneratePDF");
		rd.forward(request, response);
	}
}
