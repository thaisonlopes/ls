package br.com.lopessolutions.acesso.rne;

import java.io.Serializable;
import java.util.List;

import br.com.lopessolutions.base.criptografia.Criptografia;
import br.com.lopessolutions.base.exception.AltException;
import br.com.lopessolutions.base.rne.GenericRNE;
import br.com.lopessolutions.base.util.ValidandoEmail;
import br.com.lopessolutions.entidades.principal.Log;
import br.com.lopessolutions.entidades.principal.Permissao;
import br.com.lopessolutions.entidades.principal.Usuario;
import jakarta.persistence.EntityManager;

/**
 * 
 * @author Thaison
 *
 */
public class UsuarioRNE extends GenericRNE {

	private static final long serialVersionUID = -3280598183813616359L;

	@Override
	protected void setLog(Log log, Serializable pojo) {
		Usuario t = (Usuario) pojo;
		log.setC1(t.getCodigo().toString());
	}

	public Usuario getByCpf(EntityManager session, Integer codigo, String cpf) {
		cpf = Criptografia.getCriptografar(cpf.trim());
		List<Usuario> listaUsuario = getPureList(session, Usuario.class,
				"select e from Usuario e where e.codigo !=?1 and e.cpf =?2", (codigo == null ? 0 : codigo),
				cpf.strip());
		if (listaUsuario != null && !listaUsuario.isEmpty()) {
			return listaUsuario.get(0);
		}
		return null;
	}

	public void validacao(EntityManager session, Usuario usuario) {
		if (getByCpf(session, usuario.getCodigo(), usuario.getCpf()) != null) {
			throw new AltException("Pessoa já cadastrada com este CPF! \n");
		}
		if (usuario.getNome() == null || usuario.getNome().trim().length() < 3) {
			throw new AltException("O nome é nulo ou é menor que 3 caracteres. \n");
		}
		if (usuario.getCpf() == null || usuario.getCpf().trim().length() < 11) {
			throw new AltException("O CPF é nulo ou tem valor invalido. \n");
		}
		if (usuario.getTipoAcesso() == null) {
			throw new AltException("Selecione um tipo de acesso. \n");
		}
		if (usuario.getSenha() == null || usuario.getSenha().trim().length() < 3) {
			throw new AltException("A Senha é nula ou é menor que 3 caracteres.\n");
		}
		if (!ValidandoEmail.isValid(usuario.getEmail())) {
			throw new AltException("Email inserido incorretamente.\n");
		}
		if (usuario.getFornecedorMateria() == null && usuario.getPublicador() == null) {
			throw new AltException("Insira se o usuário é Fornecedor de Matéria e/ou Publicador.\n");
		}
	}

	public Usuario salvar(EntityManager session, Usuario usuario) {

		validacao(session, usuario);
		return super.saveOrUpdatePojo(session, usuario);

	}

	public void delete(EntityManager session, Usuario usuario) {

		if (usuario == null) {
			throw new AltException("Usuário tem valor nulo.");
		}

		deletarPermissao(session, usuario.getCodigo());

		super.deletePojo(usuario, session);
	}

	private void deletarPermissao(EntityManager session, int codUsuario) {
		String sql = "select e from Permissao e where e.usuario.codigo=?1";
		List<Permissao> lista = getPureList(session, Permissao.class, sql, codUsuario);
		if (lista != null && !lista.isEmpty()) {
			lista.stream().forEach((permissao) -> {
				session.remove(permissao);
			});
		}
	}

	public Usuario getPorCodigo(EntityManager session, int codUsuario) {

		List<Usuario> listaUsuario = getPureList(session, Usuario.class, "select e from Usuario e where e.codigo=?1 order by e.nome",
				codUsuario);
		if (listaUsuario != null && !listaUsuario.isEmpty()) {
			return listaUsuario.get(0);
		}
		return null;
	}

	public Usuario getPorNivelCodigo(EntityManager session, int nvAcesso, int nvPermissao, int codUsuario) {

		String sql = "select e from Usuario e where e.codigo=?1 and e.tipoAcesso.codigo<=" + nvAcesso;
		if (nvAcesso < 20) {
			sql = " select e from Usuario e" + " where e.codigo = ?1" + " and e.tipoAcesso.codigo <= " + nvAcesso
					+ " and e.tipoPermissao.codigo <= " + nvPermissao + "  ";
		}

		return getPojoUnique(session, Usuario.class, sql, codUsuario);
	}

	public Usuario getPorNivelCodigoParaProcessoDig(EntityManager session, int nvAcesso, int nvPermissao,
			int codUsuario) {

		String sql = "SELECT e FROM Usuario e" + " WHERE "
				+ " e.codigo IN(SELECT u1 FROM Usuario u1 WHERE u1.codigo=?1 AND u1.tipoAcesso.codigo>=20)" + " OR "
				+ " e.codigo IN (" + " SELECT u from Usuario u" + " WHERE u.codigo =?1" + " AND u.tipoAcesso.codigo <20"
				+ "" + " )";

		if (nvAcesso < 20) {
			sql = " select e from Usuario e" + " where e.codigo =?1" + " and e.tipoAcesso.codigo <= " + nvAcesso
					+ " and e.tipoPermissao.codigo <= " + nvPermissao + "" + " ";
		}

		return getPojoUnique(session, Usuario.class, sql, codUsuario);
	}

	public Usuario getPorCodigoTipoAcesso(EntityManager session, int codUsuario) {

		String sql = "SELECT e FROM Usuario e" + " WHERE "
				+ " e.codigo IN(SELECT u1 FROM Usuario u1 WHERE u1.codigo=?1 AND u1.tipoAcesso.codigo>=20)" + " OR "
				+ " e.codigo IN (" + " SELECT u from Usuario u" + " WHERE u.codigo =?1" + " AND u.tipoAcesso.codigo <20"
				+ " " + " )";

		return getPojoUnique(session, Usuario.class, sql, codUsuario);

	}

	public Usuario getPorCPF(EntityManager session, String cpf) {
		System.out.println("########CPF REGRA::" + cpf);
		cpf = Criptografia.getCriptografar(cpf.trim());
		System.out.println("########CPF REGRA CRIPTO:" + cpf);
		String sql = "select e from Usuario e where e.cpf=?1 and e.habilitado = true";
		return getPojoUnique(session, Usuario.class, sql, cpf);
	}

	public List<Usuario> getList(EntityManager session) {
		return super.getPureList(session, Usuario.class, "select e from Usuario e order by e.nome");
	}

	public List<Usuario> getListPorNivel(EntityManager session, int nvAcesso, int nvPermissao) {

		String sql = "select e from Usuario e where e.tipoAcesso.codigo <= " + nvAcesso + " order by e.nome";
		if (nvAcesso < 20) {
			sql = " select e from Usuario e " + " where e.tipoAcesso.codigo <= " + nvAcesso
					+ " and  e.tipoPermissao.codigo <= " + nvPermissao + " order by e.nome";
		}

		return getPureList(session, Usuario.class, sql);
	}

	public List<Usuario> getListaAdministradores(EntityManager session) {
		String sql = " select e from Usuario e" + " where e.tipoAcesso.codigo >= 20" + " or e.tipoPermissao.codigo >=5"
				+ " order by e.nome";
		return super.getPureList(session, Usuario.class, sql);
	}

	public List<Usuario> getListHabilitado(EntityManager session, Boolean habilitado) {
		List<Usuario> listaPessoas = getPureList(session, Usuario.class,
				"SELECT e FROM Usuario e WHERE e.habilitado =?1", habilitado);
		if (listaPessoas != null && !listaPessoas.isEmpty()) {
			return listaPessoas;
		}
		return null;
	}

	public List<Usuario> getList(EntityManager session, String sql, Object... parametros) {
		return super.getPureList(session, Usuario.class, sql, parametros);
	}

	public List<Usuario> getList(EntityManager session, int nrRegistros, String sql, Object... parametros) {
		return super.getPureList(session, 0, nrRegistros, Usuario.class, sql, parametros);
	}

	public Integer getCount(EntityManager session) {
		return super.getInteger(session, "select count(e) from Usuario e");
	}

}
