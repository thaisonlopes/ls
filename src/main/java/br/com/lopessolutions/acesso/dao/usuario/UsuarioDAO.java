package br.com.lopessolutions.acesso.dao.usuario;

import java.util.List;

import br.com.lopessolutions.acesso.rne.UsuarioRNE;
import br.com.lopessolutions.entidades.principal.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@Stateless
public class UsuarioDAO extends UsuarioRNE {

	private static final long serialVersionUID = -3570800836229180404L;

	@Inject
	private EntityManager session;

	public EntityManager getSession() {
		this.session.joinTransaction();
		return this.session;
	}

	public Usuario salvar(String tela, Usuario t) {

		EntityManager session = getSession();

		try {

			super.setTela(tela);

			t = super.salvar(session, t);

			session.flush();

			commitSalva(t, session);

		} catch (Exception e) {
			rollback(e);
		}

		return t;

	}

	public Usuario saveOrUpdatePojo(String tela, Usuario c) {
		EntityManager session = getSession();
		try {
			super.setTela(tela);

			c = super.saveOrUpdatePojo(session, c);

			gerarLogSalvar(c, session);

		} catch (Exception e) {
			rollback(e);
		}
		return c;
	}

	public void delete(String tela, Usuario t) {

		EntityManager session = getSession();

		try {

			super.setTela(tela);

			super.delete(session, t);

			commitDelete(t, session);

		} catch (Exception e) {
			rollback(e);
		}

	}

	public Usuario getPorCodigo(int codUsuario) {
		EntityManager session = getSession();
		return super.getPorCodigo(session, codUsuario);
	}

	public Usuario getPorNivelCodigo(int nvAcesso, int nvPermissao, int codUsuario) {
		EntityManager session = getSession();
		return super.getPorNivelCodigo(session, nvAcesso, nvPermissao, codUsuario);
	}

	public Usuario getPorCPF(String cpf) {
		EntityManager session = getSession();
		return super.getPorCPF(session, cpf);
	}

	public List<Usuario> getList() {
		EntityManager session = getSession();
		return super.getList(session);
	}

	public List<Usuario> getListPorNivel(int nvAcesso, int nvPermissao) {
		EntityManager session = getSession();
		return super.getListPorNivel(session, nvAcesso, nvPermissao);
	}

	public int getCount() {
		EntityManager session = getSession();
		return super.getCount(session);
	}

	public List<Usuario> getListHabilitado(Boolean habilitado) {
		return super.getListHabilitado(session, habilitado);
	}

	public List<Usuario> getListaAdministradores() {
		EntityManager session = getSession();
		return super.getListaAdministradores(session);
	}

	public List<Usuario> getList(String sql, Object... parametros) {
		EntityManager session = getSession();
		return super.getList(session, sql, parametros);
	}

	public List<Usuario> getList(int nrRegistros, String sql, Object... parametros) {
		EntityManager session = getSession();
		return super.getList(session, nrRegistros, sql, parametros);
	}

}
