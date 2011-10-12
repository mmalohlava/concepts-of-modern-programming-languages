@groovyx.ast.bytecode.Bytecode
int fib(int i) {
    iload 1
    iconst_2
    if_icmpge l1
    iload 1
    _goto l2
   l1
    aload 0
    iload 1
    iconst_2
    isub
    invokevirtual '.fib', '(I)I'
    aload 0
    iload 1
    iconst_1
    isub
    invokevirtual '.fib' ,'(I)I'
    iadd
   l2
    ireturn
}
println fib(40)
