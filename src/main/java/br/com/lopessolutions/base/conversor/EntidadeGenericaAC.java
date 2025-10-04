package br.com.lopessolutions.base.conversor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EntidadeGenericaAC implements Serializable {

	private static final long serialVersionUID = 2567280863415039479L;

	private Integer id;
	private Object campo1, campo2, campo3, campo4, campo5;
	private Object campo6, campo7, campo8, campo9, campo10;

	public EntidadeGenericaAC(Integer codigo) {
		id = codigo;
	}

	public EntidadeGenericaAC(Long codigo) {
		 
			id = codigo.intValue();
		 
	}

	public static List<EntidadeGenericaAC> getLista(List<?> lista) {
		List<EntidadeGenericaAC> ret = new ArrayList<>();
		if (lista != null && !lista.isEmpty()) {
			int cont = 1;
			for (Object o : lista) {
				EntidadeGenericaAC t = new EntidadeGenericaAC(cont, (Object[]) o);
				ret.add(t);
				cont++;
			}
		}
		return ret;
	}

	public EntidadeGenericaAC(int id, Object[] o) {

		this.id = id;
		if (o != null && o.length > 0) {

			switch (o.length) {
			case 1:
				this.campo1 = o[0];
				break;

			case 2:
				this.campo1 = o[0];
				this.campo2 = o[1];
				break;

			case 3:
				this.campo1 = o[0];
				this.campo2 = o[1];
				this.campo3 = o[2];
				break;

			case 4:
				this.campo1 = o[0];
				this.campo2 = o[1];
				this.campo3 = o[2];
				this.campo4 = o[3];
				break;

			case 5:
				this.campo1 = o[0];
				this.campo2 = o[1];
				this.campo3 = o[2];
				this.campo4 = o[3];
				this.campo5 = o[4];
				break;

			case 6:
				this.campo1 = o[0];
				this.campo2 = o[1];
				this.campo3 = o[2];
				this.campo4 = o[3];
				this.campo5 = o[4];
				this.campo6 = o[5];
				break;

			case 7:
				this.campo1 = o[0];
				this.campo2 = o[1];
				this.campo3 = o[2];
				this.campo4 = o[3];
				this.campo5 = o[4];
				this.campo6 = o[5];
				this.campo7 = o[6];
				break;

			case 8:
				this.campo1 = o[0];
				this.campo2 = o[1];
				this.campo3 = o[2];
				this.campo4 = o[3];
				this.campo5 = o[4];
				this.campo6 = o[5];
				this.campo7 = o[6];
				this.campo8 = o[7];
				break;

			case 9:
				this.campo1 = o[0];
				this.campo2 = o[1];
				this.campo3 = o[2];
				this.campo4 = o[3];
				this.campo5 = o[4];
				this.campo6 = o[5];
				this.campo7 = o[6];
				this.campo8 = o[7];
				this.campo9 = o[8];
				break;

			case 10:
				this.campo1 = o[0];
				this.campo2 = o[1];
				this.campo3 = o[2];
				this.campo4 = o[3];
				this.campo5 = o[4];
				this.campo6 = o[5];
				this.campo7 = o[6];
				this.campo8 = o[7];
				this.campo9 = o[8];
				this.campo10 = o[9];
				break;

			default:
				break;

			}

		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Object getCampo1() {
		return campo1;
	}

	public void setCampo1(Object campo1) {
		this.campo1 = campo1;
	}

	public Object getCampo2() {
		return campo2;
	}

	public void setCampo2(Object campo2) {
		this.campo2 = campo2;
	}

	public Object getCampo3() {
		return campo3;
	}

	public void setCampo3(Object campo3) {
		this.campo3 = campo3;
	}

	public Object getCampo4() {
		return campo4;
	}

	public void setCampo4(Object campo4) {
		this.campo4 = campo4;
	}

	public Object getCampo5() {
		return campo5;
	}

	public void setCampo5(Object campo5) {
		this.campo5 = campo5;
	}

	public Object getCampo6() {
		return campo6;
	}

	public void setCampo6(Object campo6) {
		this.campo6 = campo6;
	}

	public Object getCampo7() {
		return campo7;
	}

	public void setCampo7(Object campo7) {
		this.campo7 = campo7;
	}

	public Object getCampo8() {
		return campo8;
	}

	public void setCampo8(Object campo8) {
		this.campo8 = campo8;
	}

	public Object getCampo9() {
		return campo9;
	}

	public void setCampo9(Object campo9) {
		this.campo9 = campo9;
	}

	public Object getCampo10() {
		return campo10;
	}

	public void setCampo10(Object campo10) {
		this.campo10 = campo10;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campo1 == null) ? 0 : campo1.hashCode());
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
		EntidadeGenericaAC other = (EntidadeGenericaAC) obj;
		if (campo1 == null) {
			if (other.campo1 != null)
				return false;
		} else if (!campo1.equals(other.campo1))
			return false;
		return true;
	}

}
