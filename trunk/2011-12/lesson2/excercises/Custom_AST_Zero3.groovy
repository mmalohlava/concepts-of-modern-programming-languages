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
@Target ([ElementType.TYPE])
@GroovyASTTransformationClass ("ZeroTransformation3")
public @interface Zero3 {}

//TASK Complete the transformation code at the indicated position so as the test passes

@GroovyASTTransformation(phase = SEMANTIC_ANALYSIS)
public class ZeroTransformation3 implements ASTTransformation {

    public void visit(ASTNode[] astNodes, SourceUnit source) {
        ClassNode annotatedClass = astNodes[1]

        AstBuilder ab = new AstBuilder()
        List<ASTNode> res = ab.buildFromSpec {
            method('getZero', Opcodes.ACC_PUBLIC, Integer) {
                parameters {}
                exceptions {}
                block {
                    //...
                }
                annotations {}
            }
        }
        annotatedClass.addMethod(res[0])
    }
}

final calculator = new GroovyShell(this.class.getClassLoader()).evaluate('''
@Zero3
class Calculator {}

new Calculator()
''')

assert 0 == calculator.zero

println 'done'