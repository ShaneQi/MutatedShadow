import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class INVERTNEGSMv extends MethodVisitor implements Opcodes{
	
    public INVERTNEGSMv(final MethodVisitor mv, String name) {
        super(ASM5, mv);
    }
        
    
	@Override
	public void visitInsn(int opcode) {
		if (opcode == INEG || opcode == FNEG) {
			
		} else {
			super.visitInsn(opcode);
		}
	}

}

