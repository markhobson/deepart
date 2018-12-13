package org.hobsoft.deepart;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class DeepArtPanel extends JComponent
{
	private static final int SIZE = 32 * 20;
	
	private Artwork artwork;
	
	public DeepArtPanel()
	{
		setPreferredSize(new Dimension(SIZE, SIZE));
	}
	
	public void setArtwork(Artwork artwork)
	{
		this.artwork = artwork;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		if (artwork != null)
		{
			artwork.paint((Graphics2D) graphics, getWidth(), getHeight());
		}
	}
}
