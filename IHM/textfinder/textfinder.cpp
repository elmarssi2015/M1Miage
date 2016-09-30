#include "textfinder.h"
#include "ui_textfinder.h"

textfinder::textfinder(QWidget *parent) : QWidget(parent), ui(new Ui::textfinder)
{
    ui->setupUi(this);
    setWindowIcon(QIcon(":/plus.png"));
    setWindowTitle("Ma Fenêtre de TD2 d'IHM");
}

void textfinder::parcourir()
{
    QFileDialog dialog(this);
    dialog.setFileMode(QFileDialog::ExistingFile);
    dialog.setNameFilter(tr("Fichiers Texte (*.txt);; Tous les fichiers (*.*)"));
    dialog.setLabelText(QFileDialog::FileName, "Mon Fichier :");
    dialog.setLabelText(QFileDialog::FileType, "Type de fichiers :");
    dialog.setLabelText(QFileDialog::Accept, "Sélectionner");
    dialog.setLabelText(QFileDialog::Reject, "Annuler");
    QStringList fileNames = dialog.selectedFiles();

    if(dialog.exec())
        {
            fileNames = dialog.selectedFiles();
            ui->lineEdit_2->setText(fileNames.at(0));
            fileNames.removeAt(0);
        }
    else
    {
        dialog.reject();
    }
}

void textfinder::loadTextFile()
{
    QFile inputFile;
    inputFile.setFileName(ui->lineEdit_2->text());
    inputFile.open(QIODevice::ReadOnly);

    QTextStream in(&inputFile);
    QString line = in.readAll();

    inputFile.close();

    ui->textEdit->setPlainText(line);
    ui->textEdit_3->setPlainText(line);
}

void textfinder::on_Recherche_clicked()
{
    QString searchString = ui->lineEdit->text();
    QTextDocument *document = ui->textEdit->document();

    bool isFirstTime = false;
    bool found = false;
    bool sensitive = true;

    if (isFirstTime == false)
              document->undo();

    if (searchString.isEmpty()) {
        QMessageBox::information(this, tr("Vous n'avez rien entré"), "Le champ de recherche est vide. Veuillez entrer un mot dans le champ.");
    }
    else
    {
        QTextCursor highlightCursor(document);
        QTextCursor cursor(document);
        int occurence=0;
        cursor.beginEditBlock();

        QTextCharFormat plainFormat(highlightCursor.charFormat());
        QTextCharFormat colorFormat = plainFormat;
        colorFormat.setForeground(Qt::red);

        if(!ui->radioButton->isChecked()) sensitive = false;

            while (!highlightCursor.isNull() && !highlightCursor.atEnd()) {

                if(sensitive == true) highlightCursor = document->find(searchString, highlightCursor, QTextDocument::FindWholeWords);
                else highlightCursor = document->find(searchString, highlightCursor, QTextDocument::FindCaseSensitively);

                if (!highlightCursor.isNull()) {
                    found = true;

                    if (sensitive == true) highlightCursor.movePosition(QTextCursor::WordRight, QTextCursor::KeepAnchor);

                    highlightCursor.mergeCharFormat(colorFormat);

                    occurence++;
                }
            }

        ui->lcdNumber->display(occurence);
        cursor.endEditBlock();
        isFirstTime = false;

        if (found == false) {
            QMessageBox::information(this, tr("Mot non trouvé"),
            "Aucun mot ne correspond à votre rechercher.");
        }
    }
}

void textfinder::on_Parcourir_clicked()
{
    textfinder::parcourir();
}

void textfinder::on_Quitter_clicked()
{
    QApplication::quit();
}

void textfinder::on_Ok_1_clicked()
{
    if (ui->lineEdit_2->text().isEmpty()) {
        ui->Recherche->setEnabled(false);
        ui->textEdit->setText("");
        ui->textEdit_3->setText("");
        ui->lcdNumber->display(0);
        QMessageBox::information(this, tr("Vous n'avez rien entré"), "Veuillez selectionner un chemin d'accès à votre fichier, ou le saisir.");
    }
    else
    {
        textfinder::loadTextFile();
        ui->Recherche->setEnabled(true);
    }
}

void textfinder::on_lcdNumber_overflow()
{

}

void textfinder::on_lcdNumber_2_overflow()
{

}

textfinder::~textfinder()
{
    delete ui;
}







