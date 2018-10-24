package y2018.awt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * GUIアプリのお勉強
 * @see https://www.javadrive.jp/tutorial/jfilechooser/index5.html
 * @author adachi
 */
public class PracticeSwingAwtApp extends JFrame implements ActionListener {
  /** 芝生が生えるかテストやねん*/
  //https://qiita.com/Ryuryu346/items/f896beb0e2ae2197cc5b
  //https://findy-code.io/engineer-lab/github-contributions-rule#i-2
  private final static String TOOL_VERSION = "0.900";
  private final static String TOOL_TITLE_NAME = "素敵ツールやねん";

  JTextArea textarea;

  public static void main(String[] args) {
    PracticeSwingAwtApp frame = new PracticeSwingAwtApp();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(10, 10, 500, 400);
    frame.setTitle(TOOL_TITLE_NAME + " ver " + TOOL_VERSION);
    frame.setVisible(true);
  }

  PracticeSwingAwtApp() {
    JPanel fileSelectButtonPanel = createFileSelectButtonPanel();
    JPanel textAreaPanel = createTextAreaPanel();

    getContentPane().add(textAreaPanel, BorderLayout.CENTER);
    getContentPane().add(fileSelectButtonPanel, BorderLayout.PAGE_END);
  }

  private JPanel createTextAreaPanel() {
    textarea = new JTextArea();
    textarea.setLineWrap(true);
    JScrollPane scrollpane = new JScrollPane(textarea);
    scrollpane.setPreferredSize(new Dimension(120, 120));

    JPanel textPanel = new JPanel();
    textPanel.add(scrollpane);
    return textPanel;
  }

  private JPanel createFileSelectButtonPanel() {
    JButton button = new JButton("ファイル選択");
    button.addActionListener(this);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(button);
    return buttonPanel;
  }

  /**
   * ファイル選択時のアクションリスナー
   */
  public void actionPerformed(ActionEvent e) {
    JFileChooser filechooser = new JFileChooser();

    int selected = filechooser.showOpenDialog(this);
    if (selected == JFileChooser.APPROVE_OPTION) {
      File file = filechooser.getSelectedFile();

      textarea.setText("");

      try {
        if (checkBeforeReadfile(file)) {
          BufferedReader br = new BufferedReader(new FileReader(file));

          String str;
          while ((str = br.readLine()) != null) {
            textarea.append(str);
            textarea.append("¥n");
          }

          br.close();
        } else {
          System.out.println("ファイルが見つからないか開けません");
        }
      } catch (FileNotFoundException err) {
        System.out.println(err);
      } catch (IOException err) {
        System.out.println(err);
      }
    }
  }

  private static boolean checkBeforeReadfile(File file) {
    if (file.exists()) {
      if (file.isFile() && file.canRead()) {
        return true;
      }
    }

    return false;
  }
}
