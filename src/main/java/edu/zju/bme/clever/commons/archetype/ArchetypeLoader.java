package edu.zju.bme.clever.commons.archetype;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openehr.am.archetype.Archetype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.acode.openehr.parser.ADLParser;

public class ArchetypeLoader {

	protected static final Logger LOGGER = LoggerFactory
			.getLogger(ArchetypeLoader.class);

	public static Map<String, Archetype> loadArchetypesFromDirectory(
			File directory) {
		Map<String, Archetype> archetypes = new HashMap<String, Archetype>();
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException(directory.getPath()
					+ " is not a directory.");
		}
		Arrays.asList(directory.listFiles()).forEach(
				file -> {
					try {
						ADLParser parser = new ADLParser(file);
						Archetype archetype = parser.parse();
						archetypes.put(archetype.getArchetypeId().getValue(),
								archetype);
					} catch (Exception ex) {
						// TODO Auto-generated catch
						// block
						LOGGER.debug(
								"Parser archetype " + file.getName()
										+ " failed, nested excetion: "
										+ ex.getMessage(), ex);
					}
				});
		return archetypes;
	}

	public static Map<String, Archetype> loadArchetypesFromDirectory(
			String directoryPath) {
		return loadArchetypesFromDirectory(new File(directoryPath));
	}

}
