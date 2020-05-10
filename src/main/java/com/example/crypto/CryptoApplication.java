package com.example.crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.crypto.services.CryptoService;

import lombok.extern.java.Log;

@SpringBootApplication
@Log
public class CryptoApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("Files")
	private CryptoService cryptoService;

	public static void main(String[] args) {
		SpringApplication.run(CryptoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String data = "abcdefghijklmnopqrstuvwxyz1234567890";
		String encryptedData = cryptoService.encrypt(data);
		log.info("Encrypted data: " + encryptedData);
		log.info("Decrypted data: " + cryptoService.decrypt(encryptedData));
	}

}
