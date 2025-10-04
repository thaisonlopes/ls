package br.com.lopessolutions.base.util;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;

public class WControlaAcesso {

	HttpSession session = null;

	public WControlaAcesso() {
		if (FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().getExternalContext() != null) {
			session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		}
	}

	public void setAtributoSessao(String variavel, String usr) {
		if (session != null) {
			session.setAttribute(variavel, usr);
		}
	}

	public Object getAtributoSessao(String atributo) {
		if (session != null) {
			return session.getAttribute(atributo);
		} else {
			return null;
		}
	}

}
