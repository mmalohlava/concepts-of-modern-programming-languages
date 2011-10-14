import java.lang.annotation.*
import org.codehaus.groovy.transform.*
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.control.*
import org.codehaus.groovy.ast.stmt.*
import static org.codehaus.groovy.control.CompilePhase.*
import org.codehaus.groovy.ast.builder.*
import org.codehaus.groovy.ast.expr.*
import org.objectweb.asm.Opcodes

@Retention (RetentionPolicy.SOURCE)
@Target ([ElementType.METHOD])
@GroovyASTTransformationClass ("UnsupportedTransformation")
public @interface Unsupported {}


@GroovyASTTransformation(phase = SEMANTIC_ANALYSIS)
public class UnsupportedTransformation implements ASTTransformation {

    public void visit(ASTNode[] astNodes, SourceUnit source) {
        MethodNode annotatedMethod = astNodes[1]
        String name = annotatedMethod.name
        def methodStatements = annotatedMethod.code.statements
        
        //...
        // The "Unsupported" transformation should make the annotated methods throw the UnsupportedOperationException
        // Fill in the missing AST generation code to make the script pass
        // You can take inspiration from exercises
    }
}

final calculator = new GroovyShell(this.class.getClassLoader()).evaluate('''
class Calculator {
    def plus(a, b) {
        a + b
    }

    @Unsupported
    def minus(a, b) {}

    @Unsupported
    def divide(a, b) {}
}

new Calculator()
''')

assert 5 == calculator.plus(2, 3)
try {
    calculator.minus(10, 5)
    assert false, "Exception should have been thrown since the minus method is not supported"
} catch (RuntimeException e) {
    assert e instanceof UnsupportedOperationException
    assert e.message == 'The minus operation is not supported'
}
try {
    calculator.divide(10, 5)
    assert false, "Exception should have been thrown since the divide method is not supported"
} catch (RuntimeException e) {
    assert e instanceof UnsupportedOperationException
    assert e.message == 'The divide operation is not supported'
}

println 'well done'