package javafinal;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
/**
 *
 * @author bunth
 */
public class StudentExam extends javax.swing.JFrame {
    private List<Question> questionList = new ArrayList<>();
    private  int [] userAnswers;
    private int currentIndex ;
    private Timer timer;
    private int timeLeftInSeconds = 600;
    public StudentExam() {
        if (questionList == null) {
            questionList = new java.util.ArrayList<>();
        }
        showQuestion(currentIndex);
        initComponents();
        answerGroup = new ButtonGroup();
        answerGroup.add(chkA);
        answerGroup.add(chkB);
        answerGroup.add(chkC);
        answerGroup.add(chkD);

        readerfilequestion("Question.txt");

        if (questionList != null && !questionList.isEmpty()) {

            Collections.shuffle(questionList);

            userAnswers = new int[questionList.size()];
            for (int i = 0; i < userAnswers.length; i++) {
                userAnswers[i] = -1;
            }
            
            showQuestion(currentIndex);
            startTimer();
        }  else {
            // Handle the error if no questions are found.
            JOptionPane.showMessageDialog(this, "No questions found or file could not be read.", "Error", JOptionPane.ERROR_MESSAGE);
            btnNext.setEnabled(false);
            btnPrev.setEnabled(false);
        }
    }
    private void startTimer() {
    timer = new Timer(1000, (ActionEvent e) -> {
        timeLeftInSeconds--;

        // Calculate the remaining time in milliseconds
        long timeLeftInMillis = timeLeftInSeconds * 1000L;

        // You can now use this variable. For example, to print it to the console:
        System.out.println("Milliseconds remaining: " + timeLeftInMillis);

        // This part updates your GUI as before
        int minutes = timeLeftInSeconds / 60;
        int seconds = timeLeftInSeconds % 60;
        lblTimer.setText(String.format("Time: %02d:%02d:%02d", minutes, seconds, timeLeftInMillis));

        if (timeLeftInSeconds <= 0) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Time is up! The exam will be submitted automatically.", "Time's Up", JOptionPane.WARNING_MESSAGE);
            submitExam();
        }
    });
    timer.start();
}

    public void readerfilequestion(String filePath){
        try {
            questionList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            String level = "";
            String questionText = "";
            String[] Options = new String[4];
            String Answer = "";

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("LEVEL=")) {
                    level = line.substring(6);
                } else if (line.startsWith("QUESTION=")) {
                    questionText = line.substring(9);
                } else if (line.startsWith("A=")) {
                    Options[0] = line.substring(2);
                } else if (line.startsWith("B=")) {
                    Options[1] = line.substring(2);
                } else if (line.startsWith("C=")) {
                    Options[2] = line.substring(2);
                } else if (line.startsWith("D=")) {
                    Options[3] = line.substring(2);
                } else if (line.startsWith("ANSWER=")) {
                    Answer = line.substring(7);
                } else if (line.equals("---")) {
                    questionList.add(new Question(questionText, Answer, Options.clone(), level));
                }
            }

            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error reading question file: " + e.getMessage());
        }
    }
    private void saveCurrentAnswer() {
        if (chkA.isSelected()) userAnswers[currentIndex] = 0;
        else if (chkB.isSelected()) userAnswers[currentIndex] = 1;
        else if (chkC.isSelected()) userAnswers[currentIndex] = 2;
        else if (chkC.isSelected()) userAnswers[currentIndex] = 3;
        else userAnswers[currentIndex] = -1;
    }
    private void loadSavedAnswer() {
        int answer = userAnswers[currentIndex];
        switch (answer) {
            case 0 -> chkA.setSelected(true);
            case 1 -> chkB.setSelected(true);
            case 2 -> chkC.setSelected(true);
            case 3 -> chkD.setSelected(true);
            default -> answerGroup.clearSelection();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        answerGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuestionArea = new javax.swing.JTextArea();
        chkA = new javax.swing.JCheckBox();
        chkB = new javax.swing.JCheckBox();
        chkC = new javax.swing.JCheckBox();
        chkD = new javax.swing.JCheckBox();
        chkE = new javax.swing.JCheckBox();
        btnPrev = new javax.swing.JToggleButton();
        btnNext = new javax.swing.JToggleButton();
        btnAnswer = new javax.swing.JToggleButton();
        btnSubmit = new javax.swing.JToggleButton();
        label2 = new java.awt.Label();
        lblTimer = new java.awt.Label();
        label3 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        txtQuestionArea.setEditable(false);
        txtQuestionArea.setColumns(20);
        txtQuestionArea.setRows(5);
        jScrollPane1.setViewportView(txtQuestionArea);

        scrollPane1.add(jScrollPane1);

        chkA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chkA.setText("A");
        chkA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAActionPerformed(evt);
            }
        });

        chkB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chkB.setText("B");
        chkB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBActionPerformed(evt);
            }
        });

        chkC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chkC.setText("C");

        chkD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chkD.setText("D");

        chkE.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chkE.setText("E");

        btnPrev.setText("<<Prev");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setText("Next>>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnAnswer.setText("Answer");
        btnAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnswerActionPerformed(evt);
            }
        });

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(chkA)
                        .addGap(18, 18, 18)
                        .addComponent(chkB)
                        .addGap(29, 29, 29)
                        .addComponent(chkC)
                        .addGap(18, 18, 18)
                        .addComponent(chkD)
                        .addGap(18, 18, 18)
                        .addComponent(chkE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkA)
                            .addComponent(chkB)
                            .addComponent(chkC)
                            .addComponent(chkD)
                            .addComponent(chkE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSubmit)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnAnswer))
                .addContainerGap())
        );

        label2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 51, 51));
        label2.setText("ONLINE EXAM");

        lblTimer.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblTimer.setText("Time ");

        label3.setText("Question");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(lblTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void showQuestion(int index) {
    if (index < 0 || index >= questionList.size()) return;

    currentIndex = index;
    Question q = questionList.get(index);

    txtQuestionArea.setText(q.questionText());
    txtQuestionArea.setCaretPosition(0);

    String[] opts = q.option();
    chkA.setText(opts[0]);
    chkB.setText(opts[1]);
    chkC.setText(opts[2]);
    chkD.setText(opts[3]);
    // chkE.setText(opts[4]); // DELETE THIS LINE

    label3.setText("Question " + (index + 1) + " of " + questionList.size());

    btnPrev.setEnabled(index > 0);
    btnNext.setEnabled(index < questionList.size() - 1);

    loadSavedAnswer();
}


    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {

        saveCurrentAnswer();
        if (currentIndex < questionList.size() - 1) {
            showQuestion(currentIndex + 1);
        }
    }

    private void submitExam() {
        saveCurrentAnswer();

        if (timer != null) timer.stop();

        int attemptedCount = 0;
        for (int answer : userAnswers) {
            if (answer != -1) attemptedCount++;
        }

        int score = 0;
        String[] optionLetters = {"A", "B", "C", "D"};
        for (int i = 0; i < questionList.size(); i++) {
            if (userAnswers[i] != -1) {
                String userAnswer = optionLetters[userAnswers[i]];
                if (userAnswer.equalsIgnoreCase(questionList.get(i).answer())) {
                    score++;
                }
            }
        }

        this.dispose(); // Close the exam window
        new Result(score, questionList.size(), attemptedCount).setVisible(true);
    }
    private void btnAnswerActionPerformed(java.awt.event.ActionEvent evt) {
        String selected = null;

        if (chkA.isSelected()) selected = "A";
        else if (chkB.isSelected()) selected = "B";
        else if (chkC.isSelected()) selected = "C";
        else if (chkD.isSelected()) selected = "D";

        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select an answer!");
            return;
        }

        Question currentQ = questionList.get(currentIndex);
        if (selected.equalsIgnoreCase(currentQ.answer())) {
            JOptionPane.showMessageDialog(this, "Correct!");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect! The correct answer was: " + currentQ.answer());
        }
    }
    private void chkAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkAActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        saveCurrentAnswer();
        if (currentIndex > 0) {
            showQuestion(currentIndex - 1);
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void chkBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkBActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to submit your exam?",
                "Confirm Submission", JOptionPane.YES_NO_OPTION);
        new Result().setVisible(true);
        
        if (choice == JOptionPane.YES_OPTION) {
            submitExam();
        }
    }//GEN-LAST:event_btnSubmitActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentExam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup answerGroup;
    private javax.swing.JToggleButton btnAnswer;
    private javax.swing.JToggleButton btnNext;
    private javax.swing.JToggleButton btnPrev;
    private javax.swing.JToggleButton btnSubmit;
    private javax.swing.JCheckBox chkA;
    private javax.swing.JCheckBox chkB;
    private javax.swing.JCheckBox chkC;
    private javax.swing.JCheckBox chkD;
    private javax.swing.JCheckBox chkE;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label lblTimer;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JTextArea txtQuestionArea;
    // End of variables declaration//GEN-END:variables
}