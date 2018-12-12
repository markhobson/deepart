package org.hobsoft.deepart;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class DeepArtPanel extends JComponent
{
	private static final int SIZE = 32;
	
	private static final int SCALE = 20;
	
	private Artwork artwork;
	
	public DeepArtPanel()
	{
		setPreferredSize(new Dimension(SIZE * SCALE, SIZE * SCALE));
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
			artwork.paint(graphics);
		}
	}
}
