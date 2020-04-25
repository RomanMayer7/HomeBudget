import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RemoveParamDia extends JDialog 
  {
	int k; //a loop counter
	int rmRowIndex;
	monthPanel mp;
	RemoveParamDia rpd;
	JList paramList;
	JScrollPane jsp;
	DefaultListModel listModel;
	JLabel msg;
	JButton removeB,closeB;
	RemoveParamDia(final JInternalFrame parent,final monthPanel dialogPanel)
	  {
		super(javax.swing.JOptionPane.getFrameForComponent( parent ),"Remove Parameter",true);	
		mp=dialogPanel;
		rpd=this;
		setSize(640,480);
		setLayout(new FlowLayout());
		listModel = new DefaultListModel();
		paramList=new JList(listModel);
		for(int i=0;i<mp.MainTableData2.length;i++)
		  {
			  listModel.addElement(mp.MainTableData2[i][0]);				
		  }
		jsp=new JScrollPane(paramList);
		msg=new JLabel("This action removes the chosen parameter from all Main Tables in the current year"); 
		removeB=new JButton("Remove Parameter");
		
		removeB.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent ae)
		    {
			   int selected=paramList.getSelectedIndex();
			   String selectedN=(String) paramList.getSelectedValue();
			   //System.out.println(selectedN);
			   listModel.remove(selected);
			   //int size =listModel.getSize(); 
		    //System.out.println("size:"+size);
			
			for(int i=0;i<mp.mf.monthpanels.length;i++){
				String[][] tempArray=new String[mp.mf.monthpanels[i].MainTableData2.length-1][2];
				k=0;
				for(int j=0;j<mp.mf.monthpanels[i].MainTableData2.length;j++){
					 
					if(((mp.mf.monthpanels[i].MainTableData2[j][0].equals(selectedN))==false))
					{		
						tempArray[k]=mp.mf.monthpanels[i].MainTableData2[j];
				        k++;
					}
					else
					{
						rmRowIndex=j;
					}
				}
					mp.mf.monthpanels[i].MainTableData2=tempArray;
					mp.mf.monthpanels[i].saveTable();
					mp.mf.monthpanels[i].model1.removeRow(rmRowIndex);
				}	
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
		//int size =listModel.getSize(); 
		//System.out.println("size:"+size);
	}

}
