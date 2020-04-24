import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;
public class yearListDialog extends JDialog{
MainFrame newMF;
String userID;
yearListDialog(JFrame parent,String name,final String userID)
{
super(parent,name);
this.userID=userID;
this.setSize(600,400);
this.setLayout(new FlowLayout());
final yearListDialog dialogPointer=this;
File dataDIR=new File("Data/"+userID);
String DIRlist[]=dataDIR.list();
final JList DList=new JList(DIRlist);
DList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
this.add(DList);
JButton openB=new JButton("Open");
openB.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ae){
	Object selected=DList.getSelectedValue();
	String selectedName=(String) selected;
	newMF=new MainFrame(userID,selectedName);
	newMF.setSize(1100,768);
	newMF.setVisible(true);
	//desk = new JDesktopPane();
	//parent.add(desk);
	FrameManager.mf.dispose();
	FrameManager.mf.setVisible(false);
	FrameManager.mf=newMF;
	FrameManager.desk.add(newMF);
		
	}
});
JButton closeB=new JButton("Close Window");
closeB.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ae){
    dialogPointer.dispose();
    dialogPointer.setVisible(false);
	}
});
this.add(openB);
this.add(closeB);
}
}
