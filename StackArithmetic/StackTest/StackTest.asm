// push constant
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// equals
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
@EQUALS
D;JEQ
(EQUALS)
@SP
A=M-1
M=-1
3;JMP
@SP
A=M-1
M=0

// push constant
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@16
D=A
@SP
A=M
M=D
@SP
M=M+1

// equals
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
@EQUALS
D;JEQ
(EQUALS)
@SP
A=M-1
M=-1
3;JMP
@SP
A=M-1
M=0

// push constant
@16
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// equals
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
@EQUALS
D;JEQ
(EQUALS)
@SP
A=M-1
M=-1
3;JMP
@SP
A=M-1
M=0

// push constant
@892
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@892
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@57
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@31
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@53
D=A
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

// push constant
@112
D=A
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

// push constant
@82
D=A
@SP
A=M
M=D
@SP
M=M+1