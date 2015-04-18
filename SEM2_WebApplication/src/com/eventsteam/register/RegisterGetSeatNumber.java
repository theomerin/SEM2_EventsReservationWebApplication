package com.eventsteam.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterGetSeatNumber
 */
@WebServlet("/RegisterGetSeatNumber")
public class RegisterGetSeatNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		RequestDispatcher rd=request.getRequestDispatcher("/reserve-now2.html");  
        rd.include(request, response);
	}
}
