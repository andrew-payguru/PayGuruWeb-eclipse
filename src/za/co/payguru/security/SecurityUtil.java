package za.co.payguru.security;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SecurityUtil {

	
	/**
	 * Generates an HMAC-SHA256 signature.
	 *
	 * @param data The data to hash (e.g., the JSON body).
	 * @param secret The secret key used for hashing.
	 * @return The HMAC-SHA256 signature as a hexadecimal string.
	 * @throws Exception If an error occurs during hashing.
	 */
	public static String generateHmacSHA256(String data, String secret) throws Exception {
		Mac sha256Hmac = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
		sha256Hmac.init(secretKey);
		byte[] hashBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));

		// Convert hash bytes to a hexadecimal string
		StringBuilder hashHex = new StringBuilder();
		for (byte b : hashBytes) {
			hashHex.append(String.format("%02x", b));
		}
		return hashHex.toString();
	}
	 public static String encryptRSA_EC_PKCS1(String apiKey, String publicKeyPEM) throws Exception {
	        String basePrivateKey = apiKey;         
	        PublicKey publicKey = getPublicKeyFromPEM(publicKeyPEM);
	        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); 
	        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	        byte[] encryptedBytes = cipher.doFinal(basePrivateKey.getBytes());
	        return Base64.getEncoder().encodeToString(encryptedBytes);
	    }
	    
	    private static PublicKey getPublicKeyFromPEM(String pem) throws Exception {
	        String publicKeyPEM = pem.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replaceAll("\\s", "");
	        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
	        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
	        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        return keyFactory.generatePublic(spec);
	    }
}
