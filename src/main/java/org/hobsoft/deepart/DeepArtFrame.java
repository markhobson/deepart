package org.hobsoft.deepart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class DeepArtFrame extends JFrame
{
	private static final int BRUSHSTROKES = 10;
	
	private static final double MAX_RADIUS = 0.1;
	
	public DeepArtFrame()
	{
		setTitle("DeepArt");
		setLocationByPlatform(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		DeepArtPanel panel = new DeepArtPanel();
		panel.setArtwork(randomArtwork());
		setContentPane(panel);
		pack();
	}
	
	private Artwork randomArtwork()
	{
		Random random = new Random();
		List<Brushstroke> strokes = new ArrayList<>();
		
		for (int i = 0; i < BRUSHSTROKES; i++)
		{
			double x = random.nextDouble();
			double y = random.nextDouble();
			double radius = random.nextDouble() * MAX_RADIUS;
			Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
			
			strokes.add(new Brushstroke(x, y, radius, color));
		}

		return new Artwork(strokes);
	}
}
