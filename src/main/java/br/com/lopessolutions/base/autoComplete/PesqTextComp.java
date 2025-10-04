package br.com.lopessolutions.base.autoComplete;

import java.io.Serializable;
import java.util.List;

import org.primefaces.event.SelectEvent;

import br.com.lopessolutions.base.util.MessageBean;

public abstract class PesqTextComp extends MessageBean {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 6356843178613172716L;

	private String sql;

	private AutoCompleteDAO daoAC;

	private int maxResults = 15;

	public PesqTextComp(AutoCompleteDAO daoAC) {
		super();
		this.daoAC = daoAC;
	}

	protected abstract void busca();

	protected abstract void selectAutoComplete(SelectEvent<Object> event);

	protected abstract <T extends Serializable> List<T> completeMethod(String desc);

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	protected <T extends Serializable> List<T> getLista(Class<T> classToSearch, Object... parametros) {
		return daoAC.getList(classToSearch, getSql(), maxResults, parametros);
	}

	protected List<?> getLista(Object... parametros) {
		return daoAC.getList(getSql(), maxResults, parametros);
	}

	protected String like(String comp) {
		if (comp != null) {
			return "" + comp.trim().toUpperCase() + "%";
		} else {
			return "%";
		}
	}

}
