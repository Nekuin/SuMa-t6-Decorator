package writer;

import java.io.File;

public interface FileWriter {
	public void writeIntoFile(String text);
	public File getFile();
}
