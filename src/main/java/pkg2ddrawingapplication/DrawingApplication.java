package pkg2ddrawingapplication;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LinearGradientPaint;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class DrawingApplication extends JPanel implements MouseListener, 
        MouseMotionListener{
    
    static ArrayList<Drawable> shapesDrawn;
    public JButton undo, clear;
    public Color color1 = (Color.BLACK);
    public Color color2 = (Color.BLACK);
    private final JLabel pos;
    public DrawingApplication.DrawPanel dp = new DrawPanel();
    public DrawingApplication.ControlPanel cp = new ControlPanel(dp);
    
    public DrawingApplication(){
        
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        JPanel control = new JPanel();
        pos = new JLabel("( , )");
        bottom.add(pos, BorderLayout.WEST);
        bottom.setVisible(true);
        start = end = null;
        control.add(cp);
        panel.add(control, BorderLayout.NORTH);
        panel.add(dp, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);
        panel.setVisible(true);
        add(panel, BorderLayout.PAGE_START);
        dp.setLayout(new GridLayout());
        dp.setVisible(true);
        dp.setBorder(new CompoundBorder(new LineBorder(Color.BLACK),
            new EmptyBorder(0, 0, 20, 30)));
        dp.setBackground(Color.WHITE);
        clear.addActionListener((ActionEvent event) -> {
            if (clear == event.getSource())
                shapesDrawn.clear();
            repaint();
        });            
            
        undo.addActionListener((ActionEvent event) -> {
            if (!shapesDrawn.isEmpty())
            {
                shapesDrawn.remove(shapesDrawn.size() - 1);
                repaint();}
            });
   

        }
    
    
    public static void main(String[] args){
        
        JFrame frame = new JFrame("Java 2D Drawing Application");
        frame.setSize(1000, 1000);
        final DrawingApplication drawingApplication = new DrawingApplication();
        frame.add(drawingApplication, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

    /**
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e){
        throw new UnsupportedOperationException("Not supported yet.");
}
    
    /**
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e){
        throw new UnsupportedOperationException("Not supported yet.");
}
    
    /**
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e){
        throw new UnsupportedOperationException("Not supported yet.");
}

    public class State{
        private final Color foreground;
        private final Color background;
        private final boolean filled;
        private final boolean gradient;
        private final int lineWidth;
        private final int dashLength;
        private final boolean dashed;
        
        public State(Color foreground, Color background, boolean gradient, 
                boolean filled, boolean dashed, int lineWidth, int dashLength){
            this.foreground = foreground;
            this.background = background;
            this.filled = filled;
            this.gradient = gradient;
            this.lineWidth = lineWidth;
            this.dashLength = dashLength;
            this.dashed = dashed;
}
        
public Color getForeground() { return foreground; }
public Color getBackground() {return background;}
public boolean isFilled() {return filled;}
public boolean isGradient() {return gradient;}
public int getLineWidth() {return lineWidth;}
public int getDashLength() {return dashLength;}
public boolean isDashed(){return dashed;}
}

    public class ControlPanel extends JPanel {
        
        private final JButton foreground;
        private final JButton background;;
        public final JComboBox shapes; 
        private final JCheckBox gradient, filled, dashed;
        private final JTextField lineWidth, dashLength;
        private final JLabel width, length;
        private JPanel panel;
        private final DrawPanel drawPanel;
   
        public ControlPanel(DrawPanel pane){ 
            shapes = new JComboBox<>(new String[] { "Rectangle", "Oval", "Line" });
            foreground = new JButton("1st Color");
            
            foreground.addActionListener((ActionEvent event) -> {
                color1 = JColorChooser.showDialog(null, "Pick your color", Color.BLACK);
            });
    
            background = new JButton("2nd Color");
            background.addActionListener((ActionEvent event) -> {
                color2 = JColorChooser.showDialog(null, "Pick your color.", color2);
            });
   
            gradient = new JCheckBox("Use Gradient");
            filled = new JCheckBox("Filled");
            dashLength = new JTextField("10");
            lineWidth = new JTextField("10");
            width = new JLabel("Line Width:");
            length = new JLabel("Dash Length:");
            dashed = new JCheckBox("Dashed");
            undo = new JButton("Undo");
            clear = new JButton("Clear");
            JPanel panel = new JPanel();
            GridBagConstraints gbc = new GridBagConstraints();
            panel.add(foreground);
            panel.add(background);
            setLayout(new GridBagLayout());
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1;
            gbc.gridy = 0;
            add(undo, gbc);
            gbc.gridy = 0;
            add(clear, gbc);
            gbc.gridy = 0;
            add(new JLabel("Shape: "), gbc);
            gbc.gridy = 0;
            add(shapes, gbc);
            gbc.gridy = 0;
            add(filled, gbc);
            gbc.gridy = 1;
            add(gradient, gbc);
            gbc.gridy = 1;
            add(panel, gbc);
            gbc.weighty = 1;
            gbc.gridy = 1;
            gbc.gridy = 1;
            add(width, gbc);
            gbc.gridy = 1;
            add(lineWidth, gbc);
            gbc.gridy = 1;
            add(length, gbc);
            gbc.gridy = 1;
            add(dashLength, gbc);
            gbc.gridy = 1;
            add(dashed, gbc);
            this.drawPanel = pane;
            MouseHandler mouseHandler = new MouseHandler();
            drawPanel.addMouseListener((MouseListener) mouseHandler);
            drawPanel.addMouseMotionListener((MouseMotionListener) mouseHandler);

                    }
   
        public int getDash(){
            String lng = dashLength.getText();
            int dash = Integer.parseInt(lng);
            return dash;
       }
   
        public int getLine(){
            String wth = lineWidth.getText();
            int line = Integer.parseInt(wth);
            return line;
       }
   
        protected Drawable createDrawable(){
            Drawable drawable = null;
            State state = new State(color1, color2, gradient.isSelected(), 
                    filled.isSelected(), dashed.isSelected(), getDash(), 
                    getLine());
            String selected = (String) shapes.getSelectedItem();
            if ("Rectangle".equalsIgnoreCase(selected)){
                drawable = new Square(state);
           }
       
            else if ("Oval".equalsIgnoreCase(selected)){
                drawable = new Circle(state);
           }
       
            else if ("Line".equalsIgnoreCase(selected)){
                drawable = new Line(state);
           }
       
            return drawable;
       }
   
        public class MouseHandler extends MouseAdapter{
            private Drawable drawable;                                              
            private Point clickPoint;
            
            @Override
            public void mousePressed(MouseEvent e){
                drawable = createDrawable();
                drawable.setLocation(e.getPoint());
                drawPanel.addDrawable(drawable);

   }
            
            @Override
            public void mouseMoved(MouseEvent e){
                clickPoint = e.getPoint();
                String position = "(" + e.getX() + "," + e.getY() + ")";
                pos.setText(position);
            }
            
            @Override
            public void mouseDragged(MouseEvent e){
                Point drag = e.getPoint();
                Point start = clickPoint;
                int maxX = Math.max(drag.x, start.x);
                int maxY = Math.max(drag.y, start.y);
                int minX = Math.min(drag.x, start.x);
                int minY = Math.min(drag.y, start.y);
                int width = maxX - minX;
                int height = maxY - minY;
                if( shapes.getSelectedItem().equals("Line")){       
                    if(start.x>drag.x && start.y <drag.y){
                        drawable.setLocation(clickPoint);
                        drawable.setSize(new Dimension(-width, height));
           }
       
                    else if(drag.x>start.x && drag.y<start.y){
                        drawable.setLocation( clickPoint);
                        drawable.setSize(new Dimension(width, -height));
           }
       
                    else{
                        drawable.setSize(new Dimension(width, height));
           }
       }
   
                else{
                    drawable.setLocation(new Point(minX, minY));
                    drawable.setSize(new Dimension(width, height));
       }
   
                String position = "(" + e.getX() + "," + e.getY() + ")";
                pos.setText(position);
                drawPanel.repaint();
   }
}
   }
public interface Drawable
{
   public void paint(JComponent parent, Graphics2D g2d);
   public void setLocation(Point location);
   public void setSize(Dimension size);
   public State getState();
   public Rectangle getBounds();
   }

public abstract class AbstractDrawable implements Drawable{
    private final Rectangle bounds;
    private final State state;
    
    public AbstractDrawable(State state){
        bounds = new Rectangle();
        this.state = state;
       }
   
    @Override
    public State getState(){
       return state;
       }
   
    public abstract Shape getShape();

        /**
         *
         * @param location
         */
        @Override
    public void setLocation(Point location){
        bounds.setLocation(location);
       }
    
    @Override
    public void setSize(Dimension size){
       bounds.setSize(size);
       }
   
        /**
         *
         * @return
         */
        @Override
    public Rectangle getBounds(){
        return bounds;
       }
   
        /**
         *
         * @param parent
         * @param g2d
         */
        @Override
    public void paint(JComponent parent, Graphics2D g2d){
        Shape shape = getShape();
        State state = getState();
        Rectangle bounds = getBounds();
        final float dash1[] = { state.lineWidth };
        final BasicStroke dashed = new BasicStroke(state.dashLength, 
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 
                0.0f);
        if (bounds.width > 0 && bounds.height > 0){
            if (state.isGradient()){
                if (state.isDashed()){
                    Point2D startPoint = new Point2D.Double(bounds.x, bounds.y);
                    Point2D endPoint = new Point2D.Double(bounds.x + bounds.width,
                           bounds.y + bounds.height);
                    LinearGradientPaint gp = new LinearGradientPaint(startPoint,
                            endPoint, new float[] { 0f, 1f }, new Color[] {
                                state.getForeground(), 
                                state.getBackground() });
                  
                    g2d.setPaint(gp);
                    g2d.setStroke(dashed);
                    g2d.draw(shape);
                    g2d.fill(shape);
                  
               }
               
                else
               {
                   Point2D startPoint = new Point2D.Double(bounds.x, bounds.y);
                   Point2D endPoint = new Point2D.Double(bounds.x + bounds.width,
                           bounds.y + bounds.height);  
                   LinearGradientPaint gp = new LinearGradientPaint(startPoint,
                       endPoint, new float[] { 0.2f, 0.6f }, new Color[] {
                               state.getForeground(), state.getBackground() });
                   g2d.setPaint(gp);
                   g2d.fill(shape);
                 }
               }
           
            else if (state.isFilled()){
                if (bounds.width > 0 && bounds.height > 0){
                    g2d.setPaint(state.getForeground());
                    g2d.fill(shape);
                   }
               }
           
            else if (state.isDashed()){
                g2d.setStroke(dashed);
                g2d.setPaint(state.getForeground());
                g2d.draw(shape);
               }
           
            else{
                BasicStroke stroke = new BasicStroke(state.dashLength);
                g2d.setStroke(stroke);
                g2d.setPaint(state.getForeground());
                g2d.draw(shape);
               }
           }
       }
                }
                
public class Square extends AbstractDrawable{
    public Square(State state){
        super(state);
    }

        /**
         *
         * @return
         */
        @Override
    public Shape getShape(){
        return getBounds();
    }
    }
                
public class Circle extends AbstractDrawable{
    public Circle(State state){
        super(state);
    }
                                
        /**
         *
         * @return
         */
        @Override
    public Shape getShape(){
        Rectangle bounds = getBounds();
        return new Ellipse2D.Float(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    }
                
public class Line extends AbstractDrawable{
    public Line(State state){
        super(state);
    }

        /**
         *
         * @return
         */
        @Override
    public Shape getShape(){
        Rectangle bounds = getBounds();          
        return new Line2D.Float(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height);
                                }
                }
                
public class DrawPanel extends JPanel{
    public DrawPanel(){
        shapesDrawn = new ArrayList<>();
    }
                                
        /**
         *
         * @return
         */
        @Override
    public Dimension getPreferredSize(){
        return new Dimension(500, 600);
    }
                                
        /**
         *
         * @param g
         */
        @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        shapesDrawn.forEach((d) -> {
            d.paint(this, g2d);
        });
        g2d.dispose();
    }
                                
    public void addDrawable(Drawable drawable){
        shapesDrawn.add(drawable);
    }
}
                
Point start, end;
                
    /**
     *
     * @param arg0
     */
    @Override
    public void mouseMoved(MouseEvent arg0)
                {      
                }
                
    /**
     *
     * @param arg0
     */
    @Override
    public void mouseClicked(MouseEvent arg0)
                {
                }
                
    /**
     *
     * @param arg0
     */
    @Override
    public void mouseEntered(MouseEvent arg0)
                {
                }
                
    /**
     *
     * @param arg0
     */
    @Override
    public void mouseExited(MouseEvent arg0)
                {
                }
}