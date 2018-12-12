package org.hobsoft.deepart;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.jenetics.Genotype;
import io.jenetics.MeanAlterer;
import io.jenetics.Optimize;
import io.jenetics.Phenotype;
import io.jenetics.TournamentSelector;
import io.jenetics.TruncationSelector;
import io.jenetics.UniformCrossover;
import io.jenetics.engine.Engine;

import static java.util.stream.Collectors.toList;

import static javax.swing.SwingUtilities.invokeLater;

import static io.jenetics.engine.EvolutionResult.toBestPhenotype;

public class Main
{
	private static final String IMAGE_PATH = "/dog4.png";
	
	private static final int MAX_GENERATIONS = 5000;
	
	private static final int POPULATION_SIZE = 50;
	
	private static final int BRUSHSTROKES = 50;
	
	public static BufferedImage IMAGE;
	public static BufferedImage GENOTYPE_IMAGE;
	
	public static void main(String[] args) throws IOException
	{
		DeepArtFrame frame = new DeepArtFrame();
		frame.setVisible(true);
		
		Phenotype<BrushstrokeGene, Double> fittest = createEngine(IMAGE_PATH)
			.stream()
			.limit(MAX_GENERATIONS)
			.peek(result -> {
				invokeLater(() -> frame.setArtwork(toArtwork(result.getBestPhenotype().getGenotype())));
				System.out.println(result.getGeneration()+" "+result.getBestFitness());
			})
			.collect(toBestPhenotype());
		
		System.out.println(fittest.getFitness());
	}
	
	private static Engine<BrushstrokeGene, Double> createEngine(String imagePath) throws IOException
	{
		BufferedImage targetImage = ImageIO.read(Main.class.getResource(imagePath));
		IMAGE = targetImage;
		int width = targetImage.getWidth();
		int height = targetImage.getHeight();
		int[] targetPixels = targetImage.getData().getPixels(0, 0, width, height, (int[]) null);
		
		BufferedImage genotypeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		GENOTYPE_IMAGE = genotypeImage;
		
		return Engine.builder(genotype -> fitness(genotype, genotypeImage, targetPixels), Genotype.of(ArtworkChromosome.of(BRUSHSTROKES)))
			.populationSize(POPULATION_SIZE)
			.optimize(Optimize.MAXIMUM)
			.maximalPhenotypeAge(50)
			.survivorsSelector(new TruncationSelector<>())
			.offspringSelector(new TournamentSelector<>(3))
			.alterers(
				new MeanAlterer<>(0.175),
				new BrushstrokeMutator<>(0.025, 0.15),
				new UniformCrossover<>(0.5)
			)
			.build();
	}
	
	private static double fitness(Genotype<BrushstrokeGene> genotype, BufferedImage genotypeImage, int[] targetPixels)
	{
		int width = genotypeImage.getWidth();
		int height = genotypeImage.getHeight();

		Graphics2D graphics = genotypeImage.createGraphics();
		toArtwork(genotype).paint(graphics, width, height);
		graphics.dispose();
		
		int[] genotypePixels = genotypeImage.getData().getPixels(0, 0, width, height, (int[]) null);
		
		int diff = 0;
		for (int i = 0; i < genotypePixels.length; i++)
		{
			diff += Math.abs(genotypePixels[i] - targetPixels[i]);
		}
		
		int maxDiff = genotypePixels.length * 256;
		return 1.0 - (double) diff / maxDiff;
	}
	
	private static Artwork toArtwork(Genotype<BrushstrokeGene> fittest)
	{
		return new Artwork(fittest
			.getChromosome()
			.stream()
			.map(BrushstrokeGene::getAllele)
			.collect(toList())
		);
	}
}
