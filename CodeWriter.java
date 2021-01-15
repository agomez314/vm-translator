import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CodeWriter {
    /**
     * readFile reads the contents of a single file
     * @param filename
     * @return text as an array of strings 
     * @throws IOException
     */
    public List<String> readFile(String filename) {
        List<String> result = new ArrayList<>();
        try {
            result = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result; 
    }
    /**
     * readDirectory reads the contents of a directory, combines them all, and
     * returns them as a single array of strings
     * 
     * @param filename
     * @return text as an array of strings
     * @throws IOException
     */
    public List<List<String>> readDirectory(String path) throws IOException {
        return Files.walk(Paths.get(path))
            .filter(Files::isRegularFile)
            .map(file -> readFile(file.toString()))
            .collect(Collectors.toList());
    }
    public List<String> processInput(List<String> input) {
        return input.stream()
            .filter(line -> !line.startsWith("//")) // remove comments
            .filter(line -> !line.equals("")) // remove empty lines
            .collect(Collectors.toList());
    }

    public void write(List<String> input, String filename) throws IOException {
        String newFilename;
        boolean isFile = filename.contains(".vm");
        if (isFile) {
            newFilename = filename.substring(0, filename.length() - 2) + "asm";
        } else {
            newFilename = filename.substring(0, filename.length()) + ".asm";
        }
        Writer fileWriter = new FileWriter(newFilename, false);
        fileWriter.write(String.join("\n", input).trim());
        fileWriter.close();
    }
}
