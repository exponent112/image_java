//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.event.*;
//
//class Ex5 extends JFrame{
//    ImageIcon i =new ImageIcon("./image/user1.jpg");
//    Image im = i.getImage();
//    int x=i.getIconWidth(),y=i.getIconHeight();
//    Ex5(){
//        this.setTitle("그래픽 이미지 확대 축소 연습");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        MyPanel panel = new MyPanel();
//        panel.addKeyListener(new KeyListener(){
//            @Override
//            public void keyTyped(KeyEvent e) {
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if(e.getKeyChar()=='='){
//                    x=(int)(x+(x*0.1));
//                    y=(int)(y+(y*0.1));
//                    repaint();
//                }
//                else if(e.getKeyChar()=='-'){
//                    x=(int)(x-(x*0.1));
//                    y=(int)(y-(y*0.1));
//                    repaint();
//                }
//                
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//            }
//            
//        });
//        this.setContentPane(panel);
//        this.setSize(400,400);
//        this.setVisible(true);
//        panel.requestFocus();
//    }
//    class MyPanel extends JPanel{
//        public void paintComponent(Graphics g){
//            super.paintComponent(g);
//            g.drawImage(im,10,10, x, y, this);
//        }
//    }
//}
//public class JavaApplication42 {
//
//    public static void main(String[] args) {
//        new Ex5();
//    }
//    
//}
//
//
////출처: https://whiteit.tistory.com/entry/자바-소스코드-그래픽-이미지-확대-축소-연습 [소스코드 저장소:: For Example]