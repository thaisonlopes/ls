package br.com.lopessolutions.base.relatorio;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.lopessolutions.base.exception.AltException;
import br.com.lopessolutions.base.util.Download;
import br.com.lopessolutions.entidades.principal.Parametro;
import br.com.lopessolutions.entidades.principal.Usuario;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;

public class RelatAux {/*

	private JasperPrint impressao = null;
	private byte[] bytesImpressao = null;
	private String arquivo, titulo;
	private EntityManager session;
	private String nomePrefeituraRel = "";
	private String nomeUsuario = "";
	private String codigoUsuario = "";
	private boolean mostrarUsuario = false;
	private boolean mostrarDataHora = false;
	private String nomeRelatorio = "";

	private int codPrefeitura;

	private Map<String, Object> parametros = new HashMap<String, Object>();

	public RelatAux(EntityManager session, String arquivo, String titulo, boolean mdh, String nomeRelatorio,
			Map<String, Object> parametrosAuxiliar) {

		this.codPrefeitura = 1;
		this.session = session;

		this.arquivo = arquivo;
		this.titulo = titulo;
		this.nomeRelatorio = nomeRelatorio;
		inicializaParametros(parametrosAuxiliar);
		poeMDH(mdh);

	}

	private void poeMDH(boolean mdh) {
		parametros.put("mdh", mostrarDataHora);
		parametros.put("mdhNovo", getMDHNovo(mostrarDataHora, mostrarUsuario));
		parametros.put("mdhn", getMDHNovo(mostrarDataHora, mostrarUsuario));
	}

	//// Colocar informação do computador que imprimiu
	private String getMDHNovo(boolean mdh, boolean mdu) {

		String mdhNovo = "NEXUS";

		String codUsr = "";
		String prefeitura = "";
		String dataHora = "";
		String usuario = "";

		codUsr = "U-" + codigoUsuario;

		if (nomePrefeituraRel == null || nomePrefeituraRel.trim().length() == 0) {
			prefeitura = TextoUtil.completaADireita(" ", 20, " ") + "NEXUS";
		} else {
			prefeitura = TextoUtil.completaADireita(" ", 20, " ") + nomePrefeituraRel + "  ";
		}

		if (mdh == true) {
			// 04 de Setembro de 2013 - 15:38:20
			SimpleDateFormat sdf = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH:mm:ss ");
			dataHora = sdf.format(new java.util.Date());

		} else {
			dataHora = "";
		}

		if (mdu == true) {

			usuario = "  Usuário: " + nomeUsuario;

		} else {
			usuario = "";
		}

		mdhNovo = codUsr + prefeitura + dataHora + usuario;

		return mdhNovo;

	}

	private void inicializaParametros(Map<String, Object> parametrosAuxiliar) {

		iniciarDadosDeAcesso();

		parametros.put("dir", getCaminhoSub(""));
		parametros.put("SUBREPORT_DIR", getCaminhoSub(""));
		parametros.put("subRelat", getCaminhoSub(""));
		parametros.put("REPORT_PATH", getCaminhoSub(""));

		Parametro p = session.find(Parametro.class, codPrefeitura);
		if (p == null) {
			throw new AltException("Parâmetro da entidade não encontrado.");
		}

		nomePrefeituraRel = p.getEntidade();
		String cnpjEntidade = p.getCnpj();
		parametros.put("CNPJ_ENT", cnpjEntidade);
		parametros.put("entidade", nomePrefeituraRel);
		parametros.put("prefeitura", nomePrefeituraRel);
		parametros.put("BRASAO", getBrasao(p));

		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

		if (parametrosAuxiliar != null && !parametrosAuxiliar.isEmpty()) {
			for (Map.Entry<String, Object> entry : parametrosAuxiliar.entrySet()) {
				String chave = entry.getKey();
				Object valor = entry.getValue();

				// Aqui você pode fazer algum processamento se quiser
				parametros.put(chave, valor);
			}
		}
	}

	public void geraRelatorioComImpressao(ResultSet result, Statement banco, VisRel v) throws Exception {

		poeParametroConexao(banco);
		inicializaImpressao(result, banco);

		if (impressao == null) {
			String msg = "Impressão nula.";
			throw new AltException(msg);
		}

		v.visRel(impressao, titulo);
	}

	public void geraRelatorioSemImp(ResultSet result, Statement banco) throws Exception {

		poeParametroConexao(banco);
		inicializaImpressao(result, banco);

		if (impressao == null) {
			String msg = "Impressão nula.";
			throw new AltException(msg);
		}

		bytesImpressao = JasperExportManager.exportReportToPdf(impressao);

		if (bytesImpressao == null || bytesImpressao.length <= 0) {
			String msg = "Impressão nula.";
			throw new AltException(msg);
		}

		Download.baixRel(bytesImpressao, this.nomeRelatorio, Download.PDF);

	}

	public void visualizarRelatorioModal(VisRel v) throws Exception {
		v.visRelModal(bytesImpressao, titulo);
	}

	public void visualizarRelatorioModalBloc(VisRel v) throws Exception {
		v.visRelModalBloc(bytesImpressao, titulo);
	}

	public void visualizarRelatorioModal(VisRel v, String id) throws Exception {
		v.visRelModal(bytesImpressao, titulo, id);
	}

	public void visualizarRelatorio(VisRel v) throws Exception {
		v.visRel(bytesImpressao, titulo);
	}

	public void baixarRelatorio(VisRel v) throws Exception {
		v.baixRel(bytesImpressao, titulo);
	}

	private void poeParametroConexao(Statement banco) throws Exception {
		poeParametro("conexao", banco.getConnection());
		poeParametro("REPORT_CONNECTION", banco.getConnection());
	}

	private void inicializaImpressao(ResultSet result, Statement banco) throws Exception {

		URL file = getURL(arquivo);

		if (file == null) {
			throw new AltException("Arquivo Inexistente.");
		}

		JRResultSetDataSource jr = new JRResultSetDataSource(result);

		try (InputStream inputStream = file.openStream()) {
			if (inputStream.available() == 0) {
				throw new AltException("O InputStream está vazio. Verifique o caminho do arquivo.");
			}
		}

		impressao = JasperFillManager.fillReport(file.openStream(), parametros, jr);
	}

	public URL getURL(String arquivo) throws Exception {

		String arq = getCaminhoCompleto(arquivo);

		URL file = getClass().getClassLoader().getResource(arq);

		int tipoEntidade = 0;

		tipoEntidade = getTipoEntidade();

		if (tipoEntidade != 0) {
			if (!isCaminhoCompleto(arquivo)) {
				arq = "/br/com/nexus/relatorios/jasper/" + arquivo;
			}
			file = getClass().getClassLoader().getResource(arq);

			if (file == null) {
				arq = getCaminhoCompleto(arquivo);
				file = getClass().getClassLoader().getResource(arq);
			}
		}

		return file;
	}

	public void poeParametros(Map<String, Object> map) {
		parametros.putAll(map);
	}

	public void poeParametro(String chave, Object parametro) throws Exception {
		parametros.put(chave, parametro);
	}

	public void poeSubRelatorio(String chave, String subRelatorio) throws Exception {
		parametros.put(chave, getCaminhoSub(subRelatorio));
	}

	public static void poeSubRelatorio(HashMap<String, Object> parametros, String chave, String subRelatorio)
			throws Exception {
		parametros.put(chave, getCaminhoSub(subRelatorio));
	}

	private static String getCaminhoSub(String arquivo) {
		return getCaminhoCompleto(arquivo);
	}

	private static String getCaminhoCompleto(String arquivo) {
		if (isCaminhoCompleto(arquivo)) {
			return arquivo;
		} else {
			String arq = "/br/com/nexus/relatorios/jasper/" + arquivo;
			return arq;
		}
	}

	private static boolean isCaminhoCompleto(String arquivo) {
		return arquivo.contains("/") || arquivo.contains("\\");
	}

	private int getTipoEntidade() throws Exception {
		return 1;
	}

	public int getNumPaginas() {
		int numPag = 0;
		List<JRPrintPage> list = impressao.getPages();
		if (list != null) {
			numPag = list.size();
		}
		return numPag;
	}

	// ------------------------------------------------------------------------------------------------
	private void iniciarDadosDeAcesso() {
		try {

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);

			Object obU = session.getAttribute("UsuarioSessao");
			if (obU != null && obU instanceof Usuario) {
				Usuario u = (Usuario) obU;
				nomeUsuario = u.getNome();
				codigoUsuario = "" + u.getCodigo();
			} else {
				nomeUsuario = "";
				codigoUsuario = "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public byte[] getByteRelatorio() {
		return bytesImpressao;
	}

	private ByteArrayInputStream getBrasao(Parametro parametro) {
		if (parametro == null) {
			return null;
		} else {
			ByteArrayInputStream b = new ByteArrayInputStream(parametro.getBrasao());
			return b;
		}
	}
*/
}
