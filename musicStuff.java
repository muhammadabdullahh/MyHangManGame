import javax.swing.JOptionPane;
import java.io.*;
import javax.sound.sampled.*;

public class musicStuff
{
	public static void main(String[] args)
	{
		musicStuff musicObject = new musicStuff();
		musicObject.music("backgroundMusic.wav");
	}
	void music(String musicLocation)
	{
		try
		{
			File musicPath = new File(musicLocation);
			
			if(musicPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				//JOptionPane.showMessageDialog(null, "Press OK to stop playing");
			}
			else
			{
				System.out.println("Cant find file");
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Error Music not found");
		}
				
	}
}
