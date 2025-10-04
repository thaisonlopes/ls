package br.com.lopessolutions.acesso.dao.tipoAcesso;

import java.util.List;

import br.com.lopessolutions.acesso.rne.TipoAcessoRNE;
import br.com.lopessolutions.entidades.principal.TipoAcesso;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@Stateless
public class TipoAcessoDAO extends TipoAcessoRNE {

	private static final long serialVersionUID = -8252386464200248615L;

	@Inject
	private EntityManager session;

	public EntityManager getSession() {
		this.session.joinTransaction();
		return this.session;
	}

	public TipoAcesso salvar(String tela, TipoAcesso t) {

		EntityManager session = getSession();
		

		try {

			super.setTela(tela);

			t = super.salvar(session, t);

			commitSalva(t, session);
			return t;

		} catch (Exception e) {
			rollback(e);
		}
		return null;
	}

	public void delete(String tela, TipoAcesso t) {

		EntityManager session = getSession();

		try {

			super.setTela(tela);

			super.delete(session, t);

			commitDelete(t, session);

		} catch (Exception e) {
			rollback(e);
		}

	}

	public TipoAcesso getPorCodigo(int codigo) {
		return super.getPorCodigo(getSession(), codigo);
	}

	public List<TipoAcesso> getLista() {
		return super.getLista(getSession());
	}

	public List<TipoAcesso> getLista(int codigo) {
		return super.getLista(getSession(), codigo);
	}

}
