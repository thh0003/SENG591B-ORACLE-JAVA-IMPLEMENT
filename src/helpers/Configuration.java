package helpers;

/**
 * This server configuration is accurate
 * for the Oracle Database 18c instance
 * during SENG 591b for Summer 2019.
 */
public class Configuration {
	public static final String
		HOST = "lrp-csdb000.systems.wvu.edu",
		PORT = "2201",
		DATABASE = "seng591",
		JDBC_URL = String.format(
			"jdbc:oracle:thin:@//%s:%s/%s",
			HOST,
			PORT,
			DATABASE
		);
}
