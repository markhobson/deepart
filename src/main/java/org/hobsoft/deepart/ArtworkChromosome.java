package org.hobsoft.deepart;

import io.jenetics.AbstractChromosome;
import io.jenetics.Chromosome;
import io.jenetics.util.ISeq;

public final class ArtworkChromosome extends AbstractChromosome<BrushstrokeGene>
{
	protected ArtworkChromosome(ISeq<BrushstrokeGene> genes)
	{
		super(genes);
	}
	
	public static ArtworkChromosome of(int length)
	{
		return of(BrushstrokeGene.seq(length));
	}
	
	public static ArtworkChromosome of(ISeq<BrushstrokeGene> genes)
	{
		return new ArtworkChromosome(genes);
	}
	
	@Override
	public Chromosome<BrushstrokeGene> newInstance()
	{
		return of(length());
	}
	
	@Override
	public Chromosome<BrushstrokeGene> newInstance(ISeq<BrushstrokeGene> genes)
	{
		return of(genes);
	}
}
