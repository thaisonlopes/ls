package br.com.lopessolutions.acesso.dao.formulario;

import br.com.lopessolutions.acesso.rne.FormularioRNE;
import br.com.lopessolutions.entidades.principal.Formulario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

/**
 * 
 * @author Thaison
 *
 */
@Stateless
public class FormularioDAO extends FormularioRNE {

	private static final long serialVersionUID = -7906874732129070778L;

	@Inject
	private EntityManager session;

	public EntityManager getSession() {
		this.session.joinTransaction();
		return this.session;
	}

	public Formulario addFormulario(String tela, Formulario t) {

		EntityManager session = getSession();

		try {

			super.setTela(tela);

			t = super.addFormulario(session, t);

			commitSalva(t, session);

		} catch (Exception e) {
			rollback(e);
		}
		return t;
	}

	public boolean addFormulario(String tela, String formulario, String titulo) {

		EntityManager session = getSession();
		boolean t = false;

		try {

			super.setTela(tela);

			t = super.addFormulario(session, formulario, titulo);

			commitSalva(t, session);

		} catch (Exception e) {
			rollback(e);
		}
		return t;
	}

	public void delete(String tela, Formulario t) {

		EntityManager session = getSession();

		try {

			super.setTela(tela);

			super.delete(session, t);

			commitDelete(t, session);

		} catch (Exception e) {
			rollback(e);
		}

	}

	public Formulario getFormulario(int codFormulario) {
		return super.getFormulario(getSession(), codFormulario);
	}

	public Formulario getFormulario(String formulario) {
		return super.getFormulario(getSession(), formulario);
	}

}
