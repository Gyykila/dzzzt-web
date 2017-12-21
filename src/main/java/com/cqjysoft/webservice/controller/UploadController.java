package com.cqjysoft.webservice.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cqjysoft.common.aop.Ignore;
import com.cqjysoft.modules.entity.upload.UploadIMG;
import com.cqjysoft.modules.repository.upload.UploadIMGRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping(value="/upload")
public class UploadController {
	private static Logger log = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private UploadIMGRepository uploadIMGRepository;
	
	/**
	 * 图片上传保存
	 * @param data
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@Ignore
    @RequestMapping(value="/save",method=RequestMethod.POST)
    @ResponseBody
    public String save(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
            MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = mreq.getFileNames();
            MultipartFile file = null;
            String fileName = null;
            UploadIMG uploadIMG =null;
            while (fileNames.hasNext()) {
                fileName = (String) fileNames.next();
                file = mreq.getFile(fileName);
            }
            //图片名字
            fileName = file.getOriginalFilename();
            //项目文件夹 路径
            String realPath = request.getSession().getServletContext().getRealPath("/upload");
            //文件夹创建
            Date date = new Date();
            String dateFolder = DateFormatUtils.format(date, "//yyyy");
            String folderPath = realPath + dateFolder;
            File folder = new File(folderPath);
            if(!folder.exists()) {
            	folder.mkdir();
            }
            dateFolder = DateFormatUtils.format(date, "//MM");
            folderPath = folderPath + dateFolder;
            folder = new File(folderPath);
            if(!folder.exists()) {
            	folder.mkdir();
            }
            //文件后缀名
            String extension = fileName.substring(fileName.lastIndexOf("."));
            //文件保存名字
            String saveName = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
            //文件保存路径
            String savePath = folderPath + "/" +saveName + extension;
            File imgFile = new File(savePath);
            file.transferTo(imgFile);
            //缩略图保存路径
            String SLpath = imgFile.getPath().substring(0,imgFile.getPath().lastIndexOf("\\")) + File.separator +saveName+"SL"+extension;
            //生成缩略图
            Thumbnails.of(imgFile).size(160, 160).outputQuality(0.8).toFile(new File(SLpath));
            uploadIMG = new UploadIMG(saveName,date,date,savePath,SLpath);
            uploadIMGRepository.save(uploadIMG);
//            String relativePath = "attachment/kindeditor/" + dateFolder + "/" + saveName + extension;
//            String urlType = request.getParameter("urlType");
//            // 返回图片url地址
//            String path = request.getContextPath();
//            String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
        } catch (Exception e) {
        	log.error(e.getMessage());
        }
        return mapper.writeValueAsString(map);
    }
}
