package org.hobsoft.deepart;

import javax.swing.JFrame;

public class DeepArtFrame extends JFrame
{
	public DeepArtFrame()
	{
		setTitle("DeepArt");
		setLocationByPlatform(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setContentPane(new DeepArtPanel());
		pack();
	}
}
