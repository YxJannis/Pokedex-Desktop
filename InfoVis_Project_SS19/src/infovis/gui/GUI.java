package infovis.gui;

import infovis.diagram.Diagram;
import infovis.diagram.MenuController;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;


public class GUI {

	private JFrame jFrame = null;

	private JPanel jContentPane = null;

	private JMenuBar jJMenuBar = null;

	private JMenu fileMenu = null;

	private JMenu editMenu = null;

	private JMenu helpMenu = null;

	private JMenuItem exitMenuItem = null;

	private JMenuItem aboutMenuItem = null;

	private JMenuItem cutMenuItem = null;

	private JMenuItem copyMenuItem = null;

	private JMenuItem pasteMenuItem = null;

	private JMenuItem saveMenuItem = null;

	private JDialog aboutDialog = null;

	private JPanel aboutContentPane = null;

	private JLabel aboutVersionLabel = null;

	private JToolBar jJToolBarBar = null;

	private JButton newNodeButton = null;

	private JPanel view = null;

	private JButton newLabelButton = null;

	private JSlider jSlider = null;

	private JToggleButton drawToggleButton = null;

	private JToggleButton fisheyeToggleButton = null;

	private boolean showToolbar = false;

	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	public JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setSize(800, 600);
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("InfoVisEditor");
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			if (showToolbar) jContentPane.add(getJJToolBarBar(), BorderLayout.NORTH);
			jContentPane.add(getView(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getEditMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu();
			editMenu.setText("Edit");
			editMenu.add(getCutMenuItem());
			editMenu.add(getCopyMenuItem());
			editMenu.add(getPasteMenuItem());
		}
		return editMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog aboutDialog = getAboutDialog();
					aboutDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					aboutDialog.setLocation(loc);
					aboutDialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * This method initializes aboutDialog	
	 * 	
	 * @return javax.swing.JDialog
	 */
	private JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(getJFrame(), true);
			aboutDialog.setTitle("About");
			aboutDialog.setContentPane(getAboutContentPane());
		}
		return aboutDialog;
	}

	/**
	 * This method initializes aboutContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAboutContentPane() {
		if (aboutContentPane == null) {
			aboutContentPane = new JPanel();
			aboutContentPane.setLayout(new BorderLayout());
			aboutContentPane.add(getAboutVersionLabel(), BorderLayout.CENTER);
		}
		return aboutContentPane;
	}

	/**
	 * This method initializes aboutVersionLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getAboutVersionLabel() {
		if (aboutVersionLabel == null) {
			aboutVersionLabel = new JLabel();
			aboutVersionLabel.setText("Version 1.0");
			aboutVersionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return aboutVersionLabel;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCutMenuItem() {
		if (cutMenuItem == null) {
			cutMenuItem = new JMenuItem();
			cutMenuItem.setText("Cut");
			cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
					Event.CTRL_MASK, true));
		}
		return cutMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCopyMenuItem() {
		if (copyMenuItem == null) {
			copyMenuItem = new JMenuItem();
			copyMenuItem.setText("Copy");
			copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
					Event.CTRL_MASK, true));
		}
		return copyMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getPasteMenuItem() {
		if (pasteMenuItem == null) {
			pasteMenuItem = new JMenuItem();
			pasteMenuItem.setText("Paste");
			pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
					Event.CTRL_MASK, true));
		}
		return pasteMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveMenuItem() {
		if (saveMenuItem == null) {
			saveMenuItem = new JMenuItem();
			saveMenuItem.setText("Save");
			saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
		}
		return saveMenuItem;
	}

	/**
	 * This method initializes jJToolBarBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			jJToolBarBar = new JToolBar();
			jJToolBarBar.add(getNewNodeButton());
			jJToolBarBar.add(getNewLabelButton());
			jJToolBarBar.add(getDrawToggleButton());
			jJToolBarBar.add(getFisheyeToggleButton());
			jJToolBarBar.add(getJSlider());
		}
		return jJToolBarBar;
	}

	/**
	 * This method initializes newNodeButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNewNodeButton() {
		if (newNodeButton == null) {
			newNodeButton = new JButton();
			newNodeButton.setText("new Node");
			newNodeButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//Debug.print("GUI: new Node clicked"); 
					MenuController.getMenuController().newVertex();
					//Controller.getController().newVertex();
				}
			});
		}
		return newNodeButton;
	}

	/**
	 * This method initializes view	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getView() {
		if (view == null) {
			view = new Diagram().getView();
			//view = new Scatterplot().getView();
		}
		return view;
	}

	/**
	 * This method initializes newLabelButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNewLabelButton() {
		if (newLabelButton == null) {
			newLabelButton = new JButton();
			newLabelButton.setText("new Label");
			newLabelButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					//MenuController.getMenuController().newLabel();
					//Debug.print("GUI: newLabel clicked"); 
				}
			});
		}
		return newLabelButton;
	}

	/**
	 * This method initializes jSlider	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJSlider() {
		if (jSlider == null) {
			jSlider = new JSlider();
			jSlider.setMaximum(10);
			jSlider.setMajorTickSpacing(5);
			jSlider.setMinorTickSpacing(1);
			jSlider.setValue(0);
			jSlider.setPaintTicks(true); 
			jSlider.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					//Debug.print("stateChanged() with Value:" + jSlider.getValue()); 
					MenuController.getInstance().setScale(Math.pow(10,jSlider.getValue()/ 10.0));
					
				}
			});
		}
		return jSlider;
	}

	/**
	 * This method initializes drawToggleButton	
	 * 	
	 * @return javax.swing.JToggleButton	
	 */
	private JToggleButton getDrawToggleButton() {
		if (drawToggleButton == null) {
			drawToggleButton = new JToggleButton();
			drawToggleButton.setText("Edge Draw");
			drawToggleButton.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED){
						MenuController.getInstance().startEdgeDrawingMode();
					}else if (e.getStateChange() == ItemEvent.DESELECTED){
						MenuController.getInstance().stopEdgeDrawingMode();
					}
				}
			});
		}
		return drawToggleButton;
	}

	/**
	 * This method initializes fisheyeToggleButton	
	 * 	
	 * @return javax.swing.JToggleButton	
	 */
	private JToggleButton getFisheyeToggleButton() {
		if (fisheyeToggleButton == null) {
			fisheyeToggleButton = new JToggleButton();
			fisheyeToggleButton.setText("Fisheye");
//			fisheyeToggleButton.addChangeListener(new javax.swing.event.ChangeListener() {
			fisheyeToggleButton.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED){
						MenuController.getInstance().startFisheyeMode();
					}else if (e.getStateChange() == ItemEvent.DESELECTED){
						MenuController.getInstance().stopFisheyeMode();
					}
				}
			});
//				public void stateChanged(javax.swing.event.ChangeEvent e) {
//					  AbstractButton abstractButton = (AbstractButton) e.getSource();
//				        ButtonModel buttonModel = abstractButton.getModel();
//				       
//				        //boolean armed = buttonModel.isArmed();
//				        //boolean pressed = buttonModel.isPressed();
//				        if (buttonModel.isSelected() && ! buttonModel.isRollover()){
//				        	MenuController.getInstance().startFisheyeLayout();
//				        } else {
//				        	MenuController.getInstance().stopFisheyeLayout();
//				        }
//				       }
//				
//			});
			
		}
		return fisheyeToggleButton;
	}

	/**
	 * Launches this application
	 */
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				GUI application = new GUI();
//				application.showToolbar(true);
//				application.getJFrame().setVisible(true);
//			}
//		});
//	}

	public void setView(JPanel view){
		this.view = view;
	}
	public void showToolbar(boolean showToolbar){
		this.showToolbar  = showToolbar;
	}
}
