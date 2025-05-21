package sample;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class HelloSwing {
    public static void main(String[] args) {
        // GUIの作成はイベントディスパッチスレッドで行う
        SwingUtilities.invokeLater(() -> {
            // フレーム（ウィンドウ）作成
            JFrame frame = new JFrame("Swingの例");

            // ラベルに文字列を設定
            JLabel label = new JLabel("ようこそJavaの世界へ", JLabel.CENTER);
            
            JButton button = new JButton("OK?");
            
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    label.setText("OK!");
                }
            });
            
            label.setFont(new Font("Arial", Font.PLAIN, 20));
            
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(label, BorderLayout.CENTER);
            panel.add(button, BorderLayout.SOUTH);
            
            // フレームにラベルを追加
            frame.add(label);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            // フレームのサイズを設定
            frame.setSize(400, 200);

            // 閉じるボタンでアプリ終了
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // ウィンドウを画面中央に表示
            frame.setLocationRelativeTo(null);

            // 表示
            frame.setVisible(true);
        });
    }
}