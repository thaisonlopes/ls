package br.com.lopessolutions.acesso.util;

import br.com.lopessolutions.acesso.dao.tipoAcesso.TipoAcessoDAO;
import br.com.lopessolutions.acesso.dao.usuario.UsuarioDAO;
import br.com.lopessolutions.entidades.principal.TipoAcesso;
import br.com.lopessolutions.entidades.principal.Usuario;

/**
 * 
 * @author Usuário
 *
 */
public class AmbienteDesenvolvimento {

	private static final int TESTE = 0;
	private static final int PRODUCAO = 1;
	private static int ambiente = PRODUCAO; // alterar essa variável para PRODUCAO ou TESTE

	public static boolean isProducao() {
		return (ambiente == PRODUCAO);
	}

	public static void cadastroInicialParateste(TipoAcessoDAO tipoAcessoDAO, UsuarioDAO usuarioDAO) {

		TipoAcesso ta = tipoAcessoDAO.getPorCodigo(1);
		if (ta == null) {
			ta = new TipoAcesso();
			ta.setCodigo(1);
			ta.setDescricao("Sem Acesso");
			tipoAcessoDAO.salvar("Usuário", ta);
		}

		ta = tipoAcessoDAO.getPorCodigo(2);
		if (ta == null) {
			ta = new TipoAcesso();
			ta.setCodigo(2);
			ta.setDescricao("Acesso Personalizado");
			tipoAcessoDAO.salvar("Usuário", ta);
		}

		ta = tipoAcessoDAO.getPorCodigo(20);
		if (ta == null) {
			ta = new TipoAcesso();
			ta.setCodigo(20);
			ta.setDescricao("Acesso Total");
			ta = tipoAcessoDAO.salvar("Usuário", ta);
		}

		Usuario us = usuarioDAO.getPorCodigo(1);
		if (us == null) {

			us = new Usuario();

			us.setCodigo(1);
			us.setNome("Administrador");
			us.setCpf("00000000000");
			us.setSenha("alt@2024");
			us.setEmail("nexus@gmail.com");
			us.setFornecedorMateria(true);
			us.setPublicador(true);
			us.setHabilitado(true);
			us.setTipoAcesso(ta);
			usuarioDAO.salvar("Usuário", us);

		}

	}
}