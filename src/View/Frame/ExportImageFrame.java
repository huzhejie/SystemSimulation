package View.Frame;

import twaver.TWaverUtil;
import twaver.base.A.E.P;
import twaver.network.InteractionMode;
import twaver.network.TNetwork;
import twaver.network.inputhandler.DefaultInputHandler;
import twaver.network.inputhandler.ExportImageInputHandler;
import twaver.network.inputhandler.InputHandler;
import twaver.swing.TableLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

/**
 * Created by user on 2017/3/22.
 */
public class ExportImageFrame extends JDialog implements ActionListener {
    private JFileChooser chooser = null;
    private TNetwork network = null;
    private JRadioButton I = new JRadioButton(TWaverUtil.getString("export.whole"), true);
    private JRadioButton C = new JRadioButton(TWaverUtil.getString("export.element"));
    private JRadioButton F = new JRadioButton(TWaverUtil.getString("export.area"));
    private JRadioButton E = new JRadioButton(TWaverUtil.getString("export.divide"));
    private JLabel M = new JLabel(TWaverUtil.getString("export.zoom"));
    private JLabel H = new JLabel(TWaverUtil.getString("export.margin"));
    private JTextField G = new JTextField();
    private JTextField J = new JTextField("0");
    private JLabel L = new JLabel(TWaverUtil.getString("ROW"));
    private JTextField K = new JTextField("1");
    private JLabel B = new JLabel(TWaverUtil.getString("COLUMN"));
    private JTextField A = new JTextField("1");
    JButton D = new JButton(TWaverUtil.getString("CANCEL"));

    public ExportImageFrame(Frame parent,TNetwork network) {
        super(parent);
        this.network = network;
        this.B();
    }

    public ExportImageFrame(Dialog parent,TNetwork network) {
        super(parent);
        this.network = network;
        this.B();
    }

    private void B() {
//        this.setTitle(NetworkExportImageButton.this.getToolTipText());
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
//        this.G.setText(format.format(NetworkExportImageButton.this.network.getZoom()));
        ButtonGroup group = new ButtonGroup();
        group.add(this.I);
        group.add(this.C);
        group.add(this.F);
        group.add(this.E);
        double[] rows = new double[]{-2.0D, -2.0D, -2.0D, -2.0D, -2.0D, -2.0D, -2.0D};
        double[] columns = new double[]{-2.0D, -1.0D, -1.0D, -1.0D, -1.0D};
        TableLayout layout = new TableLayout(columns, rows);
        layout.setVGap(5);
        layout.setHGap(3);
        JPanel north_JP = new JPanel(new BorderLayout());
        JPanel pane = new JPanel(layout);
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton okButton = new JButton(TWaverUtil.getString("CONFIRM"));
        okButton.addActionListener(this);
        this.D.addActionListener(this);
        JPanel buttonPane = new JPanel(new FlowLayout(2));
        buttonPane.add(okButton);
        buttonPane.add(this.D);
        pane.add(this.I, "0,0,0,0");
        pane.add(this.C, "0,1");
        pane.add(this.H, "1,1");
        pane.add(this.J, "2,1,4,1");
        this.H.setVisible(false);
        this.H.setHorizontalAlignment(4);
        this.J.setVisible(false);
        this.C.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                ExportImageFrame.this.H.setVisible(ExportImageFrame.this.C.isSelected());
                ExportImageFrame.this.J.setVisible(ExportImageFrame.this.C.isSelected());
            }
        });
        pane.add(this.F, "0,2,0,2");
        pane.add(this.E, "0,3");
        pane.add(this.L, "1,3");
        pane.add(this.K, "2,3");
        pane.add(this.B, "3,3");
        pane.add(this.A, "4,3");
        this.L.setHorizontalAlignment(4);
        this.B.setHorizontalAlignment(4);
        this.L.setVisible(false);
        this.K.setVisible(false);
        this.B.setVisible(false);
        this.A.setVisible(false);
        this.E.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                ExportImageFrame.this.L.setVisible(ExportImageFrame.this.E.isSelected());
                ExportImageFrame.this.K.setVisible(ExportImageFrame.this.E.isSelected());
                ExportImageFrame.this.B.setVisible(ExportImageFrame.this.E.isSelected());
                ExportImageFrame.this.A.setVisible(ExportImageFrame.this.E.isSelected());
            }
        });
        this.M.setHorizontalAlignment(4);
        pane.add(this.M, "1,5");
        pane.add(this.G, "2,5,4,5");
        north_JP.add(pane);
        north_JP.add(buttonPane, "South");
        this.setContentPane(north_JP);
        this.setModal(true);
        this.pack();
        this.setSize(360, this.getSize().height);
        TWaverUtil.centerWindow(this);
        this.setDefaultCloseOperation(2);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        if(e.getSource() != this.D) {
            if(network!=null)
            this.A(network);
            this.dispose();
        }
    }

    private void A(TNetwork network) {
        int margin = 0;
        double zoom = network.getZoom();

        try {
            margin = Integer.parseInt(this.J.getText());
        } catch (Exception var12) {
            this.J.setText(margin + "");
        }

        try {
            zoom = Double.parseDouble(this.G.getText());
        } catch (Exception var11) {
            this.G.setText(zoom + "");
        }

        int row = 1;
        int col = 1;

        try {
            row = Integer.parseInt(this.K.getText());
        } catch (Exception var10) {
            this.K.setText(row + "");
        }

        try {
            col = Integer.parseInt(this.A.getText());
        } catch (Exception var9) {
            this.A.setText(col + "");
        }

        if(chooser == null) {
            chooser = P.C();
        }

        if(this.F.isSelected()) {
            InteractionMode returnVal = network.getInteractionMode();
            InputHandler[] fileName = new InputHandler[]{new DefaultInputHandler(network), new ExportImageInputHandler(network, chooser, zoom, returnVal)};
            network.setInteractionMode(new InteractionMode(fileName));
        } else {
            String formatName;
            int returnVal1;
            String fileName1;
            if(this.E.isSelected()) {
                returnVal1 = chooser.showSaveDialog(P.B(network));
                if(returnVal1 != 0) {
                    return;
                }

                fileName1 = chooser.getSelectedFile().getAbsolutePath();
                formatName = chooser.getFileFilter().getDescription();
                network.exportDividedImages(fileName1, formatName, row, col, zoom);
            } else {
                returnVal1 = chooser.showSaveDialog(P.B(network));
                if(returnVal1 != 0) {
                    return;
                }

                fileName1 = chooser.getSelectedFile().getAbsolutePath();
                formatName = chooser.getFileFilter().getDescription();
                if(this.I.isSelected()) {
                    network.exportImage(fileName1, formatName, zoom);
                } else if(this.C.isSelected()) {
                    network.exportImageCoverElements(fileName1, formatName, margin, zoom);
                }
            }
        }

    }
}

