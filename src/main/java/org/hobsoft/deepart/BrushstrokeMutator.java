package org.hobsoft.deepart;

import java.util.Random;

import io.jenetics.Chromosome;
import io.jenetics.Mutator;
import io.jenetics.MutatorResult;
import io.jenetics.util.RandomRegistry;

import static org.hobsoft.deepart.Brushstroke.MAX_RADIUS;

public class BrushstrokeMutator<C extends Comparable<? super C>> extends Mutator<BrushstrokeGene, C>
{
	private final double rate;
	
	private final double magnitude;
	
	public BrushstrokeMutator(double rate, double magnitude)
	{
		super(1.0);
		
		this.rate = rate;
		this.magnitude = magnitude;
	}
	
	@Override
	protected MutatorResult<Chromosome<BrushstrokeGene>> mutate(Chromosome<BrushstrokeGene> chromosome, double p,
		Random random)
	{
		return MutatorResult.of(
			chromosome.newInstance(chromosome.toSeq().map(this::mutate)),
			chromosome.length()
		);
	}
	
	private BrushstrokeGene mutate(BrushstrokeGene gene)
	{
		Brushstroke stroke = gene.getAllele();
		
		return gene.newInstance(new Brushstroke(
			mutate(stroke.x()),
			mutate(stroke.y()),
			Math.min(mutate(stroke.radius()), MAX_RADIUS),
			mutate(stroke.red()),
			mutate(stroke.green()),
			mutate(stroke.blue())
		));
	}
	
	private double mutate(double n)
	{
		Random random = RandomRegistry.getRandom();
		
		return random.nextDouble() < rate
			? clamp(n + (2 * random.nextDouble() - 1) * magnitude)
			: n;
	}
	
	private static double clamp(double n)
	{
		return (n < 0) ? 0 : (n > 1) ? 1 : n;
	}
}
