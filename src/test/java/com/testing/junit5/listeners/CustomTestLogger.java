//package junit5.listeners;
//
//import java.io.*;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//import org.junit.platform.launcher.*;
//
//public class CustomTestLogger implements TestExecutionListener {
//
//	private final File logsFolder = new File("logs"); // Logs folder
//	private final File logFile = new File(logsFolder, "test-log.txt"); // Log file in logs folder
//	private final File tempPassedFile = new File("temp-passed.txt");
//	private final Map<String, String> failedTests = new HashMap<>();
//	private final List<String> passedTests = new ArrayList<>();
//	private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//	// Ensure the logs folder exists
//	private void ensureLogsFolderExists() {
//		if (!logsFolder.exists()) {
//			boolean created = logsFolder.mkdirs();
//			System.out.println("📁 Created logs folder: " + created);
//		}
//	}
//
//	@Override
//	public void executionFinished(TestIdentifier testIdentifier,
//			org.junit.platform.engine.TestExecutionResult testExecutionResult) {
//		if (!testIdentifier.isTest())
//			return;
//
//		String displayName = testIdentifier.getDisplayName();
//		String timeStamp = LocalDateTime.now().format(dtf);
//		String entry = "[" + timeStamp + "] " + displayName;
//
//		switch (testExecutionResult.getStatus()) {
//		case SUCCESSFUL -> passedTests.add(entry + " ✅ PASSED");
//		case FAILED -> {
//			StringWriter sw = new StringWriter();
//			testExecutionResult.getThrowable().ifPresent(t -> t.printStackTrace(new PrintWriter(sw)));
//			failedTests.put(displayName, entry + " ❌ FAILED\n" + sw);
//		}
//		}
//	}
//
//	@Override
//	public void testPlanExecutionFinished(TestPlan testPlan) {
//		// Ensure the logs folder exists before writing
//		ensureLogsFolderExists();
//
//		// Keep only FAILED tests from previous log
//		try {
//			List<String> preservedFailed = new ArrayList<>();
//			if (logFile.exists()) {
//				try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
//					String line;
//					StringBuilder failureBlock = new StringBuilder();
//					boolean insideFailure = false;
//
//					while ((line = reader.readLine()) != null) {
//						if (line.contains("❌ FAILED")) {
//							insideFailure = true;
//							failureBlock.setLength(0);
//						}
//						if (insideFailure) {
//							failureBlock.append(line).append(System.lineSeparator());
//						}
//						if (line.trim().isEmpty() && insideFailure) {
//							preservedFailed.add(failureBlock.toString());
//							insideFailure = false;
//						}
//					}
//				}
//			}
//
//			// Write new log: old failures + new failures + current passed
//			try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, false))) {
//				for (String failure : preservedFailed) {
//					writer.write(failure);
//					writer.newLine();
//				}
//				for (String failure : failedTests.values()) {
//					writer.write(failure);
//					writer.newLine();
//				}
//				for (String passed : passedTests) {
//					writer.write(passed);
//					writer.newLine();
//				}
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}
