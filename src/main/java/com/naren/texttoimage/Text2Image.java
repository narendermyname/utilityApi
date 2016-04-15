package com.naren.texttoimage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

public class Text2Image {

	public static void main(String[] args) throws Exception {
		text2Image("D:\\ffffff.png","dfdsf");
	}

	public static String text2Image(String path,String fileData) throws IOException
	{
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception useDefault) {
		}

		Object[][] data = {
				{"Hari", new Integer(23), new Double(78.23), new Boolean(true)},
				{"James", new Integer(23), new Double(47.64), new Boolean(false)},
				{"Sally", new Integer(22), new Double(84.81), new Boolean(true)}
		};

		String[] columns = {"Name", "Age", "GPA", "Pass"};

		JTable table = new JTable(data, columns);
		JScrollPane scroll = new JScrollPane(table);

		JPanel p = new JPanel(new BorderLayout());
		p.add(scroll,BorderLayout.CENTER);

		// JTable must have been added to a TLC in order to render
		// correctly - go figure.
		//JFrame f = new JFrame("Never shown");
		//f.setContentPane(scroll);
		//f.pack();

		JTableHeader h = table.getTableHeader();
		Dimension dH = h.getSize();
		Dimension dT = table.getSize();
		int x = (int)dH.getWidth();
		int y = (int)dH.getHeight() + (int)dT.getHeight();

		scroll.setDoubleBuffered(false);

		BufferedImage bi = new BufferedImage(
				(int)x,
				(int)y,
				BufferedImage.TYPE_INT_RGB
				);

		Graphics g = bi.createGraphics();
		h.paint(g);
		g.translate(0,h.getHeight());
		table.paint(g);
		g.dispose();
		File file=new File(path);
		//  JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(bi)));
		ImageIO.write(bi,"png",file);

		// our TLC forces us to explicitly exit the VM
		return file.getAbsolutePath();
	}
	public void createImage(){
		File file = new File("D:\\water-drop.jpg");

		try {          
			// Reading a Image file from file system
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);

			// Converting Image byte array into Base64 String
			String imageDataString = null;//encodeImage(imageData);

			// Converting a Base64 String into Image byte array
			byte[] imageByteArray =null;// decodeImage(imageDataString);

			// Write a image byte array into file system
			FileOutputStream imageOutFile = new FileOutputStream(
					"Dwater-drop-after-convert.jpg");
			imageOutFile.write(imageByteArray);

			imageInFile.close();
			imageOutFile.close();

			System.out.println("Image Successfully Manipulated!");
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}

	}

	/**
	 * Encodes the byte array into base64 string
	 *
	 * @param imageByteArray - byte array
	 * @return String a {@link java.lang.String}
	 */
/*	public static String encodeImage(byte[] imageByteArray) {
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}

	*//**
	 * Decodes the base64 string into byte array
	 *
	 * @param imageDataString - a {@link java.lang.String}
	 * @return byte array
	 *//*
	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}
*/}