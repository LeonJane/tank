package test;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {
    @Test
    void test(){
        try {
//            BufferedImage image= ImageIO.read(new File("/Users/leon/GoodTank1.png"));
//            assertNotNull(image);
            BufferedImage image2=ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
