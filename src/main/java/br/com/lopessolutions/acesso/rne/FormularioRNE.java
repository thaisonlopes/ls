package br.com.lopessolutions.acesso.rne;

import java.io.Serializable;

import br.com.lopessolutions.base.rne.GenericRNE;
import br.com.lopessolutions.entidades.principal.Formulario;
import br.com.lopessolutions.entidades.principal.Log;
import jakarta.persistence.EntityManager;


/**
 * 
 * @author Thaison
 *
 */
public class FormularioRNE extends GenericRNE {

	private static final long serialVersionUID = 4495872082440424059L;

	@Override
	protected void setLog(Log log, Serializable pojo) {
		Formulario t = (Formulario) pojo;
		log.setC1(t.getCodigo().toString());
	}

	public Formulario addFormulario(EntityManager session, Formulario f) {
		return super.saveOrUpdatePojo(session, f);
	}

	public boolean addFormulario(EntityManager session, String formulario, String titulo) {

		if (formulario != null && formulario.trim().length() > 0) {
			String sql = "select e from Formulario e where e.formulario=?1";

			Formulario f = super.getPojoUnique(session, Formulario.class, sql, formulario.trim());

			if (f == null) {
				f = new Formulario();
				f.setFormulario(formulario.trim());
			}

			f.setTitulo(titulo.trim());
			f = super.saveOrUpdatePojo(session, f);

			return f != null;
		}
		return false;
	}

	public void delete(EntityManager session, Formulario f) {
		super.deletePojo(f, session);
	}

	public Formulario getFormulario(EntityManager session, int codFormulario) {
		String sql = "select e from Formulario e where e.codigo=?1";
		return super.getPojoUnique(session, Formulario.class, sql, codFormulario);
	}

	public Formulario getFormulario(EntityManager session, String formulario, String titulo) {

		if (formulario != null && formulario.trim().length() > 0) {
			String sql = "select e from Formulario e where e.formulario=?1";

			Formulario f = super.getPojoUnique(session, Formulario.class, sql, formulario.trim());

			if (f == null) {
				f = new Formulario();
				f.setFormulario(formulario.trim());
				f.setTitulo(titulo.trim());
				f = super.saveOrUpdatePojo(session, f);
			}

			return f;
		}

		return null;

	}

	public Formulario getFormulario(EntityManager session, String formulario) {
		String sql = "select e from Formulario e where e.formulario=?1";
		return super.getPojoUnique(session, Formulario.class, sql, formulario.trim());
	}

}
