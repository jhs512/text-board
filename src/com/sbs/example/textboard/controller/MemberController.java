package com.sbs.example.textboard.controller;

import com.sbs.example.textboard.Container;
import com.sbs.example.textboard.dto.Member;
import com.sbs.example.textboard.service.MemberService;

public class MemberController extends Controller {
	private MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
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

			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

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

		int id = memberService.join(loginId, loginPw, name);

		System.out.printf("%s님 환영합니다.\n", name);
	}

	public void login(String cmd) {
		String loginId = null;
		String loginPw;

		System.out.println("== 로그인 ==");

		int loginIdTryMaxCount = 3;
		int loginIdTryCount = 0;

		// 로그인 아이디 입력
		while (true) {
			if (loginIdTryCount >= loginIdTryMaxCount) {
				System.out.println("로그인 아이디를 확인 후 다시 시도해주세요.");
				return;
			}

			System.out.printf("로그인 아이디 : ");
			loginId = scanner.nextLine().trim();

			if (loginId.length() == 0) {
				loginIdTryCount++;
				System.out.println("로그인 아이디를 입력해주세요.");
				continue;
			}

			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

			if (isLoginIdDup == false) {
				System.out.printf("%s(은)는 존재하지 않는 로그인 아이디 입니다.\n", loginId);
				continue;
			}

			break;
		}

		Member member = memberService.getMemberByLoginId(loginId);

		int loginPwTryMaxCount = 3;
		int loginPwTryCount = 0;

		// 로그인 비번 입력
		while (true) {
			if (loginPwTryCount >= loginPwTryMaxCount) {
				System.out.println("비밀번호를 확인 후 다시 시도해주세요.");
				return;
			}

			System.out.printf("로그인 비번 : ");
			loginPw = scanner.nextLine().trim();

			if (loginPw.length() == 0) {
				System.out.println("로그인 비번을 입력해주세요.");
				continue;
			}

			if (member.loginPw.equals(loginPw) == false) {
				loginPwTryCount++;
				System.out.printf("비번이 일치하지 않습니다.\n");
				continue;
			}

			System.out.printf("%s님 환영합니다.\n", member.name);

			Container.session.login(member);

			break;
		}

	}

	public void whoami(String cmd) {
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 상태가 아닙니다.");
		} else {
			System.out.println(Container.session.loginedMember.loginId);
		}
	}

	public void logout(String cmd) {
		Container.session.logout();

	}
}
