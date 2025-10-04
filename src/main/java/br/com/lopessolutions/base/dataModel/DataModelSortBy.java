package br.com.lopessolutions.base.dataModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Thaison
 *
 */
public class DataModelSortBy {

	private Map<String, Ordem> sortBy;

	private int nvPrioridade;

	public DataModelSortBy(int numeroDeColunas, Object... ordem) {

		sortBy = new HashMap<String, Ordem>();

		nvPrioridade = numeroDeColunas + 1;

		if (numeroDeColunas > 0) {

			for (int i = 1; i <= numeroDeColunas; i++) {

				Ordem ent = new Ordem(i);

				int c = 0;

				if (ordem != null) {
					for (int j = 0; j < ordem.length; j++) {
						c = (int) ordem[j];
						if (i == c) {
							ent.setSort(1);
							ent.setPrioridade(i);
							break;
						}
					}
				}

				sortBy.put("C" + i, ent);

			}

		}

	}

	/**
	 * ordem e ascDesc devem ter a mesma quantidade de registro
	 * 
	 * @param numeroDeColunas
	 * @param ordem           (Ordenação inicial)
	 * @param ascDesc         (Tipo ordenação. 1: ASC; 2: DESC)
	 * 
	 */
	public DataModelSortBy(int numeroDeColunas, Object[] ordem, Object[] ascDesc) {

		sortBy = new HashMap<String, Ordem>();

		nvPrioridade = numeroDeColunas + 1;

		if (numeroDeColunas > 0) {

			for (int i = 1; i <= numeroDeColunas; i++) {

				Ordem ent = new Ordem(i);

				int c = 0;
				int s = 0;

				if (ordem != null) {
					for (int j = 0; j < ordem.length; j++) {
						c = (int) ordem[j];
						s = (int) ascDesc[j];
						if (i == c) {
							if (s != 1 && s != 2) {
								s = 1;
							}
							ent.setSort(s);
							ent.setPrioridade(i);
							break;
						}
					}
				}

				sortBy.put("C" + i, ent);

			}

		}

	}

	/**
	 * Função do link de ordenação
	 * 
	 * @param coluna
	 */
	public void ordenar(int coluna) {
		Ordem ent = sortBy.get("C" + coluna);
		int s = ent.getSort() + 1;
		if (s == 1 || s == 2) {
			ent.setSort(s);
			ent.setPrioridade(nvPrioridade);
			nvPrioridade++;
		} else {
			ent.setSort(0);
			ent.setPrioridade(0);
			nvPrioridade++;
		}
		sortBy.replace("C" + coluna, ent);
	}

	/**
	 * 
	 * @param coluna
	 * @return retorna o icone da ordenação
	 */
	public String getIcone(int coluna) {
		Ordem ent = sortBy.get("C" + coluna);
		return ent.getIcone();
	}

	/**
	 * 
	 * @param coluna
	 * @return retorna a prioridade
	 */
	public String getPrioridade(int coluna) {
		Ordem ent = sortBy.get("C" + coluna);
		return ent.getPrioridade() == 0 ? "" : "" + ent.getPrioridade();
	}

	/**
	 * 
	 * @return retorna a ordem que a pesquisa deve ordenar a busca
	 */
	public String getOrderBy() {

		String ret = "";
		int nvp = 1;

		Collection<Ordem> ordens = sortBy.values();
		for (int i = 1; i <= nvPrioridade; i++) {
			for (Ordem ordem : ordens) {
				if (ordem.getPrioridade() == i && (ordem.getSort() == 1 || ordem.getSort() == 2)) {
					if (ret.length() > 0) {
						ret = ret + ", " + ordem.getColuna() + getTipoSort(ordem.getSort());
					} else {
						ret = " ORDER BY " + ordem.getColuna() + getTipoSort(ordem.getSort());
					}
					ordem.setPrioridade(nvp);
					nvp++;
					break;
				} else if (ordem.getPrioridade() > 0 && ordem.getSort() == 0) {
					ordem.setPrioridade(0);
				}

			}
		}

		nvPrioridade = nvp;

		return ret;
	}

	private String getTipoSort(int s) {
		if (s == 1) {
			return " ASC";
		} else if (s == 2) {
			return " DESC";
		}
		return "";
	}

	public class Ordem {

		// 0 - SEM; 1 - ASC; 2 - DESC;
		private int coluna = 0;
		private int sort = 0;
		private int prioridade = 0;

		public Ordem(int coluna) {
			this.coluna = coluna;
		}

		public int getColuna() {
			return coluna;
		}

		public void setColuna(int coluna) {
			this.coluna = coluna;
		}

		public int getSort() {
			return sort;
		}

		public void setSort(int sort) {
			this.sort = sort;
		}

		public int getPrioridade() {
			return prioridade;
		}

		public void setPrioridade(int prioridade) {
			this.prioridade = prioridade;
		}

		public String getIcone() {
			if (sort == 1) {
				return "ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s ui-icon-triangle-1-n";
			} else if (sort == 2) {
				return "ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s ui-icon-triangle-1-s";
			}
			return "ui-sortable-column-icon ui-icon ui-icon-carat-2-n-s";
		}

	}

}
