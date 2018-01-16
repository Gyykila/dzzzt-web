package com.cqjysoft.manager;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.cqjysoft.common.SpringUtil;
import com.cqjysoft.modules.entity.upload.UploadIMG;
import com.cqjysoft.modules.repository.upload.UploadIMGRepository;

import net.coobird.thumbnailator.Thumbnails;

public class UploadManager {
	private static final UploadManager UM = new UploadManager();
	
	public static UploadManager getUploadManager() {
		return UM;
	}
	
	private static ExecutorService cachedThreadPool = Executors.newFixedThreadPool(50);
	
	public void save(File imgFile,String realPath,Date date,String saveName,String extension,String savePath) {
		cachedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					
		            //缩略图保存路径
		            String SLpath = imgFile.getPath().substring(0,imgFile.getPath().lastIndexOf("\\")) + File.separator +saveName+"SL"+extension;
		            //生成缩略图
		            Thumbnails.of(imgFile).size(160, 160).outputQuality(0.8).toFile(new File(SLpath));
		            String path = imgFile.getPath().substring(0,imgFile.getPath().lastIndexOf("\\")) + File.separator +saveName+extension;
		            Thumbnails.of(imgFile).width(640).keepAspectRatio(true).outputQuality(1).toFile(new File(path));
		            UploadIMG uploadIMG = new UploadIMG(saveName,date,date,savePath,SLpath,path);
		            UploadIMGRepository uploadIMGRepository = SpringUtil.getBean(UploadIMGRepository.class);
		            uploadIMGRepository.save(uploadIMG);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void shutdown() {
		cachedThreadPool.shutdown();
	}
	
	public boolean waitTermination(Long time) throws InterruptedException {
		
		return cachedThreadPool.awaitTermination(1, TimeUnit.SECONDS);
	}
}