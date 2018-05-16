package encrypt;

public class SHATest {
	
	public static void main(String[] args) {
		String password = "hihi";
		String encrypted = SHA256Encryptor.shaEncrypt(password);
		System.out.println(encrypted);
		
		String password2= "hih1";
		String encrypted2 = SHA256Encryptor.shaEncrypt(password2);
		System.out.println(encrypted2);
		
		System.out.println(encrypted.equals(encrypted2));
		
		
	}
	
}
