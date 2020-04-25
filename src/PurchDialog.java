import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class PurchDialog extends JDialog
  {
	monthPanel parentPanel;
	PurchDialog pd;
	PurchDialog(final JInternalFrame parent,final monthPanel parentPanel){
		super(javax.swing.JOptionPane.getFrameForComponent( parent ),"Add New Purchase",true);
		
    this.parentPanel=parentPanel;
    pd=this;
    setSize(600,400);
	setLayout(new FlowLayout());
	Box vbox=Box.createVerticalBox();
	JLabel param = new JLabel("Purchase",JLabel.LEFT);
	JLabel cost = new JLabel("Cost",JLabel.LEFT);
	JLabel typesL=new JLabel("Select type:");
	JButton save=new JButton("Save");
	JButton close=new JButton("Close");
	JScrollPane jsp=new JScrollPane(parentPanel.typesList);
	final JTextField paramText= new JTextField(10);
	final JTextField costText = new JTextField(3);
	save.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		  {
		    // Get the index of all the selected items from the list
		    int[] selectedIx = parentPanel.typesList.getSelectedIndices();
		    String[][] args=new String[selectedIx.length][3];
		    for(int i=0;i<selectedIx.length;i++){
		    String[] pre_args={paramText.getText(),costText.getText(),parentPanel.PurchTypes[selectedIx[i]]+"#"+(parentPanel.purchTable.getRowCount()-i)};
			args[i]=pre_args;
			parentPanel.purchTableData.add(args[i]);
			//**********************************<actualy adding row in the table>
			parentPanel.model2.insertRow(parentPanel.purchTable.getRowCount(),args[i]);
		    }
		    parentPanel.PurchTableData=new String [parentPanel.purchTableData.size()][3];
			for (int i=0;i<parentPanel.PurchTableData.length;i++)
			  {
				  parentPanel.PurchTableData[i]=parentPanel.purchTableData.get(i);//renewing data in array from Collection	
			  }
			parentPanel.saveTable2();
			setVisible(false);
			dispose();
			
		}
	});
	
	close.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		  {
			  pd.dispose();
			  pd.setVisible(false);
		  }
		});
	
	add(param);
	add(paramText);
	vbox.add(typesL);
	vbox.add(jsp);
	add(vbox);
	add(cost);
	add(costText);
	add(save);
	add(close);
	
	}
	
	

}
