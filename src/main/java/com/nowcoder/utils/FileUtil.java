package com.nowcoder.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * 
 * �ļ�����������
 * 
 */
public class FileUtil {

	/**
	 * ��Դ�ļ�������д�뵽Ŀ���ļ��У� ������Դ�ļ��Ƿ���ڣ� ��Ŀ���ļ�������ֱ��д�룬 ���򴴽�Ŀ���ļ����ٽ���д�롣
	 */
	private static void copyFile(String srcPath, String desPath) {
		try {
			FileInputStream in = new FileInputStream(srcPath);
			FileOutputStream out = new FileOutputStream(desPath);
			byte[] bt = new byte[1024];
			int count;
			while ((count = in.read(bt)) > 0) {
				out.write(bt, 0, count);
			}
			in.close();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * �����ļ������ļ��������滻���ļ���
	 * 
	 * @param srcPath
	 * @param desPath
	 * @throws Exception
	 */
	public static void copyAndReplaceFile(String srcPath, String desPath) throws Exception {
		srcPath = separatorReplace(srcPath);
		desPath = separatorReplace(desPath);
		File srcFile = new File(srcPath);
		if (!srcFile.isFile()) {
			throw new Exception("source file not found!");
		}
		copyFile(srcPath, desPath);
	}

	/**
	 * �����ļ������ļ��Ѵ����򲻽����滻��
	 * 
	 * @param srcPath
	 * @param desPath
	 * @throws Exception
	 */
	public static void copyAndNotReplaceFile(String srcPath, String desPath) throws Exception {
		srcPath = separatorReplace(srcPath);
		desPath = separatorReplace(desPath);
		File srcFile = new File(srcPath);
		File desFile = new File(desPath);
		if (!srcFile.isFile()) {
			throw new Exception("source file not found!");
		}
		if (desFile.isFile()) {
			return;
		}
		copyFile(srcPath, desPath);
	}

	/**
	 * �ƶ��ļ������ļ��������滻���ļ���
	 * 
	 * @param srcPath
	 * @param desPath
	 * @throws Exception
	 */
	public static void moveAndReplaceFile(String srcPath, String desPath) throws Exception {
		srcPath = separatorReplace(srcPath);
		desPath = separatorReplace(desPath);
		copyAndReplaceFile(srcPath, desPath);
		deleteFile(srcPath);
	}

	/**
	 * �ƶ��ļ������ļ������򲻽����滻��
	 * 
	 * @param srcPath
	 * @param desPath
	 * @throws Exception
	 */
	public static void moveAndNotReplaceFile(String srcPath, String desPath) throws Exception {
		srcPath = separatorReplace(srcPath);
		desPath = separatorReplace(desPath);
		copyAndNotReplaceFile(srcPath, desPath);
		deleteFile(srcPath);
	}

	/**
	 * ���Ʋ��ϲ��ļ��У� �����滻Ŀ���ļ������Ѿ����ڵ��ļ����ļ��С�
	 * 
	 * @param srcPath
	 * @param desPath
	 * @throws Exception
	 */
	public static void copyAndMergerFolder(String srcPath, String desPath) throws Exception {
		srcPath = separatorReplace(srcPath);
		desPath = separatorReplace(desPath);
		File folder = getFolder(srcPath);
		createFolder(desPath);
		File[] files = folder.listFiles();
		for (File file : files) {
			String src = file.getAbsolutePath();
			String des = desPath + File.separator + file.getName();
			if (file.isFile()) {
				copyAndNotReplaceFile(src, des);
			} else if (file.isDirectory()) {
				copyAndMergerFolder(src, des);
			}
		}
	}

	/**
	 * ���Ʋ��滻�ļ��У� ��Ŀ���ļ�����ȫ�滻��Դ�ļ��У� Ŀ���ļ���ԭ�е����ϻᶪʧ��
	 * 
	 * @param srcPath
	 * @param desPath
	 * @throws Exception
	 */
	public static void copyAndReplaceFolder(String srcPath, String desPath) throws Exception {
		srcPath = separatorReplace(srcPath);
		desPath = separatorReplace(desPath);
		File folder = getFolder(srcPath);
		createNewFolder(desPath);
		File[] files = folder.listFiles();
		for (File file : files) {
			String src = file.getAbsolutePath();
			String des = desPath + File.separator + file.getName();
			if (file.isFile()) {
				copyAndReplaceFile(src, des);
			} else if (file.isDirectory()) {
				copyAndReplaceFolder(src, des);
			}
		}
	}

	/**
	 * �ϲ��ļ��к󣬽�Դ�ļ���ɾ����
	 * 
	 * @param srcPath
	 * @param desPath
	 * @throws Exception
	 */
	public static void moveAndMergerFolder(String srcPath, String desPath) throws Exception {
		srcPath = separatorReplace(srcPath);
		desPath = separatorReplace(desPath);
		copyAndMergerFolder(srcPath, desPath);
		deleteFolder(srcPath);
	}

	/**
	 * �滻�ļ��к󣬽�Դ�ļ���ɾ����
	 * 
	 * @param srcPath
	 * @param desPath
	 * @throws Exception
	 */
	public static void moveAndReplaceFolder(String srcPath, String desPath) throws Exception {
		srcPath = separatorReplace(srcPath);
		desPath = separatorReplace(desPath);
		copyAndReplaceFolder(srcPath, desPath);
		deleteFolder(srcPath);
	}

	/**
	 * �����ļ��У�����ļ��д����򲻽��д�����
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static void createFolder(String path) throws Exception {
		path = separatorReplace(path);
		File folder = new File(path);
		if (folder.isDirectory()) {
			return;
		} else if (folder.isFile()) {
			deleteFile(path);
		}
		folder.mkdirs();
	}

	/**
	 * ����һ���µ��ļ��У�����ļ��д��ڣ���ɾ�����ٴ�����
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static void createNewFolder(String path) throws Exception {
		path = separatorReplace(path);
		File folder = new File(path);
		if (folder.isDirectory()) {
			deleteFolder(path);
		} else if (folder.isFile()) {
			deleteFile(path);
		}
		folder.mkdirs();
	}

	/**
	 * ����һ���ļ�������ļ������򲻽��д�����
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static File createFile(String path) throws Exception {
		path = separatorReplace(path);
		File file = new File(path);
		if (file.isFile()) {
			return file;
		} else if (file.isDirectory()) {
			deleteFolder(path);
		}
		return createFile(file);
	}

	/**
	 * ����һ�����ļ����������ͬ�����ļ����ļ��н���ɾ�����ļ����ļ��У� �����Ŀ¼�������򴴽���Ŀ¼��
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static File createNewFile(String path) throws Exception {
		path = separatorReplace(path);
		File file = new File(path);
		if (file.isFile()) {
			deleteFile(path);
		} else if (file.isDirectory()) {
			deleteFolder(path);
		}
		return createFile(file);
	}

	/**
	 * �ָ����滻 window�²���ͨ��
	 * 
	 * @param path
	 * @return
	 */
	public static String separatorReplace(String path) {
		return path.replace("\\", "/");
	}

	/**
	 * �����ļ����丸Ŀ¼��
	 * 
	 * @param file
	 * @throws Exception
	 */
	public static File createFile(File file) throws Exception {
		createParentFolder(file);
		if (!file.createNewFile()) {
			throw new Exception("create file failure!");
		}
		return file;
	}

	/**
	 * ������Ŀ¼
	 * 
	 * @param file
	 * @throws Exception
	 */
	private static void createParentFolder(File file) throws Exception {
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				throw new Exception("create parent directory failure!");
			}
		}
	}

	/**
	 * �����ļ�·��ɾ���ļ������·��ָ����ļ������ڻ�ɾ��ʧ�����׳��쳣��
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static void deleteFile(String path) throws Exception {
		path = separatorReplace(path);
		File file = getFile(path);
		if (!file.delete()) {
			throw new Exception("delete file failure");
		}
	}

	/**
	 * ɾ��ָ��Ŀ¼��ָ��ǰ׺�ͺ�׺���ļ���
	 * 
	 * @param dir
	 * @param prefix
	 * @param suffix
	 * @throws Exception
	 */
	public static void deleteFile(String dir, String prefix, String suffix) throws Exception {
		dir = separatorReplace(dir);
		File directory = getFolder(dir);
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				String fileName = file.getName();
				if (fileName.startsWith(prefix) && fileName.endsWith(suffix)) {
					deleteFile(file.getAbsolutePath());
				}
			}
		}
	}

	/**
	 * ����·��ɾ���ļ��У����·��ָ���Ŀ¼���������׳��쳣�� ���������ȱ���ɾ������Ŀ����ɾ���ļ��б���
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static void deleteFolder(String path) throws Exception {
		path = separatorReplace(path);
		File folder = getFolder(path);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				deleteFolder(file.getAbsolutePath());
			} else if (file.isFile()) {
				deleteFile(file.getAbsolutePath());
			}
		}
		folder.delete();
	}

	/**
	 * ����Ŀ���ļ����µ�Ŀ���ļ�
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File searchFile(String dir, String fileName) throws FileNotFoundException {
		dir = separatorReplace(dir);
		File f = null;
		File folder = getFolder(dir);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				f = searchFile(file.getAbsolutePath(), fileName);
				if (f != null) {
					break;
				}
			} else if (file.isFile()) {
				if (file.getName().equals(fileName)) {
					f = file;
					break;
				}
			}
		}
		return f;
	}

	/**
	 * ����ļ����͡�
	 * 
	 * @param path
	 *            ���ļ�·��
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String getFileType(String path) throws FileNotFoundException {
		path = separatorReplace(path);
		File file = getFile(path);
		String fileName = file.getName();
		String[] strs = fileName.split("\\.");
		if (strs.length < 2) {
			return "unknownType";
		}
		return strs[strs.length - 1];
	}

	/**
	 * �����ļ�·������ø�·��ָ����ļ��Ĵ�С��
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public static long getFileSize(String path) throws FileNotFoundException {
		path = separatorReplace(path);
		File file = getFile(path);
		return file.length();
	}

	/**
	 * �����ļ���·������ø�·��ָ����ļ��еĴ�С�� �������ļ��м�����Ŀ¼���ļ�������Щ�ļ��Ĵ�С�����ۼӣ��ó��ľ����ļ��еĴ�С��
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public static long getFolderSize(String path) throws FileNotFoundException {
		path = separatorReplace(path);
		long size = 0;
		File folder = getFolder(path);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				size += getFolderSize(file.getAbsolutePath());
			} else if (file.isFile()) {
				size += file.length();
			}
		}
		return size;
	}

	/**
	 * ͨ��·������ļ��� �������������쳣�� �������򷵻ظ��ļ���
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File getFile(String path) throws FileNotFoundException {
		path = separatorReplace(path);
		File file = new File(path);
		if (!file.isFile()) {
			throw new FileNotFoundException("file not found!");
		}
		return file;
	}

	/**
	 * ͨ��·������ļ��У� �������������쳣�� �������򷵻ظ��ļ��С�
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File getFolder(String path) throws FileNotFoundException {
		path = separatorReplace(path);
		File folder = new File(path);
		if (!folder.isDirectory()) {
			throw new FileNotFoundException("folder not found!");
		}
		return folder;
	}

	/**
	 * ����ļ�������ʱ�䡣
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Date getFileLastModified(String path) throws FileNotFoundException {
		path = separatorReplace(path);
		File file = getFile(path);
		return new Date(file.lastModified());
	}

	/**
	 * ����ļ���������ʱ�䡣
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Date getFolderLastModified(String path) throws FileNotFoundException {
		path = separatorReplace(path);
		File folder = getFolder(path);
		return new Date(folder.lastModified());
	}

	/**
	 * ���ض������ȡ�ļ�
	 * 
	 * @param f
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String getStringFromFile(File f, String charset) throws IOException {
		StringBuffer sb = new StringBuffer(); // �ļ��ܳ��Ļ�����ʹ��StringBuffer
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis, charset);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line).append("\r\n");
		}
		br.close();
		isr.close();
		fis.close();
		return sb.toString();
	}

	public static boolean fileOutput(byte[] buffer, String pathDir, String imageName) {
		FileOutputStream fos = null;
		try {
			File fileDir = new File(pathDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			File file = new File(fileDir, imageName);

			fos = new FileOutputStream(file);
			fos.write(buffer);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
}
