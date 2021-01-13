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

// subtract
@SP
AM=M-1
D=M
@SP
AM=M-1
M=M-D
@SP
M=M+1

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
M=M-1
A=M
D=M
@SP
AM=M-1
M=D+M
@SP
M=M+1