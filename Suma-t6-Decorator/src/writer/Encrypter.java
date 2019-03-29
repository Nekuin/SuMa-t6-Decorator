package writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Encrypter extends EncryptorDecorator{
	
	private Cipher c;
	private SecretKey key;
	private File file;

	public Encrypter(FileWriter writer, SecretKey key) {
		super(writer);
		try {
			this.c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			this.key = key;
			this.file = super.getFile();
			if(!this.file.exists()) {
				this.file.createNewFile();
				System.out.println("file created");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeIntoFile(String text) {
		try {
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] iv = c.getIV();
			try(FileOutputStream fileOut = new FileOutputStream(file);
					CipherOutputStream cOut = new CipherOutputStream(fileOut, c)){
				fileOut.write(iv);
				cOut.write(text.getBytes());
				System.out.println("wrote into file");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}

}
