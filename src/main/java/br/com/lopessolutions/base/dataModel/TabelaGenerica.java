package br.com.lopessolutions.base.dataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Tabela generica para ate 20 campos
 * 
 * @author Thaison
 *
 */
public class TabelaGenerica implements Serializable {

	private static final long serialVersionUID = 6642503224444325388L;

	private int id;
	private Object campo1, campo2, campo3, campo4, campo5, campo6, campo7, campo8, campo9, campo10;
	private Object campo11, campo12, campo13, campo14, campo15, campo16, campo17, campo18, campo19, campo20;

	public static List<TabelaGenerica> getLista(List<?> lista) {
		List<TabelaGenerica> ret = new ArrayList<TabelaGenerica>();
		if (lista != null && !lista.isEmpty()) {
			int cont = 1;
			for (Object o : lista) {
				TabelaGenerica t = new TabelaGenerica(cont, (Object[]) o);
				ret.add(t);
				cont++;
			}
		}
		return ret;
	}

	public TabelaGenerica() {

	}

	public TabelaGenerica(int id) {
		this.id = id;
	}

	public TabelaGenerica(int id, Object[] o) {

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

			case 11:
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
				this.campo11 = o[10];
				break;

			case 12:
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
				this.campo11 = o[10];
				this.campo12 = o[11];
				break;

			case 13:
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
				this.campo11 = o[10];
				this.campo12 = o[11];
				this.campo13 = o[12];
				break;

			case 14:
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
				this.campo11 = o[10];
				this.campo12 = o[11];
				this.campo13 = o[12];
				this.campo14 = o[13];
				break;

			case 15:
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
				this.campo11 = o[10];
				this.campo12 = o[11];
				this.campo13 = o[12];
				this.campo14 = o[13];
				this.campo15 = o[14];
				break;

			case 16:
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
				this.campo11 = o[10];
				this.campo12 = o[11];
				this.campo13 = o[12];
				this.campo14 = o[13];
				this.campo15 = o[14];
				this.campo16 = o[15];
				break;

			case 17:
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
				this.campo11 = o[10];
				this.campo12 = o[11];
				this.campo13 = o[12];
				this.campo14 = o[13];
				this.campo15 = o[14];
				this.campo16 = o[15];
				this.campo17 = o[16];
				break;

			case 18:
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
				this.campo11 = o[10];
				this.campo12 = o[11];
				this.campo13 = o[12];
				this.campo14 = o[13];
				this.campo15 = o[14];
				this.campo16 = o[15];
				this.campo17 = o[16];
				this.campo18 = o[17];
				break;

			case 19:
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
				this.campo11 = o[10];
				this.campo12 = o[11];
				this.campo13 = o[12];
				this.campo14 = o[13];
				this.campo15 = o[14];
				this.campo16 = o[15];
				this.campo17 = o[16];
				this.campo18 = o[17];
				this.campo19 = o[18];
				break;

			case 20:
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
				this.campo11 = o[10];
				this.campo12 = o[11];
				this.campo13 = o[12];
				this.campo14 = o[13];
				this.campo15 = o[14];
				this.campo16 = o[15];
				this.campo17 = o[16];
				this.campo18 = o[17];
				this.campo19 = o[18];
				this.campo20 = o[19];
				break;

			default:
				break;

			}

		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Object getCampo11() {
		return campo11;
	}

	public void setCampo11(Object campo11) {
		this.campo11 = campo11;
	}

	public Object getCampo12() {
		return campo12;
	}

	public void setCampo12(Object campo12) {
		this.campo12 = campo12;
	}

	public Object getCampo13() {
		return campo13;
	}

	public void setCampo13(Object campo13) {
		this.campo13 = campo13;
	}

	public Object getCampo14() {
		return campo14;
	}

	public void setCampo14(Object campo14) {
		this.campo14 = campo14;
	}

	public Object getCampo15() {
		return campo15;
	}

	public void setCampo15(Object campo15) {
		this.campo15 = campo15;
	}

	public Object getCampo16() {
		return campo16;
	}

	public void setCampo16(Object campo16) {
		this.campo16 = campo16;
	}

	public Object getCampo17() {
		return campo17;
	}

	public void setCampo17(Object campo17) {
		this.campo17 = campo17;
	}

	public Object getCampo18() {
		return campo18;
	}

	public void setCampo18(Object campo18) {
		this.campo18 = campo18;
	}

	public Object getCampo19() {
		return campo19;
	}

	public void setCampo19(Object campo19) {
		this.campo19 = campo19;
	}

	public Object getCampo20() {
		return campo20;
	}

	public void setCampo20(Object campo20) {
		this.campo20 = campo20;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		TabelaGenerica other = (TabelaGenerica) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
