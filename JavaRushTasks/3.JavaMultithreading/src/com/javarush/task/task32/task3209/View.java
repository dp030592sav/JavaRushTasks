package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    // панель с двумя вкладками.
    private JTabbedPane tabbedPane = new JTabbedPane();
    // компонент для визуального редактирования html.
    private JTextPane htmlTextPane = new JTextPane();
    // компонент для редактирования html в виде текста, он будет отображать код html (теги и их содержимое).
    private JEditorPane plainTextPane = new JEditorPane();
    // объект для возмажости отмени действий.
    private UndoManager undoManager = new UndoManager();
    // объект для отследования правок, которые можно отменить или вернуть.
    private UndoListener undoListener = new UndoListener(undoManager);

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
    }

    public Controller getController() {
        return controller;
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    // Этот метод наследуется от интерфейса ActionListener
    // и будет вызваться при выборе пунктов меню, у которых наше представление указано в виде слушателя событий.
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
                break;
        }
    }

    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }

    // Инициализация панели меню.
    public void initMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);
        getContentPane().add(jMenuBar, BorderLayout.NORTH);
    }

    // Инициализация панели редактора.
    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));
        tabbedPane.addTab("Текст", new JScrollPane(plainTextPane));
        tabbedPane.setPreferredSize(new Dimension(500, 500));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    // Инициализация Gui.
    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    // Метод для закрития программы.
    public void exit() {
        controller.exit();
    }

    // Этот метод вызывается, когда произошла смена выбранной вкладки.
    public void selectedTabChanged() {
        if(tabbedPane.getSelectedIndex() == 0)
            controller.setPlainText(plainTextPane.getText());
        else if(tabbedPane.getSelectedIndex() == 1)
            plainTextPane.setText(controller.getPlainText());
        resetUndo();
    }

    // Проверяет возможность отмены последнего действия.
    public boolean canUndo() {
        return undoManager.canUndo();
    }

    // Проверяет возможность возврата ранее отмененного действия.
    public boolean canRedo() {
        return undoManager.canRedo();
    }

    // Отменяет последнее действие.
    public void undo(){
        try{
            undoManager.undo();
        }catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    // Возвращает ранее отмененное действие.
    public void redo(){
        try{
            undoManager.redo();
        }catch (Exception e){
            ExceptionHandler.log(e);
        }

    }

    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    // Возвращать true, если выбрана html вкладка.
    public boolean isHtmlTabSelected(){
        return tabbedPane.getSelectedIndex() == 0;
    }

    // Выбирает вкладку Html и сбрасывать все правки.
    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    // Устанавливает документ в панель редактирования.
    public void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    // Показывает диалоговое окно с информацией о программе.
    public void showAbout(){
        JOptionPane jOptionPane = new JOptionPane();
        JOptionPane.showMessageDialog(jOptionPane, "Homemade HTML Editor", "HTML Editor",JOptionPane.INFORMATION_MESSAGE);
    }
}
