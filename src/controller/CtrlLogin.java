/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import model.Usuario;
import view.VLogin;
import view.VRegistro;

/**
 *
 * @author jzuniga
 */
public class CtrlLogin implements ActionListener {

    private VLogin view;

    public CtrlLogin(VLogin view) {
        this.view = view;
    }
    
    public void init() {
        this.view.jButton1.addActionListener(this);
        this.view.jbtRegistro.addActionListener(this);
        this.view.setVisible(true);
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.view.jButton1) {
            String user = this.view.jtfEmail.getText();
            String password = String.valueOf(this.view.jpfPassword.getPassword());
            
            Usuario u = Usuario.findByLogin(user, password);
            if(u != null) {
                System.out.println("Bienvenido " + u.getNombre());
            } else {
                System.out.println("Usuario y contraseña incorrectos");
            }
        } else if(e.getSource() == this.view.jbtRegistro) {
            CtrlRegistro registro = new CtrlRegistro(new VRegistro());
            registro.init();
        }
            
    }
}
