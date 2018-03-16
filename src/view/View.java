package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import controller.Controller;
import model.Model;

@SuppressWarnings("serial")
public class View extends JFrame implements ActionListener {

    public View(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }

    private Model model;
    private Controller controller;

    public void launch() {
        JFrame frame = this;
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleField = this.model.titleField;
        titleField.setEditable(true);
        titlePanel.add(titleLabel);
        titlePanel.add(titleField);
        mainPanel.add(titlePanel);

        // Create text area panel
        JPanel proConPanel = new JPanel();
        JTextArea proConArea = this.model.proConArea;
        JScrollPane proConPane = new JScrollPane(proConArea);
        proConPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        proConArea.setEditable(true);
        proConArea.setLineWrap(true);
        proConArea.setWrapStyleWord(true);
        proConPanel.add(proConPane);
        mainPanel.add(proConPanel);

        // Create weight and pro/con panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JSlider slider = this.model.slider;
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        JRadioButton proButton = this.model.proButton;
        JRadioButton conButton = this.model.conButton;
        proButton.setSelected(true);
        conButton.setSelected(false);
        proButton.addActionListener(this);
        conButton.addActionListener(this);
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.add(proButton);
        radioPanel.add(conButton);
        bottomPanel.add(slider);
        bottomPanel.add(radioPanel);
        mainPanel.add(bottomPanel);

        // Create next and done buttons
        JPanel buttonPanel = new JPanel();
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(this.controller.getNextButtonListener());
        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(this.controller.getDoneButtonListener());
        buttonPanel.add(doneButton);
        buttonPanel.add(nextButton);
        mainPanel.add(buttonPanel);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(model, controller);

        view.launch();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(this.model.proButton)) {
            this.model.proButton.setSelected(true);
            this.model.conButton.setSelected(false);
        }

        else if (source.equals(this.model.conButton)) {
            this.model.conButton.setSelected(true);
            this.model.proButton.setSelected(false);
        }
    }
}
