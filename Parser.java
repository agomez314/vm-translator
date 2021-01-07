import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<String> parse(List<String> input, String filename) {
        List<String> translation = new ArrayList<>();
        for (String line : input) {
            String[] command = line.split(" ");

            switch (command[0]) {
                case "push":
                    translation.add(push(command[1], command[2], filename));
                    break;
                case "pop":
                    translation.add(pop(command[1], command[2], filename));
                    break;
                case "add":
                    translation.add(add());
                    break;
                case "sub":
                    translation.add(sub());
                    break;
                // case "neg":
                //     translation.add(add());
                //     break;
                // case "lg":
                //     translation.add(add());
                //     break;
                // case "gt":
                //     translation.add(add());
                //     break;
                // case "eq":
                //     translation.add(add());
                //     break;
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
    private String push(String command, String value, String filename) {
        String result = "";
        switch (command) {
            case "constant":
                result = pushConstant(value);
                break;
            case "local":
                result = pushLocal(value);
                break;
            case "argument":
                result = pushArgument(value);
                break;
            case "this":
                result = pushThis(value);
                break;
            case "that":
                result = pushThat(value);
                break;
            case "static":
                result = pushStatic(value, filename);
                break;
            case "temp":
                result = pushTemp(value);
                break;
            case "pointer":
                result = pushPointer(value);
                break;
            default:
                break;
        }
        return result;
    }
    private String pop(String command, String value, String filename) {
        String result = "";
        switch (command) {
            case "local":
                result = popLocal(value);
                break;
            case "argument":
                result = popArgument(value);
                break;
            case "this":
                result = popThis(value);
                break;
            case "that":
                result = popThat(value);
                break;
            case "static":
                result = popStatic(value, filename);
                break;
            case "temp":
                result = popTemp(value);
                break;
            case "pointer":
                result = popPointer(value);
                break;
            default:
                break;
        }
        return result;
    }
    private String pushConstant(String constant) {
        return 
            "@" + constant + "\n" + 
            "D=A\n" + 
            
            "@SP\n" + 
            "A=M\n" + 
            "M=D\n" + // *SP=i

            "@SP\n" + 
            "M=M+1\n"; // SP++
    }
    private String pushLocal(String constant) {
        return
            "@LCL\n" +
            "D=M\n" +
            "@" + constant + "\n" +
            "A=D+A\n" + 
            "D=M\n" +  // addr = @LCL + i

            "@SP\n" +
            "A=M\n" +
            "M=D\n" + // *SP = *addr

            "@SP\n" +
            "M=M+1\n";
    }
    private String popLocal(String constant) {
        return 
            "@SP\n" + 
            "M=M-1\n" + 
            "A=M\n" + 
            "D=M\n" +
            "@LCL\n" + 
            "A=M\n" +
            "M=D\n";
    }
    private String pushArgument(String constant) {
        return 
            "@ARG\n" +
            "D=M\n" +
            "@" + constant + "\n" +
            "A=D+A\n" + 
            "D=M\n" +  // addr = @LCL + i

            "@SP\n" +
            "A=M\n" +
            "M=D\n" + // *SP = *addr

            "@SP\n" +
            "M=M+1\n";
    }
    private String popArgument(String constant) {
        return
            "@ARG\n" + 
            "D=M\n" +
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
    String pushThis(String constant) {
        return 
            "@THIS\n" +
            "D=M\n" +
            "@" + constant + "\n" +
            "A=D+A\n" + 
            "D=M\n" +  // addr = @LCL + i

            "@SP\n" +
            "A=M\n" +
            "M=D\n" + // *SP = *addr

            "@SP\n" +
            "M=M+1\n";
    }
    String popThis(String constant) {
        return 
           "@THIS\n" + 
            "D=M\n" +
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
    String pushThat(String constant) {
        return 
            "@THAT\n" +
            "D=M\n" +
            "@" + constant + "\n" +
            "A=D+A\n" + 
            "D=M\n" +  // addr = @LCL + i

            "@SP\n" +
            "A=M\n" +
            "M=D\n" + // *SP = *addr

            "@SP\n" +
            "M=M+1\n";
    }
    String popThat(String constant) {
        return
           "@THAT\n" + 
            "D=M\n" +
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
    private String popStatic(String constant, String filename) {
        return 
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
    String pushPointer(String constant) {
        String command = "";
        if (Integer.parseInt(constant) == 0) {
            // *SP = THIS,SP++
            command = pushThis(constant);
        } else {
            // *SP = THAT,SP++
            command = pushThat(constant);
        }
        return command;
    }
    String popPointer(String constant) {
        String command = "";
        if (Integer.parseInt(constant) == 0) {
            // SP--,THIS=*SP
            command = popThis(constant);
        } else {
            // SP--,THAT=*SP
            command = popThat(constant);
        }
        return command;
    }
    String add() {
        return 
            "@SP\n" +
            "A=M-1\n" +
            "D=M\n" +
            "@R13\n" +
            "M=D\n" +

            "@SP\n" +
            "M=M-1\n" +
            
            "@SP\n" +
            "A=M-1\n" +
            "D=M\n" +

            "@R13\n" +
            "D=D+M\n" +
            
            "@SP\n" +
            "A=M-1\n" +
            "M=D\n";
    }
    String sub() {
        return 
            "@SP\n" +
            "A=M-1\n" +
            "D=M\n" +
            "@R13\n" +
            "M=D\n" +

            "@SP\n" +
            "M=M-1\n" +
            
            "@SP\n" +
            "A=M-1\n" +
            "D=M\n" +

            "@R13\n" +
            "D=D-M\n" +
            
            "@SP\n" +
            "A=M-1\n" +
            "M=D\n";
    }
}
