package reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Decryptor extends DecryptorDecorator {

	private SecretKey key;
	private Cipher c;
	
	public Decryptor(FileReader reader, SecretKey key) {
		super(reader);
		this.key = key;
		try {
			c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String readFile() {
		String content = "";
		try(FileInputStream fIn = new FileInputStream(super.getFile())) {
			byte[] iv = new byte[16];
			fIn.read(iv);
			c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
			
			try(
					CipherInputStream cIn = new CipherInputStream(fIn, c);
					InputStreamReader inReader = new InputStreamReader(cIn);
					BufferedReader reader = new BufferedReader(inReader);)
			{
				StringBuilder sb = new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					sb.append(line);
				}
				content = sb.toString();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		
		return content;
	}

}
