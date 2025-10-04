package br.com.lopessolutions.acesso.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.lopessolutions.acesso.dao.tipoAcesso.TipoAcessoDAO;
import br.com.lopessolutions.acesso.dao.usuario.UsuarioDAO;
import br.com.lopessolutions.acesso.dto.BancoDTO;
import br.com.lopessolutions.acesso.util.AmbienteDesenvolvimento;
import br.com.lopessolutions.acesso.util.LoginUsuario;
import br.com.lopessolutions.acesso.util.Utils;
import br.com.lopessolutions.base.util.MessageBean;
import br.com.lopessolutions.entidades.principal.Usuario;
import br.com.util.base.TextoUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Named("loginBean")
@ViewScoped
public class LoginBean extends MessageBean {

	private static final long serialVersionUID = 2085438637906741757L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	TipoAcessoDAO tipoAcessoDAO;

	private String dominio;
	private BancoDTO bancoDTO = new BancoDTO();
	private String cpf = "";
	private String senha = "";
	private Integer idBanco = 0;

	private ExternalContext context;
	private HttpServletRequest request;

	private List<BancoDTO> listaBancos = new ArrayList<BancoDTO>();

	public BancoDTO getBancoDTO() {
		return bancoDTO;
	}

	public void setBancoDTO(BancoDTO bancoDTO) {
		this.bancoDTO = bancoDTO;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCpfFormatado() {
		return cpf.replace(".", "").replace("-", "");
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public List<BancoDTO> getListaBancos() {
		return listaBancos;
	}

	public void setListaBancos(List<BancoDTO> listaBancos) {
		this.listaBancos = listaBancos;
	}

	public String primeiroNome() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);

		LoginUsuario user = (LoginUsuario) session.getAttribute("UsuarioLogado");
		if (user == null || user.getUsuario() == null) {
			return "";
		}

		if (user.getUsuario().getNome().contains(" ")) {
			return user.getUsuario().getNome().substring(0, user.getUsuario().getNome().indexOf(" "));
		}

		return user.getUsuario().getNome();
	}

	@PostConstruct
	public void init() {
		try {

			context = FacesContext.getCurrentInstance().getExternalContext();
			request = (HttpServletRequest) context.getRequest();

			HttpSession session = (HttpSession) context.getSession(false);
			if (session != null) {
				session.removeAttribute("UsuarioLogado");
			}

			dominio = Utils.getSubdominio(request);

			loadDataBaseConf(dominio);

			changedBanco();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadDataBaseConf(String dominio) {
		try {
			System.out.println("Carregando configuração para o domínio: " + dominio);

			String urlControle = "jdbc:postgresql://localhost:5432/clientes";
			String userControle = "postgres";
			String passControle = "alt@2024";

			System.out.println("Tentando conectar ao banco de controle: " + urlControle);

			try (Connection conn = DriverManager.getConnection(urlControle, userControle, passControle)) {
				System.out.println("Conexão estabelecida com sucesso!");

				String sql = """
						    SELECT
						        nome_municipio,
						        data_source,
						        url_banco,
						        usuario_banco,
						        senha_banco
						    FROM Controle_Dominios
						    WHERE subdominio = ?
						        AND ativo = true
						""";

				try (PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setString(1, dominio);
					System.out.println("Executando query para domínio: " + dominio);

					try (ResultSet rs = stmt.executeQuery()) {
						System.out.println("Query executada!");
						if (rs.next()) {
							System.out.println("Registro encontrado!");
							BancoDTO banco = new BancoDTO();
							banco.setCodigo(1);
							banco.setDataSource(rs.getString("data_source"));
							banco.setPrefeitura(rs.getString("nome_municipio"));
							banco.setNomeDB(dominio);
							banco.setSenhaDB(rs.getString("senha_banco"));
							banco.setTipo("a");
							banco.setUrlDB(rs.getString("url_banco"));
							banco.setUsuarioDB(rs.getString("usuario_banco"));

							listaBancos = new ArrayList<>();
							listaBancos.add(banco);
							System.out.println("Banco Carregado!");
						} else {
							System.out.println("Nenhum registro encontrado para o domínio: " + dominio);
							setMensagemERRO("Subdomínio não encontrado ou inativo: " + dominio);
						}
						if (listaBancos != null && !listaBancos.isEmpty()) {
							idBanco = listaBancos.get(0).getCodigo();
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Erro ao carregar configuração do banco: " + e.getMessage());
			e.printStackTrace();
			setMensagemERRO("Erro ao carregar configuração do banco: " + e.getMessage());
		}
	}

	public void changedBanco() {
		try {

			if (listaBancos != null && !listaBancos.isEmpty()) {

				for (int i = 0; i < listaBancos.size(); i++) {

					if (listaBancos.get(i).getCodigo().equals(idBanco)) {

						if (AmbienteDesenvolvimento.isProducao()) {

							request.getSession().setAttribute("PUDATABASE", listaBancos.get(i).getNomeDB());
							request.getSession().setAttribute("PUDATASOURCE", listaBancos.get(i).getDataSource());
							request.getSession().setAttribute("PUURL", listaBancos.get(i).getUrlDB());
							request.getSession().setAttribute("PUUSUARIO", listaBancos.get(i).getUsuarioDB());
							request.getSession().setAttribute("PUSENHA", listaBancos.get(i).getSenhaDB());
							request.getSession().setAttribute("PUPERSISTENCIA", "NEXUS");
							request.getSession().setAttribute("NOMEPREFEITURA", listaBancos.get(i).getPrefeitura());

						} else {

							request.getSession().setAttribute("PUDATABASE", listaBancos.get(i).getNomeDB());
							request.getSession().setAttribute("PUDATASOURCE", listaBancos.get(i).getDataSource());
							request.getSession().setAttribute("PUURL", listaBancos.get(i).getUrlDB());
							request.getSession().setAttribute("PUUSUARIO", listaBancos.get(i).getUsuarioDB());
							request.getSession().setAttribute("PUSENHA", "alt@2024");
							request.getSession().setAttribute("PUPERSISTENCIA", "NEXUS");
							request.getSession().setAttribute("NOMEPREFEITURA", listaBancos.get(i).getPrefeitura());
						}

						break;

					}

				}

			}
			AmbienteDesenvolvimento.cadastroInicialParateste(tipoAcessoDAO, usuarioDAO);

		} catch (Exception e) {
			e.printStackTrace();
			setMensagemERRO("Ocorreu um erro ao tentar efetuar login no sistema. Erro: " + e.getMessage());
		}
	}

	public void doLogin() {
		try {

			validarDados();

			Usuario us;

			us = usuarioDAO.getPorCPF(TextoUtil.soDigitos(cpf));
			if (us != null) {

				boolean temAcessoAUsuario = false;

				// Verificar qualo tipo de acesso do usuario
				if (us.getTipoAcesso() != null) {
//					if (us.getTipoAcesso().getCodigo() < 20) {// Acesso restrito a entidade
//						if (us.getTipoAcesso().getCodigo() > 1) {// Valores menores que 1 não tem acesso
//							us = usuarioDAO.getPorCodigo(us.getCodigo());
//							if (us != null) {
//								int tipoPermicaoAcesso = us.getTipoPermissao().getCodigo();
//								if (tipoPermicaoAcesso >= 2) {
//									temAcessoAUsuario = true;
//								} else {
//									throw new Exception("Usuário não tem permissão de acesso.");
//								}
//							} else {
//								throw new Exception("Usuário não tem permissão de acesso.");
//							}
//
//						} else {
//							throw new Exception("Usuário não tem permissão de acesso.");
//						}
//					} else {
					temAcessoAUsuario = true;
					// }
				} else {
					throw new Exception("Usuário não tem permissão de acesso.");
				}

				if (temAcessoAUsuario) {

					if (us.isAutenticacaoValida(senha.trim())) {

						LoginUsuario log = new LoginUsuario();
						log.setUsuario(us);
						log.setSiglaParam(dominio);
						log.setLogado(true);

						context = FacesContext.getCurrentInstance().getExternalContext();
						HttpServletResponse response = (HttpServletResponse) context.getResponse();
						HttpServletRequest request = (HttpServletRequest) context.getRequest();
						request.getSession().setAttribute("UsuarioLogado", log);
						request.getSession().setAttribute("UsuarioSessao", log.getUsuario());

						response.sendRedirect("nexus/dashboard.xhtml?faces-redirect=true");

					} else {
						throw new Exception("Usuário inválido.");
					}

				} else {
					throw new Exception("Acesso negado.");

				}

			} else {
				throw new Exception("Usuário inválido.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			setMensagemERRO("Ocorreu um erro ao tentar efetuar login. Erro: " + e.getMessage());
		}
	}

	private void validarDados() throws Exception {

		if (getCpf() == null || getCpf().trim().length() < 3) {
			throw new Exception("CPF inválido.");
		}

		if (senha == null || senha.trim().length() <= 1) {
			throw new Exception("Senha inválida.");
		}

		if (idBanco == null || idBanco <= 0) {
			throw new Exception("Banco de dados para a cidade não encontrado.");
		}

	}

	public String logoff() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("UsuarioLogado");
		session.invalidate();
		return "/login.xhtml?faces-redirect=true";
	}

}
