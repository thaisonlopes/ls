package br.com.lopessolutions.acesso.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.lopessolutions.acesso.dao.usuario.UsuarioDAO;
import br.com.lopessolutions.acesso.util.LoginUsuario;
import br.com.lopessolutions.entidades.principal.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

@Named("dadosLoginUser")
@ViewScoped
public class DadosLoginUser implements Serializable {

	private static final long serialVersionUID = 1793940081152429425L;

	private String nomeUsuario;
	private String parametro;
	private byte[] foto;

	@Inject
	private UsuarioDAO usuarioDao;

	@PostConstruct
	public void init() {
		iniciarDados();
	}

	private void iniciarDados() {
		try {

			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);

			if (session != null && session.getAttribute("UsuarioLogado") != null) {
				LoginUsuario user = (LoginUsuario) session.getAttribute("UsuarioLogado");
				if (user != null) {

					Usuario u = usuarioDao.getPorCodigo(user.getUsuario().getCodigo());
					if (u != null) {
						nomeUsuario = u.getNome();
						parametro = user.getSiglaParam();
						foto = u.getFoto();

					} else {

						nomeUsuario = user.getUsuario().getNome();
						parametro = user.getSiglaParam();
						foto = user.getUsuario().getFoto();
					}
				}
			} else {
				nomeUsuario = "";
				parametro = "";
				foto = null;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getNomeUsuario() {
		if (nomeUsuario == null) {
			iniciarDados();
		}
		return nomeUsuario;
	}
	
	  public void deslogar() {
	        // Obtenha o contexto e a sessão
	        FacesContext context = FacesContext.getCurrentInstance();
	        ExternalContext externalContext = context.getExternalContext();
	        HttpSession session = (HttpSession) externalContext.getSession(false);

	        // Verifique se a sessão não é nula e invalide a sessão
	        if (session != null) {
	            session.invalidate();  // Isso irá invalidar a sessão do usuário
	        }

	        // Redireciona o usuário para a página de login após deslogar
	        try {
	            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getParametro() {
		if (parametro == null) {
			iniciarDados();
		}
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public StreamedContent getFotoPng() {
		try {
			return DefaultStreamedContent.builder().contentType("image/png").stream(() -> {
				try {

					if (foto != null && foto.length > 0) {
						return new ByteArrayInputStream(foto);
					} else {
						return new ByteArrayInputStream(getImagemPadrao());
					}

				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}).build();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private byte[] getImagemPadrao() {
		try {

			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String fileImage = externalContext.getRealPath("") + File.separator + "resources" + File.separator
					+ "imagens" + File.separator + "nexus_colorida" + ".png";

			File file = new File(fileImage);
			FileInputStream is = new FileInputStream(file);

			ByteArrayOutputStream os = new ByteArrayOutputStream();

			byte[] buffer = new byte[2048];

			for (int len; (len = is.read(buffer)) != -1;) {
				os.write(buffer, 0, len);
			}

			os.flush();
			is.close();

			return os.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
