package kr.coding.team.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Board implements ActionListener{
	private final String TITLE;
	Logger logger = Logger.getLogger(getClass().getSimpleName());
	
	/**
	 * 생성자 
	 * @param title : 윈도우 제목
	 */
	public Board(String title) {
		this.TITLE = title;
		
		// 윈도우
		JFrame frame = new JFrame(this.TITLE);
		// 윈도우 위치 및 크기 지정
		frame.setBounds(100 , 100 , 700 , 800);
		// x 버튼을 누르면 모든 프로그램 종료하도록 지정
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 그림 및 버튼을 담을 객체
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		setBorderLayout(contentPane);
		
		frame.setVisible(true);
	}
	
	private void setBorderLayout(Container parent){
		// 배경화면에 나올 이미지를 파일로 가져온다.
		final Image image = getImageFromUrl();
		
		// 게임 컨트롤러 (버튼, 음량, 점수등)
		JPanel topPane = new JPanel();
		// 게임이 이뤄지는곳 (맵, 주사위)
		JPanel gamezone = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// 배경화면에 그림을 그린다.
				Image scaledImage = image.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
				g.drawImage(scaledImage, 0, 0, null);
			}
		};
		// 맵의 배경색을 정한다.
		gamezone.setBackground(Color.WHITE);
		setTextEditors(gamezone);
		
		// 컨트롤러부분과 맵 부분을 추가한뒤, 나누는 패널을 만들어
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPane, gamezone);
		// 상단부터 100만큼은 컨트롤러, 그 아래는 맵을 그린다.
		splitPane.setDividerLocation(100);
		// 중간에 벽을 없앤다.
		splitPane.setDividerSize(0);
		
		// 부모 패널에 방금 만든 패널을 추가한다.
		parent.add(splitPane);
	}
	private void setTextEditors(JPanel panel){
		panel.setLayout(new BorderLayout());
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.GREEN);
		northPanel.setSize(700, 100);
//		northPanel.setBounds(0, 0, 700, 100);
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.BLUE);
		southPanel.setSize(700, 100);
//		southPanel.setBounds(0, 555, 700, 100);
		
		JPanel eastPanel = new JPanel();
		eastPanel.setBackground(Color.PINK);
		eastPanel.setSize(100, 700);
//		eastPanel.setBounds(580, 0, 100, 700);
		
		JPanel westPanel = new JPanel();
		westPanel.setBackground(Color.RED);
		westPanel.setSize(100, 700);
//		westPanel.setBounds(0, 0, 100, 100);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.YELLOW);
		centerPanel.setSize(500, 500);
//		centerPanel.setBounds(100, 100, 500, 500);
		
//		JLabel label = new JLabel("출발");
		panel.add(northPanel); //north
		panel.add(southPanel); 
		panel.add(eastPanel); // west
//		panel.add(westPanel);// center
//		panel.add(centerPanel); // south
	}
	private Image getImageFromUrl(){
		Image image = null;
		
		try{
			image = ImageIO.read(new URL("http://dev.bacoder.kr/img/map.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return image;
	}
	private Image getImageFromFile(){
		Image image = null;
		
		try{
			image = ImageIO.read(new File("img/map.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return image;
	}
	/**
	 * 어떠한 액션이 감지 되었을 때 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Object src = evt.getSource();
		
		logger.info("actionPerformed::::" + src);
		
//		if(src.equals(obj))
	}

}
