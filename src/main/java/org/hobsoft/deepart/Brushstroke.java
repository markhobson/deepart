package org.hobsoft.deepart;

import java.awt.Color;
import java.awt.Graphics;

public class Brushstroke
{
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
	
	public void paint(Graphics graphics)
	{
		int width = graphics.getClipBounds().width;
		int height = graphics.getClipBounds().height;
		
		graphics.setColor(color);
		graphics.fillOval((int) (x * width), (int) (y * height), (int) (radius * width), (int) (radius * height));
	}
}
