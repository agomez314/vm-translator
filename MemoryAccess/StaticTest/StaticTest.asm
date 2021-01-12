// push constant
@111
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@333
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant
@888
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop static
@SP
A=M-1
D=M
@StaticTest.vm.8
M=D
@SP
M=M-1

// pop static
@SP
A=M-1
D=M
@StaticTest.vm.3
M=D
@SP
M=M-1

// pop static
@SP
A=M-1
D=M
@StaticTest.vm.1
M=D
@SP
M=M-1

// push static
@StaticTest.vm.3
D=M
@SP
A=M
M=D
@SP
M=M+1

// push static
@StaticTest.vm.1
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

// push static
@StaticTest.vm.8
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