/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi_pelayanan;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SetImage extends JPanel{
    private Image image=null; 
    public SetImage (String file) {
    try{
        image = new ImageIcon(getClass().getResource(file)).getImage();
    }catch(Exception e){
       JOptionPane.showMessageDialog(this, e); 
    }
    }
@Override
 protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
       Graphics2D graphic = (Graphics2D) grphcs.create();
       graphic.drawImage(image, 0, 0, getWidth(), getHeight(), null);
      graphic.dispose();
  }
}
