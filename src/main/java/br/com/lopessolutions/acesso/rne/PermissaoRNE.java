package br.com.lopessolutions.acesso.rne;

import java.io.Serializable;
import java.util.List;

import br.com.lopessolutions.base.rne.GenericRNE;
import br.com.lopessolutions.entidades.principal.Log;
import br.com.lopessolutions.entidades.principal.Permissao;
import jakarta.persistence.EntityManager;

public class PermissaoRNE extends GenericRNE {

	private static final long serialVersionUID = -1358795277123605038L;

	@Override
	protected void setLog(Log log, Serializable pojo) {
		Permissao t = (Permissao) pojo;
		log.setC1(t.getCodigo() + "");
		log.setC2(t.getUsuario().getCodigo().toString());
		log.setC3(t.getFormulario().getCodigo().toString());
	}

	public Permissao salvar(EntityManager session, Permissao t) {
		t = session.merge(t);
		return t;
	}

	public void delete(EntityManager session, Permissao t) {
		deletePojo(t, session);
	}

	public Permissao getPermissao(EntityManager session, int codigo) {
		String q = " SELECT t FROM Permissao t WHERE t.codigo=?1";
		return getPojoUnique(session, Permissao.class, q, codigo);
	}

	public Permissao getPorUsuarioFormulario(EntityManager session, long idUsuarioEntidade, String formulario) {
		String q = " SELECT e FROM Permissao e WHERE e.usuario.codigo =?1 AND e.formulario.formulario=?2";
		return getPojoUnique(session, Permissao.class, q, idUsuarioEntidade, formulario);
	}

	public Permissao getPorCodUsuarioFormulario(EntityManager session, int codUsuario, String formulario) {
		String q = " SELECT e FROM Permissao e WHERE e.usuario.codigo=?1 AND e.formulario.formulario =?3";
		return getPojoUnique(session, Permissao.class, q, codUsuario, formulario);
	}

	public List<Permissao> getListaPermissaoUsuario(EntityManager session, long codUsuario) {
		String sql = "SELECT e FROM Permissao e WHERE e.usuario.codigo =?1";
		return getPureList(session, Permissao.class, sql, codUsuario);
	}

	public List<Permissao> getList(EntityManager session, String sql, Object... parametros) {
		return getPureList(session, Permissao.class, sql, parametros);
	}

}
