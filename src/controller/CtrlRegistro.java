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
import view.VRegistro;

/**
 *
 * @author jzuniga
 */
public class CtrlRegistro implements ActionListener{
    private VRegistro view;

    public CtrlRegistro(VRegistro view) {
        this.view = view;
    }
    
    public void init() {
        this.view.setVisible(true);
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.jbnGuardar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.view.jbnGuardar) {
            String password = String.valueOf(this.view.jpfPassword.getPassword());
            String repeatPassword = String.valueOf(this.view.jpfRepetirPass.getPassword());
            
            if(!password.equals(repeatPassword)) {
                JOptionPane.showMessageDialog(view, "Contraseñas no coinciden");
            } else {
                Usuario u = new Usuario();
                u.setNombre(this.view.jtfNombre.getText());
                u.setApellido(this.view.jtfApellido.getText());
                u.setEmail(this.view.jtfEmail.getText());
                u.setPassword(password);
                u.setDate(this.view.jdcFechaNac.getDate());
                int result = u.create();
                if(result == 1) {
                    JOptionPane.showMessageDialog(view, "Datos guardados correctamente");
                } else {
                    JOptionPane.showMessageDialog(view, "Ocurrió un error al almacenar los datos");
                }
            }
        }
    }
}
