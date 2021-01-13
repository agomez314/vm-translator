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
AM=M-1
D=M
@SP
A=M-1
D=M-D
M=-1

@CONTINUE0
D;JEQ

@SP
A=M-1
M=0
(CONTINUE0)

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
AM=M-1
D=M
@SP
A=M-1
D=M-D
M=-1

@CONTINUE1
D;JEQ

@SP
A=M-1
M=0
(CONTINUE1)

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
AM=M-1
D=M
@SP
A=M-1
D=M-D
M=-1

@CONTINUE2
D;JEQ

@SP
A=M-1
M=0
(CONTINUE2)

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

// less than
@SP
AM=M-1
D=M
@SP
A=M-1
D=M-D
M=-1

@CONTINUE3
D;JLT

@SP
A=M-1
M=0
(CONTINUE3)

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

// less than
@SP
AM=M-1
D=M
@SP
A=M-1
D=M-D
M=-1

@CONTINUE4
D;JLT

@SP
A=M-1
M=0
(CONTINUE4)

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

// less than
@SP
AM=M-1
D=M
@SP
A=M-1
D=M-D
M=-1

@CONTINUE5
D;JLT

@SP
A=M-1
M=0
(CONTINUE5)

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

// more than
@SP
AM=M-1
D=M
@SP
A=M-1
D=M-D
M=-1

@CONTINUE6
D;JGT

@SP
A=M-1
M=0
(CONTINUE6)

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

// more than
@SP
AM=M-1
D=M
@SP
A=M-1
D=M-D
M=-1

@CONTINUE7
D;JGT

@SP
A=M-1
M=0
(CONTINUE7)

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

// more than
@SP
AM=M-1
D=M
@SP
A=M-1
D=M-D
M=-1

@CONTINUE8
D;JGT

@SP
A=M-1
M=0
(CONTINUE8)

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
AM=M-1
D=M
@SP
AM=M-1
M=D+M
@SP
M=M+1

// push constant
@112
D=A
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

// negate
@SP
A=M-1
M=-M

// not
@SP
AM=M-1
D=M
@SP
A=M-1
M=D&M

// push constant
@82
D=A
@SP
A=M
M=D
@SP
M=M+1

// not
@SP
AM=M-1
D=M
@SP
A=M-1
M=D|M

// not
@SP
A=M-1
M=!M