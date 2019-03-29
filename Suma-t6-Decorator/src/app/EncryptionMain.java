package app;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import reader.Decryptor;
import reader.FileReader;
import reader.TextFromFile;
import writer.Encrypter;
import writer.FileWriter;
import writer.TextToFile;

public class EncryptionMain {

	public static void main(String[] args) {
		try {
			
			FileWriter writer = new TextToFile("plainText.txt");
			writer.writeIntoFile("plain");
			
			FileReader fReader = new TextFromFile("plainText.txt");
			System.out.println("read plain text: " + fReader.readFile());
			
			System.out.println("---Encrypted---");
			
			SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
			FileWriter encrypter = new Encrypter(new TextToFile("storedText.txt"), secretKey);
			encrypter.writeIntoFile("AABB salattu teksti");
			
			
			
			FileReader reader = new TextFromFile("storedText.txt");
			FileReader decryptor = new Decryptor(reader, secretKey);
			System.out.println("Decrypted: " + decryptor.readFile());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
