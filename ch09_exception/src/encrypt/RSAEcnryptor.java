package encrypt;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

import org.apache.tomcat.util.codec.binary.Base64;

public class RSAEcnryptor {

	private Key publicKey, privateKey;
	
	public RSAEcnryptor() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			KeyPair keyPair = keyPairGenerator.genKeyPair();
			
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			System.out.println(publicKey);
			System.out.println(privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String rsaEncrypt(String str) {
		String encryptedStr = null;
		
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] encrypted = cipher.doFinal(str.getBytes("utf-8"));
			encryptedStr = new String(Base64.encodeBase64(encrypted));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return encryptedStr;
	}
	
	public String rsaDecrypt(String encryptedStr) {
		String decryptedStr = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] byteStr = Base64.decodeBase64(encryptedStr);
			decryptedStr = new String(cipher.doFinal(byteStr), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return decryptedStr;
	}
	
	
	
	
	
	
}
