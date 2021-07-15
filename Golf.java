/*
 * Golf.java	1.0
 *
 * Written by Ken Ross, October 2006 for Programming and Problem Solving
 * project 3.
 *
 * This code is loosely based on Sun's Area.java code.
 *
 * Compile as "javac Golf.java"
 * Run as "java Golf < file.golf" to process the input file file.golf
 *        "java Golf s < file.golf" will skip the graphical display
 *        "java Golf n < file.golf" will skip the test for three or more fairways overlapping
 *
 * Sample inputs: thebull.golf (nonoverlapping), test1.golf (overlapping but valid)
 *                test2.golf (overlapping and invalid)
 *
 * Output is written to System.out and to the title bar of the window.
 * The Area of all holes, and the score are output.
 * Score = (size of bounding rectangle)/(area of all holes)
 *
 * Various geometric operations used for correctness checking employ java.awt.geom.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.util.Scanner;
import java.awt.Font;

class Hole {
    static boolean debug=false;

    int par;
    double x[];
    double y[];
    double d[];
    double cs[];  // cos and sine of angles between fairway marker points
    double sn[];
    Ellipse2D green;
    Area gr;
    Area tee;
    Double width;
    int number;
    GeneralPath fw; // fairway
    Area f;  

    Hole(int i) {number=i+1;}
	
    public void init(Scanner scanner, double widthin) {
	double tang,tang2;
	width = widthin;
	par = scanner.nextInt();
	if ((par<3) || (par>5)) {
	    System.err.println("A Par value of "+par+" is not supported.  Use 3, 4, or 5.");
	    System.exit(-1);
	}
	if (debug)
	    System.out.println("Hole number "+number+", Par is "+par);
	x = new double[par-1];
	y = new double[par-1];
	d = new double[par-2];
	cs = new double[par-2];
	sn = new double[par-2];
	fw = new GeneralPath();
	for(int i=0;i<par-1;i++) {
	    x[i] = scanner.nextDouble();
	    y[i] = scanner.nextDouble();
	}
	for(int i=0;i<par-2;i++) {
	    d[i] = Math.sqrt(Math.pow((x[i+1]-x[i]),2.0) + Math.pow((y[i+1]-y[i]),2.0)); // diagonal
	    cs[i] = (x[i+1]-x[i])/d[i]; // angle cosine
	    sn[i] = (y[i+1]-y[i])/d[i]; // angle sine
	}

	// The following calculations use some elementary trigonometry
	switch (par) {
	case 3:
	    fw.moveTo((float) (x[0]-width*sn[0]),(float) (y[0]+width*cs[0]));
	    fw.lineTo((float) (x[1]-width*sn[0]),(float) (y[1]+width*cs[0]));
	    fw.lineTo((float) (x[1]+width*sn[0]),(float) (y[1]-width*cs[0]));
	    fw.lineTo((float) (x[0]+width*sn[0]),(float) (y[0]-width*cs[0]));
	    fw.closePath();
	    break;
	case 4:
	    tang = (1.0 - (cs[1]*cs[0]+sn[1]*sn[0]))/(sn[1]*cs[0]-cs[1]*sn[0]); // tan of half the angle difference
	    fw.moveTo((float) (x[0]-width*sn[0]),(float) (y[0]+width*cs[0]));
	    fw.lineTo((float) (x[1]-width*sn[0] - width*tang*cs[0]),(float) (y[1]+width*cs[0] - width*tang*sn[0]));
	    fw.lineTo((float) (x[2]-width*sn[1]),(float) (y[2]+width*cs[1]));
	    fw.lineTo((float) (x[2]+width*sn[1]),(float) (y[2]-width*cs[1]));
	    fw.lineTo((float) (x[1]+width*sn[0]+ width*tang*cs[0]),(float) (y[1]-width*cs[0]+ width*tang*sn[0]));
	    fw.lineTo((float) (x[0]+width*sn[0]),(float) (y[0]-width*cs[0]));
	    fw.closePath();
	    break;
	case 5:
	    tang = (1.0 - (cs[1]*cs[0]+sn[1]*sn[0]))/(sn[1]*cs[0]-cs[1]*sn[0]);
	    tang2 = (1.0 - (cs[2]*cs[1]+sn[2]*sn[1]))/(sn[2]*cs[1]-cs[2]*sn[1]);
	    fw.moveTo((float) (x[0]-width*sn[0]),(float) (y[0]+width*cs[0]));
	    fw.lineTo((float) (x[1]-width*sn[0] - width*tang*cs[0]),(float) (y[1]+width*cs[0] - width*tang*sn[0]));
	    
	    fw.lineTo((float) (x[2]-width*sn[1]- width*tang2*cs[1]),(float) (y[2]+width*cs[1]- width*tang2*sn[1]));
	    fw.lineTo((float) (x[3]-width*sn[2]),(float) (y[3]+width*cs[2]));
	    fw.lineTo((float) (x[3]+width*sn[2]),(float) (y[3]-width*cs[2]));
	    
	    fw.lineTo((float) (x[2]+width*sn[1]+ width*tang2*cs[1]),(float) (y[2]-width*cs[1]+ width*tang2*sn[1]));
	    
	    fw.lineTo((float) (x[1]+width*sn[0]+ width*tang*cs[0]),(float) (y[1]-width*cs[0]+ width*tang*sn[0]));
	    fw.lineTo((float) (x[0]+width*sn[0]),(float) (y[0]-width*cs[0]));
	    fw.closePath();
	    break;
	}
	
	f = new Area(fw);


	green = new Ellipse2D.Double(x[par-2]-width,y[par-2]-width,2.0*width,2.0*width);
	gr = new Area(green);
	tee = new Area(new Ellipse2D.Double(x[0]-0.5,y[0]-0.5,1.0,1.0));
    }

    double area() {
	double total=3.1415926535*width*width/2;  // semicircle of green
	for(int i=0;i<par-2;i++) total += d[i]*2.0*width;
	return total;
    }

    boolean teeconflict(double xtee,double ytee) {
	Point2D p = new Point2D.Double(xtee,ytee);
	return (gr.contains(p) || f.contains(p));
    }

    boolean greenconflict(Area othergreen) {
	Area temp1 = (Area) othergreen.clone();
	Area temp2 = (Area) othergreen.clone();

	temp1.intersect(gr);
	temp2.intersect(f);
	return !(temp1.isEmpty() && temp2.isEmpty());
    }
	

    Area fairwayoverlap(Area otherfairway) {
	Area temp1 = (Area) otherfairway.clone();

	temp1.intersect(f);
	return temp1;
    }
	

    public void draw(Graphics2D g2) {
	// Display the hole on g2
	
	// Make coloring semitransparent, so that overlaps are visible
	AlphaComposite myAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f);
	g2.setComposite(myAlpha);
	
	// Draw fairway
	g2.setColor(Color.cyan);
	g2.fill(f);

	// Draw Tee off point
	g2.setColor(Color.orange);
	g2.draw(tee);

	//Draw green
	g2.setColor(Color.green);
	g2.fill(gr);
	g2.setColor(Color.red);
	g2.draw(gr);

	// Put a number on the green, in blue
	g2.setColor(Color.blue);
	Font font = new Font("Serif", Font.PLAIN, 10);
	g2.setFont(font);

	g2.drawString(""+number, (float) (x[par-2]-width/2.0), (float) (y[par-2]+width/4.0));  
    }
}


public class Golf extends JApplet {

    private Hole myholes[];
    private int totalholes;
    Rectangle2D bbox;
    
    public void init(Scanner scanner) {
        Demo demo = new Demo();
        getContentPane().add(demo);
	
	totalholes=18;

	myholes = new Hole[totalholes];
	
	double width = scanner.nextDouble();
	// System.out.println("Width is "+width);
	
	for(int i=0;i<totalholes;i++) {
	    myholes[i] = new Hole(i);
	    myholes[i].init(scanner,width);
	}

    }

    public double totalarea() {
	double tot=0.0;
	for(int i=0;i<totalholes;i++) {
	    tot += myholes[i].area();
	}
	return tot;
    }

    public double bboxarea() {
	Area curr = new Area();
	bbox = new Rectangle2D.Double(0.0,0.0,0.0,0.0);
	for(int i=0;i<totalholes;i++) {
	    curr.add(myholes[i].gr);
	    curr.add(myholes[i].f);
	}
	bbox = curr.getBounds2D();
	
	if ((bbox.getX()<0.0) || (bbox.getY()<0.0)) {
	    System.err.println("Warning: Parts of the course have negative coordinates and are not displayed.");
	}
	
	return bbox.getHeight()*bbox.getWidth();
    }
	
	
    
    public boolean check(boolean nanoland) {

	boolean valid = true;
	boolean temp;
	for(int i=0;i<totalholes;i++) {
	    for(int j=0;j<totalholes;j++) {
		if (i != j) {
		    temp = myholes[i].teeconflict(myholes[j].x[0],myholes[j].y[0]);
		    if (temp) {
			System.err.println("Tee of hole " + (j+1) + " overlaps hole " + (i+1));}
		    valid &= (!temp);
		    temp = myholes[i].greenconflict(myholes[j].gr);
		    if (temp) {
			System.err.println("Green of hole " + (j+1) + " overlaps hole " + (i+1));}
		    valid &= (!temp);
		    if ((i<j) && (!nanoland)) {
			Area overl = myholes[i].fairwayoverlap(myholes[j].f);
			if (!overl.isEmpty()) {
			    //System.out.println("You've managed to overlap fairway "+(i+1)+" with fairway "+(j+1));
			    for(int k=j+1;k<totalholes;k++) {
				if ((k!=i)&&(k!=j)) {
				    Area over3 = myholes[k].fairwayoverlap(overl);
				    if (!over3.isEmpty()) {
					System.err.println("Three-way overlap on fairways "+(i+1)+"," + (j+1) + "," +(k+1));
					valid = false;
				    }
				}
			    }
			}
		    }
		}
	    }
	}
	return valid;
    };
	

    /**
     * The Demo class renders the shapes.
     */
    public class Demo extends JPanel {
    
        public Demo() {
            setBackground(Color.black);
        }
    
    
        public void drawDemo(int w, int h, Graphics2D g2) {
		for(int i=0;i<totalholes;i++) {
		    myholes[i].draw(g2);
		}
		g2.setColor(Color.yellow);
		g2.draw(new Area(bbox));
        }
    
    
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Dimension d = getSize();
            g2.setBackground(getBackground());
            g2.clearRect(0, 0, d.width, d.height);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            drawDemo(d.width, d.height, g2);
	    
        }
    } // End Demo class



    public static void main(String argv[]) {

	boolean showDisplay = true;  
	// Set to false using the command line if you just want
	// to calculate the score or test for errors

	boolean nanoland = false;
	// Set to true using the command line if you don't want
	// to test for at most two fairways overlapping

	for(int i=0;i<argv.length;i++) {
	    if (argv[i].equals("n")) nanoland = true;
	    else if (argv[i].equals("s")) showDisplay = false;
	    else {
		System.err.println("Unknown option "+argv[i]);
		System.err.println("Options are n for nanoland and s to output score without the graphical display.");
		System.exit(-1);
	    }
	}
		

	Scanner scanner = new Scanner(System.in, "ISO-8859-1");

	final Golf demo = new Golf();
        demo.init(scanner);
	double baseArea=demo.totalarea();
	double boxArea=demo.bboxarea();
	boolean isItValid = demo.check(nanoland);

	if (showDisplay) {
	    Frame f = new Frame("PPS Golf Course. Valid: " + isItValid +
				"... Hole Area = "+baseArea + "... Score = "+boxArea/baseArea);
	    f.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {System.exit(0);}
		});
	    f.add(demo);
	    f.pack();
	    f.setBounds(50,50,1200,1000); // Window size is adjustable using mouse
	    f.setVisible(true);
	}

	if (isItValid)
	    System.out.println(boxArea/baseArea + " " + baseArea);  // Output score and base area
	else
	    System.out.println("Invalid layout; score would have been "+boxArea/baseArea); 

	

    }
}
