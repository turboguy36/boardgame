package kr.coding.team.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 
 * @author leehg
 *
 */
public class IndexView extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3365336821208093157L;

	public static final Logger logger = Logger.getLogger(IndexView.class.getSimpleName());
	
	JButton btnRolldice;
	JLabel labelRollingDice;
	Timer timer;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndexView frame = new IndexView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IndexView() {
		this.setTitle("보드게임");
		play(new File("bgm/bgm.wav"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		setBorderLayout(contentPane);
	}
	private void setBorderLayout(Container parent){
		// 배경화면에 나올 이미지를 파일로 가져온다.
//		final Image image = getImageFromUrl();
		final Image image = getImageFromFile("img/map.png");
		
		// 게임 컨트롤러 (버튼, 음량, 점수등)
		JPanel topPane = new JPanel();
		
		// 게임이 이뤄지는곳 (맵, 주사위)
		JPanel gamezone = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// 배경화면에 그림을 그린다.
				logger.info("width: " + this.getWidth() + "/ height: " + this.getHeight());
				
				Image scaledImage = image.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
				g.drawImage(scaledImage, 0, 0, null);
			}
		};
		// 맵의 배경색을 정한다.
		gamezone.setBackground(Color.WHITE);
		
		// 컨트롤러부분과 맵 부분을 추가한뒤, 나누는 패널을 만들어
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPane, gamezone);
		gamezone.setLayout(null);
		
		// 캐릭터 1번째
		JLabel character01 = new JLabel();
		//new ImageIcon("img/c_01.png");
		Image characterImg01 = getImageFromFile("img/c_01.png");
		Image scaledImage = characterImg01.getScaledInstance(60,60,Image.SCALE_SMOOTH);
		ImageIcon characterIcon01 = new ImageIcon(scaledImage);
		character01.setBounds(20, 540, 70, 70);
		character01.setIcon(characterIcon01);
		gamezone.add(character01);
		
		// 캐릭터 2번째
		JLabel character02 = new JLabel();
		Image characterImg02 = getImageFromFile("img/c_02.png");
		Image scaledImage02 = characterImg02.getScaledInstance(60,60,Image.SCALE_SMOOTH);
		ImageIcon characterIcon02 = new ImageIcon(scaledImage02);
		character02.setBounds(40, 540, 70, 70);
		character02.setIcon(characterIcon02);
		gamezone.add(character02);
		
		JLabel lblNewLabel = new JLabel("출발",SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 590, 98, 30);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		gamezone.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("울릉도",SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 510, 98, 30);
		//lblNewLabel_1.setBackground(Color.YELLOW);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		gamezone.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("대한민국",SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 445, 98, 30);
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 20));
		gamezone.add(lblNewLabel_2);
		topPane.setLayout(new BorderLayout(0, 0));
		
		btnRolldice = new JButton("주사위");
		topPane.add(btnRolldice, BorderLayout.WEST);
		
		ImageIcon imageIcon = new ImageIcon("img/dice.gif");
		
		labelRollingDice = new JLabel("");
		labelRollingDice.setBackground(Color.BLUE);
		labelRollingDice.setPreferredSize(new Dimension(100, 100));
		labelRollingDice.setIcon(imageIcon);
		labelRollingDice.setVisible(false);
		
		topPane.add(labelRollingDice, BorderLayout.CENTER);
		
		btnRolldice.addActionListener(this);
//		gamezone.setLayout(new BorderLayout(0, 0));
		
		// 상단부터 100만큼은 컨트롤러, 그 아래는 맵을 그린다.
		splitPane.setDividerLocation(100);
		// 중간에 벽을 없앤다.
		splitPane.setDividerSize(0);
		
		// 부모 패널에 방금 만든 패널을 추가한다.
		parent.add(splitPane);
		
	}
	private Image getImageFromFile(String fileName){
		Image image = null;
		
		try{
			image = ImageIO.read(new File(fileName));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return image;
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRolldice){
			if(!labelRollingDice.isVisible()){
				labelRollingDice.setVisible(true);
			}
			ImageIcon imageIcon = new ImageIcon("img/dice.gif");
			labelRollingDice.setIcon(imageIcon);
			
			logger.info("roll dice button clicked");
			play(new File("bgm/dice.wav"));
			
			int delay = 1000; //milliseconds
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					logger.info(evt.getSource().toString());
					
					timer.stop();
					Random random = new Random();
					int rollingResult_01 = random.nextInt(6)+1;
					int rollingResult_02 = random.nextInt(6)+1;
					logger.info("rollingResult_01::"+rollingResult_01 + "/rollingResult_02::"+rollingResult_02);
					int rollingResult = rollingResult_01 + rollingResult_02;
					
					ImageIcon imageIcon = new ImageIcon("img/"+rollingResult+".png");
					labelRollingDice.setIcon(imageIcon);
				}
			};
			timer = new Timer(delay, taskPerformer);
			timer.start();
		}
	}
	
	public void play(File file){
	    try{
	        final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
	        clip.addLineListener(new LineListener(){
	            @Override
	            public void update(LineEvent event){
	                if (event.getType() == LineEvent.Type.STOP)
	                    clip.close();
	            }
	        });

	        clip.open(AudioSystem.getAudioInputStream(file));
	        clip.start();
	    }
	    catch (Exception exc){
	        exc.printStackTrace(System.out);
	    }
	}
}
