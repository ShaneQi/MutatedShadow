import java.util.Hashtable;
import java.util.Random;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class NEGCONDMv extends MethodVisitor implements Opcodes{
	
	int count;
	
	Hashtable<Integer, Integer> MathMutator;
	
    public NEGCONDMv(final MethodVisitor mv, String name) {
        super(ASM5, mv);
        
        count = 1;
        
        MathMutator = new Hashtable<Integer, Integer>();
        
        MathMutator.put(IF_ICMPEQ, IF_ICMPNE);
        MathMutator.put(IF_ICMPNE, IF_ICMPEQ);
        
        MathMutator.put(IF_ICMPLE, IF_ICMPGT);
        MathMutator.put(IF_ICMPGT, IF_ICMPLE);
        
        MathMutator.put(IF_ICMPGE, IF_ICMPLT);
        MathMutator.put(IF_ICMPLT, IF_ICMPGE);

    }
        
    
	@Override
	public void visitJumpInsn(int opcode, Label label) {
		if (count != 0) {
			if (MathMutator.containsKey(opcode)) {
				Random rand = new Random();
				int randomNum = Math.abs(rand.nextInt())%2;
				if (randomNum == 1) {
					super.visitJumpInsn(MathMutator.get(opcode), label);
					count--;	
					return;
				}
			} 
		} 
		super.visitJumpInsn(opcode, label);
	}

}

