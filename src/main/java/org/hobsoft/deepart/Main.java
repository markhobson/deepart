package org.hobsoft.deepart;

import io.jenetics.Genotype;
import io.jenetics.Mutator;
import io.jenetics.Phenotype;
import io.jenetics.SinglePointCrossover;
import io.jenetics.engine.Engine;

import static java.util.stream.Collectors.toList;

import static javax.swing.SwingUtilities.invokeLater;

import static io.jenetics.engine.EvolutionResult.toBestPhenotype;

public class Main
{
	private static final int MAX_GENERATIONS = 5000;
	
	private static final int POPULATION_SIZE = 100;
	
	private static final int BRUSHSTROKES = 100;
	
	public static void main(String[] args)
	{
		DeepArtFrame frame = new DeepArtFrame();
		frame.setVisible(true);
		
		createEngine()
			.stream()
			.limit(MAX_GENERATIONS)
			.peek(result -> invokeLater(() -> frame.setArtwork(toArtwork(result.getBestPhenotype()))))
			.collect(toBestPhenotype());
	}
	
	private static Engine<BrushstrokeGene, Integer> createEngine()
	{
		return Engine.builder(Main::fitness, Genotype.of(ArtworkChromosome.of(BRUSHSTROKES)))
			.populationSize(POPULATION_SIZE)
			.alterers(
				new SinglePointCrossover<>(0.2),
				new Mutator<>(0.15),
				new BrushstrokeMutator<>(0.025, 0.15)
			)
			.build();
	}
	
	private static int fitness(Genotype<BrushstrokeGene> genotype)
	{
		return genotype.getChromosome().stream()
			.map(gene -> gene.getAllele().color())
			.mapToInt(color -> color.getRed() - color.getGreen() - color.getBlue())
			.sum();
	}
	
	private static Artwork toArtwork(Phenotype<BrushstrokeGene, Integer> fittest)
	{
		return new Artwork(fittest
			.getGenotype()
			.getChromosome()
			.stream()
			.map(BrushstrokeGene::getAllele)
			.collect(toList())
		);
	}
}
