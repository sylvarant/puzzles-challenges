import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			int numberRows = sc.nextInt();
			int numberColumns = sc.nextInt();
			
			char[][] input = new char[numberRows ][ numberColumns];
			
			for(int column = 0 ; column < numberColumns ; column++)
			{
				String line = sc.next();
				for(int row = 0 ; row < numberRows; row ++)
				{
					
					input[row][column] = line.charAt(row);
					
				}
			}
			
			printMatrix(input, numberRows, numberColumns);
			
			walk(0,0 , numberRows,numberColumns );
			
		//	Vector test = new Vector<Integer>();
		//	for(int column = 0 ; column < numberColumns ; column++)
		//	{
				
		//		for(int row = 0 ; row < numberRows; row ++)
		//		{
					
		//		}
		//	}
		}
		
		
		
	}
	
	

	static List<Integer>  pathx = new ArrayList<Integer>();
	static List<Integer>  pathy = new ArrayList<Integer>();

	
	private static void printPathOrder(int numberRows,int numberColumns){
		
		for(int column = 0 ; column < numberColumns ; column++)
		{
					
			for(int row = 0 ; row < numberRows; row ++)
			{
				
				int number = findTileNumber(row, column);
				System.out.print( number + " ");
				
				
			}
			System.out.println();
		}
		
	}
	
	private static int findTileNumber(int row, int column) {
		for(int i = 0; i < pathx.size();i++)
		{
			if(pathx.get(i) == row  &&  pathy.get(i)== column)
				return i;
		}
		return -1;
	}

	private static void walk(int x, int y,int numberRows,int numberColums){

		 //append path
		pathx.add(x);
		pathy.add(y);
		
		if ( pathx.size() == 9 ) //HARDCODED length
		{
			//path found
			//check validity of path
			//print path  
			System.out.println("Path found");
			//for(int i = 0; i < pathx.size();i++)
			//{
			//	System.out.println(pathx.get(i) + " " + pathy.get(i));
			//}
			//System.out.println(" ");
			
			printPathOrder(numberRows, numberColums);
			
			 //Remove path
		    pathx.remove(pathx.size()-1);
		    pathy.remove(pathy.size()-1);
			return;
		}
		
		//path is not yet full size, add a new tile if possible, else return 
		
		//check north 
		if( x > 0 && unvisitedTile(x -1,y))
		{
			//north is possible
			walk(x-1,  y, numberRows, numberColums);
		}
		 
		//check east
		if( y +1 < numberColums && unvisitedTile(x,y+1))
		{
			//east is possible
			walk(x,  y+1, numberRows, numberColums);
		}
			
		//check south
		if( x +1 <  numberRows && unvisitedTile(x + 1,y))
		{
			//south is possible
			walk(x +1,  y, numberRows, numberColums);
		}
		
		//check west 
		if( y > 0 && unvisitedTile(x ,y - 1))
		{
			//west is possible
			walk(x,  y - 1 , numberRows, numberColums);
		}
			
		    //path.remove(move)
		    pathx.remove(pathx.size()-1);
		    pathy.remove(pathy.size()-1);
		
		
	}
	
	
	private static boolean unvisitedTile(int x, int y) 
	{
		for(int i = 0; i < pathx.size();i++)
		{
			if(pathx.get(i) == x  &&  pathy.get(i)== y)
				return false;
		}
		return true;
	}


	//create empty path
	
	//add first element to path
	
	//start in certain default direction e.g to the north
	// check bounds 
	// and check whether element is nog parth of path
	//if not add new tile to path else next
	
	//next: check east, south , west else backtrack
	
	//path is found if size == numberRows* numberColumns
	
	
	
	public int splitIndex(String expr)
	{
		//eerst = 
		//dan *en /
		// en dan + en  -  
		// else -1
		return 0;
	}
	
	
	public static void printMatrix(char[][] input,int maxRows, int maxColumns )
	{
		for(int column = 0 ; column < maxColumns ; column++)
		{
			for(int row = 0 ; row < maxRows; row ++)
			{
				
				System.out.print(input[row][column] + " ");
				
			}
			System.out.println();
		}
	}

}
