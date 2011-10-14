import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformationClass
import org.objectweb.asm.Opcodes
import static org.codehaus.groovy.control.CompilePhase.SEMANTIC_ANALYSIS

@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.TYPE])
@GroovyASTTransformationClass("ZeroTransformation3")
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