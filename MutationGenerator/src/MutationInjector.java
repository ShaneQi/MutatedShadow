

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassVisitor;

public class MutationInjector {
	public static void main(String[] args) throws Exception {
		ArrayList<File> classes = new ArrayList<File>();
		File ParentDirectory = new File(System.getProperty("user.dir")).getParentFile();
		ClassFiles.getClasses(new File(ParentDirectory.getAbsolutePath()+"/target/classes"), classes);
		
		if (args[0].equals("MATH")) {
			System.out.println("Injecting MATH mutator.");
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
			MATHCv ca = new MATHCv(cw);
			inject(classes, cw, ca);
		} 
		else if (args[0].equals("INCREMENTS")) {
			System.out.println("Injecting INCREMENTS mutator.");
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
			INCREMENTSCv ca = new INCREMENTSCv(cw);
			inject(classes, cw, ca);
		}

	}
	
	private static void inject(ArrayList<File> classes,ClassWriter cw, ClassVisitor ca) throws IOException {
		for (File f : classes) {
			FileInputStream is = new FileInputStream(f);
			try {
				ClassReader cr = new ClassReader(is);
				/* YIELD */
				cr.accept(ca, 0);
				FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile());
				fos.write(cw.toByteArray());
				fos.close();	
			} catch (IllegalArgumentException e) {
				System.out.println("Skiped a none-class file: "+ f.getName());
			}
		}
	}
}
