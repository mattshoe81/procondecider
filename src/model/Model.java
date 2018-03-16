package model;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public final class Model {

    public Model() {
        this.title = "Untitled";
        this.pros = new HashMap<>();
        this.cons = new HashMap<>();

        this.titleField = new JTextField(27);
        this.proConArea = new JTextArea(3, 30);
        this.slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        this.proButton = new JRadioButton("Pro");
        this.conButton = new JRadioButton("Con");
    }

    private String title;
    private Map<String, Integer> pros;
    private Map<String, Integer> cons;

    public JTextField titleField;
    public JTextArea proConArea;
    public JSlider slider;
    public JRadioButton proButton;
    public JRadioButton conButton;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void addPro(String pro, int weight) {
        this.pros.put(pro, weight);
    }

    public void addCon(String con, int weight) {
        this.cons.put(con, weight);
    }

    public Map<String, Integer> getPros() {
        return this.pros;
    }

    public Map<String, Integer> getCons() {
        return this.cons;
    }

}
