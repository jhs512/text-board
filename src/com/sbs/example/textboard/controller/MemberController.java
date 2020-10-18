package com.sbs.example.textboard.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.sbs.example.textboard.util.DBUtil;
import com.sbs.example.textboard.util.SecSql;

public class MemberController {
	private Connection conn;
	private Scanner scanner;
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void join(String cmd) {
		String loginId;
		String loginPw;
		String loginPwConfirm;
		String name;

		System.out.println("== 회원가입 ==");

		// 로그인 아이디 입력
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = scanner.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("로그인 아이디를 입력해주세요.");
				continue;
			}
			
			SecSql sql = new SecSql();

			sql.append("SELECT COUNT(*) > 0");
			sql.append("FROM member");
			sql.append("WHERE loginId = ?", loginId);
			
			boolean isLoginIdDup = DBUtil.selectRowBooleanValue(conn, sql);
			
			if (isLoginIdDup) {
				System.out.printf("%s(은)는 이미 사용중인 로그인 아이디를 입니다.\n", loginId);
				continue;
			}

			break;
		}

		// 로그인 비번 입력
		while (true) {
			System.out.printf("로그인 비번 : ");
			loginPw = scanner.nextLine().trim();

			if (loginPw.length() == 0) {
				System.out.println("로그인 비번을 입력해주세요.");
			}

			// 로그인 비번 확인 입력
			boolean loginPwConfirmIsSame = true;

			while (true) {
				System.out.printf("로그인 비번확인 : ");
				loginPwConfirm = scanner.nextLine().trim();

				if (loginPwConfirm.length() == 0) {
					System.out.println("로그인 비번확인을 입력해주세요.");
					continue;
				}

				if (loginPw.equals(loginPwConfirm) == false) {
					System.out.println("로그인 비번이 일치하지 않습니다. 다시 입력해주세요.");
					loginPwConfirmIsSame = false;
					break;
				}

				break;
			}

			// 로그인 비번과 로그인 비번확인이 일치한다면 입력완료라고 본다.
			if (loginPwConfirmIsSame) {
				break;
			}
		}

		// 이름
		while (true) {
			System.out.printf("이름 : ");
			name = scanner.nextLine().trim();

			if (name.length() == 0) {
				System.out.println("이름을 입력해주세요.");
				continue;
			}

			break;
		}

		SecSql sql = new SecSql();

		sql.append("INSERT INTO member");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);

		int id = DBUtil.insert(conn, sql);

		System.out.printf("%s님 환영합니다.\n", name);
	}
}
