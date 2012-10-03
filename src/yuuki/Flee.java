import java.util.Random;
import java.text.*;
/**
 *
 * @author Caleb Smith
 */
public class Flee {
    
    /**
     * Using character and monster points, this method determines what the chance
     * that the character will be able to flee is.  It also returns whether the 
     * character manages to flee of not.
     * 
     * @author Caleb Smith
     * @param monsterLevel
     * @param playerLevel 
     * @return Boolean variable if character can escape.
     */
    public boolean Flee(int playerLevel, int monsterLevel)
    {
        boolean flee = false;
        Random rand;
        double advantageLevel = playerLevel / monsterLevel;
        int randomNumber;
        rand = new Random();  
        DecimalFormat oneDigit = new DecimalFormat("#,##0.0");
        oneDigit.format(advantageLevel);
        
        //Get Character agility level.
        int agility = Character.getAgility();
        advantageLevel += (agility * 0.2);
        
        
        if(advantageLevel == 1)
        {
            randomNumber = rand.nextInt(1) + 1;
            if(randomNumber == 1)
            {
                flee = true;
            }
            else
            {
                flee = false;
            }
        }
        
        if(advantageLevel > 1)
        {
            flee = true;
        }
        
        if(advantageLevel > 0.1)
        {
            flee = false;
        }
        
        if(advantageLevel == 0.1)
        {
            randomNumber = rand.nextInt(9) + 1;
            if(randomNumber == 1)
            {
                flee = true;
            }
            else
            {
                flee = false;
            }
        }
        
        if(advantageLevel == 0.2)
        {
            randomNumber = rand.nextInt(9) + 1;
            if(randomNumber <= 2)
            {
                flee = true;
            }
            else
            {
                flee = false;
            }
        }
        
        if(advantageLevel == 0.3)
        {
            randomNumber = rand.nextInt(9) + 1;
            if(randomNumber <= 3)
            {
                flee = true;
            }
            else
            {
                flee = false;
            }
        }
        
        if(advantageLevel == 0.4)
        {
            randomNumber = rand.nextInt(9) + 1;
            if(randomNumber <= 4)
            {
                flee = true;
            }
            else
            {
                flee = false;
            }
        }
        
        if(advantageLevel == 0.5)
        {
            randomNumber = rand.nextInt(9) + 1;
            if(randomNumber <= 5)
            {
                flee = true;
            }
            else
            {
                flee = false;
            }
        }
        
        if(advantageLevel == 0.6)
        {
            randomNumber = rand.nextInt(9) + 1;
            if(randomNumber <= 6)
            {
                flee = true;
            }
            else
            {
                flee = false;
            }
        }
        
        if(advantageLevel == 0.7)
        {
            randomNumber = rand.nextInt(9) + 1;
            if(randomNumber <= 7)
            {
                flee = true;
            }
            else
            {
                flee = false;
            }
        }
        
        if(advantageLevel == 0.8)
        {
            randomNumber = rand.nextInt(9) + 1;
            if(randomNumber <= 8)
            {
                flee = true;
            }
            else
            {
                flee = false;
            }
        }
        
        if(advantageLevel == 0.9)
        {
            randomNumber = rand.nextInt(9) + 1;
            if(randomNumber <= 9)
            {
                flee = true;
            }
            else
            {
                flee = false;
            }
        }
        
        return flee;
    }
}