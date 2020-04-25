import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HouseDialog2  extends JDialog
 {
	monthPanel dialogPanel;
	HouseDialog2 hd2;
	monthPanel mp;
	JList paramList;
	DefaultListModel listModel;
	JScrollPane jsp;
	HouseDialog2(final JInternalFrame parent,final monthPanel dialogPanel)
	  {
		  super(javax.swing.JOptionPane.getFrameForComponent( parent ),"Create New Parameter",true);	
		  mp=dialogPanel;
          hd2=this;
		  setSize(400,400);
		
		  setLayout(new FlowLayout());
		  listModel = new DefaultListModel();
		  paramList=new JList(listModel);
		  for(int i=0;i<mp.MainTableData2.length;i++)
		    {
			   listModel.addElement(mp.MainTableData2[i][0]);				
		    }
		  jsp=new JScrollPane(paramList);
		  JLabel param = new JLabel("Parameter",JLabel.LEFT);
		  final JTextField paramText= new JTextField(20);
		  this.dialogPanel=dialogPanel;
		  Box hbox=Box.createHorizontalBox();
		  Box vbox=Box.createVerticalBox();
		  JLabel msg1=new JLabel("The created parameter will be added");
		  JLabel msg2=new JLabel("to all tables in currient year");
		  JButton save=new JButton("Save");
		  JButton closeB=new JButton("close");
		  //dialogPanel.AddUsed=false; //Initialy we still don't used the AddEntry button
	      save.addActionListener(new ActionListener(){
		     public void actionPerformed(ActionEvent ae)
		       {
			      //System.out.println(paramText.getText()); //check the text field
			      //System.out.println(dialogPanel.PanelName);//check panel object
			      //System.out.println(dialogPanel+" "+dialogPanel.mainTable); check table obj
			
			      String[]args={paramText.getText(),"0"};
			      //***THE NEXT PROCEDURES ARE IMPLEMENTED FOR ALL MONTH PANELS: dialogPanel.mf.monthpanels[i]***
			      for(int i=0;i<dialogPanel.mf.monthpanels.length;i++)
			       {
				    dialogPanel.mf.monthpanels[i].mainTableData.add(args);
				    System.out.println(dialogPanel.mf.monthpanels[i].mainTableData.size());
				    dialogPanel.mf.monthpanels[i].MainTableData2=new String [dialogPanel.mf.monthpanels[i].mainTableData.size()][2];
			       }
			      //dialogPanel.MainTableData2Pntr=dialogPanel.MainTableData2;//referring to the new MainTableData2 object
			      for(int i=0;i<dialogPanel.mf.monthpanels.length;i++)
			       {
				     System.out.println("dialog panel MT2 length"+dialogPanel.MainTableData2.length);
			         for (int j=0;j<dialogPanel.MainTableData2.length;j++)
			           {
				          //System.out.println(dialogPanel.mf.monthpanels[i].PanelName+" MT2 length "+dialogPanel.mf.monthpanels[i].MainTableData2.length);
				            dialogPanel.mf.monthpanels[i].MainTableData2[j]=dialogPanel.mf.monthpanels[i].mainTableData.get(j);//renewing data in array from Collection
				          //System.out.println(dialogPanel.MainTableData2[j][0]); //check
			           }
			       }                                                    
		          ///*********************************************************************
			      for(int i=0;i<dialogPanel.mf.monthpanels.length;i++)
			        {
			          // ***updating ComboBox parameters list***
				      dialogPanel.mf.monthpanels[i].houseParams=new String[dialogPanel.MainTableData2.length];
				
			         //dialogPanel.AddUsed=true;//after the houseParams refers to the new String object we changing boolean value AddUsed
			         //dialogPanel.MainTableData2Pntr=dialogPanel.MainTableData2;//referring to the new MainTableData2 object
			
			          for(int j=0;j<dialogPanel.MainTableData2.length;j++)
			            {
				           dialogPanel.mf.monthpanels[i].houseParams[j]=(String) dialogPanel.MainTableData2[j][0];
			            }// ***updating ComboBox parameters list***
			
			          dialogPanel.mf.monthpanels[i].comboModel.insertElementAt(paramText.getText(), dialogPanel.mf.monthpanels[i].comboModel.getSize());
			
			          //**********************************<actualy adding row in the table>
			          dialogPanel.mf.monthpanels[i].model1.insertRow(dialogPanel.mf.monthpanels[i].mainTable.getRowCount(),args);
			          //********************************************
			          //Saving table data for all panels//
			          dialogPanel.mf.monthpanels[i].saveTable();
			          //********************************************
			        }
			      listModel.addElement(paramText.getText());
			//setVisible(false);
			//dispose();
	       }
	});
		closeB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			   {
				  hd2.dispose();
				  hd2.setVisible(false);
			   }
			});
	 vbox.add(jsp);	
	 vbox.add(msg1);
	 vbox.add(msg2);
	 hbox.add(param);
	 hbox.add(paramText);
	 vbox.add(hbox);
	 vbox.add(save);
	 vbox.add(closeB);
	 add(vbox);
	  }
	//protected void parseDialog(){
		//dialogPanel.mainTable.setValueAt( ,0, 0);
		
	//}
}
