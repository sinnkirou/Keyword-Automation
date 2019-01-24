package com.aaa.olb.automation.utils;

import java.io.File;

public class DeleteFile {
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // delete all files in this folder
			// comment this if do not want to delete this folder
			// String filePath = folderPath;
			// filePath = filePath.toString();
			// java.io.File myFilePath = new java.io.File(filePath);
			// myFilePath.delete(); // delete the folder
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * path: absolute path of the folder
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);//
				// delFolder(path + "/" + tempList[i]);// delete the folder
				flag = true;
			}
		}
		return flag;
	}
}
