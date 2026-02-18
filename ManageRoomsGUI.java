import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ManageRoomsGUI extends JFrame {

    public ManageRoomsGUI() {
        setTitle("Admin System - จัดการห้องประชุม");
        setSize(1100, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(243, 244, 246)); // Gray-100
        setLayout(new BorderLayout());

        // --- 1. Sidebar (เหมือนหน้าแรก) ---
        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.BLACK);
        sidebar.setPreferredSize(new Dimension(250, 850));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("<html><div style='border-bottom: 2px solid #3b82f6;'>Admin System</div></html>");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        sidebar.add(titleLabel);

        JLabel subTitleLabel = new JLabel("ระบบจองห้องประชุม");
        subTitleLabel.setForeground(Color.WHITE);
        subTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sidebar.add(subTitleLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 40)));

        sidebar.add(createMenuButton("ภาพรวมระบบ", Color.BLACK));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("จัดการห้องประชุม", new Color(37, 99, 235))); // Active
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("จัดการการจอง", Color.BLACK));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(createMenuButton("จัดการผู้ใช้", Color.BLACK));

        sidebar.add(Box.createVerticalGlue());
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(new Color(96, 165, 250));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setMaximumSize(new Dimension(210, 40));
        sidebar.add(logoutBtn);

        add(sidebar, BorderLayout.WEST);

        // --- 2. Main Content (พื้นที่แสดง Card ห้องประชุม) ---
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        mainContent.setBackground(new Color(243, 244, 246));
        mainContent.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Panel สำหรับรวม Card ทั้งหมด
        JPanel cardsContainer = new JPanel();
        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        cardsContainer.setBackground(new Color(243, 244, 246));

        // เพิ่มรายการห้องประชุม (ข้อมูลจำลองตามภาพ)
        cardsContainer.add(createRoomCard("ROOM101", "35", "โปรเจคเตอร์ 1 เครื่อง ไมค์ 5 ตัว", "ติดจอง"));
        cardsContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        cardsContainer.add(createRoomCard("ROOM102", "9", "ทีวี 1 เครื่อง ไมค์ 3 ตัว", "ปิดปรับปรุง"));
        cardsContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        cardsContainer.add(createRoomCard("ROOM103", "16", "ทีวี 1 เครื่อง ไมค์ 3 ตัว ลำโพง 5 ตัว โปรเจคเตอร์ 1 ตัว", "ว่าง"));
        cardsContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        cardsContainer.add(createRoomCard("ROOM104", "12", "ทีวี 1 เครื่อง ไมค์ 3 ตัว Macbook 1 เครื่อง", "ว่าง"));

        // ใส่ ScrollPane เพื่อให้เลื่อนดูได้ถ้าห้องเยอะ
        JScrollPane scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainContent.add(scrollPane, BorderLayout.CENTER);

        add(mainContent, BorderLayout.CENTER);
    }

    // ฟังก์ชันสร้าง Card ห้องประชุม
    private JPanel createRoomCard(String name, String cap, String tools, String status) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setLayout(new BorderLayout());
        card.setMaximumSize(new Dimension(800, 200));
        card.setPreferredSize(new Dimension(800, 200));

        // ส่วนรูปภาพ (จำลองเป็นสี่เหลี่ยมสีเทา)
        JPanel imgPlaceholder = new JPanel();
        imgPlaceholder.setBackground(new Color(209, 213, 219));
        imgPlaceholder.setPreferredSize(new Dimension(250, 200));
        imgPlaceholder.add(new JLabel("Image Placeholder"));
        card.add(imgPlaceholder, BorderLayout.WEST);

        // ส่วนเนื้อหาด้านขวา
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // ข้อความรายละเอียด
        String infoHtml = "<html><body style='font-family:Tahoma;'>"
                + "<h2 style='margin-bottom:5px;'>" + name + "</h2>"
                + "<p><b>จำนวน :</b> " + cap + " คน</p>"
                + "<p><b>อุปกรณ์ :</b> " + tools + "</p>"
                + "<p><b>สถานะห้อง :</b> " + status + "</p>"
                + "</body></html>";
        JLabel infoLabel = new JLabel(infoHtml);
        infoPanel.add(infoLabel, BorderLayout.CENTER);

        // ปุ่มจัดการ (ล่างขวา)
        JButton manageBtn = new JButton("จัดการ");
        manageBtn.setBackground(new Color(37, 99, 235));
        manageBtn.setForeground(Color.WHITE);
        manageBtn.setFocusPainted(false);
        manageBtn.setPreferredSize(new Dimension(120, 35));
        
        JPanel btnWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnWrapper.setOpaque(false);
        btnWrapper.add(manageBtn);
        infoPanel.add(btnWrapper, BorderLayout.SOUTH);

        card.add(infoPanel, BorderLayout.CENTER);
        return card;
    }

    private JButton createMenuButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 45));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageRoomsGUI().setVisible(true));
    }
}