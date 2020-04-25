import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class graphFrame extends JFrame
{
	monthPanel Mpanel;
	graphPanel Gpanel;
	graphFrame gf;
	static String paramName;
	JButton rfrsh=new JButton("Refresh");
	JButton close=new JButton("Close");
	JRadioButton b1=new JRadioButton("Get House Data");
	//b2.addActionListener(this);
	JRadioButton b2=new JRadioButton("Get Purchases Data");
	//b3.addActionListener(this);	
	//Defining buttons group
	ButtonGroup bg=new ButtonGroup();
	JList ValList;
	JScrollPane jsp;
	MainFrame mf;
	int valSum=0;//stores the values of PurchTable cost integers which are parsed by type  
	static float ParamValues[];
	JLabel msg=new JLabel("Chose Parameter and press 'Refresh' to see new Data");
	graphFrame(final monthPanel mp,String framename)
	{
		super(framename);
		gf=this;
		setLayout(new BorderLayout());
		Box column=Box.createHorizontalBox();
		Box Innerbox=Box.createVerticalBox();
		Mpanel=mp;
		//System.out.println(mp.houseParams);
		final DefaultListModel lmodel=new DefaultListModel();
         for(int i=1;i<mp.houseParams.length-1;i++)
         {
        	lmodel.addElement(mp.houseParams[i]);
         }
		ValList=new JList(lmodel);
		ValList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ValList.setSelectedIndex (0);
		jsp=new JScrollPane(ValList);
		JPanel comboPanel=new JPanel();
		mf=mp.mf;//pointer to the MainFrame
		Gpanel=new graphPanel();
		Gpanel.setSize(350,350);
		Gpanel.setVisible(true);
		column.add(Gpanel);
		
		comboPanel.add(jsp);
	
		comboPanel.setSize(200,200);
		column.add(comboPanel);
		rfrsh.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e)
		    {
		       if (b2.isSelected())
		         {
			        refreshdata2();
		         }
		       else
		         {
			        refreshdata();
		         }
		      Gpanel.repaint();
			}
		});
		
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				gf.dispose();
				gf.setVisible(false);
			}
		});

		Innerbox.add(msg);
		Innerbox.add(rfrsh);
		bg.add(b1);
		bg.add(b2);
		Innerbox.add(b1);
		Innerbox.add(b2);
		Innerbox.add(close);
		Innerbox.add(Box.createHorizontalStrut(150));
		column.add(Innerbox);
		add(column);
		
		ParamValues=new float[15];//creating 12 month +2 elements array because of the drawLine from x-coordinates (n+1;n+2)
		//System.out.println(mf.monthpanels.length);
		//System.out.println(mf.monthpanels[11].MainTableData2[Mpanel.SelectedID][1]);//test
		b1.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae)
		  {
			lmodel.removeAllElements();
			  for(int i=1;i<mp.houseParams.length-1;i++)
			     {
		        	lmodel.addElement(mp.houseParams[i]);
		         }
		      ValList.setSelectedIndex (0);	  
		      refreshdata();
			}});
		
		b2.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
			lmodel.removeAllElements();
			  for(int i=0;i<mp.PurchTypes.length;i++)
			    {
		        	lmodel.addElement(mp.PurchTypes[i]);
		        }
			ValList.setSelectedIndex (0);  
			refreshdata2();	
			}
		});
		
	   
		
		/*for(int i=0;i<12;i++){
			System.out.println(mf.monthpanels[i].MainTableData2[Mpanel.SelectedID][1]);
			ParamValues[i]=Integer.parseInt(mf.monthpanels[i].MainTableData2[Mpanel.SelectedID][1]);
		*///	}
            for(int i=0;i<12;i++)
            {
			    ParamValues[i]=Float.parseFloat(mf.monthpanels[i].MainTableData2[ValList.getSelectedIndex()+1][1]);
			    paramName=Mpanel.MainTableData2[ValList.getSelectedIndex()+1][0];
			}

		    if(b2.isSelected())
		    {
			  for(int i=0;i<12;i++)
			   {
				 for(int j=0;j<mf.monthpanels[i].PurchTableData.length;j++)
				  {	
				    String[] tokens=((String) mf.monthpanels[i].PurchTableData[j][2]).split("#");
				    String purchtype=tokens[0];
			        if (Mpanel.PurchTypes[ValList.getSelectedIndex()]==purchtype)   
			        {
				       valSum +=Integer.parseInt((String) mf.monthpanels[i].PurchTableData[j][1]);
			        }   
				}
				  ParamValues[i]=valSum;
			   }
	          paramName=Mpanel.PurchTypes[ValList.getSelectedIndex()];		
		    }
		    else{
	               paramName=Mpanel.MainTableData2[ValList.getSelectedIndex()+1][0];
		        }
		    }
	       public void refreshdata()
	        {
		      for(int i=0;i<12;i++)
		      {	
			     ParamValues[i]=Float.parseFloat(mf.monthpanels[i].MainTableData2[ValList.getSelectedIndex()+1][1]);
			      paramName=Mpanel.MainTableData2[ValList.getSelectedIndex()+1][0];
			  }
	        }
	       
	      public void refreshdata2()
	      {
            valSum=0;
		    for(int i=0;i<12;i++)
		    {
			   System.out.println(mf.monthpanels[i].PurchTableData.length);
			   for(int j=0;j<mf.monthpanels[i].PurchTableData.length;j++)
			    { 	
			       String[] tokens=((String) mf.monthpanels[i].PurchTableData[j][2]).split("#");
			       String purchtype=tokens[0];
                   System.out.println(purchtype+"_"+i);
			       System.out.println(Mpanel.PurchTypes[ValList.getSelectedIndex()]+"_"+i);
		           if (Mpanel.PurchTypes[ValList.getSelectedIndex()].equals(purchtype))
		            {
			          //System.out.println("o yeah!");
			           valSum +=Float.parseFloat((String) mf.monthpanels[i].PurchTableData[j][1]);
		            }  
			   }
			  ParamValues[i]=valSum;
			  valSum=0;
		    }
        paramName=Mpanel.PurchTypes[ValList.getSelectedIndex()];	
		
	  }
		
 }
	

