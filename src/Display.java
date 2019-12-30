import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;

public class Display extends JFrame  {
	MyPanel menu = new MyPanel();	
	static String originPicture = "./image/user1.jpg";
	static String fname ="./image/user1.jpg";
	makeGray grayT ;
	Display toGray ;
	Image img;
	JLabel imgLabel;

	Display(){
		setLocation(500,300);
		setContentPane(menu);
		setSize(670,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	class MyPanel extends JPanel implements ActionListener {
		JButton gray = new JButton("흑백");
		int grayInt = 0;
		JButton origin = new JButton("원본");
		JButton plus = new JButton("합치기");
		JButton big = new JButton("돋보기/색 자르기");
		JButton edge = new JButton("Edge");
		JButton negative = new JButton("Negative");
		JButton red = new JButton("Red");
		JTextField name = new JTextField(10);
		JButton addPic = new JButton("MarkName");
		JButton blue = new JButton("Blue");
		JButton green = new JButton("Green");
		JButton picture = new JButton("사진바꾸기");
		JButton Con = new JButton("선명하게");
		//JButton ColorSlice = new JButton("색 자르기");
		JSlider slider_b = new   JSlider(1, 100,50); //0 //1 %10
		JSlider slider_d = new   JSlider(10, 100,50); //0 //1 %10
		JLabel b_name = new JLabel("밝기조절");
		Bright bright;
		float brightScale = (float) 1.0;
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue;
		public MyPanel(){
			add(picture);
			add(gray);
			add(edge);
			add(negative);
			add(red);
			add(blue);
			add(green);
			add(name);
			add(addPic);
			add(big);
			add(plus);
			add(Con);
			//add(ColorSlice);
			add(origin);
			add(b_name);
			slider_b.setMajorTickSpacing(2);
			slider_b.addChangeListener((ChangeListener) new ChangeListener() {
		        @Override
		        public void stateChanged(ChangeEvent ce) {
		            int value = (int) ((JSlider) ce.getSource()).getValue();
		            float temp = (float) (value/1.0);
		            brightScale = temp;
		            try {
						bright = new Bright(Display.originPicture,brightScale);
						Display.fname ="./image/bright.jpg";
						repaint();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
			slider_d.setMajorTickSpacing(2);
			slider_d.addChangeListener((ChangeListener) new ChangeListener() {
		        @Override
		        public void stateChanged(ChangeEvent ce) {
		            int value = (int) ((JSlider) ce.getSource()).getValue();
		            float temp = (float) (value/100.0);
		            brightScale = temp;
		            try {
						bright = new Bright(Display.originPicture,brightScale);
						Display.fname ="./image/bright.jpg";
						repaint();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
			add(slider_b);
			add(slider_d);

			Con.addActionListener(this);
		//	ColorSlice.addActionListener(this);
			gray.addActionListener(this);
			addPic.addActionListener(this);
			edge.addActionListener(this);
			negative.addActionListener(this);
			picture.addActionListener(this);
			red.addActionListener(this);
			blue.addActionListener(this);
			green.addActionListener(this);
			origin.addActionListener(this);
			big.addActionListener(this);
			plus.addActionListener(this);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==gray) {
				try {
					grayT = new makeGray(originPicture);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Display.fname ="./image/gray.png";
				repaint();
			}
			else if(e.getSource() == edge){
			 SobelTest as = new SobelTest();
			 try {
				as.EdgeDetection(originPicture);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			 Display.fname = "./image/Sobel.png";
			 repaint();
			}
			else if(e.getSource() == origin) {
				 Display.fname = originPicture;
				 repaint();
			}
			else if(e.getSource() == Con) {
				 Contrast  as = new Contrast(originPicture);
				 Display.fname = "./image/Cont.png";
				 repaint();
			}
			else if(e.getSource() == picture) {
				 returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					Display.originPicture = selectedFile.getAbsolutePath();
					Display.fname = originPicture;
					System.out.println(originPicture);
					try {
 					img = ImageIO.read(new File(selectedFile.getAbsolutePath()));
 				}catch(IOException e1) {
 					JOptionPane.showMessageDialog(null, "Fail");
 				}
					repaint();
				} //L
			}
			else if(e.getSource() == negative) {
				Negative  as;
				 as = new Negative(originPicture);
				 Display.fname = "./image/negative.jpg";
				 repaint();
			}
			else if(e.getSource() == red) {
				 RedImage as = new RedImage(originPicture);
				 Display.fname = "./image/Red.jpg";
				 repaint();
			}
//			else if(e.getSource() == ColorSlice) {
//				
//			}
			else if(e.getSource() == green) {
				 GreenImage as = new GreenImage(originPicture);
				 Display.fname ="./image/green.jpg";
				 repaint();
			}
			else if(e.getSource() == blue) {
				 BlueImage as = new BlueImage(originPicture);
				 Display.fname ="./image/blue.jpg";
				 repaint();
			}
			else if(e.getSource() == big) {
				makeBig makeplus = new makeBig(fname);
			}
			else if(e.getSource() == plus) {
				LineDrawEx makeplus = new LineDrawEx();
				
			}
			else if(e.getSource() == addPic) {
				String yourname = name.getText();
				WaterMark mark = new WaterMark (fname,yourname);
				Display.fname = "./image/mark.png";
				repaint();
			}
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g); 
			Graphics2D g2 = (Graphics2D)g;
		    try {
		      	  File source = new File(fname);
		      	  img = ImageIO.read(source);   	  
		        }catch(IOException e) {
		        }
		    Image resizeImage = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			if(img != null) g.drawImage(resizeImage,100 , 100, null);
		}
		}
		
}

