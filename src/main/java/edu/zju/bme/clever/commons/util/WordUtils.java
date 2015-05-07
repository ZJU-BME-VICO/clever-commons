package edu.zju.bme.clever.commons.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class WordUtils {

	private final static Pattern PATTERN = Pattern
			.compile("(.+\\.v\\d+)\\..+$");

	public static String extractVersionMasterName(String name) {
		Matcher matcher = PATTERN.matcher(name);
		String versionMasterName = null;
		if (matcher.find()) {
			versionMasterName = matcher.group(1);
		}
		return versionMasterName;
	}

	public static String toCamelCase(List<String> words,
			boolean capitalizeFirstLetter) {
		StringBuilder sb = new StringBuilder();
		IntStream.range(0, words.size()).forEach(index -> {
			if (words.get(index).length() > 0) {
				if (capitalizeFirstLetter || index > 0) {
					sb.append(words.get(index).substring(0, 1).toUpperCase());
				} else {
					sb.append(words.get(index).substring(0, 1).toLowerCase());
				}
				sb.append(words.get(index).substring(1).toLowerCase());
			}
		});
		return sb.toString();
	}

	public static String toCamelCase(String[] words,
			boolean capitalizeFirstLetter) {
		return toCamelCase(Arrays.asList(words), capitalizeFirstLetter);
	}

	public static String capitalizeFirstLetter(String word) {
		if (word.length() > 0) {
			return word.substring(0, 1).toUpperCase() + word.substring(1);
		}
		return word;
	}

	public static String uncapitalizeFirstLetter(String word) {
		if (word.length() > 0) {
			return word.substring(0, 1).toLowerCase() + word.substring(1);
		}
		return word;
	}

	public static String filterIllegalCharacter(String word) {
		return word.replace("&", "And").replace("'", "")
				.replaceAll("[^a-zA-Z0-9 ]", " ");
	}
}
