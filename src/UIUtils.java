import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class UIUtils {

    // Apply basic modern L&F tweaks
    public static void applyModernTheme() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Table.font", new Font("Segoe UI", Font.PLAIN, 13));
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 13));
        UIManager.put("Panel.background", new Color(245, 248, 255));
        UIManager.put("ScrollPane.background", new Color(245, 248, 255));
        UIManager.put("Table.selectionBackground", new Color(34, 139, 230));
        UIManager.put("Table.selectionForeground", Color.white);
        UIManager.put("List.selectionBackground", new Color(34, 139, 230));
        UIManager.put("List.selectionForeground", Color.white);
    }

    // Rounded button with hover effect
    public static class RoundedButton extends JButton {
        private Color base = new Color(34, 139, 230);
        private Color hover = new Color(20, 120, 215);
        private Color text = Color.white;

        public RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setForeground(Color.WHITE);
            setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setFont(new Font("Segoe UI", Font.BOLD, 14));
            setBackground(base);
            initListeners();
        }

        private void initListeners() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    base = hover;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    base = new Color(34, 139, 230);
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(base);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            g2.setColor(text);
            FontMetrics fm = g.getFontMetrics();
            Rectangle r = new Rectangle(0, 0, getWidth(), getHeight());
            String s = getText();
            int x = (r.width - fm.stringWidth(s)) / 2;
            int y = (r.height - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(s, x, y);
            g2.dispose();
        }
    }

    // Simple gradient background panel
    public static class GradientPanel extends JPanel {
        private Color top = new Color(10, 95, 210);
        private Color bottom = new Color(72, 201, 176);

        public GradientPanel() {
            setOpaque(true);
        }

        public void setColors(Color top, Color bottom) {
            this.top = top;
            this.bottom = bottom;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            int w = getWidth(), h = getHeight();
            GradientPaint gp = new GradientPaint(0, 0, top, 0, h, bottom);
            g2.setPaint(gp);
            g2.fillRect(0, 0, w, h);
            g2.dispose();
        }
    }

    public static JPanel createCard(String title, JComponent body) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.white);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                new EmptyBorder(12, 12, 12, 12)));
        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setBorder(new EmptyBorder(0, 0, 8, 0));
        card.add(lbl, BorderLayout.NORTH);
        card.add(body, BorderLayout.CENTER);
        return card;
    }

    public static void styleTable(JTable table) {
        table.setRowHeight(26);
        table.setFillsViewportHeight(true);
        table.setShowGrid(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(34, 139, 230));
        table.setSelectionForeground(Color.white);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(252, 254, 255));
                    } else {
                        c.setBackground(Color.white);
                    }
                }
                return c;
            }
        };
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    public static void styleTableHeader(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setBackground(new Color(240, 244, 255));
        header.setForeground(new Color(40, 40, 40));
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(240, 244, 255));
                setHorizontalAlignment(CENTER);
                return c;
            }
        });
    }

    public static void decorateScrollPane(JScrollPane sp) {
        sp.setBorder(BorderFactory.createLineBorder(new Color(225, 230, 240)));
        sp.getViewport().setBackground(Color.white);
    }

    public static JPanel createHeader(String title) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.BOLD, 26));
        t.setForeground(Color.WHITE);

        JComponent dots = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int h = getHeight();
                int r = Math.min(20, h - 8);
                int y = (h - r) / 2;
                int x = 0;
                Color[] cs = new Color[]{new Color(255, 179, 71), new Color(72, 201, 176), new Color(34, 139, 230)};
                for (Color c : cs) {
                    g2.setColor(c);
                    g2.fillOval(x, y, r, r);
                    x += r + 8;
                }
                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(80, 32);
            }
        };

        p.setBorder(new EmptyBorder(16, 20, 8, 20));
        p.add(t, BorderLayout.WEST);
        p.add(dots, BorderLayout.EAST);
        return p;
    }
}
