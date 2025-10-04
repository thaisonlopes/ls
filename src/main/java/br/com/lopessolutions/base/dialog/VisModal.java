package br.com.lopessolutions.base.dialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.PrimeFaces;

/**
 * 
 * @author Thaison
 *
 */
public class VisModal {

	public static void visModal(String tela) throws Exception {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("id", "modalBase");
		options.put("widgetVar", "modalBase");

		options.put("modal", true);
		options.put("draggable", true);
		options.put("resizable", true);
		options.put("responsive", true);
		options.put("width", "90%");
		options.put("height", "80%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("styleClass", "sw-dialog");

		options.put("closeOnEscape", true);
		options.put("closable", true);

		options.put("minimizable", true);
		options.put("maximizable", true);

		PrimeFaces.current().dialog().openDynamic(tela, options, null);

	}

	public static void visModal(String id, String tela) throws Exception {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("id", id);
		options.put("widgetVar", id);

		options.put("modal", true);
		options.put("draggable", true);
		options.put("resizable", true);
		options.put("responsive", true);
		options.put("width", "90%");
		options.put("height", "80%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("styleClass", "sw-dialog");

		options.put("closeOnEscape", true);
		options.put("closable", true);

		options.put("minimizable", true);
		options.put("maximizable", true);

		PrimeFaces.current().dialog().openDynamic(tela, options, null);

	}

	public static void visModal(String tela, Map<String, List<String>> params) throws Exception {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("id", "modalBase");
		options.put("widgetVar", "modalBase");

		options.put("modal", true);
		options.put("draggable", true);
		options.put("resizable", true);
		options.put("responsive", true);
		options.put("width", "90%");
		options.put("height", "80%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("styleClass", "sw-dialog");

		options.put("closeOnEscape", true);
		options.put("closable", true);

		options.put("minimizable", true);
		options.put("maximizable", true);

		PrimeFaces.current().dialog().openDynamic(tela, options, params);

	}

	public static void visModal(String id, String tela, Map<String, List<String>> params) throws Exception {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("id", id);
		options.put("widgetVar", id);

		options.put("modal", true);
		options.put("draggable", true);
		options.put("resizable", true);
		options.put("responsive", true);
		options.put("width", "90%");
		options.put("height", "80%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("styleClass", "sw-dialog");

		options.put("closeOnEscape", true);
		options.put("closable", true);

		options.put("minimizable", true);
		options.put("maximizable", true);

		PrimeFaces.current().dialog().openDynamic(tela, options, params);

	}

	// =============================================================================================
	/**
	 * Sem opção de fechar, deve ser implementada a parte
	 * 
	 * @param tela
	 * @throws Exception
	 */
	public static void visModal_CF(String tela) throws Exception {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("id", "modalBase");
		options.put("widgetVar", "modalBase");

		options.put("modal", true);
		options.put("draggable", true);
		options.put("resizable", true);
		options.put("responsive", true);
		options.put("width", "90%");
		options.put("height", "80%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("styleClass", "sw-dialog");

		options.put("closeOnEscape", false);
		options.put("closable", false);

		options.put("minimizable", false);
		options.put("maximizable", false);

		PrimeFaces.current().dialog().openDynamic(tela, options, null);

	}

	/**
	 * Sem opção de fechar, deve ser implementada a parte
	 * 
	 * @param id
	 * @param tela
	 * @throws Exception
	 */
	public static void visModal_CF(String id, String tela) throws Exception {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("id", id);
		options.put("widgetVar", id);

		options.put("modal", true);
		options.put("draggable", true);
		options.put("resizable", true);
		options.put("responsive", true);
		options.put("width", "90%");
		options.put("height", "80%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("styleClass", "sw-dialog");

		options.put("closeOnEscape", false);
		options.put("closable", false);

		options.put("minimizable", false);
		options.put("maximizable", false);

		PrimeFaces.current().dialog().openDynamic(tela, options, null);

	}

	/**
	 * Sem opção de fechar, deve ser implementada a parte
	 * 
	 * @param tela
	 * @param params
	 * @throws Exception
	 */
	public static void visModal_CF(String tela, Map<String, List<String>> params) throws Exception {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("id", "modalBase");
		options.put("widgetVar", "modalBase");

		options.put("modal", true);
		options.put("draggable", true);
		options.put("resizable", true);
		options.put("responsive", true);
		options.put("width", "90%");
		options.put("height", "80%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("styleClass", "sw-dialog");

		options.put("closeOnEscape", false);
		options.put("closable", false);

		options.put("minimizable", false);
		options.put("maximizable", false);

		PrimeFaces.current().dialog().openDynamic(tela, options, params);

	}

	/**
	 * Sem opção de fechar, deve ser implementada a parte
	 * 
	 * @param id
	 * @param tela
	 * @param params
	 * @throws Exception
	 */
	public static void visModal_CF(String id, String tela, Map<String, List<String>> params) throws Exception {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("id", id);
		options.put("widgetVar", id);

		options.put("modal", true);
		options.put("draggable", true);
		options.put("resizable", true);
		options.put("responsive", true);
		options.put("width", "90%");
		options.put("height", "80%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("styleClass", "sw-dialog");

		options.put("closeOnEscape", false);
		options.put("closable", false);

		options.put("minimizable", false);
		options.put("maximizable", false);

		PrimeFaces.current().dialog().openDynamic(tela, options, params);

	}
}
