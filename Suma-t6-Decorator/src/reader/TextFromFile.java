package reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TextFromFile implements FileReader{
	
	private Path path;
	private File file;
	
	public TextFromFile(String location) {
		this.path = Paths.get(location);
		this.file = new File(location);
	}

	@Override
	public String readFile() {
		try {
			List<String> lines = Files.readAllLines(path);
			StringBuilder b = new StringBuilder();
			for(String s : lines) {
				b.append(s);
			}
			return b.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public File getFile() {
		return this.file;
	}
	
}
