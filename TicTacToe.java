import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe implements ActionListener
{

    private final JFrame   frame        = new JFrame("Tic-Tac-Toe");
    private final JLabel   statusLabel  = new JLabel("X's turn");
    private final JButton[][] tile      = new JButton[3][3];

    private boolean xTurn = true;   
    private int     moves = 0;      

    public TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 650);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        statusLabel.setOpaque(true);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 50));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBackground(Color.DARK_GRAY);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(statusLabel, BorderLayout.NORTH);

        JPanel board = new JPanel(new GridLayout(3, 3));
        board.setBackground(Color.DARK_GRAY);
        frame.add(board, BorderLayout.CENTER);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton b = new JButton();
                b.setFont(new Font("Arial", Font.BOLD, 120));
                b.setForeground(Color.WHITE);
                b.setBackground(Color.DARK_GRAY);
                b.setFocusable(false);
                b.addActionListener(this);
                tile[r][c] = b;
                board.add(b);
            }
        }

        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();

        if (!b.getText().isEmpty() || gameFinished()) return;

        b.setText(xTurn ? "X" : "O");
        moves++;

        if (hasWinner()) {
            statusLabel.setText((xTurn ? "X" : "O") + " wins!");
        } else if (moves == 9) {
            statusLabel.setText("Tie!");
        } else {
            xTurn = !xTurn;
            statusLabel.setText((xTurn ? "X" : "O") + "'s turn");
        }
    }

    private boolean gameFinished() {
        return statusLabel.getText().endsWith("wins!") || statusLabel.getText().equals("Tie!");
    }

    private boolean hasWinner() {
        String p = xTurn ? "X" : "O";

        for (int i = 0; i < 3; i++) {
            if (p.equals(tile[i][0].getText()) &&
                p.equals(tile[i][1].getText()) &&
                p.equals(tile[i][2].getText())) return true;

            if (p.equals(tile[0][i].getText()) &&
                p.equals(tile[1][i].getText()) &&
                p.equals(tile[2][i].getText())) return true;
        }
        
        return (p.equals(tile[0][0].getText()) &&
                p.equals(tile[1][1].getText()) &&
                p.equals(tile[2][2].getText()))
            || (p.equals(tile[0][2].getText()) &&
                p.equals(tile[1][1].getText()) &&
                p.equals(tile[2][0].getText()));
    }

 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}