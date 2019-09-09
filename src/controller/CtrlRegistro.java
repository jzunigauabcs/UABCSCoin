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
import view.VRegister;

/**
 *
 * @author jzuniga
 */
public class CtrlRegistro implements ActionListener{

    VRegister view;
    
    public CtrlRegistro(VRegister view) {
        this.view = view;
    }
    
    public void init() {
        this.view.jButton1.addActionListener(this);
        this.view.setVisible(true);
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.view.jButton1) {
            String password = String.valueOf(this.view.jPasswordField1.getPassword());
            String passwordRepeat = String.valueOf(this.view.jPasswordField2.getPassword());
            
            if(!password.equals(passwordRepeat)) {
                JOptionPane.showMessageDialog(view, "Error: las passwords no coinciden");               
            } else {
                Usuario u = new Usuario();
                u.setNombre(this.view.jtfNombre.getText());
                u.setApellido(this.view.jtfApellido.getText());
                u.setEmail(this.view.jtfEmail.getText());
                u.setPassword(password);
                u.setFechaNacimiento(this.view.jDateChooser1.getDate());
                if(u.create()) {
                    JOptionPane.showMessageDialog(view, "Datos guardados correctamente");
                } else {
                    JOptionPane.showMessageDialog(view, "Ocurrió un erro al almacenar los datos");
                }
            }
        }
    }
    
}
