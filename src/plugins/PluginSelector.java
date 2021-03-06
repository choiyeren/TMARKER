/*
 * Copyright (C) 2015 Peter J. Schüffler
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package plugins;

import TMARKERPluginInterface.Pluggable;
import java.awt.Component;
import java.net.URL;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.jdesktop.swingx.JXTable;
import tmarker.tmarker;

/**
 * Let the user choose which plugins to use.
 *
 * @author Peter J. Schüffler
 */
public class PluginSelector extends javax.swing.JFrame {

    tmarker t;
    String[] selectedPlugins;

    /**
     * Creates new form PluginSelector.
     *
     * @param t The tmarker instance to access plugin information.
     */
    public PluginSelector(tmarker t) {
        initComponents();
        this.t = t;
        this.setIconImage(t.getIconImage());
        this.setLocationRelativeTo(t);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setTitle("Please select the plugins to be installed:");
        setPreferredSize(new java.awt.Dimension(1000, 500));

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Selected", "Name", "Version", "Author", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jXTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jXTable1.setColumnMargin(10);
        jScrollPane1.setViewportView(jXTable1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("<html>Following plugins are available at<a href=\"http://www.nexus.ethz.ch\">http://www.nexus.ethz.ch</a>. Please choose the plugins you want to use and restart <b>TMARKER using online plugins</b>:<br>\n(<b>Hint:</b> if you want to select local plugins, just copy the desired plugins into the local plugin folder.)</html>");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        updateParam_loadedPlugins();
        this.setVisible(false);
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
            java.util.logging.Logger.getLogger(PluginSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PluginSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PluginSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PluginSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PluginSelector(null).setVisible(true);
            }
        });
    }

    /**
     * Lists for available plugins (online or locally, depending on whether
     * online or local plugins are setup in the program options). Loaded plugins
     * are selected, not loaded plugins are delected.
     */
    public void initializePluginList() {
        if (t != null) {
            // loaded plugins
            List<Pluggable> loadedPlugins = t.getPlugins();

            // available plugins
            String[] availablePlugins = new String[]{};
            try {
                availablePlugins = PluginLoader.listAvailablePluginsFromURL(new URL("http://www.nexus.ethz.ch/equipment_tools/software/tmarker/Plugins.html"));
            } catch (Exception e) {
                //e.printStackTrace();
            }

            ((DefaultTableModel) jXTable1.getModel()).setRowCount(0);
            for (String p : availablePlugins) {
                boolean isAlreadyloaded = false;
                String[] compartments = p.split("\\|\\|\\|");
                String name = compartments[0];
                String version = compartments[1];
                String author = compartments[2];
                String description = compartments[3];
                for (Pluggable lp : loadedPlugins) {
                    if (name.equals(lp.getPluginName())) { // && version.equals(lp.getVersion())) {
                        isAlreadyloaded = true;
                        break;
                    }
                }
                ((DefaultTableModel) jXTable1.getModel()).addRow(new Object[]{isAlreadyloaded, name, version, author, description});
            }
            
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setVerticalAlignment(JLabel.TOP);
            jXTable1.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
            
            updateRowHeights(jXTable1);
            resizeColumnWidth(jXTable1);
            
            revalidate();
            repaint();
        }
    }

    public void updateParam_loadedPlugins() {
        int n = 0;
        for (int i = 0; i < jXTable1.getRowCount(); i++) {
            if (((Boolean) (jXTable1.getValueAt(i, jXTable1.convertColumnIndexToView(0))))) {
                n++;
            }
        }
        selectedPlugins = new String[n];
        n = 0;
        for (int i = 0; i < jXTable1.getRowCount(); i++) {
            if (((Boolean) (jXTable1.getValueAt(i, jXTable1.convertColumnIndexToView(0))))) {
                selectedPlugins[n++] = (String) jXTable1.getValueAt(i, jXTable1.convertColumnIndexToView(1)) + "|||" + jXTable1.getValueAt(i, jXTable1.convertColumnIndexToView(2)) + "|||" + jXTable1.getValueAt(i, jXTable1.convertColumnIndexToView(3));
            }
        }
    }

    public String[] getParam_loadedPlugins() {
        return selectedPlugins;
    }

    public void setParam_loadedPlugins(String[] loadedPlugins) {
        this.selectedPlugins = loadedPlugins;
    }

    private void updateRowHeights(JXTable table) {
        try {
            for (int row = 0; row < table.getRowCount(); row++) {
                int rowHeight = table.getRowHeight();

                for (int column = 0; column < table.getColumnCount(); column++) {
                    Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }

                //table.setRowHeight(row, rowHeight);
                table.setRowHeight(row, 70);
            }
        } catch (ClassCastException e) {
        }
    }
    
    public void resizeColumnWidth(JXTable table) {
    final TableColumnModel columnModel = table.getColumnModel();
    /*for (int column = 0; column < table.getColumnCount(); column++) {
        int width = 50; // Min width
        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer renderer = table.getCellRenderer(row, column);
            Component comp = table.prepareRenderer(renderer, row, column);
            width = Math.max(comp.getPreferredSize().width, width);
        }
        
        columnModel.getColumn(column).setPreferredWidth(width);
    }*/
    columnModel.getColumn(0).setPreferredWidth(60);
    columnModel.getColumn(1).setPreferredWidth(180);
    columnModel.getColumn(2).setPreferredWidth(60);
    columnModel.getColumn(3).setPreferredWidth(120);
    columnModel.getColumn(4).setPreferredWidth(580);
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTable jXTable1;
    // End of variables declaration//GEN-END:variables

}
