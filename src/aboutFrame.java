import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class aboutFrame extends JFrame {
	aboutFrame afPointer;
	
	aboutFrame()
	  {
        super("About Home Budget");
        afPointer=this;
		Box vbox=Box.createVerticalBox();
		setLayout(new FlowLayout());
		JLabel demolabel=new JLabel("demo");
		JLabel label1=new JLabel("Home Budget v 1.1 beta ");
		JLabel label2=new JLabel("Development,Programming and Design");
		JLabel label3=new JLabel("by Roman Mayerson/August 2009" );
		JLabel label4=new JLabel("                                   ");
		JLabel label5=new JLabel(("'Home Budget'"));
		JLabel label6=new JLabel("is crossplatform software based on Java 1.6 technology");
		JLabel label7=new JLabel("It provides database and set of  visual tools for tracking an personal home budget");
		JLabel label8=new JLabel("                              ");
		JLabel label9=new JLabel("*You can create your own data types in order to  sort your data by them");
		JLabel label10=new JLabel("*You can use graphs and tables for analyzing your incomes and outcomes");
		JLabel label11=new JLabel("*The program have multiuser interface");
		JLabel label12=new JLabel("*You can import and export the user data into and from the program");

		label1.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		label2.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		label3.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		label4.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		label5.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		label6.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		label7.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		label8.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		label9.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		label10.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		label11.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		label12.setFont(new Font(demolabel.getFont().getName(),demolabel.getFont().getStyle(),16));
		//System.out.println(demolabel.getFont().getName()+","+demolabel.getFont().getStyle());

		vbox.add(label1);
		vbox.add(label2);
		vbox.add(label3);
		vbox.add(label4);
		vbox.add(label5);
		vbox.add(label6);
		vbox.add(label7);
		vbox.add(label8);
		vbox.add(label9);
		vbox.add(label10);
		vbox.add(label11);
		vbox.add(label12);
		add(vbox);
		
		JButton close=new JButton("Close");
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			   {
				 afPointer.dispose();
				 afPointer.setVisible(false);
			   }
		   });
		
		add(close);
		setSize(800,600);
		setVisible(true);
		
	}

}
