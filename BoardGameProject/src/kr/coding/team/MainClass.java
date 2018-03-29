package kr.coding.team;

import javax.swing.SwingUtilities;

import kr.coding.team.view.Board;


public class MainClass {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			
			@Override
			public void run() {
				new Board("보드게임");
			}
		});
	}
}
