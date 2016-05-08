

import java.util.Hashtable;
import java.util.Random;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MATHMv extends MethodVisitor implements Opcodes {

	Hashtable<Integer, Integer> MathMutator;
	
	int count;
	
    public MATHMv(final MethodVisitor mv, String name) {
        super(ASM5, mv);
        
        count = 1;
        
        MathMutator = new Hashtable<Integer, Integer>();
        /* + & - */
        MathMutator.put(IADD, ISUB);
        MathMutator.put(LADD, LSUB);
        MathMutator.put(FADD, FSUB);
        MathMutator.put(DADD, DSUB);
        
        MathMutator.put(ISUB, IADD);
        MathMutator.put(LSUB, LADD);
        MathMutator.put(FSUB, FADD);
        MathMutator.put(DSUB, DADD);
        
        /* * & / & % */
        MathMutator.put(IMUL, IDIV);
        MathMutator.put(LMUL, LDIV);
        MathMutator.put(FMUL, FDIV);
        MathMutator.put(DMUL, DDIV);
        
        MathMutator.put(IDIV, IMUL);
        MathMutator.put(LDIV, LMUL);
        MathMutator.put(FDIV, FMUL);
        MathMutator.put(DDIV, DMUL);
        
        MathMutator.put(IREM, IMUL);
        MathMutator.put(LREM, LMUL);
        MathMutator.put(FREM, FMUL);
        MathMutator.put(DREM, DMUL);
        
        /* AND & OR & XOR */
        MathMutator.put(IAND, IOR);
        MathMutator.put(LAND, LOR);
        
        MathMutator.put(IOR, IAND);
        MathMutator.put(LOR, LAND);
        
        MathMutator.put(IXOR, IAND);
        MathMutator.put(LXOR, LAND);
        
        /* SHIFT LEFT & RIGHT */
        MathMutator.put(ISHL, ISHR);
        MathMutator.put(LSHL, LSHR);
        
        MathMutator.put(ISHR, ISHL);
        MathMutator.put(LSHR, LSHL);
        
        MathMutator.put(IUSHR, ISHL);
        MathMutator.put(LUSHR, LSHL);
    }

	@Override
	public void visitInsn(int opcode) {
		if (count != 0) {
			if (MathMutator.containsKey(opcode)) {
				Random rand = new Random();
				int randomNum = Math.abs(rand.nextInt())%2;
				if (randomNum == 1) {
					super.visitInsn(MathMutator.get(opcode));
					count--;	
					return;
				}
			} 
		} 
		super.visitInsn(opcode);
	}
}