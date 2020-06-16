import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.GroupLayout.Alignment.*;

public class MainWindow extends JFrame {
    private final Color BG_COLOR = new Color(42, 42, 42);
    private final Color IN_AREA_COLOR = new Color(22, 22, 22);
    private final Color OUT_AREA_COLOR = new Color(22, 22, 22);
    private final Color BUTTONS_COLOR = new Color(56, 56, 56);
    private final Color GO_BUTTON_TEXT_COLOR = new Color(254, 63, 111);
    private final Color IN_FIELD_KEY_COLOR = new Color(32, 32, 32);
    private final Font MAIN_LABEL_FONT = new Font("Arial", Font.PLAIN, 16);
    private final Font TEXT_FONT = new Font("Lucida Console", Font.PLAIN, 14);
    private final Font LABELS_FONT = new Font("Arial", Font.PLAIN, 12);
    private final Font BUTTONS_FONT = new Font("Arial", Font.PLAIN, 14);
    private final Font GO_BUTTON_FONT = new Font("Arial", Font.BOLD, 26);
    private final Dimension TEXT_AREAS_DIMENSION = new Dimension(600, 160);

    private int key;
    static String inMessage = "";
    static String outMessage = "";
    static String inPath;
    static String outPath;

    public MainWindow() {
        setTitle("Encrypt | Decrypt");
        setLocation(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        getContentPane().setBackground(BG_COLOR);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel mainLabel = new JLabel("Enter your text here or");
        mainLabel.setFont(MAIN_LABEL_FONT);
        mainLabel.setForeground(Color.lightGray);

        JButton openButton = new JButton("Open File");
        openButton.setBackground(BUTTONS_COLOR);
        openButton.setForeground(Color.lightGray);
        openButton.setBorderPainted(false);
        openButton.setFont(BUTTONS_FONT);
        openButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                openButton.setBackground(Color.gray);
                openButton.setForeground(Color.black);
            }

            public void mouseExited(MouseEvent evt) {
                openButton.setBackground(BUTTONS_COLOR);
                openButton.setForeground(Color.lightGray);
            }
        });

        JTextArea areaIn = new JTextArea();
        areaIn.setBackground(IN_AREA_COLOR);
        areaIn.setFont(TEXT_FONT);
        areaIn.setForeground(Color.white);
        areaIn.setCaretColor(Color.white);
        areaIn.setLineWrap(true);
        areaIn.setWrapStyleWord(true);
        JScrollPane scrollIn = new JScrollPane(areaIn);
        scrollIn.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollIn.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollIn.setBorder(BorderFactory.createEmptyBorder());
        scrollIn.setPreferredSize(TEXT_AREAS_DIMENSION);

        openButton.addActionListener(e ->{
            inPath = ReadWrite.getPath(this, "Choose a file");
            if (inPath != null) {
                inMessage = ReadWrite.readFile(inPath, this);
                areaIn.setText("");
                areaIn.setText(inMessage);
            }
        });

        JRadioButton shiftAlg = new JRadioButton("Shift", true);
        shiftAlg.setBackground(BG_COLOR);
        shiftAlg.setForeground(Color.lightGray);
        shiftAlg.setFont(LABELS_FONT);

        JRadioButton uniAlg = new JRadioButton("Unicode");
        uniAlg.setBackground(BG_COLOR);
        uniAlg.setForeground(Color.lightGray);
        uniAlg.setFont(LABELS_FONT);

        ButtonGroup algButtonGroup = new ButtonGroup();
        algButtonGroup.add(shiftAlg);
        algButtonGroup.add(uniAlg);

        JRadioButton encodeBtn = new JRadioButton("Encode", true);
        encodeBtn.setBackground(BG_COLOR);
        encodeBtn.setForeground(Color.lightGray);
        encodeBtn.setFont(LABELS_FONT);

        JRadioButton decodeBtn = new JRadioButton("Decode");
        decodeBtn.setBackground(BG_COLOR);
        decodeBtn.setForeground(Color.lightGray);
        decodeBtn.setFont(LABELS_FONT);

        ButtonGroup modeButtonGroup = new ButtonGroup();
        modeButtonGroup.add(encodeBtn);
        modeButtonGroup.add(decodeBtn);

        JLabel keyLabel = new JLabel("Key:");
        keyLabel.setForeground(Color.lightGray);
        keyLabel.setFont(LABELS_FONT);

        JTextField keyIn = new JTextField();
        keyIn.setBackground(IN_FIELD_KEY_COLOR);
        keyIn.setFont(TEXT_FONT);
        keyIn.setForeground(Color.lightGray);
        keyIn.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        keyIn.setText("0");
        keyIn.setCaretColor(Color.white);

        JCheckBox saveCheck = new JCheckBox("Save To File");
        saveCheck.setBackground(BG_COLOR);
        saveCheck.setForeground(Color.lightGray);
        saveCheck.setFont(LABELS_FONT);

        JTextArea areaOut = new JTextArea();
        areaOut.setBackground(OUT_AREA_COLOR);
        areaOut.setFont(TEXT_FONT);
        areaOut.setForeground(Color.white);
        areaOut.setEditable(false);
        areaOut.setLineWrap(true);
        areaOut.setWrapStyleWord(true);
        JScrollPane scrollOut = new JScrollPane(areaOut);
        scrollOut.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollOut.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollOut.setBorder(BorderFactory.createEmptyBorder());
        scrollOut.setPreferredSize(TEXT_AREAS_DIMENSION);

        JButton goButton = new JButton("GO!");
        goButton.setBackground(BUTTONS_COLOR);
        goButton.setForeground(GO_BUTTON_TEXT_COLOR);
        goButton.setBorderPainted(false);
        goButton.setFont(GO_BUTTON_FONT);
        goButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                goButton.setBackground(Color.gray);
                goButton.setForeground(Color.black);
            }

            public void mouseExited(MouseEvent evt) {
                goButton.setBackground(BUTTONS_COLOR);
                goButton.setForeground(GO_BUTTON_TEXT_COLOR);
            }
        });
        goButton.addActionListener(e ->{
            inMessage = areaIn.getText();
            if (!inMessage.isEmpty()) {
                key = Integer.parseInt(keyIn.getText());
                Encrypter encrypter = new Encrypter();

                if (shiftAlg.isSelected()) {
                    encrypter.setAlgorithm(new ShiftAlgorithm());
                } else {
                    encrypter.setAlgorithm(new UnicodeAlgorithm());
                }

                if (encodeBtn.isSelected()) {
                    outMessage = encrypter.encodeText(inMessage, key);
                } else {
                    outMessage = encrypter.decodeText(inMessage, key);
                }

                areaOut.setText(outMessage);

                if (saveCheck.isSelected()) {
                    outPath = ReadWrite.getPath(this, "Save as");
                    if (outPath != null) {
                        ReadWrite.writeFile(outPath, outMessage, this);
                    }
                }
            }
        });

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(mainLabel)
                                .addComponent(openButton))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(scrollIn))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(encodeBtn)
                                        .addComponent(decodeBtn))
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(shiftAlg)
                                        .addComponent(uniAlg))
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(keyLabel))
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(keyIn)
                                        .addComponent(saveCheck))
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(goButton)))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(scrollOut)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(mainLabel)
                        .addComponent(openButton))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(scrollIn))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(BASELINE)
                                        .addComponent(encodeBtn)
                                        .addComponent(shiftAlg)
                                        .addComponent(keyLabel)
                                        .addComponent(keyIn))
                                .addGroup(layout.createParallelGroup(BASELINE)
                                        .addComponent(decodeBtn)
                                        .addComponent(uniAlg)
                                        .addComponent(saveCheck)))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(goButton)))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(scrollOut))
        );
        setVisible(true);
        pack();

    }

    public void errorMessage(String error) {
        JOptionPane.showMessageDialog(this, error, "Inane error",
                JOptionPane.ERROR_MESSAGE);
    }
}
