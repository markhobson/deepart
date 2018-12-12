package org.hobsoft.deepart;

import java.util.stream.Collectors;

import io.jenetics.Genotype;
import io.jenetics.engine.Engine;
import io.jenetics.util.Factory;

import static io.jenetics.engine.EvolutionResult.toBestGenotype;

public class Main
{
	private static final int MAX_GENERATIONS = 1000;
	
	private static final int POPULATION_SIZE = 100;
	
	private static final int BRUSHSTROKES = 10;
	
	public static void main(String[] args)
	{
		DeepArtFrame frame = new DeepArtFrame();
		frame.setVisible(true);
		
		Factory<Genotype<BrushstrokeGene>> genotypeFactory = Genotype.of(ArtworkChromosome.of(BRUSHSTROKES));
		
		Engine<BrushstrokeGene, Integer> engine = Engine.builder(Main::fitness, genotypeFactory)
			.populationSize(POPULATION_SIZE)
			.build();
		
		Genotype<BrushstrokeGene> fittest = engine.stream()
			.limit(MAX_GENERATIONS)
			.collect(toBestGenotype());
		
		Artwork artwork = new Artwork(fittest.getChromosome()
			.stream()
			.map(BrushstrokeGene::getAllele)
			.collect(Collectors.toList())
		);
		
		frame.setArtwork(artwork);
	}
	
	private static int fitness(Genotype<BrushstrokeGene> genotype)
	{
		return genotype.getChromosome().stream()
			.map(gene -> gene.getAllele().color())
			.mapToInt(color -> color.getRed() - color.getGreen() - color.getBlue())
			.sum();
	}
}
