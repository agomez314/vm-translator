// push constant
@3030
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop THIS
@THIS
D=A
@R13
M=D
@SP
M=M-1
@SP
A=M
D=M
@R13
A=M
M=D

// push constant
@3040
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop THAT
@THAT
D=A
@R13
M=D
@SP
M=M-1
@SP
A=M
D=M
@R13
A=M
M=D

// push constant
@32
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop THIS 2
@THIS
D=M
@2
D=D+A
@R13
M=D
@SP
M=M-1
@SP
A=M
D=M
@R13
A=M
M=D

// push constant
@46
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop THAT 6
@THAT
D=M
@6
D=D+A
@R13
M=D
@SP
M=M-1
@SP
A=M
D=M
@R13
A=M
M=D

// push THIS
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1

// push THAT
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1

// add
@SP
A=M-1
D=M
@R13
M=D
@SP
M=M-1
@SP
A=M-1
D=M
@R13
D=D+M
@SP
A=M-1
M=D

// push THIS 2
@THIS
D=M
@2
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// sub
@SP
A=M-1
D=M
@R13
M=D
@SP
M=M-1
@SP
A=M-1
D=M
@R13
D=D-M
@SP
A=M-1
M=D

// push THAT 6
@THAT
D=M
@6
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// add
@SP
A=M-1
D=M
@R13
M=D
@SP
M=M-1
@SP
A=M-1
D=M
@R13
D=D+M
@SP
A=M-1
M=D