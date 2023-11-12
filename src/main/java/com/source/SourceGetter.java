package com.source;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * Hello world!
 *
 */
public class SourceGetter extends JFrame{  
    private static final long serialVersionUID = 1L;
	JTextField m_oUrlTextField;  
    JTextArea m_oUrlTextArea;  
    JButton m_oValidateButton;  
    JLabel m_oLabel;  
    
    public static void main(String[] args) throws UnsupportedLookAndFeelException {  
    	UIManager.setLookAndFeel(new NimbusLookAndFeel());
    	SourceGetter  oSourceGetter = new SourceGetter("Source Getter Tool", new Dimension(600,400));  
    	oSourceGetter.setVisible(true);
        
    }  
    
    SourceGetter(String iStrTitle, Dimension oDimension ){  
        super(iStrTitle);  
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(oDimension);
        ///this.setResizable(false);
        
        JPanel oPanel = (JPanel) this.getContentPane();
;        
        JToolBar oToolBar = new JToolBar();
        
        m_oLabel=new JLabel("Enter URL:");  
        m_oLabel.setBounds(50,50,50,20);  
          
        m_oUrlTextField=new JTextField();  
        m_oUrlTextField.setBounds(120,50,250,20);  
        m_oUrlTextField.setColumns(20);
        
       
          
        m_oValidateButton=new JButton("Click to get Source Code");  
        m_oValidateButton.setBounds(120, 100,120,30);  
        m_oValidateButton.setForeground(Color.GREEN);
        m_oValidateButton.addActionListener(this::displaySource);

        oToolBar.add(m_oLabel);
        oToolBar.add(m_oUrlTextField);
        oToolBar.add(m_oValidateButton);
          
        JScrollPane oScrollPane;
        m_oUrlTextArea=new JTextArea();  
        m_oUrlTextArea.setBounds(120,150,250,150);  
        oScrollPane = new JScrollPane(m_oUrlTextArea);
      
          
        oPanel.add(oToolBar, BorderLayout.NORTH);
        //add(m_oValidateButton);
        //add(m_oUrlTextField);
        add(oScrollPane, BorderLayout.CENTER);  
       
    }  
    
    
    public void displaySource(ActionEvent e) {
    	String strSource=m_oUrlTextField.getText();  
        if(strSource==null){m_oUrlTextArea.setText("no source found!!!"); }  
        else{  
            try{  
            URL oUrl=new URL(strSource);  
            URLConnection oConnection=oUrl.openConnection();  
          
            InputStream is=oConnection.getInputStream();  
            int i;  
            StringBuilder oStrStringBuilder=new StringBuilder();  
            while((i=is.read())!=-1){  
            	oStrStringBuilder.append((char)i);  
            }  
            String source=oStrStringBuilder.toString();  
            
            m_oUrlTextArea.setText(""); 
            m_oUrlTextArea.setText(source); 
            if(m_oUrlTextArea.getText().equals("")||m_oUrlTextArea.getText().equals(null)) {
            	m_oUrlTextArea.setText("no source found!!!"); 
            }
            }catch(Exception ex){System.out.println(e);}  
        }
    }
    
}  