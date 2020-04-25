import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.regex.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.event.*;


public class FrameManager extends JFrame{
    static SystemTrayManager std;
    static FrameManager fmPointer,fm;
    String userID;
	JMenuBar bar;
	JMenu year;
	JMenuItem about;
	JMenu data;
	JMenu program;
	final JFileChooser chooser;
	static loginPanel lp;
	JMenuItem createNew;
	JMenuItem openExisted;
	JMenuItem exit;
	JMenuItem importd;
	JMenuItem exportd;
	JMenuItem logout;
	static Date date;
	static DateFormat dateformat;
	String current_date;
	String current_year;
	String dateparse[];
	static MainFrame mf;
	public static JDesktopPane desk;
	int currentFrame;
	BufferedImage image;
	
	FrameManager(){
		
	   super("Home Budget");
	   
	   fmPointer=this;
	   fm=this;
	   //Create a file chooser
	   chooser = new JFileChooser();
	   bar=new JMenuBar();
	   year=new JMenu("Year");
	   createNew=new JMenuItem("Create New Year Data");
	 
	   createNew.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent ae){
			 createNewDialog newDialog=new createNewDialog(fmPointer,"Create New Year Data",fmPointer);
			 newDialog.setVisible(true);
		  }
	   });
	 
	   openExisted=new JMenuItem("Open Existed Data(Year's List)");
	
	   openExisted.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent ae){
			 yearListDialog openDialog =new yearListDialog(fmPointer,"Year's List",userID);
			 openDialog.setVisible(true);	
		  }
	   });
	
	   program=new JMenu("Program");
	   logout=new JMenuItem("Logout or Switch User");
	   logout.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent ae){
			  fm.remove(FrameManager.desk);
			  desk = new JDesktopPane();//reconstructing desk(JDesktopPane)
		      lp=new loginPanel(fmPointer);
			  fm.add(desk);
		      FrameManager.desk.add(lp);//adding loginPanel to the desk
		   }
	  	});
	   
	   exit=new JMenuItem("Quit Program");
	   exit.addActionListener(new ActionListener(){
	    	public void actionPerformed( ActionEvent ae){
			  fmPointer.dispose();
			  fmPointer.setVisible(false);
			  std.tray.remove(std.trayIcon);			
		   }
	    });
	      
	   data=new JMenu("User Data");
	   importd=new JMenuItem("Import User Data");
	   exportd=new JMenuItem("Export User Data");
	   
	   importd.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent ae){
				 chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			     int returnVal = chooser.showOpenDialog(FrameManager.this);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = chooser.getSelectedFile();
			            System.out.println("choosed:"+file);
			            String sfile=file.toString();
			            System.out.println("file:"+sfile);//Debug
			            Scanner s = new Scanner(sfile);
			            s.useDelimiter("[\\\\]");
			            String token;
			            
			            do {		            	
     		            token = s.next();
			            System.out.println("found " + token);
			            } while (s.hasNext());
			            
			            //This is where a real application would open the file.
			           //String tokenizer[]=sfile.split("\\"); 
			          // int tokensLength=tokenizer.length;
			          // System.out.println("Debug token length:"+tokensLength);//Debug
			           //getting the last directory in the "path" directories tree
			           //String username=tokenizer[tokensLength-1];
			           System.out.println(token);//DEBUG
			           //creating directory named with username in our program home folder
			           File newDir=new File("Data/"+token);
			           newDir.mkdir();
			           //performing copying
			           CopyDirectory cd=new CopyDirectory();
			           try {
						cd.copyDirectory(file, newDir);}
			           catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					   }
			        }				
			   }});
	   
	     exportd.addActionListener(new ActionListener(){
		     public void actionPerformed(ActionEvent ae){
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        int returnVal = chooser.showSaveDialog(FrameManager.this);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = chooser.getSelectedFile();
		            System.out.println("choosed to save:"+file);
		            //This is where a application would open the file.
		            File dest=new File(file.toString()+"/"+userID );
		            if(dest.exists()==false)
		              {
		            	 dest.mkdir();
		              } 
		            File source=new File("Data/"+userID);
		            CopyDirectory cd=new CopyDirectory();
		            try {
					      cd.copyDirectory(source, dest);
				        } 
		            catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				    }
		          }				
		         }});
	
	      data.add(importd);
          data.add(exportd);
	      year.add(createNew);
	      year.add(openExisted);
	      program.add(logout);
	      about=new JMenuItem("About HomeBudget");
	      
	      about.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent ae){
			//System.out.println("About");
			   aboutFrame af=new aboutFrame();	
		          }
	               });
	      
	      program.add(about);
	      program.add(exit);
	      bar.add(program);
	      bar.add(year);
	      bar.add(data);
	      this.setJMenuBar(bar);
	
	      desk = new JDesktopPane();
	      add(desk);
	      
	      Locale locale=Locale.UK;
	      
	      date=new Date();
	      dateformat=DateFormat.getDateInstance(DateFormat.DEFAULT,locale);
	      current_date=dateformat.format(date);	      
	      System.out.println(current_date);
	      dateparse=current_date.split("-");
	      current_year=dateparse[2];
	}

 List<Object> yearslist=new ArrayList<Object>();
 public static void main(String[] args){
	  
	    try {
		    	javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");

		        // if you want decorations for frames and dialogs you can put this two lines
		        //
			         JFrame.setDefaultLookAndFeelDecorated(true);	// to decorate frames 
			         JDialog.setDefaultLookAndFeelDecorated(true);	// to decorate dialogs 
		        //
		        // or put this one line
		        //
		     	// com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true);
			    //
			    // or if you want to use Apple's Panther window decoration
			    //
			    // com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true, "panther");
	         } catch (Exception e) {}
	
	FrameManager fm =new FrameManager();
	std=new SystemTrayManager(FrameManager.fmPointer); //creating program icon in System Tray
	System.out.println(fm.current_year);
	lp=new loginPanel(fmPointer);
    FrameManager.desk.add(lp);//adding loginPanel to the desk
	fm.setSize(1200, 768);
	fm.setVisible(true);
  }
 
    void startProgram()
    {
	    System.out.println("userd:"+fm.userID+",year:"+fm.current_year);
	    mf=new MainFrame(fm.userID,fm.current_year);
		mf.setSize(1200, 768);
		mf.setVisible(true);
		fm.remove(FrameManager.desk);
		desk = new JDesktopPane();//reconstructing desk(JDesktopPane)
		fm.add(desk);
		FrameManager.desk.add(mf);//adding MainFrame to the desk
		//fm.setSize(1100, 768);
		//fm.setVisible(true);
		//
     }
}