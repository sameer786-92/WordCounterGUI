import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class WordCounterGUI extends JFrame {

    private JTextArea textArea;
    private JLabel wordCountLabel;
    private JTextArea wordFrequencyArea;

    public WordCounterGUI() {
        setTitle("Word Counter");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea(10, 30);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton countButton = new JButton("Count Words");
        wordCountLabel = new JLabel("Word Count: ");
        wordFrequencyArea = new JTextArea(10, 30);
        wordFrequencyArea.setEditable(false);
        wordFrequencyArea.setLineWrap(true);
        JScrollPane frequencyScrollPane = new JScrollPane(wordFrequencyArea);

        countButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                countWords();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(scrollPane);
        panel.add(countButton);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(2, 1));
        resultPanel.add(wordCountLabel);
        resultPanel.add(frequencyScrollPane);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(panel, BorderLayout.NORTH);
        container.add(resultPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private void countWords() {
        String text = textArea.getText();
        String[] words = text.split("\\s+");

        // Calculate word count
        int wordCount = words.length;
        wordCountLabel.setText("Word Count: " + wordCount);

        // Calculate word frequency
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        // Display word frequency
        StringBuilder frequencyText = new StringBuilder();
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            frequencyText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        wordFrequencyArea.setText(frequencyText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WordCounterGUI wordCounter = new WordCounterGUI();
                wordCounter.setVisible(true);
            }
        });
    }
}
