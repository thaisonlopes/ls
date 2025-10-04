package br.com.lopessolutions.base.util;

import java.io.OutputStream;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 * @author Thaison
 *
 */
public class Download {

	public static final String PDF = "pdf";

	public static final String CSV = "csv";

	public static final String IMPT = "impt";

	public static final String DOC = "doc";

	public static final String XLS = "xls";

	public static final String DOCX = "docx";

	public static final String TEXT = "text";

	public static final String TXT = "txt";

	public static final String XLSX = "xlsx";

	public static void baixRel(byte[] bytes, String titulo, String extensao) throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();

		res.setContentType("application/pdf");
		res.setHeader("Content-Disposition", "attachment; filename=" + getTitulo(titulo) + "." + extensao);
		res.setContentLength(bytes.length);

		OutputStream ouputStream = res.getOutputStream();

		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();

		context.responseComplete();

	}

	private static String getTitulo(String titulo) {
		if (titulo == null || titulo.length() == 0) {
			return "rel";
		}
		return titulo.replace(",", "").replace(";", " ").trim();
	}

}
