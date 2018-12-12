package org.hobsoft.deepart;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import static java.awt.Color.BLACK;

public class DeepArtPanel extends JComponent
{
	private static final int SIZE = 32;
	
	private static final int SCALE = 20;
	
	public DeepArtPanel()
	{
		setPreferredSize(new Dimension(SIZE * SCALE, SIZE * SCALE));
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		graphics.setColor(BLACK);
		graphics.fillRect(0, 0, getWidth(), getHeight());
	}
}
