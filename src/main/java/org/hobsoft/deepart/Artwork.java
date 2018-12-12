package org.hobsoft.deepart;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.BLACK;
import static java.util.Collections.emptyList;

public final class Artwork
{
	private final List<Brushstroke> strokes;
	
	public Artwork()
	{
		this(emptyList());
	}
	
	public Artwork(List<Brushstroke> strokes)
	{
		this.strokes = new ArrayList<>(strokes);
	}
	
	public void paint(Graphics graphics)
	{
		int width = graphics.getClipBounds().width;
		int height = graphics.getClipBounds().height;
		
		graphics.setColor(BLACK);
		graphics.fillRect(0, 0, width, height);
		
		for (Brushstroke stroke : strokes)
		{
			stroke.paint(graphics);
		}
	}
}
