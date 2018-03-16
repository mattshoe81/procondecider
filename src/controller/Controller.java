package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import model.Model;

public class Controller {

    public Controller(Model model) {
        this.model = model;
        this.firstRun = true;
    }

    private Model model;
    private boolean firstRun;

    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Controller.this.firstRun) {
                Controller.this.model
                        .setTitle(Controller.this.model.titleField.getText());
                Controller.this.model.titleField.setEditable(false);
            }
            String proConText = Controller.this.model.proConArea.getText();
            int score = Controller.this.model.slider.getValue();

            if (Controller.this.model.proButton.isSelected()) {
                Controller.this.model.addPro(proConText, score);
            } else {
                Controller.this.model.addCon(proConText, score);
            }

            Controller.this.model.proConArea.setText("");
            Controller.this.model.slider.setValue(0);

        }
    }

    private class DoneButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                File file = new File(
                        "data/" + Controller.this.model.getTitle() + ".txt");
                file.createNewFile();
                PrintWriter out = new PrintWriter(file);
                Controller.this.outputResults(out);
                out.close();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private void outputResults(PrintWriter out) {
        Map<String, Integer> pros = this.model.getPros();
        Map<String, Integer> cons = this.model.getCons();
        int proScore = 0;
        int conScore = 0;

        out.println("\n" + this.model.getTitle() + "\n\n");

        Set<Map.Entry<String, Integer>> proSet = pros.entrySet();
        out.println("************ Pros **************");
        for (Map.Entry<String, Integer> pro : proSet) {
            out.println(pro.getKey());
            proScore += pro.getValue();
        }
        out.println("***********************************************\n\n");

        Set<Map.Entry<String, Integer>> conSet = cons.entrySet();
        out.println("************ Cons **************");
        for (Map.Entry<String, Integer> con : conSet) {
            out.println(con.getKey());
            conScore += con.getValue();
        }

        out.println("***********************************************\n\n");

        if (proScore > conScore) {
            int diff = proScore - conScore;
            out.println(
                    "The pros outweigh the cons by " + diff + " points!\n\n");
        } else if (proScore < conScore) {
            int diff = conScore - proScore;
            out.println(
                    "The pros DO NOT outweigh the cons. The cons outweighed the pros by "
                            + diff + " points.\n\n");
        } else {
            out.println(
                    "No definitive decision can be made. The comparison resulted in a tie");
        }

    }

    public NextButtonListener getNextButtonListener() {
        return new Controller.NextButtonListener();
    }

    public DoneButtonListener getDoneButtonListener() {
        return new DoneButtonListener();
    }
}
