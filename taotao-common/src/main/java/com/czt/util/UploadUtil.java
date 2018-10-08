package com.czt.util;

import org.csource.fastdfs.*;

public class UploadUtil {

	/***
     * FastDFS图片上传工具类
	 * @param trackerserver		
	 * 				tracker服务端链接的socket链接IP和端口，格式 tracker_server=192.168.174.130:22122
	 * 				这里的trackerserver是完整的文件，供客户端代码读取解析，文件格式如上
	 * 
	 * @param buffer	文件的字节数组
	 * @param subfix	上传的文件后缀
	 * @return
	 */
	public static String[] upload(String trackerserver, byte[] buffer, String subfix) {
		try {
			//初始化tracker服务
			ClientGlobal.init(trackerserver);
			
			//创建一个Tracker客户端
			TrackerClient tracker = new TrackerClient();
			
			//链接tracker服务
			TrackerServer trackerServer = tracker.getConnection();
			
			//通过链接tracker获得Storage信息并创建一个Storage服务端和客户端
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);

			/***
			 * 通过客户端想storage上传图片
			 * buffer：文件的字节数组
			 * subfix:文件的后缀
			 * 上传响应信息
			 * fileinfos[0]:文件存储的组名
			 * fileinfos[1]:文件的其他存储区间名和文件名的组合
			 */
			String[] fileinfos= storageClient.upload_file(buffer, subfix, null);

			return fileinfos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/***
	 * @param trackerserver		
	 * 				tracker服务端链接的socket链接IP和端口，格式 tracker_server=192.168.174.130:22122
	 * 				这里的trackerserver是完整的文件，供客户端代码读取解析，文件格式如上
	 * @param trackerserver
	 * @param vfile	需要上传的文件完整访问路径 如D:/123.jpg
	 * @return
	 */
	public static String[] upload(String trackerserver, String vfile) {
		try {
			//初始化tracker服务
			ClientGlobal.init(trackerserver);
			
			//创建一个Tracker客户端
			TrackerClient tracker = new TrackerClient();
			
			//链接tracker服务
			TrackerServer trackerServer = tracker.getConnection();
			
			//通过链接tracker获得Storage信息并创建一个Storage服务端和客户端
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);

			/***
			 * 通过客户端想storage上传图片
			 * buffer：文件的字节数组
			 * subfix:文件的后缀
			 * 上传响应信息
			 * fileinfos[0]:文件存储的组名
			 * fileinfos[1]:文件的其他存储区间名和文件名的组合
			 */
			String[] fileinfos= storageClient.upload_file(vfile, null, null);

			return fileinfos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * 主方法测试
	 * @param args
	 */
	public static void main(String[] args) {
		String path = System.getProperty("user.dir")+"/src/main/resources/";
		System.out.println(path);
		
		//文件
		String vfile = path+"2.jpg";
		
		//上传测试
		String[] fileinfos = upload(path+"tracker.conf", vfile);
		
		for (String string : fileinfos) {
			System.out.println(string);
		}
		/***
		 * web访问地址
		 * 因为这里之前已经配置好了Nginx，所以这里直接用了
		 */
		String weblik = "http://fastimg.taotao.com/"+fileinfos[0]+"/"+fileinfos[1];
		System.out.println(weblik);
	}

}
