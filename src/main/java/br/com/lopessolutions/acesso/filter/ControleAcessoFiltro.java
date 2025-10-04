package br.com.lopessolutions.acesso.filter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.lopessolutions.base.util.Conexao;
import jakarta.servlet.http.HttpServletRequest;

public class ControleAcessoFiltro {

	public boolean isAcessoFormularioSistema(long idUsuario, String formulario, HttpServletRequest request) {

		boolean temAcesso = false;

		try {

			Connection conn = Conexao.getConexao(request);
			Statement stmt = Conexao.getBanco(conn);

			try {

				StringBuilder sql = new StringBuilder();

				sql.append(" SELECT p.codPermissao, p.acesso FROM intPermissao p");
				sql.append(" INNER JOIN intFormulario f ON f.codformulario = p.codformulario");
				sql.append(" WHERE p.usuario = ").append(idUsuario);
				sql.append(" AND f.formulario = '").append(formulario).append("'");

				ResultSet rs = stmt.executeQuery(sql.toString());

				if (rs.next()) {
					temAcesso = rs.getBoolean("acesso");
				}

				rs.close();
				stmt.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				if (stmt != null) {
					stmt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return temAcesso;
	}

}
