package fillapix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;

/**
 * Class to handle a Fill-a-Pix file and parse it.
 * @author Flip van Rijn
 */
public class FileHandler
{
    private String fileName;
    private int[][] numbersMatrix;
    BufferedReader inFile;

    /**
     * Initializes a new file object and determines the size of the puzzle.
     * @param fileName File name of the puzzle
     */
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

    /**
     * Parses the lines in a file and returns the multi-dimensional numbers
     * array which represents the puzzle. 
     * @return int array
     */
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

    /**
     * Counts the number of lines and the line size to figure out how large
     * the puzzle will be.
     */
    private void setSize()
    {
        int countLines = countLines();
        int lineSize = getLineSize();
        
        numbersMatrix = new int[countLines][lineSize];
    }

    /**
     * Determines the size of a line.
     * @return int Line size
     */
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

    /**
     * Counts the number of lines.
     * @return int Number of lines
     */
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

    /**
     * Transforms a Grid object of the puzzle into a .gif format.
     * @param fileName The name of the picture
     * @param picture The Grid object of the puzzle
     * @return boolean Save successful
     */
    public boolean toImage(String fileName, Grid picture)
    {
        Image image = new Image(picture.getHeight(), picture.getWidth());
        image.draw(picture.getMatrix());
        
        return image.save(fileName);
    }

    /**
     * Returns a number representation of a character.
     * @param character Character to represent
     * @return int Transformed character
     */
    private int getValue(char character)
    {
        if (character == ' ')
            return -1;

        return Character.getNumericValue(character);
    }
}
