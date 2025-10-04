package br.com.lopessolutions.acesso.dao.permissao;

import java.util.List;

import br.com.lopessolutions.acesso.rne.TipoPermissaoRNE;
import br.com.lopessolutions.entidades.principal.TipoPermissao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;



@Stateless
public class TipoPermissaoDAO extends TipoPermissaoRNE {

	private static final long serialVersionUID = 2227880585852040344L;

	@Inject
	private EntityManager session;

	public EntityManager getSession() {
		this.session.joinTransaction();
		return this.session;
	}

	public void salvar(String tela, TipoPermissao t) {

		EntityManager session = getSession();

		try {

			super.setTela(tela);

			t = super.salvar(session, t);

			commitSalva(t, session);

		} catch (Exception e) {
			rollback(e);
		}

	}

	public void delete(String tela, TipoPermissao t) {

		EntityManager session = getSession();

		try {

			super.setTela(tela);

			super.delete(session, t);

			commitDelete(t, session);

		} catch (Exception e) {
			rollback(e);
		}

	}

	public TipoPermissao getPorCodigo(int codigo) {
		return super.getPorCodigo(getSession(), codigo);
	}

	public List<TipoPermissao> getList() {
		return super.getList(getSession());
	}

	public List<TipoPermissao> getList(int codigo) {
		return super.getList(getSession(), codigo);
	}

}
