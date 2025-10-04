package br.com.lopessolutions.base.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

/**
 * 
 * @author Thaison
 *
 */
public class BDAction implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public void dropIfExists(String... tabelas) {
		BDUtil.dropIfExists(getBanco(), tabelas);
	}

	@Override
	protected void finalize() throws Throwable {
		banco.close();
		conexao.close();
		super.finalize();
	}

	public void close() throws Exception {
		banco.close();
		conexao.close();
	}

	public void closeConect() throws SQLException {

		if (banco != null && !banco.isClosed()) {
			banco.close();
		}

		if (conexao != null && !conexao.isClosed()) {
			conexao.close();
		}
	}

	public Statement getBanco() {
		try {

			getConexao();

			banco = Conexao.getBanco(conexao);

			return banco;

		} catch (Exception e) {
			FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao criar statement: "
						+ e, ""));
			return null;
		}
	}

	public Connection getConexao() {

		if (!ValidarConexao.isValidConexao(conexao, "postgres")) {
			conexao = Conexao.getConexao();
		}
		return conexao;
	}

	public Statement getNovoBanco() throws Exception {
		return Conexao.getBanco(getConexao());
	}

	// Sem AutoCommit ======================================================
	public Statement getBancoSemAutoCommit() {
		try {

			getConexaoSemAutoCommit();

			banco = Conexao.getBanco(conexao);

			return banco;

		} catch (Exception e) {
			FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao criar statement: "
						+ e, ""));
			return null;
		}
	}

	public Connection getConexaoSemAutoCommit() {

		if (!ValidarConexao.isValidConexao(conexao, "postgres")) {
			conexao = Conexao.getConexaoSemAutoCommit();
		}
		return conexao;
	}

	public Statement getNovoBancoSemAutoCommit() throws Exception {
		return Conexao.getBanco(getConexaoSemAutoCommit());
	}

	public void commit() throws Exception {
		if (conexao != null && conexao.getAutoCommit() == false) {
			conexao.commit();
		}
	}

	public void rollback() throws Exception {
		if (conexao != null && conexao.getAutoCommit() == false) {
			conexao.rollback();
		}
	}

	public void rollback(Exception e) throws Exception {
		if (conexao != null && conexao.getAutoCommit() == false) {
			conexao.rollback();
		}
		throw (e);
	}

	public String getTela() {
		return tela;
	}

	public void setTela(String tela) {
		this.tela = tela;
	}

	public String getItemTela() {
		return itemTela;
	}

	public void setItemTela(String itemTela) {
		this.itemTela = itemTela;
	}

	private String tela, itemTela;
	private Connection conexao;
	private Statement banco;

}
