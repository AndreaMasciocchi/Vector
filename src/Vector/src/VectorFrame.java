import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author andrea.masciocchi
 */
public class VectorFrame extends javax.swing.JFrame {
    
    int xDrag;
    int yDrag;
    int centerX;
    int centerY;
    boolean startDrag = true;
    Body body;
    Vector resultVector;
    boolean isPolar = false;
    Timer timer;
    ActionListener act;
    
    /**
     * Creates new form VectorFrame
     */
    public VectorFrame() {
        initComponents();
        this.body = new Body(jCanvas.getWidth(), jCanvas.getHeight());
        centerX = jCanvas.getWidth()/2;
        centerY = jCanvas.getHeight()/2;
    }
    
    public boolean checkCorrectSelection(int selectedIndex){
        return selectedIndex != jVectorList.getLastVisibleIndex();
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
    public void enableTextBox(boolean value){
        jFirstValue.setEnabled(value);
        jSecondValue.setEnabled(value);
        jVectorName.setEnabled(value);
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
    public void calcResultVector(){
        int resultX = 0;
        int resultY = 0;
        
        for(int i = 1; i < body.vectors.size(); i++){
            resultX += body.vectors.get(i).getX();
            resultY += body.vectors.get(i).getY();
        }
        
        resultVector = new Vector(resultX, resultY, "ResultVector", isPolar);
        body.vectors.set(0, resultVector);
        
        repaint();
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
        if(body.vectors.isEmpty()){
            body.vectors.add(vector);
            if(jVectorName.getText().isBlank() || jVectorName.getText().equals("AutoName 0"))
                vector.setName("AutoName 1");
            body.vectors.add(vector);
        }
        else if(!overwrite){
            body.vectors.add(vector);
        }
        calcResultVector();
        
        
        //things for JList
        updateJList();
        
        System.out.println(vector);   
        resetTextBoxes();
        repaint();
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
    public boolean containValue(int indexValue){
        for (int i = 0; i < jVectorList.getSelectedIndices().length; i++) {
            if(indexValue == jVectorList.getSelectedIndices()[i]){
                return true;
            }
        }
        return false;
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
        jCanvas = new javax.swing.JPanel();
        jStartAnimation = new javax.swing.JButton();
        jStartAnimation1 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 300));
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
        jDeleteVector.setMaximumSize(new java.awt.Dimension(103, 22));
        jDeleteVector.setMinimumSize(new java.awt.Dimension(100, 25));
        jDeleteVector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteVectorActionPerformed(evt);
            }
        });

        jVectorList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jVectorListMouseReleased(evt);
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
                        .addComponent(jDeleteVector, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jNewVector, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jDeleteVector, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jCanvasLayout.setVerticalGroup(
            jCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jStartAnimation.setText("Start");
        jStartAnimation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStartAnimationActionPerformed(evt);
            }
        });

        jStartAnimation1.setText("Stop");
        jStartAnimation1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStartAnimation1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 234, Short.MAX_VALUE)
                        .addComponent(jStartAnimation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jStartAnimation1))
                    .addComponent(jCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jStartAnimation)
                            .addComponent(jStartAnimation1))))
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
        if(jVectorList.getSelectedIndex() != 0){
            startDrag = false;
        }
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
        startDrag = true;
        enableTextBox(true);
    }//GEN-LAST:event_jCanvasMouseReleased

    private void jDeleteVectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteVectorActionPerformed
        int[] selectedVectors = jVectorList.getSelectedIndices();
        for(int i = 0; i < selectedVectors.length; i++){
            if(selectedVectors[i] != 0 && checkCorrectSelection(selectedVectors[i])){
                body.vectors.remove(selectedVectors[i]);
            }
        }
        for (int i = 0; i < body.vectors.size(); i++) {
            if(body.vectors.get(i).getName().startsWith("AutoName ")){
                body.vectors.get(i).setName("AutoName " + i);
            }
        }
        calcResultVector();
        updateJList();
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

    private void jNewVectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNewVectorActionPerformed
        addVector();
    }//GEN-LAST:event_jNewVectorActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        centerX = jCanvas.getWidth()/2;
        centerY = jCanvas.getHeight()/2;
    }//GEN-LAST:event_formComponentResized

    private void jStartAnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStartAnimationActionPerformed
        ActionListener act = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                body.setX((int)body.vectors.get(0).getX() * 5/100 + body.getX());
                body.setY(-(int)body.vectors.get(0).getY() * 5/100 + body.getY());
                repaint();
            }
        };
        timer = new Timer(10, act);
        timer.start();        
    }//GEN-LAST:event_jStartAnimationActionPerformed

    private void jVectorListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jVectorListMouseReleased
        enableTextBox(true);
        int[] selectedVectors = jVectorList.getSelectedIndices();
        if(selectedVectors.length == 1){
            if(checkCorrectSelection(selectedVectors[0])){
                if(!isPolar){
                    jFirstValue.setText(String.valueOf(body.vectors.get(selectedVectors[0]).getX()));
                    jSecondValue.setText(String.valueOf(body.vectors.get(selectedVectors[0]).getY()));
                }else{
                    jFirstValue.setText(String.valueOf(body.vectors.get(selectedVectors[0]).getNewton()));
                    jSecondValue.setText(String.valueOf(body.vectors.get(selectedVectors[0]).getAngle()));
                }
                jVectorName.setText(body.vectors.get(selectedVectors[0]).getName());
                if(selectedVectors[0] == 0){
                    enableTextBox(false);
                }
            }
        }else{
            resetTextBoxes();
        }
        repaint();
    }//GEN-LAST:event_jVectorListMouseReleased

    private void jStartAnimation1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStartAnimation1ActionPerformed
        timer.stop();
    }//GEN-LAST:event_jStartAnimation1ActionPerformed

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = jCanvas.getGraphics();
        g.clearRect(0, 0, jCanvas.getWidth(), jCanvas.getHeight());
        for (int i = 0; i < body.vectors.size(); i++) {
            
            if(i == 0){
                g.setColor(new Color(52, 163, 97));
            }else if(containValue(i)){
                g.setColor(new Color(0, 115, 255));
            }else {
                g.setColor(Color.RED);
            }
            g.drawLine(body.getX(), body.getY(), (int)body.vectors.get(i).getX()+body.getX(), -(int)body.vectors.get(i).getY()+body.getY());
        }
        if((xDrag != 0 || yDrag != 0) && !startDrag){
            g.setColor(Color.BLACK);
            g.drawLine(body.getX(), body.getY(), xDrag, yDrag);
        }
        body.paint(g);
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
    private javax.swing.JButton jStartAnimation;
    private javax.swing.JButton jStartAnimation1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList<String> jVectorList;
    private javax.swing.JTextPane jVectorName;
    // End of variables declaration//GEN-END:variables
}
