package br.com.lopessolutions.acesso.util;

import java.io.Serializable;

import br.com.lopessolutions.entidades.principal.Usuario;

public class LoginUsuario implements Serializable {

	private static final long serialVersionUID = -3854760414532198046L;

	private Usuario usuario;
	private boolean logado = false;
	private String siglaParam;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public boolean isLogado() {
		return logado;
	}

	public String getSiglaParam() {
		return siglaParam;
	}

	public void setSiglaParam(String siglaParam) {
		this.siglaParam = siglaParam;
	}

}
