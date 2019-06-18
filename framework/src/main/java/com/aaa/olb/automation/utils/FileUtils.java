package com.aaa.olb.automation.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.aaa.olb.automation.log.Log;

public class FileUtils {
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // delete all files in this folder
			
			// comment this if do not want to delete this folder
			/*
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // delete the folder
			*/
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * @param path: absolute path of the folder
	 * @return
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists() || !file.isDirectory()) {
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
	
	/**
	 * @param dir: absolute path of the folder
	 * @return
	 */
	public static List<String> getFilePaths(String dir, String testname) {
		File file = new File(dir);
		List<String> filePaths = new ArrayList<>();
		if (!file.exists() || !file.isDirectory()) {
			return filePaths;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (dir.endsWith(File.separator)) {
				temp = new File(dir + tempList[i]);
			} else {
				temp = new File(dir + File.separator + tempList[i]);
			}
			if (temp.isFile() && temp.getAbsolutePath().contains(testname)) {
				filePaths.add(temp.getAbsolutePath());
			}
			if (temp.isDirectory()) {
				getFilePaths(dir + "/" + tempList[i], testname);
			}
		}
		return filePaths;
	}
}
