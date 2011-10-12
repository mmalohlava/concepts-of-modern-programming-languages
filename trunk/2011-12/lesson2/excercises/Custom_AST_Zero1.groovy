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
@GroovyASTTransformationClass ("ZeroTransformation1")
public @interface Zero1 {}

//TASK Complete the transformation code at the indicated position so as the test passes

@GroovyASTTransformation(phase = SEMANTIC_ANALYSIS)
public class ZeroTransformation1 implements ASTTransformation {

    public void visit(ASTNode[] astNodes, SourceUnit source) {
        ClassNode annotatedClass = astNodes[1]

        def statements = """
                //...
        """

        AstBuilder ab = new AstBuilder()
        List<ASTNode> res = ab.buildFromString(CompilePhase.SEMANTIC_ANALYSIS, statements)
        annotatedClass.addMethod("getZero", Opcodes.ACC_PUBLIC, ClassHelper.Integer_TYPE, [] as Parameter[], [] as ClassNode[], res[0])
    }
}

final calculator = new GroovyShell(this.class.getClassLoader()).evaluate('''
@Zero1
class Calculator {}

new Calculator()
''')

assert 0 == calculator.zero

println 'done'