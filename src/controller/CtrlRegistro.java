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

    private VRegister view;
    
    public CtrlRegistro() {
        this.view = new VRegister();
    }
    
    public void init() {
        this.view.setVisible(true);
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.jbtnRegistrar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String password = String.valueOf(this.view.jpfPassword.getPassword());
        String repeatPassword = String.valueOf(this.view.jpfRepeatPassword.getPassword());
        if(!password.equals(repeatPassword)) {
            JOptionPane.showMessageDialog(view, "Las password no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
            
        Usuario usuario = new Usuario();
        usuario.setNombre(this.view.jtfNombre.getText());
        usuario.setApellido(this.view.jtfApellido.getText());
        usuario.setEmail(this.view.jtfEmail.getText());
        usuario.setPassword(password);
        usuario.setFechaNacimiento(this.view.jdcFechaNacimiento.getDate());
        
        if(usuario.create()) {
            JOptionPane.showMessageDialog(view, "Datos guardados correctamente");
        } else {
            JOptionPane.showMessageDialog(view, "Ocurrió un error al intentar guardar los datos");
        }
    }
    
}
