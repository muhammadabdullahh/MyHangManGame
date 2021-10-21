/** This is a two player game, called Hangman 
 * In this game one of the players will
 * chose a word for the second player to guess
 * the second player has 8 lives, if all lives are lost they lose
 * if they guess the word, they win
 * @author Muhammad Abdullah
 * January 20th 2020
 * */
 
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

class HangmanGame extends JFrame
{
	//Panels
	private JPanel basePanel;
	private StartPanel startPanel;
	private GamePanel gamePanel;
	private Game2Panel game2Panel;
	private GameOverPanel gameOverPanel;
	private WinPanel winPanel;
	
	//Other Variables
	private String wordStored;
	private int livesLost = 1;
	private int correctScore = 0;
	private ImageIcon hang;
	
	//GAME PANEL 2 Variables
	private JButton letterA;
	private JButton letterB;
	private JButton letterC;
	private JButton letterD;
	private JButton letterE;
	private JButton letterF;
	private JButton letterG;
	private JButton letterH;
	private JButton letterI;
	private JButton letterJ;
	private JButton letterK;
	private JButton letterL;
	private JButton letterM;
	private JButton letterN;
	private JButton letterO;
	private JButton letterP;
	private JButton letterQ;
	private JButton letterR;
	private JButton letterS;
	private JButton letterT;
	private JButton letterU;
	private JButton letterV;
	private JButton letterW;
	private JButton letterX;
	private JButton letterY;
	private JButton letterZ;
	private JButton letter0;
	
	private JButton[] location = new JButton[10]; 
	private String hangStages = "Hang1.jpg";
	private char letter;
	private String letterName;
	
	//MAIN METHOD
	public static void main(String[] args)
	{
		musicStuff musicObject = new musicStuff();
		musicObject.music("backgroundMusic.wav");		//calls a Music class to play the music
		HangmanGame hmg = new HangmanGame();
	}
	
	public HangmanGame()
	{
		//creata a base panel to display others in and set it to CardLayout
		basePanel = new JPanel();
		basePanel.setLayout(new CardLayout());
		
		//create the panels (see classes below)
		startPanel = new StartPanel();
		gamePanel = new GamePanel();
		game2Panel = new Game2Panel();
		gameOverPanel = new GameOverPanel();
		winPanel = new WinPanel();
		
		//add all panels used
		basePanel.add(startPanel,"INTRO");
		basePanel.add(gamePanel,"GAME");
		basePanel.add(game2Panel, "SCREENONE");
		basePanel.add(gameOverPanel, "GAMEOVER");
		basePanel.add(winPanel, "WINPANEL");
		
		//add the base panel to the frame and display
		add(basePanel);
		setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	
	//the start game panel
	class StartPanel extends JPanel implements ActionListener
	{
		private JButton startButton;
		private JLabel background;
		
		protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);								//clear viewing area before drawing
				ImageIcon i = new ImageIcon("SPbackground.jpg");
				i.paintIcon(this, g, 0, 0);
			}
		
		public StartPanel()
		{
			setLayout(null);
			setBackground(Color.RED);
			setPreferredSize(new Dimension(800,400));
			setResizable(false);
			
			//add button to panel 
			startButton = new JButton("press to play");
			startButton.setBounds(300,260,195,50);
			startButton.setIcon(new ImageIcon("playButton.jpg"));
			startButton.addActionListener(this);						//register button to listen
			add(startButton);	
		}
		
		public void actionPerformed(ActionEvent e)
		{
			//if start button is pressed switch to "GAME" panel
			if(e.getSource() == startButton)
			{
				musicStuff clickSound = new musicStuff();
				clickSound.music("clickSound.wav");
				((CardLayout)basePanel.getLayout()).show(basePanel,"GAME");
			}
		}
	}
	
	//the game panel
	class GamePanel extends JPanel implements ActionListener
	{
		private JTextField guessWord;
		private JButton enter;
		
		protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);							
				ImageIcon i = new ImageIcon("GPbackground.jpg");
				i.paintIcon(this, g, 0, 0);
			}
		
		public GamePanel()
		{
			setLayout(null);
			setBackground(Color.RED);
			setPreferredSize(new Dimension(800,400));
			setResizable(false);
			
			//add textfield
			guessWord = new JTextField(15);
			guessWord.setDocument(new JTextFieldLimit(10));
			guessWord.setBounds(300,200,195,30);
			add(guessWord);
			
			//add button
			enter = new JButton("press to play");
			enter.setBounds(300,260,195,50);
			enter.setIcon(new ImageIcon("enterButton.jpg"));
			enter.addActionListener(this);
			add(enter);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == enter)
			{
				wordStored = guessWord.getText().toUpperCase();
				musicStuff clickSound = new musicStuff();
				clickSound.music("clickSound.wav");
				((CardLayout)basePanel.getLayout()).show(basePanel,"SCREENONE");
			}
		}
	}
	
//********************************ACTUAL GAME SCREENS*******************************	
	//Screen one panel
	class Game2Panel extends JPanel implements ActionListener
	{
		protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);	//clear viewing area before drawing
				hang = new ImageIcon(hangStages);
				hang.paintIcon(this, g, 0, 0);
				for(int j = 0; j < wordStored.length(); j ++)
				{
					add(location[j]);
				}
			}
		
		public Game2Panel()
		{
			setLayout(null);
			setBackground(Color.RED);
			setPreferredSize(new Dimension(800,400));
			setResizable(false);
			
			//All answered letter location
			location[0] = new JButton();
			location[0].setBounds(40,350,35,35);
			location[0].setIcon(new ImageIcon("usedLetter.jpg"));
			
			location[1] = new JButton();
			location[1].setBounds(80,350,35,35);
			location[1].setIcon(new ImageIcon("usedLetter.jpg"));
			
			location[2] = new JButton();
			location[2].setBounds(120,350,35,35);
			location[2].setIcon(new ImageIcon("usedLetter.jpg"));
			
			location[3] = new JButton();
			location[3].setBounds(160,350,35,35);
			location[3].setIcon(new ImageIcon("usedLetter.jpg"));
			
			location[4] = new JButton();
			location[4].setBounds(200,350,35,35);
			location[4].setIcon(new ImageIcon("usedLetter.jpg"));
			
			location[5] = new JButton();
			location[5].setBounds(240,350,35,35);
			location[5].setIcon(new ImageIcon("usedLetter.jpg"));
			
			location[6] = new JButton();
			location[6].setBounds(280,350,35,35);
			location[6].setIcon(new ImageIcon("usedLetter.jpg"));
			
			location[7] = new JButton();
			location[7].setBounds(320,350,35,35);
			location[7].setIcon(new ImageIcon("usedLetter.jpg"));
			
			location[8] = new JButton();
			location[8].setBounds(360,350,35,35);
			location[8].setIcon(new ImageIcon("usedLetter.jpg"));
			
			location[9] = new JButton();
			location[9].setBounds(400,350,35,35);
			location[9].setIcon(new ImageIcon("usedLetter.jpg"));
			
			
			
			//ALL LETTERS
			//layer 1
			letterA = new JButton();
			letterA.setBounds(500,10,35,35);
			letterA.setIcon(new ImageIcon("letterA.jpg"));
			letterA.setName("letterA");
			letterA.addActionListener(this);
			add(letterA);
			
			letterB = new JButton();
			letterB.setBounds(560,10,35,35);
			letterB.setIcon(new ImageIcon("letterB.jpg"));
			letterB.setName("letterB");
			letterB.addActionListener(this);
			add(letterB);
			
			letterC = new JButton();
			letterC.setBounds(620,10,35,35);
			letterC.setIcon(new ImageIcon("letterC.jpg"));
			letterC.setName("letterC");
			letterC.addActionListener(this);
			add(letterC);
			
			letterD = new JButton();
			letterD.setBounds(680,10,35,35);
			letterD.setIcon(new ImageIcon("letterD.jpg"));
			letterD.setName("letterD");
			letterD.addActionListener(this);
			add(letterD);
			
			letterE = new JButton();
			letterE.setBounds(740,10,35,35);
			letterE.setIcon(new ImageIcon("letterE.jpg"));
			letterE.setName("letterE");
			letterE.addActionListener(this);
			add(letterE);
			
			//layer 2
			letterF = new JButton();
			letterF.setBounds(500,65,35,35);
			letterF.setIcon(new ImageIcon("letterF.jpg"));
			letterF.setName("letterF");
			letterF.addActionListener(this);
			add(letterF);
			
			letterG = new JButton();
			letterG.setBounds(560,65,35,35);
			letterG.setIcon(new ImageIcon("letterG.jpg"));
			letterG.setName("letterG");
			letterG.addActionListener(this);
			add(letterG);
			
			letterH = new JButton();
			letterH.setBounds(620,65,35,35);
			letterH.setIcon(new ImageIcon("letterH.jpg"));
			letterH.setName("letterH");
			letterH.addActionListener(this);
			add(letterH);
			
			letterI = new JButton();
			letterI.setBounds(680,65,35,35);
			letterI.setIcon(new ImageIcon("letterI.jpg"));
			letterI.setName("letterI");
			letterI.addActionListener(this);
			add(letterI);
			
			letterJ = new JButton();
			letterJ.setBounds(740,65,35,35);
			letterJ.setIcon(new ImageIcon("letterJ.jpg"));
			letterJ.setName("letterJ");
			letterJ.addActionListener(this);
			add(letterJ);
			
			//layer 3
			letterK = new JButton();
			letterK.setBounds(500,120,35,35);
			letterK.setIcon(new ImageIcon("letterK.jpg"));
			letterK.setName("letterK");
			letterK.addActionListener(this);
			add(letterK);
			
			letterL = new JButton();
			letterL.setBounds(560,120,35,35);
			letterL.setIcon(new ImageIcon("letterL.jpg"));
			letterL.setName("letterL");
			letterL.addActionListener(this);
			add(letterL);
			
			letterM = new JButton();
			letterM.setBounds(620,120,35,35);
			letterM.setIcon(new ImageIcon("letterM.jpg"));
			letterM.setName("letterM");
			letterM.addActionListener(this);
			add(letterM);
			
			letterN = new JButton();
			letterN.setBounds(680,120,35,35);
			letterN.setIcon(new ImageIcon("letterN.jpg"));
			letterN.setName("letterN");
			letterN.addActionListener(this);
			add(letterN);
			
			letterO = new JButton();
			letterO.setBounds(740,120,35,35);
			letterO.setIcon(new ImageIcon("letterO.jpg"));
			letterO.setName("letterO");
			letterO.addActionListener(this);
			add(letterO);
			
			//layer 4
			letterP = new JButton();
			letterP.setBounds(500,175,35,35);
			letterP.setIcon(new ImageIcon("letterP.jpg"));
			letterP.setName("letterP");
			letterP.addActionListener(this);
			add(letterP);
			
			letterQ = new JButton();
			letterQ.setBounds(560,175,35,35);
			letterQ.setIcon(new ImageIcon("letterQ.jpg"));
			letterQ.setName("letterQ");
			letterQ.addActionListener(this);
			add(letterQ);
			
			letterR = new JButton();
			letterR.setBounds(620,175,35,35);
			letterR.setIcon(new ImageIcon("letterR.jpg"));
			letterR.setName("letterR");
			letterR.addActionListener(this);
			add(letterR);
			
			letterS = new JButton();
			letterS.setBounds(680,175,35,35);
			letterS.setIcon(new ImageIcon("letterS.jpg"));
			letterS.setName("letterS");
			letterS.addActionListener(this);
			add(letterS);
			
			letterT = new JButton();
			letterT.setBounds(740,175,35,35);
			letterT.setIcon(new ImageIcon("letterT.jpg"));
			letterT.setName("letterT");
			letterT.addActionListener(this);
			add(letterT);
			
			//layer 5
			letterU = new JButton();
			letterU.setBounds(500,230,35,35);
			letterU.setIcon(new ImageIcon("letterU.jpg"));
			letterU.setName("letterU");
			letterU.addActionListener(this);
			add(letterU);
			
			letterV = new JButton();
			letterV.setBounds(560,230,35,35);
			letterV.setIcon(new ImageIcon("letterV.jpg"));
			letterV.setName("letterV");
			letterV.addActionListener(this);
			add(letterV);
			
			letterW = new JButton();
			letterW.setBounds(620,230,35,35);
			letterW.setIcon(new ImageIcon("letterW.jpg"));
			letterW.setName("letterW");
			letterW.addActionListener(this);
			add(letterW);
			
			letterX = new JButton();
			letterX.setBounds(680,230,35,35);
			letterX.setIcon(new ImageIcon("letterX.jpg"));
			letterX.setName("letterX");
			letterX.addActionListener(this);
			add(letterX);
			
			letterY = new JButton();
			letterY.setBounds(740,230,35,35);
			letterY.setIcon(new ImageIcon("letterY.jpg"));
			letterY.setName("letterY");
			letterY.addActionListener(this);
			add(letterY);
			
			//layer 6
			letterZ = new JButton();
			letterZ.setBounds(670,285,35,35);
			letterZ.setIcon(new ImageIcon("letterZ.jpg"));
			letterZ.setName("letterZ");
			letterZ.addActionListener(this);
			add(letterZ);
			
			letter0 = new JButton();
			letter0.setBounds(580,285,70,35);
			letter0.setIcon(new ImageIcon("letter0.jpg"));
			letter0.setName("letter ");
			letter0.addActionListener(this);
			add(letter0);
		}
		
		public void actionPerformed(ActionEvent e)
		{	
			letterName = (((JComponent) e.getSource()).getName() + "");
			letter = letterName.charAt(6);
			((JComponent)e.getSource()).setVisible(false);				//sets the letter chosen to invisible
			
			//FINDING IF LETTER CHOSEN IS CORRECT
			int posOfLetter = wordStored.indexOf(letter);
			int posOfLetter2 = wordStored.indexOf(letter, posOfLetter +1);
			int posOfLetter3 = wordStored.indexOf(letter, posOfLetter2 +1);
			int posOfLetter4 = wordStored.indexOf(letter, posOfLetter3 +1);
			int posOfLetter5 = wordStored.indexOf(letter, posOfLetter4 +1);
			int posOfLetter6 = wordStored.indexOf(letter, posOfLetter5 +1);
			int posOfLetter7 = wordStored.indexOf(letter, posOfLetter6 +1);
			int posOfLetter8 = wordStored.indexOf(letter, posOfLetter7 +1);
			int posOfLetter9 = wordStored.indexOf(letter, posOfLetter8 +1);
			int posOfLetter10 = wordStored.indexOf(letter, posOfLetter9 +1);
			int posOfLetter11 = wordStored.indexOf(letter, posOfLetter10 +1);
			int posOfLetter12 = wordStored.indexOf(letter, posOfLetter11 +1);
			int posOfLetter13 = wordStored.indexOf(letter, posOfLetter12 +1);
			int posOfLetter14 = wordStored.indexOf(letter, posOfLetter13 +1);
			int posOfLetter15 = wordStored.indexOf(letter, posOfLetter14 +1);
			if(posOfLetter >= 0)
			{
				musicStuff musicObject2 = new musicStuff();
				musicObject2.music("correctSound.wav");
				correctScore++;
				location[posOfLetter].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter2 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter2].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter3 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter3].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter4 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter4].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter5 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter5].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter6 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter6].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter7 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter6].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter8 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter6].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter9 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter6].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter10 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter6].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter11 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter6].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter12 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter6].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter13 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter6].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter14 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter6].setIcon(new ImageIcon(letterName + ".jpg"));
				if(posOfLetter15 >= 0)
					correctScore++;
					if(correctScore == wordStored.length())
					{
						((CardLayout)basePanel.getLayout()).show(basePanel,"WINPANEL");
						musicStuff musicObject = new musicStuff();
						musicObject.music("youWinSound.wav");
					}
					location[posOfLetter6].setIcon(new ImageIcon(letterName + ".jpg"));
			}
			else
			{
				musicStuff musicObject = new musicStuff();
				musicObject.music("oofSound.wav");
				livesLost = livesLost + 1;
				hangStages = "Hang"+ livesLost +".jpg";
				repaint();	
				if(livesLost == 8)
				{
					((CardLayout)basePanel.getLayout()).show(basePanel,"GAMEOVER");
					musicStuff musicObject2 = new musicStuff();
					musicObject2.music("gameOverSound.wav");
				}
							
			}
			
		}
	}
	
	//GAMEOVER
	class GameOverPanel extends JPanel implements ActionListener
	{
		private JButton playAgain;
		
		protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);	//clear viewing area before drawing
				ImageIcon i = new ImageIcon("gameOver.jpg");
				i.paintIcon(this, g, 0, 0);
			}
		
		public GameOverPanel()
		{
			setLayout(null);
			setBackground(Color.RED);
			setPreferredSize(new Dimension(800,400));
			setResizable(false);
			
			playAgain = new JButton();
			playAgain.setBounds(50,340,195,50);
			playAgain.setIcon(new ImageIcon("playAgain.jpg"));
			playAgain.addActionListener(this);
			add(playAgain);
		}
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == playAgain)
			{
				resetValues();
				musicStuff clickSound = new musicStuff();
				clickSound.music("clickSound.wav");
				((CardLayout)basePanel.getLayout()).show(basePanel,"GAME");
				
			}
		}
		public void resetValues()
		{
			wordStored = "";
			livesLost = 1;
			correctScore = 0;
			hangStages = "Hang1.jpg";
			
			//All answered letter location
			location[0].setIcon(new ImageIcon("usedLetter.jpg"));			
			location[1].setIcon(new ImageIcon("usedLetter.jpg"));			
			location[2].setIcon(new ImageIcon("usedLetter.jpg"));			
			location[3].setIcon(new ImageIcon("usedLetter.jpg"));			
			location[4].setIcon(new ImageIcon("usedLetter.jpg"));			
			location[5].setIcon(new ImageIcon("usedLetter.jpg"));
			location[6].setIcon(new ImageIcon("usedLetter.jpg"));
			location[7].setIcon(new ImageIcon("usedLetter.jpg"));
			location[8].setIcon(new ImageIcon("usedLetter.jpg"));
			location[9].setIcon(new ImageIcon("usedLetter.jpg"));
			
			letterA.setVisible(true);
			letterB.setVisible(true);
			letterC.setVisible(true);
			letterD.setVisible(true);
			letterE.setVisible(true);
			letterF.setVisible(true);
			letterG.setVisible(true);
			letterH.setVisible(true);
			letterI.setVisible(true);
			letterJ.setVisible(true);
			letterK.setVisible(true);
			letterL.setVisible(true);
			letterM.setVisible(true);
			letterN.setVisible(true);
			letterO.setVisible(true);
			letterP.setVisible(true);
			letterQ.setVisible(true);
			letterR.setVisible(true);
			letterS.setVisible(true);
			letterT.setVisible(true);
			letterU.setVisible(true);
			letterV.setVisible(true);
			letterW.setVisible(true);
			letterX.setVisible(true);
			letterY.setVisible(true);
			letterZ.setVisible(true);
		}
	}
	
	
	
	class WinPanel extends JPanel implements ActionListener
	{
		private JButton playAgain;
		
		protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);	//clear viewing area before drawing
				ImageIcon i = new ImageIcon("winScreen.jpg");
				i.paintIcon(this, g, 0, 0);
			}
		
		public WinPanel()
		{
			setLayout(null);
			setBackground(Color.RED);
			setPreferredSize(new Dimension(800,400));
			setResizable(false);
			
			playAgain = new JButton();
			playAgain.setBounds(500,290,195,50);
			playAgain.setIcon(new ImageIcon("playAgain.jpg"));
			playAgain.addActionListener(this);
			add(playAgain);
		}
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == playAgain)
			{
				resetValues();
				musicStuff clickSound = new musicStuff();
				clickSound.music("clickSound.wav");
				((CardLayout)basePanel.getLayout()).show(basePanel,"GAME");
				
			}
		}
		public void resetValues()
		{
			wordStored = "";
			livesLost = 1;
			correctScore = 0;
			hangStages = "Hang1.jpg";
			
			//All answered letter location
			location[0].setIcon(new ImageIcon("usedLetter.jpg"));			
			location[1].setIcon(new ImageIcon("usedLetter.jpg"));			
			location[2].setIcon(new ImageIcon("usedLetter.jpg"));			
			location[3].setIcon(new ImageIcon("usedLetter.jpg"));			
			location[4].setIcon(new ImageIcon("usedLetter.jpg"));			
			location[5].setIcon(new ImageIcon("usedLetter.jpg"));
			location[6].setIcon(new ImageIcon("usedLetter.jpg"));
			location[7].setIcon(new ImageIcon("usedLetter.jpg"));
			location[8].setIcon(new ImageIcon("usedLetter.jpg"));
			location[9].setIcon(new ImageIcon("usedLetter.jpg"));
			
			//sets all letters to visible
			letterA.setVisible(true);
			letterB.setVisible(true);
			letterC.setVisible(true);
			letterD.setVisible(true);
			letterE.setVisible(true);
			letterF.setVisible(true);
			letterG.setVisible(true);
			letterH.setVisible(true);
			letterI.setVisible(true);
			letterJ.setVisible(true);
			letterK.setVisible(true);
			letterL.setVisible(true);
			letterM.setVisible(true);
			letterN.setVisible(true);
			letterO.setVisible(true);
			letterP.setVisible(true);
			letterQ.setVisible(true);
			letterR.setVisible(true);
			letterS.setVisible(true);
			letterT.setVisible(true);
			letterU.setVisible(true);
			letterV.setVisible(true);
			letterW.setVisible(true);
			letterX.setVisible(true);
			letterY.setVisible(true);
			letterZ.setVisible(true);
			letter0.setVisible(true);
		}
	}
	
}
