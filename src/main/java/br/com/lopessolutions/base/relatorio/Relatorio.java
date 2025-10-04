package br.com.lopessolutions.base.relatorio;

import java.sql.ResultSet;

import br.com.lopessolutions.base.exception.AltException;
import br.com.lopessolutions.base.util.BDAction; 

/**
 * 
 * @author Thaison
 *
 */
public abstract class Relatorio extends BDAction {/*

	private static final long serialVersionUID = 1L;

	private boolean mdh = true, gerado = false;
	private boolean mostrarSemRegistros = false;
	private int numPaginas = 0;

	private ResultSet r;
	private RelatAux re;

	public abstract void dropIfExists(boolean fim) throws Exception;

	public abstract ResultSet getDados() throws Exception;

	public abstract RelatAux getRelatAux() throws Exception;

	// Relatórios com impressão direta -------------------------------
	public void geraRelatorioComImpressao() throws Exception {
		imprimeComImp(new VisPDFNoBrowser());
	}

	private void imprimeComImp(VisRel v) throws Exception {

		dropIfExists(false);

		try {

			r = getDados();

			commit();

			if (r.next() || mostrarSemRegistros) {

				r.beforeFirst();

				re = getRelatAux();
				re.geraRelatorioComImpressao(r, getBanco(), v);

				numPaginas = re.getNumPaginas();
				gerado = true;

			} else {
				gerado = false;
			}

			commit();
			r.close();

		} catch (Exception e) {
			rollback(e);
		}

		dropIfExists(true);

		closeConect();

	}

	// Relatórios sem impressão direta ou com modal -------------------------------
	public void geraRelatorio() throws Exception {
		geraRelatorioSemImp();
	}

	public void imprime() throws Exception {
		visRel(new VisPDFNoBrowser());
	}

	public void download() throws Exception {
		baixaRel(new VisPDFNoBrowser());
	}

	public void visModal(String id) throws Exception {
		visRelModal(new VisPDFNoBrowser(), id);
	}

//	public void visModal() throws Exception {
//		visRelModal(new VisPDFNoBrowser());
//	}

	public void visModalBloc() throws Exception {
		visRelModalBloc(new VisPDFNoBrowser());
	}

	private void geraRelatorioSemImp() throws Exception {

		dropIfExists(false);
		gerado = false;
		re = null;

		try {

			r = getDados();

			commit();

			if (r.next() || mostrarSemRegistros) {

				r.beforeFirst();

				re = getRelatAux();
				re.geraRelatorioSemImp(r, getBanco());

				numPaginas = re.getNumPaginas();
				gerado = true;

			} else {
				gerado = false;
			}

			commit();
			r.close();

		} catch (Exception e) {
			rollback(e);
		}

		dropIfExists(true);

		closeConect();

	}

	public byte[] bytesDoRelatorio() {
		try {

			if (gerado && re != null) {
				return re.getByteRelatorio();
			} else {
				throw new AltException("Relatório vazio.");
			}

		} catch (Exception e) {
			throw new AltException("Erro ao processar relatório. Erro: " + e.getLocalizedMessage());
		}
	}

	private void visRel(VisRel v) throws Exception {

		try {

			if (gerado && re != null) {
				re.visualizarRelatorio(v);
			} else {
				throw new AltException("Relatório vazio.");
			}

		} catch (Exception e) {
			throw new AltException("Erro ao processar relatório. Erro: " + e.getLocalizedMessage());
		}

	}

	private void visRelModal(VisRel v, String id) throws Exception {

		try {

			if (gerado && re != null) {
				re.visualizarRelatorioModal(v, id);
			} else {
				throw new AltException("Relatório vazio.");
			}

		} catch (Exception e) {
			throw new AltException("Erro ao processar relatório. Erro: " + e.getLocalizedMessage());
		}

	}

//	private void visRelModal(VisRel v) throws Exception {
//
//		try {
//
//			if (gerado && re != null) {
//				re.visualizarRelatorioModal(v);
//			} else {
//				throw new AltException("Relatório vazio.");
//			}
//
//		} catch (Exception e) {
//			throw new AltException("Erro ao processar relatório. Erro: " + e.getLocalizedMessage());
//		}
//
//	}

	private void visRelModalBloc(VisRel v) throws Exception {

		try {

			if (gerado && re != null) {
				re.visualizarRelatorioModalBloc(v);
			} else {
				throw new AltException("Relatório vazio.");
			}

		} catch (Exception e) {
			throw new AltException("Erro ao processar relatório. Erro: " + e.getLocalizedMessage());
		}

	}

	private void baixaRel(VisRel v) throws Exception {

		try {

			if (gerado && re != null) {
				re.baixarRelatorio(v);
			} else {
				throw new AltException("Relatório vazio.");
			}

		} catch (Exception e) {
			throw new AltException("Erro ao processar relatório. Erro: " + e.getLocalizedMessage());
		}

	}

	// -----------------------------------------------------------------------------------------------------------
	public void setMdh(boolean mdh) throws Exception {
		this.mdh = mdh;
	}

	public boolean isGerado() {
		return gerado;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setMostrarSemRegistros(boolean mostrarSemRegistros) {
		this.mostrarSemRegistros = mostrarSemRegistros;
	}

	public boolean isMdh() {
		return mdh;
	}
*/
}
