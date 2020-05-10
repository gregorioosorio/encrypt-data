package com.example.crypto.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Mock")
public class MockCryptoService implements CryptoService {

	@Override
	public String decrypt(String encryptedData) {
		return "mock clear data";
	}

	@Override
	public String encrypt(String clearData) {
		return "mock encrypted data";
	}

}
