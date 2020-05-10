package com.example.crypto.services;

/**
 * The Interface CryptoService.
 */
public interface CryptoService {

	/**
	 * Decrypt.
	 *
	 * @param encryptedData the encrypted data
	 * @return the clear data
	 */
	String decrypt(String encryptedData);

	/**
	 * Encrypt.
	 *
	 * @param clearData the clear data
	 * @return the encrypted data
	 */
	String encrypt(String clearData);

}
