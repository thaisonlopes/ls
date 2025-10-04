package br.com.lopessolutions.base.criptografia;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import br.com.lopessolutions.base.exception.AltException;



/**
 * 
 * @author Thaison
 *
 */
public class Criptografia {

	private static final String METODO_ENCRIPTACAO = "AES";

	public static String getCriptografar(String value) {
		try {

			byte[] ByteKey = getKey();

			SecretKeySpec skeySpec = new SecretKeySpec(ByteKey, METODO_ENCRIPTACAO);
			Cipher cipher = Cipher.getInstance(METODO_ENCRIPTACAO);

			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(value.getBytes());

			Base64.Encoder e = Base64.getEncoder();

			return e.encodeToString(encrypted);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			throw new AltException("Ocorreu um erro ao tentar criptografar os dados. Erro: " + e.getMessage());
		}
	}

	public static String getDescriptografar(String encrypted) {
		byte[] decrypted = null;
		byte[] ByteKey = getKey();

		try {

			SecretKeySpec skeySpec = new SecretKeySpec(ByteKey, METODO_ENCRIPTACAO);

			byte[] decoded = Base64.getDecoder().decode(encrypted);

			Cipher cipher = Cipher.getInstance(METODO_ENCRIPTACAO);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			decrypted = cipher.doFinal(decoded);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			throw new AltException("Ocorreu um erro ao tentar descriptografar os dados. Erro: " + e.getMessage());
		}

		return new String(decrypted);

	}

	private static byte[] getKey() {
		return new byte[] { 85, 10, 0, -25, 68, 88, 46, 37, 107, 48, 10, -1, -37, -90, 70, -36 };
	}

}
