package com.revature.util;


public class HashPassword {
	private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);

	public static String hash(String password) {
	    return bcrypt.hash(password);
	}

	public static boolean verifyHash(String password,String hashedPassword) {
		return bcrypt.verifyHash(password, hashedPassword);
	}
	
	public static String verifyAndUpdateHash(String password, String hashedPassword) {
	    return bcrypt.verifyAndUpdateHash(password, hashedPassword);
	}
}
