package Formularios;
import BD.ConexionBD;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.Statement;

public class FrmNuevo extends javax.swing.JFrame {
  ConexionBD con=new ConexionBD();
         Connection cn = con.conectar();
         String CarneAlumno;
         String NombreAlumno;
         String Usuario;
    public FrmNuevo(String carne, String nombre, String user) {
        CarneAlumno=carne;     
        NombreAlumno=nombre;
        Usuario=user;
        initComponents();
       labelcarne.setText(CarneAlumno);
       labelnombre.setText(NombreAlumno);
         
        this.setLocationRelativeTo(null);
         this.setIconImage( new ImageIcon(getClass().getResource("/Img/user.png")).getImage());
        txtArea.setEditable(false);
    }

    /** ------------------------------- detector de huella ----------------------**/
    //Varible que permite iniciar el dispositivo de lector de huella conectado
// con sus distintos metodos.
private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();

//Varible que permite establecer las capturas de la huellas, para determina sus caracteristicas
// y poder estimar la creacion de un template de la huella para luego poder guardarla
private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();

//Esta variable tambien captura una huella del lector y crea sus caracteristcas para auntetificarla
// o verificarla con alguna guardada en la BD
private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();

//Variable que para crear el template de la huella luego de que se hallan creado las caracteriticas
// necesarias de la huella si no ha ocurrido ningun problema
private DPFPTemplate template;
public static String TEMPLATE_PROPERTY = "template";

    private FrmNuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

protected void Iniciar(){
   Lector.addDataListener(new DPFPDataAdapter() {
    @Override public void dataAcquired(final DPFPDataEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    EnviarTexto("La Huella Digital ha sido Capturada");
    ProcesarCaptura(e.getSample());
    }});}
   });

   Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
    @Override public void readerConnected(final DPFPReaderStatusEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    EnviarTexto("El Sensor de Huella Digital esta Conectado");
    }});}
    @Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    EnviarTexto("El Sensor de Huella Digital no esta Conectado");
    }});}
   });

   Lector.addSensorListener(new DPFPSensorAdapter() {
    @Override public void fingerTouched(final DPFPSensorEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    EnviarTexto("El dedo ha sido colocado sobre el Lector de Huella");
    }});}
    @Override public void fingerGone(final DPFPSensorEvent e) {
    SwingUtilities.invokeLater(new Runnable() {	public void run() {
    EnviarTexto("El dedo ha sido quitado del Lector de Huella");
    }});}
   });
   
   
   
   Lector.addErrorListener(new DPFPErrorAdapter(){
    public void errorReader(final DPFPErrorEvent e){
    SwingUtilities.invokeLater(new Runnable() {  public void run() {
    EnviarTexto("Error: "+e.getError());
    }});}
   });
}

 public DPFPFeatureSet featuresinscripcion;
 public DPFPFeatureSet featuresverificacion;

 public  void ProcesarCaptura(DPFPSample sample)
 {
 // Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
 featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

 // Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
 featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

 // Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
 if (featuresinscripcion != null)
     try{
     System.out.println("Las Caracteristicas de la Huella han sido creada");
     Reclutador.addFeatures(featuresinscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear

     // Dibuja la huella dactilar capturada.
     Image image=CrearImagenHuella(sample);
     DibujarHuella(image);
     }catch (DPFPImageQualityException ex) {
     System.err.println("Error: "+ex.getMessage());
     }

     finally {
     EstadoHuellas();
     // Comprueba si la plantilla se ha creado.
	switch(Reclutador.getTemplateStatus())
        {
            case TEMPLATE_STATUS_READY:	// informe de éxito y detiene  la captura de huellas
	    stop();
            setTemplate(Reclutador.getTemplate());
	    EnviarTexto("La Plantilla de la Huella ha Sido Creada, ya puede inscribir al alumno");	   
            btnGuardar.setEnabled(true);
            btnGuardar.grabFocus();
            break;

	    case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
	    Reclutador.clear();
            stop();
	    EstadoHuellas();
	    setTemplate(null);
	    JOptionPane.showMessageDialog(this, "La Plantilla de la Huella no pudo ser creada, Repita el Proceso", "Inscripcion de Huellas Dactilares", JOptionPane.ERROR_MESSAGE);
	    start();
	    break;
	}
	     }
}

 public  DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
     DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
     try {
      return extractor.createFeatureSet(sample, purpose);
     } catch (DPFPImageQualityException e) {
      return null;
     }
}

  public  Image CrearImagenHuella(DPFPSample sample) {
	return DPFPGlobal.getSampleConversionFactory().createImage(sample);
}

  public void DibujarHuella(Image image) {
        lblImagenHuella.setIcon(new ImageIcon(
        image.getScaledInstance(lblImagenHuella.getWidth(), lblImagenHuella.getHeight(), Image.SCALE_DEFAULT)));
        repaint();
 }

public  void EstadoHuellas(){
	EnviarTexto("Muestra de Huellas Necesarias para Guardar Template "+ Reclutador.getFeaturesNeeded());
}

public void EnviarTexto(String string) {
        txtArea.append(string + "\n");
}

public  void start(){
	Lector.startCapture();
	EnviarTexto("Utilizando el Lector de Huella Dactilar ");
}

public  void stop(){
        Lector.stopCapture();
        EnviarTexto("No se está usando el Lector de Huella Dactilar ");
}

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
	this.template = template;
	firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }
    
    //-------------------------------------------------guardar huella
    public void guardarHuella(){
     //Obtiene los datos del template de la huella actual
     ByteArrayInputStream datosHuella = new ByteArrayInputStream(template.serialize());
     Integer tamañoHuella=template.serialize().length;

     //Pregunta el nombre de la persona a la cual corresponde dicha huella
   
     try {
   
     PreparedStatement guardarStmt =cn.prepareStatement("UPDATE con_alumnos  set huella_i_d=? where Carne='"+CarneAlumno+"'");   
    guardarStmt.setBinaryStream(1, datosHuella,tamañoHuella);
     //Ejecuta la sentencia
     guardarStmt.execute();
     guardarStmt.close();
     insertarinscrpcion();
     JOptionPane.showMessageDialog(null,"Se ha Inscrito correctamente");
     
     btnGuardar.setEnabled(false);
   imprimi dialog = new imprimi(new javax.swing.JFrame(), true);
   dialog.setVisible(true);
    FrmAlumnosno alumnos = new FrmAlumnosno(Usuario);
    alumnos.setVisible(true);
    this.dispose();
    
     } catch (SQLException ex) {
     //Si ocurre un error lo indica en la consola
     System.err.println("Error al guardar los datos de la huella." + ex.toString());
     }finally{

     }
    
    }
    
    
    public void insertarinscrpcion()
    {       
        String idusuario ="",idalumno = "";
        String consulta;
        try
        {
             //primero necesitamos saber el id del usuario logeado
            consulta = "select id_usuario from con_usuarios where nombre_usuario ='"+Usuario+"'";
            Statement st =  cn.createStatement();
            ResultSet rs = st.executeQuery(consulta);                   
            while(rs.next())  //while simple      
                idusuario= rs.getString(1);
            
             //segundo necesitamos saber el id del alumno
            consulta = "select Id_Alumno from con_alumnos where carne ='"+CarneAlumno+"'";
            Statement st2 =  cn.createStatement();
            ResultSet rs2 = st2.executeQuery(consulta);                   
            while(rs2.next())  //while simple      
                idalumno= rs2.getString(1);
            
            //tercero vamos a guardar la inscripcion
             PreparedStatement guardarStmt =cn.prepareStatement("INSERT INTO con_inscripciones (id_alumno, id_evento, inscribio) VALUES (?,?,?)");   
                guardarStmt.setInt(1,  Integer.parseInt(idalumno));
                guardarStmt.setInt(2, 1);
                guardarStmt.setInt(3, Integer.parseInt(idusuario));
     //Ejecuta la sentencia
     guardarStmt.execute();
     guardarStmt.close();
     
     //tercero vamos a guardar la inscripcion
             PreparedStatement guardarStmt2 =cn.prepareStatement("INSERT INTO con_votaciones (num_diploma, Id_Alumno) VALUES (?,?)");   
               guardarStmt2.setInt(2,  Integer.parseInt(idalumno));  
             guardarStmt2.setInt(1,  Integer.parseInt(combodiploma.getSelectedItem().toString()));      
                           
     //Ejecuta la sentencia
     guardarStmt2.execute();
     guardarStmt2.close();
            
         }
        catch(Exception ex) {        
         JOptionPane.showMessageDialog(null, "Error al inscribit" + ex.toString());
        }
        con.desconectar();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        lblImagenHuella = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        labelcarne = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        combodiploma = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        labelcarne1 = new javax.swing.JLabel();
        labelcarne2 = new javax.swing.JLabel();
        labelnombre = new javax.swing.JLabel();
        labelfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Inscripcion");
        setMaximumSize(new java.awt.Dimension(615, 490));
        setMinimumSize(new java.awt.Dimension(615, 490));
        setPreferredSize(new java.awt.Dimension(615, 490));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 21, 56));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/write.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(70, 290, 170, 40);

        lblImagenHuella.setToolTipText("Huella digital");
        lblImagenHuella.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), null));
        getContentPane().add(lblImagenHuella);
        lblImagenHuella.setBounds(30, 110, 230, 133);

        txtArea.setEditable(false);
        txtArea.setBackground(new java.awt.Color(240, 240, 240));
        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 360, 600, 60);

        labelcarne.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelcarne.setForeground(new java.awt.Color(0, 21, 56));
        labelcarne.setText("Nombre del Alumno");
        getContentPane().add(labelcarne);
        labelcarne.setBounds(310, 180, 180, 14);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 21, 56));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menu.png"))); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(10, 10, 150, 30);

        combodiploma.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combodiploma.setForeground(new java.awt.Color(0, 21, 56));
        combodiploma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        combodiploma.setToolTipText("Diseños de Diplomas");
        getContentPane().add(combodiploma);
        combodiploma.setBounds(390, 300, 80, 23);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 21, 56));
        jLabel1.setText("Seleccione el Diseño del Diploma");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(340, 270, 210, 20);

        labelcarne1.setForeground(new java.awt.Color(0, 21, 56));
        labelcarne1.setText("Carne:");
        getContentPane().add(labelcarne1);
        labelcarne1.setBounds(270, 180, 70, 14);

        labelcarne2.setForeground(new java.awt.Color(0, 21, 56));
        labelcarne2.setText("Estudiante:");
        getContentPane().add(labelcarne2);
        labelcarne2.setBounds(270, 160, 70, 14);

        labelnombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelnombre.setForeground(new java.awt.Color(0, 21, 56));
        labelnombre.setText("090311");
        getContentPane().add(labelnombre);
        labelnombre.setBounds(340, 160, 250, 14);

        labelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/FondoPrincipal2.jpg"))); // NOI18N
        getContentPane().add(labelfondo);
        labelfondo.setBounds(0, 0, 600, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Iniciar();
	start();
        EstadoHuellas();
    }//GEN-LAST:event_formWindowOpened

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarHuella();
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        stop();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmNuevo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox combodiploma;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelcarne;
    private javax.swing.JLabel labelcarne1;
    private javax.swing.JLabel labelcarne2;
    private javax.swing.JLabel labelfondo;
    private javax.swing.JLabel labelnombre;
    private javax.swing.JLabel lblImagenHuella;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
