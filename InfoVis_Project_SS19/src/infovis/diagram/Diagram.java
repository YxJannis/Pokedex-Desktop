package infovis.diagram;

import infovis.gui.GUI;

import javax.swing.SwingUtilities;

/**
 * 
 * @author patrick.riehmann(at)medien.uni-weimar.de
 */

public class Diagram {
    
	private MouseController controller = null;
    private Model model = null;
    private View view = null;
       
	/**
	 * @return View for GUI integration
	 */
    public View getView() {
		if (view == null) generateDiagram();
		return view;
	}

	private void generateDiagram(){
	   model = new Model();
	   model.generateTestValues();
	   view = new View();
	   controller = new MouseController();
	   MenuController menuController = MenuController.getMenuController();
	
	   view.addMouseListener(controller);
	   view.addMouseMotionListener(controller);
	   view.setModel(model);
	   controller.setModel(model);
	   controller.setView(view);
	   menuController.setView(view);
	   menuController.setModel(model);
	   menuController.setMouseControllerAddedToView(controller);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI application = new GUI();
				application.showToolbar(true);
				application.setView(new Diagram().getView());
				application.getJFrame().setVisible(true);
			}
		});
	}
	

}
