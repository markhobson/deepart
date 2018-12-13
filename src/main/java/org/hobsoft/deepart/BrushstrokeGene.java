package org.hobsoft.deepart;

import io.jenetics.Gene;
import io.jenetics.util.ISeq;
import io.jenetics.util.MSeq;
import io.jenetics.util.Mean;
import io.jenetics.util.RandomRegistry;

public final class BrushstrokeGene implements Gene<Brushstroke, BrushstrokeGene>, Mean<BrushstrokeGene>
{
	private final Brushstroke stroke;
	
	private BrushstrokeGene(Brushstroke stroke)
	{
		this.stroke = stroke;
	}
	
	public static BrushstrokeGene of(Brushstroke stroke)
	{
		return new BrushstrokeGene(stroke);
	}
	
	public static ISeq<BrushstrokeGene> seq(int length)
	{
		return MSeq.<BrushstrokeGene>ofLength(length)
			.fill(() -> of(Brushstroke.random(RandomRegistry.getRandom())))
			.toISeq();
	}
	
	@Override
	public Brushstroke getAllele()
	{
		return stroke;
	}
	
	@Override
	public BrushstrokeGene newInstance()
	{
		return newInstance(Brushstroke.random(RandomRegistry.getRandom()));
	}
	
	@Override
	public BrushstrokeGene newInstance(Brushstroke stroke)
	{
		return of(stroke);
	}
	
	@Override
	public boolean isValid()
	{
		return true;
	}
	
	@Override
	public BrushstrokeGene mean(BrushstrokeGene that)
	{
		return of(getAllele().mean(that.getAllele()));
	}
	
	@Override
	public String toString()
	{
		return stroke.toString();
	}
}
