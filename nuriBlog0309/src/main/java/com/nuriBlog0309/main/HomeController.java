package com.nuriBlog0309.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static final String FILE2 = "file";
	private static final String ARTICLE = "article";
	private static final String STATIC_IMAGES_THUMBNAILS = "/static/images/thumbnails/";
	private static final String UPLOADIMG = "/static/uploadimg/";
	private static final String UPLOADFILES = "/static/uploadfiles/";
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	ServletContext servletContext;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	
//	@ResponseBody
//	@RequestMapping("/article")
//	public String imageUpload(MultipartHttpServletRequest request) throws IOException
//	{
//		System.out.println("���� ����?");
//		
//		// 01. ������Ʈ���� ��Ƽ��Ʈ������ �޾Ƽ�
//		MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
//		List<MultipartFile> list = multiFileMap.get(FILE2);
//		MultipartFile multipartFile = list.get(0);
//		LOGGER.debug(multipartFile.getOriginalFilename());
//
//		// 02. ������ �����ϰ�
//		String webappRoot = servletContext.getRealPath("/");
//		String filename = UPLOADIMG + multipartFile.getOriginalFilename();
//		File file = new File(webappRoot + filename);
//		multipartFile.transferTo(file);
//		
//		System.out.println("���� �ּ� : "+webappRoot);
//		// 03. �������� ���� �ּҸ� ��ȯ�Ѵ�.
//		// requet.getServername �� �ϴ�, ajax���� ������ ���� ������Ʈ ������ �ȶ��� InetAddress��
//		// �޾Ҵ�.
//		String localIP = InetAddress.getLocalHost().getHostAddress();
//		// http://�� �ٿ���� ������ â���� �ҷ��� ���� �ִ�. ��.. �ڹٽ�ũ��Ʈ������ ���ϱ�? �ϴ� �׳� ����.
//		return "http://" + localIP+ ":" + request.getServerPort() + filename;
//	}

	
	
	

	@ResponseBody
	@RequestMapping("/image")
	public void summer_image(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String save_folder = request.getSession().getServletContext().getRealPath("/image/");
		System.out.println("����� : "+save_folder);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String file_name = file.getOriginalFilename();
		String server_file_name = fileDBName(file_name, save_folder);
		System.out.println("server file : " + server_file_name);
		file.transferTo(new File(save_folder + server_file_name));
		out.println("resources/upload"+server_file_name);
		out.close();
		System.out.println("���� ��� : "+save_folder+server_file_name);
	}
	
    private String fileDBName(String fileName, String saveFolder) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);

		String homedir = saveFolder + year + "-" + month + "-" + date;
		System.out.println(homedir);
		File path1 = new File(homedir);
		if (!(path1.exists())) {
			path1.mkdir();
		}
		Random r = new Random();
		int random = r.nextInt(100000000);

		int index = fileName.lastIndexOf(".");

		String fileExtension = fileName.substring(index + 1);
		System.out.println("fileExtension = " + fileExtension);

		String refileName = "bbs" + year + month + date + random + "." + fileExtension;
		System.out.println("refileName = " + refileName);

		String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		System.out.println("fileDBName = " + fileDBName);

		return fileDBName;
	}

	
}
