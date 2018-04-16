package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    // Метод инициализации контроллера.
    public void init() {
        createNewDocument();
    }

    // Метод для закрития программы.
    public void exit() {
        System.exit(0);
    }

    // Сбрасивает текущий документ.
    public void resetDocument() {
        if (document != null)
            document.removeUndoableEditListener(view.getUndoListener());
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    // Записывает переданный текст с html тегами в документ document.
    public void setPlainText(String text) {
        try {
            resetDocument();
            StringReader stringReader = new StringReader(text);
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    // Получает текст из документа со всеми html тегами.
    public String getPlainText() {
        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    // Метод создания нового документа.
    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    // Метод открития существующего документа.
    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int resChoose = jFileChooser.showOpenDialog(view);

        if (resChoose != JFileChooser.APPROVE_OPTION) {
            return;
        }

        currentFile = jFileChooser.getSelectedFile();
        resetDocument();
        view.setTitle(currentFile.getName());

        try (FileReader fileReader = new FileReader(currentFile)) {
            new HTMLEditorKit().read(fileReader, document, 0);
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }

        view.resetUndo();
    }

    // Метод для сохранения файла (save).
    public void saveDocument() {
        if (currentFile != null) {
            view.selectHtmlTab();
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        } else
            saveDocumentAs();
    }

    // Метод для сохранения файла под новым именем (save as).
    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int resChoose = jFileChooser.showSaveDialog(view);

        if (resChoose != JFileChooser.APPROVE_OPTION) {
            return;
        }

        currentFile = jFileChooser.getSelectedFile();
        view.setTitle(currentFile.getName());
        try (FileWriter fileWriter = new FileWriter(currentFile)) {
            new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
}
