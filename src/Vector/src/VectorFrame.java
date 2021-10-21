import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.Timer;

/**
 *
 * @author andrea.masciocchi
 */
public class VectorFrame extends javax.swing.JFrame {
    
    ArrayList<Line> lines = new ArrayList<>();
    
    int xDrag;
    int yDrag;
    int centerX;
    int centerY;
    int preBodyX;
    int preBodyY;
    int canvasX;
    int canvasY;
    int gcd = 1;
    
    private double xOffset = 0;
    private double yOffset = 0;
    private int xDiff;
    private int yDiff;
    
    double zoomFactor = 1;
    double prevZoomFactor = 1;
    
    boolean zoomer;
    boolean startDrag = true;
    boolean isPolar = false;
    
    Body body;
    
    Timer timer;
    ActionListener act;
    
    /**
     * Creates new form VectorFrame
     */
    public VectorFrame() {
        initComponents();
        canvasX = jCanvas.getWidth();
        canvasY = jCanvas.getHeight();
        this.body = new Body(canvasX, canvasY);
        centerX = body.getX();
        centerY = body.getY();
        body.vectors.add(new Vector(0, 0, "Result Vector", Color.RED, 0, 0));
        updateJList();
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
        jVariableX.setEnabled(value);
        jVariableY.setEnabled(value);
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
        
        body.vectors.set(0, new Vector(resultX, resultY, "Result Vector", Color.RED, 0, 0));
        lines.add(new Line(centerX, centerY, body.getX(), body.getY()));
        centerX = body.getX();
        centerY = body.getY();
        //repaint();
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
                jVectorName.getText(), Color.RED, Integer.parseInt(jVariableX.getText()), Integer.parseInt(jVariableY.getText()));
        
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
        jVariableX.setText("");
        jVariableY.setText("");
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
    public void createLine(int startX, int startY, int destX, int destY){
        if(timer != null){
            if(timer.isRunning()){
                lines.add(new Line(startX, startY, destX, destY));
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jColorPicker = new javax.swing.JButton();
        jVariableX = new javax.swing.JTextField();
        jVariableY = new javax.swing.JTextField();
        jCanvas = new javax.swing.JPanel();
        jStartAnimation = new javax.swing.JButton();
        jStopAnimation = new javax.swing.JButton();
        jSave = new javax.swing.JButton();
        jUpload = new javax.swing.JButton();
        jReset = new javax.swing.JButton();

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

        jColorPicker.setText("Color");
        jColorPicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jColorPickerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPolarCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jColorPicker))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jFirstValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSecondValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSecondLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jVariableY, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jFirstLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jVariableX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPolarCheckbox)
                    .addComponent(jColorPicker)))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFirstValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFirstLabel)
                    .addComponent(jVariableX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSecondValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSecondLabel)
                    .addComponent(jVariableY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jNewVector, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDeleteVector, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jNewVector, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jDeleteVector, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jCanvas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jCanvas.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jCanvas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jCanvasMouseDragged(evt);
            }
        });
        jCanvas.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jCanvasMouseWheelMoved(evt);
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

        jStopAnimation.setText("Stop");
        jStopAnimation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStopAnimationActionPerformed(evt);
            }
        });

        jSave.setText("Save");
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed(evt);
            }
        });

        jUpload.setText("Upload");
        jUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUploadActionPerformed(evt);
            }
        });

        jReset.setText("Reset");
        jReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jUpload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jStartAnimation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jStopAnimation))
                    .addComponent(jCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jStopAnimation)
                            .addComponent(jSave)
                            .addComponent(jUpload)
                            .addComponent(jReset))))
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
            jVariableX.setText("0");
            jVariableY.setText("0");
        }
        if(jVectorName.getText().isEmpty()){
            jVectorName.setText("AutoName " + String.valueOf(body.vectors.size()));
        }
//        createLine(preBodyX, preBodyY, body.getX(), body.getY());
//        preBodyX = body.getX();
//        preBodyY = body.getY();
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
    private void jNewVectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNewVectorActionPerformed
        addVector();
    }//GEN-LAST:event_jNewVectorActionPerformed
    private void jStartAnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStartAnimationActionPerformed
        preBodyX = body.getX();
        preBodyY = body.getY();
        ActionListener act;
        act = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
//                for(int i = 1; i <= body.vectors.get(0).getX() && i <= body.vectors.get(0).getY(); i++){  
//                    if(body.vectors.get(0).getX()%i==0 && body.vectors.get(0).getY()%i==0){
//                        gcd = i;  
//                    }
//                }
                for(Vector vector : body.vectors){
                    vector.setX(vector.getX() + vector.getVarX());
                    vector.setY(vector.getY() + vector.getVarY());
                }
                body.setX((int)body.vectors.get(0).getX()/gcd + body.getX());
                body.setY(-(int)body.vectors.get(0).getY()/gcd + body.getY());
                createLine(preBodyX, preBodyY, body.getX(), body.getY());
                preBodyX = body.getX();
                preBodyY = body.getY();
                calcResultVector();
                repaint();
            }
        };
        timer = new Timer(200, act);
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
                    jVariableX.setText(String.valueOf(body.vectors.get(selectedVectors[0]).getVarX()));
                    jVariableY.setText(String.valueOf(body.vectors.get(selectedVectors[0]).getVarY()));
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
    private void jStopAnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStopAnimationActionPerformed
        timer.stop();
    }//GEN-LAST:event_jStopAnimationActionPerformed
    private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        try {
            File newFile = new File(chooser.getSelectedFile().toString());
            String whatToWrite = "";
            for(int i = 1; i < body.vectors.size(); i++){
                whatToWrite += body.vectors.get(i).getName() + ";" + body.vectors.get(i).getX() + ";" + body.vectors.get(i).getY() + "\n";
            }
            newFile.createNewFile();
            Path path = Paths.get(newFile.getPath());
            Files.writeString(path, whatToWrite);
        } catch (IOException e) {
            System.err.println("File saving error");
        }catch (NullPointerException e){
            System.err.println("No file selected");
        }
    }//GEN-LAST:event_jSaveActionPerformed
    private void jUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUploadActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        try{
            File readFile = chooser.getSelectedFile();
            Scanner reader = new Scanner(readFile);
            body.vectors.clear();
            body.vectors.add(new Vector(0, 0, "Result Vector", Color.RED, Integer.parseInt(jVariableX.getText()), Integer.parseInt(jVariableY.getText())));
            while(reader.hasNextLine()){
                String[] splitted = reader.nextLine().split(";");
                body.vectors.add(new Vector(Double.valueOf(splitted[1]), Double.valueOf(splitted[2]), splitted[0], Color.RED, Integer.parseInt(jVariableX.getText()), Integer.parseInt(jVariableY.getText())));
            }
            calcResultVector();
            repaint();
            updateJList();
        }catch(FileNotFoundException e){
            System.err.println("Reading file error");
        }catch(NullPointerException e){
            System.err.println("No file selected");
        }
    }//GEN-LAST:event_jUploadActionPerformed
    private void jResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetActionPerformed
        timer.stop();
        body.vectors.clear();
        body.vectors.add(new Vector(0, 0, "Result Vector", Color.GREEN, Integer.parseInt(jVariableX.getText()), Integer.parseInt(jVariableY.getText())));
        updateJList();
        repaint();
        body.setX(centerX);
        body.setY(centerY);
    }//GEN-LAST:event_jResetActionPerformed
    private void jColorPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jColorPickerActionPerformed
        Color color = Color.BLACK;
        
        color  = JColorChooser.showDialog(null, "Select a color", color);
        
        int[] selectedVectors = jVectorList.getSelectedIndices();
        
        for(int i = 0; i < selectedVectors.length; i++){
            body.vectors.get(selectedVectors[i]).setColor(color);
        }
    }//GEN-LAST:event_jColorPickerActionPerformed
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
    private void jCanvasMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jCanvasMouseWheelMoved
        zoomer = true;
        //Zoom in
        if (evt.getWheelRotation() < 0) {
            zoomFactor *= 1.1;
            repaint();
        }
        //Zoom out
        if (evt.getWheelRotation() > 0) {
            zoomFactor /= 1.1;
            repaint();
        }
    }//GEN-LAST:event_jCanvasMouseWheelMoved
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        canvasX = jCanvas.getWidth();
        canvasY = jCanvas.getHeight();
    }//GEN-LAST:event_formComponentResized

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = jCanvas.getGraphics();
        Graphics2D graphics2D = (Graphics2D) g;
        if (zoomer) {
            AffineTransform at = new AffineTransform();

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            at.translate(xOffset, yOffset);
            at.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            graphics2D.transform(at);
            zoomer = false;
        }
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON); 
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.clearRect(0, 0, jCanvas.getWidth(), jCanvas.getHeight());
        for (int i = 0; i < body.vectors.size(); i++) {
            if(i == 0){
                graphics2D.setColor(new Color(52, 163, 97));
            }else if(containValue(i)){
                graphics2D.setColor(new Color(0, 115, 255));
            }else {
                graphics2D.setColor(body.vectors.get(i).getColor());
            }
            graphics2D.drawLine(body.getX(), body.getY(), (int)body.vectors.get(i).getX()+body.getX(), -(int)body.vectors.get(i).getY()+body.getY());
        }
        if((xDrag != 0 || yDrag != 0) && !startDrag){
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawLine(body.getX(), body.getY(), xDrag, yDrag);
        }
        graphics2D.setColor(new Color(157, 0, 255));
        for (int i = 0; i < lines.size(); i++) {
            Line l = lines.get(i);
            graphics2D.drawLine(l.getStartX(), l.getStartY(), l.getDestX(), l.getDestY());
        }
        graphics2D.drawLine(centerX, centerY, body.getX(), body.getY());
        body.paint(graphics2D);
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
    private javax.swing.JPanel jCanvas;
    private javax.swing.JButton jColorPicker;
    private javax.swing.JButton jDeleteVector;
    private javax.swing.JLabel jFirstLabel;
    private javax.swing.JTextField jFirstValue;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jNewVector;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JCheckBox jPolarCheckbox;
    private javax.swing.JButton jReset;
    private javax.swing.JButton jSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jSecondLabel;
    private javax.swing.JTextField jSecondValue;
    private javax.swing.JButton jStartAnimation;
    private javax.swing.JButton jStopAnimation;
    private javax.swing.JButton jUpload;
    private javax.swing.JTextField jVariableX;
    private javax.swing.JTextField jVariableY;
    private javax.swing.JList<String> jVectorList;
    private javax.swing.JTextPane jVectorName;
    // End of variables declaration//GEN-END:variables
}   
