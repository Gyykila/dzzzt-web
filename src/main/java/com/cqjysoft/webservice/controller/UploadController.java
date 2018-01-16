package com.cqjysoft.webservice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
            while (fileNames.hasNext()) {
                fileName = (String) fileNames.next();
                file = mreq.getFile(fileName);
            }
            
            UploadIMG uploadIMG =null;
            //图片名字
            fileName = file.getOriginalFilename();
            log.info("{}start save!",fileName);
            //项目文件夹 路径
            String realPath = request.getSession().getServletContext().getRealPath("/upload");
            //文件夹创建
            Date date = new Date();
            //文件后缀名
            String extension = fileName.substring(fileName.lastIndexOf("."));
            //文件保存名字
            String saveName = DateFormatUtils.format(date, "yyyyMMddHHmmssSSS")+UUID.randomUUID();
            //文件保存路径
            String savePath = realPath + "/" +saveName+"ORI" + extension;
            File imgFile = new File(savePath);
            file.transferTo(imgFile);
            log.info("{} ORIIMG start save!",savePath);
            //缩略图保存路径
            String SLpath = imgFile.getPath().substring(0,imgFile.getPath().lastIndexOf("\\")) + File.separator +saveName+"SL"+extension;
            //生成缩略图
            Thumbnails.of(imgFile).size(160, 160).outputQuality(0.8).toFile(new File(SLpath));
            log.info("{} SLIMG start save!",SLpath);
            String path = imgFile.getPath().substring(0,imgFile.getPath().lastIndexOf("\\")) + File.separator +saveName+extension;
            Thumbnails.of(imgFile).width(640).keepAspectRatio(true).outputQuality(1).toFile(new File(path));
            log.info("{} IMG start save!",path);
            uploadIMG = new UploadIMG(saveName,date,date,savePath,SLpath,path);
            uploadIMGRepository.save(uploadIMG);
        } catch (Exception e) {
        	log.error(e.getMessage());
        }
        return mapper.writeValueAsString(map);
    }
	/**
	 * 缩略图列表
	 * @param data
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws InterruptedException 
	 */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    @ResponseBody
    @Ignore
    public String list(String params,String token) throws ParseException, IOException, InterruptedException {
    	ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		List<UploadIMG> imgs = uploadIMGRepository.findAll();
		map.put("code", "SUCCESS");
		map.put("msg", "获取数据成功");
		map.put("data", imgs);
        return mapper.writeValueAsString(map);
    }
    
    @RequestMapping(value="/listimg",method=RequestMethod.GET)
    @Ignore
    public void listimg(HttpServletRequest request,HttpServletResponse response) throws ParseException, IOException, InterruptedException {
		String url = request.getParameter("imgUrl");
		if(StringUtils.isNotBlank(url)){
			FileInputStream is = new FileInputStream(new File(url));
			byte[] diagramBytes = new byte[is.available()];
			is.read(diagramBytes);
			OutputStream os = response.getOutputStream();
			os.write(diagramBytes);
			os.flush();
			os.close();
		}
    }
}
