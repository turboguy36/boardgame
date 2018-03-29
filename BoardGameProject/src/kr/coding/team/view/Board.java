package kr.coding.team.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Board implements ActionListener{
	private final String TITLE;
	
	public Board(String title) {
		this.TITLE = title;
		
		// TODO Auto-generated constructor stub
		JFrame frame = new JFrame(this.TITLE);
		frame.setBounds(100 , 100 , 1000 , 800);	
		// x 버튼을 누르면 모든 프로그램 종료하도록 지정
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Object src = evt.getSource();
		
//		if(src.equals(obj))
	}

}
