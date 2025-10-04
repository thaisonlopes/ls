package br.com.util.base;

import br.com.lopessolutions.acesso.util.LoginUsuario;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;

public class UsuarioUtil {

 
	public static Boolean usuarioAcessoTotal() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			Object ob = session.getAttribute("UsuarioLogado");

			if (ob != null && ob instanceof LoginUsuario) {
				LoginUsuario lu = (LoginUsuario) ob;
				if (lu.getUsuario() != null && lu.getUsuario().getHabilitado() != null
						&& lu.getUsuario().getHabilitado() && lu.getUsuario().getTipoAcesso() != null
						&& lu.getUsuario().getTipoAcesso().getCodigo() == 20) {
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Boolean usuarioPublicador() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			Object ob = session.getAttribute("UsuarioLogado");

			if (ob != null && ob instanceof LoginUsuario) {
				LoginUsuario lu = (LoginUsuario) ob;
				if (lu.getUsuario() != null && lu.getUsuario().getHabilitado() != null
						&& lu.getUsuario().getHabilitado()) {

					if (lu.getUsuario().getTipoAcesso() != null && lu.getUsuario().getTipoAcesso().getCodigo() == 20) {
						return true;
					}

					if (lu.getUsuario().getTipoAcesso() != null && lu.getUsuario().getTipoAcesso().getCodigo() >= 2
							&& lu.getUsuario().getPublicador() != null && lu.getUsuario().getPublicador()) {
						return true;
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Boolean usuarioFornecedorMateria() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			Object ob = session.getAttribute("UsuarioLogado");

			if (ob != null && ob instanceof LoginUsuario) {
				LoginUsuario lu = (LoginUsuario) ob;
				if (lu.getUsuario() != null && lu.getUsuario().getHabilitado() != null
						&& lu.getUsuario().getHabilitado()) {

					if (lu.getUsuario().getTipoAcesso() != null && lu.getUsuario().getTipoAcesso().getCodigo() == 20) {
						return true;
					}

					if (lu.getUsuario().getTipoAcesso() != null && lu.getUsuario().getTipoAcesso().getCodigo() >= 2
							&& lu.getUsuario().getFornecedorMateria() != null
							&& lu.getUsuario().getFornecedorMateria()) {
						return true;
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

