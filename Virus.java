//Virus Class
//By: Jean Luka Molina
//1/04/2022

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;
import java.util.ArrayList;

public class Virus extends Organism
{
    private int turnCount;

    public Virus()
    {
        super();
        turnCount = 0;
    }

    public Virus(int x, int y)
    {
        super(x, y);
        turnCount = 0;
    }

    public int TurnIncrease(int max)
    {
        if(this.turnCount == max)
        {
            this.turnCount = 1;
        }
        else
        {
            this.turnCount++;
        }

        return(this.turnCount);
    }

    public Organism Reproduce()
    {
        Virus sweetChild = new Virus(GetX(), GetY());

        return(sweetChild);
        
    }

    private boolean Checker(int[] list, int x, int y)
    {
        if((GetX() + list[x] < 10) || (GetX() + list[x] > 990) ||
           (GetY() + list[y] < 10) || (GetY() + list[y] > 990))
        {
            return(false);
        }

        return(true);
    }

    public void Move(ArrayList<Organism> listOfOrganism)
    {
        int [] moves = {-10, 0, 10};
        Random random = new Random();
        boolean canMove = false;

        while(!canMove)
        {
            int newX = random.nextInt(3);
            int newY = random.nextInt(3);
            canMove = Checker(moves, newX, newY);
            if(canMove)
            {
                SetX(GetX() + moves[newX]);
                SetY(GetY() + moves[newY]);
            }
        }
    }

    public void Draw(GraphicsContext gc)
    {
        gc.setFill(Color.RED);
        gc.fillOval (GetX(), GetY(), 10, 10);
    }
}
