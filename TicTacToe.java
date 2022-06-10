import java.util.Scanner;
import java.util.Random;

class TicTacToe
{
   private char [][] board = new char[3][3];

   public Player P1;
   public Player P2;
   TicTacToe()
   {
      P1 = new Human(1);
      P2 = new Human(2);
      for(int i = 0; i < board.length; i++)
      {
       for(int j  = 0; j < board.length; j++)
        board[i][j] = ' ';
      }

   }


   TicTacToe(String args[])
   {

     if(args.length == 0)
     {
       P1 = new Human(1);
       P2 = new Human(2);
     }
     else if(args.length == 1)
     {
       if(args[0].equals("-c"))
       {
         P1 = new DumbAi(1);
         P2 = new DumbAi(2);
       }
     }
     else if(args.length == 2)
     {
      if(args[0].equals("-c") && args[1].equals("1"))
      {
       P1 = new DumbAi(1);
       P2 = new Human(2);
      }
      else if(args[0].equals("-c") && args[1].equals("2"))
      {
       P1 = new Human(1);
       P2 = new DumbAi(2);
      }

     }



      for(int i = 0; i < board.length; i++)
      {
       for(int j = 0; j < board.length; j++)
       {
        board[i][j] = ' ';
       }
      }

   }            //end of constructor


   public void Draw()
   {
    for(int i = 0; i < board.length; i++)
    {
     for(int j = 0; j < board.length; j++)
     {
       System.out.print(" " + board[i][j] + " ");
       if(j < 2)
        System.out.print("|");
     }
      System.out.println();
      if(i < 2)
      System.out.println("-----------");
    }
    System.out.println();
   } //end of draw


  public void Example()
   {
    int c = 0;
    for(int i = 0; i < board.length; i++)
    {
     for(int j = 0; j < board.length; j++)
     {
       c++;
       System.out.print(" " + c + " ");
       if(j < 2)
        System.out.print("|");
     }
      System.out.println();
      if(i < 2)
      System.out.println("-----------");
    }
   } //end of draw


 public void SetSpace(int s,Player p)
 {
   int c = 0;
   if(CheckFilled(s) == false)
    {
    for(int i = 0; i < board.length; i++)
    {
     for(int j = 0; j < board.length; j++)
     {
        c++;
        if(board[i][j] == ' ' && s == c)
         {
          if(p.getOrder() == 1)
          {
           board[i][j] = 'X';
          }
          else
           board[i][j] = 'O';
        }
     }
    }
   }    //end of if CheckedFilled Statement
   else
   {
    System.out.println("Space is filled sorry bucko");
   }

  }     //end of SetSpace

  private boolean CheckFilled(int s)                                    //Converts int to correct board space and
  {
    int c = 0;
    for(int i = 0; i < board.length; i++)
    {
     for(int j = 0; j <  board.length; j++)
     {
      c++;
      if(board[i][j] == ' ' && s == c)
       return false;
     }
    }
     return true;                                                       //If the space is filled return true, if not return false

  }


  public char getChar(int s)
  {
   int c = 0;
   for(int i = 0; i < board.length; i++)
   {
    for(int j = 0; j < board.length; j++)
    {
     c++;
     if(s == c)
      return board[i][j];
    }
   }
   return 'f';
  }



  public void Turn(Player p1, Player p2)
  {
    Scanner s = new Scanner(System.in);
    int p1c,p2c;
    do
    {
     do
     {
      p1c = p1.getInt();
      if(CheckFilled(p1c) == true)
        System.out.println("Sorry Spot: " + p1c + " is taken please choose a new one");

     }while(CheckFilled(p1c) == true);

      SetSpace(p1c,p1);

    }while(CheckFilled(p1c) == false);

     Draw();

   if(this.gameOver() == false)
   {
    do
    {
     do
     {
      p2c = p2.getInt();
      if(CheckFilled(p2c) == true);
        System.out.println("Sorry Spot: " + p2c + " is taken please choose a new one");

     }while(CheckFilled(p2c) == true);
      SetSpace(p2c,p2);
    }while(CheckFilled(p2c) == false);
   }

  }                                                                     //End of turn function


   public boolean gameOver()
   {


        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' ')
            return true;
      else if(board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != ' ')
            return true;
      else if(board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != ' ')
            return true;
      else if(board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != ' ')
            return true;
      else if(board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != ' ')
            return true;
      else if(board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != ' ')
           return true;

     boolean GO = true;
      for(int i = 0; i < board.length; i++)
      {
       for(int j = 0; j < board.length; j++)
       {
         if(board[i][j] == ' ')
            GO = false;
       }
      }
       return GO;
   }





abstract class Player
{
  int lineup;
   Player(int t)
   {
    lineup = t;
   }

   public int getOrder()
   {
    return lineup;
   }

   abstract public int getInt();


}


class Human extends Player
{
  Human(int t)
  {
   super(t);
  }


  public int getInt()
  {
   int p1c;
   Scanner s = new Scanner(System.in);
    do
      {
        System.out.print("Player 1 choose a num 1 - 9: ");
        p1c = s.nextInt();

        if(p1c < 1 || p1c > 9)
          System.out.println("Not a valid entry please try again");

      }while(p1c < 1 || p1c > 9 );
    return p1c;
  }

}

class DumbAi extends Player
{
  DumbAi(int t)
  {
   super(t);
  }

  public int getInt()
  {
   Random r = new Random();
   int rand;
   do
   {
    rand = r.nextInt(9) + 1;
   }while(CheckFilled(rand) == true);
   return rand;
  }
}
/*
class SmartAi extends Player
{
  SmartAi(int t)
  {
   super(t);
  }

 pubic int getInt()
 {
  if(CheckFilled(5) == false)                   //If possible go for the middle
   return 5;
  else if(CheckFilled(5) == true)
  {
   Random r = new Random();
   int rand;
   do
   {
    rand = r.nextInt(3);                        //If middle is not possible go for the corners
    switch(rand)
    {
     case 0: rand = 1;
             break;
     case 1: rand = 3;
             break;
     case 2: rand = 7;
             break;
     case 3: rand = 9;
             break;
    }
   }while(CheckFilled(rand) == false);
   return rand;
  }
 }
}

*/


   public static void main( String args[] )
   {
      TicTacToe g = new TicTacToe(args);
      System.out.println("Example Board: ");
      g.Example();
      System.out.println("The Current Board: ");
      g.Draw();
     do
     {
      g.Turn(g.P1,g.P2);
      g.Draw();
     }while(g.gameOver() == false);
     System.out.println("Game Over!");
     System.out.println();
     g.Draw();
    }
}
