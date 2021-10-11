import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrea.masciocchi
 */
public class VectorFrame extends javax.swing.JFrame {
    
    ActionListener act;
    
    int xDrag;
    int yDrag;
    boolean startDrag = true;
    Timer timer;
    Body body;
    Vector resultVector;
    int resultVectorIndex = 0;
    
    boolean isPolar = false;
    

    /**
     * Creates new form VectorFrame
     */
    public VectorFrame() {
        initComponents();
        this.body = new Body(jCanvas.getWidth(), jCanvas.getHeight());
        //initVars();
    }
    
    public boolean isPolar(){
        return isPolar;
    }
    public String[] addVectorNames(){
        String[] vectorNames = new String[body.vectors.size() + 1];
        for(int i = 0; i < body.vectors.size(); i++){
            vectorNames[i] = body.vectors.get(i).getName();
        }
        return vectorNames;
    }
    
    public static double getNewton(double x, double y){
        double result = 1;
        if(x<0){
            result = -1;
        }
        return result * Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    
    public static double getAngle(double x, double y){
        return Math.toDegrees(Math.atan(y/x));
    }
    
    public static double getX(double newton, double angle){
        return newton * Math.cos(angle);
    }
    
    public static double getY(double newton, double angle){
        return newton * Math.sin(angle);
    }
    
    public void addVector(){
        boolean overwrite = false;
        
        //check if any textbox is empty
        if(
            jFirstValue.getText().isBlank() ||
            jSecondValue.getText().isBlank() ||
            jVectorName.getText().isBlank()
        ){
            return;
        }
        try {
            Double.parseDouble(jFirstValue.getText());
            Double.parseDouble(jSecondValue.getText());
        }catch (NumberFormatException e){
            return;
        }
        //Create a new Vector
        Vector vector = new Vector(
                Double.parseDouble(jFirstValue.getText()),
                Double.parseDouble(jSecondValue.getText()),
                jVectorName.getText(),
                isPolar()
        );
        
        //check if any Vector has the same name of this, if true, overwrite
        for(int i = 0; i < body.vectors.size(); i++){
            if(jVectorName.getText().equals(body.vectors.get(i).getName())){
                body.vectors.set(i, vector);
                overwrite = true;
            }
        }
        if(!overwrite){
            body.vectors.add(vector);
        }
        
        //things for JList
        updateJList();
        
        System.out.println(vector);
           
        resetTextBoxes();
    }
    
    public void resetTextBoxes(){
        //reset textbox
        jFirstValue.setText("");
        jSecondValue.setText("");
        jVectorName.setText("");
    }
    
    public void updateJList(){
        jVectorList.setModel(new javax.swing.AbstractListModel<String>() {
            
            String[] strings = addVectorNames();
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jVectorList);
    }

    public void initVars(){
        
        ActionListener act = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                repaint();
            }
        };
        timer = new Timer(500, act);
        timer.setRepeats(true);
        timer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jNewVector = new javax.swing.JButton();
        jDeleteVector = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jVectorList = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jFirstValue = new javax.swing.JTextField();
        jFirstLabel = new javax.swing.JLabel();
        jSecondLabel = new javax.swing.JLabel();
        jSecondValue = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jVectorName = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jPolarCheckbox = new javax.swing.JCheckBox();
        jCalcVector = new javax.swing.JButton();
        jCanvas = new javax.swing.JPanel();

        jTextField1.setText("jTextField1");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jNewVector.setText("+ New Vector");
        jNewVector.setMinimumSize(new java.awt.Dimension(100, 25));
        jNewVector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNewVectorActionPerformed(evt);
            }
        });

        jDeleteVector.setText("- Delete Vector");
        jDeleteVector.setMinimumSize(new java.awt.Dimension(100, 25));
        jDeleteVector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteVectorActionPerformed(evt);
            }
        });

        jVectorList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jVectorListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jVectorList);

        jFirstLabel.setText("x");

        jSecondLabel.setText("y");

        jVectorName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(jVectorName);

        jLabel3.setText(" Name");

        jPolarCheckbox.setText("Polar");
        jPolarCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPolarCheckboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPolarCheckbox)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSecondValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSecondLabel))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jFirstValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFirstLabel)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPolarCheckbox))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFirstValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFirstLabel)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSecondValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSecondLabel))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jCalcVector.setText("Calc Result Vector");
        jCalcVector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCalcVectorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jNewVector, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDeleteVector, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCalcVector, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jNewVector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDeleteVector, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCalcVector)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jCanvas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jCanvasMouseDragged(evt);
            }
        });
        jCanvas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jCanvasMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCanvasMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jCanvasLayout = new javax.swing.GroupLayout(jCanvas);
        jCanvas.setLayout(jCanvasLayout);
        jCanvasLayout.setHorizontalGroup(
            jCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );
        jCanvasLayout.setVerticalGroup(
            jCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCanvasMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCanvasMouseDragged
        xDrag = evt.getX();
        yDrag = evt.getY();
        repaint();
    }//GEN-LAST:event_jCanvasMouseDragged

    private void jCanvasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCanvasMousePressed
        startDrag = false;
    }//GEN-LAST:event_jCanvasMousePressed

    private void jCanvasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCanvasMouseReleased
        xDrag = evt.getX();
        yDrag = evt.getY();
        double xLength = -(body.getX() - xDrag);
        double yLength = body.getY() - yDrag;
        if(isPolar){
            jFirstValue.setText(String.valueOf(getNewton(xLength, yLength)));
            jSecondValue.setText(String.valueOf(getAngle(xLength, yLength)));
        }else{
            jFirstValue.setText(String.valueOf(xLength));
            jSecondValue.setText(String.valueOf(yLength));
        }
        if(jVectorName.getText().isEmpty()){
            jVectorName.setText("AutoName " + String.valueOf(body.vectors.size()));
        }
        addVector();
        repaint();
        startDrag = true;
    }//GEN-LAST:event_jCanvasMouseReleased

    private void jDeleteVectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteVectorActionPerformed
        int[] selectedVectors = jVectorList.getSelectedIndices();
        for(int i = 0; i < selectedVectors.length; i++){
            body.vectors.remove(i);
        }
        updateJList();
        xDrag = 0;
        yDrag = 0;
        repaint();
        resetTextBoxes();
    }//GEN-LAST:event_jDeleteVectorActionPerformed

    private void jPolarCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPolarCheckboxActionPerformed
        isPolar = !isPolar;
        double firstValue = 0;
        double secondValue = 0;
        if(!jFirstValue.getText().isEmpty() || !jSecondValue.getText().isEmpty()){
            firstValue = Double.parseDouble(jFirstValue.getText());
            secondValue = Double.parseDouble(jSecondValue.getText());
        }
        if(isPolar){
            jFirstLabel.setText("N");
            jSecondLabel.setText("°");
            if(!jFirstValue.getText().isEmpty() || !jSecondValue.getText().isEmpty()){
                jFirstValue.setText(String.valueOf(getNewton(firstValue, secondValue)));
                jSecondValue.setText(String.valueOf(getAngle(firstValue, secondValue)));
            }
        }else{
            jFirstLabel.setText("x");
            jSecondLabel.setText("y");
            if(!jFirstValue.getText().isEmpty() || !jSecondValue.getText().isEmpty()){
                jFirstValue.setText(String.valueOf(getX(firstValue, secondValue)));
                jSecondValue.setText(String.valueOf(getY(firstValue, secondValue)));
            }
        }

    }//GEN-LAST:event_jPolarCheckboxActionPerformed

    private void jVectorListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jVectorListMouseClicked
        int[] selectedVectors = jVectorList.getSelectedIndices();
        if(selectedVectors.length == 1){
            if(!isPolar){
                jFirstValue.setText(String.valueOf(body.vectors.get(selectedVectors[0]).getX()));
                jSecondValue.setText(String.valueOf(body.vectors.get(selectedVectors[0]).getY()));
            }else{
                jFirstValue.setText(String.valueOf(body.vectors.get(selectedVectors[0]).getNewton()));
                jSecondValue.setText(String.valueOf(body.vectors.get(selectedVectors[0]).getAngle()));
            }
            jVectorName.setText(body.vectors.get(selectedVectors[0]).getName());
        }
    }//GEN-LAST:event_jVectorListMouseClicked

    private void jNewVectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNewVectorActionPerformed
        addVector();
    }//GEN-LAST:event_jNewVectorActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        body.setX(jCanvas.getWidth()/2);
        body.setY(jCanvas.getHeight()/2);
    }//GEN-LAST:event_formComponentResized

    private void jCalcVectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCalcVectorActionPerformed
        if(resultVectorIndex != 0){
            body.vectors.remove(resultVectorIndex);
        }
        int sumX = 0;
        int sumY = 0;
        for (int i = 0; i < body.vectors.size(); i++) {
            sumX += body.vectors.get(i).getX();
            sumY += body.vectors.get(i).getY();
        }
        resultVectorIndex = body.vectors.size();
        resultVector = new Vector(sumX, sumY, "ResultVector", isPolar);
        body.vectors.add(resultVector);
        updateJList();
        repaint();
    }//GEN-LAST:event_jCalcVectorActionPerformed

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = jCanvas.getGraphics();
        g.clearRect(0, 0, jCanvas.getWidth(), jCanvas.getHeight());
        body.paint(g);
        g.setColor(Color.red);
        for (Vector vector : body.vectors) {
            g.drawLine(body.getX(), body.getY(), (int)vector.getX()+body.getX(), -(int)vector.getY()+body.getY());
        }
        if((xDrag != 0 || yDrag != 0) && !startDrag){
            g.drawLine(body.getX(), body.getY(), xDrag, yDrag);
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
            java.util.logging.Logger.getLogger(VectorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VectorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VectorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VectorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VectorFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jCalcVector;
    private javax.swing.JPanel jCanvas;
    private javax.swing.JButton jDeleteVector;
    private javax.swing.JLabel jFirstLabel;
    private javax.swing.JTextField jFirstValue;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jNewVector;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JCheckBox jPolarCheckbox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jSecondLabel;
    private javax.swing.JTextField jSecondValue;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList<String> jVectorList;
    private javax.swing.JTextPane jVectorName;
    // End of variables declaration//GEN-END:variables
}
