import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static int label_counter = 0;
    public List<String> parse(List<String> input, String filename) {
        List<String> translation = new ArrayList<>();
        for (String line : input) {
            String[] commandList = line.split(" ");
            
            switch (commandList[0]) {
                case "push":
                    translation.add(push(commandList, filename));
                    break;
                case "pop":
                    translation.add(pop(commandList, filename));
                    break;
                case "add":
                    translation.add(add());
                    break;
                case "sub":
                    translation.add(sub());
                    break;
                case "eq":
                    translation.add(equals());
                    label_counter++;
                    break;
                case "lt":
                    translation.add(lessThan());
                    label_counter++;
                    break;
                case "gt":
                    translation.add(moreThan());
                    label_counter++;
                    break;
                case "neg":
                    translation.add(negate());
                    break;
                case "and":
                    translation.add(and());
                    break;
                case "or":
                    translation.add(or());
                    break;
                case "not":
                    translation.add(not());
                    break;
                default:
                    break;
            }
        }
        return translation;
    }
    
    /**
     * push: increment SP in memory segment, put number in memory segment.
     * 
     * @param stackCommand the line segment split into its atomic commands
     * @return a valid hack machine language push command
     */
    private String push(String[] commandList, String filename) {
        String result = "";
        String command = commandList[1];
        String value = commandList[2];
        switch (command) {
            case "constant":
                result = pushConstant(value);
                break;
            case "local":
            case "argument":
            case "this":
            case "that":
                result = pushPattern(value, mapCommand(command));
                break;
            case "static":
                result = pushStatic(value, filename);
                break;
            case "temp":
                result = pushTemp(value);
                break;
            case "pointer":
                result = value.equals(String.valueOf(0)) ? pushPattern("0", "THIS") : pushPattern("1", "THAT");
                break;
            default:
                break;
        }
        return result;
    }
    private String pop(String[] commandList, String filename) {
        String result = "";
        String command = commandList[1];
        String value = commandList[2];
        switch (command) {
            case "local":
            case "argument":
            case "this":
            case "that":
                result = popPattern(value, mapCommand(command));
                break;
            case "static":
                result = popStatic(value, filename);
                break;
            case "temp":
                result = popTemp(value);
                break;
            case "pointer":
                result = value.equals(String.valueOf(0)) ? popPattern("0", "THIS") : popPattern("1", "THIS");
                break;
            default:
                break;
        }
        return result;
    }
    private String pushConstant(String constant) {
        return 
            "// push constant\n" +
            "@" + constant + "\n" + 
            "D=A\n" + 
            
            "@SP\n" + 
            "A=M\n" + 
            "M=D\n" + // *SP=i

            "@SP\n" + 
            "M=M+1\n"; // SP++
    }
    private String pushPattern(String constant, String command) {
        return
            "// push " + command + " " + constant + "\n" +
            "@" + command + "\n" +
            "D=M\n" +
            "@" + constant + "\n" +
            "A=D+A\n" + 
            "D=M\n" +  // addr = @command + i

            "@SP\n" +
            "A=M\n" +
            "M=D\n" + // *SP = *addr

            "@SP\n" +
            "M=M+1\n";
    }
    private String popPattern(String constant, String command) {
        return 
            "// pop " + command + " " + constant + "\n" +
            "@" + command + "\n" +  
            "D=M\n" +
            "@" + constant + "\n" + 
            "D=D+A\n" + 
            "@R13\n" +
            "M=D\n" +

            "@SP\n" +
            "M=M-1\n" +

            "@SP\n" +
            "A=M\n" +
            "D=M\n" +

            "@R13\n" +
            "A=M\n" +
            "M=D\n";            
    }
    private String popStatic(String constant, String filename) {
        return 
            "// pop static\n" +
            "@SP\n" + 
            "A=M-1\n" + 
            "D=M\n" +
            "@" + filename + "." + constant + "\n" + 
            "M=D\n" +

            "@SP\n" +
            "M=M-1\n";
    }
    private String pushStatic(String constant, String filename) {
        return 
            "// push static\n" +
            "@" + filename + "." + constant + "\n" +
            "D=M\n" +

            "@SP\n" +
            "A=M\n" +
            "M=D\n" + // *SP = *addr

            "@SP\n" +
            "M=M+1\n";
    }
    private String pushTemp(String constant) {
        return 
            "// push temp\n" +
            "@5\n" +
            "D=A\n" +
            "@" + constant + "\n" +
            "A=D+A\n" + 
            "D=M\n" +  // addr = @LCL + i

            "@SP\n" +
            "A=M\n" +
            "M=D\n" + // *SP = *addr

            "@SP\n" +
            "M=M+1\n";
    }
    String popTemp(String constant) {
        return 
            "// pop temp\n" +
            "@5\n" + 
            "D=A\n" +
            "@" + constant + "\n" +
            "D=D+A\n" +
            "@R13\n" +
            "M=D\n" +

            "@SP\n" +
            "A=M-1\n" +
            "D=M\n" +
            "@R13\n" +
            "A=M\n" +
            "M=D\n" +

            "@SP\n" +
            "M=M-1\n";
    }
    String add() {
        return 
            "// add\n" +
            "@SP\n" +
            "M=M-1\n" +
            "A=M\n" +
            "D=M\n" +
            "@SP\n" +
            "AM=M-1\n" +
            "M=D+M\n" +
            "@SP\n" +
            "M=M+1\n";
    }
    String sub() {
        return 
             "// subtract\n" +
            "@SP\n" +
            "AM=M-1\n" +
            "D=M\n" +
            "@SP\n" +
            "AM=M-1\n" +
            "M=M-D\n" +
            "@SP\n" +
            "M=M+1\n";
    }
    private String equals() {
        return 
            "// equals\n" +
            "@SP\n" +
            "AM=M-1\n" +
            "D=M\n" +
            "@SP\n" +
            "A=M-1\n" +
            "D=M-D\n" +
            "M=-1\n\n" +
            "@CONTINUE" + label_counter + "\n" +
            "D;JEQ\n\n" +
            "@SP\n" +
            "A=M-1\n" +
            "M=0\n" +
            "(CONTINUE" + label_counter + ")\n";
    }
    private String lessThan() {
        return
            "// less than\n" +
            "@SP\n" +
            "AM=M-1\n" +
            "D=M\n" +
            "@SP\n" +
            "A=M-1\n" +
            "D=M-D\n" +
            "M=-1\n\n" +
            "@CONTINUE" + label_counter + "\n" +
            "D;JLT\n\n" +
            "@SP\n" +
            "A=M-1\n" +
            "M=0\n" +
            "(CONTINUE" + label_counter + ")\n";
    }
      private String moreThan() {
        return
            "// more than\n" +
            "@SP\n" +
            "AM=M-1\n" +
            "D=M\n" +
            "@SP\n" +
            "A=M-1\n" +
            "D=M-D\n" +
            "M=-1\n\n" +
            "@CONTINUE" + label_counter + "\n" +
            "D;JGT\n\n" +
            "@SP\n" +
            "A=M-1\n" +
            "M=0\n" +
            "(CONTINUE" + label_counter + ")\n";
    }
    private String negate() {
        return 
            "// negate\n" +
            "@SP\n" +
            "A=M-1\n" +
            "M=-M\n";
    }
    private String not() {
        return 
            "// not\n" +
            "@SP\n" +
            "A=M-1\n" +
            "M=!M\n";
    }
    private String and() {
        return 
            "// not\n" +
            "@SP\n" +
            "AM=M-1\n" +
            "D=M\n" +
            "@SP\n" +
            "A=M-1\n" +
            "M=D&M\n";
    }
    private String or() {
        return 
            "// not\n" +
            "@SP\n" +
            "AM=M-1\n" +
            "D=M\n" +
            "@SP\n" +
            "A=M-1\n" +
            "M=D|M\n";
    }
    private String mapCommand(String command) {
        switch(command) {
            case "local":
                return "LCL";
            case "argument":
                return "ARG";
            case "this":
                return "THIS";
            case "that":
                return "THAT";
            default:
                return "";
        }
    }
}
