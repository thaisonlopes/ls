package br.com.lopessolutions.base.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.primefaces.PrimeFaces;

import br.com.lopessolutions.acesso.util.LoginUsuario;
import br.com.lopessolutions.base.exception.AltException;
import br.com.lopessolutions.base.relatorio.Relatorio;
import br.com.lopessolutions.base.relatorio.TabelaRelatorios;
import br.com.lopessolutions.base.relatorio.VisPDFNoBrowser;
import br.com.lopessolutions.base.util.atualizacao.AtDivSemRel;
import br.com.lopessolutions.entidades.principal.Usuario;
import br.com.util.base.TextoUtil;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;

/**
 * 
 * @author Thaison
 *
 */
public class MessageBean implements Serializable {

	private static final long serialVersionUID = 5689315723691133885L;

	public void setMensagemERRO(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", msg));
	}

	public void setMensagemERRO(Throwable erro) {
		if (erro != null) {
			erro.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, erro.getLocalizedMessage(), ""));
		}
	}

	public void setMensagemFATAL(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", msg));
	}

	public void setMensagemWARN(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", msg));
	}

	public void setMensagemINFO(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
	}

	public void mensagemFim() {
		setMensagemINFO("Operação realizada com sucesso!");
	}

	public Usuario getUsuarioLogado() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			Object ob = session.getAttribute("UsuarioLogado");

			if (ob != null && ob instanceof LoginUsuario) {
				LoginUsuario us = (LoginUsuario) ob;
				return us.getUsuario();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer getPermissaoUsuarioLogado() {
		try {

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			Object ob = session.getAttribute("UsuarioLogado");

			if (ob != null && ob instanceof LoginUsuario) {
				LoginUsuario lu = (LoginUsuario) ob;
				if (lu.getUsuario() != null && lu.getUsuario().getTipoPermissao() != null) {
					return lu.getUsuario().getTipoPermissao().getCodigo();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Integer getCodUsuarioLogado() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			Object ob = session.getAttribute("UsuarioLogado");

			if (ob != null && ob instanceof LoginUsuario) {
				LoginUsuario lu = (LoginUsuario) ob;
				if (lu.getUsuario() != null && lu.getUsuario().getTipoAcesso() != null) {
					return lu.getUsuario().getCodigo();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getNomeUsuarioLogado() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			Object ob = session.getAttribute("UsuarioLogado");

			if (ob != null && ob instanceof LoginUsuario) {
				LoginUsuario lu = (LoginUsuario) ob;
				if (lu.getUsuario() != null && lu.getUsuario().getTipoAcesso() != null) {
					return lu.getUsuario().getNome();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public int getObjInt(Object[] o, int p) {
		if (o[p] != null) {
			return getIntOB(o[p]);
		} else {
			return 0;
		}
	}

	public String getObjText(Object[] o, int p) {
		if (o[p] != null) {
			return o[p].toString();
		} else {
			return "";
		}
	}

	public BigDecimal getObjBig(Object[] o, int p) {
		if (o[p] != null) {

			if (o[p] instanceof BigDecimal) {
				return (BigDecimal) o[p];
			} else if (o[p] instanceof Double) {
				return BigDecimal.valueOf((Double) o[p]);
			} else {
				return (BigDecimal) o[p];
			}

		} else {
			return BigDecimal.ZERO;
		}
	}

	public java.util.Date getObjDate(Object[] o, int p) {
		if (o[p] != null) {
			return (java.util.Date) o[p];
		} else {
			return null;
		}
	}

	public Long getObjLong(Object[] o, int p) {
		if (o[p] != null) {
			return getLongOB(o[p]);
		} else {
			return 0l;
		}
	}

	public Boolean getObjBoolean(Object[] o, int p) {
		if (o[p] != null) {
			return (Boolean) o[p];
		} else {
			return null;
		}
	}

	public Integer getAnoExercicioAcesso() {
		try {

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			Object ob = session.getAttribute("AnoSessao");

			if (ob != null) {
				Integer ano = (Integer) ob;

				return ano;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Integer getNivelAcessoUsuario() {
		try {

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			Object ob = session.getAttribute("UsuarioLogado");

			if (ob != null && ob instanceof LoginUsuario) {
				LoginUsuario lu = (LoginUsuario) ob;
				if (lu.getUsuario() != null && lu.getUsuario().getTipoAcesso() != null) {
					return lu.getUsuario().getTipoAcesso().getCodigo();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Integer getPermissaoUsuarioAcesso() {
		try {

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			Object ob = session.getAttribute("UsuarioLogado");

			if (ob != null && ob instanceof LoginUsuario) {
				LoginUsuario lu = (LoginUsuario) ob;
				if (lu.getUsuario() != null && lu.getUsuario().getTipoPermissao() != null) {
					return lu.getUsuario().getTipoPermissao().getCodigo();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getInt(Integer v) {
		if (v == null) {
			return 0;
		} else {
			return v;
		}
	}

	public int getIntN(Integer v) {
		if (v == null) {
			return -1;
		} else {
			return v;
		}
	}

	public double getDouble(Double v) {
		if (v == null) {
			return 0.0;
		} else {
			return v;
		}
	}

	public double getDouble(BigDecimal v) {
		if (v == null) {
			return 0.0;
		} else {
			return v.doubleValue();
		}
	}

	public long getLong(Long v) {
		if (v == null) {
			return 0l;
		} else {
			return v;
		}
	}

	public long getLongOB(Object v) {
		if (v == null) {
			return 0l;
		} else if (v instanceof BigInteger) {
			return ((BigInteger) v).longValue();
		} else if (v instanceof Long) {
			return (long) v;
		} else if (v instanceof Integer) {
			return (Integer) v;
		} else {
			return (long) v;
		}
	}

	public int getIntOB(Object v) {
		if (v == null) {
			return 0;
		} else if (v instanceof BigInteger) {
			return ((BigInteger) v).intValue();
		} else if (v instanceof Long) {
			return ((Long) v).intValue();
		} else if (v instanceof Integer) {
			return (Integer) v;
		} else {
			return (int) v;
		}
	}

	public BigDecimal getBigDec(Object v) {
		if (v == null) {
			return BigDecimal.ZERO;
		} else if (v instanceof BigDecimal) {
			return (BigDecimal) v;
		} else if (v instanceof Double) {
			return BigDecimal.valueOf((double) v);
		} else {
			return (BigDecimal) v;
		}
	}

	public BigDecimal getDec(BigDecimal v) {
		if (v == null) {
			return BigDecimal.ZERO;
		} else {
			return v;
		}
	}

	public java.sql.Date getDateSQL(java.util.Date data) {
		if (data == null) {
			return null;
		} else {
			return DataUtil.getDateSQL(data);
		}
	}

	public String getText(String v) {
		if (v == null) {
			return "";
		} else {
			return v.trim();
		}
	}

	public String getTextOB(Object v) {
		if (v == null) {
			return null;
		} else {
			return v.toString().trim();
		}
	}

	public boolean getBoolean(Object o) {
		if (o != null) {
			return (Boolean) o;
		} else {
			return false;
		}
	}

	public boolean getBoolean(Boolean o) {
		if (o != null) {
			return o;
		} else {
			return false;
		}
	}

	/**
	 * Não funciona para botões
	 * 
	 * @param id
	 */
	public void setPFoco(String id) {
		if (id != null) {
			PrimeFaces.current().focus(id);
		}
	}

	public void updateCP(String update) {
		if (update != null) {
			PrimeFaces.current().ajax().update(update);
		}
	}

	public void executeScript(String script) {
		if (script != null) {
			PrimeFaces.current().executeScript(script);
		}
	}

	public void abrirDialog(String caminho) {
		if (caminho != null) {
			PrimeFaces.current().dialog().openDynamic(caminho);
		}
	}

	public String getIntComp(String obj, int tamanho) {
		return TextoUtil.completaAEsquerda(obj, tamanho, "0");
	}

	// Relatorio ------------------------------------------------------
	/*public void showRelatorio(Relatorio r) throws Exception {

		r.geraRelatorio();

		if (!r.isGerado()) {
			throw new AltException("Não foi possível gerar um relatório válido. Verifique os dados e tente novamente.");
		}

		//r.visModal();

	}

	public boolean showRelatorioVC(Relatorio r) throws Exception {

		r.geraRelatorio();

		if (r.isGerado()) {
			//r.visModal();
			return true;
		}

		return false;

	}

	public boolean showRelatorio(Relatorio r, boolean imp) throws Exception {

		if (imp) {

//			r.geraRelatorio();

			if (r.isGerado()) {
				//r.visModal();
				return true;
			}

		} else {
			r.closeConect();
		}

		return false;

	}

	public boolean showRelatorio(List<TabelaRelatorios> l, int id, String titulo, Relatorio r, boolean imp)
			throws Exception {

		if (imp) {

			r.geraRelatorio();

			if (r.isGerado()) {

				TabelaRelatorios t = new TabelaRelatorios();

				t.setId(id);
				t.setDescricao(titulo);
				t.setRelatorio(r);

				l.add(t);

				return true;
			}

		} else {
			r.closeConect();
		}

		return false;

	}

	public void showRelatorio(byte[] relatorio, String titulo) throws Exception {

		if (relatorio == null) {
			throw new AltException("Não foi possível gerar um relatório válido. Verifique os dados e tente novamente.");
		}

		new VisPDFNoBrowser().visRelModal(relatorio, titulo);
	}

	public boolean executar(String tela, AtDivSemRel at, boolean check) throws Exception {
		try {

			if (getBoolean(check)) {
				at.setTela(tela);
				at.setItemTela(tela);
				at.atualiza();
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			at.closeConect();
		}

		return false;
	}

	public boolean executar(List<TabelaRelatorios> l, int id, String titulo, Relatorio r, boolean check)
			throws Exception {
		try {

			if (check) {

				r.geraRelatorio();

				if (r.isGerado()) {

					TabelaRelatorios t = new TabelaRelatorios();

					t.setId(id);
					t.setDescricao(titulo);
					t.setRelatorio(r);

					l.add(t);

					return true;
				}

			} else {
				r.closeConect();
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			r.closeConect();
		}

		return false;
	}*/

}
