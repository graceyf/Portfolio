/**
 * Capstone Project
 * GUI.java
 * 
 * Grace Zhu
 * 
 * May 01, 2017
 */



// Imports
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javafx.scene.chart.LineChart;
import static thorwin.math.Math.polyfit;
import static thorwin.math.Math.polynomial;


@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener, WindowListener {
    
	// instance variables
    private final JFileChooser openFileChooser;
    private File originalCSV;
    private String[] header = null;
    private int row = 0;
    private int column = 0;
    private String[][] data = null;
    private int[][] dataInt = null;
    private final JFXPanel fxPanel = new JFXPanel();
    private LineChart<Number,Number> sc = null;
    private double[] xArray;
    private double[] yArray;
    
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        
        // create a new FileChoose, set the default path to "c:\\temp", and set the default type to be csv file
        openFileChooser = new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("c:\\temp"));
        openFileChooser.setFileFilter(new FileNameExtensionFilter("csv","CSV"));
    }

    /**
     * # NetBeans
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */                        
    private void initComponents() {
    	
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        plotButton = new javax.swing.JButton();
        RB1 = new javax.swing.JRadioButton();
        RB2 = new javax.swing.JRadioButton();
        RB3 = new javax.swing.JRadioButton();
        RB4 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        binTF = new javax.swing.JTextField();
        bin = new javax.swing.JLabel();
        yCB = new javax.swing.JComboBox<>();
        xCB = new javax.swing.JComboBox<>();
        xvar = new javax.swing.JLabel();
        yvar = new javax.swing.JLabel();
        
        fileChooser = new javax.swing.JButton();
        headerChecker = new javax.swing.JCheckBox();
        importButton = new javax.swing.JButton();
        messageLabel1 = new javax.swing.JLabel();
        messageLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        degree = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Predictive Analysis Program");
        setResizable(false);

        plotButton.setText("Plot");
        plotButton.setEnabled(false);
        plotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(RB1);
        RB1.setText("Linear Regression");
        RB1.setEnabled(false);
        RB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(RB2);
        RB2.setText("Polynomial Regression");
        RB2.setEnabled(false);
        
        buttonGroup1.add(RB3);
        RB3.setText("Logistic Regression");
        RB3.setEnabled(false);
        RB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB3ActionPerformed(evt);
            }
        });

        @SuppressWarnings("rawtypes")
		org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, degree, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), RB2, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        RB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(RB4);
        RB4.setText("Moving Average Analysis");
        RB4.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, binTF, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), RB4, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        RB4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB4ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        binTF.setEnabled(false);
        binTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binTFActionPerformed(evt);
            }
        });

        bin.setText("Avg Period:");
        bin.setEnabled(false);

        yvar.setText("Y Variable:");
        yvar.setEnabled(false);

        yCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        yCB.setEnabled(false);
        yCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yCBActionPerformed(evt);
            }
        });

        xCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        xCB.setEnabled(false);
        xCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xCBActionPerformed(evt);
            }
        });

        xvar.setText("X Variable:");
        xvar.setEnabled(false);

        fileChooser.setText("Select File");
        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

        headerChecker.setText("First row as header?");
        headerChecker.setEnabled(false);
        headerChecker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headerCheckerActionPerformed(evt);
            }
        });

        importButton.setText("Import");
        importButton.setEnabled(false);
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });

        messageLabel1.setText("Please select a file");

        jLabel1.setText("Degree:");
        jLabel1.setEnabled(false);

        degree.setEnabled(false);
        degree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                degreeActionPerformed(evt);
            }
        });

        jLabel2.setText("Predictive Period:");
        jLabel2.setEnabled(false);

        jTextField1.setText("1");
        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(plotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(messageLabel1)
                                    .addGap(25, 25, 25)
                                    .addComponent(messageLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(25, 25, 25)
                                    .addComponent(headerChecker)
                                    .addGap(25, 25, 25)
                                    .addComponent(importButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(yCB, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(xCB, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(xvar)
                                    .addComponent(yvar)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(RB1)
                                                .addComponent(RB2))
                                            .addGap(0, 0, 0))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(10, 10, 10)
                                            .addComponent(jTextField1))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(0, 0, 0)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(RB4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jLabel1)
                                                    .addGap(15, 15, 15)
                                                    .addComponent(degree, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(bin)
                                .addGap(10, 10, 10)
                                .addComponent(binTF, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(yvar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(yCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(xvar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(xCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(RB1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(RB2)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(degree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(RB4)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(binTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bin))
                        .addGap(10, 10, 10)
                        .addComponent(plotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(importButton, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(headerChecker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(messageLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(messageLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>                        

    private void RB1ActionPerformed(java.awt.event.ActionEvent evt) {                                    
    }                                   

    private void binTFActionPerformed(java.awt.event.ActionEvent evt) {                                      
    }                                     

    private void yCBActionPerformed(java.awt.event.ActionEvent evt) {                                    
    }                                   

    private void headerCheckerActionPerformed(java.awt.event.ActionEvent evt) {                                              
    }                                             

    private void RB2ActionPerformed(java.awt.event.ActionEvent evt) {                                    
    }                                   

    private void xCBActionPerformed(java.awt.event.ActionEvent evt) {                                    
    }                                   

    private void RB4ActionPerformed(java.awt.event.ActionEvent evt) {                                    

    }                                   

    private void RB3ActionPerformed(java.awt.event.ActionEvent evt) {                                    
    }                                                                                                                    

    private void degreeActionPerformed(java.awt.event.ActionEvent evt) {                                       
    }                                      

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
    }     
    
    
    // file chooser
    private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {                                            
    	int returnValue = openFileChooser.showOpenDialog(this);

    	if (returnValue == JFileChooser.APPROVE_OPTION)
    	{
    		originalCSV = openFileChooser.getSelectedFile();
    		messageLabel1.setText("Upload Success!"); 

    		// disable all the buttons
    		this.jPanel1.setEnabled(false);
    		this.RB1.setEnabled(false);
    		this.RB2.setEnabled(false);
    		this.RB3.setEnabled(false);
    		this.RB4.setEnabled(false);
    		this.yCB.setEnabled(false);
    		this.xCB.setEnabled(false);
    		this.yvar.setEnabled(false);
    		this.xvar.setEnabled(false);
    		this.bin.setEnabled(false);
    		this.plotButton.setEnabled(false);
    		this.jLabel2.setEnabled(false);
    		this.jTextField1.setEnabled(false);
    		this.jLabel1.setEnabled(false);
    		this.headerChecker.setEnabled(true);
    		this.importButton.setEnabled(true);
    		this.messageLabel2.setText("");
    		plotButton.addActionListener(this);
    		buttonGroup1.clearSelection();
    		fxPanel.setVisible(false);
    	}
    	else
    	{       
    		messageLabel1.setText("No file Chosen");

    		// disable all the buttons
    		this.headerChecker.setEnabled(false);
    		this.importButton.setEnabled(false);
    		this.jPanel1.setEnabled(false);
    		this.RB1.setEnabled(false);
    		this.RB2.setEnabled(false);
    		this.RB3.setEnabled(false);
    		this.RB4.setEnabled(false);
    		this.yCB.setEnabled(false);
    		this.xCB.setEnabled(false);
    		this.yvar.setEnabled(false);
    		this.xvar.setEnabled(false);
    		this.bin.setEnabled(false);
    		this.plotButton.setEnabled(false);
    		this.jLabel1.setEnabled(false);
    		this.jLabel2.setEnabled(false);
    		this.jTextField1.setEnabled(false);
    		this.messageLabel2.setText("");
    		buttonGroup1.clearSelection();
    		fxPanel.setVisible(false);
    	}
    }                                           

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	String line = "";
    	String cvsSplitBy = ",";   
    	boolean flag = true;
    	row = 0;
    	column = 0;

    	try (BufferedReader br = new BufferedReader(new FileReader(originalCSV))) {

    		while ((line = br.readLine()) != null) {
    			String[] country = line.split(cvsSplitBy);
    			row++;
    			column = country.length;
    		}
    		//System.out.println(row+"**"+column);
    	} catch (IOException e) {
    	}

    	// first row as header? NO
    	if(!headerChecker.isSelected())
    	{
    		data = new String[row][column];
    		dataInt = new int[row][column];
    		try (BufferedReader br = new BufferedReader(new FileReader(originalCSV))) {
    			int count = 0;
    			String[] country = null;
    			while ((line = br.readLine()) != null) {
    				if(count == 0)
    				{
    					country = line.split(cvsSplitBy);
    					String[] defaultHeader = country;

    					for (int i = 1; i <= defaultHeader.length; i++)
    					{
    						defaultHeader[i-1] = "column"+ i;
    					}
    					header = defaultHeader;
    				}
    				else
    				{
    					country = line.split(cvsSplitBy);
    				}

    				data[count]=country;
    				count++;
    			}

    			for (int i = 0; i< data.length; i++)
    			{
    				for (int j = 0; j < data[i].length;j++)
    				{
    					if(data[i][j].matches("[0-9]+"))
    					{
    						dataInt[i][j] = Integer.parseInt(data[i][j]);
    					}
    					else
    					{
    						flag = false;// false when the file is not numerical value
    					}
    				}
    			}    


    			if(flag)
    			{
    				messageLabel2.setText("Import Success!"); 
    				yCB.setModel(new DefaultComboBoxModel<String>(header));
    				xCB.setModel(new DefaultComboBoxModel<String>(header));

    				// enable all the buttons
    				this.jPanel1.setEnabled(true);
    				this.RB1.setEnabled(true);
    				this.RB2.setEnabled(true);
    				this.RB3.setEnabled(true);
    				this.RB4.setEnabled(true);
    				this.yCB.setEnabled(true);
    				this.xCB.setEnabled(true);
    				this.yvar.setEnabled(true);
    				this.xvar.setEnabled(true);
    				this.bin.setEnabled(true);
    				this.jLabel1.setEnabled(true);
    				this.plotButton.setEnabled(true);
    				this.jLabel2.setEnabled(true);
    				this.jTextField1.setEnabled(true);
    				buttonGroup1.clearSelection();
    			}
    			else
    			{
    				messageLabel2.setText("Wrong type of file!  *Must be csv with all numerical values"); 
    				yCB.setModel(new DefaultComboBoxModel<String>(header));
    				xCB.setModel(new DefaultComboBoxModel<String>(header));

    				// disable all the buttons
    				this.jPanel1.setEnabled(false);
    				this.RB1.setEnabled(false);
    				this.RB2.setEnabled(false);
    				this.RB3.setEnabled(false);
    				this.RB4.setEnabled(false);
    				this.yCB.setEnabled(false);
    				this.xCB.setEnabled(false);
    				this.yvar.setEnabled(false);
    				this.xvar.setEnabled(false);
    				this.bin.setEnabled(false);
    				this.jLabel1.setEnabled(false);
    				this.plotButton.setEnabled(false);
    				this.jLabel2.setEnabled(false);
    				this.jTextField1.setEnabled(false);
    				buttonGroup1.clearSelection();
    			}
    		} catch (IOException e) {
    		}
    	}
    	else // first row as header? YES
    	{	
    		data = new String[row-1][column];
    		dataInt = new int[row-1][column];
    		try (BufferedReader br = new BufferedReader(new FileReader(originalCSV))) { 
    			int count = 0;
    			String[] country = null;
    			while ((line = br.readLine()) != null) {
    				if(count == 0)
    				{
    					country = line.split(cvsSplitBy);
    					header = country;
    				}
    				else
    				{
    					country = line.split(cvsSplitBy);                          
    					data[count-1] = country;
    				}
    				count++;
    			}
    			for (int i = 0; i< data.length; i++)
    			{
    				for (int j = 0; j < data[i].length;j++)
    				{
    					if(data[i][j].matches("[0-9]+"))
    					{
    						dataInt[i][j] = Integer.parseInt(data[i][j]);
    					}
    					else
    					{
    						flag = false;
    					}
    				}
    			}    
    			if(flag)
    			{
    				messageLabel2.setText("Import Success!"); 
    				yCB.setModel(new DefaultComboBoxModel<String>(header));
    				xCB.setModel(new DefaultComboBoxModel<String>(header));
    				this.jPanel1.setEnabled(true);
    				this.RB1.setEnabled(true);
    				this.RB2.setEnabled(true);
    				this.RB3.setEnabled(true);
    				this.RB4.setEnabled(true);
    				this.yCB.setEnabled(true);
    				this.xCB.setEnabled(true);
    				this.yvar.setEnabled(true);
    				this.xvar.setEnabled(true);
    				this.bin.setEnabled(true);
    				this.jLabel1.setEnabled(true);
    				this.plotButton.setEnabled(true);
    				this.jLabel2.setEnabled(true);
    				this.jTextField1.setEnabled(true);
    				buttonGroup1.clearSelection();
    			}
    			else
    			{
    				messageLabel2.setText("Wrong type of file!  *Must be csv with all numerical values"); 
    				yCB.setModel(new DefaultComboBoxModel<String>(header));
    				xCB.setModel(new DefaultComboBoxModel<String>(header));
    				this.jPanel1.setEnabled(false);
    				this.RB1.setEnabled(false);
    				this.RB2.setEnabled(false);
    				this.RB3.setEnabled(false);
    				this.RB4.setEnabled(false);
    				this.yCB.setEnabled(false);
    				this.xCB.setEnabled(false);
    				this.yvar.setEnabled(false);
    				this.xvar.setEnabled(false);
    				this.bin.setEnabled(false);
    				this.jLabel1.setEnabled(false);
    				this.plotButton.setEnabled(false);
    				this.jLabel2.setEnabled(false);
    				this.jTextField1.setEnabled(false);
    				buttonGroup1.clearSelection();
    			}
    		} catch (IOException e) {
    		}  
    	}
    }                                            

    private void plotButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	jPanel1.add(fxPanel);     
    	fxPanel.setSize(450,320);
    	fxPanel.setVisible(true);  
    } 

    private void initFX(JFXPanel fxPanel) {
    	// This method is invoked on the JavaFX thread
    	Scene scene = createScene();
    	fxPanel.setScene(scene);

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Scene createScene() {
    	int xIndex = xCB.getSelectedIndex();
    	int yIndex = yCB.getSelectedIndex();
    	xArray = new double[dataInt.length];
    	yArray = new double[dataInt.length];

    	// first set of data
    	XYChart.Series series1 = new XYChart.Series();
    	for(int i = 0; i < dataInt.length; i++)
    	{    
    		xArray[i] = dataInt[i][xIndex];
    		yArray[i] = dataInt[i][yIndex];
    		//System.out.println(xArray[i]+ " " +yArray[i]);

    		series1.getData().add(new XYChart.Data(dataInt[i][xIndex], dataInt[i][yIndex]));
    	}
    	//Arrays.sort(xArray);
    	//Arrays.sort(yArray);

    	// set up axis
    	final NumberAxis xAxis;
    	final NumberAxis yAxis;
    	if(jTextField1.getText().matches("[0-9]+")){
    		xAxis = new NumberAxis(xArray[0]-1,xArray[xArray.length-1]+Integer.parseInt(jTextField1.getText()), 1);
    		yAxis = new NumberAxis(yArray[0]-1,yArray[yArray.length-1]+2*Integer.parseInt(jTextField1.getText()), 1);        
    	}
    	else{
    		xAxis = new NumberAxis(xArray[0]-1,xArray[xArray.length-1]+1, 1);
    		yAxis = new NumberAxis(yArray[0]-1,yArray[yArray.length-1]+1, 1);                     
    	}
    	xAxis.setLabel(xCB.getSelectedItem().toString());                
    	yAxis.setLabel(yCB.getSelectedItem().toString()); 

    	// create a Scatter plot
    	sc = new LineChart<>(xAxis,yAxis);
    	sc.setAnimated(false);

    	// plot a scatter chart
    	sc.getData().addAll(series1);
    	sc.setLegendVisible(false);

    	XYChart.Series series2 = new XYChart.Series();


    	// linear fitting
    	if(RB1.isSelected())
    	{
    		double slope1 = 0;
    		double slope2 = 0;
    		double intercept = 0;
    		double xavg = 0;
    		double yavg =0;

    		for(int i=0; i<xArray.length; i++)
    		{    
    			xavg = xavg + xArray[i];
    			yavg = yavg + yArray[i];
    		}
    		xavg = xavg / xArray.length;
    		yavg = yavg / yArray.length;

    		for(int i = 0; i < xArray.length; i++)
    		{    
    			slope1 = slope1 + (xArray[i] - xavg)*(yArray[i] - yavg);
    			slope2 = slope2 + (xArray[i] - xavg)*(xArray[i] - xavg);
    		}

    		slope1 = slope1 / slope2;
    		intercept = yavg - slope1*xavg;

    		if(jTextField1.getText().matches("[0-9]+")){
    			for(double i = xArray[0] - 1; i <= xArray[xArray.length-1] + Integer.parseInt(jTextField1.getText()); i += 0.5)
    			{    
    				series2.getData().add(new XYChart.Data(i, (slope1*i + intercept)));
    			}    
    		}
    		else{
    			for(double i = xArray[0] - 1; i <= xArray[xArray.length-1] + 1; i += 0.5)
    			{    
    				series2.getData().add(new XYChart.Data(i, (slope1*i + intercept)));
    			}
    		}
    	}

    	// polynomial algorithm
    	if(RB2.isSelected())
    	{
    		if(degree.getText().matches("[0-9]+")){
    			double[] coefficients = polyfit(xArray, yArray, Integer.parseInt(degree.getText()));
    			
    			if(jTextField1.getText().matches("[0-9]+")){ 
    				for (double x = xArray[0] - 1; x <= xArray[xArray.length-1] + Integer.parseInt(jTextField1.getText()); x += 0.5) {
    					double y = polynomial(x, coefficients);
    					series2.getData().add(new XYChart.Data<>(x,y));
    				}
    			}
    			else{
    				for (double x = xArray[0] - 1; x <= xArray[xArray.length-1] + 1; x += 0.5) {
    					double y = polynomial(x, coefficients);
    					series2.getData().add(new XYChart.Data<>(x,y));
    				}
    			}
    		}
    	}

    	// Moving average algorithm
    	if(RB4.isSelected())
    	{
    		if(binTF.getText().matches("[0-9]+")){
    			int width = Integer.parseInt(binTF.getText());

    			if(width <= xArray[xArray.length-1]-xArray[0])
    			{
    				for (int x = width; x <= xArray.length; x ++) {
    					double average = 0;
    					for(int y = x - width; y < x; y++)
    					{
    						average = average + yArray[y];
    					}  
    					average = average / width;
    					series2.getData().add(new XYChart.Data<>(xArray[x-1], average));
    				}
    			}
    		}
    	}

    	sc.getData().addAll(series2);
    	Scene scene  = new Scene(sc, 450, 320);
    	scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	return scene;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	/* Set the Nimbus look and feel */
    	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    	/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
    	 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
    	 */
    	try {
    		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
    			if ("Nimbus".equals(info.getName())) {
    				javax.swing.UIManager.setLookAndFeel(info.getClassName());
    				break;
    			}
    		}
    	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
    		java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    	}

    	/* Create and display the form */
    	java.awt.EventQueue.invokeLater(() -> {
    		new GUI().setVisible(true);
    	});
    }

    // Variables declaration - do not modify                     
    private javax.swing.JRadioButton RB1;
    private javax.swing.JRadioButton RB2;
    private javax.swing.JRadioButton RB3;
    private javax.swing.JRadioButton RB4;
    private javax.swing.JLabel bin;
    private javax.swing.JTextField binTF;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField degree;
    private javax.swing.JButton fileChooser;
    private javax.swing.JCheckBox headerChecker;
    private javax.swing.JButton importButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel messageLabel1;
    private javax.swing.JLabel messageLabel2;
    private javax.swing.JButton plotButton;
    private javax.swing.JComboBox<String> xCB;
    private javax.swing.JLabel xvar;
    private javax.swing.JComboBox<String> yCB;
    private javax.swing.JLabel yvar;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration                   

    /* ActionEvent handler */
    @Override
    public void actionPerformed(ActionEvent evt) {
    	Platform.runLater(() -> {
    		initFX(fxPanel);
    	});    
    }

    @Override
    public void windowOpened(WindowEvent e) {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}