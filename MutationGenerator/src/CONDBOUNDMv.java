import java.util.Hashtable;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CONDBOUNDMv extends MethodVisitor implements Opcodes{
	
	Hashtable<Integer, Integer> MathMutator;
	
    public CONDBOUNDMv(final MethodVisitor mv, String name) {
        super(ASM5, mv);
        
        MathMutator = new Hashtable<Integer, Integer>();
        
        MathMutator.put(IF_ICMPLE, IF_ICMPLT);
        MathMutator.put(IF_ICMPLT, IF_ICMPLE);
        MathMutator.put(IF_ICMPGE, IF_ICMPGT);
        MathMutator.put(IF_ICMPGT, IF_ICMPGE);
    }
        
    
	@Override
	public void visitJumpInsn(int opcode, Label label) {
		if (MathMutator.containsKey(opcode)) {
			super.visitJumpInsn(MathMutator.get(opcode), label);
		} else {
			super.visitJumpInsn(opcode, label);
		}
	}

}

