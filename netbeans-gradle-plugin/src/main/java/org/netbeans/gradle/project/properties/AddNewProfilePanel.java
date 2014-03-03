package org.netbeans.gradle.project.properties;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.gradle.project.NbStrings;
import org.netbeans.gradle.project.validate.BackgroundValidator;
import org.netbeans.gradle.project.validate.GroupValidator;
import org.netbeans.gradle.project.validate.Problem;
import org.netbeans.gradle.project.validate.Validator;
import org.netbeans.gradle.project.validate.Validators;

@SuppressWarnings("serial")
public class AddNewProfilePanel extends javax.swing.JPanel {
    private final BackgroundValidator bckgValidator;
    private final GroupValidator validators;

    /**
     * Creates new form AddNewProfilePanel
     */
    public AddNewProfilePanel() {
        initComponents();
        jInfoLabel.setText("");

        bckgValidator = new BackgroundValidator();

        validators = new GroupValidator();
        validators.addValidator(
                profileNameValidator(),
                Validators.createCollector(jProfileEdit));

        Validators.connectLabelToProblems(bckgValidator, jInfoLabel);

        jProfileEdit.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                bckgValidator.performValidation();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                bckgValidator.performValidation();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                bckgValidator.performValidation();
            }
        });
    }

    private static Validator<String> profileNameValidator() {
        return Validators.merge(
                Validators.createFileNameValidator(Problem.Level.SEVERE, NbStrings.getInvalidProfileName()),
                Validators.createNonEmptyValidator(Problem.Level.SEVERE, NbStrings.getEmptyProfileName()));
    }

    public void startValidation() {
        bckgValidator.setValidators(validators);
    }

    public String getProfileName() {
        return jProfileEdit.getText().trim();
    }

    public org.jtrim.property.PropertySource<Boolean> validProfileName() {
        return bckgValidator.valid();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProfileNameCaption = new javax.swing.JLabel();
        jProfileEdit = new javax.swing.JTextField();
        jInfoLabel = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jProfileNameCaption, org.openide.util.NbBundle.getMessage(AddNewProfilePanel.class, "AddNewProfilePanel.jProfileNameCaption.text")); // NOI18N

        jProfileEdit.setText(org.openide.util.NbBundle.getMessage(AddNewProfilePanel.class, "AddNewProfilePanel.jProfileEdit.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jInfoLabel, org.openide.util.NbBundle.getMessage(AddNewProfilePanel.class, "AddNewProfilePanel.jInfoLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jProfileNameCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProfileEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jInfoLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jProfileNameCaption)
                    .addComponent(jProfileEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jInfoLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jInfoLabel;
    private javax.swing.JTextField jProfileEdit;
    private javax.swing.JLabel jProfileNameCaption;
    // End of variables declaration//GEN-END:variables
}
