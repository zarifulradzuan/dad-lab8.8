import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Sender {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sender window = new Sender();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sender() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblImage = new JLabel("Image");
		lblImage.setBounds(182, 78, 46, 14);
		frame.getContentPane().add(lblImage);
		
		JButton btnGetImage = new JButton("Get Image");
		btnGetImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread thread = new Thread(){
					public void run(){
						try{
							Socket varSock = new Socket("localhost",8081);
							varSock.setSoTimeout(10000);
							DataInputStream in = new DataInputStream(varSock.getInputStream());
							BufferedImage bimg = ImageIO.read(ImageIO.createImageInputStream(varSock.getInputStream()));
							ImageIcon varImgIcon = new ImageIcon(bimg);
							
							lblImage.setIcon(varImgIcon);
						}catch(IOException e){
							e.printStackTrace();
						}
					}
				};
				thread.start();
			}
		});
		btnGetImage.setBounds(156, 203, 89, 23);
		frame.getContentPane().add(btnGetImage);
	}

}
