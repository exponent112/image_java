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

public class LineDrawEx extends JFrame {
	int delete = 0;
	static Color b = new Color(221,221,221);
	String image_name = null;
	int erase = 0;
	int edit = 0;
	int slice = 0;
	int plus = 0;
	BufferedImage img  = null,plus_img = null,edit_img = null,slice_img= null;
	String address = null;
	
	BufferedImage image = new BufferedImage(500,500, BufferedImage.TYPE_INT_RGB);
	
    JMenuBar mb = new JMenuBar();
    JMenuItem [] FileMenu = new JMenuItem [5];
    JMenu FMenu = new JMenu("File");
    MyPanel Mypanel = new MyPanel();
    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    int returnValue ;
	public LineDrawEx(){
		createMenu();
		setContentPane(Mypanel);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
    void createMenu() {
    	MenuActionListener listener = new MenuActionListener(); 
    	String[] FileItems = {"Slice","plus","Save","Load","Erase"};
    	JButton a = new JButton("dd");
		for(int i=0; i<5; i++) {
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
    			//case "Delete" : delete = 1; repaint(); break;
    			case "Erase" : erase = 1; break;
    			case "Edit" : edit = 1; break;
    			case "Slice" : slice = 1; break;
    			case "plus":  
    				plus = 1;
    				returnValue = jfc.showOpenDialog(null);
    				if (returnValue == JFileChooser.APPROVE_OPTION) {
    					File selectedFile = jfc.getSelectedFile();
    					System.out.println(selectedFile.getAbsolutePath());
    					try {
    						plus_img = ImageIO.read(new File(selectedFile.getAbsolutePath()));
    					}catch(IOException e1) {
    						JOptionPane.showMessageDialog(null, "Fail");
    					}
    					repaint();
    					} //L
    					break;
    			case "Save" : 
    				Mypanel.printAll(image.getGraphics());
    				returnValue = jfc.showSaveDialog(null);
    				if(returnValue == JFileChooser.APPROVE_OPTION) {
    					File selectedFile = jfc.getSelectedFile();
    					address = selectedFile.getAbsolutePath();
    				}
    				try {
    					address = address +".jpg";
    					ImageIO.write(image,"jpg",new File(address));
    				}catch(IOException e1){
    					e1.printStackTrace();
    				}
    					
    				 break;
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
		Color colourR = null;
		Point startP=null;
		Point endP=null;
		Point RealP=null;
		
		Point EditstartP = null;
		Point EditendP = null;
		
		Point erasePoint = null;
		
		Vector<Point> savePoint = new Vector<Point>();

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
				if(img != null) g.drawImage(img, 0, 0, null);

				if(plus_img!=null) {
					if(startP != null) {
						if(edit_img!=null) {
							g2.drawImage(edit_img, EditstartP.x, EditstartP.y,EditendP.x-EditstartP.x, EditendP.y-EditstartP.y, this);
						}
						else g2.drawImage(plus_img, startP.x, startP.y,endP.x-startP.x, endP.y-startP.y, this);
					}
				}
				for(Point a: savePoint) {
					Color colour = new Color(img.getRGB(a.x, a.y));
					g2.setColor(colour);
					g2.fillOval(a.x-5,a.y-5,30,30);
				}
				  
			}//case delete == 1
			else {
				delete = 0;
			}//case delete == 0
		}
		
		class MyMouseListener extends MouseAdapter implements MouseMotionListener{
			
			public void mousePressed(MouseEvent e){
				if(plus == 1)
					startP = e.getPoint();
				if(erase == 1) {
					savePoint.add( e.getPoint());
				}
				if(edit == 1)
					EditstartP = e.getPoint();
				if(slice == 1) {
					colourR = new Color(img.getRGB(e.getPoint().x, e.getPoint().y));
					repaint();
				}
				
			}
			
			public void mouseReleased(MouseEvent e){
				if(plus == 1) {
					RealP = e.getPoint();
					plus = 0;
				}
				if(erase == 1) {
					erase = 0;
				}
				if(edit == 1)
					edit = 0;
			}
			
			public void mouseDragged(MouseEvent e){
				if(plus == 1)
					endP = e.getPoint();
				if(erase == 1) {
					savePoint.add(e.getPoint());
				}
				if(edit == 1) {
					EditendP = e.getPoint();
					edit_img= plus_img.getSubimage(EditstartP.x-startP.x, EditstartP.y-startP.y,EditendP.x-EditstartP.x, EditendP.y-EditstartP.y);
				}
				repaint();
			}
			public void mouseMoved(MouseEvent e){
			}
		}
	}
}