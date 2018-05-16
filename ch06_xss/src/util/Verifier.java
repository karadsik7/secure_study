package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verifier {
	
	public static boolean idVerify(String id) {
		String policy = "([A-Za-z0-9]{5,30})";
		/*Pattern pattern = Pattern.compile(policy);
		Matcher matcher = pattern.matcher(id);*/
		return Pattern.compile(policy).matcher(id).matches();
	}

	public static boolean passwordVerify(String password) {
		String policy = "((?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,30})";
		return Pattern.compile(policy).matcher(password).matches();
	}
	
	public static boolean nameVerify(String name) {
		String policy = "([가-힣]{2,10})";
		return Pattern.compile(policy).matcher(name).matches();
	}
	
}
