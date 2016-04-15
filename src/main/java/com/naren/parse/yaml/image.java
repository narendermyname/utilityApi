package com.naren.parse.yaml;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class image {

 public static void main(String[] args) {
  try {
   File f = new File("C:/Users/nstanwar/Desktop/Desktop/diwali/Happy-Diwali-2013.jpg");
   BufferedImage image = ImageIO.read(f);
   int height = image.getHeight();
   int width = image.getWidth();
   System.out.println("Height : "+ height + "*" + width);
  
  } catch (IOException ioe) {
   ioe.printStackTrace();
  }
 }

}
