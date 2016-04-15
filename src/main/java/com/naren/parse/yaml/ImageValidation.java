/**
 * 
 */
package com.naren.parse.yaml;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author nstanwar
 *Thnis class created for validation of images 
 */
public class ImageValidation {

	/**
	 * 
	 */
	public ImageValidation() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("start of main method");
		String imageFolderPath="C:/Users/nstanwar/Desktop/Desktop/diwali";
		String logFIleName="C:/Users/nstanwar/Desktop/Desktop/log/narender.log";
		validateImageFiles(imageFolderPath,logFIleName);
		System.out.println("end of main method");
	}
	/**
	 * THis method use to validate(height width and DPI) image files form imageFolderPath  
	 */
	public static void validateImageFiles(String imageFolderPath,String logFIleName)
	{
		System.out.println("start of validateImageFiles method");
		File file = new File(imageFolderPath);
		File[] fileList=file.listFiles();
		PrintWriter error =null;
		System.out.println("files found "+fileList.length);
		try {
			error  = new PrintWriter(new FileWriter(logFIleName));;
			for(File imageFile:fileList)
			{
				BufferedImage image = ImageIO.read(imageFile);
				ImageInputStream iis = ImageIO.createImageInputStream(imageFile);
                Iterator i = ImageIO.getImageReaders( iis );
                ImageReader r = (ImageReader) i.next();
				//int[] dpi=getResolution(r);
				int height = image.getHeight();
				int width = image.getWidth();
				System.out.println("File Name = "+imageFile.getName()+"Width : "+ width+" height : "+ height);
				if(height==1200  && width==1800)
				{	
					error.write("Invalid File "+imageFile.getAbsolutePath());
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}finally {
			error.close();
			
		}
		System.out.println("start of validateImageFiles method");
	}
	/**
	 * This method is use to get DPI of Image 
	 * @param r
	 * @return
	 * @throws IOException
	 */
	public static int[] getDPI(ImageReader r) throws IOException {
		System.out.println("Start of getDpit methoD file Name ");
        int hdpi=96, vdpi=96;
        double mm2inch = 25.4;
        NodeList lst;
        Element node = (Element)r.getImageMetadata(0).getAsTree("javax_imageio_1.0");
        lst = node.getElementsByTagName("HorizontalPixelSize");
        if(lst != null && lst.getLength() == 1) hdpi = (int)(mm2inch/Float.parseFloat(((Element)lst.item(0)).getAttribute("value")));
        lst = node.getElementsByTagName("VerticalPixelSize");
        if(lst != null && lst.getLength() == 1) vdpi = (int)(mm2inch/Float.parseFloat(((Element)lst.item(0)).getAttribute("value")));
        System.out.println("Start of getDpit methoD ");
        return new int[]{hdpi, vdpi};
    }

}
