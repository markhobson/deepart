package org.hobsoft.deepart;

import java.awt.Graphics2D;
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
	
	public void paint(Graphics2D graphics, int width, int height)
	{
		graphics.setBackground(BLACK);
		graphics.clearRect(0, 0, width, height);
		
		for (Brushstroke stroke : strokes)
		{
			stroke.paint(graphics, width, height);
		}
	}
}
