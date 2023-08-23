import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }
}

class StudentManagementSystemGUI extends JFrame {
    private ArrayList<Student> students = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;

    private JTextField nameField;
    private JTextField rollNumberField;
    private JTextField gradeField;

    public StudentManagementSystemGUI() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        tableModel = new DefaultTableModel(new Object[] { "Name", "Roll Number", "Grade" }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        nameField = new JTextField(15);
        rollNumberField = new JTextField(5);
        gradeField = new JTextField(2);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String rollNumberStr = rollNumberField.getText();
                String grade = gradeField.getText();

                if (name.isEmpty() || rollNumberStr.isEmpty() || grade.isEmpty()) {
                    showMessage("Please fill in all fields.");
                } else {
                    try {
                        int rollNumber = Integer.parseInt(rollNumberStr);
                        Student student = new Student(name, rollNumber, grade);
                        students.add(student);
                        updateDisplay();
                        clearFields();
                        showMessage("Student added successfully.");
                    } catch (NumberFormatException ex) {
                        showMessage("Invalid roll number.");
                    }
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll Number:"));
        inputPanel.add(rollNumberField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateDisplay() {
        tableModel.setRowCount(0);
        for (Student student : students) {
            tableModel.addRow(new Object[] { student.getName(), student.getRollNumber(), student.getGrade() });
        }
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StudentManagementSystemGUI gui = new StudentManagementSystemGUI();
                gui.setVisible(true);
            }
        });
    }
}
