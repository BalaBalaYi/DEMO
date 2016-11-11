package com.demo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.BookEntity;
import com.demo.service.BookService;
import com.demo.vo.BookVO;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/*")
public class IndexAction {

	private Logger logger = LoggerFactory.getLogger(IndexAction.class);
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("toIndex.do")
	public String toIndex(HttpServletRequest request){
		logger.info("��ת��ҳ");
		return "index";
	}
	
	@RequestMapping("toQuery.do")
	public String toQuery(HttpServletRequest request){
		logger.info("��ת��DEMOҳ");
		return "query";
	}
	
	@RequestMapping("query.do")
	public void query(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<BookVO> bookList = new ArrayList<BookVO>();
		
		String bookId = request.getParameter("bookId");
		String pageNumParam = request.getParameter("pageNum");
		
		int pageNum = 0;
		PageInfo<BookVO> page = null;
		
		if(!StringUtil.isEmpty(pageNumParam)){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		if(bookId != "" && bookId != null){
			logger.info("����id��ѯ�鼮��id:" + bookId);
			
			//����id��ѯ
			BookVO book = bookService.queryById(Integer.parseInt(bookId));
			bookList.add(book);
			resultMap.put("bookList", bookList);
		} else {
			logger.info("��ѯȫ���鼮");
			
			//��ѯ����
			bookList = bookService.queryAll(pageNum);
			resultMap.put("bookList", bookList);
			page = new PageInfo<BookVO>(bookList);
			resultMap.put("pageNum", page.getPageNum());
			resultMap.put("pages", page.getPages());
			resultMap.put("totalCount", page.getTotal());
		}
		
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(JSONObject.fromObject(resultMap).toString());
			writer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if( writer != null){
				writer.close();
			}
		}
	}
	
	@RequestMapping("toAdd.do")
	public String toAdd(){
		return "add";
	}
	
	@RequestMapping("add.do")
	public void add(HttpServletRequest request, HttpServletResponse response, BookEntity book){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		logger.info("�����鼮��book:" + book.toString());
		
		boolean result = bookService.insert(book);
		
		if(result){
			resultMap.put("addResult", "��ӳɹ���");
		} else {
			resultMap.put("addResult", "���ʧ�ܣ�");
		}
		
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(JSONObject.fromObject(resultMap).toString());
			writer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if( writer != null){
				writer.close();
			}
		}
	}
	
	@RequestMapping("toUpdate.do")
	public ModelAndView toUpdate(HttpServletRequest request){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String bookId = request.getParameter("bookId");
		
		//����id��ѯ
		BookVO book = bookService.queryById(Integer.parseInt(bookId));
		resultMap.put("book", book);
		
		return new ModelAndView("update", resultMap);
	}
	
	@RequestMapping("update.do")
	public void update(HttpServletRequest request, HttpServletResponse response, BookEntity book){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		logger.info("�޸��鼮��book:" + book.toString());
		
		boolean result = bookService.update(book);
		
		if(result){
			resultMap.put("updateResult", "�޸ĳɹ���");
		} else {
			resultMap.put("updateResult", "�޸�ʧ�ܣ�");
		}
		
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(JSONObject.fromObject(resultMap).toString());
			writer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if( writer != null){
				writer.close();
			}
		}
	}
	
	@RequestMapping("delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String bookId = request.getParameter("bookId");
		
		logger.info("ɾ���鼮��id:" + bookId);
		
		boolean result = bookService.delete(Integer.parseInt(bookId));
		
		if(result){
			resultMap.put("deleteResult", "ɾ���ɹ���");
		} else {
			resultMap.put("deleteResult", "ɾ��ʧ�ܣ�");
		}
		
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(JSONObject.fromObject(resultMap).toString());
			writer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if( writer != null){
				writer.close();
			}
		}
	}
	
	@RequestMapping("queryByISBN.do")
	public void queryByISBN(HttpServletRequest request, HttpServletResponse response){
				
		Map<String, Object> resultMap = new HashMap<String, Object>();		
		String isbn = request.getParameter("isbn");
		
		logger.info("����ISBN��ѯ�鼮��ISBN��" + isbn);
		
		BookVO book = bookService.queryByISBN(isbn);
		logger.info("����ISBN��ѯ���鼮��" + book);
		resultMap.put("book", book);
	
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(JSONObject.fromObject(resultMap).toString());
			writer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if( writer != null){
				writer.close();
			}
		}
	}
	
}
