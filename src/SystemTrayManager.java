import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
//import java.applet.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SystemTrayManager
  {
	   FrameManager fm;
	   Image image;
	   SystemTray tray;
	   TrayIcon trayIcon;
	public SystemTrayManager(final FrameManager fm)
	   {
		  this.fm=fm;
	      //Check the SystemTray is supported
          if (!SystemTray.isSupported()) 
            {
               System.out.println("SystemTray is not supported");
               return;
            }
          final PopupMenu pop_up = new PopupMenu();
          //final TrayIcon trayIcon =
          //new TrayIcon(createImage("images/bulb.gif", "tray icon")); //first way to create image
    
          //final TrayIcon trayIcon=new TrayIcon(getImage(getCodeBase(),"Roman.gif"));  //second way to create image
          try 
          { 
             //finally creating image ,using swing classes
		     File imageFile = new File("SystemTrayIcon2.gif");
	         System.out.println(imageFile.exists());
	         image= ImageIO.read( imageFile );
	      }catch(IOException ae){System.out.println("an problem to load the image");}
          
          trayIcon=new TrayIcon(image);
          trayIcon.setImageAutoSize(true);
          tray = SystemTray.getSystemTray();
  
    // Create a pop-up menu components
    MenuItem aboutItem = new MenuItem("About");
    aboutItem.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		  {
			  //System.out.println("About");
			  aboutFrame af=new aboutFrame();	
		  }
	});
    
    CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
    CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
    Menu displayMenu = new Menu("Display");
    MenuItem errorItem = new MenuItem("Error");
    MenuItem warningItem = new MenuItem("Warning");
    MenuItem infoItem = new MenuItem("Info");
    MenuItem noneItem = new MenuItem("None");
    MenuItem exitItem = new MenuItem("Exit");
    
    exitItem.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent ae)
    	   {
    		  fm.dispose();
    		  fm.setVisible(false);
    		  tray.remove(trayIcon);
    	   }
       });
   
    //Add components to pop-up menu
    pop_up.add(aboutItem);
    pop_up.addSeparator();
    pop_up.add(cb1);
    pop_up.add(cb2);
    pop_up.addSeparator();
    pop_up.add(displayMenu);
    displayMenu.add(errorItem);
    displayMenu.add(warningItem);
    displayMenu.add(infoItem);
    displayMenu.add(noneItem);
    pop_up.add(exitItem);
   
    trayIcon.setPopupMenu(pop_up);
    trayIcon.setToolTip("Home Budget");
    try 
       {
         tray.add(trayIcon);
       } 
    catch (AWTException e) 
       {
         System.out.println("TrayIcon could not be added.");
       }
   }
	
}
