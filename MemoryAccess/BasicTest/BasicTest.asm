// push constant
@10
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop LCL
@LCL
D=M
@0
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
@21
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@22
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop ARG
@ARG
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

// pop ARG
@ARG
D=M
@1
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
@36
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop THIS
@THIS
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

// push constant
@42
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@45
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop THAT
@THAT
D=M
@5
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

// pop THAT
@THAT
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
@510
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop temp
@5
D=A
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

// push LCL
@LCL
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push THAT
@THAT
D=M
@5
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

// push ARG
@ARG
D=M
@1
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

// push THIS
@THIS
D=M
@6
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push THIS
@THIS
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

// push temp
@5
D=A
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