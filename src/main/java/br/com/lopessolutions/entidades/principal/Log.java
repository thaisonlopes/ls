package br.com.lopessolutions.entidades.principal;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import br.com.lopessolutions.base.util.UEnderecoComputador;
import br.com.lopessolutions.base.util.WControlaAcesso;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "log")
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo", nullable = false)
	private long codigo;

	@Column(name = "codUser", nullable = false)
	private Integer codUser;

	@Column(name = "usuario", nullable = false, length = 250)
	private String usuario;

	@Column(name = "tela", nullable = false, length = 500)
	private String tela;

	@Column(name = "itemTela")
	private String itemTela;

	@Column(name = "dthr")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dthr;

	@Column(name = "ip", nullable = false, length = 100)
	private String ip;

	@Column(name = "computador", nullable = false, length = 100)
	private String computador;

	@Column(name = "acao", nullable = false, length = 70)
	private String acao;

	@Column(name = "c1", nullable = false, length = 70)
	private String c1 = "";

	@Column(name = "c2", nullable = false, length = 70)
	private String c2 = "";

	@Column(name = "c3", nullable = false, length = 70)
	private String c3 = "";

	@Column(name = "c4", nullable = false, length = 70)
	private String c4 = "";

	@Column(name = "c5", nullable = false, length = 70)
	private String c5 = "";

	@Column(name = "c6", nullable = false, length = 70)
	private String c6 = "";

	@Column(name = "c7", nullable = false, length = 70)
	private String c7 = "";

	public Log() {
		try {

			// Define o usuario -----------------------------------------------------
			WControlaAcesso cc = new WControlaAcesso();

			Usuario u = (Usuario) cc.getAtributoSessao("UsuarioSessao");
			if (u != null) {
				usuario = u.getNome();
				codUser = u.getCodigo();
			} else {
				String nu = (String) cc.getAtributoSessao("nomeUsuarioSessao");
				if (nu != null) {
					codUser = 0;
					usuario = nu;
				} else {
					codUser = 0;
					usuario = "Não Identificado";
				}
			}

			// -define a data e hora da operação ------------------------------------
			TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
			TimeZone.setDefault(tz);
			Calendar ca = GregorianCalendar.getInstance(tz);
			dthr = ca.getTime();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// -Define computado usado ----------------------------------------------
		try {
			ip = UEnderecoComputador.getRemoteAddr();
			computador = UEnderecoComputador.getRemoteHost();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Integer getCodUser() {
		return codUser;
	}

	public void setCodUser(Integer codUser) {
		this.codUser = codUser;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTela() {
		return tela;
	}

	public void setTela(String tela) {
		this.tela = tela;
	}

	public String getItemTela() {
		return itemTela;
	}

	public void setItemTela(String itemTela) {
		this.itemTela = itemTela;
	}

	public Date getDthr() {
		return dthr;
	}

	public void setDthr(Date dthr) {
		this.dthr = dthr;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getComputador() {
		return computador;
	}

	public void setComputador(String computador) {
		this.computador = computador;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public String getC6() {
		return c6;
	}

	public void setC6(String c6) {
		this.c6 = c6;
	}

	public String getC7() {
		return c7;
	}

	public void setC7(String c7) {
		this.c7 = c7;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

}
