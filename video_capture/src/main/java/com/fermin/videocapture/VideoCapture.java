/*
 * Created by JFormDesigner on Tue Jun 29 13:59:37 CST 2021
 */

package com.fermin.videocapture;

import com.fermin.videocapture.bo.CaptureRequest;
import com.fermin.videocapture.util.CaptureUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.regex.Pattern;

/**
 * @author unknown
 */
public class VideoCapture extends JFrame {

    //标志：是否在截图过程中
    private static boolean isExecute = false;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VideoCapture frame = new VideoCapture();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VideoCapture() {
        initComponents();
    }

    /**
     * 打开文件夹，选择文件
     *
     * @param e
     * @param jtf
     */
    private void selectFileActionPerformed(ActionEvent e, JTextField jtf) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //fileChooser.setDialogTitle("请选择文件");
        fileChooser.setFileFilter(new FileNameExtensionFilter("video file filter", CaptureUtil.VIDEO_FILE_EXTENSION));
        int val = fileChooser.showOpenDialog(null);    //文件打开对话框
        if (val == fileChooser.APPROVE_OPTION) {
            //正常选择文件
            File file = fileChooser.getSelectedFile();
            jtf.setText(file.getAbsolutePath());
        } else {

        }
    }


    /**
     * @param e
     */
    private void okButtonActionPerformed(ActionEvent e) {
        if (isExecute) {
            JOptionPane.showMessageDialog(null, "please hold on!");
            return;
        }

        try {
            isExecute = true;
            String videoPath;
            if (StringUtils.isEmpty(videoPath = textField1.getText()) || !FileUtils.getFile(videoPath).exists()) {
                JOptionPane.showMessageDialog(null, "please check the selected video file or path!");
                return;
            }

            String outPutPath = null;
            if (StringUtils.isEmpty(outPutPath = textField3.getText()) || !FileUtils.getFile(outPutPath).isDirectory()) {
                JOptionPane.showMessageDialog(null, "please chek the output folder!");
                return;
            }

            boolean useOriginalSize;
            String width = null, height = null;
            if (!(useOriginalSize = radioButton2.isSelected())) {
                if (StringUtils.isEmpty(width = textField4.getText()) || !this.check(width) ||
                        StringUtils.isEmpty(height = textField5.getText()) || !this.check(height)) {
                    JOptionPane.showMessageDialog(null, "please input the correct width or height!");
                    return;
                }

            }

            boolean isRandom;
            String numberOfCaptures = null, timeStr = null;
            if (!(isRandom = radioButton1.isSelected())) {

                timeStr = textField2.getText();
                Pattern pattern = Pattern.compile("^[0-2]?[0-4]\\d*:[0-5]?[0-9]:[0-5]?[0-9]$");
                if (!pattern.matcher(timeStr).matches()) {
                    JOptionPane.showMessageDialog(null, "please the check capture time ,format is HH:mm:ss!");
                    return;
                }
            } else {

                if (StringUtils.isEmpty(numberOfCaptures = spinner1.getValue().toString()) || !this.check(numberOfCaptures)) {
                    JOptionPane.showMessageDialog(null, "please enter the correct number of captures!");
                    return;
                }
            }

            CaptureRequest request = new CaptureRequest();
            request.setCaptureTime(timeStr);
            request.setCount(numberOfCaptures);
            request.setHeight(height);
            request.setWidth(width);
            request.setOriginalSize(useOriginalSize);
            request.setSavePath(outPutPath);
            request.setTimeRandom(isRandom);

            CaptureUtil.getCover(videoPath, request);

        } catch (Exception exception) {
            exception.printStackTrace();

        } finally {
            isExecute = false;
        }
    }

    /**
     * 校验是不是正整数
     * ^[1-9]\d*$
     *
     * @param value
     * @return
     */
    private boolean check(String value) {
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        return pattern.matcher(value).matches();
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        spinner1.setValue(0);
    }


    private void radioButton2ActionPerformed(ActionEvent e) {
        if (radioButton2.isSelected()) {
            textField4.setEnabled(false);
            textField5.setEnabled(false);
        } else {
            textField4.setEnabled(true);
            textField5.setEnabled(true);
        }
    }

    private void radioButton1ActionPerformed(ActionEvent e) {
        if (radioButton1.isSelected()) {
            textField2.setEnabled(false);
            spinner1.setEnabled(true);
        } else {
            spinner1.setEnabled(false);
            spinner1.setValue(1);
            textField2.setEnabled(true);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        button1 = new JButton();
        label2 = new JLabel();
        spinner1 = new JSpinner();
        label3 = new JLabel();
        textField2 = new JTextField();
        radioButton1 = new JRadioButton();
        label4 = new JLabel();
        textField3 = new JTextField();
        button2 = new JButton();
        button3 = new JButton();
        label5 = new JLabel();
        textField4 = new JTextField();
        textField5 = new JTextField();
        radioButton2 = new JRadioButton();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/cartoon-avatar.png")).getImage());
        setTitle("\u89c6\u9891\u622a\u56fe\u5de5\u5177");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
                    javax.swing.border.EmptyBorder(0, 0, 0, 0), "", javax
                    .swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java
                    .awt.Font("Dia\u006cog", java.awt.Font.BOLD, 12), java.awt
                    .Color.red), dialogPane.getBorder()));
            dialogPane.addPropertyChangeListener(new java.beans.
                    PropertyChangeListener() {
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("bord\u0065r".equals(e.getPropertyName())) throw new RuntimeException();
                }
            });
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout) contentPanel.getLayout()).columnWidths = new int[]{0, 0, 69, 69, 0, 91, 0};
                ((GridBagLayout) contentPanel.getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout) contentPanel.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout) contentPanel.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- label1 ----
                label1.setText("\u6587\u4ef6\u9009\u62e9");
                label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
                contentPanel.add(label1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- textField1 ----
                textField1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
                contentPanel.add(textField1, new GridBagConstraints(2, 0, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- button1 ----
                button1.setText("\u9009\u62e9");
                button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
                button1.addActionListener(e -> selectFileActionPerformed(e, textField1));
                contentPanel.add(button1, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- label2 ----
                label2.setText("\u751f\u6210\u6570\u91cf");
                label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
                contentPanel.add(label2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));
                contentPanel.add(spinner1, new GridBagConstraints(2, 1, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- label3 ----
                label3.setText("\u622a\u53d6\u56fe\u7247\u65f6\u95f4");
                label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
                contentPanel.add(label3, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- textField2 ----
                textField2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
                contentPanel.add(textField2, new GridBagConstraints(2, 2, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- radioButton1 ----
                radioButton1.setText("\u968f\u673a\u65f6\u95f4");
                radioButton1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
                radioButton1.addActionListener(e -> radioButton1ActionPerformed(e));
                contentPanel.add(radioButton1, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- label4 ----
                label4.setText("\u8f93\u51fa\u76ee\u5f55");
                label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
                contentPanel.add(label4, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- textField3 ----
                textField3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
                contentPanel.add(textField3, new GridBagConstraints(2, 3, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- button2 ----
                button2.setText("\u9009\u62e9");
                button2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
                button2.addActionListener(e -> selectFileActionPerformed(e, textField3));
                contentPanel.add(button2, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- button3 ----
                button3.setText("\u6253\u5f00\u76ee\u5f55");
                button3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
                contentPanel.add(button3, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                //---- label5 ----
                label5.setText("\u622a\u53d6\u5927\u5c0f");
                label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
                contentPanel.add(label5, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));
                contentPanel.add(textField4, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));
                contentPanel.add(textField5, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- radioButton2 ----
                radioButton2.setText("\u539f\u59cb\u89c6\u9891\u5927\u5c0f");
                radioButton2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
                radioButton2.addActionListener(e -> radioButton2ActionPerformed(e));
                contentPanel.add(radioButton2, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[]{0, 85, 80};
                ((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[]{1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("\u751f\u6210");
                okButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("\u53d6\u6d88");
                cancelButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
                cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    private JLabel label2;
    private JSpinner spinner1;
    private JLabel label3;
    private JTextField textField2;
    private JRadioButton radioButton1;
    private JLabel label4;
    private JTextField textField3;
    private JButton button2;
    private JButton button3;
    private JLabel label5;
    private JTextField textField4;
    private JTextField textField5;
    private JRadioButton radioButton2;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
