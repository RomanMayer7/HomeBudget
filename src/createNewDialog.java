import javax.swing.*;
import java.io.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class createNewDialog extends JDialog 
{
    FrameManager fm;
	JDesktopPane desk;
	File check;
	MainFrame newMF;
	createNewDialog cnd;
	createNewDialog(final JFrame parent,String name,final FrameManager fm)
	{
		super(parent,name);
		
		cnd=this;
		this.setSize(600,400);
		this.fm=(FrameManager) fm;
		
		JLabel msg=new JLabel("Enter the year");
		final JTextField yearBox = new JTextField(7);
		JButton create=new JButton("Create and Open");
		JButton close=new JButton("Close");
		
		create.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			  {
			    check=new File("Data/"+fm.userID+"/"+yearBox.getText());
			    
			    if(check.exists()==false)
			    {
				  newMF=new MainFrame(fm.userID,yearBox.getText());
				  newMF.setSize(1024,768);
				  newMF.setVisible(true);
				  //desk = new JDesktopPane();
				  //parent.add(desk);
				  FrameManager.mf.dispose();
				  FrameManager.mf.setVisible(false);
				  FrameManager.mf=newMF;
				  FrameManager.desk.add(newMF);
				  //parent.setSize(1100, 768);
				  //parent.setVisible(true);
			    }
			    else
			     {
			        System.out.println("already exist!");
			        final JFrame error=new JFrame("Warning!");
			        error.setLayout(new FlowLayout());
			        error.add(new JLabel("The year data already exist!"));
			        error.add(new JLabel("Please retype!"));
			        
			        JButton closeerror=new JButton("Close");
			        closeerror.addActionListener(new ActionListener(){
			     	  public void actionPerformed(ActionEvent ae)
			     	    {
			    		   error.dispose();
			    		   error.setVisible(false);
			    	    }});
			        
			         error.add(closeerror);
			         error.setSize(320,200);
			         error.setVisible(true); 
			    
			      }
			    }
		    });
		
		    close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			  {
				cnd.dispose();
				cnd.setVisible(false);
			  }
			});
		
		    this.setLayout(new FlowLayout());
		    add(msg);
		    add(yearBox);
		    add(create);
		    add(close);
	}
	/*public static void changeMainFrame( createNewDialog pointer)
	{
		
		pointer.fm.mf.dispose();
		pointer.fm.mf.setVisible(false);
		pointer.fm.mf=pointer.newMF;
		pointer.fm.desk.add(pointer.newMF);
	*///}
	

}
