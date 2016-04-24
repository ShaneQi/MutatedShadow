

import java.io.File;
import java.util.ArrayList;

public class ClassFiles {
	
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
