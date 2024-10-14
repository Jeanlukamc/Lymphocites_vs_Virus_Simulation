//Main 
//By: Jean Luka Molina
//1/04/2022

import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import java.util.Random;
import java.util.ArrayList;

public class Main extends Application
{
    private ArrayList<Organism> cells = new ArrayList<Organism>();
    private Random random = new Random();
    private int turn;

    GraphicsContext gc;


    private void update ()
	{
        //Draw the Canvas
		gc.setFill( Color.BLACK );  
		gc.fillRect( 0, 0, 1000, 1000 );
        //Count viruses / check if there are no viruses, and draw
        for(Organism o : cells)
        {
            o.Draw(gc);
        }
        /* Sleep for 1 second */
		try 
        {
			Thread.sleep( 200 );
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace( );
		}
    }

    public void UpdateArray()
    {
        //Check if there are any viruses that need to be destroyed by lymphocytes
        ArrayList<Organism> removableViruses = new ArrayList< Organism >();
        ArrayList<Organism> removableLymphs = new ArrayList< Organism >();

        for(Organism org : cells)
        {
            if(org instanceof Lymphocyte == true)
            {
                Lymphocyte possibleLympDeath = (Lymphocyte) org;
                for(Organism possibleVirus : cells)
                {
                    if((possibleVirus instanceof Virus) && (possibleVirus.GetX() == possibleLympDeath.GetX()) && (possibleVirus.GetY() == possibleLympDeath.GetY()))
                    {
                        removableViruses.add(possibleVirus);
                        int totalKills = possibleLympDeath.IncreaseKillCount();
                        //System.out.println(totalKills);
                        if(totalKills >= 10)
                        {
                            //System.out.println("reached here!");
                            removableLymphs.add(possibleLympDeath);
                        }
                    }
                }
            }
        }
        cells.removeAll(removableViruses);
        cells.removeAll(removableLymphs);
    }

    public void MoveCells(int lymphMax, int virusMax)
    {

        ArrayList<Integer> indexes = new ArrayList< Integer >();
        //Move and reproduce each cell based on their turn counter
        for(Organism org : cells)
        {
            int count = 0;
            org.Move(cells);
            if(org instanceof Virus)
            {
                count = org.TurnIncrease(virusMax);
            }
             
            if(count == virusMax)
            {
                indexes.add(cells.indexOf(org));
            }
        }
        
        for(Integer i : indexes)
        {
            int index = i;
            cells.add(cells.get(index).Reproduce());
        }

        //Change Lymphocyte spawn rate here!
        if (lymphMax % 2 == 0)
        {
            cells.add(new Lymphocyte(10* (random.nextInt(100) + 1), 10 * (random.nextInt(100) + 1)));
        }

    }

    public boolean CheckWinner()
    {
        int count = 0;

        //Move and reproduce each cell based on their turn counter
        for(Organism org : cells)
        {

            if(org instanceof Virus)
            {
                count++;
            }
        }

        if(count == 0)
        {
            System.out.println("Lymphocytes Win!");
            return(true);
        }
        if(count >= 1000)
        {
            System.out.println("Viruses Win!");
            return(true);
        }

        return(false);
    }

    @Override
    public void start( Stage stage ) 
    {
        //Create the graphic's context
        gc = JIGraphicsUtility.setUpGraphics(stage, "Plague Inc.", 1000, 1000 );

        //Add only one virus
        cells.add(new Virus(10* (random.nextInt(100) + 1), 10 * (random.nextInt(100) + 1)));
        //Add 10 Lymphocytes
        for(int i = 0; i < 10; i++)
        {
            cells.add(new Lymphocyte(10* (random.nextInt(100) + 1), 10 * (random.nextInt(100) + 1)));
        }

        turn = 0;
        //Start the timer
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) 
            {
                update();
                UpdateArray();
                //Change second number for Virus spawn rate
                MoveCells(turn, 5);

                if(CheckWinner())
                {
                    stop();
                }
                
                if (turn == 100)
                {
                    turn = 1;
                }
                else
                {
                    turn++;
                }
                //System.out.println(cells.size());
            }
        };
        timer.start();
    }
    public static void main(String [] args)
    {
        launch(args);
    }

}
