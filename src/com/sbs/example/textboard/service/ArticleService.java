package com.sbs.example.textboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.textboard.Container;
import com.sbs.example.textboard.dao.ArticleDao;
import com.sbs.example.textboard.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public int add(int memberid, String title, String body) {
		return articleDao.add(memberid, title, body);
	}

	public boolean articleExists(int id) {
		return articleDao.articleExists(id);
	}

	public void delete(int id) {
		articleDao.delete(id);
	}

	public Article getForPrintArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void update(int id, String title, String body) {
		articleDao.update(id, title, body);		
	}

	public List<Article> getForPrintArticles(int page, int itemsInAPage, String searchKeyword) {
		int limitFrom = (page - 1) * itemsInAPage;
		int limitTake = itemsInAPage;
		
		Map<String, Object> args = new HashMap<>();
		args.put("searchKeyword", searchKeyword);
		args.put("limitFrom", limitFrom);
		args.put("limitTake", limitTake);
		
		return articleDao.getArticles(args);
	}

	public void increaseHit(int id) {
		articleDao.increaseHit(id);
	}

}
