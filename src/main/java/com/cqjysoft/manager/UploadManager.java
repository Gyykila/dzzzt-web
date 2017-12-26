package com.cqjysoft.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.cqjysoft.modules.entity.upload.UploadIMG;

import sun.misc.BASE64Encoder;

public class UploadManager {
	private static final UploadManager UM = new UploadManager();
	
	public static UploadManager getUploadManager() {
		return UM;
	}
	
	private static ExecutorService cachedThreadPool = Executors.newFixedThreadPool(5);
	
	public List<UploadIMG> getSLIMGS(List<UploadIMG> imgs) {
		for (UploadIMG uploadIMG : imgs) {
//			cachedThreadPool.execute(new Runnable() {
//				@Override
//				public void run() {
					try {
//						System.out.println(Thread.currentThread().getName() + "正在执行… …");
						uploadIMG.setImgSLURL("data:image/x-icon;base64,"+JpgToimgBase64(uploadIMG.getImgSLURL()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
//			});
//		}
		return imgs;
	}
	
	public List<UploadIMG> getIMGS(List<UploadIMG> imgs) throws IOException {
		for (UploadIMG uploadIMG : imgs) {
//			cachedThreadPool.execute(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						System.out.println(Thread.currentThread().getName() + "正在执行… …");
						uploadIMG.setImgURL("data:image/x-icon;base64,"+JpgToimgBase64(uploadIMG.getImgURL()));
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			});
		}
		return imgs;
	}
	
	private String JpgToimgBase64(String path) throws IOException{
		InputStream in=null;
		byte[] data=null;
		try {
			in = new FileInputStream(path);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (Exception e) {
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
	
	public void shutdown() {
		cachedThreadPool.shutdown();
	}
	
	public boolean waitTermination(Long time) throws InterruptedException {
		
		return cachedThreadPool.awaitTermination(1, TimeUnit.SECONDS);
	}
}