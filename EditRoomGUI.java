import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class EditRoomGUI extends JFrame {

    public EditRoomGUI() {
        setTitle("Admin System - แก้ไขข้อมูลห้อง");
        setSize(1100, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(243, 244, 246));
        setLayout(new BorderLayout());

        // --- 1. Sidebar (ใช้ดีไซน์เดิมเพื่อให้เข้าชุดกัน) ---
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
        logoutBtn.setMaximumSize(new Dimension(210, 40));
        sidebar.add(logoutBtn);

        add(sidebar, BorderLayout.WEST);

        // --- 2. Main Content Area ---
        JPanel mainContent = new JPanel(new GridBagLayout());
        mainContent.setBackground(new Color(243, 244, 246));
        
        // --- Edit Card (แผ่นขาวตรงกลาง) ---
        JPanel editCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30));
                g2.dispose();
            }
        };
        editCard.setOpaque(false);
        editCard.setPreferredSize(new Dimension(750, 350));
        editCard.setLayout(new BorderLayout());

        // ส่วนรูปภาพด้านซ้ายของ Card
        JPanel imgBox = new JPanel();
        imgBox.setBackground(new Color(200, 200, 200));
        imgBox.setPreferredSize(new Dimension(300, 350));
        imgBox.add(new JLabel("Room Image")); // ใส่รูป ROOM101 จริงๆ ได้ที่นี่
        editCard.add(imgBox, BorderLayout.WEST);

        // ส่วนฟอร์มแก้ไขด้านขวา
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(null); // ใช้ Absolute เพื่อจัดตำแหน่งตามภาพเป๊ะๆ
        formPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel headLabel = new JLabel("จัดการข้อมูลห้อง");
        headLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        headLabel.setBounds(30, 20, 200, 25);
        formPanel.add(headLabel);

        JLabel roomName = new JLabel("ROOM101");
        roomName.setFont(new Font("Tahoma", Font.BOLD, 18));
        roomName.setBounds(30, 45, 200, 25);
        formPanel.add(roomName);

        JLabel editTitle = new JLabel("แก้ไขข้อมูล");
        editTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        editTitle.setBounds(30, 85, 200, 20);
        formPanel.add(editTitle);

        // จำนวนอุปกรณ์
        JLabel deviceLabel = new JLabel("จำนวนอุปกรณ์");
        deviceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        deviceLabel.setBounds(30, 110, 120, 20);
        formPanel.add(deviceLabel);

        JLabel projLabel = new JLabel("โปรเจคเตอร์ :  1");
        projLabel.setBounds(30, 135, 120, 20);
        formPanel.add(projLabel);

        JLabel micLabel = new JLabel("ไมค์ :           5");
        micLabel.setBounds(30, 155, 120, 20);
        formPanel.add(micLabel);

        // สถานะห้อง (Checkboxes ตามภาพ)
        JLabel statusLabel = new JLabel("สถานะห้อง");
        statusLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        statusLabel.setBounds(180, 110, 120, 20);
        formPanel.add(statusLabel);

        JCheckBox cb1 = new JCheckBox(" ว่าง", false);
        cb1.setBounds(180, 135, 100, 20);
        cb1.setOpaque(false);
        formPanel.add(cb1);

        JCheckBox cb2 = new JCheckBox(" ไม่ว่าง", true);
        cb2.setBounds(180, 155, 100, 20);
        cb2.setOpaque(false);
        formPanel.add(cb2);

        JCheckBox cb3 = new JCheckBox(" ปิดปรับปรุง", false);
        cb3.setBounds(180, 175, 100, 20);
        cb3.setOpaque(false);
        formPanel.add(cb3);

        // ปุ่มด้านล่าง
        JButton backBtn = new JButton("ย้อนกลับ");
        backBtn.setBackground(Color.GRAY);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBounds(30, 250, 120, 35);
        formPanel.add(backBtn);

        JButton confirmBtn = new JButton("ยืนยันการแก้ไข");
        confirmBtn.setBackground(new Color(46, 204, 113)); // สีเขียวตามภาพ
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.setBounds(260, 250, 150, 35);
        formPanel.add(confirmBtn);

        editCard.add(formPanel, BorderLayout.CENTER);
        mainContent.add(editCard); // เพิ่ม Card ลงในพื้นที่หลัก

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
        SwingUtilities.invokeLater(() -> new EditRoomGUI().setVisible(true));
    }
}