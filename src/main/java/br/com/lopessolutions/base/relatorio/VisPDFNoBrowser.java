package br.com.lopessolutions.base.relatorio;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.primefaces.PrimeFaces;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 * @author Thaison
 *
 */
public class VisPDFNoBrowser extends VisRel {/*

	@Override
	public void visRel(JasperPrint jp, String titulo) throws Exception {

		byte[] bytes = JasperExportManager.exportReportToPdf(jp);

		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();

		res.setContentType("application/pdf");
		res.setHeader("Content-Disposition", "inline; filename=" + getTitulo(titulo) + ".pdf");
		res.setContentLength(bytes.length);

		OutputStream ouputStream = res.getOutputStream();

		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();

		context.responseComplete();

	}

	@Override
	public void visRel(byte[] bytes, String titulo) throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();

		res.setContentType("application/pdf");
		res.setHeader("Content-Disposition", "inline; filename=" + getTitulo(titulo) + ".pdf");
		res.setContentLength(bytes.length);

		OutputStream ouputStream = res.getOutputStream();

		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();

		context.responseComplete();

	}

	@Override
	public void baixRel(byte[] bytes, String titulo) throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();

		res.setContentType("application/pdf");
		res.setHeader("Content-Disposition", "attachment; filename=" + getTitulo(titulo) + ".pdf");
		res.setContentLength(bytes.length);

		OutputStream ouputStream = res.getOutputStream();

		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();

		context.responseComplete();

	}

	@Override
	public void visRelModal(byte[] bytes, String titulo, String id) throws Exception {

		//Faces.setSessionAttribute("reportBytes", bytes);
		//Faces.setSessionAttribute("reportTitulo", getTitulo(titulo));

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("modal", false);
		options.put("widgetVar", id);
		options.put("draggable", true);
		options.put("resizable", true);
		options.put("responsive", true);
		options.put("width", "80%");
		options.put("height", "90%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("styleClass", "sw-dialog-imp");
		options.put("closeOnEscape", true);

		options.put("minimizable", false);
		options.put("maximizable", true);

		PrimeFaces.current().dialog().openDynamic("/sistema/util/reportview.xhtml", options, null);

	}

	@Override
	public void visRelModal(byte[] bytes, String titulo) throws Exception {

		//Faces.setSessionAttribute("reportBytes", bytes);
		//Faces.setSessionAttribute("reportTitulo", getTitulo(titulo));

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("modal", false);
		options.put("draggable", true);
		options.put("resizable", true);
		options.put("responsive", true);
		options.put("width", "80%");
		options.put("height", "90%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("styleClass", "sw-dialog-imp");
		options.put("closeOnEscape", true);

		options.put("minimizable", true);
		options.put("maximizable", true);

		PrimeFaces.current().dialog().openDynamic("/nexus/util/reportview.xhtml", options, null);

	}

	@Override
	public void visRelModalBloc(byte[] bytes, String titulo) throws Exception {

		//Faces.setSessionAttribute("reportBytes", bytes);
		//Faces.setSessionAttribute("reportTitulo", getTitulo(titulo));

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("modal", true);
		options.put("draggable", true);
		options.put("resizable", true);
		options.put("responsive", true);
		options.put("width", "80%");
		options.put("height", "90%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("styleClass", "sw-dialog-imp");
		options.put("closeOnEscape", true);

		options.put("minimizable", true);
		options.put("maximizable", true);

		PrimeFaces.current().dialog().openDynamic("/sistema/util/reportview.xhtml", options, null);

	}

	private String getTitulo(String titulo) {
		if (titulo == null || titulo.length() == 0) {
			return "rel";
		}
		return titulo.replace(",", "").replace(";", " ").trim();
	}
*/
}
