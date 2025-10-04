package br.com.lopessolutions.base.relatorio;

import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


 
@WebServlet("/documento.pdf")
public class PDFReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		byte[] bytes = (byte[]) request.getSession().getAttribute("reportBytes");
		String titulo = (String) request.getSession().getAttribute("reportTitulo");

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=" + getTitulo(titulo) + ".pdf");
		response.setContentLength(bytes.length);

		OutputStream ouputStream = response.getOutputStream();

		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected String getTitulo(String titulo) {
		if (titulo == null || titulo.length() == 0) {
			return "rel";
		}
		return titulo.replace(",", "").replace(";", " ").trim();
	}

}
