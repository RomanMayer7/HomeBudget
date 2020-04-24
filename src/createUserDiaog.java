import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class createUserDiaog extends JDialog{
	createUserDiaog CUDpointer;
	File userslist;
	String[]usernames;
	String[][]users;
	ArrayList<String[]> usersCollect;
	JPasswordField newpassF;
	JPasswordField passValidF;
	JTextField newuserF;
	JLabel welcomeMsg;
	JLabel newpassL;
	JLabel passValidL;
	JLabel newuserL;
	JLabel notes1;
	JLabel notes2;
	JLabel notes3;
	JButton createB;
	JButton closeB;
	Box hBox1;
	Box hBox2;
	Box hBox3;
	Box vBox;
	//char[] password; //SECURE VERSION
	//char[] passwordval; //SECURE VERSION
	String password;
	String passwordval;
	 boolean check;
	createUserDiaog(final JInternalFrame parent,String name){
		super(javax.swing.JOptionPane.getFrameForComponent( parent ),name);
		CUDpointer=this;
		 check=true;
		userslist=new File("userslist.dat");
		if (userslist.exists()==false){
			try {
				userslist.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error create file");
				e.printStackTrace();
			}
		}
		usersCollect=new ArrayList<String[]>();
		try{
			Scanner in=new Scanner(new FileReader(userslist));
			while(in.hasNextLine())
			{ 
			String line = in.nextLine();
			String[] tokens=line.split("/");
			//String a=tokens[0];
			//String b=tokens[1];
			//String transfer[]={a,b};
			//MainTableData2[i]=tokens;
			//i++;
			usersCollect.add(tokens);//populating our data colection,with tokens readed from the file
			 }
				}
			catch(FileNotFoundException e){}
			catch(IOException e){}
			
			usernames=new String[usersCollect.size()];
			for(int i=0;i<usersCollect.size();i++){
				usernames[i]=usersCollect.get(i)[0];
			}
	setLayout(new FlowLayout());	
	vBox=Box.createVerticalBox();	
	welcomeMsg=new JLabel("Create New user here");
	vBox.add(welcomeMsg);
	newuserL=new JLabel("Choose your username:");
	newuserF=new JTextField(12);
	notes1=new JLabel("Min:6,Max:12 symbols which can be letters or numbers");
	hBox1=Box.createHorizontalBox();
	hBox1.add(newuserL);
	hBox1.add(newuserF);
	hBox1.add(notes1);
	vBox.add(hBox1);
	newpassL=new JLabel("Choose your password:");
	newpassF=new JPasswordField(12);
	notes2=new JLabel("Min:6,Max:12 symbols which can be letters or numbers");
	hBox2=Box.createHorizontalBox();
	hBox2.add(newpassL);
	hBox2.add(newpassF);
	hBox2.add(notes2);
	vBox.add(hBox2);
	passValidL=new JLabel("Please validate your password:");
	passValidF=new JPasswordField(12);
	hBox3=Box.createHorizontalBox();
	hBox3.add(passValidL);
	hBox3.add(passValidF);
	vBox.add(hBox3);
	createB=new JButton("Create");
	closeB=new JButton("Close");
	vBox.add(createB);
	vBox.add(closeB);
	add(vBox);
	
	//*******************HANDLING EVENTS VALIDATING INPUT DATA HERE*******************************************************
	createB.addActionListener(new ActionListener(){
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent ae){
			if(((newuserF.getText().length())<6)||((newuserF.getText().length())>12)){
				check=false;
		final JFrame error=new JFrame("Error");
		error.setLayout(new FlowLayout());
		error.add(new JLabel("The number of symbols must be from 6 to 12!"));
		JButton close=new JButton("Close");
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				error.dispose();
				error.setVisible(false);
			}
		});
		
		error.add(close);
		error.setSize(640,480);
		error.setVisible(true);		
	}
		if(check==true){
			for (int i=0;i<usernames.length;i++){
				System.out.println(usernames[i]+","+newuserF.getText());
			if (usernames[i].equals(newuserF.getText())){
				check=false;
				final JFrame error=new JFrame("Warning");
				error.setLayout(new FlowLayout());
				error.add(new JLabel("The username is already exist,choose different"));
				error.setSize(640,480);
				JButton close=new JButton("Close");
				close.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						error.dispose();
						error.setVisible(false);
					}
				});
				error.add(close);
				error.setVisible(true);	
				break;
				
			}
			}
			//********************************************
			if(check==true){	
				 //password=newpassF.getPassword(); //SECURE VERSION
				 //passwordval=passValidF.getPassword(); //SECURE VERSION
				 password=newpassF.getText();
				 passwordval=passValidF.getText();
				//  if(password.length!=passwordval.length){ //SECURE VERSION
			    if(password.length()!=passwordval.length()){
			    	JFrame error=new JFrame("Error");
			    	error.setLayout(new FlowLayout());
			    	check=false;
					error.add(new JLabel("Password validation fails,please retype"));
					error.setSize(640,480);
					error.setVisible(true);	
			    }
			    else if((password.equals(passwordval))==false){
			   // else if((Arrays.equals(password, passwordval))==false){ //SECURE VERSION
			    	final JFrame error=new JFrame("Error");
			    	error.setLayout(new FlowLayout());
			    	check=false;
					error.add(new JLabel("Password validation fails,please retype"));
					JButton close=new JButton("Close");
					close.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent ae){
							error.dispose();
							error.setVisible(false);
						}
					});
					error.add(close);
					error.setSize(640,480);
					error.setVisible(true);	
					
			    }
			    
			//********************************************************************
			    if(check==true){
				    String[] usersucceed={newuserF.getText(),password.toString()};
				    usersCollect.add(usersucceed);
				    //*******************************************************************
				    users=new String[usersCollect.size()][2];
				 for(int i=0;i<usersCollect.size();i++){
					 users[i]=usersCollect.get(i);
				 }
				    try {
						BufferedWriter bw=new BufferedWriter(new FileWriter(userslist));
						for(int j=0;j<users.length;j++){
							System.out.println(users[j][0]+"/"+users[j][1]);
							//bw.write("writing test");
							bw.write(users[j][0]+"/"+users[j][1]);
							bw.newLine();
							}
							bw.close();
							System.out.println("Your file has been written");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//updating usernames in our currient object in order we will be able to create more users
					usernames=new String[usersCollect.size()];
					for(int i=0;i<usersCollect.size();i++){
						usernames[i]=usersCollect.get(i)[0];
					} 
					
					final JFrame success=new JFrame("Success");
					success.setLayout(new FlowLayout());
					success.add(new JLabel("The new user '"+newuserF.getText()+"' has been created"));
					JButton close=new JButton("Close");
					close.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent ae){
							success.dispose();
							success.setVisible(false);
						}
					});
					success.add(close);
					success.setSize(640,480);
					success.setVisible(true);
					CUDpointer.dispose();
					CUDpointer.setVisible(false);
				}
			    //******end IF 3
			}
			//***********end IF 2
		}	
		//***************end IF 1
		
		
		}
		//*************end of ActionPerformed()
	});
	closeB.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			CUDpointer.dispose();
			CUDpointer.setVisible(false);
		}
	});
	
	}

}
