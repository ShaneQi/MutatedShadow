

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
		getClasses(new File(ParentDirectory.getAbsolutePath()+"/target/classes"), classes);
		
		if (args[0].equals("MATH")) {
			System.out.println("========== MATH ==========");
			injectMATH(classes);
		} 
		else if (args[0].equals("INCREMENTS")) {
			System.out.println("======= INCREMENTS =======");
			injectINCREMENTS(classes);
		}
		else if (args[0].equals("INVERTNEGS")) {
			System.out.println("======= INVERTNEGS =======");
			injectINVERTNEGS(classes);
		}
		else if (args[0].equals("CONDBOUND")) {
			System.out.println("======= CONDBOUND =======");
			injectCONDBOUND(classes);
		}
		else if (args[0].equals("NEGCOND")) {
			System.out.println("======= NEGCOND =======");
			injectNEGCOND(classes);
		}
	}
	
	private static void injectINCREMENTS(ArrayList<File> classes) throws IOException {
		for (File f : classes) {
			FileInputStream is = new FileInputStream(f);
			try {
				ClassReader cr = new ClassReader(is);
				ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
				INCREMENTSCv ca = new INCREMENTSCv(cw);
				cr.accept(ca, 0);
				FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile());
				fos.write(cw.toByteArray());
				fos.close();	
				System.out.println("Injecting INCREMENTS mutators into "+ f.getName() + ",");
			} catch (IllegalArgumentException e) {
				System.out.println("Skiped a none-class file: "+ f.getName());
			}
		}
	}
	
	private static void injectMATH(ArrayList<File> classes) throws IOException {
		for (File f : classes) {
			FileInputStream is = new FileInputStream(f);
			try {
				ClassReader cr = new ClassReader(is);
				ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
				MATHCv ca = new MATHCv(cw);
				cr.accept(ca, 0);
				FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile());
				fos.write(cw.toByteArray());
				fos.close();	
				System.out.println("Injecting MATH mutators into "+ f.getName() + ",");
			} catch (IllegalArgumentException e) {
				System.out.println("Skiped a none-class file: "+ f.getName());
			}
		}
	}
	
	private static void injectINVERTNEGS(ArrayList<File> classes) throws IOException {
		for (File f : classes) {
			FileInputStream is = new FileInputStream(f);
			try {
				ClassReader cr = new ClassReader(is);
				ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
				INVERTNEGSCv ca = new INVERTNEGSCv(cw);
				cr.accept(ca, 0);
				FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile());
				fos.write(cw.toByteArray());
				fos.close();	
				System.out.println("Injecting INVERTNEGS mutators into "+ f.getName() + ",");
			} catch (IllegalArgumentException e) {
				System.out.println("Skiped a none-class file: "+ f.getName());
			}
		}
	}
	
	private static void injectCONDBOUND(ArrayList<File> classes) throws IOException {
		for (File f : classes) {
			FileInputStream is = new FileInputStream(f);
			try {
				ClassReader cr = new ClassReader(is);
				ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
				CONDBOUNDCv ca = new CONDBOUNDCv(cw);
				cr.accept(ca, 0);
				FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile());
				fos.write(cw.toByteArray());
				fos.close();	
				System.out.println("Injecting COUNDBOUND mutators into "+ f.getName() + ",");
			} catch (IllegalArgumentException e) {
				System.out.println("Skiped a none-class file: "+ f.getName());
			}
		}
	}
	
	private static void injectNEGCOND(ArrayList<File> classes) throws IOException {
		for (File f : classes) {
			FileInputStream is = new FileInputStream(f);
			try {
				ClassReader cr = new ClassReader(is);
				ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
				NEGCONDCv ca = new NEGCONDCv(cw);
				cr.accept(ca, 0);
				FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile());
				fos.write(cw.toByteArray());
				fos.close();	
				System.out.println("Injecting NEGCOND mutators into "+ f.getName() + ",");
			} catch (IllegalArgumentException e) {
				System.out.println("Skiped a none-class file: "+ f.getName());
			}
		}
	}
	
	public static void getClasses(File directory, ArrayList<File> files) throws Exception {
	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile() && !file.isHidden()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	        	getClasses(file, files);
	        }
	    }
	}
}
