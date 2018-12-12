package org.hobsoft.deepart;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public final class Brushstroke
{
	private static final double MAX_RADIUS = 0.1;
	
	private final double x;
	
	private final double y;
	
	private final double radius;
	
	private final Color color;
	
	public Brushstroke(double x, double y, double radius, Color color)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}
	
	public static Brushstroke random(Random random)
	{
		double x = random.nextDouble();
		double y = random.nextDouble();
		double radius = random.nextDouble() * MAX_RADIUS;
		Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		
		return new Brushstroke(x, y, radius, color);
	}
	
	public Color color()
	{
		return color;
	}
	
	public void paint(Graphics graphics)
	{
		int width = graphics.getClipBounds().width;
		int height = graphics.getClipBounds().height;
		
		graphics.setColor(color);
		graphics.fillOval((int) (x * width), (int) (y * height), (int) (radius * width), (int) (radius * height));
	}
}
