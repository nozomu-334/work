package sample;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	JLabel playerLabel, cpuLabel, resultLabel;
	String[] hands = { "グー", "チョキ", "パー" };
	ImageIcon[] icons = {
			new ImageIcon(getClass().getResource("/img/gu.png")),
			new ImageIcon(getClass().getResource("/img/choki.png")),
			new ImageIcon(getClass().getResource("/img/pa.png"))
	};

	public Main() {
		setTitle("じゃんけんゲーム");
		setSize(600, 500);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel();
		for (int i = 0; i < 3; i++) {
			JButton btn = new JButton(icons[i]);
			final int playerHand = i;
			btn.addActionListener(e -> playJanken(playerHand));
			buttonPanel.add(btn);
		}

		playerLabel = new JLabel("あなた: ");
		cpuLabel = new JLabel("コンピュータ: ");
		resultLabel = new JLabel("結果: ");

		JPanel infoPanel = new JPanel(new GridLayout(3, 1));
		infoPanel.add(playerLabel);
		infoPanel.add(cpuLabel);
		infoPanel.add(resultLabel);

		add(buttonPanel, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.CENTER);
	}

	private void playJanken(int player) {
		 int cpu = new Random().nextInt(3);
		 playerLabel.setText("あなた: " + hands[player]);
		 cpuLabel.setText("コンピュータ: " + hands[cpu]);
		 resultLabel.setText("結果: " + judge(player, cpu));
	}

	private String judge(int p, int c) {
		 if (p == c)
		 return "あいこ";
		 if ((p == 0 && c == 1) || (p == 1 && c == 2) || (p == 2 && c == 0))
		 return "あなたの勝ち！";
		 else
		 return "あなたの負け...";
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main().setVisible(true));
	}

}
