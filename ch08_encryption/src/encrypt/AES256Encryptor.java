package encrypt;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES256Encryptor {
	
	private String iv;
	private Key keySpec;
	
	public AES256Encryptor() {
		try {
			String key = "aes256-test-key!!";
			this.iv = key.substring(0, 16);
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("utf-8");
			int leng = b.length;
			if(leng > keyBytes.length) {
				leng = keyBytes.length;
			}
			System.arraycopy(b, 0, keyBytes, 0, leng);
			
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			
			this.keySpec = keySpec;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String aesEncrypt(String str) {
		String encryptedStr = null;
		try {
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5padding");
			c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
			byte[] encrypted = c.doFinal(str.getBytes("utf-8"));
			encryptedStr = new String(Base64.encodeBase64(encrypted));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedStr;
	}
	
	public String aesDecrypt(String encryptedStr) {
		String decryptedStr = null;
		try {
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5padding");
			c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("utf-8")));
			byte[] byteStr = Base64.decodeBase64(encryptedStr.getBytes());
			decryptedStr = new String(c.doFinal(byteStr), "utf-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedStr;
	}
	
	
	
	
	
	
}
