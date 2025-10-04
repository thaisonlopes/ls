package br.com.lopessolutions.base.util;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 
 * @author Thaison
 *
 */
public class UEnderecoComputador {

	public static String getRemoteAddr() {
		String ipAddress = null;
		try {

			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

			ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}

		} catch (Exception e) {
		}

		return ipAddress;
	}

	public static String getRemoteHost() {
		String host = "";
		try {

			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

			if (request != null) {
				host = request.getRemoteHost();
			}

			if (host == null) {
				host = "";
			}

		} catch (Exception e) {
		}

		return host;
	}

}
