package fillapix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;

/**
 *
 * @author flipvanrijn
 */
public class FileHandler
{
    private String fileName;
    private int[][] numbersMatrix;
    BufferedReader inFile;

    public FileHandler(String fileName)
    {
        this.fileName = fileName;
        try
        {
            inFile = new BufferedReader(new FileReader(fileName));
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        
        setSize();
    }

    public int[][] read()
    {
        try
        {
            String line;
            int row = 0;
            while ((line = inFile.readLine()) != null)
            {
                for (int i = 0; i < line.length(); i++)
                    numbersMatrix[row][i] = getValue(line.charAt(i));
                
                row++;
            }

            inFile.close();
        }
        catch (Exception e)
        {
            System.err.println("Error while reading!" + e.getMessage());
        }
        
        return numbersMatrix;
    }

    private void setSize()
    {
        int countLines = countLines();
        int lineSize = getLineSize();
        
        numbersMatrix = new int[countLines][lineSize];
    }

    public int getLineSize()
    {
        int lineSize = 0;
        try
        {
            BufferedReader file = new BufferedReader(new FileReader(fileName));
            String line = file.readLine();
            lineSize = line.length();
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }

        return lineSize;
    }

    public int countLines()
    {
        int countLines = 0;
        
        try {
            LineNumberReader line = new LineNumberReader(new FileReader(fileName));
            
            while (line.readLine() != null)
                countLines++;

            line.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return countLines;
    }

    public boolean toImage(String fileName, Grid picture)
    {
        Image image = new Image(picture.getHeight(), picture.getWidth());
        image.draw(picture.getMatrix());
        
        return image.save(fileName);
    }

    private int getValue(char character)
    {
        if (character == ' ')
            return -1;

        return Character.getNumericValue(character);
    }
}
