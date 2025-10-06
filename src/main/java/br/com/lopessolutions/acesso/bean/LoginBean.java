package br.com.lopessolutions.acesso.bean;

import br.com.lopessolutions.acesso.dao.usuario.UsuarioDAO;
import br.com.lopessolutions.acesso.util.LoginUsuario;
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

	private String cpf = "";
	private String senha = "";

	private ExternalContext context;

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
			HttpSession session = (HttpSession) context.getSession(false);
			if (session != null) {
				session.removeAttribute("UsuarioLogado");
			}

            // Seed inicial é feito pelo StartupSeeder no boot do servidor

		} catch (Exception e) {
			e.printStackTrace();
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
						log.setLogado(true);

						context = FacesContext.getCurrentInstance().getExternalContext();
						HttpServletResponse response = (HttpServletResponse) context.getResponse();
						HttpServletRequest request = (HttpServletRequest) context.getRequest();
						request.getSession().setAttribute("UsuarioLogado", log);
						request.getSession().setAttribute("UsuarioSessao", log.getUsuario());

						response.sendRedirect("ls/dashboard.xhtml?faces-redirect=true");

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
