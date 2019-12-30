import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class makeBig extends JFrame {
	int delete = 0;
	static Color b = new Color(221,221,221);
	String image_name = null;
	BufferedImage img  = null,slice_img = null;
	int slice = 0;
	BufferedImage big_img  = null;
	String address = null;
	
	BufferedImage image = new BufferedImage(500,500, BufferedImage.TYPE_INT_RGB);
	
    JMenuBar mb = new JMenuBar();
    JMenuItem [] FileMenu = new JMenuItem [3];
    JMenu FMenu = new JMenu("File");
    MyPanel Mypanel = new MyPanel();
    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    int returnValue ;
	public makeBig(String fname){
		createMenu();
		address = fname;
		setContentPane(Mypanel);
		setSize(500,500);
		setVisible(true);
	}
	
    void createMenu() {
    	MenuActionListener listener = new MenuActionListener(); 
    	String[] FileItems = {"Delete","Slice","Exit"};
    	JButton a = new JButton("dd");
		for(int i=0; i<3; i++) {
				FileMenu[i] = new JMenuItem(FileItems[i]); 
				FileMenu[i].addActionListener(listener); 
				FMenu.add(FileMenu[i]);
		}
		mb.add(FMenu);
        setJMenuBar(mb);
    }
    
   
    
    
    class MenuActionListener implements ActionListener { 
    	JColorChooser chooser=new JColorChooser();
    	
    	public void actionPerformed(ActionEvent e) {
    		String cmd = e.getActionCommand(); 
    		switch(cmd) { // 메뉴 아이템의 종류 구분
    			case "Delete" : delete = 1; repaint(); break;
    			case "Exit" : System.exit(0); break;
    			case "Slice" : slice = 1; break;
    			case "Load" : 
    				   returnValue = jfc.showOpenDialog(null);
    					if (returnValue == JFileChooser.APPROVE_OPTION) {
    					File selectedFile = jfc.getSelectedFile();
    					System.out.println(selectedFile.getAbsolutePath());
    					try {
        					img = ImageIO.read(new File(selectedFile.getAbsolutePath()));
        				}catch(IOException e1) {
        					JOptionPane.showMessageDialog(null, "Fail");
        				}
    					repaint();
    				} //L
    				   break;
    		}
    	}
    }
	
	class MyPanel extends JPanel{
		Point startP=null;
		Point endP=null;
		Point RealP=null;
		Color colour = null;
		public MyPanel(){
			MyMouseListener ml = new MyMouseListener();
			this.setBackground(b);
			this.addMouseListener(ml);
			this.addMouseMotionListener(ml);
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g); // 부모 페인트호출	
			Graphics2D g2 = (Graphics2D)g;
			
			
			if(delete == 0) {
				try {
   					img = ImageIO.read(new File(address));
   				}catch(IOException e1) {
   					JOptionPane.showMessageDialog(null, "Fail");
   				}
				
				if(colour!=null) {
					slice_img = img;
					for(int y = 0; y < img.getHeight(); y++) {
						   for(int x = 0; x < img.getWidth(); x++) {
						       Color colour2 = new Color(img.getRGB(x, y));
						       if(Math.abs(colour.getRed()-colour2.getRed())<10&&Math.abs(colour.getBlue()-colour2.getBlue())<10
						    		   &&Math.abs(colour.getGreen()-colour2.getGreen())<10) continue;
						       int Y = (int) (0.2126 * colour.getRed() + 0.7152 * colour.getGreen() + 0.0722 * colour.getBlue());
						       slice_img.setRGB(x, y, new Color(Y, Y, Y).getRGB());
						   }
						}
				}
				if(img != null) {
					if(slice_img!= null)g.drawImage(slice_img, 0, 0, null);
					else g.drawImage(img, 0, 0, null);
				}
				if(startP!= null) {
					big_img = img.getSubimage(startP.x-20, startP.y-20, 40, 40);
					try {
						ImageIO.write(big_img,"jpg",new File("./image/a.jpg"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(big_img!=null) {
						File source = new File("./image/a.jpg");
			        	try {
							big_img = ImageIO.read(source);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}   	  
						Image T_img = big_img.getScaledInstance(80,80,Image.SCALE_SMOOTH);
						g2.drawImage(T_img, startP.x-40, startP.y-40, 80, 80, this);
					}
				}
		
			}//case delete == 1
			else {
				delete = 0;
			
			}//case delete == 0
		}
		
		class MyMouseListener extends MouseAdapter implements MouseMotionListener{
			
			public void mousePressed(MouseEvent e){
				startP = e.getPoint();
				if(slice == 1) {
					colour = new Color(img.getRGB(e.getPoint().x, e.getPoint().y));
					repaint();
				}
			}
			
			public void mouseReleased(MouseEvent e){
				RealP = e.getPoint();
				startP = null;
				repaint();
			}
			
			public void mouseDragged(MouseEvent e){
				startP = e.getPoint();
				repaint();
				
			}
			public void mouseMoved(MouseEvent e){
				
			}
		}
	}
}