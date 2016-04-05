package GUI;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class FormPanel extends JPanel{

	private JLabel coinLabel;
	private JLabel billLabel;
	private JLabel cardLabel;
	private JTextField coinField;
	private JTextField billField;
	private JTextField cardPINField;
	private JButton select;
	private JButton deselect;
	private JButton payByMoney;
	private JButton payByCard;
	private JButton bankPay;
	private DrinkListener drinkListener;
	private DrinkDeselectListener drinkDeselectListener;
	private MoneyListener moneyListener;
	private CardListener cardListener;
	private JList drinkList;
	
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		coinLabel = new JLabel("Coin: ");
		billLabel = new JLabel("Bill: ");
		cardLabel = new JLabel("Card Slot: ");
		coinField = new JTextField(10);
		billField = new JTextField(10);
		cardPINField = new JTextField(10);
		drinkList = new JList();
		
		DefaultListModel drinkModel = new DefaultListModel();
		drinkModel.addElement(new DrinkCategory(0, "Coke"));
		drinkModel.addElement(new DrinkCategory(1, "Sprite"));
		drinkModel.addElement(new DrinkCategory(2, "Beer"));
		drinkModel.addElement(new DrinkCategory(3, "LightBeer"));
		drinkModel.addElement(new DrinkCategory(4, "FunBeer"));
		drinkModel.addElement(new DrinkCategory(5, "MineralWater"));
		drinkModel.addElement(new DrinkCategory(6, "AppleJuice"));
		drinkModel.addElement(new DrinkCategory(7, "OrangeJuice"));
		drinkModel.addElement(new DrinkCategory(8, "TomatoJuice"));
		drinkModel.addElement(new DrinkCategory(9, "Wine"));
		drinkList.setModel(drinkModel);
		
		drinkList.setPreferredSize(new Dimension(110, 190));
		drinkList.setBorder(BorderFactory.createEtchedBorder());
		drinkList.setSelectedIndex(1);
		
		select = new JButton("Select");
		
		select.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DrinkCategory drinkCat = (DrinkCategory)drinkList.getSelectedValue();
				System.out.println(drinkCat.getId());
				
				DrinkSelectionEvent ev = new DrinkSelectionEvent(this, drinkCat.getId());
				
				if(drinkListener != null){
					drinkListener.drinkSelectionOccurred(ev);
				}
			}
		});
		
		
		deselect = new JButton("Deselct");
		
		deselect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DrinkCategory drinkCat = (DrinkCategory) drinkList.getSelectedValue();
				System.out.println(drinkCat.getId());
				
				DrinkDeselectEvent ev = new DrinkDeselectEvent(this, drinkCat.getId());
				if(drinkDeselectListener != null){
					drinkDeselectListener.drinkDeselectionOccurred(ev);
					
				}
			}
		});
		
		
		payByMoney = new JButton("Pay");
		
		payByMoney.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String coinName = coinField.getText();
				String billName = billField.getText();
				
				PayingEvent ev = new PayingEvent(this, coinName, billName);
				
				if(moneyListener != null){
					moneyListener.paymentOccurred(ev);
				}
			}
		});
		
		bankPay = new JButton("BankPay");
		bankPay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String pinCode = cardPINField.getText();
				BankEvent ev = new BankEvent(this, pinCode);
				
				if(cardListener != null){
					cardListener.cardInsertionOccurred(ev);
				}
			}
		});
		
		
		Border innerBorder = BorderFactory.createTitledBorder("Drink Control Panel");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc =  new GridBagConstraints();
		
		
		///////////////////////////First Row////////////////////////////////////////////////
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		
		add(coinLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(coinField, gc);
		
		///////////////////////////Second Row////////////////////////////////////////////////
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridy = 1;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(billLabel, gc);
		
		gc.gridy = 1;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(billField, gc);
		
		
		///////////////////////////Third Row////////////////////////////////////////////////
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridy = 2;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(drinkList, gc);
		
		
		///////////////////////////Fourth Row////////////////////////////////////////////////
		gc.weightx = 1;
		gc.weighty = 2.0;
		
		gc.gridy = 3;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(select, gc);
		
		gc.weightx = 1;
		gc.weighty = 2.0;
		
		gc.gridy = 3;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(deselect, gc);
		
		///////////////////////////Fifth Row////////////////////////////////////////////////
		gc.weightx = 1;
		gc.weighty = 2.0;
		
		gc.gridy = 4;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(payByMoney, gc);
		
		
		///////////////////////////Sixth Row////////////////////////////////////////////////
		gc.weightx = 1;
		gc.weighty = 2.0;
		
		gc.gridy = 5;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(cardLabel, gc);
		
		gc.weightx = 1;
		gc.weighty = 2.0;
		
		gc.gridy = 5;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(cardPINField, gc);
		
		///////////////////////////7th Row////////////////////////////////////////////////
		
		gc.weightx = 1;
		gc.weighty = 2.0;
		
		gc.gridy = 6;
		gc.gridx = 0;
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(bankPay, gc);
	
	}

	public void setDrinkListener(DrinkListener listener){
		this.drinkListener = listener;
	}
	
	public void setDrinkDeselectListener(DrinkDeselectListener listener){
		this.drinkDeselectListener = listener;
	}
	
	public void setMoneyListener(MoneyListener listener){
		this.moneyListener = listener;
	}
	
	public void setCardListener(CardListener listener){
		this.cardListener = listener;
	}
	
	public void clearFields(){
		coinField.setText("");
		billField.setText("");
	}
	
	public JTextField getCoinField(){
		return coinField;
	}
	
	public JTextField getBillField(){
		return billField;
	}


}
