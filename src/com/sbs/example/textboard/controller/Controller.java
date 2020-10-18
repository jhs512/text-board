package com.sbs.example.textboard.controller;

import java.sql.Connection;
import java.util.Scanner;

public abstract class Controller {
	protected Connection conn;
	protected Scanner scanner;

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
}
