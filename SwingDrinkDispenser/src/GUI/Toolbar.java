package GUI;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Toolbar extends JPanel implements ActionListener {
	private JButton fillDrinks;
	private JButton fillCoins;
	
	private ToolbarListener toolbarListener;
	
	public Toolbar(){
		setBorder(BorderFactory.createEtchedBorder());
		
		fillDrinks = new JButton("Fill Drink Storage");
		fillCoins = new JButton("Fill Coin Storage");
		
		fillDrinks.addActionListener(this);
		fillCoins.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(fillDrinks);
		add(fillCoins);
		
	}
	
	public void setToolbarListener(ToolbarListener listener){
		this.toolbarListener = listener;
	}
	
	public void actionPerformed(ActionEvent e){
		JButton clicked = (JButton)e.getSource() ;
		
		if(clicked == fillDrinks){
			if(toolbarListener != null){
				toolbarListener.performMaintenance("Fill Drink Storage\n");
			}
		}
		
		else if(clicked == fillCoins){
			 if(toolbarListener != null){
				 toolbarListener.performMaintenance("Fill Coin Storage\n");
			 }
		}
	}
}
