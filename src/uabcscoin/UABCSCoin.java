/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uabcscoin;

import controller.CtrlLogin;
import javafx.scene.shape.VLineTo;
import view.VLogin;


/**
 *
 * @author jzuniga
 */
public class UABCSCoin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        VLogin view = new VLogin();
        CtrlLogin controller = new CtrlLogin(view);
        controller.init();
    }
    
}
