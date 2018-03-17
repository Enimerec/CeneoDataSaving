package pl.sda;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * author:
 * Mateusz
 * Marczak
 **/
public class ImageService {
    public String getImgInBase64(String line) {
        try {
            BufferedImage image = savePhotoFromUrl(line);
            //BufferedImage bufferedImage = convertToBufferedImg(image);
            return encodeImgToBase64(image);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String encodeImgToBase64(BufferedImage bufferedImage) throws IOException {
        if(bufferedImage!=null) {
            String imageString;
            ByteArrayOutputStream bAOS = new ByteArrayOutputStream();

            ImageIO.write(bufferedImage, "png", bAOS);
            byte[] imagebytes = bAOS.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imagebytes);
            bAOS.close();

            return imageString;
        }
        return null;
    }

    private BufferedImage savePhotoFromUrl(String line){
        BufferedImage image;
        try {
            URL url = new URL(line);
            image = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("Brak zdejcia" + e.getCause());
            return null;

        }
        return image;

    }

    private BufferedImage convertToBufferedImg(Image image) {
        if(image != null) {
            BufferedImage bfImage = new BufferedImage(
                    image.getWidth(null),
                    image.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2D = bfImage.createGraphics();
            g2D.drawImage(image, 0, 0, null);
            g2D.dispose();
            return bfImage;
        }
        return null;
    }
}
