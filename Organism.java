//Organism Class
//By: Jean Luka Molina
//1/04/2022

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

public abstract class Organism
{
    private int xPosition;
    private int yPosition;

    public Organism()
    {
        xPosition = 10;
        yPosition = 10;
    }

    public Organism(int x, int y)
    {
        this.xPosition = x;
        this.yPosition = y;
    }

    public int GetX()
    {
        return(xPosition);
    }

    public int GetY()
    {
        return(yPosition);
    }
    
    public Organism SetX(int x)
    {
        this.xPosition = x;
        return(this);
    }

    public Organism SetY(int y)
    {
        this.yPosition = y;
        return(this);
    }

    public abstract void Move(ArrayList<Organism> listOfOrganism);

    public abstract int TurnIncrease(int max);
    
    public abstract Organism Reproduce();

    public abstract void Draw(GraphicsContext gc);

}