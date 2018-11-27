import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;


public class Receiver {
	public static void main(String []args){
		try{
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(8081);	
			Socket socket = serverSocket.accept();
			BufferedImage bimg = ImageIO.read(new File("D:\\cat.jpg"));
			ImageIO.write(bimg, "JPG", socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
