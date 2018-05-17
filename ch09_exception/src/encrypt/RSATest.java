package encrypt;

public class RSATest {
	
	public static void main(String[] args) {
		String password = "hihi";
		RSAEcnryptor encryptor = new RSAEcnryptor();
		String encryptedPassword = encryptor.rsaEncrypt(password);
		System.out.println(encryptedPassword);
		String decryptedPassword = encryptor.rsaDecrypt(encryptedPassword);
		System.out.println(decryptedPassword);
		System.out.println(decryptedPassword.equals(password));
		
	}
	
}
