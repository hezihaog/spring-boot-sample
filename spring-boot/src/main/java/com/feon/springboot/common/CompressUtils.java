package com.feon.springboot.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 压缩文件帮助类
 * @author 蒙飞鸿(meng.fh)
 * 2013-5-22 下午12:48:24
 */
public class CompressUtils {
	
	private static final Logger LOG = Logger.getLogger(CompressUtils.class);

	/**
	 * 压缩单个文件
	 * @param inputFile 要压缩文件
	 * @param ouputStream 目的地文件流
	 * @throws Exception
	 */
	/*private static void zipSingleFile(File inputFile, ZipOutputStream ouputStream) throws Exception {
		String fileName = "";
		BufferedInputStream bins = null;
		try {
			if (inputFile.exists() && inputFile.isFile()) {
				bins = new BufferedInputStream(new FileInputStream(inputFile));
				// 文件转码(修改者：mengfh)
				// fileName = new
				// String(inputFile.getName().getBytes(System.getProperty("file.encoding")),
				// "ISO-8859-1");
				fileName = inputFile.getName();
				// ZipEntry entry = new ZipEntry(inputFile.getName());
				ZipEntry entry = new ZipEntry(fileName);
				ouputStream.putNextEntry(entry);
				// 向压缩文件中输出数据
				int nNumber = 0;
				byte[] buffer = new byte[6144];

				while ((nNumber = bins.read(buffer)) != -1) {
					ouputStream.write(buffer, 0, nNumber);
					ouputStream.flush();
				}
			} else {
				LOG.error("压缩" + fileName + "文件失败");
				throw new Exception("压缩" + fileName + "文件失败");
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
			LOG.error("压缩" + fileName + "文件失败");
			throw new Exception("压缩" + fileName + "文件失败");
		} finally {
			try {
				bins.close();
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		}
	}*/
	
	/**
	 * 压缩多个文件
	 * @param files 要压缩文件集合 
	 * @param outputStream  输出目的地文件流
	 */
	/*private static void zipMulFiles(File[] files, ZipOutputStream outputStream) throws Exception {
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			LOG.info("开始压缩第" + i + "文件 文件名====" + file.getAbsolutePath() + "====" + file.getName());
			zipSingleFile(file, outputStream);
		}
	}*/
	 
	
	//----------------------------------------以下是需要压缩的文件内存在嵌套的文件或文件夹的解决方案----------------------------------------//
	/**
	 * 递归zip
	 * @param zipOut 压缩输出流
	 * @param fileName 文件名
	 * @param base 基本路径
	 * @throws IOException
	 */
	private static void zip(ZipOutputStream zipOut, String fileName, String base) throws IOException {
		File srcFile = new File(fileName);
		if(!srcFile.exists()) {
			LOG.error(srcFile.getName() + "不存在，不能进行压缩");
			return;
		}
		if (srcFile.isDirectory()) {
			// 如果是文件夹，遍历下面的文件
			File[] files = srcFile.listFiles();
			if (files != null && files.length > 0) {
				base = (StringUtils.isBlank(base) ? "" : base + "/");
				for (File file : files) {
					zip(zipOut, file.getAbsolutePath(), base + file.getName());
				}
			}
		} else {
			if (StringUtils.isBlank(base)) {
				base = srcFile.getName();
			}
			compressNestedFile(srcFile, zipOut, base);
		}
	}
	
	/**
	 * 压缩一个文件
	 * @param file 文件对象
	 * @param out 压缩输出流
	 * @param basedir 基本目录(一般至为空串)
	 */
	private static void compressNestedFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			LOG.error(file.getName() + "不存在，不能进行压缩");
			return;
		}
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir);
			out.putNextEntry(entry);
			int count = 0;
			byte[] buffer = new byte[8192];
			while ((count = bis.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
			LOG.info("压缩" + file.getName() + "成功。");
		} catch (IOException e) {
			LOG.error(e.getMessage());
		} finally {
			try {
				if(bis != null) {
					bis.close();
					bis = null;
				}
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		}
	}
	
	/**
	 * 将指定的文件压缩成zip
	 * @param destPath 压缩文件输出路（包括路径和文件名）
	 * @param filePath 需要被压缩的文件目录
	 * @throws IllegalArgumentException
	 */
	public static File zipNestedFiles(String destPath, String filePath) throws IllegalArgumentException {
		return zipNestedFiles(destPath, new String[]{filePath});
	}
	
	/**
	 * 将指定的文件压缩成zip
	 * @param destPath 压缩文件输出路径（包括路径和文件名）
	 * @param filePaths 需要被压缩的文件目录集合
	 * @throws IllegalArgumentException
	 */
	public static File zipNestedFiles(String destPath, String[] filePaths) throws IllegalArgumentException {
		if (StringUtils.isBlank(destPath) || filePaths == null || filePaths.length == 0) {
			throw new IllegalArgumentException("指定的压缩文件路径或者需要被压缩的文件路径为空");
		}
		destPath = destPath.replaceAll("\\\\", "/");
		for(String filePath : filePaths) {
			filePath = filePath.replaceAll("\\\\", "/");
			if(destPath.indexOf(filePath) != -1) {
				throw new IllegalArgumentException("指定的压缩文件路径是需要被压缩的文件路径或者其子路径");
			}
		}
		//生成压缩文件对象
		File zipFile = new File(destPath); 
		//根据压缩文件对象，构建压缩文件的所在目录
		File zipPathFile = zipFile.getParentFile();
		if(!zipPathFile.exists() || !zipPathFile.isDirectory()) {
			zipPathFile.mkdirs();
		}
		//创建压缩文件
		try {
			if (!zipFile.exists()) {
				zipFile.createNewFile();
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
		FileOutputStream fos = null;
		CheckedOutputStream cos = null;
		ZipOutputStream zos = null;
		try {
			// 创建文件输出流对象
			fos = new FileOutputStream(zipFile);
			cos = new CheckedOutputStream(fos, new CRC32());
			// 创建ZIP数据输出流对象
			zos = new ZipOutputStream(new BufferedOutputStream(cos));
			// 循环调用
			for (String srcFileName : filePaths) {
				// 调用打包的方法
				zip(zos, srcFileName, "");
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		} finally {
			try {
				if(zos != null) {
					zos.close();
					zos = null;
				}
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
			try {
				if(cos != null) {
					cos.close();
					cos = null;
				}
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
			try {
				if(fos != null) {
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		}
		return zipFile;
	}
	
	/**
	 * 解压文件操作
	 * @param zipFile 压缩文件对象
	 * @param unZipPath 解压的目标路径
	 */
	@SuppressWarnings("rawtypes")
	private static void unZipFileByOpache(ZipFile zipFile, String unZipPath) {
		Enumeration e = zipFile.getEntries();
		ZipEntry zipEntry = null;
		while (e.hasMoreElements()) {
			zipEntry = (ZipEntry) e.nextElement();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			if (!zipEntry.isDirectory()) {
				File dstFile = new File(unZipPath + File.separator + zipEntry.getName());
				File parentPath = dstFile.getParentFile();
				//构建解压的目标路径
				if(!parentPath.exists() || !parentPath.isDirectory()) {
					parentPath.mkdirs();
				}
				try {
					bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));
					bos = new BufferedOutputStream(new FileOutputStream(dstFile));
					byte[] buffer = new byte[2048];
					int len = 0;
					while ((len = bis.read(buffer)) != -1) {
						bos.write(buffer, 0, len);
					}
				} catch (IOException e1) {
					LOG.error(e1.getMessage());
				} finally {
					try {
						if(bos != null) {
							bos.close();
							bos = null;
						}
					} catch (IOException e1) {
						LOG.error(e1.getMessage());
					}
					try {
						if(bis != null) {
							bis.close();
							bis = null;
						}
					} catch (IOException e1) {
						LOG.error(e1.getMessage());
					}
				}
			}
		}
	}
	
	/**
	  * 解压文件
	  * @param zipPath 需要被解压文件的路径(包括路径和文件名)
	  * @param destPath 解压的目标路径
	  * @throws Exception
	  */
	public static void unZipNestedFile(String zipPath, String destPath) {
		File file = new File(zipPath);
		if(!file.exists()) {
			throw new IllegalArgumentException(zipPath + "不存在");
		}
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipPath, System.getProperty("file.encoding"));
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
		try {
			if(zipFile != null) {
				unZipFileByOpache(zipFile, destPath);
				zipFile.close();
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		unZipNestedFile("D:/test.zip", "D:/zipTest/");
	}
}
