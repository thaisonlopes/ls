package br.com.lopessolutions.base.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import br.com.lopessolutions.entidades.principal.Usuario;

/**
 * 
 * @author Thaison
 *
 */
public abstract class UsuarioDataHora {

	public abstract void setUsuarioAcesso(String nome);

	public abstract void setDataHora(Date dataHora);

	public void iniciarDadosUsuarioDataLanc() {
		try {

			WControlaAcesso ca = new WControlaAcesso();
			Object o = ca.getAtributoSessao("UsuarioSessao");
			if (o == null) {
				Object nu = ca.getAtributoSessao("nomeUsuarioSessao");
				if (nu != null) {
					setUsuarioAcesso(nu.toString());
				} else {
					setUsuarioAcesso("Não Identificado");
				}

			} else {
				Usuario u = (Usuario) ca.getAtributoSessao("UsuarioSessao");
				if (u != null) {
					setUsuarioAcesso(u.getCodigo() + " - " + u.getNome());
				} else {
					String nu = (String) ca.getAtributoSessao("nomeUsuarioSessao");
					if (nu != null) {
						setUsuarioAcesso(nu);
					} else {
						setUsuarioAcesso("Não Identificado");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// -define a data e hora da operação ------------------------------------
			TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
			TimeZone.setDefault(tz);
			Calendar ca = GregorianCalendar.getInstance(tz);
			setDataHora(ca.getTime());
		} catch (Exception e) {
		}

	}

}
