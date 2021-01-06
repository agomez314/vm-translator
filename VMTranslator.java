import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class VMTranslator {
   
    public static void main(String[] args) throws IOException {
        CodeWriter writer = new CodeWriter();
        Parser parser = new Parser();

        String filename = args[0].toString();
        List<String> input = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);

        List<String> processedInput = writer.processInput(input);
        List<String> translation = parser.parse(processedInput, filename.substring(0, filename.length() - 2));
        writer.write(translation, filename);       
    }
}