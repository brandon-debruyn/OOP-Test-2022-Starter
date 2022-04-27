package ie.tudublin;

import java.util.ArrayList;

import javax.swing.border.LineBorder;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NematodeVisualiser extends PApplet
{

	ArrayList<Nematode> nematodes = new ArrayList<Nematode>();

	public void keyPressed()
	{		
		if (keyCode == LEFT)
		{
		}		
	}


	public void settings()
	{
		size(800, 800);
	}

	public void setup() 
	{
		colorMode(HSB);
		background(0);
		smooth();		
		
		// check load -> with toString Method
		loadNematodes();
		for( Nematode n:nematodes) {
			System.out.println(n);
		}
	}
	

	public void loadNematodes() {
		Table table = loadTable("nematodes.csv", "header");

		for(TableRow r:table.rows()) {
			Nematode nematode = new Nematode(r);
			nematodes.add(nematode);
		}
	}

	int choice = 0;
	float radius = 75.0f;
	float limbLength = 5.0f;
	float eyeSize = 10;

	public void displayNematodes() {
		background(0);
		float halfW = width / 2;
		float boundary = 0.15f * width;
		

		float c = map(choice, 1, nematodes.size(), 0, 255);
		
		fill(c, 255, 255);
		textSize(35);
		textAlign(CENTER, LEFT);
		text(nematodes.get(choice).getNematodeName(), halfW, boundary);

		stroke(c, 255, 255);
		strokeWeight(5);
		noFill();
		for(int i=0; i<nematodes.get(choice).getLength(); i++) {
			
			ellipse(halfW, (2 * boundary) + ((i) * radius) , radius, radius);
			
			stroke(c, 255, 255);
			if(nematodes.get(choice).isLimbs()) {
				line(halfW + radius / 2, (2 * boundary) + ((i) * radius), halfW  + radius + limbLength, (2 * boundary) + ((i) * radius));
				line(halfW - radius / 2, (2 * boundary) + ((i) * radius), halfW - radius - limbLength, (2 * boundary) + ((i) * radius));
			}

		}

		if(nematodes.get(choice).isEyes()) {

			noFill();
			float x = halfW  + sin(PI / 4) * radius;
			float y = (2 * boundary) - cos(PI /4) * radius;
			line(x, y , halfW + radius / 2 , y + radius / 2);

			circle(x, y, eyeSize);
			
			x = halfW  + sin(3 * PI / 4) * -radius;
			y = (2 * boundary) - cos(3 * PI /4) * -radius;
			line(x , y, halfW - radius / 2, y + radius / 2);
			circle(x, y, eyeSize);

		}
			
	}

	
	public void draw() {
		background(0);
		displayNematodes();
		
	}
}
