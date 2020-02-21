package inicio;

import javax.swing.UIManager;

import ventanas.login;

public class inicio {

	public static void main(String[] args) {
		login principal = new login();
		principal.setVisible(true);
		principal.setLocationRelativeTo(null);
		 try {
	            //here you can put the selected theme class name in JTattoo
	            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
	 
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }

	}

}
