import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class NewTypeDialog extends JDialog {
	NewTypeDialog NTDpointer;
	JList types;
	ArrayList<String> typesCollect;
	DefaultListModel listModel;
	JScrollPane jsp;
	monthPanel mp;
	JLabel newtypeL;
	JLabel listL;
	JLabel removeL;
	JTextField newtypeF;
    JButton createB;
	JButton closeB;
	JButton removeB;
	Box hBox;
	Box vBox;
	File typesFile;
	NewTypeDialog(final JInternalFrame parent,String name,final monthPanel mp){
			super(javax.swing.JOptionPane.getFrameForComponent( parent ),name);
			NTDpointer=this;
			this.mp=mp;
			typesCollect=new ArrayList<String>();
			listModel = new DefaultListModel();
			types=new JList(listModel);
			for(int i=0;i<mp.PurchTypes.length;i++){
				listModel.addElement(mp.PurchTypes[i]);				
			}
			jsp=new JScrollPane(types);
			newtypeL=new JLabel("Enter New Type:");
			newtypeF=new JTextField(25);
			listL=new JLabel("Types List:");
			removeL=new JLabel("Remove Type from the List");
			setLayout(new FlowLayout());
			vBox=Box.createVerticalBox();
			hBox=Box.createHorizontalBox();
			createB=new JButton("Create New Type");
			closeB=new JButton("close");
			removeB=new JButton("Remove Type");
			vBox.add(listL);
			vBox.add(jsp);
			hBox.add(newtypeL);
			hBox.add(newtypeF);
			vBox.add(hBox);
			vBox.add(createB);
			vBox.add(removeL);
			vBox.add(removeB);
			vBox.add(closeB);
			add(vBox);
			setSize(800,600);
			setVisible(true);
			
			createB.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					if(newtypeF.getText().length()>0){
					listModel.addElement(newtypeF.getText());
					
					typesFile=new File("types.txt");
					if(typesFile.exists()==false){
						try {
							typesFile.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//mp.typesCollect.removeAll(typesCollect);
					mp.typesCollect=new ArrayList<String> ();//reseting collection
				
					for(int i=0;i<mp.PurchTypes.length;i++){
						mp.typesCollect.add(mp.PurchTypes[i]);
					}
					String newType=newtypeF.getText();
					mp.typesCollect.add(newType);
					
					mp.PurchTypes=new String[mp.typesCollect.size()];
					
					for(int i=0;i<mp.typesCollect.size();i++){
						mp.PurchTypes[i]=mp.typesCollect.get(i);
					}
					mp.typesList=new JList(mp.PurchTypes);
					
					try {
						BufferedWriter bw=new BufferedWriter(new FileWriter(typesFile));
						for(int j=0;j<mp.PurchTypes.length;j++){
							
							//bw.write("writing test");
							bw.write(mp.PurchTypes[j]);
							bw.newLine();
							}
							bw.close();
							System.out.println("Your file has been written");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
					else{
						final JFrame error=new JFrame("Error");
						error.setLayout(new FlowLayout());
						error.add(new JLabel("The 'type' must contain at least one character!"));
						JButton close=new JButton("Close");
						close.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent ae){
								error.dispose();
								error.setVisible(false);
							}
						});
						error.add(close);
						error.setSize(640,480);
						error.setVisible(true);
					}
				}
			});
			removeB.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
					
					int selected=types.getSelectedIndex();
					String[] tempArray=new String[(mp.PurchTypes.length)-1];
					int index=0;
					//System.out.println("PurchTypes[13]="+mp.PurchTypes[13]+",selected:"+selected);
					for(int i=0;i<mp.PurchTypes.length;i++)
					{
						//System.out.println("PurchTypes length:"+mp.PurchTypes.length+",tempArray length:"+tempArray.length);
					if(i!=selected){
						tempArray[index]=mp.PurchTypes[i];
						System.out.println("PurchTypes["+i+"]="+mp.PurchTypes[i]+",tempArray["+index+"]="+tempArray[index]);
							index++;
						
					                }	
					
					}
					listModel.removeElementAt(types.getSelectedIndex());
					
					mp.PurchTypes=new String[tempArray.length];
					mp.PurchTypes=tempArray;
					
					mp.typesList=new JList(mp.PurchTypes);//updating monthPanel object
					
					typesFile=new File("types.txt");
					if(typesFile.exists()==false){
						try {
							typesFile.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					try {
						BufferedWriter bw=new BufferedWriter(new FileWriter(typesFile));
						for(int j=0;j<mp.PurchTypes.length;j++){
							
							//bw.write("writing test");
							bw.write(mp.PurchTypes[j]);
							bw.newLine();
							}
							bw.close();
							System.out.println("Your file has been written");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			});
			closeB.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					NTDpointer.dispose();
					NTDpointer.setVisible(false);
				}
			});
			
	}
	
	

}
