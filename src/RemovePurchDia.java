import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class RemovePurchDia extends JDialog 
  {
	 monthPanel mp;
	 RemovePurchDia rpd;
	 JButton removeB;
	 JButton closeB;
	 JLabel msg;
	 JList purchList;
	 DefaultListModel listModel;
	 JScrollPane jsp;
	 RemovePurchDia(final JInternalFrame parent,final monthPanel dialogPanel)
	   {
		  super(javax.swing.JOptionPane.getFrameForComponent( parent ),"Remove Purchase",true);
		  
		  mp=dialogPanel;
		  rpd=this;
		  setSize(640,480);
		  setLayout(new FlowLayout());
		  listModel = new DefaultListModel();
		  purchList=new JList(listModel);
		  for(int i=0;i<mp.PurchTableData.length;i++)
		    {
			   listModel.addElement(mp.PurchTableData[i][0]);				
		    }
		  jsp=new JScrollPane(purchList);
		  msg=new JLabel("This action removes the chosen purchase"); 
		  removeB=new JButton("Remove Parameter");
		  removeB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			  {
				 String[][]tempArray=new String[(mp.PurchTableData.length)-1][3];
				 int selected=purchList.getSelectedIndex();
				 listModel.remove(selected);
				 int tempindex=0;
				 for(int i=0;i<mp.PurchTableData.length;i++)
				 {
					if(i!=selected)
                      {
						 tempArray[tempindex]=mp.PurchTableData[i];
						 tempindex++;							                           
					  }
				  }
				mp.PurchTableData=new String[tempArray.length][3];
				mp.PurchTableData=tempArray;
				mp.saveTable2();
				mp.model2.removeRow(selected);
			}
		});
		closeB=new JButton("Close");
		closeB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			  {
				 rpd.dispose();
				 rpd.setVisible(false);
			  }
			});
		
		add(msg);
		add(jsp);
		add(removeB);
		add(closeB);
		setVisible(true);
	}

}
