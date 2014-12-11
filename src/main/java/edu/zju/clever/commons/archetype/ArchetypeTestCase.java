package edu.zju.clever.commons.archetype;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openehr.am.archetype.Archetype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.acode.openehr.parser.ADLParser;

public class ArchetypeTestCase {

	protected static final Logger LOGGER = LoggerFactory
			.getLogger(ArchetypeTestCase.class);

	public static Map<String, Archetype> loadArchetypesFromDirectory(
			String directoryPath) {
		Map<String, Archetype> archetypes = new HashMap<String, Archetype>();
		File directory = new File(directoryPath);
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException(directoryPath
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

}
