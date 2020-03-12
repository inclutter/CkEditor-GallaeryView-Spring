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
import org.springframework.web.servlet.ModelAndView;

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
	@Autowired
	DBService dbService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpServletRequest request, VO vo) {
		// �̹��� ����
		int number = 0;
		// ���� ������ ����
		int currentPageNo = 0;
		// ���� �� ���ڵ� ����
		int currentRecord = 0;

		if (request.getParameter("number") != null) {
			number = Integer.parseInt(request.getParameter("number"));
		} else {
			number = 3;
		}

		if (request.getParameter("currentPageNo") != null) {
			currentPageNo = (Integer.parseInt(request.getParameter("currentPageNo")));
			currentRecord = ((currentPageNo - 1) * (number * 4));
			if (currentPageNo <= 0) {
				currentPageNo = 0;
				currentRecord = currentPageNo * (number * 4);
			}
		}

		BoardPaging bp = new BoardPaging(dbService.getCount(), currentPageNo, number);
		System.out.println("���ڵ尪 : " + dbService.getCount());

		// ������ �ȿ� ǥ�õǴ� ���ڵ� ���� ����
		if (currentRecord == 0) {
			vo.setMinLimit(currentRecord);
		} else {
			vo.setMinLimit(currentRecord+1);
		}

		vo.setMaxLimit(currentRecord + (number * 4));
		System.out.println("���� ������ : " + currentPageNo);
		System.out.println("�ּҰ� : " + vo.getMinLimit());
		System.out.println("�ִ밪 : " + vo.getMaxLimit());

		ModelAndView mav = new ModelAndView("home");
		// ����Ʈ
		mav.addObject("boardList", dbService.listBoard(vo));
		// �̹��� ���� view�� ����
		mav.addObject("number", number);
		// ����¡ ó�� view�� ����
		mav.addObject("boardPage", bp);

		return mav;
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

	@RequestMapping("/insertForm.do")
	public String insertForm() {
		return "insertForm";
	}

}
