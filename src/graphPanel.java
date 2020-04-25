import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;
public class graphPanel extends JPanel
{
	Graphics g;
	int corZerox;
	int corZeroy;
	int axisYlen;
	int axisXlen;
	int xscales[];
	int yscales[];
	int xdelta,ydelta;
	int xscale,yscale;
	graphPanel()
	{
	   setBorder(BorderFactory.createLineBorder(Color.RED,5));
	   corZerox=30;
	   corZeroy=280;
	   axisYlen=260;
	   axisXlen=260;
	   xscale=5;
	   xdelta=20;
	   yscale=5;
	   ydelta=20;
	   xscales=new int[15];//creating 12 month +2 elements array because of the drawLine from x-coordinates (n+1;n+2)
	   yscales=new int[13];
	   for(int i=0;i<13;i++)
	     {
		    xscales[i]=corZerox+i*xdelta;
		    yscales[i]=corZeroy-i*ydelta;
	     }
	}
	public void paintComponent(Graphics g)
	{
		//The superclass method is always called first
		super.paintComponent(g);
		g.drawLine(corZerox,corZeroy,corZerox,corZeroy-axisYlen);
		g.drawLine(corZerox, corZeroy, corZerox+axisXlen, corZeroy);
		for(int i=0;i<13;i++)
		  {
		     g.drawLine(xscales[i],corZeroy+xscale,xscales[i],corZeroy-xscale);
		     g.drawLine(corZerox+yscale, yscales[i], corZerox-yscale, yscales[i]);
		  }
		for(int i=0;i<12;i++)
		  {
		     g.setColor(Color.RED);	
		     g.drawString(i+1+"", xscales[i+1],corZeroy+15);
		     g.drawString(""+320*(i+1),corZerox-20,yscales[i+1]);
		     g.drawString("money units", corZerox,corZeroy-260 );
		     g.drawString("month",xscales[11],corZeroy+25);
		  }
		g.drawLine(corZerox+axisXlen, corZeroy, corZerox+axisXlen-xscale*2,corZeroy-xscale*2);//drawing arrow X
		g.drawLine(corZerox+axisXlen, corZeroy, corZerox+axisXlen-xscale*2, corZeroy+xscale*2);//.....
		g.drawLine(corZerox, corZeroy-axisYlen, corZerox+xscale*2, corZeroy-axisYlen+xscale*2);
		g.drawLine(corZerox, corZeroy-axisYlen, corZerox-xscale*2, corZeroy-axisYlen+xscale*2);
		g.setColor(Color.BLUE);
		for(int j=0;j<12;j++)
		  {
			g.setColor(Color.BLUE);
			g.drawLine(xscales[j+1],corZeroy-(int)graphFrame.ParamValues[j]/16,xscales[j+2],corZeroy-(int)graphFrame.ParamValues[j+1]/16);
			g.setColor(Color.RED);
			g.drawString(""+(int)(graphFrame.ParamValues[j]),xscales[j+1],corZeroy-(int)graphFrame.ParamValues[j]/16);
			System.out.println(corZerox+","+corZeroy);
			System.out.println(xscales[j+1]+","+(corZeroy-graphFrame.ParamValues[j]/16)+";"+xscales[j+2]+","+(corZeroy-graphFrame.ParamValues[j+1]/16));			
			//g.setFont(new Font("Helvetica",Font.ROMAN_BASELINE,12));
		  }
		g.setFont(new Font("Helvetica",Font.BOLD,18));
		g.drawString(""+graphFrame.paramName, corZerox+50, corZeroy+50);
		}

	}
