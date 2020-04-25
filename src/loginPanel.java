import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class loginPanel extends  JInternalFrame
{
	static FrameManager fm;
	loginPanel lp;
	Box VBox;
	Box HBox;
	Box HInnerBox1;
	Box HInnerBox2;
	JPasswordField passwordF;
	JTextField usernameF;
	JLabel passwordL;
	JLabel usernameL;
	Image  titleImage;
	Image  logoImage;
	JLabel message;
	JLabel message2;
	JLabel message3;
	JLabel message4;
	JLabel message5;
	JButton loginB;
    JButton createB;
    JButton demoB;
    JCheckBox remember;
    File userslist;
    ArrayList<String[]> usersCollect;
    boolean notFound;
    boolean checkbox,initcheckbox;
   
    loginPanel(final FrameManager fm)
      {
    	  lp=this;
    	  loginPanel.fm=fm;
    	  usersCollect=new ArrayList<String[]>();
    	  try 
    	    {  //adding program logo image
			   File imageFile = new File("HomeBudget.jpg");
			   File imageFile2=new File("SystemTrayIcon2.gif");
		       System.out.println(imageFile.exists()+" "+imageFile2.exists());
		       titleImage= ImageIO.read( imageFile );
		       logoImage= ImageIO.read( imageFile2 );
		    }
    	  catch(IOException ae){System.out.println("an problem to load the image");}	
		
		  ImageIcon ii=new ImageIcon( titleImage);
		  ImageIcon ii2=new ImageIcon( logoImage);
		  //Creating the Label
		  JLabel jl=new JLabel("Roman Mayerson,2009",ii,JLabel.CENTER);
		  JLabel jl2=new JLabel("Home Budget",ii2,JLabel.CENTER);
    	  setLayout(new FlowLayout());
    	  VBox=Box.createVerticalBox();
    	  VBox.add(jl);
    	  message=new JLabel("Welcome to Home Budget");
    	  VBox.add(message);
    	  message2=new JLabel("You can try to start use program with demo user:");
    	  VBox.add(message2);
    	  demoB= new JButton("Start Demo");
    	  VBox.add(demoB);
    	  message3=new JLabel("to create new user");
    	  VBox.add(message3);
    	  createB=new JButton("Create New User");
    	  VBox.add(createB);
    	  message4=new JLabel("LogIN here!");
    	  VBox.add(message4);
    	  HBox=Box.createHorizontalBox();
    	  Box HInnerBox1=Box.createHorizontalBox();
    	  Box HInnerBox2=Box.createHorizontalBox();
    	  passwordL=new JLabel("Password:");
    	  passwordF=new JPasswordField(8);
    	  HInnerBox1.add(passwordL);
    	  HInnerBox1.add(passwordF);
    	  usernameL=new JLabel("Username:");
    	  usernameF=new JTextField(8);
    	  HInnerBox2.add(usernameL);
    	  HInnerBox2.add(usernameF);
    	  remember=new JCheckBox("Remember password for next Logon");
    	  HBox.add(HInnerBox1);
    	  VBox.add(HInnerBox2);
    	  VBox.add(HBox);
    	  VBox.add(remember);
    	  loginB=new JButton("LogIn");
    	  VBox.add(loginB);
    	  VBox.add(jl2);	
    	  add(VBox);
    	  setSize(1024,768);
    	  setVisible(true);
    	  notFound=true;
    	  checkbox=false;
    	  initcheckbox=false;
    	
    //******************IF checkbox 'remember password' is checked
    	  String checkcode = "false";
    	  File rc=new File("remembercheck.dat");
    	  if (rc.exists()==true)
    	    {
    		  System.out.println("remembercheck.dat exists");//DEBUG
    		  try
    		    {
    			   Scanner in=new Scanner(new FileReader(rc));
    			   System.out.println("in.hasNextLine():"+in.hasNextLine());//DeBUG
    			   while(in.hasNextLine())
    			    { 
    			       checkcode = in.nextLine();
    		           System.out.println("The checkcode is:"+checkcode);//DEBUG
    			    }
                 }
    		  catch(FileNotFoundException e){System.out.println("IO error");}
    	      catch(IOException e){System.out.println("IO error");}
    	    }
    	  
    	  if(checkcode.equals("true"))
    	     {
    		    remember.setSelected(true);
    		    initcheckbox=true;
    	     }
    	
          if (remember.isSelected())
           {
    	      String tokens[] = null;
    	      File remembered=new File("remembered.dat");
    	      try
    	        {
			       Scanner in=new Scanner(new FileReader(remembered));
			       while(in.hasNextLine())
			         { 
			            String line = in.nextLine();
			            tokens=line.split("/");
			         }
                 }
    	      catch(FileNotFoundException e){System.out.println("IO error");}
	          catch(IOException e){System.out.println("IO error");}
              usernameF.setText(tokens[0]);
              passwordF.setText(tokens[1]);
            }

    //******************NOW ADD LISTENERS******************************************************	
    	
 demoB.addActionListener(new ActionListener()
  {
	  @Override
	  public void actionPerformed(ActionEvent arg0)
	    {
		   fm.userID="Demo";
		   fm.startProgram();	
	    }
  });
 
 createB.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent ae)
	   {
	       createUserDiaog cud=new createUserDiaog(lp,"Create New User");
	       cud.setSize(800,600);
	       cud.setVisible(true);
	   }
 });
 
 loginB.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ae)
	   {
		   System.out.println("We have pressed login button");//Debug
		   userslist=new File("userslist.dat");	
		   try
		     {
			    Scanner in=new Scanner(new FileReader(userslist));
			    while(in.hasNextLine())
			     { 
			         String line = in.nextLine();
			         String[] tokens=line.split("/");
			         usersCollect.add(tokens);//populating our data collection,with tokens  from the  file
			      }
			 }
			catch(FileNotFoundException e){System.out.println("IO error");}
			catch(IOException e){System.out.println("IO error");}
		   
			for(int i=0;i<usersCollect.size();i++)
			  {
				  System.out.println(usersCollect.get(i)[0]+","+usernameF.getText()+";"+usersCollect.get(i)[1]+","+passwordF.getPassword().toString());
				  System.out.println(Arrays.equals(usersCollect.get(i)[1].toCharArray(),(passwordF.getPassword())));
			      if(usersCollect.get(i)[0].equals(usernameF.getText())&(Arrays.equals(usersCollect.get(i)[1].toCharArray(),(passwordF.getPassword()))))
			        {
				       System.out.println("login succeed");
				       notFound=false;
				       //*******************if 'remember password' is pressed***************************************************
				       if(checkbox==true)
				         {
					        if((remember.isSelected()==true)&(initcheckbox==false))
					           {
						          System.out.println("((remember.isSelected()==true)&(initcheckbox==false))");//DEBUG
						          File f=new File("remembercheck.dat");	
							try 
							   {
							      f.createNewFile();
								  System.out.println("remembercheck.dat has been recreated");//DEBUG
							   } 
							catch (IOException e) 
							   {
								  // TODO Auto-generated catch block
								  e.printStackTrace();
							   }
						
						    try 
						      {
							     BufferedWriter bw=new BufferedWriter(new FileWriter(f));
							     bw.write("true");
							     bw.close();
							     System.out.println("hopefully the remembercheck has been writed");//DEBUG
						      } 
						    catch (IOException e)
						      {
							    // TODO Auto-generated catch block
							    e.printStackTrace();
						      }
						    //****get user info and write it to the file****
						    File userinfo=new File("remembered.dat");
						    try
						      {
							    userinfo.createNewFile();
						      }
						    catch (IOException e) 
						      {
							     // TODO Auto-generated catch block
							     e.printStackTrace();
						      }		
						    try 
						      {
							    BufferedWriter bw2 = new BufferedWriter(new FileWriter(userinfo));
							    bw2.write(usernameF.getText()+"/"+passwordF.getText());
							    bw2.close();
						      } 
						    catch (IOException e)
						      {
							     // TODO Auto-generated catch block
							        e.printStackTrace();
						      }
							  
					     }
					   else if((remember.isSelected()==false)&(initcheckbox==true))
					      {
						     File f=new File("remembercheck.dat");	
						     try 
						       {
							     f.createNewFile();
						       } 
						     catch (IOException e) 
						     {
							   // TODO Auto-generated catch block
							   e.printStackTrace();
						     }	
					        try 
					         {
						       BufferedWriter bw=new BufferedWriter(new FileWriter(f));
						       bw.write("false");
					         }
					        catch (IOException e) 
					         {
						         // TODO Auto-generated catch block
						          e.printStackTrace();
					         }
					      }	
				}
				else{
					  if (remember.isSelected())
					    {
				    	   String tokens[] = null;
				    	   File remembered=new File("remembered.dat");
				    
				    	try{
							  Scanner in=new Scanner(new FileReader(remembered));
							  while(in.hasNextLine())
							    { 
							      String line = in.nextLine();
							      tokens=line.split("/");
							    }
				           }
				    	catch(FileNotFoundException e){System.out.println("IO error");}
					    catch(IOException e){System.out.println("IO error");}
				        String oldUsername=(tokens[0]);
				        if(oldUsername.equals(usernameF.getText())==false)
				        {
				           //REMEMBERING NEW USER 	
				    	   File userinfo=new File("remembered.dat");
						   try 
						   {
							  userinfo.createNewFile();
						   } catch (IOException e) 
						   {
							 // TODO Auto-generated catch block
							 e.printStackTrace();
						   }
						
						   try 
						   {
							 BufferedWriter bw2 = new BufferedWriter(new FileWriter(userinfo));
							 bw2.write(usernameF.getText()+"/"+passwordF.getText());
							 bw2.close();
						   } 
						   catch (IOException e)
						   {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
						   }	
				        }
					}
				}
				//***********************************************************************
				fm.userID=usernameF.getText();
				fm.startProgram();
			  }
			
			}
			
			if(notFound==true)
			{
				final JFrame error=new JFrame("Error");
				error.setLayout(new FlowLayout());
				error.add(new JLabel("Wrong username or password!"));
				JButton close=new JButton("Close");
				
				close.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae)
					  {
						error.dispose();
						error.setVisible(false);
					  }
				});
				error.add(close);
				error.setSize(640,480);
				error.setVisible(true);
			}		 
	   }
   });
    	
      remember.addItemListener(new ItemListener(){
	  public void itemStateChanged(ItemEvent ae)
	    {
		  checkbox=true;	  
	    }
      });  	
    	
    	
    }
/*public static void main(String[] args){ DEBUGGING PART
	try{
		UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		 } catch (Exception unused) {
		        ; // Ignore exception because we can't do anything.  Will use default.
		    }
	JFrame jf=new JFrame();
	loginPanel lp=new loginPanel(fm);
	jf.setSize(800,600);
	jf.add(lp);
	jf.setVisible(true);
	
*///}
}
