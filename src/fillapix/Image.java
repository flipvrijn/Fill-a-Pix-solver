package fillapix;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Class to create an image of a Grid object.
 * @author Flip van Rijn
 */
public class Image
{
    private int sizeY, sizeX;
    private BufferedImage image;
    private int black = 0x000;
    private int white = 0xFFFFFF;

    /**
     * Constructor which sets the height and width and initializes an 
     * image object.
     * @param height Height of the image
     * @param width Width of the image
     */
    public Image(int height, int width)
    {
        this.sizeY = height;
        this.sizeX = width;
        this.image = new BufferedImage(this.sizeX, this.sizeY, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Draws each square in the grid onto the image.
     * @param matrix The grid of the puzzle
     */
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

    /**
     * Saves the drawn pixels to a file.
     * @param fileName File name
     * @return boolean Saved
     */
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
