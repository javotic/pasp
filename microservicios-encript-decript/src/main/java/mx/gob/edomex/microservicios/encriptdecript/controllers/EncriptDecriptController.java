package mx.gob.edomex.microservicios.encriptdecript.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.DigestInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jolmv
 * @version v1.0
 * @date Abril, 06, 2020.
 * @description the common method of encrypt and decrypt
 */
//@CrossOrigin({ "http://localhost:4200" })
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class EncriptDecriptController {

	private static final String CHARSET = "UTF-8";
	private static final int DEFAULT_KEY_SIZE = 128;

	public enum ALGORITHM {
		// following algorithm is for encrypt and decrypt
		AES("AES"), DES("DES"),

		// following algorithm is for message digest
		MD2("MD2"), MD5("MD5");

		private final String text;

		ALGORITHM(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	/**
	 * encrypt content
	 * 
	 * @param content  content
	 * @param password password
	 * @return encrypted content
	 */
	@GetMapping("/contenidoencriptar/{content}/password/{password}/algoritmo/{algorithm}")
	public static String encrypt(@PathVariable String content, @PathVariable String password,
			@PathVariable ALGORITHM algorithm) {
		String valEncript = "Como parametro obligatorio en necesario el algoritmo de encriptación";
		System.out.println("Dentro del metodo encrypt()");

		if (algorithm.toString().equals("MD2") || algorithm.toString().equals("MD5")) {
			System.out.println("Entro a encriptar MD2 o  MD5");
			// valEncript=md5(content);
			valEncript = encrypttMD5(content);
		}

		if (algorithm.toString().equals("AES") || algorithm.toString().equals("DES")) {
			System.out.println("Entro a encriptar AES o DES");
			valEncript = encryptt(content, password, null, algorithm);
		}

		System.out.println("Valor Encriptado " + algorithm + ":  " + valEncript);
		return valEncript;
	}

	/**
	 * encrypt content
	 * 
	 * @param content   content
	 * @param password  password
	 * @param iv        initialization vector
	 * @param algorithm encrypt algorithm: AES, DES
	 * @return encrypted content
	 */
	public static String encryptt(final String content, final String password, final byte[] iv,
			final ALGORITHM algorithm) {
		try {
			final SecretKeySpec keySpec = generateKey(password, algorithm);
			Cipher cipher = Cipher.getInstance(algorithm.toString());
			if (iv == null) {
				cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			} else {
				IvParameterSpec ivSpec = new IvParameterSpec(iv);
				cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			}
			byte[] result = cipher.doFinal(content.getBytes(CHARSET));
			return byte2Hex(result);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypttMD5(String texto) {

		String secretKey = "qualityinfosolutions"; // llave para encriptar datos
		String base64EncryptedString = "";

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] plainTextBytes = texto.getBytes("utf-8");
			byte[] buf = cipher.doFinal(plainTextBytes);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			base64EncryptedString = new String(base64Bytes);

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}

	/**
	 * decrypt content
	 * 
	 * @param encryptedContent encrypted content
	 * @param password         password
	 * @return decrypted content
	 * @throws Exception
	 */
	@GetMapping("/contenidodesencriptar/{encryptedContent}/password/{password}/algoritmo/{algorithm}")
	public ResponseEntity<?> decrypt(@PathVariable String encryptedContent, @PathVariable String password,
			@PathVariable ALGORITHM algorithm) throws Exception {
		String valDecript = "Error in descrypt in method decrypt()";
		System.out.println("Dentro del metodo decrypt()");

		if (algorithm.toString().equals("MD2") || algorithm.toString().equals("MD5")) {
			System.out.println("Entro a Desencriptar MD2 o  MD5");
			valDecript = decryptMD5(encryptedContent);
		} else {
			System.out.println("Entro a Desencriptar AES o  DES");
			valDecript = decrypt(encryptedContent, password, null, algorithm);
		}

		System.out.println("Valor Desencriptado " + algorithm + ":  " + valDecript);

		return ResponseEntity.ok().body(valDecript);
	}

	/**
	 * decrypt content
	 * 
	 * @param encryptedContent encrypted content
	 * @param password         password
	 * @param iv               initialization vector
	 * @param algorithm        decrypt algorithm: AES, DES
	 * @return decrypted content
	 */
	public static String decrypt(final String encryptedContent, final String password, final byte[] iv,
			final ALGORITHM algorithm) {
		try {
			final SecretKeySpec keySpec = generateKey(password, algorithm);
			Cipher cipher = Cipher.getInstance(algorithm.toString());
			if (iv == null) {
				cipher.init(Cipher.DECRYPT_MODE, keySpec);
			} else {
				IvParameterSpec ivSpec = new IvParameterSpec(iv);
				cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
			}
			byte[] result = cipher.doFinal(hex2byte(encryptedContent));
			return new String(result, CHARSET);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decryptMD5(String textoEncriptado) throws Exception {

		String secretKey = "qualityinfosolutions"; // llave para encriptar datos
		String base64EncryptedString = "";

		try {
			byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			Cipher decipher = Cipher.getInstance("DESede");
			decipher.init(Cipher.DECRYPT_MODE, key);

			byte[] plainText = decipher.doFinal(message);

			base64EncryptedString = new String(plainText, "UTF-8");

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}

	private static SecretKeySpec generateKey(final String password, final ALGORITHM algorithm) {
		try {
			final KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm.toString());
			SecureRandom secureRandom;
			if (isAndroidPlatform()) {
				secureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
			} else {
				secureRandom = SecureRandom.getInstance("SHA1PRNG");
			}
			secureRandom.setSeed(password.getBytes());
			int keySize = DEFAULT_KEY_SIZE;
			switch (algorithm) {
			case DES:
				keySize = 56;
				break;
			}
			keyGenerator.init(keySize, secureRandom);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] encodedFormat = secretKey.getEncoded();
			return new SecretKeySpec(encodedFormat, algorithm.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * get the md5 of content
	 * 
	 * @param content get the md5 using this content
	 * @return the computed md5 hash value
	 */
	public static String md5(String content) {
		return digest(content, ALGORITHM.MD5);
	}

	/**
	 * get the file md5
	 * 
	 * @param file get the md5 using this file
	 * @return the computed md5 hash value
	 */
	public static String md5(File file) {
		return digest(file, ALGORITHM.MD5);
	}

	/**
	 * get the hash value of content
	 * 
	 * @param content   get the hash value using this content
	 * @param algorithm hash algorithm
	 * @return the computed hash value
	 */
	public static String digest(String content, ALGORITHM algorithm) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm.toString());
			messageDigest.update(content.getBytes(CHARSET));
			byte[] result = messageDigest.digest();
			return byte2Hex(result);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * get the hash value of a file
	 * 
	 * @param file      get the hash value using this file
	 * @param algorithm hash algorithm
	 * @return the computed hash value
	 */
	public static String digest(File file, ALGORITHM algorithm) {
		DigestInputStream inputStream = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm.toString());
			inputStream = new DigestInputStream(new FileInputStream(file), messageDigest);
			byte[] buffer = new byte[128 * 1024];
			while (inputStream.read(buffer) > 0)
				; // message digest will update during reading file
			messageDigest = inputStream.getMessageDigest();
			byte[] result = messageDigest.digest();
			return byte2Hex(result);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	// more about message digest:
	// http://commons.apache.org/proper/commons-codec/archives/1.9/apidocs/src-html/org/apache/commons/codec/digest/DigestUtils.html

	private static String byte2Hex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			String hex = Integer.toHexString(b & 0xFF);
			if (hex.length() == 1) {
				hex = "0" + hex; // one byte to double-digit hex
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	private static byte[] hex2byte(String hex) {
		if (hex == null || hex.length() < 1) {
			return null;
		}
		int len = hex.length() / 2;
		byte[] bytes = new byte[len];
		for (int i = 0; i < len; i++) {
			int high = Integer.parseInt(hex.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hex.substring(i * 2 + 1, i * 2 + 2), 16);
			bytes[i] = (byte) (high * 16 + low);
		}
		return bytes;
	}

	/**
	 * for android platform compatibility
	 * 
	 * @return is android platform
	 */
	private static boolean isAndroidPlatform() {
		Properties properties = System.getProperties();
		return properties.getProperty("java.vendor").contains("Android")
				|| properties.getProperty("java.vm.vendor").contains("Android");
	}

}