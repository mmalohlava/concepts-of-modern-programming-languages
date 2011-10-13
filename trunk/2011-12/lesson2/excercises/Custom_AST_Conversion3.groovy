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
@GroovyASTTransformationClass ("NumberConversionTransformation3")
public @interface NumberConversion3 {}


@GroovyASTTransformation(phase = SEMANTIC_ANALYSIS)
public class NumberConversionTransformation3 implements ASTTransformation {

    public void visit(ASTNode[] astNodes, SourceUnit source) {
        ClassNode annotatedClass = astNodes[1]

        AstBuilder ab = new AstBuilder()
        List<ASTNode> res = ab.buildFromSpec {
            method('convertToNumber', Opcodes.ACC_PUBLIC, Integer) {
                parameters {
                    parameter 'valueToConvert': String.class
                }
                exceptions {
                    classNode NumberFormatException
                }
                block {
                    returnStatement {
                        staticMethodCall(Integer, "parseInt") {
                            argumentList {
                                variable "valueToConvert"
                            }
                        }
                    }
                }
                annotations {}
            }
        }
        annotatedClass.addMethod(res[0])
    }
}

final calculator = new GroovyShell(this.class.getClassLoader()).evaluate('''
@NumberConversion3
class Calculator {}

new Calculator()
''')

println calculator.convertToNumber("20")