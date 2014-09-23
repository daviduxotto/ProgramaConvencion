package Formularios;
import javax.swing.ImageIcon;

public class FrmPrincipalRoot extends javax.swing.JFrame {
public String Usuario;

    public FrmPrincipalRoot(String user) {
        initComponents();
        Usuario = user;
        labeluser.setText(Usuario);
        this.setLocationRelativeTo(null);
         this.setIconImage( new ImageIcon(getClass().getResource("/Img/umg.png")).getImage());
    }

    private FrmPrincipalRoot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        labeluser = new javax.swing.JLabel();
        BtnUsuarios = new javax.swing.JButton();
        labelbienvenido = new javax.swing.JLabel();
        labelfondo = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Control De Asistencia");
        setMaximumSize(new java.awt.Dimension(615, 490));
        setMinimumSize(new java.awt.Dimension(615, 490));
        getContentPane().setLayout(null);

        labeluser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labeluser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labeluser.setText("Usuario");
        getContentPane().add(labeluser);
        labeluser.setBounds(450, 90, 100, 20);

        BtnUsuarios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnUsuarios.setForeground(new java.awt.Color(0, 21, 56));
        BtnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/user.png"))); // NOI18N
        BtnUsuarios.setText("Usuarios");
        BtnUsuarios.setToolTipText("Inscripción de Alumnos");
        BtnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUsuariosActionPerformed(evt);
            }
        });
        getContentPane().add(BtnUsuarios);
        BtnUsuarios.setBounds(420, 130, 130, 50);

        labelbienvenido.setText("Bienvenido(a)");
        getContentPane().add(labelbienvenido);
        labelbienvenido.setBounds(490, 80, 90, 14);

        labelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/FondoPrincipal.jpg"))); // NOI18N
        getContentPane().add(labelfondo);
        labelfondo.setBounds(0, 0, 600, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUsuariosActionPerformed
      frmUsuario usuarios = new frmUsuario(Usuario);
       usuarios.setVisible(true);
    }//GEN-LAST:event_BtnUsuariosActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalRoot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalRoot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalRoot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalRoot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipalRoot().setVisible(true);                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnUsuarios;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel labelbienvenido;
    private javax.swing.JLabel labelfondo;
    private javax.swing.JLabel labeluser;
    // End of variables declaration//GEN-END:variables
}
