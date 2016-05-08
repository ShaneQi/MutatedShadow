import java.util.Random;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class INCREMENTSMv extends MethodVisitor implements Opcodes{
	
	int count;
	
    public INCREMENTSMv(final MethodVisitor mv, String name) {
        super(ASM5, mv);
        
        count = 1;
    }
        
    
	@Override
	public void visitIincInsn(int var, int increment) {
		if (count != 0) {
			if (increment == 1) {
				Random rand = new Random();
				int randomNum = Math.abs(rand.nextInt())%2;
				if (randomNum == 1) {
					super.visitIincInsn(var, -1);
					count--;	
					return;
				}
			} 
		} 
		super.visitIincInsn(var, 1);
	}

}

