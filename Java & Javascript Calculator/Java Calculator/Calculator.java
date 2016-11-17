import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
public class Calculator extends JFrame{
	private JTextField resultField;
	
	private JButton one, two, three, four, five, six, seven, eight, nine, zero; // Numbers
	private JButton add, subtract, multiply, div, equals; // Operators
	private JButton dec, negative;
	private JButton clear;
	
	private String num1, num2, tResult;
	private String operation = "";
	
	private double result = 0.0;
	private JPanel contentPanel;
	private boolean isClicked = false, opClicked = false, equalsClicked = false;
	
	
	public Calculator() {
		super("Calculator");
		
		resultField = new JTextField(null,22);
		resultField.setEditable(false);
		
		JButton[] arr = new JButton[10];
		for(int i=0; i<10; i++){
			arr[i] = new JButton(""+i);
		}

		dec = new JButton(".");
		negative = new JButton("+/-");
		
		add = new JButton("+");
		subtract = new JButton("-");
		multiply = new JButton("*");
		div = new JButton("/");
		equals = new JButton("=");
		
		clear = new JButton("C");
		clear.setForeground(Color.GREEN);
		
		Dimension dim = new Dimension(60, 30);
		Dimension small = new Dimension(40, 30);
		
		
		for(int i=0; i<10; i++) {
			arr[i].setPreferredSize(dim);
		}

		dec.setPreferredSize(dim);
		
		Dimension operatorDim = new Dimension(80, 30);
		Dimension clearDim = new Dimension(125, 30);
		add.setPreferredSize(operatorDim);
		subtract.setPreferredSize(operatorDim);
		multiply.setPreferredSize(operatorDim);
		div.setPreferredSize(operatorDim);
		equals.setPreferredSize(clearDim);
		
		clear.setPreferredSize(clearDim);
		negative.setPreferredSize(operatorDim);

		
		
		Numbers n = new Numbers();
		Operators o = new Operators();
		
		for(int i=0; i<arr.length;i++) {
			arr[i].addActionListener(n);
		}
		
		dec.addActionListener(n);
		negative.addActionListener(n);
		
		// ActionListners for operators
		add.addActionListener(o); subtract.addActionListener(o); multiply.addActionListener(o); div.addActionListener(o);
		equals.addActionListener(o); clear.addActionListener(o); 
		
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setLayout(new FlowLayout());
		
		contentPanel.add(resultField, BorderLayout.NORTH);
		contentPanel.add(arr[1]); contentPanel.add(arr[2]); contentPanel.add(arr[3]); contentPanel.add(add); contentPanel.add(arr[4]); contentPanel.add(arr[5]);
		contentPanel.add(arr[6]); contentPanel.add(subtract); contentPanel.add(arr[7]); contentPanel.add(arr[8]); contentPanel.add(arr[9]); contentPanel.add(multiply); 
		contentPanel.add(arr[0]); contentPanel.add(equals); contentPanel.add(div); contentPanel.add(dec); contentPanel.add(clear); contentPanel.add(negative);
		resultField.setHorizontalAlignment(SwingConstants.RIGHT);
		
		Border padding = BorderFactory.createEmptyBorder(20, 0, 0, 0);
		contentPanel.setBorder(padding);
		this.setContentPane(contentPanel);
	}
	
	private class Numbers implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton src = (JButton)e.getSource();
			String num = src.getText();
			
			if(opClicked!=true) {
				if(src.equals(negative)) {
					if(num1==null) {
						num1 = "-";
					} else if(num1!=null && num1.startsWith("-")) {
						num1 = num1.substring(1); 
					} else {
						num1 = "-"+num1;
					}
				} else {
					if(num1==null) {
						num1 = num;
					} else {
						num1 += num;
					}
				}
				
				
			} else {
				if(src.equals(negative)) {
					if(num2==null) {
						num2 = "-";
					} else if(num2!=null && num2.startsWith("-")) {
						num2 = num2.substring(1);
					} else {
						num2 = "-"+num2;
					}
				} else {
					if(num2==null) {
						num2 = num;
					} else {
						num2 += num;
					}
				}
			}
			
			// Decimal
			if(src.equals(dec)) {
				if(opClicked!=true) {
					if(num1==null) {
						num1 = "0.";
					} else {
						if(num1!=null) {
							if(num1.contains(".")) {
								resultField.setText("Decimal point already exists");
							} else {
								num1 += ".";
							}
						}
					}
				} else {
					if(num2==null) {
						num2 = "0.";
					} else {
						if(num2!=null) {
							if(num2.contains(".")) {
								resultField.setText("Decimal point already exists");
							} else {
								num2 += ".";
							}
						}
					}
				}
			}
			
			if(equalsClicked==false) {
				if(opClicked!=true) {
					resultField.setText(num1);
				} else {
					resultField.setText(num2);
				}
			}
			
		}
	}
	
	
	private class Operators implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton src = (JButton)e.getSource();
			
			if(src.equals(add)) {
				if(num1==null) {
					resultField.setText("Input your first number");
				} else {
					if(num1!=null && num2==null) {
						opClicked = true;
						operation = "+";
					} else {
						if(num1!=null && num2!=null) {
							resultField.setText("Two operands only!");
						}
					}
				}
			}
			
			if(src.equals(subtract)) {
				if(num1==null) {
					resultField.setText("Input your first operand");
				} else {
					if(num1!=null && num2==null) {
						opClicked = true;
						operation = "-";
					} else {
						if(num1!=null && num2!=null) {
							resultField.setText("Two operands only!");
						}
					}
				}
			}
			
			if(src.equals(multiply)) {
				if(num1==null) {
					resultField.setText("Input your first operand");
				} else {
					if(num1!=null && num2==null) {
						opClicked = true;
						operation = "*";
					} else {
						if(num1!=null && num2!=null) {
							resultField.setText("Two operands only!");
						}
					}
				}
			}
			
			if(src.equals(div)) {
				if(num1==null) {
					resultField.setText("Input your first operand");
				} else {
					if(num1!=null && num2==null) {
						opClicked = true;
						operation = "/";
					} else {
						if(num1!=null && num2!=null) {
							resultField.setText("Two operands only!");
						}
					}
				}
			}
			
			if(src.equals(equals)) {
				if(num1==null) {
					resultField.setText("Input first operand");
				} else {
					if(num1!=null && num2==null) {
						resultField.setText("Input second operand");
					} else {
						opClicked = true;
						equalsClicked = true;
						
						double d1 = 0.0, d2 = 0.0;
						d1 = Double.parseDouble(num1);
						d2 = Double.parseDouble(num2);
						
						switch(operation) {
						case "+":
							result = d1+d2;
							break;
						case "-":
							result = d1-d2;
							break;
						case "*":
							result = d1*d2;
							break;
						case "/":
							result = d1/d2;
							break;
						}
						
						
						tResult = Double.toString(result);
						resultField.setText(tResult);
						if(operation=="/" && d2==0.0) {
							resultField.setText("DIVISION BY ZERO ERROR");;
						}
					}
				}
			}
			
			//Clear 
			if(src.equals(clear)) {
				num1 = null;
				num2 = null;
				equalsClicked = false;
				opClicked = false;
				operation = " ";
				resultField.setText(null);
				tResult = null;
			}
		}
		
	}
	

}
