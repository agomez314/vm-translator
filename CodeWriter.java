import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

public class CodeWriter {
    public List<String> processInput(List<String> input) {
        return input.stream().filter(line -> !line.startsWith("//")) // remove comments
                .filter(line -> !line.equals("")) // remove empty lines
                .collect(Collectors.toList());
    }

    public void write(List<String> input, String filename) throws IOException {
        String newFilename = filename.substring(0, filename.length() - 2) + "asm";
        Writer fileWriter = new FileWriter(newFilename, false);
        fileWriter.write(String.join("\n", input).trim());
        fileWriter.close();
    }
}
