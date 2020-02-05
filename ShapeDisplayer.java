import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;

class SnowmanShape implements compositeShape
{
    private int x;
    private int y;
    private int width;
    public SnowmanShape(int x, int y, int width)
    {
        this.x = x;
        this.y = y;
        this.width = width;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return width*2;
    }
    public void draw(Graphics2D g2)
    {
        Ellipse2D.Double top=new Ellipse2D.Double(x,y,width,width);
        Ellipse2D.Double bottom=new Ellipse2D.Double(x,y-width,width,width);
        g2.draw(top);
        g2.draw(bottom);
    }
}
class TreeShape implements compositeShape
{
    private int x;
    private int y;
    private int width;
    public TreeShape(int x, int y, int width)
    {
        this.x = x;
        this.y = y;
        this.width = width;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return width*3;
    }
    public void draw(Graphics2D g2)
    {
        Point2D.Double r1 = new Point2D.Double(x+5*width, y+width);
        Point2D.Double r2 = new Point2D.Double(x+3*width  , y+3*width);
        Point2D.Double r3 = new Point2D.Double(x+7*width , y+3*width);
        Point2D.Double r4 = new Point2D.Double(x+5*width, y+3*width);
        Point2D.Double r5 = new Point2D.Double(x+width  , y+6*width);
        Point2D.Double r6 = new Point2D.Double(x+9*width , y+6*width);
        Line2D.Double line1 = new Line2D.Double(r1, r2);
        Line2D.Double line2 = new Line2D.Double(r1, r3);
        Line2D.Double line3 = new Line2D.Double(r2, r3);
        Line2D.Double line4 = new Line2D.Double(r4, r5);
        Line2D.Double line5 = new Line2D.Double(r4, r6);
        Line2D.Double line6 = new Line2D.Double(r5, r6);
        Rectangle2D.Double bottom=new Rectangle2D.Double(x+4*width,y+6*width,2*width,4*width);
        Ellipse2D.Double top=new Ellipse2D.Double(x+5*width-width/2,y+0,width,width);
        g2.draw(line1);
        g2.draw(line2);
        g2.draw(line3);
        g2.draw(line4);
        g2.draw(line5);
        g2.draw(line6);
        g2.draw(bottom);
        g2.draw(top);

    }
}

class CarShape implements compositeShape
{
    private int x;
    private int y;
    private int width;
    public CarShape(int x, int y, int width)
    {
        this.x = x;
        this.y = y;
        this.width = width;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return width;
    }
    public void draw(Graphics2D g2)
    {
        Rectangle2D.Double body
                = new Rectangle2D.Double(x, y + width / 6,
                width - 1, width / 6);
        Ellipse2D.Double frontTire
                = new Ellipse2D.Double(x + width / 6, y + width / 3,
                width / 6, width / 6);
        Ellipse2D.Double rearTire
                = new Ellipse2D.Double(x + width * 2 / 3, y + width / 3,
                width / 6, width / 6);
        Point2D.Double r1 = new Point2D.Double(x + width / 6, y + width / 6);
        Point2D.Double r2 = new Point2D.Double(x + width / 3, y);
        Point2D.Double r3 = new Point2D.Double(x + width * 2 / 3, y);
        Point2D.Double r4 = new Point2D.Double(x + width * 5 / 6, y + width / 6);
        Line2D.Double frontWindshield = new Line2D.Double(r1, r2);
        Line2D.Double roofTop = new Line2D.Double(r2, r3);
        Line2D.Double rearWindshield = new Line2D.Double(r3, r4);
        g2.draw(body);
        g2.draw(frontTire);
        g2.draw(rearTire);
        g2.draw(frontWindshield);
        g2.draw(roofTop);
        g2.draw(rearWindshield);
    }
}

interface compositeShape
{
    void draw(Graphics2D g2);
    int getHeight();
    int getWidth();

}

class show extends JPanel
{
    private ArrayList<compositeShape> shapes;
    public show()
    {
        shapes=new ArrayList<>();

    }

    public void add(compositeShape shape)
    {
        this.shapes.add(shape);
    }
    public void paintComponent(Graphics g)
    { super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (compositeShape s:shapes)
        {
            s.draw(g2);
        }
    }
}
class buttomIcon implements Icon
{
    compositeShape shape;
    public buttomIcon(compositeShape shape)
    {
        this.shape=shape;
    }
    public int getIconWidth()
    {
        return shape.getWidth();
    }

    public int getIconHeight()
    {
        return shape.getHeight();
    }

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D) g;
        shape.draw(g2);
    }
}
public class ShapeDisplayer
{
    static public void select(char word)
    {
        selection=word;
    }
    static char selection;

    public static void main(String[] args)
    {

        JFrame frame = new JFrame();
        show D=new show();

        D.setPreferredSize(new Dimension(500,500));


        JButton car=new JButton(new buttomIcon(new CarShape(8,30,55)));
        car.setPreferredSize(new Dimension(70,70));
        JButton snowman=new JButton(new buttomIcon(new SnowmanShape(24,35,24)));
        snowman.setPreferredSize(new Dimension(70,70));
        JButton tree=new JButton(new buttomIcon(new TreeShape(11,12,5)));
        tree.setPreferredSize(new Dimension(70,70));

        car.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select('c');
            }
        });
        snowman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select('s');
            }
        });
        tree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select('t');
            }
        });
        D.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (selection=='c')
                {
                    compositeShape shape = new CarShape(e.getX(),e.getY(),100);
                    D.add(shape);
                    D.repaint();
                }
                else if (selection=='s')
                {
                    compositeShape shape = new SnowmanShape(e.getX(),e.getY(),50);
                    D.add(shape);
                    D.repaint();
                }
                else if (selection=='t')
                {
                    compositeShape shape = new TreeShape(e.getX(),e.getY(),10);
                    D.add(shape);
                    D.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        JPanel panel=new JPanel();
        panel.setBackground(Color.blue);
        panel.add(snowman);
        panel.add(car);
        panel.add(tree);


        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.NORTH);
        frame.add(D,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}