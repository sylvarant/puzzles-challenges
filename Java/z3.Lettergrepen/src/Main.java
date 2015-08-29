import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {

        public static void main(String[] args) throws IOException{
        	
        	
        	//Read undefined number of lines
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(isr);
            
            String line=in.readLine();
           
        	 List<String> parts = new ArrayList<String>();
       
            while(in.ready()){
            	line=in.readLine();
            	parts.add(line);
            }
            System.out.println(parts.size());
            
            
            
            
            
        	
        	
//        		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
//                
//                List<String> parts = new ArrayList<String>();
//                String input = sc.next();
//                while(!input.equals("----------") )
//                {
//                	parts.add(input);
//                	input = sc.next();
//                }
//                
//                List<String> words = new ArrayList<String>();
//              //  try{
//                
//                //input = sc.next();
//                while(sc.hasNext() )
//                {
//                	words.add(sc.next());
//
//                }
//                
//                
//
//	            	 
//				
//                //}
//                //catch (Exception e)
//                //{
//                	
//                //}
//                System.out.println(words.size());
//                for(int i = 0 ; i < words.size(); i ++)
//                {
//                	System.out.println(words.get(i));
//                }
//                
//                
                
                
                
               
        }

}