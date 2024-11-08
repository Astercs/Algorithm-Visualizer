import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TheFrame extends JFrame 
{
    JPanel Main_P;
    JButton BubbleSort_B, SelectionSort_B, Coming_B;
    JLabel Welcome_L, MainPrompt_L;
    
    BubbleSort bubbleSort;
    SelectionSort selectionSort;
    ComingSoon comingSoon;
    
    public void TheFramee() 
    {
        this.setTitle("Algorithm Visualizer"); // set title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of Algorithm Visualizer
        this.setResizable(false); // prevent resized of frame
        this.setSize(800, 800); // set size of frame
        this.getContentPane().setBackground(Color.black); 
        
        Welcome_L = new JLabel("Welcome to Algorithm Visualizer!");
        Welcome_L.setForeground(Color.white);
        Welcome_L.setHorizontalAlignment(JLabel.CENTER);
        Welcome_L.setVerticalAlignment(JLabel.CENTER);
        Welcome_L.setFont(new Font("Comic Sans", Font.BOLD, 25));
        
        MainPrompt_L = new JLabel("Please click the Algorithm that you want to view.");
        MainPrompt_L.setForeground(Color.white);
        MainPrompt_L.setHorizontalAlignment(JLabel.CENTER);
        MainPrompt_L.setVerticalAlignment(JLabel.CENTER);
        MainPrompt_L.setFont(new Font("Comic Sans", Font.BOLD, 15));
        
        Main_P = new JPanel();
        Main_P.setBackground(Color.black); 
        BubbleSort_B = new JButton("Bubble Sort");
        SelectionSort_B = new JButton("Selection Sort");
        Coming_B = new JButton("Coming Soon");
        

        BubbleSort_B.addActionListener(e -> 
        {
            bubbleSort = new BubbleSort();
            bubbleSort.BubbleSortVisualizer();
            this.setVisible(false); // Hide main menu 
        });
        
        SelectionSort_B.addActionListener(e -> 
        {
            selectionSort = new SelectionSort();
            selectionSort.SelectionSortVisualizer();
            this.setVisible(false); // Hide main menu 
        });
        
        Coming_B.addActionListener(e -> 
        {
            
        });
        
        
        
        Main_P.add(Welcome_L);
        Main_P.add(MainPrompt_L);
        Main_P.add(BubbleSort_B);
        Main_P.add(SelectionSort_B);
        Main_P.add(Coming_B);
        add(Main_P);
        
        Main_P.setLayout(new GridLayout(5,1, 0, 0));
        
        this.setVisible(true); // make frame visible
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
            new TheFrame();
        });
    }
}
