/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;



/**
 *
 * @author HP
 */
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class consola extends JFrame {
    private final JTextArea areaEntrada;
    private final Funciones_Archivos funcionesArchivos;
    private String prompt;

    public consola() {
        funcionesArchivos = new Funciones_Archivos();  
        prompt = funcionesArchivos + "> ";//obtener la carpeta

        setTitle("Simulador CMD");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

       
        areaEntrada = new JTextArea();
        areaEntrada.setEditable(true);
        areaEntrada.setBackground(Color.BLACK);
        areaEntrada.setForeground(Color.LIGHT_GRAY);
        areaEntrada.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaEntrada.append("Microsoft Windows [Versión 10.0.22621.521]\n");
        areaEntrada.append("(c) Microsoft Corporation. All rights reserved.\nMade By:\nRoy Umaña\nWilmer Colindres\n\n");
        areaEntrada.append(prompt);

        JScrollPane scrollEntrada = new JScrollPane(areaEntrada);
        add(scrollEntrada, BorderLayout.CENTER);

        // Detecta la tecla Enter para ejecutar comandos
        areaEntrada.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume(); 
                    ejecutarComando();
                }
            }
        });

        setVisible(true);
    }

    private void ejecutarComando() {
        try {
           
            int promptPosition = areaEntrada.getText().lastIndexOf(prompt) + prompt.length();
            String comando = areaEntrada.getText().substring(promptPosition).trim();

            
            String respuesta = "x";//funcion ejecutar comandos funcionesArchivos.ejecutarComando(comando)

            // Mostrar respuesta debajo del comando
            areaEntrada.append("\n" + respuesta + "\n");

            // Actualizar el prompt
            prompt = funcionesArchivos+ "> ";//obtener carpeta funcionesArchivos.getCarpetaActual()
            areaEntrada.append(prompt);

            // Asegura que el cursor siempre esté al final
            areaEntrada.setCaretPosition(areaEntrada.getDocument().getLength());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(consola::new);
    }
}