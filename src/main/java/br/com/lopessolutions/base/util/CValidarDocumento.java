package br.com.lopessolutions.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CValidarDocumento {

	public CValidarDocumento() {

	}

	public static boolean eCNPJValido(String umCNPJ) {

		if (umCNPJ == null) {
			return false;
		}

		if (umCNPJ.length() != 18 && umCNPJ.length() != 14) {
			return false;
		}

		int validos = 0;
		for (int i = 0; i < umCNPJ.length(); i++) {
			if (Character.isDigit(umCNPJ.charAt(i))) {
				validos++;
			}
		}

		if (validos != 14) {
			return false;
		}

		int numeroCNPJ[] = new int[validos - 2];
		int j = 0;

		for (int i = umCNPJ.length() - 3; i >= 0; i--) {
			if (Character.isDigit(umCNPJ.charAt(i))) {
				numeroCNPJ[j++] = Integer.parseInt(new String(umCNPJ.charAt(i) + ""));
			}
		}

		int digito[] = new int[2];
		j = 0;
		for (int i = umCNPJ.length() - 1; i >= umCNPJ.length() - 2; i--) {
			if (Character.isDigit(umCNPJ.charAt(i))) {
				digito[j++] = Integer.parseInt(new String(umCNPJ.charAt(i) + ""));
			}
		}
		j = 2;
		int soma = 0;
		for (int i = 0; i < 12; i++) {
			if (i == 8) {
				j = 2;
			}
			soma += numeroCNPJ[i] * j++;
		}

		int aux = soma % 11;
		int digito1 = 11 - aux;
		if (digito1 >= 10) {
			digito1 = 0;
		}

		if (digito1 != digito[1]) {
			return false;
		}

		soma = digito1 * 2;
		j = 3;
		for (int i = 0; i < 12; i++) {
			if (i == 7) {
				j = 2;
			}
			soma += numeroCNPJ[i] * j++;
		}

		aux = soma % 11;
		int digito2 = 11 - aux;
		if (digito2 >= 10) {
			digito2 = 0;
		}

		return digito2 == digito[0];

	}

	public static boolean eCPFValido(String cpf) {

		if (cpf == null) {
			return false;
		}

		if (cpf.length() != 14 && cpf.length() != 11) {
			return false;
		}

		char prim = cpf.charAt(0);

		if (!Character.isDigit(prim)) {
			return false;
		}

		int d1, d2, digito1, digito2, digitoCPF, resto;
		String nDigResult;
		cpf = LimparPontuacao(cpf);

		d1 = d2 = digito1 = digito2 = digitoCPF = resto = 0;
		for (int i = 1; i < cpf.length() - 1; i++) {
			try {
				digitoCPF = Integer.valueOf(cpf.substring(i - 1, i)).intValue();
			} catch (NumberFormatException e) {
				return false;
			}
			d1 += (11 - i) * digitoCPF;
			d2 += (12 - i) * digitoCPF;
		}

		resto = (d1 % 11);
		if (resto < 2) {
			digito1 = 0;
		} else {
			digito1 = 11 - resto;
		}

		d2 = d2 + (2 * digito1);
		resto = (d2 % 11);

		if (resto < 2) {
			digito2 = 0;
		} else {
			digito2 = 11 - resto;
		}

		String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

		return nDigResult.equals(nDigVerific);

	}

	private static String LimparPontuacao(String dados) {
		String TempDados = "";
		for (int a = 0; a < dados.length(); a++) {
			if (dados.substring(a, a + 1).equals(".")) {
			} else if (dados.substring(a, a + 1).equals("-")) {
			} else if (dados.substring(a, a + 1).equals("/")) {
			} else {
				TempDados += dados.substring(a, a + 1);
			}
		}
		return TempDados;
	}

	public static boolean eCPFValido2(String cpf) {
		if (eCPFValido(cpf)) {
			cpf = LimparPontuacao(cpf);
			return vCpfInvalidos(cpf);
		}
		return false;
	}

	public static boolean eCNPJValido2(String umCNPJ) {
		if (eCNPJValido(umCNPJ)) {
			umCNPJ = LimparPontuacao(umCNPJ);
			return vCnpjInvalidos(umCNPJ);
		}
		return false;
	}

	private static boolean vCpfInvalidos(String cpf) {

		if (cpf.trim().equals("00000000000") || cpf.trim().equals("11111111111")
				|| cpf.trim().equals("22222222222") || cpf.trim().equals("33333333333")
				|| cpf.trim().equals("44444444444") || cpf.trim().equals("55555555555")
				|| cpf.trim().equals("66666666666") || cpf.trim().equals("77777777777")
				|| cpf.trim().equals("88888888888") || cpf.trim().equals("99999999999")) {
			return false;
		}

		return true;
	}

	private static boolean vCnpjInvalidos(String cpf) {

		if (cpf.trim().equals("00000000000000") || cpf.trim().equals("11111111111111")
				|| cpf.trim().equals("22222222222222") || cpf.trim().equals("33333333333333")
				|| cpf.trim().equals("44444444444444") || cpf.trim().equals("55555555555555")
				|| cpf.trim().equals("66666666666666") || cpf.trim().equals("77777777777777")
				|| cpf.trim().equals("88888888888888") || cpf.trim().equals("99999999999999")) {
			return false;
		}

		return true;
	}

	////////////
	public static final int DIGIT_COUNT = 11;
	/**
	 * PIS/PASEP Mask
	 */
	public static final String MASK = "###.#####.##-#";

	public static boolean ePisPasepValido(String pisOrPasep) {
		if (pisOrPasep == null) {
			return false;
		}
		String n = pisOrPasep.replaceAll("[^0-9]*", "");
		if (n.length() != DIGIT_COUNT) {
			return false;
		}
		int i; // just count
		int digit; // A number digit
		int coeficient; // A coeficient
		int sum; // The sum of (Digit * Coeficient)
		int foundDv; // The found Dv (Chek Digit)
		int dv = Integer.parseInt(String.valueOf(n.charAt(n.length() - 1)));
		sum = 0;
		coeficient = 2;
		for (i = n.length() - 2; i >= 0; i--) {
			digit = Integer.parseInt(String.valueOf(n.charAt(i)));
			sum += digit * coeficient;
			coeficient++;
			if (coeficient > 9) {
				coeficient = 2;
			}
		}
		foundDv = 11 - sum % 11;
		if (foundDv >= 10) {
			foundDv = 0;
		}
		return dv == foundDv;
	}

	private String number = null;

	public String getPisPasep() {
		if (number != null) {
			return number.replaceAll("([0-9]{3})([0-9]{5})([0-9]{2})([0-9])", "$1\\.$2\\.$3\\-$4");
		} else {
			return null;
		}
	}

	public boolean isValid() {
		return ePisPasepValido(number);
	}

	public void setPisPasep(String pisPasep) {
		if (pisPasep != null) {
			number = pisPasep.replaceAll("[^0-9]*", "");
		} else {
			number = null;
		}
	}

	public static boolean eEmail(String email) {
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		boolean matchFound = m.matches();

		return matchFound;
	}

}
