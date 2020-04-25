import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
public class totalCalculator extends JFrame 
  {
	 totalCalculator tc; 
	 monthPanel mp;
	 totalGraphPanel tgp;
	 JLabel monthN,salaryL,expenseL,resultsL;
	 JLabel msg,msg2;
	 JButton closeB;
	 JButton refreshB,nextMonthB;
	 JList monthList;
	 JPanel secondPanel;
	 DefaultListModel listModel;
	 JScrollPane jsp;
	 Box hBox;
	 Box vBox,vBox2;
	 int valSum; //int
	 int counter=0; //count the last filled index of totalValues[][] when we filling it first time
	             //when filling second time we starting from that index
	 float expense; //int
	 float results; //int
	 String tempIndex;//for indicating if next value in purchases iteration have the same index as previous,if not
	 //adding it to the 'expense' sum
	 String[][] totalValues;
	 
     totalCalculator(String name,final monthPanel mp)
       {
	     super(name);
	     
	     this.mp=mp;
	     tc=this;
	     totalValues=new String[mp.PurchTypes.length+(mp.MainTableData2.length-1)][2];
	     setLayout(new BorderLayout());
	     //setLayout(new FlowLayout());
	     PopulateValues();
	     
	     for(int i=0;i<totalValues.length;i++)
	        { 
	    	   //DEBUGGING PART	
		        System.out.println(totalValues[i][0]+";"+totalValues[i][1]);
		    }
	     listModel = new DefaultListModel();
	     monthList=new JList(listModel);
		 for(int i=0;i<mp.mf.month.length;i++)
		    {
			   listModel.addElement(mp.mf.month[i]);
		    }
		 msg=new JLabel("Choose month and press 'Refresh'");
		 msg2=new JLabel("or press 'Next' to show next month");
		 monthN=new JLabel(mp.PanelName);
		 salaryL=new JLabel("Your month Salary:"+mp.MainTableData2[0][1]);
		 expenseComputer();
		 expenseL=new JLabel("The month Expense:"+expense);
		 results=Integer.parseInt(mp.MainTableData2[0][1])-expense;
		 resultsL=new JLabel("The month Results:"+results);
		
		 JLabel demolabel=new JLabel("demo");
		 monthN.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),18));
		 salaryL.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		 expenseL.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		 resultsL.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		
		 closeB=new JButton("close");
		 nextMonthB=new JButton("Next Month");
		 refreshB=new JButton("Refresh Data");
		
	     secondPanel=new JPanel();
	     hBox=Box.createHorizontalBox();
	     secondPanel.setLayout(new FlowLayout());
	     secondPanel.setSize(200,200);
	     vBox=Box.createVerticalBox();
	     vBox.add(monthN);
	     vBox.add(salaryL);
	     vBox.add(expenseL);
	     vBox.add(resultsL);
	     vBox.add(monthList);
	     vBox.add(msg);
	     vBox.add(msg2);
	     vBox.add(nextMonthB);
	     vBox.add(refreshB);
	     vBox.add(closeB);
	     secondPanel.add(Box.createHorizontalStrut(-320));
	     secondPanel.add(vBox);
	     secondPanel.add(Box.createHorizontalStrut(100));
	    tgp=new totalGraphPanel(totalValues);//passing totalValues to the totalGraphPanel constructor
	    tgp.setSize(640,480);
	    tgp.setVisible(true);
	    jsp=new JScrollPane(tgp);
	    secondPanel.add(Box.createHorizontalStrut(-470));
	    hBox.add(secondPanel);
	    hBox.add(Box.createHorizontalStrut(-100));
	    hBox.add(secondPanel);
	    hBox.add(jsp);
	    add(hBox);
	
//***************BUTTON'S LISTENERS**********************
	refreshB.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		   {
			   expense=0;//reseting variable
			   results=0;//reseting variable
			   hBox.remove(jsp);
			   int selected=monthList.getSelectedIndex();
			   counter=0;
			   PopulateValues(mp.mf.monthpanels[selected]);
			   tgp=new totalGraphPanel(totalValues);//passing totalValues to the totalGraphPanel constructor
		   	   jsp=new JScrollPane(tgp);
			   hBox.add(jsp);
			   
		       monthN.setText(mp.mf.month[selected]);
		       salaryL.setText("Your month Salary:"+mp.mf.monthpanels[selected].MainTableData2[0][1]);
		       expenseComputer(mp.mf.monthpanels[selected]);
		       expenseL.setText("The month Expense:"+expense);
		       results=Integer.parseInt(mp.mf.monthpanels[selected].MainTableData2[0][1])-expense;
		       resultsL.setText("The month Results:"+results);		
		   }
	   });
	nextMonthB.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		   {
			   expense=0;//reseting variable
			   results=0;//reseting variable
			   hBox.remove(jsp);
			   int next=(monthList.getSelectedIndex()+1)%12; //LOOP ITERATION FOR AN ARRAY INDEX!
			   counter=0;
			   PopulateValues(mp.mf.monthpanels[next]);
			   jsp=new JScrollPane(tgp);
			   tgp=new totalGraphPanel(totalValues);//passing totalValues to the totalGraphPanel constructor
			   hBox.add(jsp);
			
		       monthN.setText(mp.mf.month[next]);
		       salaryL.setText("Your month Salary:"+mp.mf.monthpanels[next].MainTableData2[0][1]);
		       expenseComputer(mp.mf.monthpanels[next]);
		       expenseL.setText("The month Expense:"+expense);
		       results=Integer.parseInt(mp.mf.monthpanels[next].MainTableData2[0][1])-expense;
		       resultsL.setText("The month Results:"+results);	
		
		       monthList.setSelectedIndex(next);//finally moving selector in the JList GUI		
		   }
	   });
	
	closeB.addActionListener(new ActionListener()
	  {
		public void actionPerformed(ActionEvent ae)
		  {
			  tc.dispose();
			  tc.setVisible(false);
		  }
	  });
	
}
void PopulateValues()
    {
	   //First of all adding parameters and values of all purchases into general array totalValues
	   for(int i=0;i<mp.PurchTypes.length;i++)
	      {
		    for(int j=0;j<mp.PurchTableData.length;j++)
		      {
			     String[] tokens=((String) mp.PurchTableData[j][2]).split("#");
			     String purchtype=tokens[0];
			     
		         if (mp.PurchTypes[i].equals(purchtype))
		          { 
			         valSum +=Float.parseFloat((String) mp.PurchTableData[j][1]);
		          }  
			  }
			  totalValues[i][1]=""+valSum;
			  totalValues[i][0]=mp.PurchTypes[i];
			  counter++;
			  valSum=0;//reseting ValSum after iterating all PurchTableData
		  }
	   
	 //now adding parameters and values of MainTableData2 into totalValues 
	 for (int i=0;i<mp.MainTableData2.length-1;i++)
	    {
	       //System.out.println(mp.MainTableData2[i+1-1][1]);//DEBUGGING
	       totalValues[counter+i][0]=mp.MainTableData2[i+1][0];
	       // System.out.println("array index:"+i);//DEBUGGING
	       totalValues[counter+i][1]=mp.MainTableData2[i+1][1]; 
	    }
	}

void PopulateValues(monthPanel mp)
    {
	   //OVERRIDING THIS METHOD FOR GETTING INFO FROM ALL MONTH TABLES
	   //First of all adding parameters and values of all purchases into general array totalValues
	   for(int i=0;i<mp.PurchTypes.length;i++)
	     {
		   for(int j=0;j<mp.PurchTableData.length;j++)
		     {
			    String[] tokens=((String) mp.PurchTableData[j][2]).split("#");
			    String purchtype=tokens[0];
		        if (mp.PurchTypes[i].equals(purchtype))
		         {
			       valSum +=Float.parseFloat((String) mp.PurchTableData[j][1]);
		         }  
			 }
			  totalValues[i][1]=""+valSum;
			  totalValues[i][0]=mp.PurchTypes[i];
			  counter++;
			  valSum=0;//reseting ValSum after iterating all PurchTableData
		}
	   
	//now adding parameters and values of MainTableData2 into totalValues 
	for (int i=0;i<mp.MainTableData2.length-1;i++){
	  //System.out.println(mp.MainTableData2[i+1-1][1]);//DEBUGGING
	  totalValues[counter+i][0]=mp.MainTableData2[i+1][0];
	 // System.out.println("array index:"+i);//DEBUGGING
	  totalValues[counter+i][1]=mp.MainTableData2[i+1][1];
	  
	}
}

void expenseComputer()
  {
	  for (int i=0;i<mp.MainTableData2.length-1;i++)
	     {
		   expense+=Float.parseFloat(mp.MainTableData2[i+1][1]);
	     }
	  
	  for(int j=0;j<mp.PurchTableData.length;j++)
	     {
		   String[] tokens=((String) mp.PurchTableData[j][2]).split("#");
		   String purchIndex=tokens[1];
	       if((purchIndex.equals(tempIndex))==false)
	          {
		        expense+=Float.parseFloat((String) mp.PurchTableData[j][1]);
	          }
	       tempIndex=tokens[1];	
	     }
}

void expenseComputer(monthPanel mp)
  {
     //OVERRIDING METHOD FOR ALL MONTH PANELS	
	 for (int i=0;i<mp.MainTableData2.length-1;i++)
	   {
		  expense+=Integer.parseInt(mp.MainTableData2[i+1][1]);
	   }
	 for(int j=0;j<mp.PurchTableData.length;j++)
	 {
		String[] tokens=((String) mp.PurchTableData[j][2]).split("#");
		String purchIndex=tokens[1];
	    if((purchIndex.equals(tempIndex))==false)
	      {
		    String cast=(String) mp.PurchTableData[j][1];
		    expense+=Float.parseFloat(cast);
	      }
	     tempIndex=tokens[1];	
	 }	
 }
}
