import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class setPurchValue extends JDialog{
	monthPanel mp;
	setPurchValue spv;
	JComboBox purchCombo;
	Box hBox;
	DefaultComboBoxModel comboModel;
	setPurchValue(JInternalFrame parent,final monthPanel dialogPanel){
		super(javax.swing.JOptionPane.getFrameForComponent( parent )
				,"Set Parameter Value",true);	
				    spv=this;
					setSize(400,200);
					setLayout(new FlowLayout());
					JLabel param = new JLabel("Purchase",JLabel.LEFT);
					JLabel cost = new JLabel("Cost",JLabel.LEFT);
					//final JTextField paramText= new JTextField(10);
					final JTextField costText = new JTextField(5);
					mp=dialogPanel;
					JButton save=new JButton("Save");
					JButton close=new JButton("Close");

					String[] Purchases=new String[mp.PurchTableData.length];// parameters names list used in ComboBox
					
					for(int i=0;i<mp.PurchTableData.length;i++){
					Purchases[i]= mp.PurchTableData[i][0];
					}
				 comboModel=new DefaultComboBoxModel(Purchases);
				purchCombo=new JComboBox(comboModel);
				
				save.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						int selectedID=purchCombo.getSelectedIndex();
						mp.model2.setValueAt(costText.getText(),selectedID,1);
						mp.PurchTableData[selectedID][1]=costText.getText();
						mp.saveTable2();
					}
				});
				
				close.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						spv.dispose();
						spv.setVisible(false);
					}
					});
				 hBox=Box.createHorizontalBox();
				 hBox.add(param);
				 hBox.add(purchCombo);
				 hBox.add(cost);
				 hBox.add(costText);
				 add(hBox);
				 add(save);
				 add(close);
		
	}

}
