package writer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextToFile implements FileWriter{

	private File file;
	
	public TextToFile(String location) {
		file = new File(location);
		if(!this.file.exists()) {
			try {
				file.createNewFile();
				System.out.println("created new file");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void writeIntoFile(String text) {
		System.out.println("storing: " + text);
		Path path = Paths.get(file.getPath());
		try {
			Files.write(path, text.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public File getFile() {
		return this.file;
	}

}
