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
@GroovyASTTransformationClass ("NumberConversionTransformation1")
public @interface NumberConversion1 {}


@GroovyASTTransformation(phase = SEMANTIC_ANALYSIS)
public class NumberConversionTransformation1 implements ASTTransformation {

    public void visit(ASTNode[] astNodes, SourceUnit source) {
        ClassNode annotatedClass = astNodes[1]

        AstBuilder ab = new AstBuilder()
        List<ASTNode> res = ab.buildFromString('''
                Integer.parseInt("$valueToConvert")
            ''')
        
        def param = new Parameter(ClassHelper.STRING_TYPE, "valueToConvert")
        annotatedClass.addMethod("convertToNumber", Opcodes.ACC_PUBLIC, ClassHelper.Integer_TYPE, [param] as Parameter[], [] as ClassNode[], res[0])
    }
}

final calculator = new GroovyShell(this.class.getClassLoader()).evaluate('''
@NumberConversion1
class Calculator {}

new Calculator()
''')

println calculator.convertToNumber("20")