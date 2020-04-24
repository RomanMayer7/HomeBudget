import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class HouseDialog extends JDialog {
	monthPanel dialogPanel;
	HouseDialog hd;
	Box hBox;
HouseDialog(JInternalFrame parent,final monthPanel dialogPanel){
	super(javax.swing.JOptionPane.getFrameForComponent( parent )
,"Set Parameter Value",true);	
    hd=this;
	setSize(400,200);
	setLayout(new FlowLayout());
	JLabel param = new JLabel("Parameter",JLabel.LEFT);
	JLabel cost = new JLabel("Cost",JLabel.LEFT);
	//final JTextField paramText= new JTextField(10);
	final JTextField costText = new JTextField(3);
	this.dialogPanel=dialogPanel;
	JButton save=new JButton("Save");
	JButton close=new JButton("Close");
 save.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ae){
		//System.out.println(paramText.getText()); //check the text field
		//System.out.println(dialogPanel.PanelName);//check panel object
		//System.out.println(dialogPanel+" "+dialogPanel.mainTable); check table obj
		//System.out.println("SelectedID:"+dialogPanel.SelectedID);
		
		dialogPanel.model1.setValueAt(costText.getText(),dialogPanel.SelectedID,1);
		dialogPanel.MainTableData2[dialogPanel.SelectedID][1]=costText.getText();
		dialogPanel.saveTable();
		setVisible(false);
		dispose();
}
});
	close.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			hd.dispose();
			hd.setVisible(false);
		}
		});
 hBox=Box.createHorizontalBox();
 hBox.add(param);
 hBox.add(dialogPanel.houseCombo);
 hBox.add(cost);
 hBox.add(costText);
 add(hBox);
 add(save);
 add(close);
}
//protected void parseDialog(){
	//dialogPanel.mainTable.setValueAt( ,0, 0);
	
//}
}