package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws IOException {

        for(int i = 0; i < args.length;i++){
            if(args[i].equals("-help")){
                System.out.println("Syntax: _heightMap _diffuseMap startingHeight(1,255) outputFileDir typeOfFile");
            }
        }

        if(args.length > 1){
            BufferedImage img = ImageIO.read(new File(args[0]));
            BufferedImage dimg = ImageIO.read(new File(args[1]));
            BufferedImage blank2 = dimg;
            dimg = new BufferedImage(dimg.getWidth(), dimg.getHeight(), BufferedImage.TYPE_INT_ARGB);
            ColorConvertOp convert = new ColorConvertOp(null);
            convert.filter(blank2, dimg);

            int width = img.getWidth();
            int height = img.getHeight();
            int defaultWL = Integer.parseInt(args[2]);
            ArrayList<PixelInf> pixels = new ArrayList<PixelInf>();
            Graphics2D g2d = dimg.createGraphics();

            for(int x = 0; x < width; x++){
                for (int y = 0; y < height; y++){
                    int rgb = img.getRGB(x,y);
                    int blue =   rgb & 0x000000ff;
                    System.out.println(blue);
                    if(blue < defaultWL){
                        Color color = new Color(2,5,20);
                        dimg.setRGB(x,y, color.getRGB());
                    }else{
                        PixelInf pixelInf = new PixelInf(x,y,img.getRGB(x,y));
                        pixels.add(pixelInf);
                    }
                    System.out.println("[" + x + "] [" + y + "]");
                }
            }
            System.out.println("Preparing pixels...");
            boolean save = false;
            for(int wl = defaultWL ; wl < 255; wl++){
                save = false;
                for(int i = 0; i < pixels.size(); i++){
                    System.out.println("[" + wl + "] [" + i + "]");
                    if(pixels.get(i).getBlue() < wl){
                        Color color = new Color(2,5,20);
                        dimg.setRGB(pixels.get(i).getX(),pixels.get(i).getY(),color.getRGB());
                        pixels.remove(i);
                        save = true;
                    }
                }
                if(save){
                    g2d.dispose();
                    System.out.println("Saving...");
                    File file = new File(args[3] + "_" + (wl) + "." + args[4]);
                    ImageIO.write(dimg, args[4], file);
                    System.out.println("Saved!");
                }
            }
            System.out.println("Done!");
        }
    }
}
