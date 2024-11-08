import javax.swing.*;
import java.awt.*;



public class SelectionSort extends JPanel 
{
    
    private int[] data = {8, 13, 15, 11, 6, 2, 7, 5, 3};
    private int[] dataCopy = {8, 15, 13, 11, 6, 2, 7, 5, 3};
    private int currentStep = 0;
    private int currentBar = 0;
    private int smallestBar = 0;
    
    JPanel Selection_P;
    JButton Replay_B, Back_B;
    TheFrame theframe;

    public void SelectionSortVisualizer() 
    {
        JFrame SelectionSort_F = new JFrame("Selection Sort Visualizer");
        SelectionSort_F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SelectionSort_F.setSize(1200, 800);
        SelectionSort_F.setResizable(false);
        SelectionSort_F.add(this);
        SelectionSort_F.setVisible(true);
        
        Selection_P = new JPanel();
        Selection_P.setBackground(Color.black); 
        Replay_B = new JButton("Replay");
        Back_B = new JButton("Back to Main menu");
        
        Replay_B.addActionListener(e -> 
        {
            Replay_B.setEnabled(false);
            replaySelection();
        });
        
        Back_B.addActionListener(e -> 
        {
            theframe = new TheFrame();
            theframe.TheFramee();
            SelectionSort_F.setVisible(false);
        });
        
        Selection_P.add(Replay_B);
        Selection_P.add(Back_B);
        SelectionSort_F.add(Selection_P, BorderLayout.SOUTH);
        Replay_B.setEnabled(false);        
        selectionSort();
    }
    
    public void selectionSort() 
    {
        new Thread(() -> {
            int smallest, temp;
            
            for (int t = 0; t < data.length - 1; t++)
            {
                smallest = t;
                smallestBar = smallest;
                for (int i = t; i < data.length; i++)
                {
                    currentBar = i;
                    if (data[i] < data[smallest])
                    {
                        smallest = i;
                        smallestBar = smallest;
                    }
                    
                    SwingUtilities.invokeLater(() -> 
                    {
                        repaint();
                    });
                
                    try 
                    {
                        Thread.sleep(500); // change the speed
                    } 
                    catch (InterruptedException e) 
                    {
                        e.printStackTrace();
                    }
                }
                temp = data[smallest];
                data[smallest] = data[t];
                data[t] = temp;
                
                currentStep++;
                
                SwingUtilities.invokeLater(() -> 
                {
                    repaint();
                });
                
                try 
                {
                    Thread.sleep(500); // change the speed
                } 
                catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
                
            }
            SwingUtilities.invokeLater(() -> 
            {
                Replay_B.setEnabled(true);
            });
        }).start();
    }
    
    
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        
        int x = 20;
      
        for (int value : data)
        {   
            if (value == data[smallestBar] )
            {
                g2D.setPaint(new Color(200, 0, 0, 100));
            }
            else if (value == data[currentBar])
            {
                g2D.setPaint(new Color(0, 200, 0, 100));
            }
            else 
            {
                g2D.setPaint(new Color(0, 0, 0, (value) * 15));
            } 
            g2D.fillRect(x, 100, 100, value * 40);
            g2D.drawString(String.valueOf(value), x + 50 , 90 );
            x += 120;
        }
        g.drawString("Step count: " + currentStep, 20, 20);
    }
    
    private void replaySelection()
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = dataCopy[i];
        }
        
        currentStep = 0;
        currentBar = 0;
        smallestBar = 0;
        
        SwingUtilities.invokeLater(() -> 
        {
            selectionSort();
        });
        
    }
}