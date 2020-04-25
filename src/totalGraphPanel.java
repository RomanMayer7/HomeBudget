import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;

public class totalGraphPanel extends JPanel
   {
	  String[][] totalValues;
	  Graphics g;
	  int corZerox;
	  int corZeroy;
	  int axisYlen;
	  int axisXlen;
	  int xscales[];
	  int yscales[];
	  int xdelta,ydelta;
	  int xscale,yscale;
	
	 totalGraphPanel(String[][] totalValues){
	 this.totalValues=sortValues(totalValues);//sorting values by ASC order
	
	 System.out.println("After sorting");//DEBUGGING PART
	 for(int k=0;k<totalValues.length;k++)
	    { 	
	      System.out.println(totalValues[k][0]+";"+totalValues[k][1]);
	    }
	
	 setBorder(BorderFactory.createLineBorder(Color.RED,5));
	 corZerox=30;
	 corZeroy=560;
	 xscale=5;
	 xdelta=40;
	 yscale=5;
	 ydelta=20;
	 axisYlen=520;
	 axisXlen=xdelta*totalValues.length+25;
	
	 xscales=new int[totalValues.length+1];//
	 yscales=new int[26];
	 
	 for(int i=0;i<26;i++)
	   {
		 yscales[i]=corZeroy-i*ydelta;
	   }
	 for(int i=0;i<totalValues.length+1;i++)
	   {
		 xscales[i]=corZerox+i*xdelta;
	   }
	}
	
	public void paintComponent(Graphics g)
	{
		//The superclass method is always calling first
		super.paintComponent(g);
		g.drawLine(corZerox,corZeroy,corZerox,corZeroy-axisYlen);
		g.drawLine(corZerox, corZeroy, corZerox+axisXlen, corZeroy);
		for(int i=0;i<totalValues.length+1;i++)
		  {
		     g.drawLine(xscales[i],corZeroy+xscale,xscales[i],corZeroy-xscale);
		  }
		for(int i=0;i<26;i++)
		  {
		     g.drawLine(corZerox+yscale, yscales[i], corZerox-yscale, yscales[i]);
		  }
		for(int i=0;i<25;i++)
		  {
		     g.setColor(Color.RED);	
		     g.drawString(""+320*(i+1),corZerox-20,yscales[i+1]);
		  }
		g.drawString("money units", corZerox,corZeroy-520 );
		g.drawString("Parameter",xscales[totalValues.length],corZeroy+25);
		g.drawLine(corZerox+axisXlen, corZeroy, corZerox+axisXlen-xscale*2,corZeroy-xscale*2);//drawing arrow X
		g.drawLine(corZerox+axisXlen, corZeroy, corZerox+axisXlen-xscale*2, corZeroy+xscale*2);//.....
		g.drawLine(corZerox, corZeroy-axisYlen, corZerox+xscale*2, corZeroy-axisYlen+xscale*2);
		g.drawLine(corZerox, corZeroy-axisYlen, corZerox-xscale*2, corZeroy-axisYlen+xscale*2);	
		
		for(int i=0;i<totalValues.length;i++)
		  {
			 g.fillRect(xscales[i],corZeroy-(Integer.parseInt(totalValues[i][1])/16),xdelta,(Integer.parseInt(totalValues[i][1])/16));
			 g.drawString(totalValues[i][0], xscales[i],corZeroy-(Integer.parseInt(totalValues[i][1])/16)-20-i*10);
			 g.drawString(totalValues[i][1], xscales[i], corZeroy+15);
		  }
	}
		
//****************************************************	sorting two dimensional array by the second index value
String[][] sortValues(String[][] valuesToSort)
  {
	  String[][] sortedValues=new String[valuesToSort.length][2];
      sortedValues=valuesToSort;
      for(int i=0;i<sortedValues.length;i++)
        { 
    	  //DEBUGGING PART	
          System.out.println(sortedValues[i][0]+";"+sortedValues[i][1]);
        }
	  for(int i=0;i<sortedValues.length-1;i++)
	    {
		    for(int j=0;j<(sortedValues.length-1-i);j++)
		    {
			   String[] MaxEntry=sortedValues[sortedValues.length-1-i];
		       if((Integer.parseInt(sortedValues[j][1]))>(Integer.parseInt(MaxEntry[1])))
		         {
			       String[] tempEntry=sortedValues[j];
			       sortedValues[j]=MaxEntry;
			       sortedValues[sortedValues.length-1-i]=tempEntry;
		         }
	        }
         }
       return sortedValues;

  }
//**************************************************************************************************************
}
