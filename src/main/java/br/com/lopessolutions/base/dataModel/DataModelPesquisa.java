package br.com.lopessolutions.base.dataModel;

import java.util.List;

public interface DataModelPesquisa {

	public int getQuantidadeRegistroDM();

	public List<?> getLista(int posisaoInicial, int qtdeRegCarregado);

	public Object getValoresExtras();

	public DataModelSortBy getSortBy();

}
