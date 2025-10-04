package br.com.lopessolutions.base.util;

/**
 * 
 * @author Thaison
 *
 */
public class ModoTela {

	public static final int INSERCAO = 0, ALTERACAO = 1, NULO = 2, PESQUISA = 3;

	private int modo;
	private boolean salvar, alterar, excluir, campo;

	public ModoTela() {
		this.modo = NULO;
		this.salvar = false;
		this.alterar = false;
		this.excluir = false;
		this.campo = false;
	}

	// Desativa os botões e campos
	public void nulo() {
		this.modo = NULO;
		this.salvar = false;
		this.alterar = false;
		this.excluir = false;
		this.campo = false;
	}

	// Ativa o botão salvar e os campos, desabilita alterar e excluir
	public void insercao() {
		this.modo = INSERCAO;
		this.salvar = true;
		this.alterar = false;
		this.excluir = false;
		this.campo = true;
	}

	// Ativa o botão alterar, excluir e os campos, desabilita salvar
	public void alteracao() {
		this.modo = ALTERACAO;
		this.salvar = false;
		this.alterar = true;
		this.excluir = true;
		this.campo = false;
	}

	// Ativa o botão alterar, excluir e os campos, desabilita salvar
	public void pesquisa() {
		this.modo = PESQUISA;
		this.salvar = false;
		this.alterar = false;
		this.excluir = false;
		this.campo = true;
	}

	public void exclusaoCompleta() {
		this.modo = PESQUISA;

		this.salvar = false;
		this.alterar = true;
		this.excluir = true;
	}

	public boolean isSalvar() {
		return salvar;
	}

	public void setSalvar(boolean salvar) {
		this.salvar = salvar;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public boolean isExcluir() {
		return excluir;
	}

	public void setExcluir(boolean excluir) {
		this.excluir = excluir;
	}

	public boolean isCampo() {
		return campo;
	}

	public void setCampo(boolean campo) {
		this.campo = campo;
	}

	public int getModo() {
		return modo;
	}

	public void setModo(int modo) {
		this.modo = modo;
	}

}
