/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotechserranocampossemestre1año2018;

import GUI.Icon;
import GUI.Principal;

/**
 *
 * @author Arturo
 */
public class BiblioTechSerranoCamposSemestre1Año2018 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws InterruptedException {
        long start = System.currentTimeMillis();
        Icon icon= new Icon();
        icon.setVisible(true);
        // abro la ventana icono
        Thread.sleep(3000);
        // hil para esparar abrir principal
        icon.setVisible(false);
         Principal principal=new Principal();
        principal.setVisible (true); 
    } 
}
