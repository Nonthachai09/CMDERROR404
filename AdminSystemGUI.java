import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminSystemGUI extends JFrame {

    public AdminSystemGUI() {
        setTitle("Admin System - ระบบจองห้องประชุม");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- 1. Sidebar (ซ้าย) ---
        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.BLACK);
        sidebar.setPreferredSize(new Dimension(250, 800));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // หัวข้อ Sidebar
        JLabel titleLabel = new JLabel("<html><div style='border-bottom: 2px solid #3b82f6;'>Admin System</div></html>");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        sidebar.add(titleLabel);

        JLabel subTitleLabel = new JLabel("ระบบจองห้องประชุม");
        subTitleLabel.setForeground(Color.WHITE);
        subTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        sidebar.add(subTitleLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 40)));

        // ปุ่มเมนู
        sidebar.add(createMenuButton("ภาพรวมระบบ", new Color(37, 99, 235))); // สีน้ำเงิน active
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("จัดการห้องประชุม", Color.BLACK));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("จัดการการจอง", Color.BLACK));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("จัดการผู้ใช้", Color.BLACK));

        // ปุ่ม Logout ด้านล่าง
        sidebar.add(Box.createVerticalGlue());
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(new Color(59, 130, 246));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setMaximumSize(new Dimension(200, 40));
        sidebar.add(logoutBtn);

        // --- 2. Main Content (ขวา) ---
        JPanel mainContent = new JPanel();
        mainContent.setBackground(Color.WHITE);
        mainContent.setLayout(new BorderLayout(20, 20));
        mainContent.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // ส่วนบน: ค้นหา
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(Color.WHITE);
        JTextField searchField = new JTextField("ค้นหา", 15);
        searchField.setBackground(new Color(37, 99, 235));
        searchField.setForeground(Color.WHITE);
        topPanel.add(searchField);
        mainContent.add(topPanel, BorderLayout.NORTH);

        // ส่วนกลาง: ตารางและกราฟ
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // ตาราง
        JLabel tableLabel = new JLabel("จำนวนห้องประชุมทั้งหมด");
        tableLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        centerPanel.add(tableLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        String[] columns = {"ชื่อห้อง", "สถานะการจองห้อง"};
        Object[][] data = {
            {"ROOM101", "ว่าง"},
            {"ROOM102", "ไม่ว่าง"},
            {"ROOM103", "ว่าง"},
            {"ROOM104", "ว่าง"},
            {"ROOM105", "ว่าง"}
        };
        JTable table = new JTable(new DefaultTableModel(data, columns));
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 180));
        centerPanel.add(scrollPane);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // กราฟจำลอง (ใช้ JPanel วาดแท่ง)
        JLabel graphLabel = new JLabel("จำนวนการจองสัปดาห์นี้");
        graphLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        centerPanel.add(graphLabel);
        centerPanel.add(new JLabel("26 มกราคม - 1 กุมภาพันธ์"));
        
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int[] values = {2, 4, 5, 2, 4, 2, 3};
                String[] days = {"M", "T", "W", "T", "F", "S", "S"};
                g.setColor(new Color(37, 99, 235));
                for (int i = 0; i < values.length; i++) {
                    int h = values[i] * 30;
                    g.fillRect(50 + (i * 60), 180 - h, 20, h);
                    g.setColor(Color.BLACK);
                    g.drawString(days[i], 55 + (i * 60), 200);
                    g.setColor(new Color(37, 99, 235));
                    g.drawString(String.valueOf(values[i]), 55 + (i * 60), 175 - h);
                }
            }
        };
        chartPanel.setPreferredSize(new Dimension(500, 250));
        chartPanel.setBackground(Color.WHITE);
        centerPanel.add(chartPanel);

        mainContent.add(centerPanel, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(mainContent, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 45));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminSystemGUI().setVisible(true);
        });
    }
}