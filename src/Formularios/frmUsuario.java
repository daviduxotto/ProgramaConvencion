
package Formularios;
import java.sql.*;
import BD.ConexionBD;
import java.util.logging.*;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.table.*;

public class frmUsuario extends javax.swing.JFrame {
DefaultTableModel Modelo;
String Usuario;

 
    public frmUsuario(String user) {
        initComponents();
        Usuario = user;
       this.setLocationRelativeTo(null);
         this.setIconImage( new ImageIcon(getClass().getResource("/Img/user.png")).getImage());
         usuarios();
    }

    frmUsuario() {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    void  usuarios(){
        

        String [] titulos = {"ID", "NOMBRE", "ROL"};
        Modelo = new DefaultTableModel(null, titulos);
        String Datos [] = new String[5];
        String buscar = "select * from con_usuarios";
        try { 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(buscar);
            while (rs.next()) {
             Datos[0]= rs.getString("id_usuario");
             Datos[1]= rs.getString("nombre_usuario");
             Datos[2]= rs.getString("rol");
             Modelo.addRow(Datos);
             
            }
            tbusuario.setModel(Modelo);
        } catch (Exception e) {
        }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbusuario = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        cboRol = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        labelfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuarios");
        setMaximumSize(new java.awt.Dimension(615, 490));
        setMinimumSize(new java.awt.Dimension(615, 490));
        setPreferredSize(new java.awt.Dimension(615, 485));
        setResizable(false);
        getContentPane().setLayout(null);

        tbusuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbusuario);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 230, 390, 179);

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 21, 56));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(430, 380, 90, 25);

        txtUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(0, 21, 56));
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(80, 100, 161, 23);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 21, 56));
        jLabel1.setText("Nuevo Usuario");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 80, 140, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 21, 56));
        jLabel2.setText("Contraseña:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(290, 90, 75, 30);

        txtpass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtpass.setForeground(new java.awt.Color(0, 21, 56));
        txtpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassActionPerformed(evt);
            }
        });
        getContentPane().add(txtpass);
        txtpass.setBounds(390, 100, 161, 23);

        cboRol.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboRol.setForeground(new java.awt.Color(0, 21, 56));
        cboRol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "inscripcion", "Administrador" }));
        getContentPane().add(cboRol);
        cboRol.setBounds(80, 140, 160, 23);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 21, 56));
        jLabel3.setText("ROL");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 140, 26, 17);

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 21, 56));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(300, 140, 260, 25);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 21, 56));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menu.png"))); // NOI18N
        jButton1.setText("Menu Principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(10, 10, 150, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 21, 56));
        jLabel4.setText("Usuario:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 100, 100, 20);

        labelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/FondoPrincipal2.jpg"))); // NOI18N
        getContentPane().add(labelfondo);
        labelfondo.setBounds(0, 0, 610, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int FILA = tbusuario.getSelectedRow();
        if (FILA < 0){
            JOptionPane.showMessageDialog(this, "Seleccione un usuario");           
        }else {
            idfila = tbusuario.getValueAt(FILA, 0).toString();                       
            eliminar();
            
            
    }       
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        if ( txtUsuario.getText().equals("") && txtpass.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Favor llenar los campos");
        } else {
            String  guardar ="INSERT INTO con_usuarios (nombre_usuario, password, rol) VALUES(?,?,?)";
            try {
                PreparedStatement ingreso = cn.prepareStatement(guardar);
                ingreso.setString(1, txtUsuario.getText());
                ingreso.setString(2,new String(txtpass.getPassword()));
                ingreso.setString(3, cboRol.getSelectedItem().toString());
                int numero = ingreso.executeUpdate();
                if (numero > 0) {
                    JOptionPane.showMessageDialog(this, "Se ha creado el nuevo usuario");
                    usuarios();
                    txtUsuario.setText(null);
                    txtpass.setText(null);
                    cboRol.setSelectedIndex(0);

                } else {
                    JOptionPane.showMessageDialog(this, "Error");

                }
            } catch (Exception e) {

            }

        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void eliminar()
    {
        String compara = "";
        String contra = "";
        JPasswordField pf = new JPasswordField();
int okCxl = JOptionPane.showConfirmDialog(null, pf, "Su contraseña para confirmar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

if (okCxl == JOptionPane.OK_OPTION) {
   contra = new String(pf.getPassword());
}
else
{
    return;
}

       String cap = Usuario;
       System.out.print(cap);
      String sql="SELECT * FROM con_usuarios WHERE nombre_usuario='"+cap+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())        {
            compara=rs.getString("password");
            }
          System.out.println(compara);
        } catch (SQLException ex) {
        }
          if(compara.equals(contra))
            {
                
                String eli="DELETE FROM con_usuarios WHERE id_usuario = '"+idfila+"'";
                PreparedStatement pst;
            try {
                pst = cn.prepareStatement(eli);
                  int m=pst.executeUpdate();
                if(m>0){
                    JOptionPane.showMessageDialog(this, "elimino regisro");
                     usuarios();
                     
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar");
                    usuarios();
                }
                
            } catch (SQLException ex) {
            }
              
            }
    }
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(frmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new frmUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cboRol;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelfondo;
    private javax.swing.JTable tbusuario;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JPasswordField txtpass;
    // End of variables declaration//GEN-END:variables
ConexionBD cc = new ConexionBD();
Connection cn = cc.conectar();
String idfila = "";


}
