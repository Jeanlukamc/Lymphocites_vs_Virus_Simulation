//Lymphocyte Class
//By: Jean Luka Molina
//1/04/2022

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;
import java.util.ArrayList;

public class Lymphocyte extends Organism
{
    private int turnCount;
    private int killCount;

    public Lymphocyte()
    {
        super();
        turnCount = 0;
        killCount = 0;
    }

    public Lymphocyte(int x, int y)
    {
        super(x,y);
        turnCount = 0;
        killCount = 0;
    }

    public int IncreaseKillCount()
    {
        killCount++;

        return(killCount);
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
        Random random = new Random();
        Lymphocyte newLymph = new Lymphocyte(10 * (random.nextInt(100) + 1), 10 * (random.nextInt(100) + 1));
        
        return(newLymph);
    }

    public void Move(ArrayList<Organism> listOfOrganism)
    {
        Virus closestVirus = new Virus();
        double bestDistance = 100000000000000.0;
        for (Organism org : listOfOrganism)
        {
            if((org instanceof Virus))
            {
                double tempDistance = Distance(org);
                if(tempDistance < bestDistance)
                {
                    closestVirus =  (Virus) org;
                    bestDistance = tempDistance;
                }
            }
        }

        if(closestVirus.GetX() == GetX() && closestVirus.GetY() == GetY())
        {
            return;
        }
        //Diagonals
        if (closestVirus.GetX() > GetX() && closestVirus.GetY() > GetY())
        {
            SetX(GetX() + 10);
            SetY(GetY() + 10);
            return;
        }
        else if(closestVirus.GetX() < GetX() && closestVirus.GetY() > GetY())
        {
            SetX(GetX() - 10);
            SetY(GetY() + 10);
            return;
        }
        else if(closestVirus.GetX() < GetX() && closestVirus.GetY() < GetY())
        {
            SetX(GetX() - 10);
            SetY(GetY() - 10);
            return;
        }
        else if(closestVirus.GetX() > GetX() && closestVirus.GetY() < GetY())
        {
            SetX(GetX() + 10);
            SetY(GetY() - 10);
            return;
        }

        //Left
        if (closestVirus.GetX() < GetX() && closestVirus.GetY() == GetY())
        {
            SetX(GetX() - 10);
            return;
        }
        //Right
        else if (closestVirus.GetX() > GetX() && closestVirus.GetY() == GetY())
        {
            SetX(GetX() + 10);
            return;
        }
        //Up
        else if (closestVirus.GetX() == GetX() && closestVirus.GetY() > GetY())
        {
            SetY(GetX() + 10);
            return;
        }
        //Down
        else if (closestVirus.GetX() == GetX() && closestVirus.GetY() < GetY())
        {
            SetY(GetX() - 10);
            return;
        }
    }

    public double Distance(Organism virus)
    {
        double x1 = GetX(), x2 = virus.GetX();
        double y1 = GetY(), y2 = virus.GetY();
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        return(distance);
    }

    public void Draw(GraphicsContext gc)
    {
        gc.setFill(Color.WHITE);
        gc.fillOval (GetX(), GetY(), 10, 10);
    }
}
