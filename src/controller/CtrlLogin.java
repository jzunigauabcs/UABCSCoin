/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Usuario;
import view.VLogin;
import view.VRegister;

/**
 *
 * @author jzuniga
 */
public class CtrlLogin implements ActionListener{

    private VLogin view;

    public CtrlLogin(VLogin view) {
        this.view = view;
    }
    
    public void init() {
        this.view.jButton1.addActionListener(this);
        this.view.jbtRegistro.addActionListener(this);
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.view.jButton1) {
            String email = this.view.jTextField1.getText();
            String password = String.valueOf(this.view.jPasswordField1.getPassword());
            Usuario usuario = Usuario.findByLogin(email, password);
            if(usuario != null) {
                JOptionPane.showMessageDialog(view, 
                        "Bienvenido " + usuario.getNombre());
            } else {
                JOptionPane.showMessageDialog(view, 
                        "Usuario y contraseña incorrectos");
            }
        } else if(e.getSource() == this.view.jbtRegistro) {
            CtrlRegistro registro = new CtrlRegistro(new VRegister());
            registro.init();
        }
        
    }
    
}
