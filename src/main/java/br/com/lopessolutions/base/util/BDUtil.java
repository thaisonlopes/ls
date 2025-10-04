package br.com.lopessolutions.base.util;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Thaison
 *
 */
public class BDUtil {

	public static void dropIfExists(Statement banco, String... tabelas) {
		for (String tabela : tabelas) {
			dropIfExists(tabela, banco);
		}
	}

	public static void dropIfExists(String tabela, Statement banco) {
		try {

			String sql = "drop table if exists " + tabela;

			banco.execute(sql);

			banco.getConnection().commit();

		} catch (SQLException e) {
			try {

				banco.getConnection().rollback();

			} catch (SQLException e1) {
			}
		}
	}

}
