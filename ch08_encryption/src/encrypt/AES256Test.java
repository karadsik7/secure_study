package encrypt;

public class AES256Test {

	public static void main(String[] args) {
		String password = "qwerasdf1541687!#$A";
		
		AES256Encryptor encryptor = new AES256Encryptor();
		
		String encryptedPassword = encryptor.aesEncrypt(password);
		System.out.println(encryptedPassword);
		
		String decrytpedPassword = encryptor.aesDecrypt(encryptedPassword);
		System.out.println(decrytpedPassword);
		System.out.println(password.equals(decrytpedPassword));
	}
	
}
