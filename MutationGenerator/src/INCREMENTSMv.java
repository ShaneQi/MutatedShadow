import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class INCREMENTSMv extends MethodVisitor implements Opcodes{
	
    public INCREMENTSMv(final MethodVisitor mv, String name) {
        super(ASM5, mv);
    }
        
    
	@Override
	public void visitIincInsn(int var, int increment) {
		if (increment == 1) {
			super.visitIincInsn(var, -1);
		} else if (increment == -1) {
			super.visitIincInsn(var, 1);
		}
	}

}

