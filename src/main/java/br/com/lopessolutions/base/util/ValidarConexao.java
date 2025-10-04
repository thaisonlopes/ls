package br.com.lopessolutions.base.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Thaison
 *
 */
public class ValidarConexao {

	/**
	 * Teste de validade de uma conexão JDBC
	 *
	 * @param conn a JDBC conexão para teste
	 * @return true se a conexão é válida; caso contrário retornará false.
	 */
	public static boolean isValidConexaoPostgres(Connection conn) {
		return isValidConexao(conn, "postgres");
	}

	/**
	 * Teste de validade de uma conexão JDBC
	 *
	 * @param conn a JDBC conexão para teste
	 * @return true se a conexão é válida; caso contrário retornará false.
	 */
	public static boolean isValidConexaoMySql(Connection conn) {
		return isValidConexao(conn, "mysql");
	}

	/**
	 * Teste de validade de uma conexão JDBC
	 *
	 * @param conn a JDBC conexão para teste
	 * @return true se a conexão é válida; caso contrário retornará false.
	 */
	public static boolean isValidConexaoOracle(Connection conn) {
		return isValidConexao(conn, "oracle");
	}

	/**
	 * Teste de validade de uma conexão JDBC
	 *
	 * @param conn     a JDBC conexão para teste
	 * @param dbVendor db vendor {"oracle", "mysql", "postgres"}
	 * @return true se a conexão é válida; caso contrário retornará false.
	 */
	public static boolean isValidConexao(Connection conn, String dbVendor) {
		try {

			if (conn == null) {
				return false;
			}

			if (conn.isClosed()) {
				return false;
			}

			// Se a consulta retorna o resultado, então ela
			// é um objeto de conexão válido.
			if (dbVendor.equalsIgnoreCase("mysql")) {
				return testConexao(conn, "select 1");

			} else if (dbVendor.equalsIgnoreCase("postgres")) {
				return testConexao(conn, "select 1");

			} else if (dbVendor.equalsIgnoreCase("oracle")) {
				return testConexao(conn, "select 1 from dual");

			} else {
				return false;
			}

		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Teste de validade de uma conexão
	 *
	 * @param conn  a JDBC conexão para teste
	 * @param query uma sql query para testar a conexão db
	 * @return true se a conexão é válida; caso contrário retornará false.
	 *
	 */
	public static boolean testConexao(Connection conn, String query) {
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			if (stmt == null) {
				return false;
			}

			rs = stmt.executeQuery(query);
			if (rs == null) {
				return false;
			}

			// Conexão é válida: foi capaz de
			// conectar-se ao banco de dados e retornar algo útil.
			if (rs.next()) {
				return true;
			}

			// Não há mais teste de validade de uma conexão
			return false;

		} catch (SQLException e) {
			// Algo deu errado: conexão é ruim
			return false;
		} finally {
			// close recursos de banco de dados
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				// e.printStackTrace();
			}
		}
	}

}
