package MyShell;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyShell  
{
	public static void main(String[] args) throws IOException 
	{	
		// commaInput will receive the user input.
		BufferedReader commandInput = new BufferedReader(new InputStreamReader(System.in));
		boolean userInput= true;
		String command = null;

		// This is the Runtime and Process API's 
		Runtime runtime = Runtime.getRuntime();
		Process run;

		// looping the user input, it will keep asking the user to enter a command.
		while(userInput)
		{
			try {
				// where user enter the command.
				command = commandInput.readLine();
				System.out.println("prompt>");
				
				if(command.equals("exit"))
				{
					System.out.println("Exiting...");
					System.exit(0);
				}  				
				
				// the user command will be execute it with the runtime.exec() method. 
				run = runtime.exec(command);

				// The input need to be out to allow the user to see the result of the commands,
				// we need to loop through the out to be able to read all the out.
				BufferedReader readProcess = new BufferedReader(new InputStreamReader(run.getInputStream()));
				String string= null;
				while((string=readProcess.readLine()) != null)
				{  
					System.out.println(string);
				}
				
				try {
					run.waitFor();
				} catch (InterruptedException e) {						
					e.printStackTrace();
				}
				
				if(command.equals("exit"))
				{
					System.out.println("Exiting...");
					System.exit(0);
				}
				// it will catch the error and let the user know that the command enter it its no a command
			} catch (IOException e) {
				System.out.println("Error executing: " + command.trim());
			}
		}
		commandInput.close();
	}
}
