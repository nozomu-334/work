package sample2;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

// キャラクタークラス
class Character {
    protected String name;
    protected int hp;
    protected int attackPower;

    public Character(String name, int hp, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
    }

    public void attack(Character target, JTextArea log) {
        log.append(name + " は攻撃した！\n");
        target.hp -= this.attackPower;
        if (target.hp < 0) target.hp = 0;
        log.append(target.name + " のHPは " + target.hp + " になった！\n");
        if (!target.isAlive()) {
            log.append(target.name + " は戦闘不能です...\n");
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public String getStatus() {
        return name + "：HP=" + hp;
    }
}

// 勇者
class Hero extends Character {
    public Hero(String name, int hp, int attackPower) {
        super(name, hp, attackPower);
    }

    @Override
    public void attack(Character target, JTextArea log) {
        log.append("🔥 勇者 " + name + " のこうげき！\n");
        super.attack(target, log);
    }
}

// 敵
class Enemy extends Character {
    public Enemy(String name, int hp, int attackPower) {
        super(name, hp, attackPower);
    }

    @Override
    public void attack(Character target, JTextArea log) {
        log.append("💀 敵 " + name + " が襲いかかる！\n");
        super.attack(target, log);
    }
}

// GUIメインクラス
public class BattleGame extends JFrame {
    private Hero hero;
    private Enemy enemy;
    private JTextArea log;
    private JButton attackButton;

    public BattleGame() {
        hero = new Hero("勇者アキラ", 80, 15);
        enemy = new Enemy("ドラゴン", 100, 10);

        setTitle("勇者 vs 敵 - バトルゲーム");
        setSize(400, 300);
        setLocationRelativeTo(null); // 画面中央
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // テキストエリア
        log = new JTextArea();
        log.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(log);

        // ボタン
        attackButton = new JButton("攻撃する");
        attackButton.addActionListener(e -> performBattle());

        // レイアウト
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(attackButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);

        log.append("戦闘スタート！\n");
        showStatus();
    }

    private void performBattle() {
        if (!hero.isAlive() || !enemy.isAlive()) {
            log.append("⚠️ 戦闘はすでに終了しています\n");
            return;
        }

        hero.attack(enemy, log);

        if (enemy.isAlive()) {
            enemy.attack(hero, log);
        } else {
            log.append("🎉 敵を倒した！\n");
            attackButton.setEnabled(false);
        }

        if (!hero.isAlive()) {
            log.append("💀 勇者は倒れてしまった...\n");
            attackButton.setEnabled(false);
        }

        showStatus();
    }

    private void showStatus() {
        log.append("🟢 " + hero.getStatus() + "\n");
        log.append("🔴 " + enemy.getStatus() + "\n\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BattleGame::new);
    }
}
