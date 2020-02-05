import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//this is model in mvc
class Model
{
    private ArrayList<String> list=new ArrayList<String>();
    private ChangeListener listener;
    public ArrayList<String> GetData()
    {
        return list;
    }
    public void attach(ChangeListener c)
    {
        this.listener=c;
    }

    public void addline(String content)
    {
        list.add(content);
        listener.stateChanged(new ChangeEvent(this));
    }

}
//this is view and controller in mvc
class View extends JFrame implements ChangeListener
{   private Model model;
    private ArrayList<String> data;
    JTextArea textArea=new JTextArea(20,40);

    public View(Model m)
    {
        this.model=m;
        JTextField textField = new JTextField("type something here!",20);
        data=m.GetData();
        JButton Add = new JButton("Add");
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content=textField.getText();
                m.addline(content);
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(40,80);
        this.setLayout(new BorderLayout());
        this.add(Add, BorderLayout.NORTH);
        this.add(textArea, BorderLayout.CENTER);
        this.add(textField, BorderLayout.SOUTH);
        this.setVisible(true);
        this.pack();

    }
    public void stateChanged(ChangeEvent e)
    {
        System.out.println("123");
        data = model.GetData();
        textArea.setText("");
        for (int i=0;i<data.size();i++)
        {textArea.append(data.get(i)+"\n");}
    }

}
class no implements ChangeListener
{


    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println("22");
    }
}
public class MVCTester {
    public static void main(String[] args)
    {
        Model model=new Model();
        View view=new View(model);
        no no=new no();
        model.attach(no);

        model.attach(view);

        view.setVisible(true);
    }
}
