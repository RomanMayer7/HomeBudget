import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
public class MainFrame extends JInternalFrame
{
	String[] month={"January","February","March","April","May","June","July","August",
   "September","October","November","December"};
	
	File[] mainFile=new File[12];
	File[] purchFile=new File[12];
	monthPanel monthpanels[];
	public String yearid;
	public String userID;
	MainFrame mf;//reference to this object
	MainFrame(String user,String year)
	{	
	   mf=this;
	   userID=user;
	   yearid=year;
	   monthpanels=new monthPanel[12];
	   JTabbedPane yearPane=new JTabbedPane();
	   File datadir=new File("Data");
	   if(datadir.exists()==false)
	     {
	        datadir.mkdir();
	     }
	   for(int i=0;i<12;i++){
		   monthpanels[i]=new monthPanel(month[i],this,this);
		   yearPane.addTab(month[i],monthpanels[i]);
		   //System.out.println(yearid+"/MainData/"+month[i]+".txt"); //debugging
		   try {
			     mainFile[i]=new File("Data/"+userID+"/"+yearid+"/MainData/"+month[i]+".txt");
		         if (mainFile[i].exists()!=true)
		           {
                       File dir11=new File("Data/"+userID);
	                   if(dir11.exists()==false){dir11.mkdir();
	               }
	             File dir12=new File("Data/"+userID+"/"+yearid);
	             
	             if(dir12.exists()==false){dir12.mkdir();}
	             
	             File dir13=new File("Data/"+userID+"/"+yearid+"/MainData/");
	             if(dir13.exists()==false){dir13.mkdir();}
	             mainFile[i].createNewFile();}	
	           }
		   catch(IOException e)
		         {
		           System.out.println("Error create file");
		           e.printStackTrace();
		         }
	      //repeating all the procedure for the "Purchases" table
	      try{
	    	  purchFile[i]=new File("Data/"+userID+"/"+yearid+"/PurchasesData/"+month[i]+"_p"+".txt");
    	      if (purchFile[i].exists()!=true)
    	      {
		        File dir21=new File("Data/"+userID);
	        	if(dir21.exists()==false){dir21.mkdir();}
		        File dir22=new File("Data/"+userID+"/"+yearid);
		        
		        if(dir22.exists()==false){dir22.mkdir();}
		        File dir23=new File("Data/"+userID+"/"+yearid+"/PurchasesData/");
		        if(dir23.exists()==false){dir23.mkdir();}
                purchFile[i].createNewFile();}	
              }
	      catch(IOException e)
	      {
	            System.out.println("Error create file");
	            e.printStackTrace();
	      }
	
	    }
	    add(yearPane);
	
	}

/*public static void main(String[] args){ //debugging part
	try{
		UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		 } catch (Exception unused) {
		        ; // Ignore exception because we can't do anything.  Will use default.
		    }
MainFrame mf=new MainFrame();
mf.setSize(1024, 768);
mf.setVisible(true);

}*/

}