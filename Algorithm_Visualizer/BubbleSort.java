import javax.swing.*;
import java.awt.*;


public class BubbleSort extends JPanel
{
    JPanel Bubble_P;
    JButton Replay_B, Back_B;
    TheFrame theframe;
    
    private int[] data = {8, 15, 13, 11, 6, 2, 7, 5, 3};
    private int[] dataCopy = {8, 15, 13, 11, 6, 2, 7, 5, 3};
    private int currentStep = 0;
    private int currentBar = 0;
    
    public void BubbleSortVisualizer() 
    {
        JFrame BubbleSort_F = new JFrame("Bubble Sort Visualizer");
        BubbleSort_F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BubbleSort_F.setSize(1200, 800);
        BubbleSort_F.setResizable(false);
        BubbleSort_F.add(this);
        BubbleSort_F.setVisible(true);
        
        Bubble_P = new JPanel();
        Bubble_P.setBackground(Color.black); 
        Replay_B = new JButton("Replay");
        Back_B = new JButton("Back to Main menu");
        
        Replay_B.addActionListener(e -> 
        {
            Replay_B.setEnabled(false);
            replayBubble();
        });
        
        Back_B.addActionListener(e -> 
        {
            theframe = new TheFrame();
            theframe.TheFramee();
            BubbleSort_F.setVisible(false);
        });
        
        Bubble_P.add(Replay_B);
        Bubble_P.add(Back_B);
        BubbleSort_F.add(Bubble_P, BorderLayout.SOUTH);
        Replay_B.setEnabled(false);
        
        bubbleSort();
    }
    
    public void bubbleSort() 
    {
        new Thread(() -> {
            for (int i = 0; i < data.length - 1; i++) 
            {
                for (int j = 0; j < data.length - 1 - i ; j++) 
                {
                    if (data[j] > data[j + 1]) 
                    {
                        currentBar = j + 1; 
                        int temp = data[j]; 
                        data[j] = data[j + 1];                                                
                        data[j + 1] = temp;
    
                        currentStep++;

                        SwingUtilities.invokeLater(() -> 
                        {
                            repaint();
                        });

                        try 
                        {
                            Thread.sleep(400); // change the speed
                        } 
                        catch (InterruptedException e) 
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        currentBar = j + 1;
                        
                        SwingUtilities.invokeLater(() -> 
                        {
                            repaint();
                        });
                        
                        try 
                        {
                            Thread.sleep(400); // change the speed
                        } 
                        catch (InterruptedException e) 
                        {
                            e.printStackTrace();
                        }
                    }
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
            if (value == data[currentBar])
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
        g.drawString("Step count: " + currentStep, 20, 20);  //display step count
    }
    
    private void replayBubble()
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = dataCopy[i];
        }
        
        currentStep = 0;
        currentBar = 0;
        
        SwingUtilities.invokeLater(() -> 
        {
            bubbleSort();
        });
        
    }
}