package br.com.lopessolutions.acesso.rne;

import java.io.Serializable;
import java.util.List;

import br.com.lopessolutions.base.rne.GenericRNE;
import br.com.lopessolutions.entidades.principal.Log;
import br.com.lopessolutions.entidades.principal.TipoPermissao;
import jakarta.persistence.EntityManager;

/**
 * 
 * @author Thaison
 *
 */
public class TipoPermissaoRNE extends GenericRNE {

	private static final long serialVersionUID = -5529991918312952376L;

	@Override
	protected void setLog(Log log, Serializable pojo) {
		TipoPermissao t = (TipoPermissao) pojo;
		log.setC1(t.getCodigo().toString());
	}

	public TipoPermissao salvar(EntityManager session, TipoPermissao t) {
		t = session.merge(t);
		return t;
	}

	public void delete(EntityManager session, TipoPermissao t) {
		deletePojo(t, session);
	}

	public TipoPermissao getPorCodigo(EntityManager session, int codigo) {
		String q = " SELECT t FROM TipoPermissao t WHERE t.codigo=?1";
		return getPojoUnique(session, TipoPermissao.class, q, codigo);
	}

	public List<TipoPermissao> getList(EntityManager session) {
		String q = " SELECT t FROM TipoPermissao t ORDER BY t.codigo";
		return getPureList(session, TipoPermissao.class, q);
	}

	public List<TipoPermissao> getList(EntityManager session, int codigo) {
		String q = " SELECT t FROM TipoPermissao t WHERE t.codigo <= ?1 ORDER BY t.codigo";
		return getPureList(session, TipoPermissao.class, q, codigo);
	}

}
