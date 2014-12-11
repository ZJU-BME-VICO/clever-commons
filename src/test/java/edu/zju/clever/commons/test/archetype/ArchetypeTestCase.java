package edu.zju.clever.commons.test.archetype;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.openehr.am.archetype.Archetype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import edu.zju.bme.clever.commons.test.exception.ArchetypeNotFoundException;
import se.acode.openehr.parser.ADLParser;

public class ArchetypeTestCase {

	protected static final Logger LOGGER = LoggerFactory
			.getLogger(ArchetypeTestCase.class);
	private static final Map<String, Archetype> ARCHETYPES = new HashMap<String, Archetype>();

	static {
		URL url = ArchetypeTestCase.class.getClassLoader().getResource(
				"edu/zju/bme/clever/commons/adl");
		File directory = new File(url.getPath());
		Arrays.asList(directory.listFiles()).forEach(
				file -> {
					try {
						ADLParser parser = new ADLParser(file);
						Archetype archetype = parser.parse();
						ARCHETYPES.put(archetype.getArchetypeId().getValue(),
								archetype);
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						LOGGER.debug(
								"Parser archetype " + file.getName()
										+ " failed, nested excetion: "
										+ ex.getMessage(), ex);
					}
				});
	}

	public static Map<String, Archetype> getArchetypesById(
			String... archetypeIds) throws ArchetypeNotFoundException {
		Map<String, Archetype> result = new HashMap<String, Archetype>();
		for (String archetypeId : archetypeIds) {
			Archetype archetype = ARCHETYPES.get(archetypeId);
			if (archetype == null) {
				throw new ArchetypeNotFoundException("Cannot find archetype "
						+ archetypeId + ".");
			}
			result.put(archetypeId, archetype);
		}
		return result;
	}

	public static Archetype getArchetypeById(String archetypeId)
			throws ArchetypeNotFoundException {
		Archetype archetype = ARCHETYPES.get(archetypeId);
		if (archetype == null) {
			throw new ArchetypeNotFoundException("Cannot find archetype "
					+ archetypeId + ".");
		}
		return archetype;
	}

	public static Map<String, Archetype> getAllArchetypes() {
		return ARCHETYPES;
	}

}
