package br.com.lopessolutions.base.rne;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.lopessolutions.entidades.principal.Log;
import jakarta.persistence.EntityManager;


/**
 * 
 * @author Thaison
 *
 */
public class ControleNavegacaoRNE extends GenericRNE {

	private static final long serialVersionUID = 5761387624677139781L;

	// Variaveis de Retorno
	private List<Serializable> entidadeList = null;
	private Class<Serializable> c;

	// Variaveis para pesquisa simples
	private String nomeEntidade = "";
	private String ordenacao = "";
	private String condicao = "";

	// Variaveis da quantidade de registros
	private int qtdeRegTotal = 0;
	private int qtdeRegCarregado = 0;
	private int posisaoInicial = -1;
	private int qtPagina = 1;
	private int pagina = 1;

	// Variaveis para pesquisas personalidadas
	private String sqlQuantidadeRegistro = "";
	private String sqlBuscaLista = "";
	private String sqlPosisaoRegistro = "";

	@Override
	protected void setLog(Log log, Serializable pojo) {
		// não e necessarrio a sua implementação
	}

	private void definirPagina(int a, int b) {
		if (b == 0) {
			b = 1;
		}
		int ab = (a % b);
		if (ab > 0) {
			qtPagina = (a / b) + 1;

		} else {
			qtPagina = (a / b);
		}

		if (qtPagina <= 0) {
			qtPagina = 1;
		}

	}

	/**
	 * Tipo Ordem : desc = Do último pro primeiro<br>
	 * Condicao : Considere 'e' , o nome da entidade, ex.: e.codigo < 1000
	 *
	 * @param session
	 * @param nomeEntidade
	 * @param condicao
	 * @param ordenacao
	 * @param qtdeRegPorPag
	 * @param parametros
	 */
	public void iniciarControleNavegacao(EntityManager session, String nomeEntidade, String condicao, String ordenacao,
			int qtdeRegPorPag, Object... parametros) {

		if (condicao.trim().length() > 0) {
			this.condicao = condicao;
		}

		this.qtdeRegCarregado = qtdeRegPorPag;
		this.nomeEntidade = nomeEntidade;
		this.ordenacao = ordenacao;

		// Inicia a lista
		if (entidadeList == null) {
			entidadeList = new ArrayList<>();
		}

		// Seta a posição inicial da busca
		posisaoInicial = 0;

		// Mota a string sql pra retorna a quantidade de registro
		sqlQuantidadeRegistro = "select count(e) from " + this.nomeEntidade + " e " + this.condicao;

		// Mota a string sql pra retorna a lista de registros
		sqlBuscaLista = "select e from " + this.nomeEntidade + " e " + this.condicao + " order by " + this.ordenacao;

		// Busca a quantidade de registro no banco de dados
		qtdeRegTotal = getInteger(session, sqlQuantidadeRegistro, parametros);

		// Define a quantidade de pagina que vai ter e seta a pagina inicial
		definirPagina(qtdeRegTotal, qtdeRegCarregado);
		pagina = 1;

		// Busca a lista
		List<Serializable> merged = getPureList(session, 0, qtdeRegCarregado, c, sqlBuscaLista, parametros);
		entidadeList.clear();
		entidadeList.addAll(merged);

	}

	/**
	 *
	 * @param session
	 * @param sqlQuantidadeRegistro
	 * @param sqlBuscaLista
	 * @param qtdeRegPorPag
	 * @param parametros
	 */
	public void iniciarControleNavegacao(EntityManager session, String sqlQuantidadeRegistro, String sqlBuscaLista,
			int qtdeRegPorPag, Object... parametros) {

		// define a quantidade de registro por pagina
		this.qtdeRegCarregado = qtdeRegPorPag;

		// Inicia a lista
		if (entidadeList == null) {
			entidadeList = new ArrayList<>();
		}

		// Seta a posição inicial da busca
		posisaoInicial = 0;

		// Mota a string sql pra retorna a quantidade de registro
		this.sqlQuantidadeRegistro = sqlQuantidadeRegistro;

		// Mota a string sql pra retorna a lista de registros
		this.sqlBuscaLista = sqlBuscaLista;

		// Busca a quantidade de registro no banco de dados
		qtdeRegTotal = getInteger(session, this.sqlQuantidadeRegistro, parametros);

		// Define a quantidade de pagina que vai ter e seta a pagina inicial
		definirPagina(qtdeRegTotal, qtdeRegCarregado);
		pagina = 1;

		// Busca a lista
		List<Serializable> merged = getPureList(session, 0, qtdeRegCarregado, c, this.sqlBuscaLista, parametros);
		entidadeList.clear();
		entidadeList.addAll(merged);

	}

	public void atualizarNR(EntityManager session, Object... parametros) {
		// Busca a quantidade de registro no banco de dados
		qtdeRegTotal = getInteger(session, this.sqlQuantidadeRegistro, parametros);
	}

	public void proximo(EntityManager session, Object... parametros) {
		try {

			if (posisaoInicial < 0) {
				posisaoInicial = 0;

			} else {
				int aux = posisaoInicial + qtdeRegCarregado;
				if (aux >= qtdeRegTotal) {
					aux = posisaoInicial + 0;
				}

				posisaoInicial = aux;
			}

			if (posisaoInicial < qtdeRegTotal) {

				List<Serializable> merged = getPureList(session, posisaoInicial, qtdeRegCarregado, c, sqlBuscaLista, parametros);
				entidadeList.clear();
				entidadeList.addAll(merged);

				if (pagina == qtPagina) {
					pagina = qtPagina;
				} else {
					pagina = pagina + 1;
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void anterior(EntityManager session, Object... parametros) {
		try {

			if (qtdeRegCarregado > 0) {

				posisaoInicial = posisaoInicial - qtdeRegCarregado;

				if (posisaoInicial < 0) {
					posisaoInicial = 0;
				}

				if (pagina == 1) {
					pagina = 1;
				} else {
					pagina = pagina - 1;
				}

				List<Serializable> merged = getPureList(session, posisaoInicial, qtdeRegCarregado, c, sqlBuscaLista, parametros);
				entidadeList.clear();
				entidadeList.addAll(merged);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void primeiro(EntityManager session, Object... parametros) {
		try {

			if (qtdeRegCarregado > 0) {

				posisaoInicial = 0;
				pagina = 1;

				List<Serializable> merged = getPureList(session, posisaoInicial, qtdeRegCarregado, c, sqlBuscaLista, parametros);
				entidadeList.clear();
				entidadeList.addAll(merged);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void ultimo(EntityManager session, Object... parametros) {
		try {

			if (qtdeRegCarregado != 0) {

				int aux = (qtdeRegTotal % qtdeRegCarregado);

				if (aux == 0) {
					aux = qtdeRegCarregado;
				}

				posisaoInicial = qtdeRegTotal - aux;

				if (posisaoInicial < 0) {
					posisaoInicial = 0;
				}

				pagina = qtPagina;
				List<Serializable> merged = getPureList(session, posisaoInicial, qtdeRegCarregado, c, sqlBuscaLista, parametros);
				entidadeList.clear();
				entidadeList.addAll(merged);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * So para navegasão com um registro - Deve ser implementado a
	 * sqlPosisaoRegistro
	 *
	 * @param session
	 * @param parametros
	 */
	public void atualizarPosisaoRegistro(EntityManager session, Object... parametros) {
		try {

			if (sqlPosisaoRegistro == null || sqlPosisaoRegistro.length() <= 10) {
				sqlPosisaoRegistro = sqlQuantidadeRegistro;
			}

			int i = getInteger(session, sqlPosisaoRegistro, parametros);

			if (qtdeRegCarregado != 0) {

				if (i < 0) {
					i = 0;
				}

				int aux = (qtdeRegTotal - i) - 1;

				if (aux <= 0) {
					aux = 0;
				}

				posisaoInicial = aux;

				if (posisaoInicial < 0) {
					posisaoInicial = 0;
				}

			}

		} catch (Exception ex) {

		}
	}

	public void setSqlPosisaoRegistro(String sqlPosisaoRegistro) {
		this.sqlPosisaoRegistro = sqlPosisaoRegistro;
	}

	@SuppressWarnings("unchecked")
	public <T extends Serializable> List<T> getLista() {
		if (entidadeList == null) {
			entidadeList = new ArrayList<>();
		}
		return (List<T>) entidadeList;
	}

	public String getVar() {
		String str = " Página: " + pagina + " de " + qtPagina + "  ";
		return str;
	}

	public int getQuantidadeRegistro() {
		return qtdeRegTotal;
	}

	public int getPagina() {
		return pagina;
	}

	// Para uso no data model
	// -------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public <T extends Serializable> List<T> getLista(EntityManager session, int posisaoInicial, int qtdeRegCarregado,
			Object... parametros) {

		// Inicia a lista
		if (entidadeList == null) {
			entidadeList = new ArrayList<>();
		}

		List<Serializable> merged = getPureList(session, posisaoInicial, qtdeRegCarregado, c, sqlBuscaLista, parametros);
		entidadeList.clear();
		entidadeList.addAll(merged);

		return (List<T>) entidadeList;

	}

	@SuppressWarnings("unchecked")
	protected <T extends Serializable> T getEnt(EntityManager session, Class<T> classToSearchPojo, Serializable key) {
		Serializable pojo = session.find(classToSearchPojo, key);
		return (T) pojo;
	}

	public int getQuantidadeRegistroDM(EntityManager session, Object... parametros) {
		qtdeRegTotal = getInteger(session, this.sqlQuantidadeRegistro, parametros);
		return qtdeRegTotal;
	}

}
