import java.util.Random;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class INVERTNEGSMv extends MethodVisitor implements Opcodes{
	
	int count;
	
    public INVERTNEGSMv(final MethodVisitor mv, String name) {
        super(ASM5, mv);
        
        count = 1;
    }
        
    
	@Override
	public void visitInsn(int opcode) {
		if (count != 0) {
			if (opcode == INEG || opcode == FNEG) {
				Random rand = new Random();
				int randomNum = Math.abs(rand.nextInt())%2;
				if (randomNum == 1) {
					count--;	
					return;
				}
			} 
		} 
		super.visitInsn(opcode);
	}

}

