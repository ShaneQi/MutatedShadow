

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class MutationInjector {
	public static void main(String[] args) throws Exception {
		ArrayList<File> classes = new ArrayList<File>();
		File ParentDirectory = new File(System.getProperty("user.dir")).getParentFile();
		ClassFiles.getClasses(new File(ParentDirectory.getAbsolutePath()+"/target/classes"), classes);
		
		/* MATH */
		MATH(classes);
	}
	
	private static void MATH(ArrayList<File> classes) throws IOException {
		for (File f : classes) {
			System.out.println(f.getName());
			FileInputStream is = new FileInputStream(f);
			try {
				ClassReader cr = new ClassReader(is);
				ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
				ClassAdapter ca = new ClassAdapter(cw);
				cr.accept(ca, 0);
				FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile());
				fos.write(cw.toByteArray());
				fos.close();	
			} catch (IllegalArgumentException e) {
				System.out.println("Skiped a none-class file.");
			}
		}
	}
}
