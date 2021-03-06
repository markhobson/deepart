package org.hobsoft.deepart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public final class Brushstroke
{
	public static final double MAX_RADIUS = 0.3;
	
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
	
	public void paint(Graphics2D graphics, int width, int height)
	{
		int x0 = (int) ((x - radius) * width);
		int y0 = (int) ((y - radius) * height);
		int w = (int) (2 * radius * width);
		int h = (int) (2 * radius * height);
		
		graphics.setColor(color());
		graphics.fillOval(x0, y0, w, h);
	}
	
	public Brushstroke mean(Brushstroke that)
	{
		return new Brushstroke(
			mean(x, that.x),
			mean(y, that.y),
			mean(radius, that.radius),
			mean(red, that.red),
			mean(green, that.green),
			mean(blue, that.blue)
		);
	}
	
	@Override
	public String toString()
	{
		return String.format("(%.2f,%.2f,%.2f,#%s)", x, y, radius, Integer.toHexString(color().getRGB() & 0xFFFFFF));
	}
	
	private static double mean(double x, double y)
	{
		return (x + y) / 2;
	}
}
