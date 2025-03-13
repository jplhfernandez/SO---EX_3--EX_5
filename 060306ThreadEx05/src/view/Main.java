package view;

import controller.ThreadController;

public class Main {
	public static void main(String[] args) {
		ThreadController tc1 = new ThreadController("www.uol.com.br");
		ThreadController tc2 = new ThreadController("www.terra.com.br");
		ThreadController tc3 = new ThreadController("www.google.com.br");
		
		tc1.start();
		tc2.start();
		tc3.start();
	}
}
