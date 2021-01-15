import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static int label_counter = 0;

    public List<String> parse(List<List<String>> input, List<String> filenames) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            result.addAll(parse(input.get(i), filenames.get(i)));
        }
        return result;
    }
    
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
                case "goto":
                    translation.add(_goto(commandList[1]));
                    break;
                case "label":
                    translation.add(gotoLabel(commandList[1]));
                    break;
                case "if-goto":
                    translation.add(ifgoto(commandList[1]));
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
        if (command.equals("constant")) {
            result =
                "// push constant\n" +
                "@" + value + "\n" + 
                "D=A\n" + 
                
                "@SP\n" + 
                "A=M\n" + 
                "M=D\n" + // *SP=i

                "@SP\n" + 
                "M=M+1\n"; // SP++
        } else if (command.equals("local")) {
            result = 
                "// push local " + value + "\n" +
                "@" + value + "\n" +
                "D=A\n" +
                "@LCL\n" +
                "A=M+D\n" +  // addr = @command + i
                "D=M\n" +
                
                "@SP\n" +
                "A=M\n" +
                "M=D\n" + // *SP = *addr

                "@SP\n" +
                "M=M+1\n";
        } else if (command.equals("argument")) {
            result = 
                "// push argument " + value + "\n" +
                "@" + value + "\n" +
                "D=A\n" +
                "@ARG\n" +
                "A=M+D\n" +  // addr = @command + i
                "D=M\n" +
                
                "@SP\n" +
                "A=M\n" +
                "M=D\n" + // *SP = *addr

                "@SP\n" +
                "M=M+1\n";
        } else if (command.equals("this")) {
            result = 
                "// push this " + value + "\n" +
                "@" + value + "\n" +
                "D=A\n" +
                "@THIS\n" +
                "A=M+D\n" +  // addr = @command + i
                "D=M\n" +
                
                "@SP\n" +
                "A=M\n" +
                "M=D\n" + // *SP = *addr

                "@SP\n" +
                "M=M+1\n";
        } else if (command.equals("that")) {
            result =
                "// push that " + value + "\n" +
                "@" + value + "\n" +
                "D=A\n" +
                "@THAT\n" +
                "A=M+D\n" +  // addr = @command + i
                "D=M\n" +

                "@SP\n" +
                "A=M\n" +
                "M=D\n" + // *SP = *addr

                "@SP\n" +
                "M=M+1\n";
        } else if (command.equals("static")) {
            result =
                "// push static\n" +
                "@" + filename + "." + value + "\n" +
                "D=M\n" +

                "@SP\n" +
                "A=M\n" +
                "M=D\n" + // *SP = *addr

                "@SP\n" +
                "M=M+1\n";
        } else if (command.equals("temp")) {
            result =
                "// push temp " + value + "\n" +
                "@" + value + "\n" +
                "D=A\n" +
                "@5\n" +
                "A=A+D\n" +  // addr = @command + i
                "D=M\n" +
                
                "@SP\n" +
                "A=M\n" +
                "M=D\n" + // *SP = *addr

                "@SP\n" +
                "M=M+1\n";
        } else if (command.equals("pointer")) {
            result = 
                "// push pointer " + value + "\n" +
                "@" + value + "\n" +
                "D=A\n" +
                "@3\n" +
                "A=A+D\n" +  // addr = @command + i
                "D=M\n" +

                "@SP\n" +
                "A=M\n" +
                "M=D\n" + // *SP = *addr

                "@SP\n" +
                "M=M+1\n";
        } else {
            result = "***do not recognize command***";
        }
        return result;
    }
    private String pop(String[] commandList, String filename) {
        String result = "";
        String command = commandList[1];
        String value = commandList[2];
        if (command.equals("local")) {
            result = 
                "// pop local " + value + "\n" +
                "@" + value + "\n" +  
                "D=A\n" +
                "@LCL\n" + 
                "D=M+D\n" + 

                "@R13\n" +
                "M=D\n" +

                "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +

                "@R13\n" +
                "A=M\n" +
                "M=D\n";    
        } else if (command.equals("argument")) {
            result = 
                "// pop argument " + value + "\n" +
                "@" + value + "\n" +  
                "D=A\n" +
                "@ARG\n" + 
                "D=M+D\n" + 

                "@R13\n" +
                "M=D\n" +

                "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +

                "@R13\n" +
                "A=M\n" +
                "M=D\n";    
        } else if (command.equals("this")) {
            result = 
                "// pop this " + value + "\n" +
                "@" + value + "\n" +  
                "D=A\n" +
                "@THIS\n" + 
                "D=M+D\n" + 

                "@R13\n" +
                "M=D\n" +

                "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +

                "@R13\n" +
                "A=M\n" +
                "M=D\n";   
        } else if (command.equals("that")) {
            result =
                "// pop that " + value + "\n" +
                "@" + value + "\n" +  
                "D=A\n" +
                "@THAT\n" + 
                "D=M+D\n" + 

                "@R13\n" +
                "M=D\n" +

                "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +

                "@R13\n" +
                "A=M\n" +
                "M=D\n"; 
        } else if (command.equals("static")) {
            result =
                "// pop static\n" +
                "@SP\n" + 
                "A=M-1\n" + 
                "D=M\n" +
                "@" + filename + "." + value + "\n" + 
                "M=D\n" +

                "@SP\n" +
                "M=M-1\n";
        } else if (command.equals("temp")) {
            result =
                "// pop temp " + value + "\n" +
                "@" + value + "\n" +  
                "D=A\n" +
                "@5\n" + 
                "D=A+D\n" + 

                "@R13\n" +
                "M=D\n" +

                "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +

                "@R13\n" +
                "A=M\n" +
                "M=D\n";    
        } else if (command.equals("pointer")) {
            result = 
                "// pop pointer " + value + "\n" +
                "@" + value + "\n" +  
                "D=A\n" +
                "@THIS\n" + 
                "D=A+D\n" + 

                "@R13\n" +
                "M=D\n" +

                "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +

                "@R13\n" +
                "A=M\n" +
                "M=D\n";   
        } else {
            result = "***do not recognize command***";
        }
       return result;
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
    private String gotoLabel(String label) {
        return "(" + label + ")\n";
    }
    private String _goto(String label) {
        return 
            "@" + label + "\n0;JMP\n";
    }
    
    private String ifgoto(String label) {
        return 
            "@SP\n" +
            "AM=M-1\n" + 
            "D=M\n" +
            "@" + label + "\n" +
            "D;JMP\n";
    }
}
