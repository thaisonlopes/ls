package br.com.lopessolutions.altoComplete.cadastro.usuario;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.SelectEvent;

import br.com.lopessolutions.acesso.dao.usuario.UsuarioDAO;
import br.com.lopessolutions.base.autoComplete.AutoCompleteDAO;
import br.com.lopessolutions.base.autoComplete.PesqTextComp;
import br.com.lopessolutions.base.conversor.EntidadeGenericaAC;
import br.com.lopessolutions.base.criptografia.Criptografia;
import br.com.lopessolutions.entidades.principal.Usuario;

public class PesqUsuario extends PesqTextComp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7264170572860762481L;

	static Logger logger = LogManager.getLogger(PesqUsuario.class);

	private UsuarioDAO dao;
	private Usuario ent;
	private EntidadeGenericaAC entGenerica;
	private Integer codigo;
	private String cpf;

	public PesqUsuario(AutoCompleteDAO daoAC, UsuarioDAO dao) {
		super(daoAC);
		this.dao = dao;

		// Ajustado SQL para trazer os dados necessários
		String sql = "SELECT us.codUsuario, us.nome, us.cpf " + "FROM intUsuario us "
				+ "WHERE TRIM(UPPER(us.nome)) LIKE ?1 " + " AND us.habilitado = true ORDER BY us.nome";
		setSql(sql);
	}

	@SuppressWarnings("unchecked")
	public List<EntidadeGenericaAC> completeMethod(String desc) {
		if (entGenerica == null) {
			entGenerica = new EntidadeGenericaAC(0);
		}
		entGenerica.setCampo2(desc);

		List<Object[]> lista = (List<Object[]>) getLista(like(desc));
		List<EntidadeGenericaAC> listaEntidades = new ArrayList<>();

		if (lista != null) {
			for (Object[] obj : lista) {
				EntidadeGenericaAC entAC = new EntidadeGenericaAC((Integer) obj[0]);
				Integer cod = (Integer) obj[0];
				String nome = (String) obj[1];
				String cpfCnpj = (String) obj[2];

				entAC.setCampo1(cod);
				entAC.setCampo2(nome);

				// Formata CPF/CNPJ baseado no tipo
				String cpfCnpjFormatado = formatarCpfCnpj(cpfCnpj, 1);
				entAC.setCampo3(cpfCnpjFormatado);

				listaEntidades.add(entAC);
			}
		}
		return listaEntidades;
	}

	private String formatarCpfCnpj(String valor, Integer tipo) {
		if (valor == null || valor.trim().isEmpty()) {
			return "";
		}

		String cpfCnpjDescriptografado = Criptografia.getDescriptografar(valor);
		cpfCnpjDescriptografado = cpfCnpjDescriptografado.replaceAll("[^0-9]", "");

		if (tipo != null && tipo == 1) { // CPF
			return cpfCnpjDescriptografado.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
		} else { // CNPJ
			return cpfCnpjDescriptografado.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
		}
	}

	@Override
	public void busca() {
		try {

			ent = dao.getPorCodigo(codigo);
			mostra();

		} catch (Exception e) {
			logger.error("Erro na busca: ", e);
			setMensagemERRO("Erro: " + e.getLocalizedMessage());
		}
	}

	private void mostra() {
		if (ent != null) {
			codigo = ent.getCodigo();
			if (entGenerica == null) {
				entGenerica = new EntidadeGenericaAC(codigo);
			}
			entGenerica.setCampo1(ent.getCodigo());
			entGenerica.setCampo2(ent.getNome());
			cpf = ent.getCpf();
			entGenerica.setCampo3(cpf);
		} else {
			entGenerica = null;
			codigo = null;
			cpf = null;
		}
	}

	@Override
	public void selectAutoComplete(SelectEvent<Object> event) {
		try {
			entGenerica = (EntidadeGenericaAC) event.getObject();
			if (entGenerica != null) {
				codigo = (Integer) entGenerica.getCampo1();
				cpf = (String) entGenerica.getCampo3();
				busca();
			}
		} catch (Exception e) {
			logger.error("Erro: ", e);
			setMensagemERRO("Erro: " + e.getLocalizedMessage());
		}
	}

	// Getters e Setters

	public UsuarioDAO getDao() {
		return dao;
	}

	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}

	public Usuario getEnt() {
		return ent;
	}

	public void setEnt(Usuario ent) {
		this.ent = ent;
		mostra();
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public EntidadeGenericaAC getEntGenerica() {
		return entGenerica;
	}

	public void setEntGenerica(EntidadeGenericaAC entGenerica) {
		this.entGenerica = entGenerica;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}