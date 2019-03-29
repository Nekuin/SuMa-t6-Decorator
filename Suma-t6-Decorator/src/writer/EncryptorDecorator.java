package writer;

import java.io.File;

abstract class EncryptorDecorator implements FileWriter{
	
	protected FileWriter writer;
	
	public EncryptorDecorator(FileWriter writer) {
		this.writer = writer;
	}
	
	@Override
	public File getFile() {
		return writer.getFile();
	}
}
