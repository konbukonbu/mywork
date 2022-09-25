package com.konbu.tool.codecount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * git log コマンドの実行結果をもとにコミット行数をカウントしてレポートを出力します.<br>
 * 前提条件:Windowsクライアントで、git bash(git for windows)がインストールされていること.<br>
 * <br>
 * git for windows<br>
 * https://gitforwindows.org/ <br>
 * <br>
 * @author あっちー
 */
public class StartupMain extends JFrame implements ActionListener {

  private final static String TOOL_TITLE_NAME = "コミットコード行数の集計ツール🐧🐧🐧==33";

  private static JTextField targetGitPathTextField;
  private static JTextField userIdTextField;
  private static JTextField fromDateTextField;
  private static JTextField toDateTextField;
  private static JTextField gitBashPathTextField;

  public static void main(String[] args) {
    System.out.println("コミット行数カウントツールを起動します.");
    userInterfaceProc();
  }

  /**
   * ツール実行ボタン押下時のイベントリスナ
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String gitBashPath = gitBashPathTextField.getText();
    gitBashPath = gitBashPath.replaceAll("\\\\", "/");//bash使って、git logコマンドを実行するので\→/の必要あり.

    String targetGitPath = targetGitPathTextField.getText();
    targetGitPath = targetGitPath.replaceAll("\\\\", "/");//bash使って、git logコマンドを実行するので\→/の必要あり.

    String inputFromDate = fromDateTextField.getText();
    String inputToDate = toDateTextField.getText();
    String author = userIdTextField.getText();

    CodeCountMainProc.countMainProc(gitBashPath, targetGitPath, inputFromDate, inputToDate, author);
  }

  /**
   * ユーザから必要パラメータを入力してもらうためのUI画面処理
   */
  private static void userInterfaceProc() {
    StartupMain main = new StartupMain();
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setBounds(10, 10, 800, 320);
    main.setTitle(TOOL_TITLE_NAME);
    main.setVisible(true);
  }

  private StartupMain() {
    JPanel execButtonPanel = createExecButtonPanel();
    JPanel textFieldPanel = createTextFieldPanel();
    getContentPane().add(textFieldPanel, BorderLayout.CENTER);
    getContentPane().add(execButtonPanel, BorderLayout.PAGE_END);
  }

  private JPanel createExecButtonPanel() {
    JButton button = new JButton("  ツール実行  ");
    button.addActionListener(this);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(button);
    buttonPanel.setBackground(Color.ORANGE);
    return buttonPanel;
  }

  private JPanel createTextFieldPanel() {
    JPanel textPanel = new JPanel();
    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));

    JLabel gitPathLabel = new JLabel("1) ローカルgitリポジトリのパス(.gitがある場所) 【入力例】C:\\workspace\\java\\eclipse\\Git_Workspace\\mywork");
    targetGitPathTextField = new JTextField("", 40);
    textPanel.add(gitPathLabel);
    textPanel.add(targetGitPathTextField);

    JLabel userIdLabel = new JLabel("2) 集計対象のユーザーID(未入力の場合は全て集計)  【入力例】konbu");
    userIdTextField = new JTextField("", 20);
    textPanel.add(userIdLabel);
    textPanel.add(userIdTextField);

    JLabel fromDateLabel = new JLabel("3) 集計開始日(yyyyMMdd) 【入力例】20220801");
    fromDateTextField = new JTextField("", 20);
    textPanel.add(fromDateLabel);
    textPanel.add(fromDateTextField);

    JLabel toDateLabel = new JLabel("4) 集計終了日(yyyyMMdd) 【入力例】20220830");
    toDateTextField = new JTextField("", 20);
    textPanel.add(toDateLabel);
    textPanel.add(toDateTextField);

    JLabel gitBashLabel = new JLabel("5) gitbashのパス ※デフォルトの場合は編集しなくてOK");
    gitBashPathTextField = new JTextField("C:\\Program Files\\Git\\bin\\bash.exe", 20);
    textPanel.add(gitBashLabel);
    textPanel.add(gitBashPathTextField);

    return textPanel;
  }


}
