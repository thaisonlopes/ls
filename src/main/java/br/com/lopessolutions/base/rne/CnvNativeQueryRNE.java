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
public class CnvNativeQueryRNE extends GenericRNE {

	private static final long serialVersionUID = -1342487568660496676L;

	// Variaveis de Retorno
	private List<?> entidadeList = null;

	// Variaveis da quantidade de registros
	private int qtdeRegTotal = 0;
	private int qtdeRegCarregado = 0;
	private int posisaoInicial = -1;
	private int qtPagina = 1;
	private int pagina = 1;

	// Variaveis para pesquisas personalidadas
	private String sqlQuantidadeRegistro = "";
	private String sqlBuscaLista = "";

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
	 *
	 * @param session
	 * @param sqlNrReg
	 * @param sql
	 * @param nrReg
	 * @param parametros
	 */
	public void iniciarCnv(EntityManager session, String sqlNrReg, String sql, int nrReg, Object... parametros) {

		// define a quantidade de registro por pagina
		this.qtdeRegCarregado = nrReg;

		// Inicia a lista
		if (this.entidadeList == null) {
			this.entidadeList = new ArrayList<>();
		}

		// Seta a posição inicial da busca
		this.posisaoInicial = -1;

		// Mota a string sql pra retorna a quantidade de registro
		this.sqlQuantidadeRegistro = sqlNrReg;

		// Mota a string sql pra retorna a lista de registros
		this.sqlBuscaLista = sql;

		// Busca a quantidade de registro no banco de dados
		this.qtdeRegTotal = getIntegerNativeQuery(session, this.sqlQuantidadeRegistro, parametros);

		// Define a quantidade de pagina que vai ter e seta a pagina inicial
		definirPagina(this.qtdeRegTotal, this.qtdeRegCarregado);
		this.pagina = 1;

		// Busca a lista
		this.entidadeList.clear();

		// Não vi necessidade de buscar a lista neste momento
		// this.entidadeList = getListNativeQuery(session, 0, this.qtdeRegCarregado,
		// this.sqlBuscaLista, parametros);
	}

	public void atualizarNR(EntityManager session, Object... parametros) {
		// Busca a quantidade de registro no banco de dados
		this.qtdeRegTotal = getIntegerNativeQuery(session, this.sqlQuantidadeRegistro, parametros);
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

				entidadeList.clear();
				entidadeList = getListNativeQuery(session, posisaoInicial, qtdeRegCarregado, sqlBuscaLista, parametros);

				if (pagina == qtPagina) {
					pagina = qtPagina;
				} else {
					pagina = pagina + 1;
				}

			}

		} catch (Exception ex) {

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

				entidadeList.clear();
				entidadeList = getListNativeQuery(session, posisaoInicial, qtdeRegCarregado, sqlBuscaLista, parametros);

			}

		} catch (Exception ex) {

		}
	}

	public void primeiro(EntityManager session, Object... parametros) {
		try {

			if (qtdeRegCarregado > 0) {

				posisaoInicial = 0;
				pagina = 1;

				entidadeList.clear();
				entidadeList = getListNativeQuery(session, posisaoInicial, qtdeRegCarregado, sqlBuscaLista, parametros);

			}

		} catch (Exception e) {

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

				entidadeList.clear();
				entidadeList = getListNativeQuery(session, posisaoInicial, qtdeRegCarregado, sqlBuscaLista, parametros);

			}

		} catch (Exception ex) {

		}

	}

	/**
	 * So para navegasão com um registro
	 *
	 * @param session
	 * @param parametros
	 */
	public void atualizarPosisaoRegistro(EntityManager session, Object... parametros) {
		try {

			int i = getIntegerNativeQuery(session, sqlQuantidadeRegistro, parametros);

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

	public List<?> getLista() {
		if (entidadeList == null) {
			entidadeList = new ArrayList<>();
		}
		return entidadeList;
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

	public int getQtPagina() {
		return qtPagina;
	}

	// Para uso no data model
	// -------------------------------------------------------------------------------------
	public List<?> getLista(EntityManager session, int posisaoInicial, int qtdeRegCarregado, Object... parametros) {

		// Inicia a lista
		if (entidadeList == null) {
			entidadeList = new ArrayList<>();
		}

		entidadeList.clear();
		entidadeList = getListNativeQuery(session, posisaoInicial, qtdeRegCarregado, sqlBuscaLista, parametros);

		return entidadeList;

	}

	/**
	 * Para usso desta busca não pode ter declarado uma ordenação na pesquisa
	 *
	 * @param session
	 * @param posisaoInicial
	 * @param qtdeRegCarregado
	 * @param ordenacao
	 * @param parametros
	 * @return
	 */
	public List<?> getListaOrderBy(EntityManager session, int posisaoInicial, int qtdeRegCarregado, String ordenacao,
			Object... parametros) {

		// Inicia a lista
		if (entidadeList == null) {
			entidadeList = new ArrayList<>();
		}

		entidadeList.clear();
		entidadeList = getListNativeQuery(session, posisaoInicial, qtdeRegCarregado, sqlBuscaLista + ordenacao, parametros);

		return entidadeList;

	}

	@SuppressWarnings("unchecked")
	protected <T extends Serializable> T getEnt(EntityManager session, Class<T> classToSearchPojo, Serializable key) {
		Serializable pojo = session.find(classToSearchPojo, key);
		return (T) pojo;
	}

	public int getQuantidadeRegistroDM(EntityManager session, Object... parametros) {
		qtdeRegTotal = getIntegerNativeQuery(session, this.sqlQuantidadeRegistro, parametros);
		return qtdeRegTotal;
	}

}
