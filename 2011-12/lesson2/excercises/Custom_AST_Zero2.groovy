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
@GroovyASTTransformationClass ("ZeroTransformation2")
public @interface Zero2 {}

//TASK Complete the transformation code at the indicated position so as the test passes

@GroovyASTTransformation(phase = SEMANTIC_ANALYSIS)
public class ZeroTransformation2 implements ASTTransformation {

    public void visit(ASTNode[] astNodes, SourceUnit source) {
        ClassNode annotatedClass = astNodes[1]


        AstBuilder ab = new AstBuilder()
        List<ASTNode> res = ab.buildFromCode(CompilePhase.SEMANTIC_ANALYSIS) {
            //...
        }
        annotatedClass.addMethod("getZero", Opcodes.ACC_PUBLIC, ClassHelper.Integer_TYPE, [] as Parameter[], [] as ClassNode[], res[0])
    }
}

final calculator = new GroovyShell(Zero1.class.getClassLoader()).evaluate('''
@Zero2
class Calculator {}

new Calculator()
''')

assert 0 == calculator.zero

println 'done'