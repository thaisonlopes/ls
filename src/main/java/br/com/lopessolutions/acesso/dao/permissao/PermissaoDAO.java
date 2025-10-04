package br.com.lopessolutions.acesso.dao.permissao;

import java.util.List;

import br.com.lopessolutions.acesso.rne.PermissaoRNE;
import br.com.lopessolutions.entidades.principal.Permissao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@Stateless
public class PermissaoDAO extends PermissaoRNE {

	private static final long serialVersionUID = -6565371229735636081L;

	@Inject
	private EntityManager session;

	public EntityManager getSession() {
		this.session.joinTransaction();
		return this.session;
	}

	public void salvar(String tela, Permissao t) {

		EntityManager session = getSession();

		try {

			super.setTela(tela);

			t = super.salvar(session, t);

			commitSalva(t, session);

		} catch (Exception e) {
			rollback(e);
		}

	}

	public void delete(String tela, Permissao t) {

		EntityManager session = getSession();

		try {

			super.setTela(tela);

			super.delete(session, t);

			commitDelete(t, session);

		} catch (Exception e) {
			rollback(e);
		}

	}

	public Permissao getPermissao(int codigo) {
		return super.getPermissao(getSession(), codigo);
	}

	public Permissao getPorUsuarioFormulario(long idUsuario, String formulario) {
		return super.getPorUsuarioFormulario(getSession(), idUsuario, formulario);
	}

	public Permissao getPorCodUsuarioFormulario(int codUsuario, String formulario) {
		return super.getPorCodUsuarioFormulario(getSession(), codUsuario, formulario);
	}

	public List<Permissao> getListaPermissaoUsuario(long codUsuario) {
		return super.getListaPermissaoUsuario(getSession(), codUsuario);
	}

	public List<Permissao> getList(String sql, Object... parametros) {
		return super.getList(getSession(), sql, parametros);
	}

	public List<?> getListNativeQuery(String sql, Object... parametros) {
		return super.getListNativeQuery(session, sql, parametros);
	}

}
