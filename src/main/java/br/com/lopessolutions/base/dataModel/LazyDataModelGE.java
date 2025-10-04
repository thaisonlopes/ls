package br.com.lopessolutions.base.dataModel;

import org.primefaces.model.LazyDataModel;

/**
 * 
 * @author Thaison
 *
 * @param <T>
 */
public abstract class LazyDataModelGE<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;

	public int getQuantidadeRegistroDM() {
		return 0;
	}

	public Object getValoresExtras() {
		return null;
	}

	public DataModelSortBy getSortBy() {
		return new DataModelSortBy(0);
	};

}
