package org.hobsoft.deepart;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public final class Brushstroke
{
	public static final double MAX_RADIUS = 0.1;
	
	private final double x;
	
	private final double y;
	
	private final double radius;
	
	private final double red;
	
	private final double green;
	
	private final double blue;
	
	public Brushstroke(double x, double y, double radius, double red, double green, double blue)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public static Brushstroke random(Random random)
	{
		double x = random.nextDouble();
		double y = random.nextDouble();
		double radius = random.nextDouble() * MAX_RADIUS;
		double red = random.nextDouble();
		double green = random.nextDouble();
		double blue = random.nextDouble();
		
		return new Brushstroke(x, y, radius, red, green, blue);
	}
	
	public double x()
	{
		return x;
	}
	
	public double y()
	{
		return y;
	}
	
	public double radius()
	{
		return radius;
	}
	
	public double red()
	{
		return red;
	}
	
	public double green()
	{
		return green;
	}
	
	public double blue()
	{
		return blue;
	}
	
	public Color color()
	{
		return new Color((float) red, (float) green, (float) blue);
	}
	
	public void paint(Graphics graphics)
	{
		int canvasWidth = graphics.getClipBounds().width;
		int canvasHeight = graphics.getClipBounds().height;
		
		int x0 = (int) ((x - radius) * canvasWidth);
		int y0 = (int) ((y - radius) * canvasHeight);
		int width = (int) (2 * radius * canvasWidth);
		int height = (int) (2 * radius * canvasHeight);
		
		graphics.setColor(color());
		graphics.fillOval(x0, y0, width, height);
	}
	
	@Override
	public String toString()
	{
		return String.format("(%.2f,%.2f,%.2f,#%s)", x, y, radius, Integer.toHexString(color().getRGB() & 0xFFFFFF));
	}
}
