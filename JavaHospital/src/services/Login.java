package services;
import javax.swing.*; //pentru interfata grafica - fereastra, butoane etc
import java.awt.*; // pentru interfata -  culori etc
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// clasa Login extinde clasa JFrame.
// clasa Login implementeaza clasa abstracta ActionListener
// Login devine o fereastra personalizata
// in Login se introduc datele contului detinut de un user pentru a se conecta si a-si accesa contul bancar online
// https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html

public class Login extends JFrame implements ActionListener
{
    private static Login instance;

    private JLabel messageLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn, exitBtn;

    private Login()
    {
        //constructorul superclasei (JFrame)
        super("JavaHospital");


        // label pentru un mesaj de bun venit
        messageLabel = new JLabel("Bine ati revenit!");
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font("AvantGarde", Font.BOLD,38));
        messageLabel.setBounds(150, 100, 450, 40);
        add(messageLabel);


        // label pentru username
        usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("AvantGarde", Font.BOLD,28));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(150,190,375,30);
        add(usernameLabel);


        // textField pentru username
        usernameField = new JTextField(15);
        usernameField.setBounds(325,190,230,30);
        usernameField.setForeground(Color.BLACK);
        usernameField.setFont(new Font("Arial",Font.BOLD,14));
        add(usernameField);


        // label pentru parola
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("AvantGarde", Font.BOLD,28));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(150,250,375,30);
        add(passwordLabel);


        // passwordField pentru parola --> parola nu e vizibila la introducere
        passwordField = new JPasswordField();
        passwordField.setBounds(325,250,230,30);
        passwordField.setFont(new Font("Arial", Font.BOLD,14));
        passwordLabel.setForeground(Color.WHITE);
        add(passwordField);


        //buton pentru login
        loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD,14));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setBounds(325,300,100,30);
        loginBtn.addActionListener(this);
        add(loginBtn);


        //buton pentru exit (iesirea din aplicatie)
        exitBtn = new JButton("Exit");
        exitBtn.setFont(new Font("Arial", Font.BOLD,14));
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setBackground(Color.BLACK);
        exitBtn.setBounds(445,300,100,30);
        exitBtn.addActionListener(this);
        add(exitBtn);


        //setare background
        ImageIcon background = new ImageIcon(ClassLoader.getSystemResource("resources/img/LoginBG.jpg"));
        Image background2 = background.getImage().getScaledInstance(850,450, Image.SCALE_DEFAULT);
        ImageIcon background3 = new ImageIcon(background2);
        JLabel backgroundImage = new JLabel(background3);
        backgroundImage.setBounds(0,0,850,450);
        setLayout(null);
        add(backgroundImage);


        //setari pentru dimensiunea fereastrei
        setSize(850,480);
        setLocation(450,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); //incarca fereastra
    }

    public static Login getInstance()
    {
        if(instance==null)
            instance = new Login();
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            if (e.getSource() == loginBtn)
            {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // urmeaza modificarea la integrarea bazei de date --> Etapa 2
                if (username.equals("admin") && password.equals("admin"))
                {
                    //JOptionPane.showMessageDialog(this, "Logarea a fost realizata!");
                    this.dispose();
                    new Home();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Username sau parola incorecte!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Curațare date dupa login esuat
                usernameField.setText("");
                passwordField.setText("");
            }
            else if (e.getSource() == exitBtn)
            {
                System.exit(0);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }

}
