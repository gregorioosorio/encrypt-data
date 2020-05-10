package com.example.crypto.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Files")
public class FilesCryptoService implements CryptoService {

	private PublicKey publicKey;
	private PrivateKey privateKey;

	private Cipher encryptCipher;
	private Cipher decryptCipher;

	private static final String PRIVATE_KEY_PATH = "keys/private_key.der";
	private static final String PUBLIC_KEY_PATH = "keys/tst_public.der";

	@PostConstruct
	void init() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, NoSuchPaddingException,
			InvalidKeyException {

		File privateKeyFile = new ClassPathResource(PRIVATE_KEY_PATH).getFile();
		byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
		privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

		File publicKeyFile = new ClassPathResource(PUBLIC_KEY_PATH).getFile();
		byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
		publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));

		encryptCipher = Cipher.getInstance("RSA");
		encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

		decryptCipher = Cipher.getInstance("RSA");
		decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

	}

	@Override
	public String encrypt(String clearData) {
		String result = null;
		try {
			byte[] encryptedData = encryptCipher.doFinal(clearData.getBytes());
			result = new String(Base64.encodeBase64(encryptedData));
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String decrypt(String encryptedData) {
		String result = null;
		try {
			byte[] decryptedData = decryptCipher.doFinal(Base64.decodeBase64(encryptedData));
			result = new String(decryptedData);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
