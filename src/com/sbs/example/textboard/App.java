package com.sbs.example.textboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sbs.example.textboard.controller.ArticleController;
import com.sbs.example.textboard.controller.MemberController;
import com.sbs.example.textboard.util.DBUtil;
import com.sbs.example.textboard.util.SecSql;

public class App {
	public void run() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어) ");
			String cmd = scanner.nextLine();
			cmd = cmd.trim();

			// DB 연결 시작
			Connection conn = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.err.println("예외 : MySQL 드라이버 클래스가 없습니다.");
				System.out.println("프로그램을 종료합니다.");
				break;
			}

			String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
			try {
				conn = DriverManager.getConnection(url, "sbsst", "sbs123414");

				int actionResult = action(conn, scanner, cmd);

				if (actionResult == -1) {
					break;
				}
			} catch (SQLException e) {
				System.err.println("예외 : DB에 연결할 수 없습니다.");
				System.out.println("프로그램을 종료합니다.");
				break;
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// DB 연결 끝
		}

		scanner.close();
	}

	private int action(Connection conn, Scanner scanner, String cmd) {

		MemberController memberController = new MemberController(conn, scanner);
		ArticleController articleController = new ArticleController(conn, scanner);
		
		if (cmd.equals("member join")) {
			memberController.join(cmd);
		} else if (cmd.equals("article add")) {
			articleController.add(cmd);
		} else if (cmd.startsWith("article delete ")) {
			articleController.delete(cmd);
		} else if (cmd.startsWith("article detail ")) {
			articleController.showDetail(cmd);
		} else if (cmd.startsWith("article modify ")) {
			articleController.modify(cmd);
		} else if (cmd.equals("article list")) {
			articleController.showList(cmd);
		} else if (cmd.equals("system exit")) {
			System.out.println("== 프로그램 종료 ==");
			return -1;
		}

		return 0;
	}

}
