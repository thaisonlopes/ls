package br.com.lopessolutions.acesso.rne;

import java.io.Serializable;
import java.util.List;

import br.com.lopessolutions.base.rne.GenericRNE;
import br.com.lopessolutions.entidades.principal.Log;
import br.com.lopessolutions.entidades.principal.TipoAcesso;
import jakarta.persistence.EntityManager;

public class TipoAcessoRNE extends GenericRNE {

	private static final long serialVersionUID = 503714104084634569L;

	@Override
	protected void setLog(Log log, Serializable pojo) {
		br.com.lopessolutions.entidades.principal.TipoAcesso t = (TipoAcesso) pojo;
		log.setC1(t.getCodigo().toString());
	}

	public TipoAcesso salvar(EntityManager session, TipoAcesso t) {
		t = session.merge(t);
		return t;
	}

	public void delete(EntityManager session, TipoAcesso t) {
		deletePojo(t, session);
	}

	public TipoAcesso getPorCodigo(EntityManager session, int codigo) {
		String q = " SELECT t FROM TipoAcesso t WHERE t.codigo=?1";
		return getPojoUnique(session, TipoAcesso.class, q, codigo);
	}

	public List<TipoAcesso> getLista(EntityManager session) {
		String q = " SELECT t FROM TipoAcesso t ORDER BY t.codigo";
		return getPureList(session, TipoAcesso.class, q);
	}

	public List<TipoAcesso> getLista(EntityManager session, int codigo) {
		String q = " SELECT t FROM TipoAcesso t WHERE t.codigo <=?1 ORDER BY t.codigo";
		return getPureList(session, TipoAcesso.class, q, codigo);
	}

}
