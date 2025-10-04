package br.com.lopessolutions.base.relatorio;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import jakarta.persistence.EntityManager;

public class RImpressaoGenerica extends Relatorio {/*

	private static final long serialVersionUID = 6971589884939457161L;

	private EntityManager session;
	private Statement banco;
	private String sqlRelatorio, arquivo, titulo;
	private Map<String, Object> map;
	private String nomeRelatorio = "";
	public RImpressaoGenerica(EntityManager session, String sqlRelatorio, String arquivo, String titulo,
			Map<String, Object> map, String nomeRelatorio) throws Exception {

		this.session = session;
		this.sqlRelatorio = sqlRelatorio;
		this.arquivo = arquivo;
		this.titulo = titulo;
		this.map = map;
		this.nomeRelatorio = nomeRelatorio;
		banco = getBanco();
	}

	@Override
	public ResultSet getDados() throws Exception {
		ResultSet r = banco.executeQuery(sqlRelatorio);

		return r;
	}

	@Override
	public RelatAux getRelatAux() throws Exception {
		Map<String, Object> parametroAuxiliar = new HashMap<String, Object>();
		RelatAux re = new RelatAux(session, arquivo, titulo, isMdh(), nomeRelatorio, parametroAuxiliar);

		poeParametros(re);

		return re;
	}

	private void poeParametros(RelatAux re) throws Exception {

		re.poeParametros(map);

	}

	@Override
	public void dropIfExists(boolean fim) throws Exception {

	}
*/
}