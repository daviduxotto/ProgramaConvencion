package Formularios;
import javax.swing.ImageIcon;

public class FrmPrincipalInsc extends javax.swing.JFrame {
public String Usuario;

    public FrmPrincipalInsc(String user) {
        initComponents();
        Usuario = user;
        labeluser.setText(Usuario);
        this.setLocationRelativeTo(null);
         this.setIconImage( new ImageIcon(getClass().getResource("/Img/umg.png")).getImage());
    }

    private FrmPrincipalInsc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        labeluser = new javax.swing.JLabel();
        BtnAlumnosNo = new javax.swing.JButton();
        labelbienvenido = new javax.swing.JLabel();
        labelfondo = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Control De Asistencia");
        setMaximumSize(new java.awt.Dimension(615, 490));
        setMinimumSize(new java.awt.Dimension(615, 490));
        setPreferredSize(new java.awt.Dimension(615, 485));
        getContentPane().setLayout(null);

        labeluser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labeluser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labeluser.setText("Usuario");
        getContentPane().add(labeluser);
        labeluser.setBounds(450, 90, 100, 20);

        BtnAlumnosNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnAlumnosNo.setForeground(new java.awt.Color(0, 21, 56));
        BtnAlumnosNo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/user.png"))); // NOI18N
        BtnAlumnosNo.setText("Inscripciones");
        BtnAlumnosNo.setToolTipText("Inscripción de Alumnos");
        BtnAlumnosNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlumnosNoActionPerformed(evt);
            }
        });
        getContentPane().add(BtnAlumnosNo);
        BtnAlumnosNo.setBounds(20, 110, 150, 50);

        labelbienvenido.setText("Bienvenido(a)");
        getContentPane().add(labelbienvenido);
        labelbienvenido.setBounds(490, 80, 90, 14);

        labelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/FondoPrincipal.jpg"))); // NOI18N
        getContentPane().add(labelfondo);
        labelfondo.setBounds(0, 0, 600, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAlumnosNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlumnosNoActionPerformed
       FrmAlumnosno alumnos = new FrmAlumnosno( Usuario);
       alumnos.setVisible(true);
    }//GEN-LAST:event_BtnAlumnosNoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPrincipalInsc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalInsc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalInsc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalInsc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipalInsc().setVisible(true);                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlumnosNo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel labelbienvenido;
    private javax.swing.JLabel labelfondo;
    private javax.swing.JLabel labeluser;
    // End of variables declaration//GEN-END:variables
}
