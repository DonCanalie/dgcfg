package main;
public class HelloWorld 
{
 
       public static void main (String[] args)
       {
             guis.Main main = new guis.Main();
             
             try {
				main.open();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}       
       }
}