package edu.zju.bme.clever.commons.util;

import java.io.File;

public class FileUtils {

	public static boolean deleteDirectory(File directory) {
		if (directory.isDirectory()) {
			String[] children = directory.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDirectory(new File(directory,
						children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return directory.delete();
	}
	
}
