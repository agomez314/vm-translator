import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class VMTranslator {

    public static void main(String[] args) throws IOException {
        CodeWriter writer = new CodeWriter();
        Parser parser = new Parser();

        String filename = args[0].toString();
        boolean isFile = filename.contains(".vm");
        List<String> translation = new ArrayList<>();
        if (isFile) {
            List<String> input= writer.readFile(filename);
            translation = parser.parse(input, filename);            
        } else {
            List<String> fileList = Files.walk(Paths.get(filename))
                                    .filter(Files::isRegularFile)
                                    .map(Path::toString)
                                    .collect(Collectors.toList());
            
            List<List<String>> input = writer.readDirectory(filename);             
            translation = parser.parse(input, fileList);
        }
        writer.write(translation, filename);
    }
}