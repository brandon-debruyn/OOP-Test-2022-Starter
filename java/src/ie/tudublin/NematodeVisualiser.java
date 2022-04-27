package ie.tudublin;

import java.util.ArrayList;

import javax.swing.border.LineBorder;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NematodeVisualiser extends PApplet
{

	int choice = 5;
	float radius = 75.0f;
	float limbLength = 5.0f;
	float eyeSize = 13;
	int arrowLength = 50;
	
	float arrowRightX = width * 0.75f;
	float arrowRightY = height * 0.45f;

	float arrowLeftX = width * 0.25f;
	float arrowleftY = height * 0.45f;

	ArrayList<Nematode> nematodes = new ArrayList<Nematode>();

	public void keyPressed()
	{		
		
		if (keyCode == LEFT) {
			if(choice <= 0) {
				choice = nematodes.size() - 1;
			}
			else {
				choice -= 1;
			}
		}		
		if(keyCode == RIGHT) {
			if(choice >= nematodes.size() - 1) {
				choice = 0;
			}
			else {
				choice += 1;
			}
			
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

	// my cool function
	public void autoCycle() {
		if(frameCount % 120 == 0) {
			choice += 1;
		}
	}


	public void displayNematodes() {
		background(0);
		float halfW = width / 2;
		float boundary = 0.15f * width;
		

		float c = map(choice, 1, nematodes.size(), 0, 255);
		
		// draw name
		fill(c, 255, 255);
		textSize(35);
		textAlign(CENTER, LEFT);
		text(nematodes.get(choice).getNematodeName(), halfW, boundary);

		// draw arrows
		stroke(c, 255, 255);
		strokeWeight(4);
		noFill();

		// --- left arrow
		line(width * 0.25f - arrowLength, height * 0.45f, width * 0.30f - arrowLength, height * 0.40f);
		line(width * 0.25f - arrowLength , height * 0.45f, width * 0.25f + arrowLength, height * 0.45f);
		line(width * 0.25f - arrowLength, height * 0.45f, width * 0.30f - arrowLength, height * 0.50f);

		// --- right arrow
		line(width * 0.75f - arrowLength , height * 0.45f, width * 0.75f + arrowLength, height * 0.45f);
		line(width * 0.75f + arrowLength , height * 0.45f, width * 0.70f + arrowLength, height * 0.40f);
		line(width * 0.75f + arrowLength , height * 0.45f, width * 0.70f + arrowLength, height * 0.50f);

		// draw body
		for(int i=0; i<nematodes.get(choice).getLength(); i++) {
			
			ellipse(halfW, (2 * boundary) + ((i) * radius) , radius, radius);
			
			stroke(c, 255, 255);
			if(nematodes.get(choice).isLimbs()) {
				line(halfW + radius / 2, (2 * boundary) + ((i) * radius), halfW  + radius + limbLength, (2 * boundary) + ((i) * radius));
				line(halfW - radius / 2, (2 * boundary) + ((i) * radius), halfW - radius - limbLength, (2 * boundary) + ((i) * radius));
			}

		}

		// draw eyes
		if(nematodes.get(choice).isEyes()) {
			noFill();
			float x = halfW  + sin(PI / 4) * radius;
			float y = (2 * boundary) - cos(PI /4) * radius;
			line(x, y , halfW + radius / 2 , y + radius / 2);

			circle(x + (sin(PI / 4) * eyeSize/2), y - (cos(PI /4) * (eyeSize / 2)), eyeSize);
			
			x = halfW  + sin(3 * PI / 4) * -radius;
			y = (2 * boundary) - cos(3 * PI /4) * -radius;
			line(x , y, halfW - radius / 2, y + radius / 2);
			circle(x - ((sin(PI / 4) * eyeSize/2)), y + (cos(3 * PI /4) *(eyeSize / 2)), eyeSize);


		}

		// draw gender
		int nematodeL = nematodes.get(choice).getLength();
		char genderL = nematodes.get(choice).getGender().charAt(0);

		// male -> line + circle
		if(genderL == 'm') {
			line(halfW, (2 * boundary) + ((nematodeL - 1) * radius) + radius / 2, halfW, (2 * boundary) + ((nematodeL) * radius) + limbLength);
			circle(halfW, (2 * boundary) + ((nematodeL) * radius) + limbLength + eyeSize / 2, eyeSize);
			
		}
		// is drawn with a circle in the bottom segment
		else if(genderL == 'f') {
			circle(halfW, (2 * boundary) + ((nematodeL - 1) * radius), 0.50f * radius);
		}
		// mix
		else if(genderL == 'h') {
			line(halfW, (2 * boundary) + ((nematodeL - 1) * radius) + radius / 2, halfW, (2 * boundary) + ((nematodeL) * radius) + limbLength);
			circle(halfW, (2 * boundary) + ((nematodeL) * radius) + limbLength + eyeSize / 2, eyeSize);
			circle(halfW, (2 * boundary) + ((nematodeL - 1) * radius), 0.50f * radius);
		}
	}

	public void draw() {
		background(0);
		displayNematodes();
		autoCycle();
	}
}
