package com.sbs.example.textboard.controller;

import java.util.List;

import com.sbs.example.textboard.Container;
import com.sbs.example.textboard.dto.Article;
import com.sbs.example.textboard.service.ArticleService;

public class ArticleController extends Controller {

	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public void add(String cmd) {
		if ( Container.session.isLogined() == false ) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		String title;
		String body;
		int memberid = Container.session.loginedMemberId;

		System.out.println("== 게시글 생성 ==");
		System.out.printf("제목 : ");
		title = scanner.nextLine();
		System.out.printf("내용 : ");
		body = scanner.nextLine();

		int id = articleService.add(memberid, title, body);

		System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
	}

	public void delete(String cmd) {
		if ( Container.session.isLogined() == false ) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		int id = Integer.parseInt(cmd.split(" ")[2]);

		System.out.printf("== %d번 게시글 삭제 ==\n", id);

		boolean articleExists = articleService.articleExists(id);

		if (articleExists == false) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}

		articleService.delete(id);

		System.out.printf("%d번 게시글이 삭제되었습니다.\n", id);
	}

	public void showDetail(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);

		System.out.printf("== %d번 게시글 상세보기 ==\n", id);

		articleService.increaseHit(id);
		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}

		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("등록날짜 : %s\n", article.regDate);
		System.out.printf("수정날짜 : %s\n", article.updateDate);
		System.out.printf("작성자 : %s\n", article.extra__writer);
		System.out.printf("조회수 : %d\n", article.hit);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
	}

	public void modify(String cmd) {
		if ( Container.session.isLogined() == false ) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmd.split(" ")[2]);
		String title;
		String body;

		System.out.printf("== %d번 게시글 수정 ==\n", id);
		System.out.printf("새 제목 : ");
		title = scanner.nextLine();
		System.out.printf("새 내용 : ");
		body = scanner.nextLine();

		articleService.update(id, title, body);

		System.out.printf("%d번 게시글이 수정되었습니다.\n", id);
	}

	public void showList(String cmd) {
		System.out.println("== 게시물 리스트 ==");

		List<Article> articles = articleService.getArticles();

		if (articles.size() == 0) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}

		System.out.println("번호 / 작성날짜 / 작성자 / 제목");

		for (Article article : articles) {
			System.out.printf("%d / %s / %s / %s\n", article.id, article.regDate, article.extra__writer, article.title);
		}
	}

}
