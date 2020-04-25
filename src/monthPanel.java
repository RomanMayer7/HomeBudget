import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.awt.event.*;

public class monthPanel extends JPanel
  {
	  String userID;
	  String PanelName;
	  MainFrame mf;
	  monthPanel panel=this;
	  graphFrame graphframe;
	  totalCalculator totalCalc;
	  Image image;
	  File cpf;//Checkpoint label showing us what table data was already saved in Main table
	  File cpf_p;//Checkpoint label showing us what table data was already saved in Purchases table
	  File typesFile;
	  ArrayList<String> typesCollect;
	  final JInternalFrame frame;//pointer to MainFrame
	  JTable mainTable,purchTable;
	  DefaultTableModel model1,model2;
	  JButton setVal,AddEntry,SaveChanges,SaveChanges2,Statistics;
	  //boolean AddUsed;//tells if we use AddEntry method on table and the
	  //reference was changed to the new String object
	  JButton purchase,setPVal,newType,monthTotals;
	  JButton removeMT,removePT;
	  JComboBox houseCombo;
	  JList typesList;//types list used in purchases table
	  int ListSelectedInd[];// The array of selected indeces of typesList
	  //each time we want to create new purchase we assigning it to one or many types,defined by selected indices 
	  DefaultComboBoxModel comboModel;
	  List<String[]> mainTableData=new ArrayList<String[]>();
	  String[][] MainTableData2;
	  //String[][] MainTableData2Pntr;//pointer to MainTableData2
	  Object[][] MainTableData={{"Salary(your month income)",0},{"Rent",0},{"Land Tax(arnona)",0},{"Water",0},{"Electricity",0},{"Transport",0},{"Telephone",0},{"Visa(credit)",0}};
	  List<String[]>purchTableData=new ArrayList<String[]>();
	  String[][] PurchTableData={{"0","0","0#0"}};
	  //Object[][] PurchTableData;
	  String[] MainTableColHeads={"Parameter","Cost"};
	  String[] PurchTableColHeads={"Purchase","Cost","Type"};
	  String[] PurchTypes={"wear","food","rest","electronic devices","computer devices","home stuff","alcohol","presents"};
	  String houseParams[];//parameters list from table used in ComboBox
	  String SelectedCombo;
	  static int SelectedID;
	  Box column1;
	  Box column2;
	  JTextField salaryText;
	  JLabel salaryMsg;
	  JLabel salaryLabel;
	  JLabel yearID;
	  JLabel currentDate;
	  public monthPanel(String x,final JInternalFrame frame,MainFrame mainframe)
	     {
		    //************************************************************************
		    mf=mainframe;
		    userID=mainframe.userID;
		    yearID=new JLabel("User:"+userID+";Year:"+mf.yearid);
		    yearID.setFont(new Font(yearID.getFont().getName(),yearID.getFont().getStyle(),22));
		  try 
		    {                                
			  //adding program logo image
			  File imageFile = new File("HomeBudget.jpg");
		      System.out.println(imageFile.exists());
		      image= ImageIO.read( imageFile );
		    }
		  catch(IOException ae){System.out.println("an problem to load the image");}	
		  ImageIcon ii=new ImageIcon(image);
		  //Creating the Label
		  JLabel jl=new JLabel("by Roman Mayerson,2009",ii,JLabel.CENTER);
		  //*************************************************************************
		  this.frame=frame;
		  PanelName=x;
		  cpf=new File("Data/"+userID+"/"+mf.yearid+"/"+"Check"+"_"+PanelName+".dat");//Checkpoint label showing us what table data was already saved
		
		  if(cpf.exists()==false)
		  { 
			//initial population of table in case Checkpoint.dat still not exist
		    String[]house={"Salary(your month income)","0"};
		    String[] house1={"Rent","0"};String[] house2={"Land Tax","0"}; 
		    String[] house3={"Water","0"};String[]house4={"Electricity","0"};
		    String[]house5={"Telephone","0"};
		    String[]house6={"Transport","0"};
		    String[]house7={"Visa(credit)","0"};
		    
		    mainTableData.add(house);
		    mainTableData.add(house1);mainTableData.add(house2);mainTableData.add(house3);
		    mainTableData.add(house4);mainTableData.add(house5);mainTableData.add(house6);
		    mainTableData.add(house7);}
		  else
		  {
		     //*************************************	                                                     
		     populateTable();  //populating collection of Main Table Data
		     //*************************************
		  }
		  //After collection was populated getting data from collection into data array which we use to create Table object
		  MainTableData2=new String [mainTableData.size()][2];
		  for (int i=0;i<mainTableData.size();i++)
		    {
			  //adding tables entries from collection into array
			  MainTableData2[i]=mainTableData.get(i);
			  //System.out.println(MainTableData2[i][0]); //check
		    }
		houseParams=new String[MainTableData2.length];// parameters names list used in ComboBox
		 for(int i=0;i<MainTableData2.length;i++)
		   {
		      houseParams[i]=(String) MainTableData2[i][0];
		   }
	    comboModel=new DefaultComboBoxModel(houseParams);
	    houseCombo=new JComboBox(comboModel);
	    
	    houseCombo.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent ae)
		    {
			  String selectedCombo=(String)houseCombo.getSelectedItem();
			  System.out.println("selected item:"+selectedCombo);
			  for(int i=0;i<MainTableData2.length;i++)
			    {
				  System.out.println("MainTableData2 data in Combo:");// CHECK MainTableData2 data in Combo
				  System.out.println(">"+MainTableData2[i][0]);  //MainTableData2 data in Combo
				  //System.out.println(selectedCombo+"equals to"+MainTableData2[i][0]+"is"+(((String)MainTableData2[i][0]).equals(selectedCombo)));
				  if (((String)MainTableData2[i][0]).equals(selectedCombo))
				   {
					 ///!!!
				     SelectedID=i;
			         //System.out.println("SelectedID:"+SelectedID);
				   }
				}		
		    }});
	    
	     typesFile=new File("types.txt");
	     typesCollect=new ArrayList<String> ();
	     if (typesFile.exists()==false)
	        {
	          typesList=new JList(PurchTypes);
	        }
	     else 
	       {
		       try
		         {
			       Scanner in=new Scanner(new FileReader(typesFile));
			       while(in.hasNextLine())
			         { 
				       String line = in.nextLine();
				       typesCollect.add(line);
			         }
	             }
		    catch(FileNotFoundException e){}
		    catch(IOException e){}
		     
		    PurchTypes=new String[typesCollect.size()];
		    for(int i=0;i<typesCollect.size();i++)
		      {
			    PurchTypes[i]=typesCollect.get(i);		
	          }
		    typesList=new JList(PurchTypes);
	       }
	     
	cpf_p=new File("Data/"+userID+"/"+mf.yearid+"/"+"Check"+"_"+PanelName+"_p.dat");//Checkpoint label showing us what table data was already saved
	if(cpf_p.exists()==true)
	  { 	
		 //*************************************	                                                     
		 populatePurchTable();  //populating collection of Purchases Table Data
		//*************************************
		//after table data was was populized from file into collection 	
		PurchTableData=new String [purchTableData.size()][3];
		for (int i=0;i<purchTableData.size();i++)
		  {   
			 //adding tables entries from collection into array
			 PurchTableData[i]=purchTableData.get(i);
			 //System.out.println(PurchTableData[i][0]); //check
	      }
	  }
	
	setBackground(Color.ORANGE);
	//innerPanel=new JPanel();
	//this.setLayout(new BorderLayout());
	 column1=Box.createVerticalBox();
	 column2=Box.createVerticalBox();
	 setVal=new JButton("SET Parameter Value");
	 purchase=new JButton("ADD new purchase");
	 purchase.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		   {
		      PurchDialog pdialog=new PurchDialog(frame,panel);
		      pdialog.setVisible(true);	
		   }
	    });
	  newType=new JButton("NEW TYPE");
	  
	  newType.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent ae)
		   {
			  NewTypeDialog ntd=new NewTypeDialog(frame,"Create New Type",panel);	
		   }
	   });
	  
	setPVal=new JButton("SET purchase value");
	setPVal.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		  {
			 setPurchValue SPVdialog=new setPurchValue(frame,panel);
			 SPVdialog.setVisible(true);
		  }
	});
	
	setVal.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		  {
		    HouseDialog dialog=new HouseDialog(frame,panel);
		    dialog.setVisible(true);	
		  }
		});
	
	AddEntry=new JButton("CREATE New Parameter");
	AddEntry.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		  {
			  HouseDialog2 dialog2=new HouseDialog2(frame,panel);
			  dialog2.setVisible(true);
		  }
	  });
	/*SaveChanges=new JButton("Save Changes"); //button depricated,but can be usefull for debugging
	SaveChanges.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			saveTable();			
		}
			
	*/ //});
	/*SaveChanges2=new JButton("Save Changes");  //button depricated,but can be usefull for debugging
	SaveChanges2.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){
			saveTable2();			
		}
			
	*/ //});
	Statistics=new JButton("GO TO STATISTICS");
	Statistics.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		  {
			graphframe=new graphFrame(panel,"Statistics");
			graphframe.setSize(1100,400);
			graphframe.setVisible(true);	
		  }
	});
	
	monthTotals=new JButton("MONTH TOTALS");
	monthTotals.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		  {
			  totalCalc=new totalCalculator("Month totals",panel);
			  totalCalc.setSize(1200,768);
			  totalCalc.setVisible(true);
		  }
	 });
	
	removeMT=new JButton("Remove parameter");
	removeMT.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		  {
			  RemoveParamDia rpd=new RemoveParamDia(frame,panel);
		  }
	 });
	
	removePT=new JButton("Remove purchase");
	removePT.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		  {
			  RemovePurchDia rpd=new RemovePurchDia(frame,panel);
		  }
	 });
	
	 salaryMsg=new JLabel("Enter your month salary");
	 salaryText= new JTextField(10);
     salaryLabel=new JLabel("SALARY:6000");
	
	 model1 = new DefaultTableModel(MainTableData2, MainTableColHeads);
	 model2 = new DefaultTableModel(PurchTableData,PurchTableColHeads);
		mainTable=new JTable(model1);
	    purchTable=new JTable(model2);
	    
	    column1.add(jl);
	    column1.add(yearID);
	    JLabel basicsLabel=new JLabel("House and Basics");
	    basicsLabel.setFont(new Font(yearID.getFont().getName(),yearID.getFont().getStyle(),15));
		column1.add(basicsLabel);
        column2.add(monthTotals);
		column2.add(Statistics);
		column2.add(newType);
		JLabel purchLabel=new JLabel("Purchases");
		purchLabel.setFont(new Font(yearID.getFont().getName(),yearID.getFont().getStyle(),15));
		column2.add(purchLabel);
		JScrollPane jsp=new JScrollPane( mainTable);
		column1.add(jsp);
		JScrollPane jsp2=new JScrollPane( purchTable);
		column2.add(jsp2);
		column2.add(purchase);
		column2.add(setPVal);
		column2.add(removePT);
		//column2.add(SaveChanges2); button depricated,but can be usefull for debugging
		column1.add(AddEntry);
		column1.add(setVal);
		//column1.add(SaveChanges); button depricated,but can be usefull for debugging
		//column1.add(salaryMsg);
		column1.add(removeMT);
		//column1.add(salaryText);
		//column1.add(salaryLabel);
		add(column1);
		add(column2);
		//System.out.println("Bingo!"); //debug
		
	}
//*********************************************************************************************************	
	public void saveTable()
	   {
		  try
		    { 
			   File f=new File("Data/"+userID+"/"+mf.yearid+"/MainData/"+PanelName+".txt");
			   System.out.println("Can write into file:"+f.canWrite());
			   //PrintWriter pw=new PrintWriter(new FileWriter(f));
			   BufferedWriter bw=new BufferedWriter(new FileWriter(f));
		       for(int j=0;j<MainTableData2.length;j++)
		         {
		            System.out.println(MainTableData2[j][0]+"/"+MainTableData2[j][1]);
		            //bw.write("writing test");
		            bw.write(MainTableData2[j][0]+"/"+MainTableData2[j][1]);
		            bw.newLine();
		         }
		       bw.close();
		       System.out.println("Your file has been written");
		
		       if (cpf.exists()==false)
		       {
			     ///If "Checkpoint.dat"  file not exist -create him
		         cpf.createNewFile();		
		       }
		    }catch(FileNotFoundException e){System.out.println("File not found");}
		     catch(IOException e){System.out.println("an problem is occured");}}
		
	
//*********************************************************************************************************
	public void saveTable2(){
		try
		  {
			 File f=new File("Data/"+userID+"/"+mf.yearid+"/PurchasesData/"+PanelName+"_p.txt");
			 System.out.println("Can write into file:"+f.canWrite());
			 //PrintWriter pw=new PrintWriter(new FileWriter(f));
			 BufferedWriter bw=new BufferedWriter(new FileWriter(f));
		     for(int j=0;j<PurchTableData.length;j++)
		       {
		         System.out.println(PurchTableData[j][0]+"/"+PurchTableData[j][1]+"/"+PurchTableData[j][2]);
		         //bw.write("writing test");
		         bw.write(PurchTableData[j][0]+"/"+PurchTableData[j][1]+"/"+PurchTableData[j][2]);
		         bw.newLine();
		       }
		     bw.close();
		     System.out.println("Your purchases file has been written");
		     if (cpf_p.exists()==false)
		       {
		    	 ///If "Checkpoint_p.dat"  file not exist -create him
		         cpf_p.createNewFile();		
		       }
		   }catch(FileNotFoundException e){System.out.println("File not found");}
		    catch(IOException e){System.out.println("an problem is occured");}}
		
	
//*********************************************************************************************************
	public void populateTable()
	{
		int i=0;//loop iteration variable
		try
		  {
		    Scanner in
		    = new Scanner(new FileReader("Data/"+userID+"/"+mf.yearid+"/MainData/"+PanelName+".txt"));
		    while(in.hasNextLine())
		      { 
		        String line = in.nextLine();
		        String[] tokens=line.split("/");
		        //String a=tokens[0];
		        //String b=tokens[1];
		        //String transfer[]={a,b};
		        //MainTableData2[i]=tokens;
		        //i++;
		        mainTableData.add(tokens);//populating our data colection,with tokens readed from the file
		      }
			}
		catch(FileNotFoundException e){}
		catch(IOException e){}
	}
	//*********************************************************************************************
	public void populatePurchTable()
	  {
		 int i=0;//loop iteration variable
		 try
		  {
		    Scanner in
		    = new Scanner(new FileReader("Data/"+userID+"/"+mf.yearid+"/PurchasesData/"+PanelName+"_p.txt"));
		    while(in.hasNextLine())
		      { 
		        String line = in.nextLine();
		        String[] tokens=line.split("/");
		        //String a=tokens[0];
		        //String b=tokens[1];
		        //String c=tokens[2];
		        //String transfer[]={a,b,c};
		        //MainTableData2[i]=tokens;
		        //i++;
		         purchTableData.add(tokens);//populating our data colection,with tokens readed from the file
		       }
			}
		catch(FileNotFoundException e){}
		catch(IOException e){}
				}
//**************************************************************************************************			

	//public void SetPanelName(String x){ ##This method are depricated,i've decided
	//	PanelName=x;                      ##To add this function in the class constructor
	//}
	
}
