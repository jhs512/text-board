package com.sbs.example.textboard.controller;

import java.util.Scanner;

import com.sbs.example.textboard.Container;

public abstract class Controller {
	protected Scanner scanner;
	
	public Controller() {
		this.scanner = Container.scanner;
	}
}
