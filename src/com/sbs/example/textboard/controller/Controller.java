package com.sbs.example.textboard.controller;

import java.sql.Connection;
import java.util.Scanner;

public abstract class Controller {
	protected Scanner scanner;
	
	public Controller(Scanner scanner) {
		this.scanner = scanner;
	}
}
