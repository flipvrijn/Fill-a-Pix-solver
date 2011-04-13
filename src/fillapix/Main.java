package fillapix;

/**
 *
 * @author flipvanrijn
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        FileHandler handler = new FileHandler("puzzle4.txt");
        int[][] matrix = handler.read();
        Solver solver = new Solver(matrix);
        solver.solve();
        solver.show();

        handler.toImage("puzzle4.gif", solver.getPicture());
    }
}