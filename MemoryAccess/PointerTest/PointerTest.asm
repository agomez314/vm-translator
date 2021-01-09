// push constant
@3030
D=A
@SP
A=M
M=D
@SP
M=M+1

// push that
@that
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push constant
@3040
D=A
@SP
A=M
M=D
@SP
M=M+1

// push that
@that
D=M
@1
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push constant
@32
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop this
@this
D=M
@2
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R13
A=M
M=D
@SP
M=M-1

// push constant
@46
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop that
@that
D=M
@6
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R13
A=M
M=D
@SP
M=M-1

// push that
@that
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push that
@that
D=M
@1
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1