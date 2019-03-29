package reader;

import java.io.File;

public abstract class DecryptorDecorator implements FileReader{
	
	protected FileReader reader;
	
	public DecryptorDecorator(FileReader reader) {
		this.reader = reader;
	}
	
	@Override
	public File getFile() {
		return reader.getFile();
	}
}
