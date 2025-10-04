package br.com.lopessolutions.base.dataModel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

/**
 * 
 * @author Thaison
 *
 */
public class GenericoDataModel extends LazyDataModelGE<TabelaGenerica> {

	private static final long serialVersionUID = -2508293113169877914L;

	private DataModelPesquisa dao;

	private List<TabelaGenerica> datasource;

	public GenericoDataModel(DataModelPesquisa dao) {
		this.dao = dao;
	}

	public void reiniciar(DataModelPesquisa dao) {
		this.dao = dao;
	}

	@Override
	public TabelaGenerica getRowData(String rowKey) {
		if (rowKey != null && datasource != null && !datasource.isEmpty()) {
			for (TabelaGenerica customer : datasource) {
				if (customer.getId() == Integer.parseInt(rowKey)) {
					return customer;
				}
			}
		}

		return null;
	}

	@Override
	public String getRowKey(TabelaGenerica customer) {
		return String.valueOf(customer.getId());
	}

	@Override
	public List<TabelaGenerica> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		try {

			this.setRowCount(dao.getQuantidadeRegistroDM());
			datasource = TabelaGenerica.getLista(dao.getLista(first, pageSize));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return datasource;
	}

	@Override
	public int getQuantidadeRegistroDM() {
		return dao.getQuantidadeRegistroDM();
	}

	@Override
	public Object getValoresExtras() {
		return dao.getValoresExtras();
	}

	@Override
	public DataModelSortBy getSortBy() {
		return dao.getSortBy();
	}

	@Override
	public int count(Map<String, FilterMeta> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
