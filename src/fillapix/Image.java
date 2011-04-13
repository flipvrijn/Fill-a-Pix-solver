package fillapix;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/**
 *
 * @author flipvanrijn
 */
public class Image
{
    private int sizeY, sizeX;
    private BufferedImage image;
    private int black = 0x000;
    private int white = 0xFFFFFF;

    public Image(int height, int width)
    {
        this.sizeY = height;
        this.sizeX = width;
        this.image = new BufferedImage(this.sizeX, this.sizeY, BufferedImage.TYPE_INT_RGB);
    }

    public void draw(Grid.Square[][] matrix)
    {
        for (int y = 0; y < matrix.length; y++)
        {
            for (int x = 0; x < matrix[0].length; x++)
            {
                if (matrix[y][x] == Grid.Square.Black)
                    this.image.setRGB(x, y, this.black);
                else
                    this.image.setRGB(x, y, this.white);
            }
        }
    }

    public boolean save(String fileName)
    {
        File file = new File(fileName);
        try
        {
            if (ImageIO.write((RenderedImage) this.image, "gif", file))
                return true;
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        
        return false;
    }
}
