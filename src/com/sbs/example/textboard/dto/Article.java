package com.sbs.example.textboard.dto;

import java.util.Map;

public class Article {
	public int id;
	public String regDate;
	public String updateDate;
	public int memberId;
	public String title;
	public String body;
	public int hit;

	public String extra__writer;

	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regDate = (String) articleMap.get("regDate");
		this.updateDate = (String) articleMap.get("updateDate");
		this.memberId = (int) articleMap.get("memberId");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		this.hit = (int) articleMap.get("hit");

		if (articleMap.get("extra__writer") != null) {
			this.extra__writer = (String) articleMap.get("extra__writer");
		}
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", memberId=" + memberId
				+ ", title=" + title + ", body=" + body + ", hit=" + hit + ", extra__writer=" + extra__writer + "]";
	}
}
