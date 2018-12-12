package org.hobsoft.deepart;

import javax.swing.JFrame;

public class DeepArtFrame extends JFrame
{
	private final DeepArtPanel panel;
	
	public DeepArtFrame()
	{
		setTitle("DeepArt");
		setLocationByPlatform(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel = new DeepArtPanel();
		setContentPane(panel);
		pack();
	}
	
	public void setArtwork(Artwork artwork)
	{
		panel.setArtwork(artwork);
	}
}
