/**
* @author http://joesgroovyblog.blogspot.com/2011/09/ast-transformations-prerequisites-and.html
*/

import java.lang.annotation.*
import org.codehaus.groovy.transform.*
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.control.*
import org.codehaus.groovy.ast.stmt.*
import static org.codehaus.groovy.control.CompilePhase.*
import org.codehaus.groovy.ast.builder.*
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.syntax.*
import org.codehaus.groovy.control.messages.*

@Retention (RetentionPolicy.SOURCE)
@Target ([ElementType.METHOD])
@GroovyASTTransformationClass ("RequiresTransformation")
public @interface Requires {
    String value() default "true";
}


@GroovyASTTransformation(phase = SEMANTIC_ANALYSIS)
public class RequiresTransformation implements ASTTransformation {

    def annotationType = Requires.class.name

    private boolean checkNodes(ASTNode[] astNodes, String annotationType) {
        if (! astNodes)    return false
        if (! astNodes[0]) return false
        if (! astNodes[1]) return false
        if (!(astNodes[0] instanceof AnnotationNode))        return false
        if (! astNodes[0].classNode?.name == annotationType) return false
        if (!(astNodes[1] instanceof MethodNode))            return false
        true
    }
    
    public void visit(ASTNode[] astNodes, SourceUnit source) {
        if (!checkNodes(astNodes, annotationType)) {
            addError("Internal error on annotation", astNodes[0], sourceUnit);
            return
        }
        MethodNode annotatedMethod = astNodes[1]
        def annotationExpression = astNodes[0].members.value

        if (annotationExpression.class != ConstantExpression) {
            addError("The condition is not a constant expression", astNodes[0], sourceUnit);
            return
        }

        String annotationValueString = annotationExpression.value
        BlockStatement block = createStatements(annotationValueString)

        def methodStatements = annotatedMethod.code.statements
        methodStatements.add(0, block)
    }

    def createStatements(String clause) {
        def statements = """
            if(!($clause)) {
                throw new Exception('Precondition violated: {$clause}')
            }
        """

        AstBuilder ab = new AstBuilder()
         List<ASTNode> res = ab.buildFromString(CompilePhase.SEMANTIC_ANALYSIS, statements)
        BlockStatement bs = res[0]
        return bs
    }
    
    public void addError(String msg, ASTNode expr, SourceUnit source) {
        int line = expr.lineNumber
        int col = expr.columnNumber
        SyntaxException se = new SyntaxException(msg + '\n', line, col)
        SyntaxErrorMessage sem = new SyntaxErrorMessage(se, source)
        source.errorCollector.addErrorAndContinue(sem)
    }    
}

final calculator = new GroovyShell(this.class.getClassLoader()).evaluate('''
class Calculator {
    @Requires("divisor != 0")
    public int divide10By(divisor) {
        10/divisor
    }
}

new Calculator()
''')

println calculator.divide10By(5)
println calculator.divide10By(0)